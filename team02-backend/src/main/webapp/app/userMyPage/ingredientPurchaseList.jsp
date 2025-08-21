<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/ingredientPurchaseList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/ingredientPurchaseList.js"></script>
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
    <!-- 사이드 메뉴바 영역 -->
    <div class="ingredientpurchase_my_page_list">
      <!-- 마이페이지 상단 제목 -->
      <div class="ingredientpurchase_my_page">마이 페이지</div>

      <!-- 마이페이지 사이드 메뉴 리스트 -->
      <ul class="ingredientpurchase_side_bar">
        <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
        <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>

        <!-- 현재 페이지는 '재료 구매 내역'이라 강조 클래스 적용 -->
        <li class="ingredientpurchase_main">
          <a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a>
        </li>

        <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
      </ul>
    </div>

    <!-- 본문 콘텐츠 (가로 너비 1100px 영역으로 예상) -->
    <div class="ingredientpurchase_page">

      <!-- 페이지 제목 -->
      <h2 class="ingredientpurchase_list">재료 구매 내역</h2>

      <!-- 리스트 제목 영역 (표의 헤더 역할) -->
      <div>
        <div class="ingredientpurchaselist_top">
          <div class="ingredientpurchaselist_date">구매날짜</div>
          <div class="ingredientpurchaselist_img">이미지</div>
          <div class="ingredientpurchaselist_restaurant_name">가게명</div>
          <div class="ingredientpurchaselist_menu_info">상품정보</div>
          <div class="ingredientpurchaselist_how_many">수량</div>
          <div class="ingredientpurchaselist_price">금액</div>
          <div class="ingredientpurchaselist_review">리뷰</div>
        </div>

        <!-- 구매 내역 1건 (예시 데이터) -->
        <div class="ingredientpurchase_page_list">
          <div class="ingredientpurchase_date_list">2025.08.03</div>
          <div class="ingredientpurchase_img_list">
            <img class="ingredientpurchase_img" src="./../../assets/img/carrot.jpg" alt="">
          </div>
          <div class="ingredientpurchase_restaurant_name_list">오늘 카레 어떄</div>
          <div class="ingredientpurchase_menu_info_list">당근</div>
          <div class="ingredientpurchase_how_many_list">4</div>
          <div class="ingredientpurchase_price_list">16000원</div>
          <div class="ingredientpurchase_review_list">
            <a href="./../userMyPage/writeReview.html" class="ingredientpurchase_review_meal">리뷰</a>
          </div>
        </div>

        <!-- 구매 내역 2건 (예시 데이터) -->
        <div class="ingredientpurchase_page_list">
          <div class="ingredientpurchase_date_list">2025.08.04</div>
          <div class="ingredientpurchase_img_list">
            <img class="ingredientpurchase_img" src="./../../assets/img/씨앗.png" alt="">
          </div>
          <div class="ingredientpurchase_restaurant_name_list">장충동 왕족발 보쌈</div>
          <div class="ingredientpurchase_menu_info_list">상추</div>
          <div class="ingredientpurchase_how_many_list">2</div>
          <div class="ingredientpurchase_price_list">5000원</div>
          <div class="ingredientpurchase_review_list">
            <a href="./../userMyPage/writeReview.html" class="ingredientpurchase_review_meal">리뷰</a>
          </div>
        </div>
      </div>

      <!-- 하단 페이지네이션 -->
      <div class="ingredientpurchase_pagination">
        <!-- 현재 페이지는 1번 -->
        <a href="#" class="ingredientpurchase_page_active">1</a>
        <a href="#" class="ingredientpurchase_page">2</a>
        <a href="#" class="ingredientpurchase_page">3</a>
        <a href="#" class="ingredientpurchase_page">4</a>
        <a href="#" class="ingredientpurchase_page">5</a>
      </div>

    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>