// /assets/js/findPw/editPw.js
document.addEventListener('DOMContentLoaded', () => {
  const form   = document.querySelector('.findPw_edit_input_container');
  if (!form) return;

  const newEl  = document.getElementById('findPw_edit_newPw');
  const chkEl  = document.getElementById('findPw_edit_newPw_chk');
  const warnNew = document.getElementById('findPw_edit_warning_message_new');
  const warnChk = document.getElementById('findPw_edit_warning_message_chk');

  const setMsg = (el, t) => { if (el) el.textContent = t || ''; };

  // 회원가입과 동일: 영문 + 숫자 + 특수문자, 8~16자
  const passwordRegex =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-\[\]{};':"\\|,.<>\/?]).{8,16}$/;

  // 1) 서버 리다이렉트 파라미터로 실패 표시 (pwChanged=0)
  (function showFailByQuery() {
    const usp = new URLSearchParams(location.search);
    if (usp.get('pwChanged') === '0') {
      // 서버가 사유를 내려주지 않으므로 '일반 실패' 메시지로 안내
      // (기존 비밀번호와 동일, 정책 미충족, 세션 만료 등일 수 있음)
      setMsg(warnNew, '비밀번호 변경에 실패했습니다.');
      newEl?.focus();
      // 주소창을 깔끔하게: 파라미터 제거(새로고침 시 계속 뜨는 것 방지)
      history.replaceState(null, '', location.pathname);
    }
  })();

  // 2) 입력 중 안내 초기화
  [newEl, chkEl].forEach(el =>
    el?.addEventListener('input', () => { setMsg(warnNew, ''); setMsg(warnChk, ''); })
  );

  // 3) 제출 전 클라이언트 검증
  form.addEventListener('submit', (e) => {
    setMsg(warnNew, ''); setMsg(warnChk, '');

    const npw = (newEl?.value || '').trim();
    const cpw = (chkEl?.value || '').trim();

    if (!passwordRegex.test(npw)) {
      e.preventDefault();
      setMsg(warnNew, '8~16자, 영문+숫자+특수문자를 포함해야 합니다.');
      newEl?.focus();
      return;
    }
    if (npw !== cpw) {
      e.preventDefault();
      setMsg(warnChk, '새 비밀번호가 일치하지 않습니다.');
      chkEl?.focus();
      return;
    }
    // 그대로 서버로 제출 → 서버 결과가 실패면 editPw.jsp?pwChanged=0 으로 다시 오고,
    // 위 showFailByQuery()가 사용자에게 메시지를 보여줌.
  });
});
