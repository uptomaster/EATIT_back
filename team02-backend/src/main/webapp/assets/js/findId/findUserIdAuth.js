document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".findIdAuth_input_form");
  if (!form) return;

  const base = form.dataset.contextPath || "";
  const foundId = form.dataset.foundId || "";

  if (foundId) {
    alert(`회원님의 아이디는 ${foundId} 입니다.`);
    // 알럿 후 로그인으로 이동
    location.replace(`${base}/login/login.lo`);
    // 추가로, 재제출 방지하려면 submit 버튼 비활성화
    const btn = document.querySelector(".findId_btn");
    if (btn) btn.disabled = true;
  }
});