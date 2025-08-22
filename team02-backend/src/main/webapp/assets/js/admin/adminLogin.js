// /assets/js/admin/adminLogin.js
document.addEventListener('DOMContentLoaded', () => {
  const form  = document.getElementById('admin_login_form');
  if (!form) return;

  const idEl  = document.getElementById('adminId');
  const pwEl  = document.getElementById('adminPw');
  const errEl = document.getElementById('admin_loginfail'); // 없을 수도 있음

  form.addEventListener('submit', (e) => {
    const id = idEl?.value.trim() || '';
    const pw = pwEl?.value.trim() || '';

    // 빈 값만 막기
    if (!id || !pw) {
      e.preventDefault();
      if (errEl) {
        errEl.textContent = '아이디와 비밀번호를 입력하세요.';
        errEl.style.display = 'block';
      } else {
        alert('아이디와 비밀번호를 입력하세요.');
      }
      (id ? pwEl : idEl)?.focus();
      return;
    }

    // 값 있으면 서버로 그대로 제출 (절대 e.preventDefault() 하지 마)
    if (errEl) errEl.style.display = 'none';
  });
});
