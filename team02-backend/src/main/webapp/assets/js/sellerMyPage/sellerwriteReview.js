
document.addEventListener("DOMContentLoaded", function() {
	const starButtons = document.querySelectorAll(".seller_write_review_counting_Star_button");
	const starImages = document.querySelectorAll(".seller_write_review_counting_Star");
	const ratingInput = document.getElementById("seller_write_review_rating_value");
	const form = document.getElementById("seller_write_review_form");
	let currentRating = 0;

	const yellowStarSrc = "${pageContext.request.contextPath}/assets/img/counting_star.png"; // 노란별
	const grayStarSrc = "${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png";     // 회색별

	starButtons.forEach((button, index) => {
	    button.addEventListener("click", function (e) {
	      e.preventDefault();

	      currentRating = index + 1; // 몇 번째 별인지 기록

	      // index보다 작은 별은 노란색, 나머지는 회색
	      starImages.forEach((img, i) => {
	        if (i <= index) {
	          img.src = yellowStarSrc;
	        } else {
	          img.src = grayStarSrc;
	        }
	      });

	      // 서버 전송용 hidden input 반영
	      if (ratingInput) {
	        ratingInput.value = currentRating;
	      }
	    });
	  });

	// 제출 전 최소 검증(1점 이상)
  form.addEventListener("submit", function (e) {
    if (!ratingInput.value || Number(ratingInput.value) < 1) {
      e.preventDefault();
      alert("별점을 1점 이상 선택해주세요.");
    }
  });
		
	if (content.length < 5) {
		e.preventDefault();
		alert('리뷰 내용을 5자 이상 입력해주세요.');
		return;
	}

	// 작성 완료 버튼 클릭 시 유효성 검사
	const submitBtn = document.querySelector(".seller_write_review_submit_btn");
	submitBtn.addEventListener("click", function(e) {
		const title = document.getElementById("title").value.trim();
		const content = document.getElementById("content").value.trim();

		if (title === "" || content === "") {
			e.preventDefault(); // 제출 막기
			alert("글을 작성해주세요.");
		} else {
			alert("작성되었습니다.");
		}
	});

	// 이미 작성된 코드들이 있을 경우, 아래 코드만 추가하면 됩니다

	const cancelBtn = document.querySelector(".seller_write_review_cancel_btn");

	cancelBtn.addEventListener("click", function(e) {
		e.preventDefault(); // 기본 리셋 동작 방지
		history.back();     // 이전 페이지로 이동
	});
});