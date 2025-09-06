document.addEventListener("DOMContentLoaded", () => {
  const listBody = document.getElementById("postListBody");
  const rows = Array.from(listBody.querySelectorAll(".list_row"));
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");
  const moveTitle = document.querySelectorAll(".col_title a");

  let filteredRows = [...rows];
  let currentPage = 1;
  const rowsPerPage = 10;

  function displayList(page) {
    listBody.innerHTML = ""; // 기존 목록 초기화

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const rowsToDisplay = filteredRows.slice(start, end);

    if (rowsToDisplay.length === 0) {
      listBody.innerHTML = `<div class="no-results">검색결과가 없습니다.</div>`;
      return;
    }

    rowsToDisplay.forEach(row => listBody.appendChild(row));
  }

  function searchList() {
    const query = searchInput.value.trim().toLowerCase();
    filteredRows = rows.filter(row => {
      const title = row.querySelector(".col_title").textContent.toLowerCase();
      return title.includes(query);
    });
    currentPage = 1;
    displayList(currentPage);
  }

  // 검색 이벤트
  searchBtn.addEventListener("click", (e) => {
    e.preventDefault();
    searchList();
  });

  searchInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });

  // 게시글 제목 클릭 시 a태그 href 이동
  moveTitle.forEach(link => {
    link.addEventListener("click", function(e) {
      e.preventDefault();
      const href = this.getAttribute("href");
      window.location.href = href;
    });
  });

  // 글쓰기 버튼 - 관리자만 작성 가능 (필요시 주석 해제)
  /*
  const writeBtn = document.getElementById("writeBtn");
  writeBtn.addEventListener("click", function(event) {
    event.preventDefault();
    alert("관리자만 작성할 수 있습니다.");
    window.location.href = window.location.pathname;
  });
  */

  // 초기 목록 표시
  displayList(currentPage);
});
