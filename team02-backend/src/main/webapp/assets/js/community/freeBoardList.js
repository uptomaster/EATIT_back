document.addEventListener("DOMContentLoaded", () => {
  const listBody = document.getElementById("postListBody");
  const pagination = document.getElementById("pagination"); 
  const searchInput = document.querySelector(".search_text");
  const searchBtn = document.querySelector(".search_btn");
  const rowsPerPage = 10;
  let currentPage = 1;

  if (!listBody) return;

  let allRows = Array.from(listBody.querySelectorAll(".list_row"));
  let filteredRows = [...allRows];
  
  //조회수 적용
  /*const postNumber = row.dataset.postNumber;
  const viewCountEl = row.querySelector(".view_count");
	  if (!postNumber || !viewCountEl) return;
	
	  try {
	      const res = await fetch(`/community/postViewCount.co?postNumber=${postNumber}`, {
	          method: "POST",
	          headers: { "Accept": "application/json" }
	      });
	      if (!res.ok) return;
	
	      const data = await res.json();
	      if (data?.viewCount !== undefined) {
	          viewCountEl.textContent = data.viewCount; // 목록이니까 단순 숫자만
	      }
	  } catch (err) {
	      console.error(`조회수 반영 실패: postNumber=${postNumber}`, err);
	  }
  });*/

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

  // 제목 클릭시 이동 처리
  listBody.addEventListener("click", e => {
    const link = e.target.closest(".col_title a");
    if (link) {
      e.preventDefault();
      window.location.href = link.getAttribute("href");
    }
  });

  displayList(currentPage);
  
});

/*조회수 증가*/
/*document.addEventListener("DOMContentLoaded", () => {
  const rows = document.querySelectorAll(".board-row.list_row");

  rows.forEach(row => {
    const postNumber = row.querySelector("a")?.href.match(/postNumber=(\d+)/)?.[1];
    const viewCountEl = row.querySelector(".col_views");

    if (!postNumber || !viewCountEl) return;

    (async () => {
      try {
        const res = await fetch(`/community/postViewCount.co?postNumber=${postNumber}`, {
          method: "POST",
          headers: { "Accept": "application/json" }
        });

        if (!res.ok) return;

        const data = await res.json();
        if (data?.viewCount !== undefined) {
          viewCountEl.textContent = data.viewCount;
        }
      } catch (err) {
        console.error("목록 조회수 반영 실패:", err);
      }
    })();
  });
});*/