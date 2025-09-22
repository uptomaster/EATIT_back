<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>밥세권 - 가게 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/orders/storeDetail.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"
	crossorigin="anonymous"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/orders/storeDetail.js"></script>

<style>
/* 지도 스타일 */
.buy_store_map {
	width: 100%;
	height: 350px;
	border-radius: 12px;
	margin-top: 20px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
}
</style>
</head>
<body>

	<!-- 헤더 -->
	<jsp:include page="${pageContext.request.contextPath}/header.jsp">
		<jsp:param name="active" value="purchase" />
	</jsp:include>

	<!-- 가게 상세 -->
	<main id="buy_store_detail">
		<div class="wrap">
			<!-- 왼쪽 -->
			<div class="buy_left_area">
				<!-- 가게 정보 -->
				<div class="buy_store_info">
					<div class="store_image_wrapper">
						<c:choose>
							<c:when test="${not empty item.storeImageSystemName}">
								<img
									src="${pageContext.request.contextPath}/upload/${item.storeImageSystemName}"
									alt="${item.storeName}">
							</c:when>
							<c:otherwise>
								<img
									src="${pageContext.request.contextPath}/assets/img/food1.jpg"
									alt="기본 이미지">
							</c:otherwise>
						</c:choose>

						<!-- ✅ 찜 버튼 -->
						<form
							action="${pageContext.request.contextPath}/orders/favoriteToggle.or"
							method="post" style="display: inline;">
							<input type="hidden" name="storeNumber"
								value="${item.businessNumber}" />
							<button type="submit"
								class="favorite-btn ${isFavorited ? 'favorited' : 'not-favorited'}">
								<i
									class="${isFavorited ? 'fa-solid' : 'fa-regular'} fa-heart heart-icon"></i>
								<span> <c:choose>
										<c:when test="${isFavorited}">찜 해제</c:when>
										<c:otherwise>찜하기</c:otherwise>
									</c:choose>
								</span>
							</button>
						</form>
					</div>

					<div class="buy_store_info_detail">
						<p class="buy_store_name">${item.storeName}</p>
						<p class="buy_store_address">주소 : ${item.storeAddress}</p>
						<p class="buy_store_tel">전화번호 : ${item.storeTel}</p>
						<!-- 리뷰탭 이동 -->
						<div class="review-tab-link">
							<a
								href="${pageContext.request.contextPath}/orders/storeReview.or?businessNumber=${item.businessNumber}">
								리뷰 보기 → </a>
						</div>
					</div>
				</div>

				<!-- 탭 -->
				<div class="buy_food">
					<ul class="buy_food_menu_choice">
						<li><a class="${param.tab eq 'FOOD' ? 'active' : ''}"
							href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&tab=FOOD">음식</a></li>
						<li><a class="${param.tab eq 'INGREDIENT' ? 'active' : ''}"
							href="${pageContext.request.contextPath}/orders/ingredientDetail.or?itemNumber=${item.itemNumber}&tab=INGREDIENT">재료</a></li>
					</ul>

					<!-- 상품 목록 -->
					<div id="buy_food_section">
						<c:choose>
							<c:when test="${not empty itemList}">
								<c:forEach var="p" items="${itemList}">
									<div class="buy_food_menu_list">
										<div class="food_img_wrapper">
											<c:choose>
												<c:when test="${not empty p.itemImageSystemName}">
													<img
														src="${pageContext.request.contextPath}/upload/${p.itemImageSystemName}"
														alt="${p.itemName}">
												</c:when>
												<c:otherwise>
													<img
														src="${pageContext.request.contextPath}/assets/img/food2.jpg"
														alt="기본 이미지">
												</c:otherwise>
											</c:choose>

											<!-- 소비기한 뱃지 -->
											<c:if test="${not empty p.itemExpireDate}">
												<c:set var="today"
													value="<%=new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(new java.util.Date())%>" />
												<fmt:parseDate var="expireDate" value="${p.itemExpireDate}"
													pattern="yyyy-MM-dd" />
												<fmt:parseDate var="todayDate" value="${today}"
													pattern="yyyy-MM-dd" />
												<c:set var="diffDays"
													value="${(expireDate.time - todayDate.time) / (1000*60*60*24)}" />
												<c:choose>
													<c:when test="${diffDays le 3}">
														<span class="badge urgent">마감임박</span>
													</c:when>
													<c:when test="${diffDays le 7}">
														<span class="badge sale">할인추천</span>
													</c:when>
													<c:otherwise>
														<span class="badge fresh">신선</span>
													</c:otherwise>
												</c:choose>
											</c:if>
										</div>

										<div class="buy_each_container">
											<p class="buy_food_stock">재고 : ${p.itemQuantity}개</p>
											<p class="buy_food_menu_name">${p.itemName}</p>
											<div class="buy_food_stock_choice">
												<a href="#" class="minus">-</a>
												<p class="count">1</p>
												<a href="#" class="plus">+</a>
												<form
													action="${pageContext.request.contextPath}/cartList/addItemOk.cl"
													method="post" style="display: inline;">
													<input type="hidden" name="itemNumber"
														value="${p.itemNumber}"> <input type="hidden"
														name="quantity" value="1" class="cartQuantity">
													<button type="submit" class="buy_add_cart_btn">장바구니</button>
												</form>
											</div>
											<div class="price_menu">
												가격 :
												<fmt:formatNumber value="${p.itemPrice}" type="number" />
												원
											</div>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<p class="no-items">판매중인 상품이 없습니다.</p>
							</c:otherwise>
						</c:choose>
					</div>

					<!-- 페이지네이션 -->
					<div class="pagination" id="pagination">
						<c:forEach var="i" begin="1" end="${maxPage}">
							<a
								href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&page=${i}&tab=${param.tab}"
								class="${i == page ? 'active' : ''}">${i}</a>
						</c:forEach>
					</div>
				</div>

				<!-- ✅ 사고보상 정책 -->
				<div class="buy_policy">
					<h3 class="buy_policy_title">사고보상 정책</h3>
					<div class="buy_policy_toggle">
						<div class="buy_policy_toggle_header">문의 접수</div>
						<div class="buy_policy_toggle_content">서비스 이용 중 문제가 발생하면
							고객센터 문의글 작성 또는 지정된 이메일을 통해 접수해야 합니다.</div>
						<div class="buy_policy_toggle_header">검토 후 안내</div>
						<div class="buy_policy_toggle_content">접수된 내용은 운영팀이 검토 후
							가맹점과 협의하여 개별적으로 안내드립니다.</div>
						<div class="buy_policy_toggle_header">보상 기준</div>
						<div class="buy_policy_toggle_content">보상 여부 및 처리 절차는 서비스 운영
							정책과 각 가맹점의 내부 기준을 따릅니다.</div>
					</div>
				</div>
			</div>

			<!-- 오른쪽 -->
			<div class="buy_map_area">
				<div class="buy_back_store_list">
					<a href="${pageContext.request.contextPath}/orders/storeList.or">목록으로
						돌아가기</a>
				</div>
				<div class="buy_origin">
					<ul class="buy_origin_menu">
						<li class="buy_origin_info"><a href="#" id="storeInfoBtn">가게정보</a></li>
						<li class="buy_origin_info"><a href="#" id="originInfoBtn">원산지</a></li>
					</ul>
					<div class="buy_origin_store_info">
						<ul class="buy_origin_content">
							<li>사업자번호: ${item.businessNumber}</li>
							<li>소비기한: ${item.itemExpireDate}</li>
						</ul>
					</div>
					<div class="origin_info_inactive">
						<ul class="buy_origin_content">
							<li>농산물: 국내산</li>
							<li>수산물: 원산지 별도 표시</li>
							<li>축산물: 국내산 한우</li>
						</ul>
					</div>
				</div>

				<!-- ✅ 지도 API -->
				<div class="buy_store_map" id="storeMap"></div>
			</div>
		</div>
	</main>

	<!-- 찜 완료/해제 메시지 -->
	<c:if test="${not empty sessionScope.favMessage}">
		<script>
            alert("${sessionScope.favMessage}");
        </script>
		<c:remove var="favMessage" scope="session" />
	</c:if>

	<!-- 카카오맵 API -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=여기에 넣으세요&libraries=services"></script>
	<script>
document.addEventListener("DOMContentLoaded", () => {
  var map = new kakao.maps.Map(document.getElementById('storeMap'), {
      center: new kakao.maps.LatLng(37.5665, 126.9780),
      level: 3
  });

  var geocoder = new kakao.maps.services.Geocoder();
  var address = "${fn:replace(item.storeAddress, '서울시', '서울특별시')}";

  geocoder.addressSearch(address, function(result, status) {
      if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // ✅ 커스텀 마커 이미지 (고양이 3D 핀)
          var imageSrc = "${pageContext.request.contextPath}/assets/img/pinmarker.png"; 
          var imageSize = new kakao.maps.Size(40, 40); 
          var imageOption = {offset: new kakao.maps.Point(20, 40)};
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          var marker = new kakao.maps.Marker({
              position: coords,
              image: markerImage,
              map: map
          });

          map.setCenter(coords);

          // ✅ 상호명 라벨 (CustomOverlay)
          var overlayContent = `
            <div style="
                background:#ff6347;
                color:#fff;
                padding:3px 8px;
                border-radius:6px;
                font-size:12px;
                white-space:nowrap;
                box-shadow:0 1px 4px rgba(0,0,0,0.3);">
              ${item.storeName}
            </div>
          `;

          var overlay = new kakao.maps.CustomOverlay({
              content: overlayContent,
              position: coords,
              yAnchor: 2.2 // 마커 위로 살짝 띄우기
          });

          overlay.setMap(map);
      } else {
          console.error("주소 변환 실패:", status, address);
      }
  });
});
</script>



	<!-- 푸터 -->
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
</body>
</html>
