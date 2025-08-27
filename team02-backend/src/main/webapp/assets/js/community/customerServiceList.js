document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;           
  let currentPage = 1;              

  const listBody = document.getElementById("inquiry_list_body");
  console.log(listBody);
  const rows = listBody.querySelectorAll(".list_row_flex_row");
  console.log(rows);
  const pagination = document.getElementById("pagination");
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");

 let filteredRows = [...rows];

  // ---------------- 특정 페이지 게시글 표시 ----------------
	function displayList(page) {
    listBody.innerHTML = "";
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    filteredRows.slice(start, end).forEach(row => {
      // 태그 표시
      const tagCell = row.querySelector(".col_tag");
      if (tagCell) {
        const tagText = tagCell.textContent.toLowerCase();
        let tagClass = "etc";
        if (tagText.includes("문의")) tagClass = "inquiry";
        else if (tagText.includes("신고")) tagClass = "report";
        tagCell.className = "col_tag tag " + tagClass;
      }

      // 답변 상태 표시
      const statusCell = row.querySelector(".col_status .status");
      if (statusCell) {
        const statusText = statusCell.textContent.toLowerCase();
        let statusClass = "";
        if (statusText.includes("접수")) statusClass = "received";
        else if (statusText.includes("답변완료")) statusClass = "completed";
        statusCell.className = "status " + statusClass;
      }

      listBody.appendChild(row);
    });
    updatePagination();
  }

  // ---------------- 페이지 번호 ----------------
  function updatePagination() {
    pagination.innerHTML = "";
    const totalPages = Math.max(1, Math.ceil(filteredRows.length / rowsPerPage));

    for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement("a");
      pageLink.href = "#";
      pageLink.textContent = i;
      pageLink.className = "page" + (i === currentPage ? " active" : "");
      pageLink.addEventListener("click", e => {
        e.preventDefault();
        currentPage = i;
        displayList(currentPage);
      });
      pagination.appendChild(pageLink);
    }
  }

  // ---------------- 검색 기능 ----------------
  function searchList() {
    const query = searchInput.value.trim().toLowerCase();
/*    filteredRows = rows.filter(row => row.textContent.toLowerCase().includes(query));*/
	const allRows = Array.from(rows);
	console.log(allRows);
	filteredRows = allRows.filter(row => row.textContent.toLowerCase().includes(query));
    currentPage = 1;
    displayList(currentPage);
  }

  searchBtn.addEventListener("click", searchList);
  searchInput.addEventListener("keypress", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });

  displayList(currentPage);
});
