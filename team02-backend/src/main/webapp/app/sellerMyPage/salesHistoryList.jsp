<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/slaeHistoryList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/salesHistoryList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="sale_history_menu">
      <div class="sale_history_menu_title">마이 페이지</div>
      <ul class="sale_history_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

    <!-- 1100px 영영ㄱ -->
    <div class="sale_history_page">
      <!-- 페이지 네목 -->
      <h2 class="sale_history_title">판매 내역</h2>
      <!-- 총/오늘 판매내역 선택  -->
      <ul class="sale_history_categaory_container">
        <li class="sale_history_today"><a href="./todaySaleList.html">오늘 판매 내역</a></li>
        <li class="sale_history_total"><a href="./salesHistoryList.html">총 판매내역</a></li>
      </ul>
      <!-- 일자 검색을 할 수 있을까요.. 아니요 자문자답-->
      <div>
        <div class="sale_history_list_top">
          <div class="sale_history_sale_num_top">전표번호</div>
          <div class="sale_history_sale_date_top">판매일자</div>
          <div class="sale_history_buyer_id_top">구매자아이디</div>
          <div class="sale_history_idx_top">품목번호</div>
          <div class="sale_history_category_top">분류</div>
          <div class="sale_history_menu_name_top">메뉴명</div>
          <div class="sale_history_quantity_top">판매개수</div>
          <div class="sale_history_price_top">금액</div>
        </div>
        <div class="sale_history_sales_list">
          <div class="sale_history_sale_num">001</div>
          <div class="sale_history_sale_date">2025.08.01.15:36</div>
          <div class="sale_history_buyer_id">dltjwls</div>
          <div class="sale_history_idx">01</div>
          <div class="sale_history_category">음식</div>
          <div class="sale_history_menu_name">파스타</div>
          <div class="sale_history_quantity">1</div>
          <div class="sale_history_price">7,000</div>
        </div>
        <div class="sale_history_sales_list">
          <div class="sale_history_sale_num">002</div>
          <div class="sale_history_sale_date">2025.08.10 17:20</div>
          <div class="sale_history_buyer_id">wlstj</div>
          <div class="sale_history_idx">01</div>
          <div class="sale_history_category">재료</div>
          <div class="sale_history_menu_name">양파한묶음</div>
          <div class="sale_history_quantity">2</div>
          <div class="sale_history_price">4,000</div>
        </div>
        <div class="sale_history_sales_list">
          <div class="sale_history_sale_num">002</div>
          <div class="sale_history_sale_date">2025.08.10 17:20</div>
          <div class="sale_history_buyer_id">wlstj</div>
          <div class="sale_history_idx">02</div>
          <div class="sale_history_category">재료</div>
          <div class="sale_history_menu_name">양송이버섯</div>
          <div class="sale_history_quantity">2</div>
          <div class="sale_history_price">1,000</div>
        </div>
      </div>
      <!-- 페이지 네이션 -->
      <div class="sale_history_pagination">
        <a href="#" class="sale_history_page_active">1</a>
        <a href="#" class="sale_history_page">2</a>
        <a href="#" class="sale_history_page">3</a>
        <a href="#" class="sale_history_page">4</a>
        <a href="#" class="sale_history_page">5</a>
      </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>