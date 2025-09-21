document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;
  const listBody = document.getElementById("inquiry_list_body");
  let currentPage = 1;
  let filteredRows = [];

  // ---------------- 특정 페이지 게시글 표시 ----------------
  function displayList(rows) {
    listBody.innerHTML = "";
    rows.forEach(row => listBody.appendChild(row));
  }

  // ---------------- tree icon mouseover 초기 처리 ----------------
  const treeIcons = document.querySelectorAll(".tree_icon");
  treeIcons.forEach(icon => {
    icon.addEventListener("mouseover", () => icon.classList.add("hover"));
    icon.addEventListener("mouseout", () => icon.classList.remove("hover"));
  });

  // ---------------- 초기 페이지 표시 ----------------
  filteredRows = Array.from(listBody.querySelectorAll(".list_row_flex_row"));
  displayList(filteredRows.slice(0, rowsPerPage));

  // ---------------- 검색 기능 ----------------
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");

  function renderList(data) {
    if (!data || data.length === 0) {
      listBody.innerHTML = `<div class="no-results">검색 결과가 없습니다.</div>`;
      return;
    }

    // 검색 결과를 기존 div 구조와 일치하도록 출력
    listBody.innerHTML = data.map(item => `
      <div class="list_row_flex_row" role="row">
        <div class="col_title" role="cell">
          <a href="${window.location.origin}/community/inquiryReadOk.co?postNumber=${item.postNumber}">
            ${item.postTitle}
          </a>
        </div>
        <div class="col_author" role="cell">
          <img src="${window.location.origin}/assets/img/새싹.png" alt="관리자" class="tree_icon" />
          ${item.memberId}
        </div>
        <div class="col_date" role="cell">
          ${item.postCreatedDate}
        </div>
        <div class="col_status" role="cell">
          <div class="status received">
            ${item.inquiryStatus || 'YET'}
          </div>
        </div>
      </div>
    `).join("");
  }

  function searchList() {
    const query = searchInput.value.trim();

    fetch(`${window.location.origin}/community/inquirySearchOk.co?q=${encodeURIComponent(query)}`)
      .then(res => res.json())
      .then(renderList)
      .catch(err => console.error("검색 실패:", err));
  }

  searchBtn.addEventListener("click", searchList);
  searchInput.addEventListener("keypress", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });
});
