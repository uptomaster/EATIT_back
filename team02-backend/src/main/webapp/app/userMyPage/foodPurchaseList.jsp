<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/foodPurchaseList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/foodPurchaseList.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>
<body>
  <!-- 헤더 -->
  <header id="header"></header>
  <!-- 메인 -->
  <main>
  <!-- 사이드바 (마이페이지 메뉴) -->
  <div class="foodpurchaselist_my_page_list">
    <!-- 마이페이지 제목 -->
    <div class="foodpurchaselist_my_page">마이 페이지</div>

    <!-- 마이페이지 좌측 메뉴 리스트 -->
    <ul class="foodpurchaselist_side_bar">
      <!-- 현재 페이지는 음식 구매 내역 -->
      <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
      <li class="foodpurchaselist_main">
        <a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a>
      </li>
      <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
      <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
      <li><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
      <li><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
    </ul>
  </div>

  <!-- 콘텐츠 본문 영역 (중앙 1100px) -->
  <div class="foodpurchaselist_page">

    <!-- 페이지 제목 -->
    <h2 class="food_purchase_list">음식 구매 내역</h2>

    <!-- 테이블 형식 리스트 헤더 (구매 내역 항목 제목) -->
    <div>
      <div class="food_purchase_list_top">
        <div class="food_purchase_list_date">구매날짜</div>
        <div class="food_purchase_list_img">이미지</div>
        <div class="food_purchase_list_restaurant_name">가게명</div>
        <div class="food_purchase_list_menu_info">상품정보</div>
        <div class="food_purchase_list_how_many">수량</div>
        <div class="food_purchase_list_price">금액</div>
        <div class="food_purchase_list_review">리뷰</div>
      </div>

      <!-- 실제 구매 내역 1건 -->
      <div class="foodpurchase_page_list">
        <div class="food_purchase_date_list">2025.08.03</div>
        <div class="food_purchase_img_list">
          <img class="meal_img" src="./../../assets/img/나무.png" alt="">
        </div>
        <div class="food_purchase_restaurant_name_list">김사부 마라탕</div>
        <div class="food_purchase_menu_info_list">마라탕</div>
        <div class="food_purchase_how_many_list">1</div>
        <div class="food_purchase_price_list">16000원</div>
        <div class="food_purchase_review_list">
          <a href="./../userMyPage/writeReview.html" class="food_purchase_review_meal">리뷰</a>
        </div>
      </div>

      <!-- 실제 구매 내역 2건 -->
      <div class="foodpurchase_page_list">
        <div class="food_purchase_date_list">2025.08.04</div>
        <div class="food_purchase_img_list">
          <img class="meal_img" src="./../../assets/img/새싹.png" alt="">
        </div>
        <div class="food_purchase_restaurant_name_list">김사부 감자탕</div>
        <div class="food_purchase_menu_info_list">감자탕</div>
        <div class="food_purchase_how_many_list">1</div>
        <div class="food_purchase_price_list">26000원</div>
        <div class="food_purchase_review_list">
          <a href="./../userMyPage/writeReview.html" class="food_purchase_review_meal">리뷰</a>
        </div>
      </div>
    </div>

    <!-- 페이지네이션 (하단 페이지 넘기기) -->
    <div class="foodpurchaselist_pagination">
      <a href="#" class="foodpurchaselist_page_active">1</a>
      <a href="#" class="foodpurchaselist_page">2</a>
      <a href="#" class="foodpurchaselist_page">3</a>
      <a href="#" class="foodpurchaselist_page">4</a>
      <a href="#" class="foodpurchaselist_page">5</a>
    </div>
  </div>
</main>
<!-- 푸터 -->
  <footer id="footer"></footer>
</body>
</html>