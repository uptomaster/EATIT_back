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

  // [인증요청] — 이름/전화 필수 체크 후 임시코드 발급
  if (reqBtn) {
    reqBtn.addEventListener("click", function () {
      const name = nameInput ? nameInput.value.trim() : "";
      if (!name) { setWarn("이름을 입력해주세요.", "red"); if (nameInput) nameInput.focus(); return; }

      const phone = phoneInput ? phoneInput.value.trim().replace(/\D/g, "") : "";
      if (!phone) { setWarn("핸드폰 번호를 입력해주세요.", "red"); if (phoneInput) phoneInput.focus(); return; }

      tempCode = String(Math.floor(100000 + Math.random() * 900000));
      verified = false;

      if (codeInput) {
        codeInput.disabled = false;
        codeInput.focus();
      }

      console.log("임시 인증번호:", tempCode);
      alert("임시 인증번호는 [" + tempCode + "] 입니다.");
      setWarn("인증번호가 발급되었습니다.", "green");
    });
  }

  // [인증확인] — 코드 비교
  if (chkBtn) {
    chkBtn.addEventListener("click", function () {
      const code = codeInput ? codeInput.value.trim() : "";
      if (!code) { setWarn("인증번호를 입력해주세요.", "red"); return; }

      if (code === tempCode) {
        verified = true;
        if (codeInput) codeInput.dataset.verified = "true";
        setWarn("인증에 성공했습니다.", "green");
      } else {
        verified = false;
        if (codeInput) codeInput.dataset.verified = "false";
        setWarn("인증번호가 일치하지 않습니다.", "red");
      }
    });
  }

  // blur 시에도 성공 처리
  if (codeInput) {
    codeInput.addEventListener("blur", function () {
      const code = codeInput ? codeInput.value.trim() : "";
      if (!code || !tempCode) return;
      if (code === tempCode) {
        verified = true;
        codeInput.dataset.verified = "true";
        setWarn("인증에 성공했습니다.", "green");
      }
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