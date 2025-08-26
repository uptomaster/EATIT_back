document.addEventListener('DOMContentLoaded', () => {
  const form    = document.getElementById('login_form');
  const idInput = document.getElementById('login_input_id');
  const pwInput = document.getElementById('login_input_pw');
  const warn    = document.getElementById('login_warning_message');

  // 주소 파라미터 처리 (pwChanged가 있으면 그걸 우선 표시)
  const params    = new URLSearchParams(location.search);
  const pwChanged = params.get('pwChanged');
  const status    = params.get('login');

  if (pwChanged === '1') {
    alert('비밀번호가 성공적으로 변경되었습니다.');
  } else if (status) {
    switch (status) {
      case 'noid':
        alert('등록된 아이디가 없습니다.');
        break;
      case 'wrongpw':
        alert('아이디 또는 비밀번호가 일치하지 않습니다.');
        break;
      case 'admin':
        alert('관리자 계정은 이 화면에서 로그인할 수 없습니다.');
        break;
      case 'fail':
        alert('아이디 또는 비밀번호를 다시 확인해주세요.');
        break;
    }
  }

  // 기존 경고문 숨김(선택)
  if (warn) warn.style.display = 'none';

  // 기본 클라이언트 검증: 빈값 방지
  form?.addEventListener('submit', (e) => {
    const id = (idInput?.value || '').trim();
    const pw = (pwInput?.value || '').trim();

    if (!id) {
      alert('아이디를 입력해주세요.');
      idInput?.focus();
      e.preventDefault();
      return;
    }
    if (!pw) {
      alert('비밀번호를 입력해주세요.');
      pwInput?.focus();
      e.preventDefault();
    }
  });
});