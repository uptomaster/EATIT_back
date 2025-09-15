document.addEventListener("DOMContentLoaded", () => {
  // 엘리먼트 선택
  const listBtn = document.querySelector(".list-btn");
  const modifyBtn = document.querySelector(".modify-btn");
  //const deleteBtn = document.querySelector(".delete-btn");
  //const commentListEl = document.querySelector("#comment-list");

  // 데이터
  const itemNumber  = listBtn?.dataset.itemNumber  ?? window.itemNumber;
  const businessNumber = listBtn?.dataset.businessNumber ?? window.businessNumber;

  console.log("확인 itemNumber :", itemNumber);
  console.log("확인 businessNumber :", businessNumber);

  // 이동 버튼
  listBtn?.addEventListener("click", () => {
    window.location.href = "/sellerMyPage/storeInfo.se";
  });

  modifyBtn?.addEventListener("click", () => {
    if (!itemNumber) return alert("itemNumber가 없습니다");
    // [MV2: 서블릿 경로 사용, .se 유지]
    window.location.href = `/sellerMyPage/editFood.se?itemNumber=${encodeURIComponent(itemNumber)}`;
  });

  /*// ====== 게시글 삭제 ======
  deleteBtn?.addEventListener("click", async () => {
    if (!boardNumber) return alert("boardNumber가 없습니다");
    if (!confirm("정말 삭제하시겠습니까?")) return;

    try {
      const res = await fetch(`/board/boardDeleteOk.bo?boardNumber=${encodeURIComponent(boardNumber)}`, {
        method: "GET",
        headers: { "X-Requested-With": "XMLHttpRequest" },
      });
      if (!res.ok) throw new Error("삭제 요청 실패");

      alert("게시글이 삭제되었습니다.");
      window.location.href = "/board/boardListOk.bo";
    } catch (err) {
      console.error("게시글 삭제 실패 :", err);
      alert("게시글 삭제에 실패했습니다.");
    }*/
  });