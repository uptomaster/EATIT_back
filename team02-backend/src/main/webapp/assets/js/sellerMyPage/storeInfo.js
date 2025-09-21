
/* ========== 버튼 이동 경로 지정 ========== */
let edit_store_info_btn = document.getElementById('edit_store_info_btn');
edit_store_info_btn.addEventListener('click', () => {
  //가게 상세 정보 수정 -> 개인정보 수정창으로
  location.replace('${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se');
});

let originBtn = document.getElementById('edit_store_origin_btn');
originBtn.addEventListener('click', () =>{
  location.replace('/sellerMyPage/originList.se');
});  
/* ========== // 버튼 이동 경로 지정 끝 ========== */


/* ========== 지도 api ========== */
// 1. 지도 준비물 만들기
// 일단 지도객체 를 만든다. 초기 위치는 이후에 바꿀 예정이므로 신경쓰지 않아도 됨
var mapContainer = document.getElementById('storeInfo_map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 2. 지도 (객체) 를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 3. 사용할 정보를 받아와 js 파일에 저장하기
// jsp 파일에서 가게 도로명 주소 받아오기
let addr = document.getElementById('roadAddr').value;
console.log("addr.value :  "+addr);
// jsp 파일에서 가게명 받아오기 -> 마커
var storeName = document.getElementById('storeName').value;
console.log('storeName : '+storeName);
// jsp 파일에서 위도 경도 받아오기
var storeLatitude = document.getElementById('storeLatitude').value;
console.log('storeLatitude : '+storeLatitude);
var storeLongitude = document.getElementById('storeLongitude').value;
console.log('storeLongitude : '+storeLongitude);
/*
// 좌표로 바로 지우 띄우기
// --------------------------------------------
// 주소를 좌표로 변환 받아서 coords에 저장함
var coords = new kakao.maps.LatLng(storeLongitude, storeLatitude);
console.log("coords :  "+coords);

// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
map.setCenter(coords);		


// 5. 마커표시
// 결과값으로 받은 위치를 마커로 표시합니다
var marker = new kakao.maps.Marker({
    map: map,
    position: coords
});
console.log(coords);

// 인포윈도우로 장소에 대한 설명을 표시합니다
var infowindow = new kakao.maps.InfoWindow({
    content: '<div style="width:150px;text-align:center;padding:6px 0;">'+storeName+'</div>'
});
// 실제 마커표시
infowindow.open(map, marker);
*/

// --------------------------------------------

// 4. 주소-좌표 변환 하기 & 지도 표시 
// 주소-좌표 변환 객체를 생성합니다

var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다 - api 가이드에 주어진 메소드 참고
geocoder.addressSearch(addr, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {
				
				// 주소를 좌표로 변환 받아서 coords에 저장함
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);		
				
				// 5. 마커표시
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });
				console.log(coords);
				
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+storeName+'</div>'
        });
				// 실제 마커표시
        infowindow.open(map, marker);

    } 
});    
/* ========== // 지도 api 끝========== */
