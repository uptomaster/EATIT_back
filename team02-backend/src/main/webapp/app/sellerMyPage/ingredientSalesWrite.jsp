<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/ingredientSalesWrite.css">
  <script defer src="./../../assets/js/sellerMyPage/ingredientSalesWrite.js"></script>
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
    <div class="ingredientsaleswrite_my_page_list">
      <div class="ingredientsaleswrite_my_page">마이 페이지</div>
      <ul class="ingredientsaleswrite_side_bar">
        <li><a href="./../sellerMyPage/editSellerInfo.html">내 정보 등록</a></li>
        <li><a href="./../sellerMyPage/sellerfoodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../sellerMyPage/selleringredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../sellerMyPage/sellerMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyReviewsList.html">내 리뷰 관리</a></li>
        <li class="ingredientsaleswrite_main"><a href="./../sellerMyPage/storeInfo.html">사업장 관리</a></li>
        <li><a href="./../sellerMyPage/tosalesHistoryList.html">판매 내역</a></li>
      </ul>
    </div>

    <div class="ingredientsaleswrite_container"> <!-- 1100px 영역-->
      <form action="" method="get" class="ingredientsaleswrite_content_container"> <!-- 컨텐츠 영역 -->
        <div class="ingredientsaleswrite_title">
          재료 판매 등록
        </div>
        <!-- 음식 정보 등록 -->
        <div class="ingredientsaleswrite_photo_container">
          <div class="ingredientsaleswrite_submit_table">
            <div class="ingredientsaleswrite_box">
              <label for="ingredientsaleswrite_photo">음식 사진 등록</label>
              <button type="button">등록</button>
            </div>
          </div>
        </div>
        <div class="ingredientsaleswrite_box">
          <label for="ingredientsaleswrite_menu">메뉴명</label>
          <input type="text" id="ingredientsaleswrite_munu">
          <button type="button">등록</button>
        </div>
        <div class="ingredientsaleswrite_box">
          <label for="ingredientsaleswrite_explain">음식 설명</label>
          <textarea name="" id="ingredientsaleswrite_explain" maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea>
          <span id="ingredientsaleswrite_char_count">0/100</span>
          <button type="button">등록</button>
        </div>
        <div class="ingredientsaleswrite_expiry_container">
          <label for="ingredientsaleswrite_expiry">소비기한</label>
          <input type="text" id="ingredientsaleswrite_expiry" placeholder="YYYY-MM-DD-MIN -SS">
          <button type="button">등록</button>
        </div>
        <div class="ingredientsaleswrite_box">
          <div class="ingredientsaleswrite_quantitiy_container">
            <label for="ingredientsaleswrite_quantity" class="ingredientsaleswrite_small_label">수량</label>
            <input type="number" id="ingredientsaleswrite_quantity" min="0" placeholder="개수">
            <span id="ingredientsaleswrite_food_count">개</span>
            <button type="button">등록</button>
          </div>
          <div class="ingredientsaleswrite_price_container">
            <label for="ingredientsaleswrite_price" class="ingredientsaleswrite_small_label">가격</label>
            <input type="number" id="ingredientsaleswrite_price" min="0" placeholder="원단위">
            <span>원</span>
            <button type="button">등록</button>
          </div>
        </div>
        <button class="ingredientsaleswrite_buzz" type="button">등록</button>
    </div> <!-- 컨텐츠 -->
    </form> <!-- 1100px 영역 -->
  </main>
  <footer id="footer"></footer>
</body>

</html>