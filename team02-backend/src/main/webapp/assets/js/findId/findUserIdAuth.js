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


document.addEventListener("DOMContentLoaded", function () {
  // 입력값, 버튼, 컨테이너 등 모든 요소 불러오기
  const name = document.getElementById("findIdAuth_input_name");
  const phone = document.getElementById("findIdAuth_input_phone");
  const auth = document.getElementById("findIdAuth_input_auth");
  const foMmainBtn = document.getElementById("findId_clear_to_main");
  const toLoginBtn = document.getElementById("findId_clear_to_login");
  const nameInput = document.querySelector("#findIdAuth_input_name");
  const phoneInput = document.querySelector("#findIdAuth_input_phone");
  const authInput = document.querySelector("#findIdAuth_input_auth");
  const reqAuthBtn = document.querySelector(".findIdAuth_req_auth");
  const chkAuthBtn = document.querySelector(".findIdAuth_chk_auth");
  const findIdBtn = document.querySelector(".findId_btn");
  const warningBox = document.querySelector(".findIdAuth_input_warning");
  const findId_btn_container = document.querySelector(".findId_btn_container");
  const findId_clear_btns = document.querySelector(".findId_clear_btns");
  // console.log(name);
  // console.log(phone);
  // console.log(auth);
  // console.log(nameInput);
  // console.log(phoneInput);
  // console.log(authInput);
  // console.log(name);
  // console.log(name);
  // console.log(name);

  // const nameInput = nameInputs[0];
  // const phoneInput = phoneInputs[0];
  // const authInput = authInputs[0];

  // 초기 상태: 인증번호 입력창과 아이디찾기 버튼 비활성화
  authInput.disabled = true;
  // findIdBtn.disabled = true;
  chkAuthBtn.disabled = true;
  //아이디찾았을때 버튼
  findId_clear_btns.style.display = 'none';

  findIdBtn.addEventListener("click", function () {
    if (authInput.disabled) {
      alert('전화번호인증을 수행하세요');
    }
  })

  // 미리 저장된 인증번호
  const storedAuthCode = "1234";
  // 미리 저장한 아이디
  const foundId = "user";

  // 비밀번호 유효성 검사 정규표현식
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

  //전화번호 유효성 검사
  function isValidPhone(phone) {
      const phoneRegex = /^01[0-9]{7,9}$/;
      // console.log('isValidPhone',phone);
      // console.log('isValidPhone',phoneRegex.test(phone));
      return phoneRegex.test(phone);
    }
      

  // 인증요청 버튼 클릭 시 인증번호 입력창 활성화
  reqAuthBtn.addEventListener("click", function () {
  
    if (!isValidPhone(phone.value)) {
      // console.log(2,phone);
      warningBox.textContent = "전화번호를 다시 입력하세요";
      warningBox.style.color = "red";

    } else if (name.value === '') {
      warningBox.textContent = "이름을 입력하세요";
      warningBox.style.color = "red";

    } else {
      authInput.focus();
      alert('인증번호가 발송되었습니다.');
      warningBox.style.color = "red";
      authInput.disabled = false;
      chkAuthBtn.disabled = false;
    }
    return;

  });

  // 인증확인 버튼 클릭 시 인증번호 검증
  chkAuthBtn.addEventListener("click", function () {
    const enteredCode = authInput.value.trim();

    if (enteredCode === "") {
      warningBox.textContent = "인증번호를 입력하세요.";
      warningBox.style.color = "red";
      return;
    }

    if (enteredCode === storedAuthCode) {
      alert("인증 성공!");
      warningBox.style.color = "green";
      findIdBtn.disabled = false; // 아이디찾기 버튼 활성화
      // location.replace('./../../app/findId/findUserIdSuccess.html');
      warningBox.textContent = foundId;
      findId_clear_btns.style.display = 'flex';
      findId_btn_container.style.display = 'none';
    } else {
      warningBox.textContent = "인증번호가 올바르지 않습니다.";
      warningBox.style.color = "red";
      findIdBtn.disabled = true;
    }
  });
});
