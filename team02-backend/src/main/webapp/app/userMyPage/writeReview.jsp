<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/writeReview.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/writeReview.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <div class="writereview_my_page_list">
      <div class="writereview_my_page">마이 페이지</div>
      <ul class="writereview_my_page">
	      <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
          <li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
          <li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
          <li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
          <li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
          <li class="active"><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
       </ul>   
    </div>
    <div class="writereview_page">
      <h2 class="writereview_list">리뷰 작성</h2>
      <div>
        <div class="writereview_top">
          <div class="writereview_restaurant_name">가게명</div>
          <div class="writereview_meal_name">음식/재료 명</div>
          <div class="writereview_quantity">수량</div>
          <div class="writereview_price">금액</div>
          <div class="writereview_date">구매 일자</div>
        </div>
        <div class="writereview_comments_list">
          <div class="writereview_restaurant_name">기수베이커리</div>
          <div class="writereview_product_img"><img class="writereview_meal_img" src="" alt=""></div>
          <div class="writereview_meal_name">식빵</div>
          <div class="writereview_quantity">1</div>
          <div class="writereview_price">5000원</div>
          <div class="writereview_date">2025-08-02</div>
        </div>
        
        
        <c:forEach var="myorder" items="${foodbuylist}">
	      <div class="foodpurchase_page_list">
	        <div class="food_purchase_date_list">
	          <c:out value="${myorder.ordersDate}"/>
	        </div>
	        <div class="food_purchase_restaurant_name_list">
	          <c:out value="${myorder.storeName}" />
	        </div>
	        <div class="food_purchase_menu_info_list">
	          <c:out value="${myorder.itemName}" />
	        </div>
	        <div class="food_purchase_how_many_list">
	          <c:out value="${myorder.orderItemQuantity}" />
	        </div>
	        <div class="food_purchase_price_list">
	          <c:out value="${myorder.orderItemUnitPrice}" />
	        </div>
	        <div class="food_purchase_review_list">
	          <a href="${pageContext.request.contextPath}/userMyPage/writeReview.jsp?orderItemNumber=${myorder.orderItemNumber}" class="food_purchase_review_meal">리뷰</a>
	        </div>
	      </div>
	    </c:forEach>
        
        
        
        
        
        
        <div class="writereview_set_rank">
          <div>별점주기</div>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="${pageContext.request.contextPath}/assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
        </div>
        <form action="">
          <div class="writereview_form_group">
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
          </div>
          <div class="writereview_button_group">
            <button type="reset" class="writereview_cancel_btn">작성 취소</button>
            <button type="submit" class="writereview_submit_btn">작성 완료</button>
          </div>
        </form>
      </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>