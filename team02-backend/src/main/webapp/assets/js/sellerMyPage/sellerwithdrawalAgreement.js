document.addEventListener("DOMContentLoaded", () => {
  // 1) 실제 JSP의 클래스명으로 폼 선택
  const form = document.querySelector(".seller_withdrawalagreement");

  // 방어코드: 폼이 없으면 종료 (로딩 순서나 include 문제 디버깅 도움)
  if (!form) {
    console.warn("[탈퇴동의] 폼(.seller_withdrawalagreement)을 찾을 수 없습니다.");
    return;
  }

  // 2) 요소 참조 (없어도 동작하게 널 체크)
  const passwordInput = document.getElementById("seller_passwordInput");
  const passwordError = document.getElementById("seller_passwordError"); // (선택) JSP에 span 추가 시 사용
  const radioError = document.getElementById("seller_radioError");       // (선택) JSP에 span 추가 시 사용

  // 3) 제출 시 클라이언트 검증
  form.addEventListener("submit", (e) => {
    // 에러메시지 영역 초기화(존재할 때만)
    if (passwordError) passwordError.textContent = "";
    if (radioError) radioError.textContent = "";

    let valid = true;

    // (1) 비밀번호 공란 체크
    const pw = passwordInput ? passwordInput.value.trim() : "";
    if (!pw) {
      valid = false;
      if (passwordError) {
        passwordError.textContent = "* 비밀번호를 입력해주세요.";
        passwordError.style.color = "red";
      } else {
        alert("현재 비밀번호를 입력해주세요.");
      }
    }

    // (2) 동의 라디오 체크 여부
    const agreed = form.querySelector("input[name='agree']:checked");
    if (!agreed) {
      valid = false;
      if (radioError) {
        radioError.textContent = "* 탈퇴 동의에 체크해주세요.";
        radioError.style.color = "red";
      } else {
        alert("회원탈퇴에 동의하셔야 진행할 수 있습니다.");
      }
    }

    // (3) 하나라도 실패면 제출 막기
    if (!valid) {
      e.preventDefault();
      return;
    }

    // 통과 시 서버로 정상 제출 → JSP의 action="/sellerMyPage/withdrawOk.se"로 이동 (서블릿 경로 통과)
  });
});