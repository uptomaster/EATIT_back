const updateBtn = document.getElementById('admin_updatebtn');
const deletebtn = document.getElementById('admin_deletebtn');
const logoutbtn = document.getElementById('admin_logoutbtn');
updateBtn.addEventListener('click', () => {
  window.location.href = './../../app/admin/bannerEdit.html';
});

deletebtn.addEventListener('click', () =>{
  if(!confirm('정말 삭제하시겠습니까?'))
    return;
    location.replace('./../../app/admin/bannerList.html');
})

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});