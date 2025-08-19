// gotoLogin.js (비회원이 글 누르면 로그인페이지로 이동하기)

document.addEventListener("DOMContentLoaded", () => {
  const postLinks = document.querySelectorAll("#postListBody .col_title a");

  postLinks.forEach(link => {
    link.addEventListener("click", (e) => {
      e.preventDefault(); // 기본 이동 막기
      alert("로그인 후 이용 가능합니다."); // 안내창 띄우기
      // 로그인 페이지로 이동
      window.location.href = "./../../app/logIn/login.html";
    });
  });
});
