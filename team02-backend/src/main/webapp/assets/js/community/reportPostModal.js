document.addEventListener('DOMContentLoaded', () => {
  const openBtn   = document.getElementById('openReportModal');
  const modalBg   = document.getElementById('reportModal');
  const cancelBtn = document.getElementById('cancelReport');
  const form      = document.getElementById('reportForm');

  if (!openBtn || !modalBg || !form) return;
  
  if (Number(window.memberNumber) === Number(window.postAuthorNumber)) {
    openBtn?.remove();
  }
  if (!openBtn) return;
  const mapReason = (r) => {
    if (['ADV','BADWORDS','PORN','PERSONAL','ETC'].includes(r)) return r;
    const M = {
      '스팸/광고':'ADV',
      '욕설/비방':'BADWORDS',
      '음란물':'PORN',
      '개인정보 노출':'PERSONAL',
      '기타':'ETC'
    };
    return M[r] || 'ETC';
  };

  const canReport = () => {
    if (!window.memberNumber) { 
		alert('신고하려면 로그인하세요.');
		return false;
	  }
    if (window.adminNumber)   {
		alert('관리자 계정은 신고 기능을 사용할 수 없습니다.'); 
		 return false;
	  }
    if (!window.postNumber)   { 
		alert('유효하지 않은 게시글입니다.'); 
		return false; 
	  }
	if (Number(window.memberNumber) === Number(window.postAuthorNumber)) {
	    alert('본인 게시글은 신고할 수 없습니다.');
	    return false;
	  }	  
    return true;
  };

  const openModal = () => {
    if (!canReport()) return;
    modalBg.style.display = 'flex';
    modalBg.setAttribute('aria-hidden', 'false');
  };

  const closeModal = () => {
    modalBg.style.display = 'none';
    modalBg.setAttribute('aria-hidden', 'true');
    form.reset();
  };

  /*모달 열기*/
  openBtn.addEventListener('click', openModal);

  /* 모달 닫기*/
  cancelBtn?.addEventListener('click', closeModal);
  modalBg.addEventListener('click', (e) => { if (e.target === modalBg) closeModal(); });

  /*신고 제출*/
  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    if (!canReport()) return;

    const raw = form.reason.value;
    const reason = mapReason(raw);

    try {
      const res = await fetch(`${window.ctx}/postReport/reportPostOk.pr`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          'Accept': 'application/json'
        },
        body: new URLSearchParams({
          postNumber: String(window.postNumber),
          reason
        })
      });

      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      const data = await res.json();

      if (data.ok) {
        alert('신고가 접수되었습니다.');
        closeModal();
      } else if (data.reason === 'LOGIN_REQUIRED') {
        alert('로그인이 필요합니다.');
        location.href = `${window.ctx}/login/login.lo`;
      } else if (data.reason === 'ALREADY_REPORTED') {
        alert('이미 신고한 게시글입니다.');
        closeModal();
      } else {
        alert('신고 처리 중 오류가 발생했습니다.');
      }
    } catch (err) {
      console.error(err);
      alert('네트워크 오류로 신고에 실패했습니다.');
    }
  });
});