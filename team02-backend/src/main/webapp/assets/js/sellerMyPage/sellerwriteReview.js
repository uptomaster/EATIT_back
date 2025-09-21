document.addEventListener("DOMContentLoaded", function () {
  const starButtons = document.querySelectorAll(".writereview_counting_Star_button");
  const starImages = document.querySelectorAll(".writereview_counting_Star");
  const submitBtn = document.querySelector(".seller_write_review_submit_btn");
  const cancelBtn = document.querySelector(".seller_write_review_cancel_btn");
  const contentInput = document.getElementById("content");
	
  let currentRating = 0;

  const yellowStarSrc = "./../assets/img/counting_Star.png";   // 노란별
  const grayStarSrc = "./../assets/img/gray_shake_it_ya.png"; // 회색별

  // 별 클릭 이벤트
  starButtons.forEach((button, index) => {
    button.addEventListener("click", function (e) {
      e.preventDefault(); // 버튼 클릭 시 폼 제출 방지
      currentRating = index + 1;

			const hidden = document.getElementById("reviewRating");
			if (hidden) hidden.value = currentRating;
			console.log("currentRating : "+currentRating);
			
      // 별 이미지 업데이트
      starImages.forEach((img, i) => {
        img.src = i < currentRating ? yellowStarSrc : grayStarSrc;
      });
    });
  });

  // 작성 완료 클릭 시
  submitBtn.addEventListener("click", function (e) {
      e.preventDefault();

      if(currentRating === 0) {
          alert("별점을 선택해주세요.");
          return;
      }

      if(!contentInput.value.trim()) {
          alert("내용을 입력해주세요.");
          return;
      }

      document.querySelector("form").submit();
  });

  // 작성 취소 클릭 시 이전 페이지로 이동
  cancelBtn.addEventListener("click", function (e) {
      e.preventDefault();
      history.back();
  });
});
