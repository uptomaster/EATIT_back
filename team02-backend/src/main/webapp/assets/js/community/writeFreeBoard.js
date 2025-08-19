// 해야될것 : 작성 취소 버튼 눌렀을때 팝업창뜨게하고 자유게시판으로 이동
// 작성완료 버튼 눌렀을때 팝업창뜨게하고 해당 글 조회페이지로 이동

// 자유게시판 -> 글쓰다가 작성 취소/작성완료 했을때 기능들

document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".write_form");
  const cancelBtn = document.querySelector(".cancel_btn");
  const submitBtn = document.querySelector(".submit_btn");

  // 작성 취소 버튼 클릭 시
  cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const confirmCancel = confirm("작성 중인 글이 취소됩니다. 정말 취소하시겠습니까?");
    if (confirmCancel) {
      // 자유게시판 목록 페이지로 이동
      window.location.href = "./freeBoardList.html";
    }
  });

  // 작성 완료 버튼 클릭 시
  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const confirmSubmit = confirm("글을 작성하시겠습니까?");
    if (confirmSubmit) {
      // 실제 글 작성 처리 후, 글 번호(postId) 등을 받아와야 하는데,
      // 예시로 임의 postId = 123으로 지정
      const postId = 123;

      alert("글이 성공적으로 작성되었습니다.");

      // 해당 글 조회 페이지로 이동
      window.location.href = `./viewOwnPost.html?postId=${postId}`;
    }
  });
});
