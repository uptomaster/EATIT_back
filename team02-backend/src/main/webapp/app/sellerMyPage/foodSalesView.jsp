<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/foodSalesView.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/foodSalesView.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="foodsalesview_my_page_list">
      <div class="foodsalesview_my_page">마이 페이지</div>
      <ul class="foodsalesview_side_bar">
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

    <form action="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se" method="get" class="foodsalesview_content_container"> <!-- 컨텐츠 영역 -->
      <div class="foodsalesview_container"> <!-- 1100px 영역-->
        <!-- 페이지 제목 -->
        <div class="foodsalesview_title">
          음식 상세보기
        </div>
        <!-- 음식 정보 등록 -->
        <div class="foodsaleswrite_box">
          <div class="foodsaleswrite_submit_table">
            <div id="food_edit_image_preview">
							<div class="foodsaleswrite_box"> <label>음식 사진 </label> </div>
							<c:if test="${not empty itemImage}">
							    <div class="food_image_box">
							        <img src="${pageContext.request.contextPath}/upload/${itemImage.itemImageSystemName}" 
							             alt="${itemImage.itemImageOriginalName}" />
							    </div>
							</c:if>
              <!-- <button type="button">등록</button> -->
            </div>
          </div>
        </div>
        
        <div class="foodsaleswrite_box">
          <label for="foodsaleswrite_menu">메뉴명</label>
          <!-- <input type="text" id="foodsaleswrite_munu"> -->
          <div id="foodsaleswrite_munu"><c:out value="${item.getItemName() }"/></div>
          <!-- <button type="button">등록</button> -->
        </div>
        
        <div class="foodsaleswrite_box">
          <label >음식 설명</label>
          <div id="foodsaleswrite_explain"><c:out value="${item.getItemContent() }"/></div>
          <!-- <textarea name="" id="foodsaleswrite_explain" maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea> -->
          <span id="foodsaleswrite_char_count">0/100</span>
          <!-- <button type="button">등록</button> -->
        </div>
        
        <div class="foodsaleswrite_box">
          <label>소비기한</label>
          <div id="foodsaleswrite_expiry"><c:out value="${item.getItemExpireDate() }"/></div>
          <!-- <input type="text" id="foodsaleswrite_expiry" placeholder="YYYY-MM-DD-MIN -SS"> -->
          <!-- <button type="button">등록</button> -->
        </div>
        
        <div class="foodsaleswrite_box">
          <div class="foodsaleswrite_quantitiy_container">
            <label class="foodsaleswrite_small_label">수량</label>
            <div id="foodsaleswrite_quantity"><c:out value="${item.getItemQuantity() }"/></div>
            <span id="foodsaleswrite_food_count">개</span>
            <!-- <input type="number" id="foodsaleswrite_quantity" min="0" placeholder="개수"> -->
            <!-- <button type="button">등록</button> -->
          </div>
          <div class="foodsaleswrite_price_container">
            <label class="foodsaleswrite_small_label">가격</label>
            <div id="foodsaleswrite_price"><c:out value="${item.getItemPrice() }"/></div>
            <span>원</span>
            <!-- <input type="number" id="foodsaleswrite_price" min="0" placeholder="원단위"> -->
            <!-- <button type="button">등록</button> -->
          </div>
        </div>
        <!-- 추가된 판매 상태 라디오 버튼 영역 -->
				<div class="foodsaleswrite_box foodsaleswrite_sellstate_container">
					<label class="foodsaleswrite_small_label">판매상태</label>
					<div class="foodsaleswrite_sellstate_options">
						<label> 
						<input type="radio" name="itemSellState" value="Y" checked> 판매중
						</label> 
						<label> <input type="radio" name="itemSellState" value="N"> 판매중지
						</label>
					</div>
				</div>
				<!-- 끝 -->
				<div class="food_edit_btn_container">
	        <button class="foodsaleswrite_buzz" type="submit">목록으로</button>
	        
					<button type="button" class="view_ingredient_edit_btn"
                onclick="location.href='${pageContext.request.contextPath}/sellerMyPage/editFood.se?itemNumber=${item.itemNumber}'">
                수정</button>
				</div>
    </div> <!-- 컨텐츠 -->
      </form> <!-- 1100px 영역 -->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>