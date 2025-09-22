document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 10;
  const listBody = document.getElementById("faq_list_body");
  const searchInput = document.getElementById("searchInput");
  const searchBtn = document.getElementById("searchBtn");

  let currentPage = 1;
  let filteredRows = [];

  // 특정 페이지 게시글 표시
  function displayList(rows) {
    listBody.innerHTML = "";
    rows.forEach(row => listBody.appendChild(row));
  }

  // 페이지별 게시글 보여주기
  function renderPage(page) {
    currentPage = page;
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageRows = filteredRows.slice(start, end);
    displayList(pageRows);
  }

  // 초기 페이지 표시를 위한 초기 데이터 로딩
  function loadInitialRows() {
    filteredRows = Array.from(listBody.querySelectorAll(".list_row_flex_row"));
  }

  // JSON 데이터 받아서 리스트 렌더링
  function renderList(data) {
    if (!data || data.length === 0) {
      listBody.innerHTML = `<div class="no-results">검색 결과가 없습니다.</div>`;
      filteredRows = [];
      return;
    }

    listBody.innerHTML = data.map(item => `
      <div class="list_row_flex_row" role="row">
        <div class="col_title" role="cell">
          <a href="${window.location.origin}/community/faqReadOk.co?postNumber=${item.postNumber}">
            ${item.postTitle}
          </a>
        </div>
        <div class="col_author" role="cell">
          <img src="${window.location.origin}/assets/img/관리자.png" alt="관리자" class="tree_icon" />관리자
        </div>
        <div class="col_date" role="cell">${item.postCreatedDate}</div>
        <div class="col_views" role="cell">${item.postViewCount}</div>
        <div class="col_likes" role="cell">${item.postLikeCount}</div>
      </div>
    `).join("");

    filteredRows = Array.from(listBody.querySelectorAll(".list_row_flex_row"));
    renderPage(1);
  }

  // 검색 기능
  // 검색 함수
  function searchList() {
    const query = searchInput.value.trim();

    if (!query) {
      alert("검색어를 입력해주세요.");
      return;
    }

    fetch(`${window.location.origin}/community/faqSearchOk.co?q=${encodeURIComponent(query)}`)
      .then(res => {
        if (!res.ok) throw new Error("서버 응답 오류");
        return res.json();
      })
      .then(data => {
        filteredData = data;  // 데이터 저장
        renderPage(1);
      })
      .catch(err => {
        console.error("검색 실패:", err);
        alert("검색 중 오류가 발생했습니다.");
      });
  }

  searchBtn.addEventListener("click", searchList);
  searchInput.addEventListener("keypress", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });

  // 초기 로드 시
  loadInitialRows();
  renderPage(1);
  addTreeIconHover();

  searchBtn.addEventListener("click", searchList);
  searchInput.addEventListener("keypress", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });
});
