document.addEventListener("DOMContentLoaded", () => {
  const selectAllCheckbox = document.querySelector(".shopping_select_all input[type='checkbox']");
  const priceDisplay = document.querySelector(".shopping_payment_summary .shopping_price_row span:last-child");
  const deleteBtn = document.querySelector(".shopping_select_delete");

  // 수량 ➖➕ 조작
  document.querySelectorAll(".shopping_cart_item").forEach(item => {
    const minusBtn = item.querySelector(".shopping_item_cnt a:first-of-type");
    const plusBtn = item.querySelector(".shopping_item_cnt a:last-of-type");
    const countSpan = item.querySelector(".shopping_item_cnt span");

    minusBtn.addEventListener("click", e => {
      e.preventDefault();
      let count = parseInt(countSpan.textContent);
      if (count > 1) {
        countSpan.textContent = --count;
        updateTotalPrice();
      }
    });

    plusBtn.addEventListener("click", e => {
      e.preventDefault();
      let count = parseInt(countSpan.textContent);
      countSpan.textContent = ++count;
      updateTotalPrice();
    });
  });

  // 전체선택 → 모두 체크/해제
  selectAllCheckbox.addEventListener("change", () => {
    const checked = selectAllCheckbox.checked;
    document.querySelectorAll(".shopping_cart_item input[type='checkbox']").forEach(chk => chk.checked = checked);
    updateTotalPrice();
  });

  // 상품 체크박스 → 전체 선택 동기화
  document.querySelectorAll(".shopping_cart_item input[type='checkbox']").forEach(chk => {
    chk.addEventListener("change", () => {
      const all = document.querySelectorAll(".shopping_cart_item input[type='checkbox']");
      const checked = [...all].every(c => c.checked);
      selectAllCheckbox.checked = checked;
      updateTotalPrice();
    });
  });

  // 선택삭제 버튼 클릭시 체크된 상품 삭제
  deleteBtn.addEventListener("click", () => {
    document.querySelectorAll(".shopping_cart_item input[type='checkbox']").forEach(chk => {
      if (chk.checked) {
        const item = chk.closest(".shopping_cart_item");
        if (item) {
          item.remove();
        }
      }
    });
    updateTotalPrice();

    // 삭제 후 전체선택 체크박스 초기화
    const remainingItems = document.querySelectorAll(".shopping_cart_item input[type='checkbox']");
    selectAllCheckbox.checked = remainingItems.length > 0 && [...remainingItems].every(c => c.checked);
  });

  function updateTotalPrice() {
    let total = 0;
    document.querySelectorAll(".shopping_cart_item").forEach(item => {
      const checkbox = item.querySelector("input[type='checkbox']");
      if (checkbox.checked) {
        const priceText = item.querySelector(".shopping_item_price").textContent;
        const count = parseInt(item.querySelector(".shopping_item_cnt span").textContent);
        const price = parseInt(priceText.replace(/[^0-9]/g, ''));
        total += price * count;
      }
    });

    priceDisplay.textContent = total.toLocaleString() + "원";
  }

  // 페이지 로드시 금액 초기화
  updateTotalPrice();
});
