document.addEventListener("DOMContentLoaded", () => {
  // 검색 input 요소 가져오기
  const searchInput = document.getElementById("buy_search");
  // 아이템들이 담겨있는 컨테이너 (가게 카드 목록)
  const itemsContainer = document.querySelector(".buy_area");
  // 페이지네이션 버튼을 표시할 영역
  const paginationContainer = document.getElementById("pagination");

  // 가게 카드 전체를 배열로 가져오기
  const allItems = Array.from(itemsContainer.querySelectorAll(".buy_food_article"));
  // 한 페이지에 보여줄 아이템 개수 설정
  const rowsPerPage = 8;
  // 검색 결과로 필터링된 아이템 배열, 초기값은 전체 아이템
  let filteredItems = [...allItems];
  // 현재 보고 있는 페이지 번호
  let currentPage = 1;

  function filterItems(keyword) {
    const lowerKeyword = keyword.toLowerCase();

    // 먼저 결과가 있는지 확인
    const tempFiltered = allItems.filter(item => {
      const storeName = item.querySelector(".buy_store_name").textContent.toLowerCase();
      const menuName = item.querySelector(".buy_menu_name").textContent.toLowerCase();
      return storeName.includes(lowerKeyword) || menuName.includes(lowerKeyword);
    });

    // 결과가 없으면 alert만 띄우고 기존 화면 유지
    if (tempFiltered.length === 0) {
      alert("검색 결과가 없습니다.");
      return; // 함수 종료 → 아래 코드 실행 안 함
    }

    // 결과가 있을 경우만 화면 갱신
    filteredItems = tempFiltered;
    currentPage = 1;
    displayPage(currentPage);
    renderPagination();
  }

  /**
   * 특정 페이지 번호에 해당하는 가게 카드만 화면에 표시하는 함수
   * @param {number} page - 표시할 페이지 번호
   */
  function displayPage(page) {
    // 기존에 표시된 아이템 모두 삭제
    itemsContainer.innerHTML = "";

    // 페이지 번호에 따른 아이템 배열의 시작과 끝 인덱스 계산
    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;

    // 해당 범위에 속하는 아이템들만 추출
    const pageItems = filteredItems.slice(start, end);

    // 추출된 아이템들을 화면에 추가
    pageItems.forEach(item => itemsContainer.appendChild(item));
  }

  /**
   * 페이지네이션 버튼들을 동적으로 생성하고 렌더링하는 함수
   */
  function renderPagination() {
    // 이전 페이지네이션 버튼 초기화
    paginationContainer.innerHTML = "";

    // 전체 페이지 수 계산 (총 아이템 수 / 페이지당 아이템 수)
    const totalPages = Math.ceil(filteredItems.length / rowsPerPage);

    // ul 요소 생성하여 버튼들을 묶음
    const ul = document.createElement("ul");

    // 스타일 적용 (flex로 가운데 정렬, 간격 조정)
    ul.style.display = "flex";
    ul.style.justifyContent = "center";
    ul.style.gap = "6px";
    ul.style.listStyle = "none";
    ul.style.padding = "0";

    // 이전 페이지 버튼 생성
    const prevLi = document.createElement("li");
    prevLi.classList.add("buy_pagenation_box");
    prevLi.innerHTML = '<a href="#">&lt;</a>';

    // 첫 페이지면 비활성화(커서변경 및 불투명도 조절)
    prevLi.style.cursor = currentPage === 1 ? "default" : "pointer";
    prevLi.style.opacity = currentPage === 1 ? "0.5" : "1";

    // 클릭 시 이전 페이지로 이동
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

    // 1부터 totalPages까지 페이지 번호 버튼 생성
    for (let i = 1; i <= totalPages; i++) {
      const li = document.createElement("li");
      li.classList.add("buy_pagenation_box");

      // 현재 페이지면 active 클래스 추가 (스타일용)
      if (i === currentPage) li.classList.add("active");

      li.innerHTML = `<a href="#">${i}</a>`;

      // 각 버튼 클릭 시 해당 페이지로 이동
      li.addEventListener("click", e => {
        e.preventDefault();
        if (currentPage === i) return; // 현재 페이지면 무시
        currentPage = i;
        displayPage(currentPage);
        renderPagination();
        scrollToTop();
      });
      ul.appendChild(li);
    }

    // 다음 페이지 버튼 생성
    const nextLi = document.createElement("li");
    nextLi.classList.add("buy_pagenation_box");
    nextLi.innerHTML = '<a href="#">&gt;</a>';

    // 마지막 페이지면 비활성화
    nextLi.style.cursor = currentPage === totalPages ? "default" : "pointer";
    nextLi.style.opacity = currentPage === totalPages ? "0.5" : "1";

    // 클릭 시 다음 페이지로 이동
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

    // 페이지네이션 컨테이너에 완성된 ul 추가
    paginationContainer.appendChild(ul);
  }

  // Enter 키를 눌렀을 때만 검색 실행
  searchInput.addEventListener("keydown", e => {
    if (e.key === "Enter") {
      e.preventDefault(); // form 제출 방지
      const keyword = e.target.value.trim();
      filterItems(keyword); // 키워드 상관없이 필터링 실행
    }
  });

  displayPage(currentPage);
  renderPagination();

  // 사고보상정책 토글 기능 구현
  const headers = document.querySelectorAll(".buy_policy_toggle_header");

  // 각 토글 헤더에 클릭 이벤트 등록
  headers.forEach(header => {
    header.addEventListener("click", () => {
      const content = header.nextElementSibling; // 헤더 다음 요소(내용)
      // 현재 보임 상태 토글
      if (window.getComputedStyle(content).display === 'block') {
        content.style.display = 'none';
      } else {
        content.style.display = 'block';
      }
    });
  });
});