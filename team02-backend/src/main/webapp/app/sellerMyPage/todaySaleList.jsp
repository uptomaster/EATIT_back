<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/todaySaleList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/todaySaleList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="today_sell_menu">
      <div class="today_sell_menu_title">마이 페이지</div>
      <ul class="today_sell_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
        <li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
    </div>

    <div class="today_sell_page"> <!-- 1100 영역-->
      <h2 class="today_sell_title"> 오늘 판매 내역</h2> <!-- 페이지 제목 -->
      <!-- 총/오늘 판매내역 선택  -->
       <!-- 탭 -->
	    <div class="tabs">
	      <a href="${pageContext.request.contextPath}/sellerMyPage/todaySale.se" class="active">오늘 판매</a>
	      <a href="${pageContext.request.contextPath}/sellerMyPage/monthSale.se">이번달 판매</a>
	      <a href="${pageContext.request.contextPath}/sellerMyPage/totalSale.se">누적 판매</a>
	    </div>
	
	    <!-- 요약 카드 -->
	    <div class="sales_summary">
	      <div class="sales_card"><div class="title">오늘 매출</div>   <div class="value"><c:out value="${summary.todayAmount}"/> 원</div></div>
	      <div class="sales_card"><div class="title">이번 달 매출</div><div class="value"><c:out value="${summary.monthAmount}"/> 원</div></div>
	      <div class="sales_card"><div class="title">누적 매출</div>   <div class="value"><c:out value="${summary.totalAmount}"/> 원</div></div>
	    </div>
	
	    <!-- 검색(오늘은 날짜 고정이라 키워드만) -->
	    <form class="sales_filters" method="get" action="${pageContext.request.contextPath}/sellerMyPage/todaySale.se">
	      <input type="text" name="q" placeholder="주문번호/상품명/구매자" value="${fn:escapeXml(param.q)}"/>
	      <button type="submit" class="btn">검색</button>
	    </form>
	
	    <!-- 리스트 헤더 -->
	    <div class="list_header">
	      <div class="col col_date">주문일자</div>
	      <div class="col col_order">주문번호</div>
	      <div class="col col_title">상품명</div>
	      <div class="col col_qty">수량</div>
	      <div class="col col_price">단가</div>
	      <div class="col col_amount">금액</div>
	      <div class="col col_buyer">구매자</div>
	      <div class="col col_rate">평점</div>
	      <div class="col col_status">상태</div>
	    </div>
	
	    <!-- 리스트 바디 -->
	    <div class="list_body">
	      <c:if test="${empty saleList}">
	        <div class="list_row"><div class="col empty">판매내역이 없습니다.</div></div>
	      </c:if>
	
	      <c:forEach var="row" items="${saleList}">
	        <div class="list_row">
	          <div class="col col_date"><c:out value="${row.ordersDate}"/></div>
	          <div class="col col_order">
	            <a href="${pageContext.request.contextPath}/sellerMyPage/orderDetail.se?ordersNumber=${row.ordersNumber}">
	              <c:out value="${row.orderId != null ? row.orderId : row.ordersNumber}"/>
	            </a>
	          </div>
	          <div class="col col_title"><c:out value="${row.itemName}"/></div>
	          <div class="col col_qty"><c:out value="${row.orderItemQuantity}"/></div>
	          <div class="col col_price"><c:out value="${row.itemPrice}"/></div>
	          <div class="col col_amount"><c:out value="${row.itemPrice * row.orderItemQuantity}"/></div>
	          <div class="col col_buyer"><c:out value="${row.memberId}"/></div>
	          <div class="col col_rate">
	            <c:choose>
	              <c:when test="${row.reviewRating != null}">★ <c:out value="${row.reviewRating}"/></c:when>
	              <c:otherwise>-</c:otherwise>
	            </c:choose>
	          </div>
	          <div class="col col_status">
	            <span class="tag ${row.ordersPaymentStatus == 'PAID' ? 'paid' : 'cancel'}">
	              <c:out value="${row.ordersPaymentStatus}"/>
	            </span>
	          </div>
	        </div>
	      </c:forEach>
	    </div>
	
	    <!-- 페이지네이션 (기존 구조 준수) -->
	    <div class="seller_store_info_pagination">
	      <ul class="seller_store_info_pagination_ul">
	        <c:if test="${prev}">
	          <li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySale.se?page=${startPage - 1}&q=${fn:escapeXml(param.q)}" class="prev">&lt;</a></li>
	        </c:if>
	        <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
	        <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
	          <c:choose>
	            <c:when test="${!(i == page)}">
	              <li><a class="pagination_item" href="${pageContext.request.contextPath}/sellerMyPage/todaySale.se?page=${i}&q=${fn:escapeXml(param.q)}"><c:out value="${i}" /></a></li>
	            </c:when>
	            <c:otherwise>
	              <li><a href="#" class="active"><c:out value="${i}" /></a></li>
	            </c:otherwise>
	          </c:choose>
	        </c:forEach>
	        <c:if test="${next}">
	          <li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySale.se?page=${endPage + 1}&q=${fn:escapeXml(param.q)}" class="next">&gt;</a></li>
	        </c:if>
	      </ul>

      </div> <!-- 컨텐츠 영역 끝 -->
    </div> <!-- 1100px 영역 끝 -->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>