window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header_login.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('header').innerHTML = data;
    });

  // 푸터 불러오기
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    });

});

document.addEventListener("DOMContentLoaded", () => {
  const modal = document.getElementById("editModal");
  const editForm = document.getElementById("editForm");
  let currentEditItem = null;
  let isEditMode = false;

  // 수정하기 버튼 클릭 이벤트
  document.addEventListener("click", (e) => {
    if (e.target.classList.contains("edit_btn")) {
      const item = e.target.closest(".origin_list");
      const product = item.querySelector(".origin_list_item").textContent;
      const origin = item.querySelector(".origin_list_origin").textContent;
      const foodName = item.querySelector(".origin_list_menu").textContent;

      // 모달창에 기존 내용 채우기
      document.getElementById("product").value = product;
      document.getElementById("origin").value = origin;
      document.getElementById("foodName").value = foodName;

      currentEditItem = item;
      isEditMode = true;
      modal.style.display = "block";
    }
  });

  // 저장 버튼 클릭 이벤트
  editForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const foodName = document.getElementById("foodName").value;
    const origin = document.getElementById("origin").value;
    const product = document.getElementById("product").value;

    if (isEditMode && currentEditItem) {
      // 수정 모드: 기존 항목 업데이트
      currentEditItem.querySelector(".origin_list_item").textContent = product;
      currentEditItem.querySelector(".origin_list_origin").textContent = origin;
      currentEditItem.querySelector(".origin_list_menu").textContent = foodName;
    } else {
      // 추가 모드: 새로운 항목 생성
      const newList = document.createElement('div');
      newList.className = 'origin_list';
      newList.innerHTML = `
        <div class="origin_list_item">${product}</div>
        <div class="origin_list_origin">${origin}</div>
        <div class="origin_list_menu">${foodName}</div>
        <div class="origin_edit_btns">
          <button class="edit_btn">수정하기</button>
        </div>
      `;

      const originListContainer = document.querySelector('.origin_list_top').parentElement;
      originListContainer.insertBefore(newList, document.getElementById('origin_list_more'));
    }

    // editForm 의 저장버튼 클릭후 alert 닫기
    alert(isEditMode ? "수정이 완료되었습니다!" : "원산지가 추가되었습니다!");
    modal.style.display = "none";
    currentEditItem = null;
    isEditMode = false;
  });

  // 삭제 버튼 클릭 이벤트
  document.getElementById("delete_btn")?.addEventListener("click", () => {
    if (currentEditItem) {
      currentEditItem.remove();
      modal.style.display = "none";
      currentEditItem = null;
      alert("항목이 삭제되었습니다.");
    }
  });

  // 원산지 추가 버튼 클릭 이벤트
  document.getElementById("add_origin_btn")?.addEventListener("click", () => {
    const modal = document.getElementById("editModal");
    const editForm = document.getElementById("editForm");

    // 폼 초기화
    editForm.reset();
    isEditMode = false;
    modal.style.display = "block";
  });

  // 모달창 외부 클릭 시 닫기
  document.getElementById("closeModal")?.addEventListener("click", () => {
    // 폼 초기화
    editForm.reset();
    modal.style.display = "none";

    // 수정 모드 초기화
    isEditMode = false;
    currentEditItem = null;
  });

});
let gobackbtn = document.getElementById('back_to_storeInfo');
gobackbtn.addEventListener('click', () => {
  location.replace('./../sellerMyPage/storeInfo.html');
});

