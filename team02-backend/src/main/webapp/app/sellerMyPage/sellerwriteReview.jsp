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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerwriteReview.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerwriteReview.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_write_review_menu">
      <div class="seller_write_review_menu_title">마이 페이지</div>
      <ul class="seller_write_review_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>


    <div class="seller_write_review_page"> <!--1100px영역-->
      <h2 class="seller_write_review_list">리뷰 작성</h2>
      <div>
        <div class="seller_write_review_top">
          <div class="seller_write_review_restaurant_name">가게명</div>
          <div class="seller_write_review_meal_name">음식/재료 명</div>
          <div class="seller_write_review_quantity">수량</div>
          <div class="seller_write_review_price">금액</div>
          <div class="seller_write_review_date">구매 일자</div>
        </div>
        <c:forEach var="order" items="${infoList}">
	        <div class="seller_write_review_comments_list">
	          <div class="seller_write_review_restaurant_name"><c:out value="${order.storeName}"/></div>
	          <div class="seller_write_review_meal_name"><c:out value="${order.itemName}"/></div>
	          <div class="seller_write_review_quantity"><c:out value="${order.orderItemQuantity}"/></div>
	          <div class="seller_write_review_price"><c:out value="${order.ordersTotalAmount}"/>원</div>
	          <div class="seller_write_review_date"><c:out value="${order.ordersDate}"/></div>
	        </div>
        </c:forEach>
        
        <form id="seller_write_review_form" action="${pageContext.request.contextPath}/sellerMyPage/sellerwriteReviewOk.se" method="post">
          <input type="hidden" value="${ordersNumber}" name="ordersNumber">
          <input type="hidden" value="${memberNumber}" name="memberNumber">
          <input type="hidden" value="${businessNumber}" name="businessNumber">
          <input type="hidden" value="${itemType}" name="itemType">
          <input type="hidden" name="reviewRating" id="reviewRating" value="0">
          
          <!-- 별점 -->
		    <div class="writereview_set_rank">
		        <div>별점주기</div>
		        <c:forEach var="i" begin="1" end="5">
		            <button type="button" class="writereview_counting_Star_button">
		                <img class="writereview_counting_Star" 
		                     src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" 
		                     alt="별점">
		            </button>
		        </c:forEach>
		    </div>
		    
		    
          <div class="seller_write_review_form_group">
            <label for="content">내용</label>
            <textarea id="content" name="reviewContent" rows="10" placeholder="맛, 양, 친절도, 재구매 의사 등을 자유롭게 작성해주세요." required></textarea>
          </div>
          
          
          <div class="seller_write_review_button_group">
            <button type="reset" class="seller_write_review_cancel_btn">작성 취소</button>
            <button type="submit" class="seller_write_review_submit_btn">작성 완료</button>
          </div>
        </form>
      </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>