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

	 
// 사용할 정보를 받아와 js 파일에 저장하기
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
// 변수 확인
// ==================================================================
	
	
	var mapContainer = document.getElementById('storeInfo_map'); // ✅ id 일치

	// 주소/가게명/위경도는 그대로 사용(위에서 세팅해둔 변수 재사용)
	window.addEventListener('load', function () {
	  // 1) Kakao SDK 준비상태 점검(autoload=false 사용 시 필수)
	  if (!window.kakao || !kakao.maps || !kakao.maps.load) {
	    console.error('Kakao Maps SDK가 아직 준비되지 않았습니다. 스크립트 URL/키/네트워크를 확인하세요.');
	    return;
	  }

	  // 2) SDK 로드 완료 후에만 kakao.maps API 사용
	  kakao.maps.load(function () {
	    // (1) 지도 옵션/생성 - 이제서야 new kakao.maps.LatLng 사용 가능
	    var mapOption = {
	      center: new kakao.maps.LatLng(33.450701, 126.570667), // 기본 중심
	      level: 3
	    };
	    var map = new kakao.maps.Map(mapContainer, mapOption);

	    // (2) 위/경도 값이 있으면 지오코딩 없이 바로 표시 (성능상 유리)
	    var lat = parseFloat(storeLatitude);
	    var lng = parseFloat(storeLongitude);
	    if (!isNaN(lat) && !isNaN(lng)) {
	      var direct = new kakao.maps.LatLng(lat, lng);
	      var marker = new kakao.maps.Marker({ map: map, position: direct });
	      var iw = new kakao.maps.InfoWindow({
	        content: '<div style="width:150px;text-align:center;padding:6px 0;">' + storeName + '</div>'
	      });
	      iw.open(map, marker);
	      map.setCenter(direct);
	      return; // 좌표가 있으면 여기서 끝
	    }

	    // (3) 좌표가 없으면 주소 → 좌표 변환(libraries=services 필요)
	    var geocoder = new kakao.maps.services.Geocoder();
	    geocoder.addressSearch(addr, function (result, status) {
	      if (status === kakao.maps.services.Status.OK && result.length > 0) {
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        var marker = new kakao.maps.Marker({ map: map, position: coords });
	        var infowindow = new kakao.maps.InfoWindow({
	          content: '<div style="width:150px;text-align:center;padding:6px 0;">' + storeName + '</div>'
	        });
	        infowindow.open(map, marker);

	        map.setCenter(coords);
	      } else {
	        console.error('주소 변환 실패:', status, result);
	      }
	    });
	  });
	});