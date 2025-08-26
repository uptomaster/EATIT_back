document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".write_form");
  const cancelBtn = document.querySelector(".cancel_btn");

  // 작성 취소 버튼
  cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const confirmCancel = confirm("작성 중인 글이 취소됩니다. 정말 취소하시겠습니까?");
    if (confirmCancel) {
      // 자유게시판 목록으로 이동
      window.location.href = `${form.action.replace('writeFreeBoardOK.co', 'freeBoardListOk.co')}`;
    }
  });

  // 작성 완료 버튼
  form.addEventListener("submit", (e) => {
    const confirmSubmit = confirm("글을 저장하시겠습니까?");
    if (!confirmSubmit) {
      e.preventDefault(); // 취소 눌렀을 때만 제출 막음
    } else {
      alert("글이 성공적으로 작성되었습니다.");
      // 여기서 e.preventDefault() 하지 않음 → form이 그대로 multipart 전송됨
      // WriteFreeBoardOKController에서 DB 저장 + redirect 처리
    }
  });
});
