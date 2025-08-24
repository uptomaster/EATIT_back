<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/assets/img/placeholder.png" var="ph"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>밥세권 - 음식점 목록</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/storeList.css" />
</head>
<body>

  <!-------------------- 헤더 ------------------------>
<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <jsp:param name="active" value="purchase"/>

<div id="buy">
  <section class="buy_store_list">
    <h2>음식 목록</h2>

    <!-- 검색 -->
    <form method="get" action="$${pageContext.request.contextPath}/app/orders/storeList.jsp">
      <input id="buy_search" type="text" name="q" value="${param.q}" placeholder="가게/메뉴 검색" />
    </form>

    <!-- 정렬 -->
    <ul class="buy_array">
      <li><a href="$${pageContext.request.contextPath}/app/orders/storeList.jsp?q=${param.q}&sort=recent">최신순</a></li>
      <li><a href="$${pageContext.request.contextPath}/app/orders/storeList.jsp?q=${param.q}&sort=distance">거리순</a></li>
      <li><a href="$${pageContext.request.contextPath}/app/orders/storeList.jsp?q=${param.q}&sort=priceAsc">가격↑</a></li>
      <li><a href="$${pageContext.request.contextPath}/app/orders/storeList.jsp?q=${param.q}&sort=priceDesc">가격↓</a></li>
    </ul>

    <!-- 카드 리스트 -->
    <div class="buy_area">
      <c:choose>
        <c:when test="${empty foodList}">
          <p style="color:#888">표시할 음식이 없습니다.</p>
        </c:when>
        <c:otherwise>
          <c:forEach var="food" items="${foodList}">
            <article class="buy_food_article">
              <a href="${pageContext.request.contextPath}/app/orders/storeDetail.jsp?businessNumber=${food.businessNumber}&itemNumber=${food.itemNumber}">
                <img src="${empty food.imagePath ? ph : food.imagePath}" alt="${food.itemName}"/>
                <div class="buy_store_info">
                  <p class="buy_store_name">${food.businessName}</p>
                  <p class="buy_menu_name">${food.itemName}</p>
                  <p class="buy_open_time">
                    <c:out value="${food.openHours}" default="영업시간 정보 없음"/>
                    <span class="buy_price"><fmt:formatNumber value="${food.itemPrice}" type="number"/>원</span>
                  </p>
                </div>
              </a>
            </article>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </div>

    <!-- 페이지네이션 (page, totalPages를 컨트롤러에서 세팅했다고 가정) -->
    <c:if test="${totalPages > 1}">
      <nav class="buy_pagenation">
        <ul>
          <c:forEach begin="1" end="${totalPages}" var="p">
            <li class="buy_pagenation_box ${p == page ? 'active' : ''}">
              <a href="${pageContext.request.contextPath}/app/orders/storeList.jsp?page=${p}&q=${param.q}&sort=${param.sort}">${p}</a>
            </li>
          </c:forEach>
        </ul>
      </nav>
    </c:if>

  </section>
</div>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

</body>
</html>
