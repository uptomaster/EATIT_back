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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/ingredientSalesView.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/ingredientSalesView.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="ingredientsalesview_my_page_list">
      <div class="ingredientsalesview_my_page">마이 페이지</div>
      <ul class="ingredientsalesview_side_bar">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
        <li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

    <div class="ingredientsalesview_container"> <!-- 1100px 영역-->
      <form action="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se" method="post" enctype="multipart/form-data"
				class="ingredientsaleswrite_content_container"> <!-- 컨텐츠 영역 -->
        <div class="ingredientsalesview_title">
          재료 상세보기
        </div>
        <!-- 음식 정보 수정 -->
        <div class="ingredientsalesview_photo_container">
          <div class="ingredientsalesview_submit_table">
            <div class="ingredientsalesview_box">
              <label for="ingredientsalesview_photo">음식 사진 </label>
            </div>
          </div>
        </div>
        <div class="ingredientsalesview_box">
          <label for="ingredientsalesview_menu">메뉴명</label>
          <input name="itemName" type="text" id="ingredientsalesview_munu">
        </div>
        <div class="ingredientsalesview_box">
          <label for="ingredientsalesview_explain">음식 설명</label>
          <textarea name="itemContent" id="ingredientsalesview_explain" maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea>
          <span id="ingredientsalesview_char_count">0/100</span>
        </div>
        <div class="ingredientsalesview_expiry_container">
          <label for="ingredientsalesview_expiry">소비기한</label>
          <input name="itemExpiry" type="text" id="ingredientsalesview_expiry" placeholder="YYYY-MM-DD-MIN -SS">
        </div>
        <div class="ingredientsalesview_box">
          <div class="ingredientsalesview_quantitiy_container">
            <label for="ingredientsalesview_quantity" class="ingredientsalesview_small_label">수량</label>
            <input name="itemQuantity" type="number" id="ingredientsalesview_quantity" min="0" placeholder="개수">
            <span id="ingredientsalesview_food_count">개</span>
          </div>
          <div class="ingredientsalesview_price_container">
            <label for="ingredientsalesview_price" class="ingredientsalesview_small_label">가격</label>
            <input name="itemPrice" type="number" id="ingredientsalesview_price" min="0" placeholder="원단위">
            <span>원</span>
          </div>
        </div> <!-- 컨텐츠 -->
      </form> <!-- 1100px 영역 -->
      </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>