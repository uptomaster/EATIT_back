const dowritebtn = document.getElementById('admin_notice_dowrite');

dowritebtn.addEventListener('click', () =>{
  if(!confirm('수정하시겠습니까?'))
    return;
    location.replace('./../../app/admin/noticeDetail.html');
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});