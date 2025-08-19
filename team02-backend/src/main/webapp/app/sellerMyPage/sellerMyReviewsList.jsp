<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/sellerMyReviewsList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/sellerMyPage/sellerMyReviewsList.js"></script>
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
    <div class="seller_reviewList_menu">
      <div class="seller_reviewList_menu_title">마이 페이지</div>
      <ul class="seller_reviewList_menu_list">
        <li><a href="./../sellerMyPage/editSellerInfo.html">내 정보 수정</a></li>
        <li><a href="./../sellerMyPage/sellerfoodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../sellerMyPage/selleringredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../sellerMyPage/sellerMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyCommentsList.html">내 댓글 관리</a></li>
        <li class="seller_reviewList_menu_list_current"><a href="./../sellerMyPage/sellerMyReviewsList.html">내 리뷰 관리</a>
        </li>
        <li><a href="./../sellerMyPage/storeInfo.html">사업장 관리</a></li>
        <li><a href="./../sellerMyPage/todaySaleList.html">판매 내역</a></li>
      </ul>
    </div>

    <div class="seller_myreviews_page">
      <h2 class="seller_myreviews_list">내 리뷰 목록</h2>
      <div>
        <div class="seller_myreviews_top">
          <div class="seller_myreviews_restaurant_name">가게명</div>
          <div class="seller_myreviews_meal_name">음식/재료 명</div>
          <div class="seller_myreviews_quantity">수량</div>
          <div class="seller_myreviews_price">금액</div>
          <div class="seller_myreviews_date">작성 일자</div>
          <div class="seller_myreviews_grade">평점</div>
        </div>
        <div class="seller_myreviews_comments_list">
          <div class="seller_myreviews_restaurant_name">기수베이커리</div>
          <div class="seller_myreviews_meal_name">식빵</div>
          <div class="seller_myreviews_quantity">1</div>
          <div class="seller_myreviews_price">5000원</div>
          <div class="seller_myreviews_date">2025-08-02</div>
          <div class="seller_myreviews_grade">5</div>
        </div>
        <div class="seller_myreviews_comments_list">
          <div class="seller_myreviews_restaurant_name">기수식당</div>
          <div class="seller_myreviews_meal_name">제육볶음</div>
          <div class="seller_myreviews_quantity">1</div>
          <div class="seller_myreviews_price">6000원</div>
          <div class="seller_myreviews_date">2025-08-08</div>
          <div class="seller_myreviews_grade">5</div>
        </div>
      </div>
      <div class="seller_myreviews_pagination">
        <a href="#" class="seller_myreviews_page_active">1</a>
        <a href="#" class="seller_myreviews_page">2</a>
        <a href="#" class="seller_myreviews_page">3</a>
        <a href="#" class="seller_myreviews_page">4</a>
        <a href="#" class="seller_myreviews_page">5</a>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>