document.addEventListener('DOMContentLoaded', () => {

  const currentPasswordInput = document.getElementById("current_password");
  const currentPasswordError = document.getElementById("current_password_error");
  const newPasswordInput = document.getElementById("new_password");
  const newPasswordError = document.getElementById("new_password_error");
  const confirmPasswordInput = document.getElementById("confirm_password");
  const confirmPasswordError = document.getElementById("confirm_password_error");
  const phoneInput = document.getElementById("new_phone");
  const phoneError = document.getElementById("phone_error");
  const sendCodeBtn = document.getElementById("send_code_btn");
  const checkCodeBtn = document.getElementById("check_code_btn");
  const codeInput = document.getElementById("code_input");
  const codeError = document.getElementById("code_error");
  const totalSaveBtn = document.querySelector(".total_info_save_buzz");

  const generatedCode = "123456"; // 테스트용 인증번호 (실제는 서버 발급)

  // --- 초기 상태: 새 비밀번호 입력 불가 ---
  if (newPasswordInput && confirmPasswordInput) {
    newPasswordInput.disabled = true;
    confirmPasswordInput.disabled = true;
  }

  // --- 현재 비밀번호 입력 시 서버 검증 ---
  if (currentPasswordInput) {

    const checkCurrentPassword = () => {
      const entered = currentPasswordInput.value.trim();

      if(entered === "") {
          currentPasswordError.textContent = "";
          newPasswordInput.disabled = true;
          confirmPasswordInput.disabled = true;
          return;
      }

      fetch(`/userMyPage/checkPwOk.my?currentPassword=${encodeURIComponent(entered)}`)
          .then(res => res.json())
          .then(data => {
              if(data.result === "success"){
                  currentPasswordError.textContent = "현재 비밀번호가 일치합니다.";
                  currentPasswordError.style.color = "green";
                  newPasswordInput.disabled = false;
                  confirmPasswordInput.disabled = false;
              } else {
                  currentPasswordError.textContent = "*현재 비밀번호와 일치하지 않습니다.";
                  currentPasswordError.style.color = "red";
                  newPasswordInput.disabled = true;
                  confirmPasswordInput.disabled = true;
                  newPasswordInput.value = "";
                  confirmPasswordInput.value = "";
              }
          });
    };

    // 엔터키 입력 시 submit 방지 및 체크
    currentPasswordInput.addEventListener("keydown", e => {
      if(e.key === "Enter"){
        e.preventDefault();
        checkCurrentPassword();
      }
    });

    // 입력 시 자동 검증
    currentPasswordInput.addEventListener("input", checkCurrentPassword);
  }

  // --- 새 비밀번호 유효성 검사 ---
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;
  if (newPasswordInput && newPasswordError) {
    newPasswordInput.addEventListener("input", () => {
      if (!passwordRegex.test(newPasswordInput.value)) {
        newPasswordError.textContent = "8~16자 영문/숫자/특수문자 포함";
        newPasswordError.style.color = "red";
      } else {
        newPasswordError.textContent = "올바르게 입력되었습니다";
		newPasswordError.style.color = "green";
      }
    });
  }

  // --- 새 비밀번호 확인 ---
  confirmPasswordInput.addEventListener("input", () => {
      console.log("confirm input event"); // 이벤트 호출 확인
      if(confirmPasswordInput.value !== newPasswordInput.value){
          confirmPasswordError.textContent = "비밀번호가 일치하지 않습니다.";
          confirmPasswordError.style.color = "red";
      } else {
          confirmPasswordError.textContent = "비밀번호가 일치합니다.";
          confirmPasswordError.style.color = "green";
      }
  });

  if (confirmPasswordInput) {
        confirmPasswordInput.addEventListener("input", checkPasswordMatch);
        newPasswordInput.addEventListener("input", checkPasswordMatch); // 새 비밀번호 바뀌어도 비교
      }
  
  // --- 전화번호 숫자만 허용 ---
  if (phoneInput) {
    phoneInput.addEventListener("input", () => {
      phoneInput.value = phoneInput.value.replace(/\D/g, '');
      if (phoneError) phoneError.textContent = "";
    });
  }

  // --- 전화번호 인증 ---
  /*function isValidPhone(phone) { return /^01[0-9]{8,9}$/.test(phone); }

  if (sendCodeBtn) {
    sendCodeBtn.addEventListener("click", e => {
      e.preventDefault();
      const phone = phoneInput.value.trim();
      phoneError.textContent = "";
      if (!isValidPhone(phone)) {
        phoneError.textContent = "전화번호를 정확히 입력해주세요.";
      } else {
        alert("인증번호가 전송되었습니다.");
      }
    });
  }

  if (checkCodeBtn) {
    checkCodeBtn.addEventListener("click", e => {
      e.preventDefault();
      if (codeInput.value.trim() !== generatedCode) {
        codeError.textContent = "인증번호가 일치하지 않습니다.";
        codeError.style.color = "red";
      } else {
        codeError.textContent = "인증되었습니다.";
        codeError.style.color = "green";
      }
    });
  }*/

    // --- 전체 저장 버튼 클릭 시 ---
    if (totalSaveBtn) {
      totalSaveBtn.addEventListener("click", e => {
        const pwMatch = checkPasswordMatch();
        if (!pwMatch) {
          e.preventDefault(); 
          alert("비밀번호가 일치하지 않습니다.");
          return false;
        }
      });
    }
  

});
