
document.addEventListener("DOMContentLoaded", function () { 
	const form = document.getElementById("generalJoinForm") || document.querySelector("form"); 
	const base = (form && form.dataset.contextPath) ? form.dataset.contextPath : ""; 
	
	const idInput = document.getElementById("user_input_id");
	const passwordInput = document.getElementById("user_input_pw");
	const passwordConfirmInput = document.getElementById("user_input_chk_pw");
	const sendSMSBtn = document.getElementById("btn_user_input_phone");
	const phoneNumberInput = document.getElementById("user_input_phone");
	
	const checkIdMsg = document.getElementById("warning_message_chk_id");
	const checkPwMsg = document.getElementById("warning_message_pw");
	const checkPwConfirmMsg = document.getElementById("warning_message_chk_pw");
	
	const verificationCodeInput = document.getElementById("user_input_chk_phone");
	const verificationStatus = document.getElementById("warning_message_chk_code");
	idInput.addEventListener("change", function(){
		const memberId = idInput.value.trim();
		if(!memberId){
			checkIdMsg.textContent = "아이디를 입력해주세요.";
			checkIdMsg.style.color = "red";
			return;
		}
		fetch(`${base}/join/checkId.jo?memberId=${encodeURIComponent(memberId)}`,{
			headers:{"Accept":"application/json"}
		})
		.then(r => { if (!r.ok) throw new Error(r.status); return r.json(); })
		.then(data => {
		   if (data.available) {
		     checkIdMsg.textContent = "사용 가능한 아이디입니다.";
		     checkIdMsg.style.color = "green";
		   } else {
		     checkIdMsg.textContent = "이미 사용 중인 아이디입니다.";
		     checkIdMsg.style.color = "red";
		   }
		 })
		 .catch(() => {
		   checkIdMsg.textContent = "아이디 중복 검사 중 오류가 발생했습니다.";
		   checkIdMsg.style.color = "red";
	});
});
// ===== 비밀번호 유효성/일치 =====
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

passwordInput.addEventListener("blur", function () {
    const pw = passwordInput.value.trim();
    if (passwordRegex.test(pw)) {
      checkPwMsg.textContent = "사용 가능한 비밀번호입니다.";
      checkPwMsg.style.color = "green";
    } else {
      checkPwMsg.textContent = "영문, 숫자, 특수문자를 포함하여 8자 이상 입력해야 합니다.";
      checkPwMsg.style.color = "red";
    }
  });

  passwordConfirmInput.addEventListener("blur", function () {
    const pw = passwordInput.value.trim();
    const pw2 = passwordConfirmInput.value.trim();
    if (pw && pw === pw2) {
      checkPwConfirmMsg.textContent = "비밀번호가 일치합니다.";
      checkPwConfirmMsg.style.color = "green";
    } else {
      checkPwConfirmMsg.textContent = "비밀번호가 일치하지 않습니다.";
      checkPwConfirmMsg.style.color = "red";
    }
  });    

/*  const phoneRegex = /^01[016789]-\d{3,4}-\d{4}$/;
  
  // 자동 하이픈(선택) — 사용하면 타이핑 중에 자동으로 '-'가 들어가요.
  phoneNumberInput.addEventListener("input", () => {
    let v = phoneNumberInput.value.replace(/[^\d]/g, "");
    if (v.startsWith("02")) {
      if (v.length > 2) v = v.slice(0, 2) + "-" + v.slice(2);
      if (v.length > 6) v = v.slice(0, 6) + "-" + v.slice(6, 10);
    } else {
      if (v.length > 3) v = v.slice(0, 3) + "-" + v.slice(3);
      if (v.length > 8) v = v.slice(0, 8) + "-" + v.slice(8, 12);
    }
    phoneNumberInput.value = v;
  });*/
  
  // ===== SMS 발송 (임시 인증번호 생성) =====
  const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;
  (function () {
    const p = new URLSearchParams(location.search);
    if (p.get('dupPhone') === '1') alert('이미 가입된 휴대폰번호입니다. 로그인 또는 아이디/비밀번호 찾기를 이용해주세요.');
  })();
  sendSMSBtn.addEventListener("click", async function () {
    const phoneNumber = phoneNumberInput.value.trim();
    if (!phoneRegex.test(phoneNumber)) {
      alert("핸드폰 번호를 입력해주세요.");
      return;
    }
	try {
	  const r = await fetch(`${base}/join/checkPhone.jo?phone=${encodeURIComponent(phoneNumber)}`,
	    { headers: { "Accept": "application/json" } });
	  const j = await r.json();
	  if (!j.available) {
	    const phoneMsg = document.getElementById("warning_message_chk_phone");
	    if (phoneMsg) {
	      phoneMsg.textContent = "이미 가입된 휴대폰번호입니다. 로그인 또는 아이디/비밀번호 찾기를 이용해주세요.";
	      phoneMsg.style.color = "red";
	    }
	    return; // ★ 여기서 중단(문자발송 안 함)
	  }
	} catch (e) {
	  alert("휴대폰 중복 확인 중 오류가 발생했습니다.");
	  return;
	}

	fetch(`${base}/join/sendSMS.jo?memberPhoneNumber=${encodeURIComponent(phoneNumber)}`, {
				method: "GET",
				headers: {
					"Accept": "text/plain",
					"X-Requested-With": "XMLHttpRequest" // 이걸 추가해야 서버를 다시로드 하지 않고 인증번호를 받을 수 있음
				}
			})
				.then(res => {
					if (!res.ok) throw new Error("발송 실패: " + res.status);
					return res.text(); // text 형식으로 받음
				})
				.then(msg => {
					// 서버가 성공적으로 처리했을 때만 실행
					alert(msg);               // 발송 메시지
				})
				.catch(err => {
					// 실패했을 때
					alert("SMS 발송 중 오류가 발생했습니다.\n" + err);
				});
		});		
  
  // ===== 인증번호 확인 (서버 대신 로컬 비교) =====
  verificationCodeInput.addEventListener("blur", async function () {
    const code = (verificationCodeInput.value || "").trim();

    if (!code) {
      verificationStatus.textContent = "인증번호를 입력해주세요.";
      verificationStatus.style.color = "red";
      verificationCodeInput.dataset.verified = "false";
      return;
    }
    if (!/^\d{6}$/.test(code)) {
      verificationStatus.textContent = "인증번호 6자리를 정확히 입력해주세요.";
      verificationStatus.style.color = "red";
      verificationCodeInput.dataset.verified = "false";
      return;
    }

    try {
      const res = await fetch(`${base}/join/sendSMSOK.jo?verificationCode=${encodeURIComponent(code)}`, {
        method: "GET",
        headers: { "Accept": "text/plain", "X-Requested-With": "XMLHttpRequest" }
      });
      const msg = (await res.text()).trim();

      if (res.ok && msg.includes("성공")) {
        verificationStatus.textContent = "인증에 성공했습니다.";
        verificationStatus.style.color = "green";
        verificationCodeInput.dataset.verified = "true";
      } else {
        verificationStatus.textContent = msg || "인증번호가 일치하지 않습니다.";
        verificationStatus.style.color = "red";
        verificationCodeInput.dataset.verified = "false";
      }
    } catch (e) {
      verificationStatus.textContent = "요청 중 오류가 발생했습니다.";
      verificationStatus.style.color = "red";
      verificationCodeInput.dataset.verified = "false";
    }
  });

  // 사용자가 코드를 다시 입력하면 검증 상태 초기화
  verificationCodeInput.addEventListener("input", function () {
    verificationCodeInput.dataset.verified = "false";
  });
  // 번호를 바꾸면 인증상태 초기화 + 메시지 지우기
  phoneNumberInput.addEventListener("input", function () {
    verificationCodeInput.dataset.verified = "false";
    verificationStatus.textContent = "";
	const phoneMsg = document.getElementById("warning_message_chk_phone") /* 일반 */
	               || document.getElementById("warning_message_phone");   /* 판매자 */
	if (phoneMsg) phoneMsg.textContent = "";
  });
  // ===== 제출 전 체크 =====
  form.addEventListener("submit", function (e) {
	
		
    if (verificationCodeInput.dataset.verified !== "true") {
      e.preventDefault();
      alert("휴대폰 인증을 완료해주세요.");
      verificationCodeInput.focus();
      return;
    }
  });
});

