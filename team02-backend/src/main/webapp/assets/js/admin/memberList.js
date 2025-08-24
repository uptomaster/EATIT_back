const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('${pageContext.request.contextPath}/admin/memberlistlist.ad');
});