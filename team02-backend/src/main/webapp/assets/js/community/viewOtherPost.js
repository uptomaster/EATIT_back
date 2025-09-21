document.addEventListener("DOMContentLoaded", () => {
	const recommendBtn = document.getElementById('recommendBtn');
	const likesSpan = document.querySelector('.likes');
	const counterRecommend = document.querySelector('#recommendCount');
	const modifyBtn = document.querySelector(".modify-btn");
	const deleteBtn = document.querySelector(".delete-btn");
	const submitBtn = document.querySelector(".submit-btn");
	const postNumber = window.postNumber;
	const viewCountEl = document.querySelector("#view_count");

	// 조회수 반영
	if (!postNumber || !viewCountEl) return;

	if (!window.hasCountedView) {
		window.hasCountedView = true;

		const postNumber = window.postNumber;
		const viewCountEl = document.querySelector("#view_count");
		if (postNumber && viewCountEl) {
			fetch(`/community/postViewCount.co?postNumber=${postNumber}`, { method: "POST" })
				.then(res => res.json())
				.then(data => {
					if (data?.viewCount !== undefined) {
						viewCountEl.textContent = `${data.viewCount}`;
					}
				}).catch(console.error);
		}
	}

	// 추천 버튼 클릭
	recommendBtn?.addEventListener('click', async () => {
	    if (!postNumber || window.memberNumber === null) {
	        alert('로그인 후 이용해주세요.');
	        window.location.href = `${window.ctx}/login/login.lo`;
	        return;
	    }

	    try {
	        const res = await fetch(`${window.ctx}/community/postlike.co?postNumber=${postNumber}`, {
	            method: 'POST',
	            headers: { 'Accept': 'application/json' }
	        });
	        const data = await res.json();

	        if (data.success) {
	            // 하단 추천수
	            counterRecommend.textContent = `추천 ${data.likeCount}`;
	            counterRecommend.classList.add('bump');
	            setTimeout(() => counterRecommend.classList.remove('bump'), 300);

	            // 상단 추천수
	            const topLikeSpan = document.querySelector('.post_like_area span:last-child');
	            if (topLikeSpan) {
	                topLikeSpan.textContent = data.likeCount;
	            }

	        } else {
	            alert(data.message || '추천 실패');
	        }
	    } catch (err) {
	        console.error(err);
	        alert('추천 처리에 실패했습니다.');
	    }
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
        const deleteBtnPost = e.target.closest(".delete-btn-post");
        if (!deleteBtnPost) return;

        const postNumber = deleteBtnPost.dataset.boardNumber;
        const postType = deleteBtnPost.dataset.postType; // 게시판 타입
        if (!postNumber) return alert("게시글 번호가 없습니다.");

        if (!confirm("정말 삭제하시겠습니까?")) return;
		
		// 클릭 이벤트 전파 차단
	   e.stopPropagation();
	   e.preventDefault();
	   
	   deleteBtnPost.disabled = true; // 삭제 버튼 비활성화
	
        try {
            const res = await fetch(`${window.ctx}/community/postDeleteOk.co?postNumber=${encodeURIComponent(postNumber)}`, {
                method: "GET"
            });

            if (!res.ok) throw new Error("삭제 요청 실패");

            // 삭제 후 게시판별 목록 이동
            let redirectUrl = "";
            switch ((postType || "").toUpperCase()) {
                case "FREE":
                    redirectUrl = `${window.ctx}/community/freeBoardListOk.co`;
                    break;
                case "PROMOTION":
                    redirectUrl = `${window.ctx}/community/promoBoardListOk.co`;
                    break;
                case "RECIPE":
                    redirectUrl = `${window.ctx}/community/recipeBoardListOk.co`;
                    break;
                default:
                    redirectUrl = `${window.ctx}/community/communityMainOk.co`;
            }

            alert("게시글이 삭제되었습니다.");
            window.location.href = redirectUrl;

        } catch (err) {
            console.error("게시글 삭제 실패:", err);
            alert("게시글 삭제에 실패했습니다.");
		} finally {
	        deleteBtnPost.disabled = false; // 삭제 버튼 다시 활성화
	    }
    });

});




// ===== 댓글  =====
document.addEventListener('DOMContentLoaded', () => {
	const postNumber = window.postNumber ? parseInt(window.postNumber, 10) : null;
	const memberNumber = window.memberNumber ? parseInt(window.memberNumber, 10) : null;
	const adminNumber = window.adminNumber ? parseInt(window.adminNumber, 10) : null;
	const postType = (window.postType || '').toUpperCase();
	const isInquiry = (postType === 'INQUIRY');

	const listEl = document.getElementById('commentList');
	const inputEl = document.getElementById('commentInput');
	const submitEl = document.getElementById('commentSubmit');
	const ctx = window.ctx || document.body.dataset.contextPath || '';
	const myIconEl = document.getElementById('myCommentIcon');

	if (myIconEl) {
		if (adminNumber && Number(adminNumber) > 0) {
			myIconEl.src = `${ctx}/assets/img/${encodeURIComponent('관리자')}.png`;
			myIconEl.alt = '관리자';
		} else if (memberNumber) {
			const gradeByTotal = (total) => {
				if (total <= 100000) return '씨앗';
				if (total <= 300000) return '새싹';
				if (total <= 700000) return '잎새';
				if (total <= 1500000) return '가지';
				return '나무';
			};
			(async () => {
				try {
					const res = await fetch(`${ctx}/main/gradeInfo.ma`, { headers: { 'Accept': 'application/json' } });
					const data = res.ok ? await res.json() : null;
					const total = Number(data?.totalPayment) || 0;
					const grade = gradeByTotal(total);
					myIconEl.src = `${ctx}/assets/img/${encodeURIComponent(grade)}.png`;
					myIconEl.alt = grade;
					//로딩 실패시 기본이미지(씨앗)
					myIconEl.onerror = () => { myIconEl.src = `${ctx}/assets/img/${encodeURIComponent('씨앗')}.png`; };
				} catch (_) {
					/* 실패 시 기본 아이콘 유지 */
				}
			})();
		}
	}

	function iconSrcByGrade(c) {
		// 관리자면 '관리자.png', 아니면 등급명 그대로 파일명 사용
		const name = (c.adminNumber && Number(c.adminNumber) > 0) ? '관리자' : (c.treeGrade || '씨앗');
		// 한글 파일명 인코딩
		return `${ctx}/assets/img/${encodeURIComponent(name)}.png`;
	}
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
				headers: { 'Accept': 'application/json', 'X-Requested-With': 'XMLHttpRequest' }
			});
			if (!res.ok) throw new Error('댓글 목록 요청 실패');
			const items = await safeJson(res);
			renderComments(Array.isArray(items) ? items : []);
		} catch (e) {
			console.error('댓글 목록 불러오기 실패:', e);
			listEl.innerHTML = `<li class="comment_item">댓글을 불러오지 못했습니다.</li>`;
		}
	}

	function emptyMessage() {
		return isInquiry ? '아직 답변이 없습니다.' : '댓글이 없습니다.';
	}
	// 렌더링
	function renderComments(items) {
		listEl.innerHTML = '';
		if (!items.length) {
			listEl.innerHTML = `<li class="comment_item">${emptyMessage()}</li>`;
			return;
		}
		const frag = document.createDocumentFragment();

		items.forEach(c => {
			const isMine = memberNumber && String(memberNumber) === String(c.memberNumber);
			const isAdmin = adminNumber && Number(adminNumber) > 0;

			// ⬇️ 등급/관리자에 맞는 아이콘 경로 계산 (여기서 icon을 만든다!)
			const icon = iconSrcByGrade(c);

			const li = document.createElement('li');
			li.className = 'comment_item';
			li.dataset.number = c.commentNumber;

			li.innerHTML = `
        <div class="comment_profile_container">
          <img class="comment_profile" src="${icon}" alt="프로필" onerror="this.src='${ctx}/assets/img/씨앗.png'"/>
          <div class="comment_info">
            <span class="comment_author">${escHtml(c.memberId)}</span>
            <time class="comment_timeline">${escHtml(c.commentedDate || '')}</time>
            ${(isMine || isAdmin) ? `<button class="comment_delete" data-number="${c.commentNumber}" title="댓글 삭제">[댓글 삭제]</button>` : ``}
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
		const canWrite = (memberNumber && memberNumber > 0) || (adminNumber && adminNumber > 0);
		if (!canWrite) return alert('로그인 후 이용해주세요.');

		try {
			const res = await fetch(`/comment/writeOk.cm`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json; charset=utf-8',
					'Accept': 'application/json',
					'X-Requested-With': 'XMLHttpRequest'
				},
				body: JSON.stringify({ postNumber, commentContent: content })
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
				headers: { 'Accept': 'application/json', 'X-Requested-With': 'XMLHttpRequest' }
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

// 문의글 삭제
document.addEventListener("DOMContentLoaded", function() {
	const deleteBtn = document.querySelector(".delete-btn");

	if (deleteBtn) {
		deleteBtn.addEventListener("click", async function() {
			const confirmed = confirm("정말 삭제하시겠습니까?");
			if (!confirmed) return;

			const postNumber = this.dataset.boardNumber;
			if (!postNumber) {
				alert("게시글 번호가 존재하지 않습니다.");
				return;
			}

			try {
				// 삭제 요청 전송 (비동기)
				const response = await fetch(`${window.ctx}/community/inquiryDeleteOk.co?postNumber=${postNumber}`, {
					method: "GET"
				});

				if (response.ok) {
					alert("삭제되었습니다.");
					// 확인 누르면 문의 목록으로 이동
					location.href = `${window.ctx}/community/inquiryListOk.co`;
				} else {
					alert("삭제에 실패했습니다. 다시 시도해주세요.");
				}
			} catch (error) {
				console.error("삭제 요청 오류:", error);
				alert("오류가 발생했습니다.");
			}
		});
	}
});



