window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header_login.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('header').innerHTML = data;
    });

  // 푸터 불러오기
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    });
});
document.addEventListener("DOMContentLoaded", function () {
  const starButtons = document.querySelectorAll(".seller_write_review_counting_Star_button");
  const starImages = document.querySelectorAll(".seller_write_review_counting_Star");
  let currentRating = 0;

  const yellowStarSrc = "./../../assets/img/counting_star.png"; // 노란별
  const grayStarSrc = "./../../assets/img/gray_shake_it_ya.png";     // 회색별

  // 별 클릭 이벤트
  starButtons.forEach((button, index) => {
    button.addEventListener("click", function (e) {
      e.preventDefault(); // 버튼 클릭 시 폼 제출 방지
      currentRating = index + 1;

      // 별 이미지 업데이트
      starImages.forEach((img, i) => {
        img.src = i < currentRating ? yellowStarSrc : grayStarSrc;
      });
    });
  });

  // 작성 완료 버튼 클릭 시 유효성 검사
  const submitBtn = document.querySelector(".seller_write_review_submit_btn");
  submitBtn.addEventListener("click", function (e) {
    const title = document.getElementById("title").value.trim();
    const content = document.getElementById("content").value.trim();

    if (title === "" || content === "") {
      e.preventDefault(); // 제출 막기
      alert("글을 작성해주세요.");
    } else {
      alert("작성되었습니다.");
    }
  });
});
document.addEventListener("DOMContentLoaded", function () {
  // 이미 작성된 코드들이 있을 경우, 아래 코드만 추가하면 됩니다

  const cancelBtn = document.querySelector(".seller_write_review_cancel_btn");

  cancelBtn.addEventListener("click", function (e) {
    e.preventDefault(); // 기본 리셋 동작 방지
    history.back();     // 이전 페이지로 이동
  });
});