document.addEventListener("DOMContentLoaded", () => {
  const listBody = document.getElementById("postListBody");
  const pagination = document.querySelector(".pagination"); 
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");
  const rowsPerPage = 10;
  let currentPage = 1;

  if (!listBody) return;

  let allRows = Array.from(listBody.querySelectorAll(".list_row"));
  let filteredRows = [...allRows];

  // --- 1. 목록 로딩 시 추천수 갱신 ---
  async function updateLikeCounts() {
    const promises = filteredRows.map(async row => {
      const postNumber = row.querySelector(".list_recommend_count")?.dataset.postNumber;
      const likeEl = row.querySelector(".list_recommend_count");
      if (!postNumber || !likeEl) return;

      try {
        const res = await fetch(`${window.ctx}/community/postlike.co?postNumber=${postNumber}`, {
          method: "GET",
          headers: { "Accept": "application/json" }
        });
        if (!res.ok) throw new Error("추천수 조회 실패");

        const data = await res.json();
        if (data?.likeCount !== undefined) {
          likeEl.textContent = data.likeCount;
        }
      } catch (err) {
        console.error(`추천수 갱신 실패: postNumber=${postNumber}`, err);
      }
    });

    await Promise.all(promises);
  }

  updateLikeCounts();

  // --- 2. 목록 클릭 이벤트 (추천/제목 이동 통합) ---
  listBody.addEventListener("click", async e => {
    // 제목 클릭 이동
    const link = e.target.closest(".col_title a");
    if (link) {
      e.preventDefault();
      window.location.href = link.getAttribute("href");
      return;
    }

    // 추천 버튼 클릭
    const btn = e.target.closest(".col_likes");
    if (!btn) return;

    const row = btn.closest(".list_row");
    const postNumber = row.querySelector(".list_recommend_count")?.dataset.postNumber;

    if (!postNumber || !window.memberNumber) {
      alert("로그인 후 이용해주세요.");
      window.location.href = `${window.ctx}/login/login.lo`;
      return;
    }

    const likeEl = row.querySelector(".list_recommend_count");
    if (!likeEl) return;

    try {
      const res = await fetch(`${window.ctx}/community/postlike.co?postNumber=${postNumber}`, {
        method: "POST",
        headers: { "Accept": "application/json" }
      });
      if (!res.ok) throw new Error("추천 처리 실패");

      const data = await res.json();
      if (!data.success) {
        alert(data.message || "추천 실패");
      } else {
        likeEl.textContent = data.likeCount;
      }
    } catch (err) {
      console.error(err);
      alert("추천 처리에 실패했습니다.");
    }
  });

  // --- 3. 목록 페이징 & 검색 처리 ---
  function displayList(page) {
    listBody.innerHTML = "";
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const rowsToDisplay = filteredRows.slice(start, end);

    if (rowsToDisplay.length === 0) {
      listBody.innerHTML = `<div class="no-results">검색결과가 없습니다.</div>`;
      pagination.innerHTML = "";
      return;
    }

    rowsToDisplay.forEach(row => listBody.appendChild(row));
    updatePagination();
  }

  function updatePagination() {
    pagination.innerHTML = "";
    const totalPages = Math.ceil(filteredRows.length / rowsPerPage);
    for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement("a");
      pageLink.href = "#";
      pageLink.textContent = i;
      pageLink.className = i === currentPage ? "active page" : "page";
      pageLink.addEventListener("click", e => {
        e.preventDefault();
        currentPage = i;
        displayList(currentPage);
        updateLikeCounts(); // 페이지 이동 시 추천수 갱신
      });
      pagination.appendChild(pageLink);
    }
  }

  function searchList() {
    const query = searchInput.value.trim().toLowerCase();
    filteredRows = allRows.filter(row => {
      const titleEl = row.querySelector(".col_title");
      if (!titleEl) return false;
      return titleEl.textContent.toLowerCase().includes(query);
    });
    currentPage = 1;
    displayList(currentPage);
    updateLikeCounts(); // 검색 후 추천수 갱신
  }

  searchBtn.addEventListener("click", e => {
    e.preventDefault();
    searchList();
  });

  searchInput.addEventListener("keypress", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      searchList();
    }
  });

  displayList(currentPage);

  // --- 4. 뒤로가기 시 강제 새로고침 ---
  window.addEventListener("pageshow", function(event) {
      if (event.persisted) {
          window.location.reload();
      }
  });
});
