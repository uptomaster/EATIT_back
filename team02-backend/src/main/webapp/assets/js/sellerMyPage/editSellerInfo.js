document.addEventListener('DOMContentLoaded', () => {
  const memberNumber = document.getElementById("memberNumber");
  const businessNumber = document.getElementById("businessNumber");

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
	
  const open = document.getElementById("store_open_time");
  const openError = document.getElementById("store_open_time_error");
	
  const close = document.getElementById("store_close_time");
  const closeError = document.getElementById("store_close_time_error");
	
  const totalSaveBtn = document.getElementById("total_info_save_buzz");
  const editForm = document.querySelector(".edit_user_info");
	
	const defaultpw = document.getElementById("seller_password").value ;
	const defaultphone = document.getElementById("seller_phone").value;
	console.log("defaultpw   "+ defaultpw);
	console.log("defaultphone" +defaultphone);
	console.log(memberNumber);
	console.log(memberNumber.value);
	console.log(businessNumber);
	console.log(businessNumber.value);

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
		
		const TIME_24H_OR_2400 = /^(?:([01]\d|2[0-3]):[0-5]\d|24:00)$/;

		/**
		 * 입력값 검증 함수
		 * @param {HTMLInputElement} input - 시간 입력 input
		 * @param {HTMLElement} errorEl - 에러 메시지 출력 요소
		 */
		function validateTime(input, errorEl) {
		  const v = (input.value || "").trim();

		  // 빈값은 여기서 메시지 출력하지 않음 (사용자 입력 중일 수 있으니)
		  if (v === "") {
		    errorEl.textContent = "";
		    return;
		  }

		  // 허용 패턴 검사
		  if (TIME_24H_OR_2400.test(v)) {
		    errorEl.textContent = ""; // 정상
		  } else {
		    input.value = "";
		    errorEl.textContent = "HH:MM(00:00~23:59) 또는 24:00 형식으로 입력해주세요";
		  }
		}

		// blur 시점에 검증 (입력 후 포커스 이동 시)
		open?.addEventListener("blur", () => validateTime(open, openError));
		close?.addEventListener("blur", () => validateTime(close, closeError));

  
  
	if (!editForm) {
     alert("폼을 찾을 수 없습니다. (.edit_user_info 확인)");
     return;	
   }
	 
	 
  /*// --- 전체 저장 버튼 클릭 시 ---
	if (totalSaveBtn) {
	  totalSaveBtn.addEventListener("click", (e) => {
	    e.preventDefault(); // 버튼 기본 제출 방지 (MVC2에서 JS로 제어)

	    // "null" 문자열 들어온 경우 빈값 처리
	    if (newPasswordInput.value === "null") newPasswordInput.value = "";
	    if (phoneInput.value === "null") phoneInput.value = "";

	    // readonly 해제(전송 가능하게) — 기존 설계 유지
	    newPasswordInput.readOnly = false;
	    phoneInput.readOnly = false;

	    // 비밀번호 일치 검증
	    if (confirmPasswordInput && confirmPasswordInput.value !== newPasswordInput.value) {
	      alert("비밀번호가 일치하지 않습니다.");
	      return;
	    }

	    // 영업시간 형식 검증 (00:00~23:59, 24:00 허용)
	    validateTime(open, openError);
	    validateTime(close, closeError);

	    // 하나라도 비면(검증 실패로 비움) 전송 중단 + 포커스
	    if ((open && open.value.trim() === "") || (close && close.value.trim() === "")) {
	      if (open && open.value.trim() === "") {
	        alert("오픈 시간을 입력해주세요");
	        open.focus();           // 기존 코드의 openTimeInput → open 으로 수정
	      } else {
	        alert("마감 시간을 입력해주세요");
	        close.focus();
	      }
	      return;
	    }

	    // === 전송 방식 통일: URL-Encoded로 서버(.se) 서블릿에 전송 =	==
	    // 폼에서 필요한 name들을 가진 input들이 존재한다는 전제
	    // (폼 구성이 준비되어 있으면 FormData → URLSearchParams로 바꿔 붙입니다)
	    if (!editForm) {
	      alert("폼을 찾을 수 없습니다. (.edit_user_info 확인)");
	      return;
	    }
	
	    const fd = new FormData(editForm);
			console.log(memberNumber);
			console.log(memberNumber.value);
			console.log(businessNumber);
			console.log(businessNumber.value);

	    // 서버에서 필요한 최소 파라미터 확실히 포함시키기(이름은 JSP name과 동일해야 함)
	    // 예) name="newPassword", name="newPhone", name="storeOpenTime", name="storeCloseTime" 등
	    fd.set("memberNumber", memberNumber.value || "");
	    fd.set("businessNumber", businessNumber.value || "");
	    fd.set("memberPassword", newPasswordInput.value || "");
	    fd.set("memberPassword",    phoneInput.value || "");            // newPhoneInput → phoneInput으로 수정
	    fd.set("storeOpenTime", open.value || "");
	    fd.set("storeCloseTime", close.value || "");

	    const body = new URLSearchParams(fd); // application/x-www-form-urlencoded

	    fetch("/sellerMyPage/editSellerInfoOk.se", {
	      method: "POST",
	      headers: { "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8" },
	      body: body.toString()
	    })
	    .then((res) => {
	      // 컨트롤러에서 JSON 반환 시
	      const ct = res.headers.get("content-type") || "";
	      return ct.includes("application/json") ? res.json() : res.text();
	    })
	    .then((result) => {
	      // JSON or TEXT 대응
	      if (typeof result === "object" ? result.status === "success" : /success/i.test(result)) {
	        alert("저장 완료되었습니다.");
	        // 필요 시 location.reload();
	      } else {
	        alert("정보 수정에 실패했습니다.");
	      }
	    })
	    .catch((err) => {
	      console.error(err);
	      alert("서버 오류가 발생했습니다.");
	    });
	  });
	}*/
  
  
});
