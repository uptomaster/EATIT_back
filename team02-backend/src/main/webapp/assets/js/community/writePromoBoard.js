document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".write_form");
  const cancelBtn = document.querySelector(".cancel_btn");

  // 작성 취소 버튼 클릭 시
  cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const confirmCancel = confirm("작성 중인 글이 취소됩니다. 정말 취소하시겠습니까?");
    if (confirmCancel) {
      // 홍보게시판 목록 페이지로 이동
      window.location.href = "./promoBoardList.html";
    }
  });

  // 작성 완료 버튼 클릭 시
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const confirmSubmit = confirm("글을 작성하시겠습니까?");
    if (confirmSubmit) {
      const postId = 1234;
      alert("글이 성공적으로 작성되었습니다.");
      setTimeout(() => {
        window.location.href = `./viewOwnPost.html?postId=${postId}`;
      }, 100);
    }
  });
});
