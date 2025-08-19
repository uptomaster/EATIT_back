document.addEventListener("DOMContentLoaded", () => {
  const editBtn = document.querySelector(".func_button .edit");
  const deleteBtn = document.querySelector(".func_button .delete");

  // 수정 버튼 클릭 → 작성 페이지로 이동
  editBtn.addEventListener("click", () => {
    // 작성 페이지 경로는 상황에 맞게 수정
    window.location.href = "./../../app/community/writeFreeBoard.html"; 
  });

  // 삭제 버튼 클릭 → 확인창
  deleteBtn.addEventListener("click", () => {
    if (confirm("정말 삭제하시겠습니까?")) {
      alert("삭제되었습니다."); 
      window.location.href = "./../../app/community/freeBoardList.html";
    }
  });
});
