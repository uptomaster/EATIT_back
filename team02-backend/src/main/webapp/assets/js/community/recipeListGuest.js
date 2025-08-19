document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;
  let currentPage = 1;

  // flex 구조에서는 tbody가 없으니 게시글 목록 컨테이너를 새로 잡습니다.
  const listContainer = document.querySelector(".community_list");
  // 게시글 각각은 .list_row 클래스라고 가정
  const rows = Array.from(listContainer.querySelectorAll(".list_row"));
  const pagination = document.getElementById("pagination");
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");

  let filteredRows = [...rows];

  function displayList(page) {
    // 전체 게시글 숨기기
    rows.forEach(row => row.style.display = "none");
    // 보여줄 게시글만 보이게
    let start = (page - 1) * rowsPerPage;
    let end = start + rowsPerPage;
    filteredRows.slice(start, end).forEach(row => row.style.display = "flex");
    updatePagination();
  }

  function updatePagination() {
    pagination.innerHTML = "";
    let totalPages = Math.max(1, Math.ceil(filteredRows.length / rowsPerPage));

    for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement("a");
      pageLink.href = "#";
      pageLink.textContent = i;
      pageLink.className = "page" + (i === currentPage ? " active" : "");
      pageLink.addEventListener("click", (e) => {
        e.preventDefault();
        currentPage = i;
        displayList(currentPage);
      });
      pagination.appendChild(pageLink);
    }
  }

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
});
