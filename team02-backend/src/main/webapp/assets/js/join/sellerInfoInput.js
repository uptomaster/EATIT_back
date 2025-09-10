document.addEventListener("DOMContentLoaded", function () { 
	const form = document.getElementById("sellerJoinForm") || document.querySelector("form"); 
	const base = (form && form.dataset.contextPath) ? form.dataset.contextPath : ""; 
	
	const idInput = document.getElementById("seller_input_id");
	const passwordInput = document.getElementById("seller_input_pw");
	const passwordConfirmInput = document.getElementById("seller_input_chk_pw");
	const sendSMSBtn = document.getElementById("btn_seller_input_phone");
	const phoneNumberInput = document.getElementById("seller_input_phone");
	
	const checkIdMsg = document.getElementById("warning_message_chk_id");
	const checkPwMsg = document.getElementById("warning_message_pw");
	const checkPwConfirmMsg = document.getElementById("warning_message_chk_pw");
	const verificationCodeInput = document.getElementById("seller_input_chk_phone");
	const verificationStatus = document.getElementById("warning_message_chk_phone");
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

  
  // ===== SMS 발송 (임시 인증번호 생성) =====
  
  const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;

  sendSMSBtn.addEventListener("click", function () {
    const phoneNumber = phoneNumberInput.value.trim();
    if (!phoneRegex.test(phoneNumber)) {
      alert("핸드폰 번호를 입력해주세요.");
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
  });
  
  const searchBtn = document.getElementById("searchPostcodeBtn");
  if (searchBtn) {
    searchBtn.addEventListener("click", function () {
      new daum.Postcode({
        oncomplete: function (data) {
          // 1) 우편번호
          document.getElementById("seller_input_store_zip").value = data.zonecode || "";

          // 2) 메인 주소(도로명 또는 지번 한 칸만)
          //    - 참고항목(동/건물명)은 괄호로 덧붙임
          var isRoad = data.userSelectedType === "R";
          var base   = isRoad ? (data.roadAddress || "") : (data.jibunAddress || "");
          var extra  = "";

          if (isRoad) {
            if (data.bname && /[동|로|가]$/.test(data.bname)) extra += data.bname;
            if (data.buildingName && data.apartment === "Y") {
              extra += (extra ? ", " : "") + data.buildingName;
            }
          }

          var main = base + (extra ? " (" + extra + ")" : "");
          document.getElementById("seller_input_store_address").value = main;

          // 3) 상세주소 포커스
          document.getElementById("seller_input_store_address_detail").focus();
        }
      }).open({ popupTitle: "우편번호 검색" });
    });
  }
  
  // ===== 사업자등록번호 (3-2-5) 자동 하이픈 & 검증 =====
  const bizNoInput  = document.getElementById('seller_input_store_number');

  if (bizNoInput) {
    // 입력 중 자동 하이픈
    bizNoInput.addEventListener('input', () => {
      let v = bizNoInput.value.replace(/\D/g, '').slice(0, 10); // 숫자만, 최대 10자리
      if (v.length > 5)        v = `${v.slice(0,3)}-${v.slice(3,5)}-${v.slice(5)}`;
      else if (v.length > 3)   v = `${v.slice(0,3)}-${v.slice(3)}`;
      bizNoInput.value = v;
    });
	}
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