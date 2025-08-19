document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;
  let currentPage = 1;

  const listBody = document.getElementById("postListBody");
  const rows = Array.from(listBody.querySelectorAll(".list_row"));
  const pagination = document.getElementById("pagination");
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");

  let filteredRows = [...rows];

  function displayList(page) {
    listBody.innerHTML = "";
    let start = (page - 1) * rowsPerPage;
    let end = start + rowsPerPage;
    filteredRows.slice(start, end).forEach(row => listBody.appendChild(row));
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

document.addEventListener("DOMContentLoaded", function() {
  const writeBtn = document.getElementById("writeBtn");
  writeBtn.addEventListener("click", function(event) {
    event.preventDefault(); // 기본 링크 이동 막기
    alert("관리자만 작성할 수 있습니다.");
    window.location.href = window.location.pathname;
  });
});
