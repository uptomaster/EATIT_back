<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/selleringredientPurchaseList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_ingredient_purchase_menu">
      <div class="seller_ingredient_purchase_menu_title">마이 페이지</div>
      <ul class="seller_ingredient_purchase_menu_list">
				<li><a href="/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li class="store_info_menu_list_current"><a href="/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>
    <div class="seller_ingredient_purchase_page">
      <h2 class="seller_ingredient_purchase_list">재료 구매 내역</h2>
      <div>
        <div class="seller_ingredient_purchaselist_top">
          <div class="seller_ingredient_purchaselist_date">구매날짜</div>
          <div class="seller_ingredient_purchaselist_img">이미지</div>
          <div class="seller_ingredient_purchaselist_restaurant_name">가게명</div>
          <div class="seller_ingredient_purchaselist_menu_info">상품정보</div>
          <div class="seller_ingredient_purchaselist_how_many">수량</div>
          <div class="seller_ingredient_purchaselist_price">금액</div>
          <div class="seller_ingredient_purchaselist_review">리뷰</div>
        </div>
        <div class="seller_ingredient_purchase_page_list">
          <div class="seller_ingredient_purchase_date_list">2025.08.03</div>
          <div class="seller_ingredient_purchase_img_list"><img class="seller_ingredient_purchase_img"
              src="./../../assets/img/carrot.jpg" alt=""></div>
          <div class="seller_ingredient_purchase_restaurant_name_list">오늘 카레 어떄</div>
          <div class="seller_ingredient_purchase_menu_info_list">당근</div>
          <div class="seller_ingredient_purchase_how_many_list">4</div>
          <div class="seller_ingredient_purchase_price_list">16000원</div>
          <div class="seller_ingredient_purchase_review_list"><a href="./../sellerMyPage/sellerwriteReview.html"
              class="seller_ingredient_purchase_review_meal">리뷰</a></div>
        </div>
        <div class="seller_ingredient_purchase_page_list">
          <div class="seller_ingredient_purchase_date_list">2025.08.04</div>
          <div class="seller_ingredient_purchase_img_list"><img class="seller_ingredient_purchase_img"
              src="./../../assets/img/gimchiJjigae.jpg" alt=""></div>
          <div class="seller_ingredient_purchase_restaurant_name_list">장충동 왕족발 보쌈</div>
          <div class="seller_ingredient_purchase_menu_info_list">상추</div>
          <div class="seller_ingredient_purchase_how_many_list">2</div>
          <div class="seller_ingredient_purchase_price_list">5000원</div>
          <div class="seller_ingredient_purchase_review_list"><a href="./../sellerMyPage/sellerwriteReview.html"
              class="seller_ingredient_purchase_review_meal">리뷰</a></div>
        </div>
      </div>
      <div class="seller_ingredient_purchase_pagination">
        <a href="#" class="seller_ingredient_purchase_page_active">1</a>
        <a href="#" class="seller_ingredient_purchase_page">2</a>
        <a href="#" class="seller_ingredient_purchase_page">3</a>
        <a href="#" class="seller_ingredient_purchase_page">4</a>
        <a href="#" class="seller_ingredient_purchase_page">5</a>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>