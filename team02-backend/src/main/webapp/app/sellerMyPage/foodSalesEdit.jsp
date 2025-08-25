<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/foodSaleEdit.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/foodSalesEdit.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
  <title>밥세권</title>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="foodsalesedit_my_page_list">
      <div class="seller_my_page">마이 페이지</div>
      <ul class="foodsalesedit_side_bar">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li class="store_info_menu_list_current">
					<a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>
    <!-- 1100px 영역-->
    <div class="food_edit_container">
      <!-- 컨텐츠 영역 -->
      <form action="" method="get" class="food_edit_content_container">
        <!-- 페이지 제목 -->
        <div class="food_edit_title"> 음식 판매 수정 </div>
        <!-- 음식 정보 수정 -->
        <!-- 음식 사진 -->
        <div class="food_edit_photo_container">
          <div class="food_edit_submit_table">
            <div class="food_edit_box">
              <label for="food_edit_photo">음식 사진 등록</label>
              <button type="button">수정</button>
            </div>
          </div>
        </div>
        <!-- 메뉴명 -->
        <div class="food_edit_box">
          <label for="food_edit_menu">메뉴명</label>
          <input type="text" id="food_edit_munu">
          <button type="button">수정</button>
        </div>
        <!-- 음식 설명 -->
        <div class="food_edit_box">
          <label for="food_edit_explain">음식 설명</label>
          <textarea name="" id="food_edit_explain" maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea>
          <span id="food_edit_char_count">0/100</span>
          <button type="button">수정</button>
        </div>
        <!-- 소비기한 -->
        <div class="food_edit_expiry_container">
          <label for="food_edit_expiry">소비기한</label>
          <input type="date" id="food_edit_expiry" placeholder="YYYY-MM-DD-MIN -SS">
          <button type="button">수정</button>
        </div>
        <!-- 수량 & 가격 -->
        <div class="food_edit_box">
          <!-- 수량 -->
          <div class="food_edit_quantitiy_container">
            <label for="food_edit_quantity" class="food_edit_small_label">수량</label>
            <input type="number" id="food_edit_quantity" min="0" placeholder="개수">
            <span id="food_edit_food_count">개</span>
            <button type="button">수정</button>
          </div>
          <!-- 가격 -->
          <div class="food_edit_price_container">
            <label for="food_edit_price" class="food_edit_small_label">가격</label>
            <input type="number" id="food_edit_price" min="0" placeholder="원단위">
            <span>원</span>
            <button type="button">수정</button>
          </div>
        </div>
        <!-- 삭제 저장 버튼 -->
        <div class="food_edit_btn_container">
          <button class="food_delete_buzz" type="button">삭제</button>
          <button class="food_edit_buzz" type="button">저장</button>
        </div>
    </div> <!-- 컨텐츠 영역 끝 -->
    </form> <!-- 1100px 영역 끝 -->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>