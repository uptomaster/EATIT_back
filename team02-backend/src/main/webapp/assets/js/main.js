
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

function searchAddress() {
   let address = document.getElementById("addressInput").value;
   if(!address) {
     alert("주소를 입력해주세요.");
     return;
   }

   // 카카오 주소 검색 서비스
   var ps = new kakao.maps.services.Geocoder();

   ps.addressSearch(address, function(result, status) {
     if (status === kakao.maps.services.Status.OK) {
       let lat = result[0].y;
       let lng = result[0].x;

       // 결과 출력
       document.getElementById("addressResult").innerHTML = 
         `위도: ${lat}, 경도: ${lng} <br> 주소: ${result[0].address.address_name}`;

       // 서버에 위도/경도 전송 (예: AJAX)
       sendCoordinatesToServer(lat, lng);

     } else {
       alert("주소 검색 결과가 없습니다.");
     }
   });
 }

 function sendCoordinatesToServer(lat, lng) {
   // AJAX로 서버에 좌표 전달 (예: jQuery 사용)
   fetch('${pageContext.request.contextPath}/location/searchNearby', {
     method: 'POST',
     headers: {'Content-Type': 'application/json'},
     body: JSON.stringify({ latitude: lat, longitude: lng })
   })
   .then(response => response.json())
   .then(data => {
     console.log("서버에서 받은 근처 가게 목록:", data);
     // 여기서 가게 리스트 UI 갱신
   })
   .catch(err => {
     console.error("서버와 통신 중 에러 발생:", err);
   });
 }

 const saveBtn = document.querySelector('.save_location');

 saveBtn.addEventListener('click', () => {
   // 예: 입력된 주소 정보 가져오기
   const postcode = document.getElementById('sample4_postcode').value;
   const roadAddress = document.getElementById('sample4_roadAddress').value;
   const jibunAddress = document.getElementById('sample4_jibunAddress').value;
   const extraAddress = document.getElementById('sample4_extraAddress').value;

   if (!postcode || !roadAddress) {
     alert('주소를 정확히 입력해주세요.');
     return;
   }

   const addressData = {
     postcode,
     roadAddress,
     jibunAddress,
     extraAddress
   };

   // 서버에 주소 데이터 전송 예시 (fetch 사용)
   fetch('main.sa', {  // 실제 주소로 변경 필요
     method: 'POST',
     headers: {'Content-Type': 'application/json'},
     body: JSON.stringify(addressData)
   })
   .then(res => {
     if (!res.ok) throw new Error('저장 실패');
     return res.json();
   })
   .then(data => {
     alert('주소가 저장되었습니다.');
     // 모달 닫기 등 추가 동작
     locationModal.style.display = 'none';
   })
   .catch(err => {
     console.error(err);
     alert('주소 저장 중 오류가 발생했습니다.');
   });
 });


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