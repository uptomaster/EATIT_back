document.addEventListener("DOMContentLoaded", () => {
  const itemsPerPage = 8; // 한 페이지에 보여줄 항목 개수
  const buyArea = document.querySelector('.buy_area'); // 음식점 카드가 들어있는 영역
  const pagination = document.getElementById('pagination'); // 페이지네이션 컨테이너
  let articles = Array.from(document.querySelectorAll('.buy_food_article')); // 모든 음식점 카드

  renderPage(1);

  //  페이지네이션
  function createPagination(totalPages, currentPage) {
    let paginationHTML = '<ul>';
    for (let i = 1; i <= totalPages; i++) {
      paginationHTML += `
        <li class="buy_pagenation_box ${i === currentPage ? 'active' : ''}" data-page="${i}">
          <a href="#">${i}</a>
        </li>
      `;
    }
    paginationHTML += '</ul>';
    pagination.innerHTML = paginationHTML;

    // 각 페이지 버튼에 클릭 이벤트 추가
    document.querySelectorAll('.buy_pagenation_box').forEach(box => {
      box.addEventListener('click', (e) => {
        e.preventDefault();
        const page = parseInt(box.getAttribute('data-page'));
        renderPage(page);
      });
    });
  }

  // 지정한 페이지 번호에 해당하는 카드만 화면에 보이게 렌더링
  function renderPage(page) {
    // 현재 화면에 남아 있는 카드 목록 다시 수집
    articles = Array.from(document.querySelectorAll('.buy_food_article'));

    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    // 모든 카드 숨기기
    articles.forEach(article => article.style.display = 'none');

    // 현재 페이지에 해당하는 카드만 보이기
    const currentItems = articles.slice(start, end);
    currentItems.forEach(article => article.style.display = 'inline-block');

    // 페이지네이션 다시 생성
    createPagination(Math.ceil(articles.length / itemsPerPage), page);

    // 하트 클릭 이벤트 
    bindHeartEvents();

    //찜을 모두 해제해 찜한 가게가 없을경우
    if (articles.length === 0) {
      alert("찜한 가게가 없습니다.");
      history.back();
      return; // 추가 렌더링 방지
    }
  }

  // 하트 클릭 시 카드 삭제 및 하트 이미지 변경
  function bindHeartEvents() {
    document.querySelectorAll('.heart_icon').forEach(icon => {
      icon.onclick = function (e) {
        e.preventDefault();
        const article = icon.closest('.buy_food_article');

        icon.src = './../../assets/img/heart_inactive.png';

        // 카드 제거 후, 현재 페이지 다시 렌더링
        setTimeout(() => {
          article.remove();
          const currentPage = document.querySelector('.buy_pagenation_box.active')?.getAttribute('data-page') || 1;
          renderPage(parseInt(currentPage));
        }, 200);
      };
    });
  }

  // 찜한 가게가 없을 경우 알림 후 이전 페이지로 이동
  if (articles.length === 0) {
    alert("찜한 가게가 없습니다");
    history.back(); // 이전 페이지로 이동
  }
});