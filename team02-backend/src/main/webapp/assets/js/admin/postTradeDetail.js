const deletebtn = document.getElementById('admin_deletebtn');

deletebtn.addEventListener('click', () =>{
  if(!confirm('정말 삭제하시겠습니까?'))
    return;
    location.replace('./../../app/admin/postTradeList.html');
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});