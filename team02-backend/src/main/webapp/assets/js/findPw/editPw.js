window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('header').innerHTML = data;
    });

  // 푸터 불러오기
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    });
});

//미리 저장한 비밀번호
const dbPw = 'user123123';

// 경고메시지 출력창
const errors = document.querySelectorAll('.findPw_warning_msg');
const errorMessageOld = document.getElementById("findPw_edit_warning_message_old");
const errorMessageNew = document.getElementById("findPw_edit_warning_message_new");
const errorMessageChk = document.getElementById("findPw_edit_warning_message_chk");
//비밀번호 입력값
let oldPasswordInput = document.querySelector("#findPw_edit_oldPw");
let newPasswordInput = document.getElementById("findPw_edit_newPw");
let confirmPasswordInput = document.getElementById("findPw_edit_newPw_chk");


console.log('oldPasswordInput', oldPasswordInput);
console.log('newPasswordInput', newPasswordInput);
console.log('confirmPasswordInput', confirmPasswordInput);
// console.log('oldPassword',oldPassword);
// console.log('newPassword',newPassword);
// console.log('confirmPassword',confirmPassword);

//현재 비밀번호 확인
oldPasswordInput.addEventListener("input", () => {
  let oldpw = oldPasswordInput.value.trim();
  if (dbPw !== oldpw) {
    errorMessageOld.textContent = "현재 비밀번호와 일치하지 않습니다.";
    errorMessageOld.style.color = "red";
  } else {
    errorMessageOld.textContent = "";
  }
});

// 비밀번호 유효성 검사 정규표현식
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

newPasswordInput.addEventListener("input", () => {
  let newpw = newPasswordInput.value.trim();

  if (!passwordRegex.test(newpw)) {
    errorMessageNew.textContent = "비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.";
    errorMessageNew.style.color = "red";
  } else {
    errorMessageNew.textContent = "";
  }
});


confirmPasswordInput.addEventListener("input", () => {
  let newpw = newPasswordInput.value.trim();
  let chkpw = confirmPasswordInput.value.trim();

  if (newpw === chkpw) {
    errorMessageChk.textContent = "";
  } else {
    errorMessageChk.textContent = "입력하신 비밀번호와 일치하지 않습니다.";
    errorMessageChk.style.color = "red";
  }
});

//수정버튼 클릭시
const editBtn = document.querySelector('.findPw_edit_btn');
editBtn.addEventListener("click", () => {
  // console.log(errors.length);
  let errorCount = 0;
  // 필수동의 여부 확인
  errors.forEach((error) => {
    if (error.value !== '') {
      errorCount++;
    }
  });
  console.log(errorCount);
  if (errorCount === 0) {
    location.href = "main.html";
    return;
  }
  alert("경고메시지를 확인해주세요");
  // alert() -> return 일때 return 이 작동 안함 왜?
});