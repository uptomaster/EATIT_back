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

let edit_store_info_btn = document.getElementById('edit_store_info_btn');
edit_store_info_btn.addEventListener('click', () => {
  //가게 상세 정보 수정 -> 개인정보 수정창으로
  location.replace('./../sellerMyPage/editSellerInfo.html');
});

let originBtn = document.getElementById('edit_store_origin_btn');
originBtn.addEventListener('click', () =>{
  location.replace('./../sellerMyPage/originList.html');
});  
