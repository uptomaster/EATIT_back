document.addEventListener('DOMContentLoaded', () => {
  const recommendBtn = document.getElementById('recommendBtn');
  const likesSpan = document.querySelector('.likes');
  const counterRecommend = document.querySelector('#recommendCount');

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
});


document.addEventListener('DOMContentLoaded', () => {
  // ===== 댓글 추천 버튼 =====
  const commentRecommendButtons = document.querySelectorAll('.comment_recommend_container .recommend');

  commentRecommendButtons.forEach((btn) => {
    btn.addEventListener('click', () => {
      const countElement = btn.parentElement.querySelector('.recommend_count');
      let currentCount = parseInt(countElement.textContent, 10);
      countElement.textContent = ++currentCount;

      // 애니메이션 효과
      countElement.classList.add('bump');
      setTimeout(() => countElement.classList.remove('bump'), 300);
    });
  });
});


document.addEventListener('DOMContentLoaded', () => {
  const commentForm = document.querySelector('.comment_form');
  const commentList = document.querySelector('.comment_list');
  const commentDelete = document.querySelectorAll('.comment_delete');
  // 댓글 작성 시 실시간 추가
  commentForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const input = commentForm.querySelector('input[name="comment"]');
    const commentText = input.value.trim();
    if (!commentText) return;

    // 새로운 댓글 요소 생성
    const newComment = document.createElement('li');
    newComment.classList.add('comment_item');
    newComment.innerHTML = `
      <div class="comment_profile_container">
        <img class="comment_profile" src="./../../assets/img/나무.png" alt="나무 등급 프로필" />
        <div class="comment_info">
          <span class="comment_author">namhyuk</span>
          <time class="comment_timeline" datetime="${new Date().toISOString()}">
          [${new Date().toLocaleString()}]</time>
          <button class="comment_delete" title="댓글 삭제">[댓글 삭제]</button>
          <p class="comment_text">${commentText}</p>
        </div>
      </div>
      <div class="comment_recommend_container">
        <button class="recommend" title="댓글 추천">
          <img src="./../../assets/img/like.jpg" alt="댓글 추천 버튼" />
        </button>
        <span class="recommend_count">0</span>
      </div>
    `;




    commentList.appendChild(newComment);

    // 입력창 초기화
    input.value = '';

    // 추가된 댓글에도 좋아요 기능 적용
    attachCommentLikeEvent(newComment.querySelector('.recommend'));
  });

  // 댓글 좋아요 기능
  function attachCommentLikeEvent(button) {
    button.addEventListener('click', () => {
      const countSpan = button.parentElement.querySelector('.recommend_count');
      let count = parseInt(countSpan.textContent) || 0;
      count++;
      countSpan.textContent = count;

      // 애니메이션 효과
      button.classList.add('liked');
      setTimeout(() => {
        button.classList.remove('liked');
      }, 400);
    });
  }

  // 기존 댓글 좋아요 버튼에도 적용
  document.querySelectorAll('.comment_recommend').forEach((btn) => {
    attachCommentLikeEvent(btn);
  });
});



// 댓글 삭제기능
document.addEventListener('DOMContentLoaded', () => {
  const commentList = document.querySelector('.comment_list');

  commentList.addEventListener('click', (e) => {
    if (e.target.classList.contains('comment_delete') || e.target.closest('.comment_delete')) {
      const deleteBtn = e.target.closest('.comment_delete');
      const commentItem = deleteBtn.closest('li.comment_item');
      if (commentItem) {
        commentItem.remove();
      }
    }
  });
});
