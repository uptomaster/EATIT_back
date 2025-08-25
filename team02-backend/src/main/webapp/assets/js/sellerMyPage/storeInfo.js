
let edit_store_info_btn = document.getElementById('edit_store_info_btn');
edit_store_info_btn.addEventListener('click', () => {
  //가게 상세 정보 수정 -> 개인정보 수정창으로
  location.replace('./../sellerMyPage/editSellerInfo.html');
});

let originBtn = document.getElementById('edit_store_origin_btn');
originBtn.addEventListener('click', () =>{
  location.replace('./../sellerMyPage/originList.html');
});  
