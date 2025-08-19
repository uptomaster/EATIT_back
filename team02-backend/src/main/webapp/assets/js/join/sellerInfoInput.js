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

//미리 저장된 아이디
const dbId = 'user'

const chkIdBtn = document.getElementById('btn_seller_input_hasSameId');
chkIdBtn.addEventListener('click', function () {
  const sellerInputId = document.getElementById('seller_input_id');
  // 아이디 중복 검사
  const warningSpan = document.getElementById('warning_message_chk_id');
  const inputId = sellerInputId.value.trim();

  if (!inputId) {
    warningSpan.textContent = '아이디를 입력하세요';
    warningSpan.style.display = 'block';
  } else if (inputId === dbId) {
    warningSpan.textContent = '중복된 아이디 입니다.';
    warningSpan.style.display = 'block';
  } else {
    warningSpan.textContent = '이 아이디를 사용할 수 있습니다.';
    warningSpan.style.display = 'block';
    warningSpan.style.color = 'green';
  }
});

//비밀번호 입력값
const newPasswordError = document.getElementById("warning_message_chk_pw");
let newPasswordInput = document.getElementById("seller_input_pw");
let confirmPasswordInput = document.getElementById("seller_input_chk_pw");

// 비밀번호 유효성 검사 정규표현식
const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

newPasswordInput.addEventListener("input", () => {
  let newPassword = newPasswordInput.value;

  if (!passwordRegex.test(newPassword)) {
    newPasswordError.textContent = "비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.";
    newPasswordError.style.color = "red";
  } else {
    newPasswordError.textContent = "";
  }
});

confirmPasswordInput.addEventListener("input", () => {
  let newPassword = newPasswordInput.value;
  let confirmPassword = confirmPasswordInput.value;

  if (newPassword !== confirmPassword) {
    newPasswordError.textContent = "입력하신 비밀번호와 일치하지 않습니다.";
    newPasswordError.style.color = "red";
  } else {
    newPasswordError.textContent = "";
  }
});
////// 전화번호
//항상
document.addEventListener("DOMContentLoaded", () => {
  //인증요청, 인증 확인 버튼
  const sendCodeBtn = document.getElementById("btn_seller_input_phone");
  const checkCodeBtn = document.getElementById("btn_seller_input_chk_phone");

  //전화번호 입력
  const phoneInput = document.getElementById("seller_input_phone");
  const phoneError = document.getElementById("warning_message_phone");

  //전화번호 인증 입력
  const codeInput = document.getElementById("seller_input_chk_phone");
  const codeError = document.getElementById("warning_message_chk_phone");

  // 미리 저장한 인증번호
  const generatedCode = "1234";
  // 인증번호 요청 전 인증번호 칸과 버튼은 비활성화
  checkCodeBtn.disabled = true;
  checkCodeBtn.style.color = 'grey';
  codeInput.disabled = true;


  //전화번호 유효성 검증
  function isValidPhone(phone) {
    const phoneRegex = /^01[0-9]{8,9}$/;
    return phoneRegex.test(phone);
  }

  //전화번호 확인
  sendCodeBtn.addEventListener("click", (e) => {
    e.preventDefault();
    // 전화번호 값만 저장
    const phone = phoneInput.value.trim();
    phoneError.textContent = "";

    if (!isValidPhone(phone)) {
      phoneError.textContent = "전화번호를 입력해주세요.";
    } else {
      checkCodeBtn.disabled = false;
      checkCodeBtn.style.color = 'white';
      codeInput.disabled = false;
      alert("인증번호가 전송되었습니다.");
    }
  });

  //인증번호 확인
  checkCodeBtn.addEventListener("click", (e) => {
    e.preventDefault();
    //인증번호 값만 저장
    const inputCode = codeInput.value.trim();
    codeError.textContent = "";

    if (inputCode !== generatedCode) {
      codeError.textContent = "인증번호가 일치하지 않습니다.";
    } else {
      alert("인증되었습니다.");
    }
  });
});

//가입하기 버튼을 눌렀을때
const essenInfos = document.querySelectorAll("input");
function goNextPage() {
  // const totalEssenCount = essenInfos.length;
  let essenCount = 0;
  // 필수 정보 전체 입력됐는지 여부 확인
  essenInfos.forEach((essenInfo) => {
    if (essenInfo.value !== '') {
      essenCount++;
    }
  });
  console.log(essenCount);
  if (essenCount === essenInfos.length) {
    //모든 체크박스 체크 시 다음 페이지로 이동
    location.href = "successJoin.html";
    return;
  }
  alert("필수 정보를 모두 입력하셔야 다음단계로 이동할 수 있습니다.");
  // alert() -> return 일때 return 이 작동 안함 왜?
}