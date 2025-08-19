window.addEventListener('DOMContentLoaded', () => {
  // HTML 내 상대경로 img src를 절대경로(/...)로 변경하는 함수
  function fixRelativePaths(htmlString) {
    const tempDiv = document.createElement('div');
    tempDiv.innerHTML = htmlString;

    const imgs = tempDiv.querySelectorAll('img');
    imgs.forEach(img => {
      const src = img.getAttribute('src');
      if (src && src.startsWith('./')) {
        img.src = '/' + src.substring(2);
      }
    });

    return tempDiv.innerHTML;
  }

  // 헤더 불러오기 및 경로 보정 후 삽입
  fetch('./../../header_login.html')
    .then(response => response.text())
    .then(data => {
      const fixedData = fixRelativePaths(data);
      document.getElementById('header').innerHTML = fixedData;
    });

  // 푸터 불러오기 및 경로 보정 후 삽입
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      const fixedData = fixRelativePaths(data);
      document.getElementById('footer').innerHTML = fixedData;
    });
});