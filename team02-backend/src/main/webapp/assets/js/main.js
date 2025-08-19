const slideBox = document.querySelector(".main_slide_box");
const slideImg = document.querySelectorAll(".main_slide_img");
const prevBtn = document.querySelector(".main_banner_prev");
const nextBtn = document.querySelector(".main_banner_next");

const slideWidth = 1920;
const slideCnt = slideImg.length;
let currentIdx = 1; 
let slideInterval;

// 슬라이드 앞뒤로 복제 슬라이드 생성
const firstClone = slideImg[0].cloneNode(true);
const lastClone = slideImg[slideCnt - 1].cloneNode(true);

firstClone.classList.add("clone");
lastClone.classList.add("clone");

slideBox.appendChild(firstClone);
slideBox.insertBefore(lastClone, slideImg[0]);

// 총 슬라이드 수 (복제 포함)
const totalSlides = slideCnt + 2;

// 슬라이드 박스 넓이 조정
slideBox.style.width = `${slideWidth * totalSlides}px`;

// 초기 위치 설정 (진짜 첫 슬라이드)
slideBox.style.left = `-${slideWidth * currentIdx}px`;

// 슬라이드 이동 함수
function moveSlide(index) {
  slideBox.style.transition = '0.8s ease';
  slideBox.style.left = `-${index * slideWidth}px`;
}

// 자동 슬라이드
function startSlide() {
  slideInterval = setInterval(() => {
    currentIdx++;
    moveSlide(currentIdx);

    // 마지막(가짜 첫 슬라이드) 도달 시 → 진짜 첫 슬라이드로 순간 이동
    slideBox.addEventListener("transitionend", handleLoop);
  }, 5000);
}

// 무한 루프 처리 함수
function handleLoop() {
  slideBox.removeEventListener("transitionend", handleLoop);

  if (currentIdx === totalSlides - 1) {
    // 마지막 슬라이드 → 첫 슬라이드
    slideBox.style.transition = "none";
    currentIdx = 1;
    slideBox.style.left = `-${slideWidth * currentIdx}px`;
  } else if (currentIdx === 0) {
    // 첫 슬라이드 → 마지막 슬라이드
    slideBox.style.transition = "none";
    currentIdx = slideCnt;
    slideBox.style.left = `-${slideWidth * currentIdx}px`;
  }
}

// 수동 이동 (다음)
function nextMove() {
  if (currentIdx >= totalSlides - 1) return;
  currentIdx++;
  moveSlide(currentIdx);
}

// 수동 이동 (이전)
function prevMove() {
  if (currentIdx <= 0) return;
  currentIdx--;
  moveSlide(currentIdx);
}

// 정지
function stopSlide() {
  clearInterval(slideInterval);
}

// 수동 버튼 이벤트
nextBtn.addEventListener("click", () => {
  stopSlide();
  nextMove();
  slideBox.addEventListener("transitionend", handleLoop);
  startSlide();
});

prevBtn.addEventListener("click", () => {
  stopSlide();
  prevMove();
  slideBox.addEventListener("transitionend", handleLoop);
  startSlide();
});

// 시작
startSlide();


const articles = document.querySelectorAll('.main_food_buy_article');
const prev = document.querySelector('.main_content_prev a');
const next = document.querySelector('.main_content_next a');

let startIndex = 0;      // 보이는 첫번째 인덱스
const visibleCount = 4;  // 한 번에 보일 개수
const total = articles.length;

function updateVisible() {
  articles.forEach((el, idx) => {
    if (idx >= startIndex && idx < startIndex + visibleCount) {
      el.classList.add('visible');
    } else {
      el.classList.remove('visible');
    }
  });
}

// 초기 보이기
updateVisible();

// 다음 버튼 클릭
next.addEventListener('click', e => {
  e.preventDefault();
  if (startIndex + visibleCount < total) {
    startIndex++;
    updateVisible();
  }
});

// 이전 버튼 클릭
prev.addEventListener('click', e => {
  e.preventDefault();
  if (startIndex > 0) {
    startIndex--;
    updateVisible();
  }
});

