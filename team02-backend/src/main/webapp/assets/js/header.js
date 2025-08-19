window.addEventListener('DOMContentLoaded', () => {
  // 각 파일마다 경로가 다르므로 경로 설정 후 진행하는 방식으로 작성함
  if (typeof headerPath === 'undefined') {
    console.error('headerPath 변수가 설정되지 않았습니다.');
    return;
  }
  if (typeof footerPath === 'undefined') {
    console.error('footerPath 변수가 설정되지 않았습니다.');
    return;
  }

  // 헤더 로드
  fetch(headerPath)
    .then(response => response.text())
    .then(headerHtml => {
      document.getElementById('header').innerHTML = headerHtml;
      bindHeaderDropdown();
    })
    .catch(error => console.error('헤더 로드 실패:', error));

  // 푸터 로드
  fetch(footerPath)
    .then(response => response.text())
    .then(footerHtml => {
      document.getElementById('footer').innerHTML = footerHtml;
    })
    .catch(error => console.error('푸터 로드 실패:', error));
});

//헤더 드롭다운 함수
function bindHeaderDropdown() {
  const buyItems = document.querySelectorAll('#header_nav > ul > li');
  const dropdown = document.getElementById('header_nav_display');
  if (!dropdown || buyItems.length === 0) return;

  buyItems.forEach((item, index) => {
    item.addEventListener('mouseenter', () => {
      dropdown.style.display = 'flex';
      const buyMenu = dropdown.querySelector('.header_nav_buy');
      const commuMenu = dropdown.querySelector('.header_nav_commu');

      if (index === 0) {
        if (buyMenu) { 
            buyMenu.style.display = 'flex';
        }
        if (commuMenu) { 
            commuMenu.style.display = 'none';
        }
      } else if (index === 1) {
        if (buyMenu) {
          buyMenu.style.display = 'none';
        }
        if (commuMenu) {
          commuMenu.style.display = 'flex';
        }
      } else {
        dropdown.style.display = 'none';
      }
    });

    item.addEventListener('mouseleave', () => {
      setTimeout(() => {
        if (!dropdown.matches(':hover')) dropdown.style.display = 'none';
      }, 100);
    });
  });
  dropdown.addEventListener('mouseleave', () => {
    dropdown.style.display = 'none';
  });
}
