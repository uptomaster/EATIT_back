document.addEventListener("DOMContentLoaded", () => {
  /** =========================
   * ❤️ 찜 버튼 토글 + 서버 연동
   * ========================= */
  const heartBtn = document.getElementById("heartBtn");
  const toast = document.createElement("div");
  toast.id = "favoriteToast";
  toast.className = "favorite-toast";
  document.body.appendChild(toast);

  function showToast(message) {
    toast.textContent = message;
    toast.classList.add("show");
    setTimeout(() => {
      toast.classList.remove("show");
    }, 1200);
  }

  if (heartBtn) {
    heartBtn.addEventListener("click", e => {
      e.preventDefault();
      const icon = heartBtn.querySelector(".heart-icon");
      const isLiked = heartBtn.dataset.favorite === "true";
      const storeNumber = heartBtn.dataset.store; // storeNumber를 data-store 속성에 담아둔다

      if (isLiked) {
        // 해제
        heartBtn.dataset.favorite = "false";
        icon.classList.remove("fa-solid");
        icon.classList.add("fa-regular");
        showToast("찜 해제되었습니다.");
      } else {
        // 추가
        heartBtn.dataset.favorite = "true";
        icon.classList.remove("fa-regular");
        icon.classList.add("fa-solid");
        showToast("찜 완료되었습니다.");
      }

      // 토스트가 뜬 후 서버 요청 → 찜목록 이동
      setTimeout(() => {
        location.href =
          `${contextPath}/orders/storeFavoriteToggle.or?storeNumber=${storeNumber}`;
      }, 1300);
    });
  }

  /** =========================
   * 🛒 수량 조절 + 재고 검증
   * ========================= */
  document.querySelectorAll(".buy_food_menu_list").forEach(menu => {
    const minus = menu.querySelector(".minus");
    const plus = menu.querySelector(".plus");
    const countEl = menu.querySelector(".count");
    const hiddenInput = menu.querySelector("input[name='quantity']");
    const stockEl = menu.querySelector(".buy_food_stock");

    let count = parseInt(countEl.textContent) || 1;
    const stock = parseInt(stockEl.textContent.replace(/[^0-9]/g, ""), 10);

    // ➖ 감소
    minus.addEventListener("click", e => {
      e.preventDefault();
      if (count > 1) {
        count--;
        countEl.textContent = count;
        if (hiddenInput) hiddenInput.value = count;
      }
    });

    // ➕ 증가 (재고 초과 시 차단)
    plus.addEventListener("click", e => {
      e.preventDefault();
      if (count < stock) {
        count++;
        countEl.textContent = count;
        if (hiddenInput) hiddenInput.value = count;
      } else {
        alert("재고 수량보다 많이 담을 수 없습니다.");
      }
    });
  });

  /** =========================
   * 🛒 장바구니 버튼 클릭
   * ========================= */
  document.querySelectorAll(".buy_add_cart_btn").forEach(btn => {
    btn.addEventListener("click", () => {
      showToast("장바구니에 담았습니다.");
    });
  });

  /** =========================
   * ℹ️ 가게정보 / 원산지 전환
   * ========================= */
  const storeInfoBtn = document.getElementById("storeInfoBtn");
  const originInfoBtn = document.getElementById("originInfoBtn");
  const storeInfo = document.querySelector(".buy_origin_store_info");
  const originInfo = document.querySelector(".origin_info_inactive");

  if (storeInfoBtn && originInfoBtn) {
    storeInfoBtn.addEventListener("click", e => {
      e.preventDefault();
      storeInfo.style.display = "block";
      originInfo.style.display = "none";
      storeInfoBtn.style.color = "black";
      originInfoBtn.style.color = "#333";
    });

    originInfoBtn.addEventListener("click", e => {
      e.preventDefault();
      storeInfo.style.display = "none";
      originInfo.style.display = "block";
      originInfoBtn.style.color = "black";
      storeInfoBtn.style.color = "#333";
    });
  }

  /** =========================
   * 📄 페이지네이션
   * ========================= */
  const rowsPerPage = 2;
  let currentPage = 1;

  const listBody = document.getElementById("buy_food_section");
  const pagination = document.getElementById("pagination");

  if (listBody && pagination) {
    const allRows = Array.from(listBody.querySelectorAll(".buy_food_menu_list"));
    let filteredRows = [...allRows];

    function displayList(page) {
      listBody.innerHTML = "";
      const start = (page - 1) * rowsPerPage;
      const end = start + rowsPerPage;
      filteredRows.slice(start, end).forEach(row => listBody.appendChild(row));
      updatePagination();
    }

    function updatePagination() {
      pagination.innerHTML = "";
      const totalPages = Math.max(1, Math.ceil(filteredRows.length / rowsPerPage));

      for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement("a");
        pageLink.href = "#";
        pageLink.textContent = i;
        pageLink.className = "page" + (i === currentPage ? " active" : "");
        pageLink.style.color = "#333";
        pageLink.style.textDecoration = "none";

        pageLink.addEventListener("click", e => {
          e.preventDefault();
          if (currentPage === i) return;
          currentPage = i;
          displayList(currentPage);
        });

        pagination.appendChild(pageLink);
      }
    }

    displayList(currentPage);
  }

  /** =========================
   * 📜 사고보상 정책 토글
   * ========================= */
  const headers = document.querySelectorAll(".buy_policy_toggle_header");
  headers.forEach(header => {
    header.addEventListener("click", () => {
      const content = header.nextElementSibling;
      if (!content) return;

      const isVisible = window.getComputedStyle(content).display === "block";
      content.style.display = isVisible ? "none" : "block";
    });
  });
});
