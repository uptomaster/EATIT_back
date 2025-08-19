const warningbtn = document.getElementById('button_warning');

warningbtn.addEventListener('click', () =>{
  if(!confirm('경고를 주시겠습니까?'))
    return;
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});