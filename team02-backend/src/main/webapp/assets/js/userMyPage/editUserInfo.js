// /assets/js/userMyPage/editUserInfo.js
document.addEventListener('DOMContentLoaded', () => {
  // 빈 값 입력 시만 경고 지우기 같은 가벼운 UX
  const cur = document.getElementById('current_password');
  const curErr = document.getElementById('current_password_error');
  if (cur && curErr) {
    cur.addEventListener('input', () => { curErr.textContent = ''; });
  }

  // 전화번호는 숫자만 유지 (선택)
  const phone = document.getElementById('new_phone');
  if (phone) {
    phone.addEventListener('input', () => {
      phone.value = phone.value.replace(/\D/g, '');
    });
  }
});