
let edit_store_info_btn = document.getElementById('edit_store_info_btn');
edit_store_info_btn.addEventListener('click', () => {
  //가게 상세 정보 수정 -> 개인정보 수정창으로
  location.replace('${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se');
});

let originBtn = document.getElementById('edit_store_origin_btn');
originBtn.addEventListener('click', () =>{
  location.replace('/sellerMyPage/originList.se');
});  

/* ========== 지도 api ========== */
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 
// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
// 버튼 클릭에 따라 지도 이동 기능을 막거나 풀고 싶은 경우에는 map.setDraggable 함수를 사용합니다
function setDraggable(draggable) {
    // 마우스 드래그로 지도 이동 가능여부를 설정합니다
    map.setDraggable(draggable);    
}
// 버튼 클릭에 따라 지도 확대, 축소 기능을 막거나 풀고 싶은 경우에는 map.setZoomable 함수를 사용합니다
function setZoomable(zoomable) {
    // 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
    map.setZoomable(zoomable);    
}

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);


const storeImageChangeBtns = document.querySelectorAll('.store_info_edit_btns');

// 1) 필요한 엘리먼트 캐싱 (기존 id/클래스명 절대 변경 X)
const changeBtns = document.querySelectorAll('.store_info_edit_btns'); // 업로드 트리거 버튼(들)
const form       = document.getElementById('store_image_form');       // 숨김 업로드 폼
const fileInput  = document.getElementById('store_image_file');       // 파일 input
const img        = document.getElementById('store_main_img');         // 현재 노출 중인 가게 이미지
const preview    = document.getElementById('store_preview_img');      // 미리보기 이미지(초기 display:none)



/*// 2) 버튼 클릭 → 파일 선택창 열기
changeBtns.forEach(btn => {
  btn.addEventListener('click', (e) => {
    e.preventDefault();           // a/button 기본 동작 차단
    fileInput.click();            // 숨김 input 클릭 트리거
  });
});*/

// 3) 파일 선택 시: 간단 유효성 검사 → 미리보기 → 즉시 업로드
fileInput.addEventListener('change', () => {
  const file = fileInput.files && fileInput.files[0];
  if (!file) return;


  // (B) 화면 즉시 반영(미리보기) - 기존 이미지는 지우지 않고 보존
  const url = URL.createObjectURL(file);
  preview.src = url;
  preview.style.display = 'block';

  // (C) 서버 전송: 반드시 .se 서블릿 경로(폼 action)로 제출 (MVC2 규칙)
  //  - 컨트롤러에서: 기존 이미지 DB/파일 삭제 → 새 이미지 저장/재등록 → storeInfo.se로 리다이렉트
  form.submit();
});

