document.addEventListener('DOMContentLoaded', () => {
  const form  = document.querySelector('.findPw_edit_input_container');
  if (!form) return;

  const oldEl  = document.getElementById('findPw_edit_oldPw');      // 임시 비밀번호
  const newEl  = document.getElementById('findPw_edit_newPw');      // 새 비밀번호
  const chkEl  = document.getElementById('findPw_edit_newPw_chk');  // 새 비밀번호 확인

  const warnOld = document.getElementById('findPw_edit_warning_message_old');
  const warnNew = document.getElementById('findPw_edit_warning_message_new');
  const warnChk = document.getElementById('findPw_edit_warning_message_chk');

  // 회원가입과 동일: 영문 + 숫자 + 특수문자, 8~16자
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-\[\]{};':"\\|,.<>\/?]).{8,16}$/;

  const setMsg = (el, text) => { if (el) el.textContent = text; };
  const clearMsgs = () => { setMsg(warnOld,''); setMsg(warnNew,''); setMsg(warnChk,''); };

  const validate = () => {
    clearMsgs();
    const oldPw = (oldEl?.value || '').trim();
    const newPw = (newEl?.value || '').trim();
    const newPw2 = (chkEl?.value || '').trim();

    if (!oldPw) { setMsg(warnOld, '임시 비밀번호를 입력해주세요.'); oldEl?.focus(); return false; }
    if (!passwordRegex.test(oldPw)) {
      setMsg(warnOld, '임시 비밀번호 형식이 올바르지 않습니다. (영문+숫자+특수문자, 8~16자)');
      oldEl?.focus(); return false;
    }

    if (!newPw) { setMsg(warnNew, '새 비밀번호를 입력해주세요.'); newEl?.focus(); return false; }
    if (!passwordRegex.test(newPw)) {
      setMsg(warnNew, '8~16자, 영문+숫자+특수문자를 포함해야 합니다.');
      newEl?.focus(); return false;
    }
    if (newPw === oldPw) {
      setMsg(warnNew, '새 비밀번호는 임시 비밀번호와 달라야 합니다.');
      newEl?.focus(); return false;
    }

    if (!newPw2) { setMsg(warnChk, '새 비밀번호 확인을 입력해주세요.'); chkEl?.focus(); return false; }
    if (newPw !== newPw2) { setMsg(warnChk, '새 비밀번호가 일치하지 않습니다.'); chkEl?.focus(); return false; }

    return true;
  };

  form.addEventListener('submit', (e) => {
    if (!validate()) e.preventDefault();
  });
});