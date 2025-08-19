const dowritebtn = document.getElementById('admin_notice_dowrite');

dowritebtn.addEventListener('click', () =>{
  if(!confirm('이대로 작성하시겠습니까?'))
    return;
    location.replace('./../../app/admin/noticeList.html');
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});