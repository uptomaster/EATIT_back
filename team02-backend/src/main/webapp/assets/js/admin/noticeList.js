const writeBtn = document.getElementById('prepare_notice_btn');

writeBtn.addEventListener('click', () => {
  window.location.href = './../../app/admin/noticeWrite.html';
});

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});