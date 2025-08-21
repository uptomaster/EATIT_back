<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/manageMyReviewsList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/manageMyReviewsList.js"></script>
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
    <div class="managemyreviews_my_page_list">
      <div class="managemyreviews_my_page">마이 페이지</div>
      <ul class="managemyreviews_side_bar">
        <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
        <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../userMyPage/manageMyCommentsList.html">내 글 관리</a></li>
        <li><a href="./../userMyPage/manageMyReviewsList.html">내 댓글 관리</a></li>
        <li class="managemyreviews_main"><a href="./../userMyPage/writeReview.html">내 리뷰 관리</a></li>
      </ul>
    </div>
    <div class="managemyreviews_page">
      <h2 class="managemyreviews_list">내 리뷰 목록</h2>
      <div>
        <div class="managemyreviews_top">
          <div class="managemyreviews_restaurant_name">가게명</div>
          <div class="managemyreviews_meal_name">음식/재료 명</div>
          <div class="managemyreviews_quantity">수량</div>
          <div class="managemyreviews_price">금액</div>
          <div class="managemyreviews_date">작성 일자</div>
          <div class="managemyreviews_grade">평점</div>
        </div>
        <div class="managemyreviews_comments_list">
          <div class="managemyreviews_restaurant_name">기수베이커리</div>
          <div class="managemyreviews_meal_name">식빵</div>
          <div class="managemyreviews_quantity">1</div>
          <div class="managemyreviews_price">5000원</div>
          <div class="managemyreviews_date">2025-08-02</div>
          <div class="managemyreviews_grade">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
          </div>
        </div>
        <div class="managemyreviews_comments_list">
          <div class="managemyreviews_restaurant_name">기수식당</div>
          <div class="managemyreviews_meal_name">제육볶음</div>
          <div class="managemyreviews_quantity">1</div>
          <div class="managemyreviews_price">6000원</div>
          <div class="managemyreviews_date">2025-08-08</div>
          <div class="managemyreviews_grade">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
            <img class="counting_Star" src="./../../assets/img/counting_Star.png" alt="">
          </div>
        </div>
      </div>
      <div class="managemyreviews_pagination">
        <a href="#" class="managemyreviews_page_active">1</a>
        <a href="#" class="managemyreviews_page">2</a>
        <a href="#" class="managemyreviews_page">3</a>
        <a href="#" class="managemyreviews_page">4</a>
        <a href="#" class="managemyreviews_page">5</a>
      </div>
    </div> 
  </main>
  <footer id="footer"></footer>
</body>
</html>