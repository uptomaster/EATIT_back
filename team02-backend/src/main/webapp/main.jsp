<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css">
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">

<!-- 헤더 js -->
<script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>

<script>
	let headerPath = './../../header.jsp';
	let footerPath = './../../footer.jsp';
</script>
<title>밥세권</title>
</head>

<body>
	<!-------------------- 헤더 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<!-- <header id="header"></header> -->

	<!-------------------- 메인 ------------------------>
	<main id="main">
		<!-- 메인 배너 영역 -->
		<div id="main_banner">
			<div class="main_banner_middle">
				<ul class="main_slide_box">
					<li class="main_slide_img"><img
						src="./assets/img/banner_ex.jpg" alt=""></li>
					<li class="main_slide_img"><img
						src="./assets/img/banner_ex.jpg" alt=""></li>
					<li class="main_slide_img"><img
						src="./assets/img/banner_ex.jpg" alt=""></li>
				</ul>
				<div class="main_banner_prev">
					<a href="#"><img src="./assets/img/main_banner_prev.png" alt=""></a>
				</div>
				<div class="main_banner_next">
					<a href="#"><img src="./assets/img/main_banner_next.png" alt=""></a>
				</div>
			</div>
		</div>


		<!-- 메인 컨텐츠 영역 -->
		<div id="main_wrapper">

			<!-- 메인 컨텐츠 > 음식점 판매 영역 -->
			<div id="main_content_buy_food">
				<h3>거리순👀</h3>
				<!-- 가게 이미지 및 정보 -->
				<div class="main_food_buy">
					<!-- 화살표 이동 버튼 -->
					<div class="main_content_prev">
						<a href="#"><img src="./assets/img/main_banner_prev.png"
							alt=""></a>
					</div>

<%-- 					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/bibim.jpg"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">푸드카페 김밥천국 역삼역점</p>
								<p class="main_menu_name">비빔밥</p>
								<p class="main_open_time">영업시간 09:00~21:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article> --%>
					<article class="main_food_buy_article">
						<!-- 상품이미지(임시) -->
						<c:choose>
							<a
								href="${pageContext.request.contextPath}/orders/storeDetail.or">
								<c:when test="${not empty memberList}">
									<c:forEach var="store" items="${store}">
										<img src="./assets/img/budaeJjigae.png" alt="상품이미지 설명 추가하기">
										<!-- 가게정보 -->
										<div class="main_store_info">
											<div class="main_store_name">
												<c:out value="${store.getStoreName()}" />
											</div>
											<div class="main_menu_name">
												<c:out value="${store.getItemName()}" />
											</div>
											<div class="main_open_time">
												영업시간 :
												<c:out value="${store.getStoreOpenTime()}" />
												<c:out value="${store.getStoreCloseTime()}" />
											</div>
											<div class="main_price">
												<c:out value="${store.getItemPrice()}" />
											</div>
										</div>
									</c:forEach>
								</c:when>
							</a>
						</c:choose>
					</article>
					<%-- <article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/dakdoritang.png"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">고갯마루</p>
								<p class="main_menu_name">닭도리탕(소)</p>
								<p class="main_open_time">영업시간 11:00~21:30</p>
								<p class="main_price">17,000원</p>
							</div>
						</a>
					</article>
					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/doenjangJjigae.png"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">담뿍화로된장찌개 역삼점</p>
								<p class="main_menu_name">화로 된장찌개</p>
								<p class="main_open_time">영업시간 11:00~22:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article>
					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/aeomuk.jpg"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">담뿍화로된장찌개 역삼점</p>
								<p class="main_menu_name">화로 된장찌개</p>
								<p class="main_open_time">영업시간 11:00~22:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article>
					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/chicken.jpg"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">담뿍화로된장찌개 역삼점</p>
								<p class="main_menu_name">화로 된장찌개</p>
								<p class="main_open_time">영업시간 11:00~22:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article>
					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/cheesegimbab.jpg"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">담뿍화로된장찌개 역삼점</p>
								<p class="main_menu_name">화로 된장찌개</p>
								<p class="main_open_time">영업시간 11:00~22:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article>
					<article class="main_food_buy_article">
						<a href="${pageContext.request.contextPath}/orders/storeDetail.or">
							<!-- 상품이미지(임시) --> <img src="./assets/img/normal_gimbab.jpg"
							alt="상품이미지 설명 추가하기"> <!-- 가게정보 -->
							<div class="main_store_info">
								<p class="main_store_name">담뿍화로된장찌개 역삼점</p>
								<p class="main_menu_name">화로 된장찌개</p>
								<p class="main_open_time">영업시간 11:00~22:00</p>
								<p class="main_price">5,000원</p>
							</div>
						</a>
					</article>
 --%>

					<!-- 화살표 이동버튼 -->
					<div class="main_content_next">
						<a href="#"><img src="./assets/img/main_banner_next.png"
							alt=""></a>
					</div>
				</div>
				<!-- 더 보러가기 버튼 -->
				<div class="main_food_buy_btn">
					<a href="${pageContext.request.contextPath}/orders/storeList.or">더
						보러가기</a>
				</div>
			</div>

			<!-- 메인 컨텐츠 > 재료판매, 레시피공유 영역 -->
			<div id="main_content_buy_ingr">
				<div class="main_ingredient_buy">
					<h3>재료 구매🥕</h3>
					<a
						href="${pageContext.request.contextPath}/orders/ingredientList.or">더보기
						></a>
					<div class="main_ingredient_store">
						<!-- 재료사진 -->
						<article class="main_ingredient_img">
							<a
								href="${pageContext.request.contextPath}/orders/ingredientDetail.or">
								<img src="./assets/img/carrot.jpg" alt="상품이미지 가게 정보 추가하기">
							</a>
						</article>
						<article class="main_ingredient_img">
							<a
								href="${pageContext.request.contextPath}/orders/ingredientDetail.or">
								<img src="./assets/img/baechu.jpg" alt="상품이미지 가게 정보 추가하기">
							</a>
						</article>
						<article class="main_ingredient_img">
							<a
								href="${pageContext.request.contextPath}/orders/ingredientDetail.or">
								<img src="./assets/img/cucumber.jpg" alt="상품이미지 가게 정보 추가하기">
							</a>
						</article>
						<article class="main_ingredient_img">
							<a
								href="${pageContext.request.contextPath}/orders/ingredientDetail.or">
								<img src="./assets/img/garlic.jpg" alt="상품이미지 가게 정보 추가하기">
							</a>
						</article>
					</div>
				</div>
				<div class="main_recipe">
					<h3>레시피 공유📃</h3>
					<a
						href="${pageContext.request.contextPath}/community/recipeListOk.co">더보기
						></a>
					<div class="main_recipe_commu">
						<div class="main_recipe_header" role="rowgroup">
							<div class="main_recipe_col_tag" role="columnheader">태그</div>
							<div class="main_recipe_col_title" role="columnheader">제목</div>
							<div class="main_recipe_col_date" role="columnheader">등록일</div>
							<div class="main_recipe_col_views" role="columnheader">조회</div>
						</div>

						<div class="main_recipe_list" role="rowgroup">
							<div class="main_recipe_col_tag" role="columnheader">한식</div>
							<div class="main_recipe_col_title" role="columnheader">
								<a
									href="${pageContext.request.contextPath}/community/recipeList.co">김치찌개
									황금레시피 공개</a>
							</div>
							<div class="main_recipe_col_date" role="columnheader">25-03-10</div>
							<div class="main_recipe_col_views" role="columnheader">230</div>
						</div>
						<div class="main_recipe_list" role="rowgroup">
							<div class="main_recipe_col_tag" role="columnheader">양식</div>
							<div class="main_recipe_col_title" role="columnheader">
								<a
									href="${pageContext.request.contextPath}/community/recipeList.co">크림
									파스타 완벽 가이드</a>
							</div>
							<div class="main_recipe_col_date" role="columnheader">25-05-20</div>
							<div class="main_recipe_col_views" role="columnheader">180</div>
						</div>
						<div class="main_recipe_list" role="rowgroup">
							<div class="main_recipe_col_tag" role="columnheader">중식</div>
							<div class="main_recipe_col_title" role="columnheader">
								<a
									href="${pageContext.request.contextPath}/community/recipeList.co">짜장면
									집에서 쉽게 만들기</a>
							</div>
							<div class="main_recipe_col_date" role="columnheader">25-06-01</div>
							<div class="main_recipe_col_views" role="columnheader">120</div>
						</div>
						<div class="main_recipe_list" role="rowgroup">
							<div class="main_recipe_col_tag" role="columnheader">일식</div>
							<div class="main_recipe_col_title" role="columnheader">
								<a
									href="${pageContext.request.contextPath}/community/recipeList.co">초밥
									만들기 초보 가이드</a>
							</div>
							<div class="main_recipe_col_date" role="columnheader">25-07-15</div>
							<div class="main_recipe_col_views" role="columnheader">95</div>
						</div>
					</div>

				</div>
			</div>
		</div>

		<!-- 고정 아이콘 영역 -->
		<div class="icon_fixed_nav">
			<a href="#" class="icon_btn" title="나무등급"> <svg
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
          <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
          <path fill="#202020"
						d="M320 32C327 32 333.7 35.1 338.3 40.5L474.3 200.5C480.4 207.6 481.7 217.6 477.8 226.1C473.9 234.6 465.4 240 456 240L431.1 240L506.3 328.5C512.4 335.6 513.7 345.6 509.8 354.1C505.9 362.6 497.4 368 488 368L449.5 368L538.3 472.5C544.4 479.6 545.7 489.6 541.8 498.1C537.9 506.6 529.4 512 520 512L352 512L352 576C352 593.7 337.7 608 320 608C302.3 608 288 593.7 288 576L288 512L120 512C110.6 512 102.1 506.6 98.2 498.1C94.3 489.6 95.6 479.6 101.7 472.5L190.5 368L152 368C142.6 368 134.1 362.6 130.2 354.1C126.3 345.6 127.6 335.6 133.7 328.5L208.9 240L184 240C174.6 240 166.1 234.6 162.2 226.1C158.3 217.6 159.6 207.6 165.7 200.5L301.7 40.5C306.3 35.1 313 32 320 32z" />
        </svg>
			</a> <a href="#" class="icon_btn" title="위치"> <svg
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
          <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
          <path fill="#202020"
						d="M128 252.6C128 148.4 214 64 320 64C426 64 512 148.4 512 252.6C512 371.9 391.8 514.9 341.6 569.4C329.8 582.2 310.1 582.2 298.3 569.4C248.1 514.9 127.9 371.9 127.9 252.6zM320 320C355.3 320 384 291.3 384 256C384 220.7 355.3 192 320 192C284.7 192 256 220.7 256 256C256 291.3 284.7 320 320 320z" />
        </svg>
			</a> <a href="${pageContext.request.contextPath}/orders/wishList.or"
				class="icon_btn" title="찜"> <svg
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
          <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
          <path fill="#202020"
						d="M305 151.1L320 171.8L335 151.1C360 116.5 400.2 96 442.9 96C516.4 96 576 155.6 576 229.1L576 231.7C576 343.9 436.1 474.2 363.1 529.9C350.7 539.3 335.5 544 320 544C304.5 544 289.2 539.4 276.9 529.9C203.9 474.2 64 343.9 64 231.7L64 229.1C64 155.6 123.6 96 197.1 96C239.8 96 280 116.5 305 151.1z" />
        </svg>
			</a> <a href="${pageContext.request.contextPath}/cartList/view.cl"
				class="icon_btn" title="장바구니"> <svg
					xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
          <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
          <path fill="#202020"
						d="M320 64C326.6 64 332.9 66.7 337.4 71.5L481.4 223.5L481.9 224L560 224C577.7 224 592 238.3 592 256C592 270.5 582.4 282.7 569.2 286.7L523.1 493.9C516.6 523.2 490.6 544 460.6 544L179.3 544C149.3 544 123.3 523.2 116.8 493.9L70.8 286.7C57.6 282.8 48 270.5 48 256C48 238.3 62.3 224 80 224L158.1 224L158.6 223.5L302.6 71.5C307.1 66.7 313.4 64 320 64zM320 122.9L224.2 224L415.8 224L320 122.9zM240 328C240 314.7 229.3 304 216 304C202.7 304 192 314.7 192 328L192 440C192 453.3 202.7 464 216 464C229.3 464 240 453.3 240 440L240 328zM320 304C306.7 304 296 314.7 296 328L296 440C296 453.3 306.7 464 320 464C333.3 464 344 453.3 344 440L344 328C344 314.7 333.3 304 320 304zM448 328C448 314.7 437.3 304 424 304C410.7 304 400 314.7 400 328L400 440C400 453.3 410.7 464 424 464C437.3 464 448 453.3 448 440L448 328z" />
        </svg>
			</a>
		</div>

	</main>

	<!-------------------- 푸터 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>