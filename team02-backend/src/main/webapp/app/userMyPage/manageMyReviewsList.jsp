<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/manageMyReviewsList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/manageMyReviewsList.js"></script>
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
    <div class="managemyreviews_my_page_list">
      <div class="managemyreviews_my_page">마이 페이지</div>
      <ul class="managemyreviews_side_bar">
        <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
        <li class="managemyreviews_main"><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
      </ul>
    </div>
    <div class="managemyreviews_page">
      <h2 class="managemyreviews_list">내 리뷰 목록</h2>
      <div>
        <div class="managemyreviews_top">
          <div class="managemyreviews_restaurant_name">가게명</div>
          <div class="managemyreviews_restaurant_name">리뷰내용</div>
          <div class="managemyreviews_meal_name">음식/재료 명</div>
          <div class="managemyreviews_quantity">수량</div>
          <div class="managemyreviews_price">금액</div>
          <div class="managemyreviews_date">작성 일자</div>
          <div class="managemyreviews_grade">평점</div>
        </div>
        <c:choose>
		    <c:when test="${empty myReviews}">
		      <div class="managemyposts_no_data">작성한 리뷰가 없습니다.</div>
		    </c:when>
		    <c:otherwise>
		      <c:forEach var="review" items="${myReviews}">
		        <div class="managemyreviews_comments_list">
		          <div class="managemyreviews_restaurant_name">
					<c:out value="${review.storeName}" />
		          </div>
		          <div class="managemyreviews_restaurant_name">
					<c:out value="${review.reviewContent}" />
		          </div>
		          <div class="managemyreviews_meal_name">
					<c:out value="${review.itemName}" />
		          </div>
		          <div class="managemyreviews_quantity">
		          	<c:out value="${review.orderItemQuantity}" />
		          </div>
		          <div class="managemyreviews_price">
		          	<c:out value="${review.orderItemUnitPrice}" />
		          </div>
		          <div class="managemyreviews_date">
		          	<c:out value="${review.reviewCreateDate}" />
		          </div>
		          <div class="managemyreviews_grade">
		          	<c:out value="${review.reviewRating}" />
		          </div>
		        </div>
		      </c:forEach>
		    </c:otherwise>
		  </c:choose>
      </div>
      
    
      
      <!-- 페이지네이션 (하단 페이지 넘기기) -->
	    <div class="pagination">
	        <ul>
	          <c:if test="${prev}">
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my?page=${startPage - 1}" class="prev">&lt;</a></li>
	          </c:if>
	          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
	          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
	          	<c:choose>
	          		<c:when test="${!(i == page) }">
	          			<li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my?page=${i}">
	          				<c:out value="${i}" />
	          			</a></li>
	          		</c:when>
	          		<c:otherwise>
	          			<li><a href="#" class="active">
	          				<c:out value="${i}" />
	          			</a></li>
	          		</c:otherwise>
	          	</c:choose>
	          </c:forEach>
	          <c:if test="${next}">
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my?page=${endPage + 1}" class="next">&gt;</a></li>
	          </c:if>
	        </ul>
	     </div>

    </div> 
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>