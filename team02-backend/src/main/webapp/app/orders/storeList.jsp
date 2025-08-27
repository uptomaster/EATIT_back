<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
</head>
<body>

    <!-------------------- 헤더 ------------------------>
    <jsp:include page="${pageContext.request.contextPath}/header.jsp">
        <jsp:param name="active" value="purchase" />
    </jsp:include>

    <div id="buy">
        <section class="buy_store_list">
            <h2>
                <c:choose>
                    <c:when test="${itemType eq 'INGREDIENT'}">재료 목록</c:when>
                    <c:otherwise>음식 목록</c:otherwise>
                </c:choose>
            </h2>

            <!-- 검색 -->
            <form method="get"
                action="${pageContext.request.contextPath}/orders/storeList.or">
                <input id="buy_search" type="text" name="q" value="${q}"
                    placeholder="가게/메뉴 검색" /> 
                <input type="hidden" name="itemType" value="${itemType}" />
            </form>

            <!-- 정렬 -->
            <ul class="buy_array">
                <li><a
                    href="${pageContext.request.contextPath}/orders/storeList.or?itemType=${itemType}&q=${param.q}&sort=recent">최신순</a></li>
                <li><a
                    href="${pageContext.request.contextPath}/orders/storeList.or?itemType=${itemType}&q=${param.q}&sort=priceAsc">가격↑</a></li>
                <li><a
                    href="${pageContext.request.contextPath}/orders/storeList.or?itemType=${itemType}&q=${param.q}&sort=priceDesc">가격↓</a></li>
            </ul>

            <!-- 카드 리스트 -->
            <div class="buy_area">
                <c:choose>
                    <c:when test="${empty items}">
                        <p style="color: #888">표시할 상품이 없습니다.</p>
                    </c:when>
                    <c:otherwise>
                        <!-- ✅ 더미 이미지 12개 세팅 -->
                        <c:set var="sampleImgs" value="/assets/img/sample1.jpg,/assets/img/sample2.jpg,/assets/img/sample3.jpg,/assets/img/sample4.jpg,/assets/img/sample5.jpg,/assets/img/sample6.jpg,/assets/img/sample7.jpg,/assets/img/sample8.jpg,/assets/img/sample9.jpg,/assets/img/sample10.jpg,/assets/img/sample11.jpg,/assets/img/sample12.jpg" />
                        <c:forEach var="item" items="${items}">
                            <c:set var="sampleArr" value="${fn:split(sampleImgs, ',')}" />
                            <c:set var="sampleImg" value="${sampleArr[item.itemNumber % fn:length(sampleArr)]}" />

                            <article class="buy_food_article">
                                <a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}">
                                    <img
                                        src="${empty item.itemImageSystemName ? pageContext.request.contextPath + sampleImg : item.itemImageSystemName}"
                                        alt="${item.itemName}" />
                                    <div class="buy_store_info">
                                        <p class="buy_store_name">상호명 : ${item.businessName}</p>
                                        <p class="buy_menu_name">${item.itemName}</p>
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
                                <a
                                href="${pageContext.request.contextPath}/orders/storeList.or?page=${p}&itemType=${itemType}&q=${param.q}&sort=${param.sort}">${p}</a>
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
