<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>밥세권 - 재료 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/orders/storeDetail.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/orders/storeDetail.js"></script>
</head>
<body>

	<!-------------------- 헤더 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp">
		<jsp:param name="active" value="purchase" />
	</jsp:include>

	<!-- 샘플 이미지 12개 세팅 -->
	<c:set var="sampleImgs" value="/assets/img/food1.jpg,/assets/img/food2.jpg,/assets/img/food3.jpg,/assets/img/food4.jpg,/assets/img/food5.jpg,/assets/img/food6.jpg,/assets/img/food7.jpg,/assets/img/food8.jpg,/assets/img/food9.jpg,/assets/img/food10.jpg,/assets/img/food11.jpg,/assets/img/food12.jpg" />
	<c:set var="sampleArr" value="${fn:split(sampleImgs, ',')}" />
	<c:set var="sampleImg" value="${sampleArr[item.itemNumber % fn:length(sampleArr)]}" />
	<c:url value="${sampleImg}" var="dummyUrl"/>

	<!-- 재료 상세 -->
	<main id="buy_store_detail">
		<div class="wrap">

			<!-- 왼쪽 영역 -->
			<div class="buy_left_area">

				<!-- 상품 기본 정보 -->
				<div class="buy_store_info">
					<c:choose>
						<c:when test="${empty item.itemImageSystemName}">
							<img src="${dummyUrl}" alt="기본 이미지">
						</c:when>
						<c:otherwise>
							<c:url value="/upload/items/${item.itemImageSystemName}" var="imgUrl"/>
							<img src="${imgUrl}" alt="${item.itemName}">
						</c:otherwise>
					</c:choose>

					<div class="buy_store_info_detail">
						<p class="buy_store_name">${item.itemName}</p>
						<p class="buy_store_address">주소 : ${item.storeAddress}</p>
					</div>
				</div>

				<!-- 탭 -->
				<div class="buy_food">
					<ul class="buy_food_menu_choice">
						<li><a class="${param.tab eq 'FOOD' ? 'active' : ''}"
							href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&tab=FOOD">메뉴</a></li>
					</ul>

					<!-- 상품 출력 -->
					<div id="buy_food_section">
						<div class="buy_food_menu_list">
							<c:choose>
								<c:when test="${empty item.itemImageSystemName}">
									<img src="${dummyUrl}" alt="상품 이미지">
								</c:when>
								<c:otherwise>
									<c:url value="/upload/items/${item.itemImageSystemName}" var="imgUrl2"/>
									<img src="${imgUrl2}" alt="${item.itemName}">
								</c:otherwise>
							</c:choose>

							<div class="buy_each_container">
								<!-- 재고 + 가격 한 줄 -->
								<div class="stock_price_row">
									<p class="buy_food_stock">재고 : ${item.itemQuantity}개</p>
									<div class="price_menu">
										가격 :
										<fmt:formatNumber value="${item.itemPrice}" type="number" />
										원
									</div>
								</div>

								<!-- 상품명 -->
								<p class="buy_food_menu_name">${item.itemName}</p>

								<!-- 수량 선택 + 장바구니 버튼 -->
								<div class="buy_food_stock_choice">
									<a href="#" class="minus">-</a>
									<p class="count">1</p>
									<a href="#" class="plus">+</a>

									<form
										action="${pageContext.request.contextPath}/cartList/addItemOk.cl"
										method="post" style="display: inline;">
										<input type="hidden" name="itemNumber" value="${item.itemNumber}">
										<input type="hidden" name="quantity" value="1" class="cartQuantity">
										<button type="submit" class="buy_add_cart_btn">장바구니</button>
									</form>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>

			<!-- 오른쪽 영역 -->
			<div class="buy_map_area">
				<div class="buy_back_store_list">
					<a href="${pageContext.request.contextPath}/orders/ingredientList.or">목록으로 돌아가기</a>
				</div>

				<!-- 원산지 & 가게정보 -->
				<div class="buy_origin">
					<ul class="buy_origin_menu">
						<li class="buy_origin_info"><a href="#" id="storeInfoBtn">가게정보</a></li>
						<li class="buy_origin_info"><a href="#" id="originInfoBtn">원산지</a></li>
					</ul>

					<!-- 가게 정보 -->
					<div class="buy_origin_store_info">
						<ul class="buy_origin_content">
							<li>가게명: ${item.storeName}</li>
							<li>주소: ${item.storeAddress}</li>
							<li>전화번호: ${item.storeTel}</li>
							<li>소비기한: ${item.itemExpireDate}</li>
						</ul>
					</div>

					<!-- 원산지 정보 -->
					<div class="origin_info_inactive">
						<ul>
							<li>원산지: ${item.itemOrigin}</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- 사고보상정책 -->
		<div class="buy_policy_toggle_section">
			<p>구매하신 상품에 이상이 있으셨나요? 아래 절차를 따라 요청을 남겨주시면 빠르게 도와드리겠습니다.</p>
			<div class="buy_policy_toggle_header">사고보상정책 안내 보기 ▼</div>
			<div class="buy_policy_toggle_content">
				<p>1. 수령 즉시 상태 확인 및 사진 촬영</p>
				<p>2. 고객센터에서 접수 (1일 이내)</p>
			</div>
		</div>
	</main>

	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

</body>
</html>
