document.addEventListener("DOMContentLoaded", () => {
	const rowsPerPage = 10;
	const listBody = document.getElementById("faq_list_body");
	let currentPage = 1;
	let filteredRows = [];

	// ---------------- 특정 페이지 게시글 표시 ----------------
	function displayList(rows) {
		listBody.innerHTML = "";
		rows.forEach(row => listBody.appendChild(row));
	}

	// ---------------- tree icon mouseover 초기 처리 ----------------

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
	}

	function searchList() {
	  const query = searchInput.value.trim();

	  fetch(`${window.location.origin}/community/faqSearch.co?q=${encodeURIComponent(query)}`)
	    .then(res => {
	      if (!res.ok) throw new Error("서버 응답 오류");
	      return res.json();
	    })
	    .then(data => {
	      renderList(data);
	    })
	    .catch(err => {
	      console.error("검색 실패:", err);
	      listBody.innerHTML = `<div class="no-results">검색 결과가 없습니다.</div>`;
	    });
	}

	searchBtn.addEventListener("click", searchList);
	searchInput.addEventListener("keypress", e => {
		if (e.key === "Enter") {
			e.preventDefault();
			searchList();
		}
	});
});
