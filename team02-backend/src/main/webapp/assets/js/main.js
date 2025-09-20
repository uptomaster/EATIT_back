
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

// 위치 모달 관련 요소 선택
const locationModal = document.querySelector('.location_modal');
const locationBtn = document.querySelector('#location_btn');
const locationClose = document.querySelector('.location_modal_close');
const locationOverlay = document.querySelector('.location_modal_overlay');
const saveLocation = document.querySelector(".save_location");

// '위치' 버튼 클릭 시 모달 열기
locationBtn.addEventListener("click", () => {
  locationModal.style.display = "flex";
});

// 모달 닫기
locationClose.addEventListener("click", () => {
  locationModal.style.display = "none";
});
locationOverlay.addEventListener("click", () => {
  locationModal.style.display = "none";
});

// Daum Postcode API 함수 (기존 코드를 그대로 사용)
function sample4_execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // ... (기존 Daum Postcode 로직) ...
      var roadAddr = data.roadAddress;
      var extraRoadAddr = '';
      if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraRoadAddr += data.bname;
      }
      if(data.buildingName !== '' && data.apartment === 'Y'){
         extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
      }
      if(extraRoadAddr !== ''){
          extraRoadAddr = ' (' + extraRoadAddr + ')';
      }
      document.getElementById('sample4_postcode').value = data.zonecode;
      document.getElementById("sample4_roadAddress").value = roadAddr;
      document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
      if(roadAddr !== ''){
          document.getElementById("sample4_extraAddress").value = extraRoadAddr;
      } else {
          document.getElementById("sample4_extraAddress").value = '';
      }
      var guideTextBox = document.getElementById("guide");
      if(data.autoRoadAddress) {
          var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
          guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
          guideTextBox.style.display = 'block';
      } else if(data.autoJibunAddress) {
          var expJibunAddr = data.autoJibunAddress;
          guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
          guideTextBox.style.display = 'block';
      } else {
          guideTextBox.innerHTML = '';
          guideTextBox.style.display = 'none';
      }
    }
  }).open();
}


// 주소 입력 → 거리순 가게 목록 요청
function sendAddress() {
    const roadAddress = document.getElementById("sample4_roadAddress").value;

    if (!roadAddress) {
        alert("주소를 입력해주세요.");
        return;
    }

    // 주소만 서버로 전송 (서버에서 위경도 계산 후 거리 계산)
    fetch(`${window.location.origin}/storeDistanceListByAddress`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ address: roadAddress })
    })
    .then(res => res.json())
    .then(data => {
        console.log("거리순 가게 목록:", data);
        renderStoreList(data); // 거리순 가게 렌더링
        document.querySelector(".location_modal").style.display = "none"; // 모달 닫기
    })
    .catch(err => console.error("가게 목록 로드 실패:", err));
}

// 저장 버튼 클릭 이벤트
document.querySelector(".save_location").addEventListener("click", e => {
    e.preventDefault();

    const roadAddress = document.getElementById("sample4_roadAddress").value;

    if (!roadAddress) {
        alert("주소를 입력해주세요.");
        return;
    }

    // MainListController에 주소 전달
    const url = `${window.location.origin}/main.ma?address=${encodeURIComponent(roadAddress)}`;
    window.location.href = url; // 페이지 새로고침하며 서버에 주소 전달
});


// 가게 목록 렌더링
function renderStoreList(storeList) {
    const container = document.querySelector(".main_food_buy");
    if (!storeList || storeList.length === 0) {
        container.innerHTML = '<p style="color: #888">표시할 상품이 없습니다.</p>';
        return;
    }

    let html = '';
    storeList.forEach(store => {
        html += `
        <article class="main_food_buy_article store_item">
            <a href="${window.location.origin}/orders/storeDetail.or?itemNumber=${store.itemNumber}">
                <img src="${window.location.origin}/upload/${store.itemImageSystemName}" alt="${store.storeName} 이미지">
                <div class="main_store_info">
                    <div class="main_store_name">${store.storeName}</div>
                    <div class="main_menu_name">${store.itemName}</div>
                    <div class="main_open_time">영업시간: ${store.storeOpenTime} ~ ${store.storeCloseTime}</div>
                    <div class="main_price">${store.itemPrice}원</div>
                    <div class="main_distance">거리: ${store.distance.toFixed(2)} km</div>
                </div>
            </a>
        </article>`;
    });

    container.innerHTML = html;
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