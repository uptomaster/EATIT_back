document.addEventListener("DOMContentLoaded", () => {
  /** 찜 버튼 토글 **/
  const heartBtn = document.getElementById("heartBtn");
  let isLiked = false;

  // 메시지 표시용 div
  const heartMessage = document.createElement("div");
  heartMessage.className = "heart-message";
  document.body.appendChild(heartMessage);

  function showHeartMessage(message) {
    heartMessage.textContent = message;
    heartMessage.classList.add("show");
    setTimeout(() => {
      heartMessage.classList.remove("show");
    }, 1500);
  }

  if (heartBtn) {
    heartBtn.addEventListener("click", () => {
      isLiked = !isLiked;
      heartBtn.src = isLiked
        ? `${pageContext.request.contextPath}/assets/img/heart_active.png`
        : `${pageContext.request.contextPath}/assets/img/heart_inactive.png`;

      showHeartMessage(isLiked ? "찜 완료" : "찜 해제");
    });
  }

  /** 수량 조절 **/
  document.querySelectorAll(".buy_food_menu_list").forEach(menu => {
    const minus = menu.querySelector(".minus");
    const plus = menu.querySelector(".plus");
    const countEl = menu.querySelector(".count");
    const hiddenInput = menu.querySelector("input[name='quantity']"); // form에 있는 hidden input

    let count = parseInt(countEl.textContent) || 1;

    minus.addEventListener("click", e => {
      e.preventDefault();
      if (count > 1) {
        count--;
        countEl.textContent = count;
        if (hiddenInput) hiddenInput.value = count;
      }
    });

    plus.addEventListener("click", e => {
      e.preventDefault();
      count++;
      countEl.textContent = count;
      if (hiddenInput) hiddenInput.value = count;
    });
  });

  /** 장바구니 버튼 **/
  document.querySelectorAll(".buy_add_cart_btn").forEach(btn => {
    btn.addEventListener("click", e => {
      // form submit이므로 preventDefault 제거 → 정상적으로 post됨
      alert("장바구니에 담았습니다.");
    });
  });

  /** 가게정보 / 원산지 전환 **/
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

  /** 페이지네이션 **/
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
        pageLink.style.color = "#333";          // 링크 색상
        pageLink.style.textDecoration = "none"; // 밑줄 제거

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

  /** 사고보상 정책 토글 **/
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
