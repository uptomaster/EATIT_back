
// 슬라이드 관련 요소 선택
const slideBox = document.querySelector(".main_slide_box");
let slideImgs = document.querySelectorAll(".main_slide_img");
const prevBtn = document.querySelector(".main_banner_prev");
const nextBtn = document.querySelector(".main_banner_next");

// 슬라이드 설정 값
const slideWidth = 1920;
const slideCnt = slideImgs.length;
let currentIdx = 1;
let slideInterval;

// 슬라이드 무한 루프를 위한 첫/마지막 슬라이드 복제
const firstClone = slideImgs[0].cloneNode(true);
const lastClone = slideImgs[slideCnt - 1].cloneNode(true);
firstClone.classList.add("clone");
lastClone.classList.add("clone");

// 복제 슬라이드 추가 (맨 앞, 맨 뒤)
slideBox.appendChild(firstClone);
slideBox.insertBefore(lastClone, slideImgs[0]);

// 복제 후 슬라이드 목록 다시 조회
slideImgs = document.querySelectorAll(".main_slide_img");

// 전체 슬라이드 개수 재설정 (복제 포함)
const totalSlides = slideImgs.length;

// 슬라이드 박스 넓이 설정
slideBox.style.width = `${slideWidth * totalSlides}px`;

// 슬라이드 초기 위치 (진짜 첫 번째 슬라이드)
slideBox.style.left = `-${slideWidth * currentIdx}px`;

// 슬라이드 이동 함수

// 슬라이드 이동 (트랜지션 적용)
function moveSlide(index) {
  slideBox.style.transition = '0.8s ease';
  slideBox.style.left = `-${index * slideWidth}px`;
}

// 무한 루프 처리 (복제 슬라이드에서 실제 슬라이드로 순간 이동)
function handleLoop() {
  if (currentIdx === totalSlides - 1) {
    slideBox.style.transition = "none";
    currentIdx = 1;
    slideBox.style.left = `-${slideWidth * currentIdx}px`;
  } else if (currentIdx === 0) {
    slideBox.style.transition = "none";
    currentIdx = slideCnt;
    slideBox.style.left = `-${slideWidth * currentIdx}px`;
  }
}

// 트랜지션 종료 후 무한 루프 처리 실행
slideBox.addEventListener("transitionend", handleLoop);

// 자동 슬라이드 시작
function startSlide() {
  slideInterval = setInterval(() => {
    currentIdx++;
    moveSlide(currentIdx);
  }, 5000); // 5초마다 슬라이드 이동
}

// 자동 슬라이드 정지
function stopSlide() {
  clearInterval(slideInterval);
}

// 다음 슬라이드로 이동
function nextMove() {
  if (currentIdx >= totalSlides - 1) return;
  currentIdx++;
  moveSlide(currentIdx);
}

// 이전 슬라이드로 이동
function prevMove() {
  if (currentIdx <= 0) return;
  currentIdx--;
  moveSlide(currentIdx);
}

// 다음 버튼 클릭 시
nextBtn.addEventListener("click", (e) => {
  e.preventDefault();
  stopSlide();
  nextMove();
  startSlide();
});

// 이전 버튼 클릭 시
prevBtn.addEventListener("click", (e) => {
  e.preventDefault();
  stopSlide();
  prevMove();
  startSlide();
});

// 페이지 로드 시 자동 슬라이드 시작
startSlide();

// 콘텐츠 요소 및 버튼 선택
const articles = document.querySelectorAll('.main_food_buy_article');
const prev = document.querySelector('.main_content_prev a');
const next = document.querySelector('.main_content_next a');

// 콘텐츠 리스트 관련 설정
let startIndex = 0;              // 현재 시작 인덱스
const visibleCount = 4;          // 한 번에 보여줄 콘텐츠 개수
const total = articles.length;   // 전체 콘텐츠 개수

// 화면에 표시할 콘텐츠 업데이트
function updateVisible() {
  articles.forEach((el, idx) => {
    if (idx >= startIndex && idx < startIndex + visibleCount) {
      el.classList.add('visible');     // 보여줄 콘텐츠
    } else {
      el.classList.remove('visible');  // 숨길 콘텐츠
    }
  });
}

// 페이지 로딩 시 초기 콘텐츠 표시
updateVisible();

// 다음 버튼 클릭 시 다음 콘텐츠 표시
next.addEventListener('click', e => {
  e.preventDefault();
  if (startIndex + visibleCount < total) {
    startIndex++;
    updateVisible();
  }
});

// 이전 버튼 클릭 시 이전 콘텐츠 표시
prev.addEventListener('click', e => {
  e.preventDefault();
  if (startIndex > 0) {
    startIndex--;
    updateVisible();
  }
});