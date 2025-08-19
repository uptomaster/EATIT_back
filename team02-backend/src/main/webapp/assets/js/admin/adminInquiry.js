const writeBtn = document.getElementById('prepare_notice_btn');

writeBtn.addEventListener('click', () => {
  window.location.href = './../../app/admin/noticeWrite.html';
});

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});


document.querySelectorAll('[data-inquiry-id]').forEach(row => {
  const id = row.getAttribute('data-inquiry-id');
  const raw = localStorage.getItem(`inquiry:${id}:status`);
  const statusText = raw === 'answered' ? '답변완료' : '접수';
  row.querySelector('.status_cell').textContent = statusText;
});