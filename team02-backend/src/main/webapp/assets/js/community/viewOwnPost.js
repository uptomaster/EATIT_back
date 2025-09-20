document.addEventListener("DOMContentLoaded", () => {
    const recommendBtn = document.getElementById('recommendBtn');
    const counterRecommend = document.getElementById('recommendCount');
    const postNumber = window.postNumber;
    //const memberNumber = window.memberNumber;
    const viewCountEl = document.getElementById('view_count');


    // 조회수 1회 반영
    if (postNumber && viewCountEl && !window.hasCountedView) {
        window.hasCountedView = true;
        fetch(`/community/postViewCount.co?postNumber=${postNumber}`, { method: "POST" })
            .then(res => res.json())
            .then(data => {
                if (data?.viewCount !== undefined) {
                    viewCountEl.textContent = data.viewCount;
                }
            })
            .catch(console.error);
    }
	

    // 추천 버튼 클릭
	recommendBtn?.addEventListener('click', async () => {
		const memberNumber = window.memberNumber;
		
		if (!postNumber || memberNumber === null) {
		    alert('로그인 후 이용해주세요.');
		    window.location.href = `${window.ctx}/login/login.lo`;
		    return;
		}

	    try {
			const res = await fetch(`${window.ctx}/community/postlike.co?postNumber=${postNumber}`, {
			    method: 'POST',
			    headers: { 'Accept': 'application/json' }
			});
	        if (!res.ok) throw new Error('서버 요청 실패');

	        const data = await res.json();
	        if (!data.success) {
	            alert(data.message || '추천 실패');
	        } else {
	            counterRecommend.textContent = `추천 ${data.likeCount}`;
	            counterRecommend.classList.add('bump');
	            setTimeout(() => counterRecommend.classList.remove('bump'), 300);
	        }
	    } catch (err) {
	        console.error(err);
	        alert('추천 처리에 실패했습니다.');
	    }
	});
	
	
	
	
});
