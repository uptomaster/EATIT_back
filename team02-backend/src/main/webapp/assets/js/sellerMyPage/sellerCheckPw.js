window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header_login.html')
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

document.addEventListener("DOMContentLoaded", () => {
  // 예시: 올바른 비밀번호 (실제로는 서버에서 확인해야 함)
  const correctPassword = "user123123";

  // 정보 입력 전 비밀번호 확인
  const pwInfo = document.getElementById('seller_chk_pw_info');
  const chkPwBtn = document.getElementById('seller_chk_pw_btn');
  const Warning = document.getElementById('seller_chk_pw_warning');

  chkPwBtn.addEventListener("click", () => {
    let Password = pwInfo.value;

    if (Password !== correctPassword) {
      Warning.textContent = "입력하신 비밀번호와 일치하지 않습니다.";
      Warning.style.color = "red";
      // console.log('wrong');
      // console.log('current', correctPassword);
      // console.log('input', typeof(Password), Password);
      
    } else {
      console.log('correct');
      Warning.textContent = "";
      window.location.href = './editSellerInfo.html';
    }
  })
})

let withdraw = document.getElementById('seller_withdraw_buzz');
withdraw.addEventListener('click', () => {
  location.replace('./../sellerMyPage/sellerwithdrawalAgreement.html');
});