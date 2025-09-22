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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/selleringredientPurchaseList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/selleringredientPurchaseList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_ingredient_purchase_menu">
      <div class="seller_ingredient_purchase_menu_title">마이 페이지</div>
      <ul class="seller_ingredient_purchase_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li class="seller_ingredient_purchase_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>
    <div class="seller_ingredient_purchase_page">
      <h2 class="seller_ingredient_purchase_list">재료 구매 내역</h2>
     
      		<%-- <c:set  var="itemimg" value="${pageContext.request.contextPath}/upload/${itemImage.itemImageSystemName}">--%>
      <div>
        <div class="seller_ingredient_purchaselist_top">
          <div class="seller_ingredient_purchaselist_date">구매날짜</div>
          <div class="seller_ingredient_purchaselist_restaurant_name">가게명</div>
          <div class="seller_ingredient_purchaselist_menu_info">상품정보</div>
          <div class="seller_ingredient_purchaselist_how_many">수량</div>
          <div class="seller_ingredient_purchaselist_price">금액</div>
          <div class="seller_ingredient_purchaselist_review">리뷰</div>
        </div>
        <c:choose>
	      	<c:when test ="${not empty foodbuylist}">
		        <c:forEach var="buy" items="${foodbuylist}">
		        <div class="seller_ingredient_purchase_page_list">
		        	<input type="hidden" name="ordersNumber" value="${buy.ordersNumber}">
		          <div class="seller_ingredient_purchase_date_list"><c:out value="${buy.ordersDate}"/></div>
		          <div class="seller_ingredient_purchase_restaurant_name_list"><c:out value="${buy.storeName}"/></div>
		          <div class="seller_ingredient_purchase_menu_info_list"><c:out value="${buy.itemName }"/></div>
		          <div class="seller_ingredient_purchase_how_many_list"><c:out value="${buy.orderItemQuantity }"/></div>
		          <div class="seller_ingredient_purchase_price_list"><c:out value="${buy.orderItemUnitPrice }"/>원</div>
		          <div class="seller_ingredient_purchase_review_list">
		          <a href="${pageContext.request.contextPath}/sellerMyPage/sellerwriteReview.se?ordersNumber=${buy.ordersNumber}"
		              class="seller_ingredient_purchase_review_meal">리뷰</a></div>
		        </div>
		      	</c:forEach>
	      </c:when>
	      <c:otherwise>
		      <div class="seller_ingredient_purchase_page_list">
		       <div colspan="7" align="center">재료 구매 내역이 없습니다.</div>
	       </div>
	      </c:otherwise>
      </c:choose>
      </div>
      
      <!-- 페이지네이션 자리 (요청하신 블록: 변경 없이 그대로 삽입) -->
      <div class="pagination">
        <ul class="pagination_ul">
          <c:if test="${prev}">
            <li><a
              href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se?page=${startPage - 1}"
              class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage"
            value="${startPage < 0 ? 0 : startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
            <c:choose>
              <c:when test="${!(i == page) }">
                <li><a class="pagination_item"
                  href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se?page=${i}">
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
              href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se?page=${endPage + 1}"
              class="next">&gt;</a>
          </c:if>
        </ul>
      </div>
      <!-- 페이지네이션 끝 -->
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
	<script>
		window.ordersNumber = "${food.ordersNumber}";
		console.log(ordersNumber);
	</script>
</body>
</html>