document.addEventListener("DOMContentLoaded", () => {
  const recommendBtn = document.getElementById('recommendBtn');
  const likesSpan = document.querySelector('.likes');
  const counterRecommend = document.querySelector('#recommendCount');
  const modifyBtn = document.querySelector(".modify-btn");
  const deleteBtn = document.querySelector(".delete-btn");
  const submitBtn = document.querySelector(".submit-btn");
	  
  recommendBtn.addEventListener('click', () => {
    // 현재 '추천 0'에서 숫자만 추출
    let currentLikes = parseInt(likesSpan.textContent.replace('추천 ', ''), 10);
    currentLikes++;

    // 변경된 숫자 넣기
    likesSpan.textContent = `추천 ${currentLikes}`;
    counterRecommend.textContent = `추천 ${currentLikes}`;

    // 숫자 애니메이션 효과
    counterRecommend.classList.add('bump');
    setTimeout(() => {
      counterRecommend.classList.remove('bump');
    }, 300);
  });
  
   //게시글 수정 버튼 클릭시
   document.body.addEventListener("click", (e) => {
       const modifyBtn = e.target.closest(".modify-btn");
       if (modifyBtn) {
         const postNumber = window.postNumber;
         if (!postNumber) return alert("postNumber가 없습니다");
         window.location.href = `/community/postUpdate.co?postNumber=${encodeURIComponent(postNumber)}`;
       }
     });
   

   //  게시글 삭제 버튼 클릭시
	document.body.addEventListener("click", async (e) => {
	   //console.log("=====클릭=====", e.target);
       const deleteBtn = e.target.closest(".delete-btn");
       if (deleteBtn) {
         const postNumber = window.postNumber;
		 console.log("DEBUG deleteBtn =", deleteBtn);
		 console.log("DEBUG postNumber =", postNumber);
         if (!postNumber) return alert("postNumber가 없습니다");
         if (!confirm("정말 삭제하시겠습니까?")) return;

         try {
           const res = await fetch(`/community/postDeleteOk.co?postNumber=${encodeURIComponent(postNumber)}`, {
             method: "GET",
             //headers: { "X-Requested-With": "XMLHttpRequest" },
           });
           if (!res.ok) throw new Error("삭제 요청 실패");

           alert("게시글이 삭제되었습니다.");
           window.location.href = "/community/freeBoardListOk.co";
         } catch (err) {
           console.error("게시글 삭제 실패 :", err);
           alert("게시글 삭제에 실패했습니다.");
         }
       }
     });
   
   
  
});




// ===== 댓글  =====
document.addEventListener('DOMContentLoaded', () => {
  const postNumber   = window.postNumber ? parseInt(window.postNumber, 10) : null;
  const memberNumber = window.memberNumber ? parseInt(window.memberNumber, 10) : null;

  const listEl   = document.getElementById('commentList');
  const inputEl  = document.getElementById('commentInput');
  const submitEl = document.getElementById('commentSubmit');

  function escHtml(s) {
    const d = document.createElement('div');
    d.textContent = String(s ?? '');
    return d.innerHTML;
  }
  async function safeJson(res) {
    const t = await res.text();
    try { return t ? JSON.parse(t) : null; } catch { return null; }
  }

  // 목록 로드
  async function loadComments() {
    if (!postNumber || !listEl) return;
    try {
      const res = await fetch(`/comment/listOk.cm?postNumber=${encodeURIComponent(postNumber)}`, {
        headers: { 'Accept':'application/json', 'X-Requested-With':'XMLHttpRequest' }
      });
      if (!res.ok) throw new Error('댓글 목록 요청 실패');
      const items = await safeJson(res);
      renderComments(Array.isArray(items) ? items : []);
    } catch (e) {
      console.error('댓글 목록 불러오기 실패:', e);
      listEl.innerHTML = `<li class="comment_item">댓글을 불러오지 못했습니다.</li>`;
    }
  }

  // 렌더링
  function renderComments(items) {
    listEl.innerHTML = '';
    if (!items.length) {
      listEl.innerHTML = `<li class="comment_item">첫 댓글의 주인공이 되어주세요!</li>`;
      return;
    }
    const frag = document.createDocumentFragment();
    items.forEach(c => {
      const isMine = memberNumber && String(memberNumber) === String(c.memberNumber);
      const li = document.createElement('li');
      li.className = 'comment_item';
      li.dataset.number = c.commentNumber;

      li.innerHTML = `
        <div class="comment_profile_container">
          <img class="comment_profile" src="/assets/img/잎새.png" alt="프로필" />
          <div class="comment_info">
            <span class="comment_author">${escHtml(c.memberId)}</span>
            <time class="comment_timeline">${escHtml(c.commentedDate || '')}</time>
            ${isMine ? `<button class="comment_delete" data-number="${c.commentNumber}" title="댓글 삭제">[댓글 삭제]</button>` : ``}
            <p class="comment_text">${escHtml(c.commentContent)}</p>
          </div>
        </div>
      `;
      frag.appendChild(li);
    });
    listEl.appendChild(frag);
  }

  // 작성
  submitEl?.addEventListener('click', async () => {
    const content = (inputEl?.value || '').trim();
    if (!content) return alert('댓글을 입력해주세요.');
    if (!postNumber) return alert('postNumber가 없습니다.');
    if (!memberNumber) return alert('로그인 후 이용해주세요.');

    try {
      const res = await fetch(`/comment/writeOk.cm`, {
        method: 'POST',
        headers: {
          'Content-Type':'application/json; charset=utf-8',
          'Accept':'application/json',
          'X-Requested-With':'XMLHttpRequest'
        },
        body: JSON.stringify({ postNumber, memberNumber, commentContent: content })
      });
      const result = await safeJson(res);
      if (result?.status === 'success') {
        if (inputEl) inputEl.value = '';
        await loadComments();
      } else {
        alert(result?.message || '댓글 작성에 실패했습니다.');
      }
    } catch (e) {
      console.error('댓글 작성 실패:', e);
      alert('댓글 작성 중 오류가 발생했습니다.');
    }
  });

  // 삭제 (위임)
  listEl?.addEventListener('click', async (e) => {
    const btn = e.target.closest('.comment_delete');
    if (!btn) return;

    const commentNumber = btn.dataset.number || btn.closest('li')?.dataset.number;
    if (!commentNumber) return;
    if (!confirm('댓글을 삭제하시겠습니까?')) return;

    try {
      const res = await fetch(`/comment/deleteOk.cm?commentNumber=${encodeURIComponent(commentNumber)}`, {
        method: 'GET',
        headers: { 'Accept':'application/json', 'X-Requested-With':'XMLHttpRequest' }
      });
      const result = await safeJson(res);
      if (result?.status === 'success') {
        btn.closest('li')?.remove();
        if (!listEl.querySelector('li')) {
          listEl.innerHTML = `<li class="comment_item">댓글이 없습니다.</li>`;
        }
      } else {
        alert('댓글 삭제에 실패했습니다.');
      }
    } catch (e) {
      console.error('댓글 삭제 실패:', e);
      alert('댓글 삭제 중 오류가 발생했습니다.');
    }
  });

  // 초기 로드
  loadComments();
});


