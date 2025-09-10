document.addEventListener("DOMContentLoaded", function () {
  const form     = document.querySelector(".findIdAuth_input_form");
  if (!form) return;

  const base     = form.dataset.contextPath || "";
  const foundId  = form.dataset.foundId || "";

  if (foundId) {
    alert("회원님의 아이디는 " + foundId + " 입니다.");
    location.replace(base + "/login/login.lo");
    const btn = document.querySelector(".findId_btn");
    if (btn) btn.disabled = true;
    return;
  }

  // 요소
  const nameInput = document.getElementById("findIdAuth_input_name");
  const phoneInput = document.getElementById("findIdAuth_input_phone");
  const codeInput  = document.getElementById("findIdAuth_input_auth");
  const reqBtn     = document.querySelector(".findIdAuth_req_auth"); // 인증요청
  const chkBtn     = document.querySelector(".findIdAuth_chk_auth"); // 인증확인
  const warnEl     = document.querySelector(".findIdAuth_input_warning");

  // 상태
  let tempCode = "";
  let verified = false;

  // 숫자만(하이픈 제거)
  if (phoneInput) {
    phoneInput.addEventListener("input", function () {
      phoneInput.value = (phoneInput.value || "").replace(/\D/g, "");
    });
  }
  const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;

  reqBtn.addEventListener("click", function () {
    const phoneNumber = phoneInput.value.trim();
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
	
	
  // [인증확인] — 코드 비교
  if (chkBtn) {
    chkBtn.addEventListener("click", function () {
      const code = codeInput ? codeInput.value.trim() : "";
      if (!/^\d{6}$/.test(code)) {
        setWarn("인증번호 6자리를 정확히 입력해주세요.", "red");
        codeInput?.focus();
        verified = false;
        codeInput && (codeInput.dataset.verified = "false");
        return;
      }

      fetch(`${base}/join/sendSMSOK.jo?verificationCode=${encodeURIComponent(code)}`, {
        method: "GET",
        headers: { "Accept": "text/plain", "X-Requested-With": "XMLHttpRequest" }
      })
      .then(res => res.text().then(t => ({ ok: res.ok, text: t.trim() })))
      .then(({ ok, text }) => {
        if (ok && text.includes("성공")) {
          setWarn("인증에 성공했습니다.", "green");
          verified = true;
          codeInput && (codeInput.dataset.verified = "true");
        } else {
          setWarn(text || "인증번호가 일치하지 않습니다.", "red");
          verified = false;
          codeInput && (codeInput.dataset.verified = "false");
        }
      })
      .catch(() => {
        setWarn("요청 중 오류가 발생했습니다.", "red");
        verified = false;
        codeInput && (codeInput.dataset.verified = "false");
      });
    });
  }


  // [폼 제출] — 이름/전화/인증 최종검사
  form.addEventListener("submit", function (e) {
    const name = nameInput ? nameInput.value.trim() : "";
    if (!name) { e.preventDefault(); setWarn("이름을 입력해주세요.", "red"); if (nameInput) nameInput.focus(); return; }

    if (phoneInput) phoneInput.value = (phoneInput.value || "").replace(/\D/g, "");
    const phone = phoneInput ? phoneInput.value.trim() : "";
    if (!phone) { e.preventDefault(); setWarn("핸드폰 번호를 입력해주세요.", "red"); if (phoneInput) phoneInput.focus(); return; }

    if (!verified) {
      e.preventDefault();
      setWarn("휴대폰 인증이 필요합니다.", "red");
      if (codeInput) codeInput.focus();
    }
  });

  function setWarn(msg, color) {
    if (!warnEl) return;
    warnEl.textContent = msg;
    if (color) warnEl.style.color = color;
  }
});