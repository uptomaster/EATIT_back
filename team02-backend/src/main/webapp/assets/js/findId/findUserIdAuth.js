<<<<<<< HEAD
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
=======
document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".findIdAuth_input_form");
  if (!form) return;
>>>>>>> d182d8ad59917b1633fd463b5f23dabcbf9fb439

  const base = form.dataset.contextPath || "";
  const foundId = form.dataset.foundId || "";

  if (foundId) {
    alert(`회원님의 아이디는 ${foundId} 입니다.`);
    // 알럿 후 로그인으로 이동 (히스토리에 남기지 않음)
    location.replace(`${base}/login/login.lo`);
    // 만약 이전 화면으로만 돌아가고 싶다면 위 줄 대신:
    // history.back();
    // 추가로, 재제출 방지하려면 submit 버튼 비활성화
    const btn = document.querySelector(".findId_btn");
    if (btn) btn.disabled = true;
  }
});