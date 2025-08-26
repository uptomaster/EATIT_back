document.addEventListener("DOMContentLoaded", () => {

  const listBody = document.getElementById("postListBody");
  const rows = Array.from(listBody.querySelectorAll(".list_row"));
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");
  const moveTitle= document.querySelectorAll(".col_title a");

  let filteredRows = [...rows];


  function searchList() {
    const query = searchInput.value.trim().toLowerCase();
    filteredRows = rows.filter(row => row.textContent.toLowerCase().includes(query));
    currentPage = 1;
    displayList(currentPage);
  }

  searchBtn.addEventListener("click", searchList);
  searchInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });

  displayList(currentPage);
  
  //게시글 제목 클릭시 a태그 href 경로 이동
  	moveTitle.forEach(link => {
      link.addEventListener("click", function(e) {
        e.preventDefault(); // 기본 링크 이동 막기
		const href = this.getAttribute("href");
        window.location.href = href; 
      });
    });
});



/*글쓰기 버튼 - 공지사항/이벤트는 관리자만 작성할 수 있으므로 주석처리 진행 이후 필요시 사용예정*/
/*document.addEventListener("DOMContentLoaded", function() {
  const writeBtn = document.getElementById("writeBtn");
  writeBtn.addEventListener("click", function(event) {
    event.preventDefault(); // 기본 링크 이동 막기
    alert("관리자만 작성할 수 있습니다.");
    window.location.href = window.location.pathname;
  });
});
