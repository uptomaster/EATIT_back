<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/todaySaleList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/todaySaleList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="today_sell_menu">
      <div class="today_sell_menu_title">마이 페이지</div>
      <ul class="today_sell_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li class="store_info_menu_list_current"><a href="/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

    <div class="today_sell_page"> <!-- 1100 영역-->
      <h2 class="today_sell_title"> 오늘 판매 내역</h2> <!-- 페이지 제목 -->
      <!-- 총/오늘 판매내역 선택  -->
      <ul class="sale_history_categaory_container">
        <li class="sale_history_today"><a href="./todaySaleList.html">오늘 판매 내역</a></li>
        <li class="sale_history_total"><a href="./salesHistoryList.html">총 판매내역</a></li>
      </ul>
      <div class="today_sell_container">
        <!-- 총 판매 건수 -->
        <div class="today_sell_summary">
          총 판매건수 : <span class="today_sell_count">2</span>
          총 판매 금액(원) : <span class="today_sell_total">24,800</span>
        </div>
        <!-- 첫번쨰 주문 -->
        <div class="today_sell_order_wapper">
          <!-- 구매자 정보 -->
          <div class="today_sell_buyer_info">
            <span class="today_sell_buyer_id">dlskagur</span>
            (<span class="today_sell_order_count">2건</span>,
            총 주문금액 <span class="today_sell_total_price">14,900원</span>) /
            <span class="today_sell_order_date">2025-08-03</span> /
            <span class="today_sell_phone">010-1234-5678</span>
          </div>

          <!-- 첫 번째 상품 -->
          <div class="today_sell_product">
            <div class="today_sell_product_image">
              <img src="brownie.jpg" alt="다노 프로틴 브라우니">
            </div>
            <div class="today_sell_product_info">
              <div class="today_sell_product_name">다노 프로틴 브라우니 1BOX(5개입)</div>
              <div class="today_sell_product_qty">1개</div>
              <div class="today_sell_product_exp">소비기한</div>
              <div class="today_sell_product_price">9,900</div>
            </div>
          </div> <!-- //첫번째 상품 -->


          <!-- 두 번째 상품 -->
          <div class="today_sell_product">
            <div class="today_sell_product_image">
              <img src="dosirak.jpg" alt="다노한끼 한식도시락">
            </div>
            <div class="today_sell_product_info">
              <div class="today_sell_product_name">다노한끼 한식도시락(2팩)</div>
              <div class="today_sell_product_qty">1개</div>
              <div class="today_sell_product_exp">소비기한 : 가공품별 기재</div>
              <div class="today_sell_product_price">5,000</div>
            </div>
          </div> <!-- //두번쨰 상품 -->
        </div> <!-- //첫번째 주문 -->

        <!-- 2 번쨰 주문 -->
        <div class="today_sell_order_wapper">

          <!-- 구매자 정보 -->
          <div class="today_sell_buyer_info">
            <span class="today_sell_buyer_id">dlskagur</span>
            (<span class="today_sell_order_count">2건</span>,
            총 주문금액 <span class="today_sell_total_price">14,900원</span>) /
            <span class="today_sell_order_date">2025-08-03</span> /
            <span class="today_sell_phone">010-1234-5678</span>
          </div> <!-- //구매자 정보 -->

          <!-- 첫 번째 상품 -->
          <div class="today_sell_product">
            <div class="today_sell_product_image">
              <img src="brownie.jpg" alt="다노 프로틴 브라우니">
            </div>
            <div class="today_sell_product_info">
              <div class="today_sell_product_name">다노 프로틴 브라우니 1BOX(5개입)</div>
              <div class="today_sell_product_qty">1개</div>
              <div class="today_sell_product_exp">소비기한</div>
              <div class="today_sell_product_price">9,900</div>
            </div>
          </div> <!-- //첫번째 상품 -->
        </div> <!-- //두번째 주문 -->


        <div class="today_sell_pagination">
          <a href="#" class="today_sell_page_active">1</a>
          <a href="#" class="today_sell_page">2</a>
          <a href="#" class="today_sell_page">3</a>
          <a href="#" class="today_sell_page">4</a>
          <a href="#" class="today_sell_page">5</a>
        </div>
      </div> <!-- 컨텐츠 영역 끝 -->
    </div> <!-- 1100px 영역 끝 -->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>