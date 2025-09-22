document.addEventListener('DOMContentLoaded', () => {

  const currentPasswordInput = document.getElementById("current_password");
  const currentPasswordError = document.getElementById("current_password_error");
  const newPasswordInput = document.getElementById("new_password");
  const newPhoneInput = document.getElementById("new_phone");
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
  const editForm = document.querySelector(".edit_user_info");

  // --- 초기 상태: 새 비밀번호 입력 불가 ---  
  if (newPasswordInput && confirmPasswordInput) {
    // disabled 대신 readonly 사용
    newPasswordInput.readOnly = true;
    confirmPasswordInput.readOnly = true;
  }

  // --- 현재 비밀번호 입력 시 서버 검증 ---
  if (currentPasswordInput) {
	const checkCurrentPassword = () => {
	  const entered = currentPasswordInput.value.trim();
	  if (entered === "") {
	    currentPasswordError.textContent = "";
	    newPasswordInput.readOnly = true;
	    confirmPasswordInput.readOnly = true;
	    return;
	  }
	  fetch(`/userMyPage/checkPwOk.my?currentPassword=${encodeURIComponent(entered)}`)
	    .then(res => res.json())
	    .then(data => {
	      if (data.result === "success") {
	        currentPasswordError.textContent = "현재 비밀번호가 일치합니다.";
	        currentPasswordError.style.color = "green";
	        newPasswordInput.readOnly = false;
	        confirmPasswordInput.readOnly = false;
			

	      } else {
	        currentPasswordError.textContent = "*현재 비밀번호와 일치하지 않습니다.";
	        currentPasswordError.style.color = "red";
	        newPasswordInput.readOnly = true;
	        confirmPasswordInput.readOnly = true;
	        newPasswordInput.value = "";
	        confirmPasswordInput.value = "";
	      }
	    });
	};

    currentPasswordInput.addEventListener("keydown", e => {
      if (e.key === "Enter") {
        e.preventDefault();
        checkCurrentPassword();
      }
    });
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
    if (confirmPasswordInput) {
      const checkPasswordMatch = () => {
        if (confirmPasswordInput.value !== newPasswordInput.value) {
          confirmPasswordError.textContent = "비밀번호가 일치하지 않습니다.";
          confirmPasswordError.style.color = "red";
          return false;
        } else {
          confirmPasswordError.textContent = "비밀번호가 일치합니다.";
          confirmPasswordError.style.color = "green";
          return true;
        }
      };
      confirmPasswordInput.addEventListener("input", checkPasswordMatch);
      newPasswordInput.addEventListener("input", checkPasswordMatch);
    }

    // --- 전화번호 숫자만 허용 ---
    if (phoneInput) {
      phoneInput.addEventListener("input", () => {
        phoneInput.value = phoneInput.value.replace(/\D/g, '');
        if (phoneError) phoneError.textContent = "";
      });
    }

    // --- SMS 인증번호 전송 (AJAX) ---
    if (sendCodeBtn) {
      sendCodeBtn.addEventListener("click", e => {
        e.preventDefault();
        const phone = phoneInput.value.trim();
        if (!phone) {
          phoneError.textContent = "전화번호를 입력해주세요.";
          phoneError.style.color = "red";
          return;
        }
        fetch(`/userMyPage/userMyPageSmsSend.my?mode=send&newPhone=${encodeURIComponent(phone)}`)
          .then(res => res.text())
          .then(msg => {
            phoneError.textContent = msg;
            phoneError.style.color = "green";
          })
          .catch(err => {
            phoneError.textContent = "SMS 전송 실패";
            phoneError.style.color = "red";
            console.error(err);
          });
      });
    }

    // --- 인증번호 확인 (AJAX) ---
    if (checkCodeBtn) {
      checkCodeBtn.addEventListener("click", e => {
        e.preventDefault();
        const code = codeInput.value.trim();
        if (!code) return;
        fetch(`/userMyPage/userMyPageSmsSend.my?mode=check&phoneCode=${encodeURIComponent(code)}`)
          .then(res => res.text())
          .then(msg => {
            codeError.textContent = msg;
            codeError.style.color = msg === "인증 성공" ? "green" : "red";
          })
          .catch(err => {
            codeError.textContent = "인증 오류";
            codeError.style.color = "red";
            console.error(err);
          });
      });
    }
  
  
  
  
  
  
  // --- 전체 저장 버튼 클릭 시 ---
  if (totalSaveBtn) {
    totalSaveBtn.addEventListener("click", e => {
      e.preventDefault();

      // "null" 값 처리
      if (newPasswordInput.value === "null") newPasswordInput.value = "";
      if (newPhoneInput.value === "null") newPhoneInput.value = "";

      // FormData 생성 전에 readonly 해제 (전송 가능하게)
      newPasswordInput.readOnly = false;
      newPhoneInput.readOnly = false;

      console.log("newPasswordInput:", newPasswordInput.value);
      console.log("newPhoneInput:", newPhoneInput.value);

      const formData = new FormData(editForm);

      // 비밀번호 일치 체크
      if (confirmPasswordInput && confirmPasswordInput.value !== newPasswordInput.value) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      }

      // 서버 전송
	  fetch("/userMyPage/editUserInfoOk.my", {
	    method: "POST",
	    headers: { "Content-Type": "application/x-www-form-urlencoded" },
	    body: `newPassword=${encodeURIComponent(newPasswordInput.value)}&newPhone=${encodeURIComponent(newPhoneInput.value)}`
	  })
      .then(res => res.json())
      .then(result => {
        if (result.status === "success") {
          alert("저장 완료되었습니다.");
		  window.location.reload();
        } else {
          alert("정보 수정에 실패했습니다.");
        }
      })
      .catch(err => {
        console.error(err);
        alert("서버 오류가 발생했습니다.");
      });
    });
  }

  
  
});
