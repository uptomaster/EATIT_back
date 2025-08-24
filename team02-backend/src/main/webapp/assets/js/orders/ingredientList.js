document.addEventListener("DOMContentLoaded", () => {
  const searchInput = document.getElementById("buy_search");
  const itemsContainer = document.querySelector(".buy_area");
  const paginationContainer = document.getElementById("pagination");

  const allItems = Array.from(itemsContainer.querySelectorAll(".buy_food_article"));
  const rowsPerPage = 8;
  let filteredItems = [...allItems];
  let currentPage = 1;

  function filterItems(keyword) {
    const lowerKeyword = keyword.toLowerCase();
    const tempFiltered = allItems.filter(item => {
      const storeName = item.querySelector(".buy_store_name").textContent.toLowerCase();
      const menuName = item.querySelector(".buy_menu_name").textContent.toLowerCase();
      return storeName.includes(lowerKeyword) || menuName.includes(lowerKeyword);
    });

    if (tempFiltered.length === 0) {
      alert("재료 검색 결과가 없습니다.");
      return;
    }

    filteredItems = tempFiltered;
    currentPage = 1;
    displayPage(currentPage);
    renderPagination();
  }

  function displayPage(page) {
    itemsContainer.innerHTML = "";
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const pageItems = filteredItems.slice(start, end);
    pageItems.forEach(item => itemsContainer.appendChild(item));
  }

  function renderPagination() {
    paginationContainer.innerHTML = "";
    const totalPages = Math.ceil(filteredItems.length / rowsPerPage);

    const ul = document.createElement("ul");
    ul.style.display = "flex";
    ul.style.justifyContent = "center";
    ul.style.gap = "6px";
    ul.style.listStyle = "none";
    ul.style.padding = "0";

    const prevLi = document.createElement("li");
    prevLi.classList.add("buy_pagenation_box");
    prevLi.innerHTML = '<a href="#">&lt;</a>';
    prevLi.style.cursor = currentPage === 1 ? "default" : "pointer";
    prevLi.style.opacity = currentPage === 1 ? "0.5" : "1";
    prevLi.addEventListener("click", e => {
      e.preventDefault();
      if (currentPage > 1) {
        currentPage--;
        displayPage(currentPage);
        renderPagination();
        scrollToTop();
      }
    });
    ul.appendChild(prevLi);

    for (let i = 1; i <= totalPages; i++) {
      const li = document.createElement("li");
      li.classList.add("buy_pagenation_box");
      if (i === currentPage) li.classList.add("active");
      li.innerHTML = `<a href="#">${i}</a>`;
      li.addEventListener("click", e => {
        e.preventDefault();
        if (currentPage === i) return;
        currentPage = i;
        displayPage(currentPage);
        renderPagination();
        scrollToTop();
      });
      ul.appendChild(li);
    }

    const nextLi = document.createElement("li");
    nextLi.classList.add("buy_pagenation_box");
    nextLi.innerHTML = '<a href="#">&gt;</a>';
    nextLi.style.cursor = currentPage === totalPages ? "default" : "pointer";
    nextLi.style.opacity = currentPage === totalPages ? "0.5" : "1";
    nextLi.addEventListener("click", e => {
      e.preventDefault();
      if (currentPage < totalPages) {
        currentPage++;
        displayPage(currentPage);
        renderPagination();
        scrollToTop();
      }
    });
    ul.appendChild(nextLi);

    paginationContainer.appendChild(ul);
  }

  searchInput.addEventListener("keydown", e => {
    if (e.key === "Enter") {
      e.preventDefault();
      const keyword = e.target.value.trim();
      filterItems(keyword);
    }
  });

  displayPage(currentPage);
  renderPagination();
});
