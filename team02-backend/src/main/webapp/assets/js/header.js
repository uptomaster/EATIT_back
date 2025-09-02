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
  bindHeaderDropdown();