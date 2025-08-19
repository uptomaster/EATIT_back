document.addEventListener('DOMContentLoaded', () => {
  const openBtn = document.getElementById('openReportModal');
  const modalBg = document.getElementById('reportModal');
  const cancelBtn = document.getElementById('cancelReport');
  const reportForm = document.getElementById('reportForm');

  // 모달 열기
  openBtn.addEventListener('click', () => {
    modalBg.style.display = 'flex';
  });

  // 모달 닫기 - 취소 버튼 클릭
  cancelBtn.addEventListener('click', () => {
    modalBg.style.display = 'none';
    reportForm.reset();
  });

  // 모달 닫기 - 배경 클릭 시
  modalBg.addEventListener('click', (e) => {
    if (e.target === modalBg) {
      modalBg.style.display = 'none';
      reportForm.reset();
    }
  });

  // 신고 제출 처리 (임시 alert)
  reportForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const selectedReason = reportForm.reason.value;
    alert(`신고가 접수되었습니다. 사유: ${selectedReason}`);
    modalBg.style.display = 'none';
    reportForm.reset();
  });
});
