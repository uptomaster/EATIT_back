document.addEventListener("DOMContentLoaded", () => {
  const rowsPerPage = 2;
  let currentPage = 1;

  // 리뷰 전체 영역
  const listContainer = document.querySelector(".buy_food");
  const paginationContainer = document.getElementById("pagination");

  // 페이지네이션 대상 리뷰 리스트
  const allReviews = Array.from(listContainer.querySelectorAll(".buy_review_area"));
  const totalPages = Math.ceil(allReviews.length / rowsPerPage);

  function renderPage(page) {
    // 전체 리뷰 숨기기
    allReviews.forEach((review, index) => {
      review.style.display = (index >= (page - 1) * rowsPerPage && index < page * rowsPerPage) ? "flex" : "none";
    });

    renderPagination(page);
  }

  function renderPagination(current) {
    paginationContainer.innerHTML = "";

    for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement("a");
      pageLink.href = "#";
      pageLink.textContent = i;
      pageLink.classList.add("page");
      if (i === current) {
        pageLink.classList.add("active");
      }

      pageLink.addEventListener("click", (e) => {
        e.preventDefault();
        currentPage = i;
        renderPage(currentPage);
      });

      paginationContainer.appendChild(pageLink);
    }
  }

  // 초기 렌더링
  renderPage(currentPage);
});
