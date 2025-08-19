const deletebtn = document.getElementById('admin_deletebtn');

deletebtn.addEventListener('click', () =>{
  if(!confirm('정말 삭제하시겠습니까?'))
    return;
    location.replace('./../../app/admin/adminInquiry.html');
})

const logoutbtn = document.getElementById('admin_logoutbtn');

logoutbtn.addEventListener('click', () => {
  location.replace('./../../app/admin/adminLogin.html');
});

document.addEventListener('DOMContentLoaded', () => {
  // 필수 요소
  const listEl   = document.getElementById('reply_list');
  const formEl   = document.getElementById('reply_form');
  const textEl   = document.getElementById('reply_text');
  const statusEl = document.getElementById('post_status_text');
  if (!listEl || !formEl || !textEl || !statusEl) return;

  // 글 ID (?id=...) 없으면 demo-1
  const params    = new URLSearchParams(location.search);
  const inquiryId = params.get('id') || 'demo-1';

  // 기존 키명과 호환 (replies / status)
  const STORAGE_KEY = `inquiry:${inquiryId}:replies`;
  const STATUS_KEY  = `inquiry:${inquiryId}:status`;

  // 저장된 댓글 불러오기
  let replies = JSON.parse(localStorage.getItem(STORAGE_KEY) || '[]');

  // -------- 렌더링 & 상태 --------
  function render() {
    listEl.innerHTML = replies.map(itemTemplate).join('');
    setStatusByCount();
  }

  function setStatusByCount() {
    const answered = replies.length > 0;
    statusEl.textContent = answered ? '답변완료' : '접수';
    localStorage.setItem(STATUS_KEY, answered ? 'answered' : 'received');
  }

  // 댓글 카드 템플릿 (관리자 아바타, 메타, 본문, 삭제)
  function itemTemplate(r) {
    const dateStr = new Date(r.createdAt).toLocaleString();
    return `
      <li class="reply_item" data-id="${r.id}">
        <div class="reply_imgicon">
          <img src="./../../assets/img/관리자.png" alt="관리자">
        </div>
        <div class="reply_content">
          <div class="reply_meta">관리자 • ${dateStr}</div>
          <p class="reply_body">${r.text}</p>
        </div>
        <button type="button" class="reply_delete">삭제</button>
      </li>
    `;
  }

  // 저장
  function save() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(replies));
  }

  // 고유 ID
  function genId() {
    return (crypto?.randomUUID?.() ?? ('r-' + Date.now().toString(36) + Math.random().toString(36).slice(2,8)));
  }

  // 초기 렌더
  render();

  // -------- 이벤트 --------
  // 등록
  formEl.addEventListener('submit', (e) => {
    e.preventDefault();
    const text = (textEl.value || '').trim();
    if (!text) { textEl.focus(); return; }

    const reply = {
      id: genId(),
      text,
      author: '관리자',
      createdAt: new Date().toISOString(),
    };

    replies.push(reply);
    save();
    render();
    textEl.value = '';
  });

  // 삭제(위임)
  listEl.addEventListener('click', (e) => {
    const delBtn = e.target.closest('.reply_delete');
    if (!delBtn) return;

    const item = delBtn.closest('li.reply_item');
    const id   = item?.dataset.id;
    if (!item || !id) return;
    const ok = window.confirm('댓글을 삭제하시겠습니까?');
    if (!ok) return; // 사용자가 취소를 누르면 아무 것도 하지 않음

    const idx = replies.findIndex(r => r.id === id);
    if (idx > -1) {
      replies.splice(idx, 1);
      save();
      render(); // 상태(접수/답변완료)도 여기서 자동 갱신됨
  }
  });
});