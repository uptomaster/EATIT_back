document.addEventListener("DOMContentLoaded", () => {
  const selectAllCheckbox = document.querySelector(".shopping_select_all input[type='checkbox']");
  const priceDisplay = document.querySelector(".shopping_payment_summary .shopping_price_row span:last-child");
  const deleteBtn = document.querySelector(".shopping_select_delete");

  document.querySelectorAll(".shopping_cart_item").forEach(item => {
    const minusBtn = item.querySelector(".shopping_item_cnt a:first-of-type");
    const plusBtn = item.querySelector(".shopping_item_cnt a:last-of-type");
    const countSpan = item.querySelector(".shopping_item_cnt span");

    // 서버에 수량 업데이트 요청하는 함수
    function updateQuantityOnServer(itemId, newQty) {
      function updateQuantityOnServer(itemId, newQty) {
        fetch("/cartList/updateQuantityOk.cl", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: `itemNumber=${itemId}&quantity=${newQty}`
        })
          .then(res => res.json())
          .then(data => console.log("서버 응답:", data))
          .catch(err => console.error("업데이트 실패", err));
      }

      .then(res => res.text())
        .then(msg => console.log("서버 업데이트:", msg))
        .catch(err => console.error("업데이트 실패", err));
    }

    minusBtn.addEventListener("click", e => {
      e.preventDefault();
      let count = parseInt(countSpan.textContent);
      if (count > 1) {
        countSpan.textContent = --count;
        updateTotalPrice();
        const itemId = item.dataset.itemId; // data-item-id 속성 필요
        updateQuantityOnServer(itemId, count);
      }
    });

    plusBtn.addEventListener("click", e => {
      e.preventDefault();
      let count = parseInt(countSpan.textContent);
      countSpan.textContent = ++count;
      updateTotalPrice();
      const itemId = item.dataset.itemId; // data-item-id 속성 필요
      updateQuantityOnServer(itemId, count);
    });
  });

  // 전체선택 체크박스
  selectAllCheckbox.addEventListener("change", () => {
    const checked = selectAllCheckbox.checked;
    document.querySelectorAll(".shopping_cart_item input[type='checkbox']")
      .forEach(chk => chk.checked = checked);
    updateTotalPrice();
  });

  // 개별 체크박스
  document.querySelectorAll(".shopping_cart_item input[type='checkbox']").forEach(chk => {
    chk.addEventListener("change", () => {
      const all = document.querySelectorAll(".shopping_cart_item input[type='checkbox']");
      const checked = [...all].every(c => c.checked);
      selectAllCheckbox.checked = checked;
      updateTotalPrice();
    });
  });

  // 선택삭제
  deleteBtn.addEventListener("click", () => {
    document.querySelectorAll(".shopping_cart_item input[type='checkbox']").forEach(chk => {
      if (chk.checked) {
        const item = chk.closest(".shopping_cart_item");
        if (item) item.remove();
      }
    });
    updateTotalPrice();
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

  updateTotalPrice();
});
