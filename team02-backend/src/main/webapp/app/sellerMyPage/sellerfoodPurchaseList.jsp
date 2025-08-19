<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/sellerfoodPurchaseList.css">
  <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="sellerfoodPurchase_my_page_list">
      <div class="sellerfoodPurchase_my_page">마이 페이지</div>
      <ul class="sellerfoodPurchase_side_bar">
        <li><a href="./../sellerMyPage/editSellerInfo.html">내 정보 수정</a></li>
        <li class="sellerfoodPurchase_main"><a href="./../sellerMyPage/sellerfoodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../sellerMyPage/selleringredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../sellerMyPage/sellerMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyReviewsList.html">내 리뷰 관리</a></li>
        <li><a href="./../sellerMyPage/storeInfo.html">사업장 관리</a></li>
        <li><a href="./../sellerMyPage/todaySaleList.html">판매 내역</a></li>
      </ul>
    </div>
    <!-- 메인 페이지 -->
    <div class="sellerfoodPurchase_page">
      <h2 class="sellerfoodPurchase_list">음식 구매 내역</h2>
      <div>
        <div class="sellerfoodPurchase_list_top">
          <div class="sellerfoodPurchase_list_date">구매날짜</div>
          <div class="sellerfoodPurchase_list_img">이미지</div>
          <div class="sellerfoodPurchase_list_restaurant_name">가게명</div>
          <div class="sellerfoodPurchase_list_menu_info">상품정보</div>
          <div class="sellerfoodPurchase_list_how_many">수량</div>
          <div class="sellerfoodPurchase_list_price">금액</div>
          <div class="sellerfoodPurchase_list_review">리뷰</div>
        </div>
        <div class="sellerfoodPurchase_page_list">
          <div class="sellerfoodPurchase_date_list">2025.08.03</div>
          <div class="sellerfoodPurchase_img_list"><img class="meal_img" src="./../../assets/img/나무.png" alt=""></div>
          <div class="sellerfoodPurchase_restaurant_name_list">김사부 마라탕</div>
          <div class="sellerfoodPurchase_menu_info_list">마라탕</div>
          <div class="sellerfoodPurchase_how_many_list">1</div>
          <div class="sellerfoodPurchase_price_list">16000원</div>
          <div class="sellerfoodPurchase_review_list"><a href="./../sellerMyPage/sellerwriteReview.html"
              class="sellerfoodPurchase_review_meal">리뷰</a></div>
        </div>
        <div class="sellerfoodPurchase_page_list">
          <div class="sellerfoodPurchase_date_list">2025.08.04</div>
          <div class="sellerfoodPurchase_img_list"><img class="meal_img" src="./../../assets/img/새싹.png" alt=""></div>
          <div class="sellerfoodPurchase_restaurant_name_list">김사부 감자탕</div>
          <div class="sellerfoodPurchase_menu_info_list">감자탕</div>
          <div class="sellerfoodPurchase_how_many_list">1</div>
          <div class="sellerfoodPurchase_price_list">26000원</div>
          <div class="sellerfoodPurchase_review_list"><a href="./../sellerMyPage/sellerwriteReview.html"
              class="sellerfoodPurchase_review_meal">리뷰</a></div>
        </div>
      </div>
      <div class="sellerfoodPurchase_pagination">
        <a href="#" class="sellerfoodPurchase_page_active">1</a>
        <a href="#" class="sellerfoodPurchase_page">2</a>
        <a href="#" class="sellerfoodPurchase_page">3</a>
        <a href="#" class="sellerfoodPurchase_page">4</a>
        <a href="#" class="sellerfoodPurchase_page">5</a>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>
</main>
<footer id="footer"></footer>
</body>

</html>