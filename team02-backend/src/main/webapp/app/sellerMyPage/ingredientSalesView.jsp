<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/ingredientSalesView.css">
  <script defer src="./../../assets/js/sellerMyPage/ingredientSalesView.js"></script>
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
    <div class="ingredientsalesview_my_page_list">
      <div class="ingredientsalesview_my_page">마이 페이지</div>
      <ul class="ingredientsalesview_side_bar">
        <li><a href="./../sellerMyPage/editSellerInfo.html">내 정보 수정</a></li>
        <li><a href="./../sellerMyPage/sellerfoodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../sellerMyPage/selleringredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../sellerMyPage/sellerMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyReviewsList.html">내 리뷰 관리</a></li>
        <li class="ingredientsalesview_main"><a href="./../sellerMyPage/storeInfo.html">사업장 관리</a></li>
        <li><a href="./../sellerMyPage/todaySaleList.html">판매 내역</a></li>
      </ul>
    </div>

    <div class="ingredientsalesview_container"> <!-- 1100px 영역-->
      <form action="" method="get" class="ingredientsalesview_content_container"> <!-- 컨텐츠 영역 -->
        <div class="ingredientsalesview_title">
          재료 상세보기
        </div>
        <!-- 음식 정보 수정 -->
        <div class="ingredientsalesview_photo_container">
          <div class="ingredientsalesview_submit_table">
            <div class="ingredientsalesview_box">
              <label for="ingredientsalesview_photo">음식 사진 등록</label>
              <button type="button">수정</button>
            </div>
          </div>
        </div>
        <div class="ingredientsalesview_box">
          <label for="ingredientsalesview_menu">메뉴명</label>
          <input type="text" id="ingredientsalesview_munu">
          <button type="button">수정</button>
        </div>
        <div class="ingredientsalesview_box">
          <label for="ingredientsalesview_explain">음식 설명</label>
          <textarea name="" id="ingredientsalesview_explain" maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea>
          <span id="ingredientsalesview_char_count">0/100</span>
          <button type="button">수정</button>
        </div>
        <div class="ingredientsalesview_expiry_container">
          <label for="ingredientsalesview_expiry">소비기한</label>
          <input type="text" id="ingredientsalesview_expiry" placeholder="YYYY-MM-DD-MIN -SS">
          <button type="button">수정</button>
        </div>
        <div class="ingredientsalesview_box">
          <div class="ingredientsalesview_quantitiy_container">
            <label for="ingredientsalesview_quantity" class="ingredientsalesview_small_label">수량</label>
            <input type="number" id="ingredientsalesview_quantity" min="0" placeholder="개수">
            <span id="ingredientsalesview_food_count">개</span>
            <button type="button">수정</button>
          </div>
          <div class="ingredientsalesview_price_container">
            <label for="ingredientsalesview_price" class="ingredientsalesview_small_label">가격</label>
            <input type="number" id="ingredientsalesview_price" min="0" placeholder="원단위">
            <span>원</span>
            <button type="button">수정</button>
          </div>
        </div> <!-- 컨텐츠 -->
      </form> <!-- 1100px 영역 -->
  </main>
  <footer id="footer"></footer>
</body>

</html>