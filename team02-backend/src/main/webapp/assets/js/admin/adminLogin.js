const login = document.getElementById('admin_login_form');
const adminid = document.getElementById('adminId');
const adminpw = document.getElementById('adminPw');
const loginfail = document.getElementById('admin_loginfail');

const db = {
  id : 'admin',
  pw : 'admin123123',
}

login.addEventListener('submit', function (e) {
  e.preventDefault();

  if(adminid.value === db.id && adminpw.value === db.pw){
    window.location.href = './../../app/admin/dashboard.html';
  }else{
    loginfail.style.display = 'block';
    adminpw.focus();
    adminpw.select();
  }
});