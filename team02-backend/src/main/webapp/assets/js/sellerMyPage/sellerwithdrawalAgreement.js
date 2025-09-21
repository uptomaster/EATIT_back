window.addEventListener("load", function () {
    const form = document.querySelector(".withdrawalagreement");
    const agreeRadio = form.querySelector("input[name='agree']");

    form.addEventListener("submit", function (e) {
        if (!agreeRadio.checked) { // 체크 안 되어 있으면
            e.preventDefault(); // 제출 막기
            alert("회원탈퇴에 동의하셔야 진행할 수 있습니다.");
        }
    });
});


document.addEventListener("DOMContentLoaded", () => {
  const withdrawBtn = document.getElementById("seller_withdrawBtn");
  const passwordInput = document.getElementById("seller_passwordInput");
  const passwordError = document.getElementById("seller_passwordError");
  const radioError = document.getElementById("seller_radioError");

  withdrawBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 초기화
    passwordError.textContent = "";
    radioError.textContent = "";

    let isValid = true;
    const password = passwordInput.value.trim();
    const isAgreed = document.querySelector('input[name="agree"]:checked');

    // 비밀번호 입력 여부 확인
    if (!password) {
      passwordError.textContent = "* 비밀번호를 입력해주세요.";
      passwordError.style.color = "red";
      isValid = false;
    }

    // 탈퇴 동의 체크 여부 확인
    if (!isAgreed) {
      radioError.textContent = "* 탈퇴 동의에 체크해주세요.";
      radioError.style.color = "red";
      isValid = false;
    }

    // 유효성 통과 시
    if (isValid) {
      alert("탈퇴되었습니다.");
      window.location.href = "./../../main.html";
    }
  });
});