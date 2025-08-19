const darkModeToggle = document.getElementById('darkModeToggle');

darkModeToggle.addEventListener('click', () => {
  document.body.classList.toggle('dark-mode');
  // 로컬스토리지에 상태 저장 (옵션)
  if(document.body.classList.contains('dark-mode')) {
    localStorage.setItem('darkMode', 'enabled');
  } else {
    localStorage.removeItem('darkMode');
  }
});

// 페이지 로드 시 저장된 설정 적용
if(localStorage.getItem('darkMode') === 'enabled') {
  document.body.classList.add('dark-mode');
}
