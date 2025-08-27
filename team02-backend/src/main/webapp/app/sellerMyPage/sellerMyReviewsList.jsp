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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerMyReviewsList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerMyReviewsList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_reviewList_menu">
      <div class="seller_reviewList_menu_title">마이 페이지</div>
      <ul class="seller_reviewList_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

        <div class="seller_myreviews_page">
      <h2 class="seller_myreviews_list">내 리뷰 목록</h2>

      <div>
        <div class="seller_myreviews_top">
          <div class="seller_myreviews_restaurant_name">가게명</div>
          <div class="seller_myreviews_meal_name">음식/재료 명</div>
          <div class="seller_myreviews_quantity">수량</div>
          <div class="seller_myreviews_price">금액</div>
          <div class="seller_myreviews_date">작성 일자</div>
          <div class="seller_myreviews_grade">평점</div>
        </div>

        <c:choose>
          <c:when test="${not empty myReviewList}">
            <c:forEach var="review" items="${myReviewList}">
              <div class="seller_myreviews_comments_list">
                <div class="seller_myreviews_restaurant_name">
                  <c:out value="${review.storeName}" />
                </div>
                <div class="seller_myreviews_meal_name">
                  <c:out value="${review.itemName}" />
                </div>
                <div class="seller_myreviews_quantity">
                  <fmt:formatNumber value="${review.orderItemQuantity}" />
                </div>
                <div class="seller_myreviews_price">
                  <fmt:formatNumber value="${review.ordersTotalAmount}" pattern="#,###" />원
                </div>
                <div class="seller_myreviews_date">
                  <c:out value="${review.reviewCreateDate}" />
                </div>
                <div class="seller_myreviews_grade">
                  <c:out value="${review.reviewRating}" />
                </div>
              </div>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="seller_myreviews_empty">작성한 리뷰가 없습니다.</div>
          </c:otherwise>
        </c:choose>
      </div>

      <!-- 페이지네이션 자리 (요청하신 블록: 변경 없이 그대로 삽입) -->
      <div class="seller_store_info_pagination">
        <ul class="seller_store_info_pagination_ul">
          <c:if test="${prev}">
            <li><a
              href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se?page=${startPage - 1}"
              class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage"
            value="${startPage < 0 ? 0 : startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
            <c:choose>
              <c:when test="${!(i == page) }">
                <li><a class="pagination_item"
                  href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se?page=${i}">
                    <c:out value="${i}" />
                </a></li>
              </c:when>
              <c:otherwise>
                <li><a href="#" class="active"> <c:out value="${i}" />
                </a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${next}">
            <li><a
              href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se?page=${endPage + 1}"
              class="next">&gt;</a>
          </c:if>
        </ul>
      </div>
      <!-- 페이지네이션 끝 -->
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>