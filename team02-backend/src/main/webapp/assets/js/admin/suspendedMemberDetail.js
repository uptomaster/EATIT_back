const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});