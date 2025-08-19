const deletebtn = document.getElementById('admin_deletebtn');
const updateBtn = document.getElementById('admin_updatebtn');

deletebtn.addEventListener('click', () =>{
  if(!confirm('정말 삭제하시겠습니까?'))
    return;
    location.replace('./../../app/admin/noticeList.html');
})

updateBtn.addEventListener('click', () => {
  window.location.href = './../../app/admin/noticeEdit.html';
});

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});
