<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>밥세권 - 상품 목록</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/footer.css" />
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/orders/storeList.css" />
<script defer
    src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<link rel="shortcut icon"
    href="${pageContext.request.contextPath}/assets/img/favicon.ico"
    type="image/x-icon">
</head>
<body>

    <!-- 헤더 -->
    <jsp:include page="${pageContext.request.contextPath}/header.jsp">
        <jsp:param name="active" value="purchase" />
    </jsp:include>

    <!-- 구매 > 음식 구매 목록 -->
    <main id="buy">
        <div class="wrap">
            <div class="buy_store_list">
                <h2>
                    <c:choose>
                        <c:when test="${itemType eq 'INGREDIENT'}">재료 목록</c:when>
                        <c:otherwise>음식 구매 🥢</c:otherwise>
                    </c:choose>
                </h2>

                <!-- 검색 -->
                <form method="get"
                    action="${pageContext.request.contextPath}/orders/storeList.or">
                    <input id="buy_search" type="text" name="q" value="${q}"
                        placeholder="가게/메뉴 검색" autocomplete="off" />
                    <input type="hidden" name="itemType" value="${itemType}" />
                </form>

                <!-- 카드 리스트 -->
                <div class="buy_area">
                    <c:choose>
                        <c:when test="${empty items}">
                            <p style="color: #888">표시할 상품이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="item" items="${items}">
                                <!-- 업로드 이미지 경로 처리 -->
                                <c:choose>
                                    <c:when test="${not empty item.itemImageSystemName}">
                                        <c:url value="/upload/${item.itemImageSystemName}" var="imgUrl" />
                                    </c:when>
                                    <c:otherwise>
                                        <c:url value="/assets/img/food1.jpg" var="imgUrl" />
                                    </c:otherwise>
                                </c:choose>

                                <article class="buy_food_article">
                                    <a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}">
                                        <img src="${imgUrl}" alt="${item.itemName}" />

                                        <!-- 유통기한 뱃지 -->
                                        <c:if test="${not empty item.itemExpireDate}">
                                            <c:set var="today"
                                                value="<%=new java.text.SimpleDateFormat(\"yyyy-MM-dd\").format(new java.util.Date())%>" />
                                            <fmt:parseDate var="expireDate" value="${item.itemExpireDate}" pattern="yyyy-MM-dd" />
                                            <fmt:parseDate var="todayDate" value="${today}" pattern="yyyy-MM-dd" />
                                            <c:set var="diffDays"
                                                value="${(expireDate.time - todayDate.time) / (1000*60*60*24)}" />

                                            <c:choose>
                                                <c:when test="${diffDays le 3}">
                                                    <span class="badge urgent">마감임박</span>
                                                </c:when>
                                                <c:when test="${diffDays le 7}">
                                                    <span class="badge sale">할인추천</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge fresh">신선</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>

                                        <!-- 카드 정보 -->
                                        <div class="buy_store_info">
                                            <p class="buy_store_name">${item.businessName}</p>
                                            <p class="buy_menu_name">${item.itemName}</p>
                                            <p class="buy_item_content">
                                                <c:out value="${item.itemContent}" default="" />
                                            </p>
                                            <p class="buy_price">
                                                <fmt:formatNumber value="${item.itemPrice}" type="number" /> 원
                                            </p>
                                        </div>
                                    </a>
                                </article>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- 페이지네이션 -->
                <c:if test="${totalPages > 1}">
                    <nav class="buy_pagenation">
                        <ul>
                            <c:forEach begin="1" end="${totalPages}" var="p">
                                <li class="buy_pagenation_box ${p == page ? 'active' : ''}">
                                    <a href="${pageContext.request.contextPath}/orders/storeList.or?page=${p}&itemType=${itemType}&q=${param.q}&sort=${param.sort}">${p}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </main>

    <!-- 푸터 -->
    <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
