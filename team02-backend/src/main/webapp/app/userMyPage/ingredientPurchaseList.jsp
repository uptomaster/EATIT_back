<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <!--css  -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/ingredientPurchaseList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  
  <!--js  -->
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/ingredientPurchaseList.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 사이드 메뉴바 영역 -->
    <div class="ingredientpurchase_my_page_list">
      <!-- 마이페이지 상단 제목 -->
      <div class="ingredientpurchase_my_page">마이 페이지</div>

      <!-- 마이페이지 사이드 메뉴 리스트 -->
      <ul class="ingredientpurchase_side_bar">
        <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
        <li class="ingredientpurchase_main">
          <a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
      </ul>
    </div>

    <!-- 본문 콘텐츠 (가로 너비 1100px 영역으로 예상) -->
    <div class="ingredientpurchase_page">

      <!-- 페이지 제목 -->
      <h2 class="ingredientpurchase_list">재료 구매 내역</h2>

      <!-- 리스트 제목 영역 (표의 헤더 역할) -->
      <div class="ingrepurchase_all">
        <div class="ingredientpurchaselist_top">
          <div class="ingredientpurchaselist_date">구매날짜</div>
          <div class="ingredientpurchaselist_restaurant_name">가게명</div>
          <div class="ingredientpurchaselist_menu_info">상품정보</div>
          <div class="ingredientpurchaselist_how_many">수량</div>
          <div class="ingredientpurchaselist_price">금액</div>
          <div class="ingredientpurchaselist_review">리뷰</div>
        </div>

        <!-- 실제 구매 내역 -->
        <c:choose>
	          <c:when test="${empty ingrebuylist}">
			    <div class="ingredientpurchase_page_list">
			      구매한 상품이 없습니다.
			    </div>
			  </c:when>
			  <c:otherwise>
			      <c:forEach var="myorder" items="${ingrebuylist}">
					  <div class="ingredientpurchase_page_list">
					    <div class="ingredientpurchase_date_list">
					      <c:out value="${myorder.ordersDate}"/>
					    </div>
					    <div class="ingredientpurchase_restaurant_name_list">
					      <c:out value="${myorder.storeName}" />
					    </div>
					    <div class="ingredientpurchase_menu_info_list">
					      <c:out value="${myorder.itemName}" />
					    </div>
					    <div class="ingredientpurchase_how_many_list">
					      <c:out value="${myorder.orderItemQuantity}" />
					    </div>
					    <div class="ingredientpurchase_price_list">
					      <c:out value="${myorder.orderItemUnitPrice}" />
					    </div>
					    <div class="ingredientpurchase_review_list">
					      <a href="${pageContext.request.contextPath}/userMyPage/writeReview.jsp?orderItemNumber=${myorder.orderItemNumber}" class="ingredientpurchase_review_meal">리뷰</a>
					    </div>
					  </div>
				  </c:forEach>
			  </c:otherwise>
		  </c:choose>

      	<!-- 페이지네이션 (하단 페이지 넘기기) -->
	    <div class="pagination">
	        <ul>
	          <c:if test="${prev}">
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my?page=${startPage - 1}" class="prev">&lt;</a></li>
	          </c:if>
	          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
	          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
	          	<c:choose>
	          		<c:when test="${!(i == page) }">
	          			<li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my?page=${i}">
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
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my?page=${endPage + 1}" class="next">&gt;</a></li>
	          </c:if>
	        </ul>
	      </div>
      
      
      

    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>