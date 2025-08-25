document.addEventListener("DOMContentLoaded", () => {
  /** 찜 버튼 토글 **/
  const heartBtn = document.getElementById("heartBtn");
  if (heartBtn) {
    let isLiked = false;

    const heartMessage = document.createElement("div");
    heartMessage.className = "heart-message";
    document.body.appendChild(heartMessage);

    function showHeartMessage(message) {
      heartMessage.textContent = message;
      heartMessage.classList.add("show");
      setTimeout(() => heartMessage.classList.remove("show"), 1500);
    }

    heartBtn.addEventListener("click", () => {
      isLiked = !isLiked;
      heartBtn.src = isLiked 
        ? `${contextPath}/assets/img/heart_active.png` 
        : `${contextPath}/assets/img/heart_inactive.png`;
      showHeartMessage(isLiked ? "찜 완료" : "찜 해제");
    });
  }

  /** 수량 조절 **/
  document.querySelectorAll(".buy_food_menu_list").forEach(menu => {
    const minus = menu.querySelector(".minus");
    const plus = menu.querySelector(".plus");
    const countEl = menu.querySelector(".count");
    const inputQuantity = menu.querySelector(".cartQuantity");
    let count = 1;

    minus.addEventListener("click", e => {
      e.preventDefault();
      if (count > 1) {
        count--;
        countEl.textContent = count;
        if (inputQuantity) inputQuantity.value = count;
      }
    });

    plus.addEventListener("click", e => {
      e.preventDefault();
      count++;
      countEl.textContent = count;
      if (inputQuantity) inputQuantity.value = count;
    });
  });

  /** 사고보상정책 토글 **/
  document.querySelectorAll(".buy_policy_toggle_header").forEach(header => {
    header.addEventListener("click", () => {
      const content = header.nextElementSibling;
      if (!content) return;
      const isVisible = window.getComputedStyle(content).display === "block";
      content.style.display = isVisible ? "none" : "block";
    });
  });
});
