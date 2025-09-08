// /assets/js/userMyPage/editUserInfo.js
document.addEventListener('DOMContentLoaded', () => {
  // 빈 값 입력 시만 경고 지우기 같은 가벼운 UX
  const cur = document.getElementById('current_password');
  const curErr = document.getElementById('current_password_error');
  if (cur && curErr) {
    cur.addEventListener('input', () => { curErr.textContent = ''; });
  }

  // 전화번호는 숫자만 유지 (선택)
  const phone = document.getElementById('new_phone');
  if (phone) {
    phone.addEventListener('input', () => {
      phone.value = phone.value.replace(/\D/g, '');
    });
  }
  
});

//// 비밀번호 
document.addEventListener("DOMContentLoaded", () => {
  const currentPasswordInput = document.getElementById("current_password");
  const newPasswordInput = document.getElementById("new_password");
  const confirmPasswordInput = document.getElementById("confirm_password");

  // 예시: 올바른 비밀번호 (실제로는 서버에서 확인해야 함)
  //const correctPassword = "1234abcd";

  // 초기에 새 비밀번호 입력창 비활성화
  newPasswordInput.disabled = true;
  confirmPasswordInput.disabled = true;

  // 현재 비밀번호 입력칸에 입력할때  
  currentPasswordInput.addEventListener("input", () => {
    const enteredPassword = currentPasswordInput.value;
    const errorMessage = document.getElementById("current_password_error");

    if (enteredPassword !== correctPassword) {
      errorMessage.textContent = "*현재 비밀번호와 일치하지 않습니다.";
      errorMessage.style.color = "red";

      // 입력 비활성화
      newPasswordInput.disabled = true;
      confirmPasswordInput.disabled = true;

      // 내용 초기화
      newPasswordInput.value = "";
      confirmPasswordInput.value = "";
    } else {
      //placeholder 교체 및 새비밀번호 입력 활성화
      errorMessage.textContent = "";
      newPasswordInput.placeholder = "새로운 비밀번호를 입력하세요"
      newPasswordInput.disabled = false;
      confirmPasswordInput.placeholder = "새 비밀번호를 다시 입력해주세요"
      confirmPasswordInput.disabled = false;
    }
  });
});

//// 새 비밀번호 유효성 검사
const newPasswordInput = document.getElementById("new_password");
const newPasswordError = document.getElementById("new_password_error");

// 비밀번호 유효성 검사 정규표현식
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

// 새 비밀번호 입력칸에 입력이 생길때 
newPasswordInput.addEventListener("input", () => {
  const newPassword = newPasswordInput.value;

  if (!passwordRegex.test(newPassword)) {
    newPasswordError.textContent = "비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.";
    newPasswordError.style.color = "red";
  } else {
    newPasswordError.textContent = "";
  }
});

//// 새 비밀번호 재확인
const confirmPasswordInput = document.getElementById("confirm_password");
const confirmPasswordError = document.getElementById("confirm_password_error");

confirmPasswordInput.addEventListener("input", () => {
  const newPassword = document.getElementById("new_password").value;
  const confirmPassword = confirmPasswordInput.value;

  if (newPassword !== confirmPassword) {
    confirmPasswordError.textContent = "입력하신 비밀번호와 일치하지 않습니다.";
    confirmPasswordError.style.color = "red";
  } else {
    confirmPasswordError.textContent = "";
  }
});