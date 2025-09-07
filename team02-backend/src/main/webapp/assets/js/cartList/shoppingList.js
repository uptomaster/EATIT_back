document.addEventListener("DOMContentLoaded", () => {
  const selectAllCheckbox = document.getElementById("selectAll");
  const deleteBtn = document.getElementById("deleteSelected");
  const clearBtn = document.getElementById("clearAll");
  const selectedAmountEl = document.getElementById("selectedAmount");
  const payForm = document.getElementById("paymentForm");
  const payCartItemNumbers = document.getElementById("payCartItemNumbers");

  // 서버에 수량 업데이트
  function updateQuantityOnServer(itemId, newQty) {
    return fetch(`${contextPath}/cartList/updateQuantityOk.cl`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `itemNumber=${itemId}&quantity=${newQty}`
    })
      .then(res => res.json())
      .then(data => {
        console.log("서버 응답:", data);
        return data;
      })
      .catch(err => console.error("업데이트 실패", err));
  }

  // 합계 계산
  function recalcTotal() {
    let selected = 0;

    document.querySelectorAll(".shopping_cart_item").forEach(item => {
      const qty = parseInt(item.querySelector(".qty").textContent);
      const price = parseInt(item.querySelector(".shopping_item_price").textContent.replace(/[^0-9]/g, ''));

      const chk = item.querySelector(".shopping_item_check");
      if (chk && chk.checked) {
        selected += price * qty;
      }
    });

    if (selectedAmountEl) selectedAmountEl.textContent = selected.toLocaleString() + " 원";
  }

  // 수량 버튼
  document.querySelectorAll(".shopping_cart_item").forEach(item => {
    const minusBtn = item.querySelector(".cnt_btn.minus");
    const plusBtn = item.querySelector(".cnt_btn.plus");
    const qtyEl = item.querySelector(".qty");
    const itemId = item.dataset.itemId;

    minusBtn?.addEventListener("click", e => {
      e.preventDefault();
      let qty = parseInt(qtyEl.textContent);
      if (qty > 1) {
        qtyEl.textContent = --qty;
        updateQuantityOnServer(itemId, qty).then(() => recalcTotal());
      } else {
        if (confirm("수량이 0 이하입니다. 상품을 삭제하시겠습니까?")) {
          item.remove();
          updateQuantityOnServer(itemId, 0).then(() => recalcTotal());
        }
      }
    });

    plusBtn?.addEventListener("click", e => {
      e.preventDefault();
      let qty = parseInt(qtyEl.textContent);
      qtyEl.textContent = ++qty;
      updateQuantityOnServer(itemId, qty).then(() => recalcTotal());
    });
  });

  // 전체선택
  selectAllCheckbox?.addEventListener("change", e => {
    const checked = e.target.checked;
    document.querySelectorAll(".shopping_item_check").forEach(cb => cb.checked = checked);
    recalcTotal();
  });

  // 개별 체크박스
  document.querySelectorAll(".shopping_item_check").forEach(cb => {
    cb.addEventListener("change", () => {
      const all = document.querySelectorAll(".shopping_item_check");
      selectAllCheckbox.checked = [...all].every(c => c.checked);
      recalcTotal();
    });
  });

  // 선택삭제
  deleteBtn?.addEventListener("click", () => {
    const selected = Array.from(document.querySelectorAll('.shopping_item_check:checked'))
      .map(cb => cb.value);

    if (selected.length === 0) {
      alert("삭제할 항목을 선택하세요.");
      return;
    }

    if (!confirm("선택한 상품을 삭제하시겠습니까?")) return;

    document.getElementById('cartItemNumbers').value = selected.join(',');
    document.getElementById('deleteForm').submit();
  });

  // 전체삭제
  clearBtn?.addEventListener("click", () => {
    if (!confirm("장바구니를 모두 비우시겠습니까?")) return;
    location.href = `${contextPath}/cartList/clearOk.cl`;
  });

  // 결제하기: 선택된 상품만 전송
  payForm?.addEventListener("submit", e => {
    const selected = Array.from(document.querySelectorAll('.shopping_item_check:checked'))
      .map(cb => cb.value);

    if (selected.length === 0) {
      alert("결제할 상품을 선택하세요.");
      e.preventDefault();
      return;
    }

    payCartItemNumbers.value = selected.join(',');
  });

  // 초기 합계 계산
  recalcTotal();
});
