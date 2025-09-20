<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>밥세권 - 내 찜 목록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/orders/myFavorite.css">
<script defer
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>
<body>

	<!-- 헤더 -->
	<jsp:include page="${pageContext.request.contextPath}/header.jsp">
		<jsp:param name="active" value="wishlist" />
	</jsp:include>

	<!-- 본문 -->
	<main id="myFavorite" class="wrap">
		<section class="favorite_store_list">
			<h2>내 찜한 가게</h2>

			<div class="favorite_area">
				<c:choose>
					<c:when test="${not empty favorites}">
						<c:forEach var="fav" items="${favorites}">
							<article class="favorite_article">
								<!-- 가게 대표 이미지 -->
								<div class="favorite_img_wrapper">
									<c:choose>
										<c:when test="${not empty fav.storeImageSystemName}">
											<img
												src="${pageContext.request.contextPath}/upload/${fav.storeImageSystemName}"
												alt="${fav.businessName}">
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/assets/img/default_store.png"
												alt="기본 이미지">
										</c:otherwise>
									</c:choose>
								</div>


								<!-- 가게 정보 -->
								<div class="favorite_store_info">
									<p class="favorite_store_name">${fav.businessName}</p>
									<p class="favorite_rating">
										⭐ ${fav.avgRating} / 5 <span class="review-count">(${fav.reviewCount}건)</span>
									</p>
									<p class="favorite_open_time">⏰ ${fav.openTime} ~
										${fav.closeTime}</p>
									<p class="favorite_menu_count">📋 메뉴 수 : ${fav.menuCount}</p>
								</div>

								<!-- 찜 해제 버튼 -->
								<div class="favorite_remove_form">
									<form
										action="${pageContext.request.contextPath}/orders/favoriteToggle.or"
										method="post">
										<input type="hidden" name="storeNumber"
											value="${fav.businessNumber}">
										<button type="submit" class="remove_btn">
											<i class="fa-solid fa-heart"></i> 찜 해제
										</button>
									</form>
								</div>
							</article>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="no-favorites">찜한 가게가 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</div>

			<!-- ✅ 페이지네이션 -->
			<div class="favorite_pagination">
				<c:forEach var="i" begin="1" end="${maxPage}">
					<a
						href="${pageContext.request.contextPath}/orders/myFavorite.or?page=${i}"
						class="${i == page ? 'active' : ''}">${i}</a>
				</c:forEach>
			</div>
		</section>
	</main>

	<!-- 찜 완료/해제 메시지 -->
	<c:if test="${not empty sessionScope.favMessage}">
		<script>
			alert("${sessionScope.favMessage}");
		</script>
		<c:remove var="favMessage" scope="session" />
	</c:if>

	<!-- 푸터 -->
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>

</body>
</html>
