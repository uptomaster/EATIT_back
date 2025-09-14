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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/salesHistoryList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/salesHistoryList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="sale_history_menu">
      <div class="sale_history_menu_title">마이 페이지</div>
      <ul class="sale_history_menu_list">
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

    <!-- 1100px 영영ㄱ -->
    <div class="sale_history_page">
      <!-- 페이지 네목 -->
      <h2 class="sale_history_title">판매 내역</h2>
		 <div class="container">
		    <div class="tabs">
		      <a href="${pageContext.request.contextPath}/sellerMyPage/todaySale.se">오늘 판매</a>
		      <%-- <a href="${pageContext.request.contextPath}/sellerMyPage/monthSale.se">이번달 판매</a> --%>
		      <a href="${pageContext.request.contextPath}/sellerMyPage/totalSale.se" class="active">누적 판매</a>
		    </div>
		
		    <div class="sales_summary">
		      <div class="sales_card"><div class="title">오늘 매출</div>   <div class="value"><c:out value="${summary.todayAmount}"/> 원</div></div>
 		      <div class="sales_card"><div class="title">이번 달 매출</div><div class="value"><c:out value="${summary.monthAmount}"/> 원</div></div> 
		      <div class="sales_card"><div class="title">누적 매출</div>   <div class="value"><c:out value="${summary.totalAmount}"/> 원</div></div>
		    </div>
		
<%-- 		 검색   
				<form class="sales_filters" method="get" action="${pageContext.request.contextPath}/sellerMyPage/totalSale.se">
		      <input type="date" name="from" value="${param.from}"/>
		      <span>~</span>
		      <input type="date" name="to" value="${param.to}"/>
		      <select name="status">
		        <option value="">상태 전체</option>
		        <option value="PAID" ${param.status == 'PAID' ? 'selected' : ''}>결제완료</option>
		        <option value="CANCELLED" ${param.status == 'CANCELLED' ? 'selected' : ''}>취소</option>
		      </select>
		      <input type="text" name="q" placeholder="주문번호/상품명/구매자" value="${fn:escapeXml(param.q)}"/>
		      <button type="submit" class="btn">검색</button>
		    </form> --%>
		
		    <div class="list_header">
				  <div class="col col_order">주문번호</div>
				  <div class="col col_date">주문일자</div>
				  <div class="col col_title">상품명</div>
				  <div class="col col_type">상품종류</div>
				  <div class="col col_price">단가</div>
				  <div class="col col_qty">수량</div>
				  <div class="col col_amount">총액</div>
				  <div class="col col_buyer">구매자ID</div>
				  <div class="col col_phone">구매자전화번호</div>
				  <div class="col col_rate">평점</div>
		    </div>
		
		    <!-- [NEW] 총 판매내역 바디 -->
				<div class="list_body">
				  <c:if test="${empty saleList}">
				    <div class="list_row"><div class="col empty">판매내역이 없습니다.</div></div>
				  </c:if>
				
				  <c:forEach var="row" items="${saleList}">
				    <div class="list_row">
				      <div class="col col_order">
				        <a href="${pageContext.request.contextPath}/sellerMyPage/orderDetail.se?ordersNumber=${row.ordersNumber}">
				          <c:out value="${row.ordersNumber}"/>
				        </a>
				      </div>
				      <div class="col col_date"><c:out value="${row.ordersDate}"/></div>
				      <div class="col col_title"><c:out value="${row.itemName}"/></div>
				      <div class="col col_type"><c:out value="${row.itemType}"/></div>
				      <div class="col col_price"><c:out value="${row.unitPrice}"/></div>
				      <div class="col col_qty"><c:out value="${row.quantity}"/></div>
				      <div class="col col_amount"><c:out value="${row.ordersTotalAmount}"/></div>
				      <div class="col col_buyer"><c:out value="${row.memberId}"/></div>
				      <div class="col col_phone"><c:out value="${row.memberPhoneNumber}"/></div>
				      <div class="col col_rate">
				        <c:choose>
				          <c:when test="${row.reviewRating != null}">★ <c:out value="${row.reviewRating}"/></c:when>
				          <c:otherwise>-</c:otherwise>
				        </c:choose>
				      </div>
				    </div>
				  </c:forEach>
				</div>

		
		    <div class="seller_store_info_pagination">
		      <ul class="seller_store_info_pagination_ul">
		        <c:if test="${prev}">
		          <li><a href="${pageContext.request.contextPath}/sellerMyPage/totalSale.se?page=${startPage - 1}&from=${param.from}&to=${param.to}&status=${param.status}&q=${fn:escapeXml(param.q)}" class="prev">&lt;</a></li>
		        </c:if>
		        <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
		        <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
		          <c:choose>
		            <c:when test="${!(i == page)}">
		              <li><a class="pagination_item" href="${pageContext.request.contextPath}/sellerMyPage/totalSale.se?page=${i}&from=${param.from}&to=${param.to}&status=${param.status}&q=${fn:escapeXml(param.q)}"><c:out value="${i}" /></a></li>
		            </c:when>
		            <c:otherwise><li><a href="#" class="active"><c:out value="${i}" /></a></li></c:otherwise>
		          </c:choose>
		        </c:forEach>
		        <c:if test="${next}">
		          <li><a href="${pageContext.request.contextPath}/sellerMyPage/totalSale.se?page=${endPage + 1}&from=${param.from}&to=${param.to}&status=${param.status}&q=${fn:escapeXml(param.q)}" class="next">&gt;</a></li>
		        </c:if>
		      </ul>
		    </div>
		  </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>