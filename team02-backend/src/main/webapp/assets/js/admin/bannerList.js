// 작성하기 버튼
const writeBtn = document.getElementById('prepare_notice_btn');
if (writeBtn) {
  writeBtn.addEventListener('click', () => {
    window.location.href = ctx + "/admin/banner/write.ad";
  });
}

// 로그아웃 버튼
const logoutbtn = document.getElementById('admin_logoutbtn');
if (logoutbtn) {
  logoutbtn.addEventListener('click', () => {
    location.replace(ctx + "/admin/login.ad");
  });
}
