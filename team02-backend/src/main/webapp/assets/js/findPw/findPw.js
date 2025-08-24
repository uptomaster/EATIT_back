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



const btn = document.getElementsByClassName('findPw_btn');
btn[0].addEventListener('click', () => {
  location.replace('./../../app/findPW/editPw.html');
  alert('문자로 임시비밀번호가 발송되었습니다.')
});

const idInput = document.getElementById('findPw_input_id');
const phoneInput = document.getElementById('findPw_input_phone');

// 구현실패로 주석처리
// btn[0].addEventListener('click', () => {
//   //입력한 아이다와 해당하는 회원이 있을때
//   //입력한 전화번호와 아이디로 찾은 회원의 전화번호와 동일할떄
//   // 전화번호 유효성 검사
//   //전화번호 유효성 검증
//   function isValidPhone(phone) {
//     const phoneRegex = /^01[0-9]{8,9}$/;
//     return phoneRegex.test(phone);
//   }
//   if (!isValidPhone(phoneInput.value)) {
//     phoneError.textContent = "전화번호를 입력해주세요.";
//     phoneError.style.color ='red';
//   } else {
//     location.replace('./../../app/findPW/editPw.html');
//     alert('문자로 임시비밀번호가 발송되었습니다.')
//   }
// });