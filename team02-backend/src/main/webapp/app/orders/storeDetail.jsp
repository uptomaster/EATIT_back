<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>밥세권 - 음식 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/storeDetail.css">
<script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer src="${pageContext.request.contextPath}/assets/js/orders/storeDetail.js"></script>
<style>
.no-items { text-align:center; padding:30px 0; font-size:16px; color:#888; }
</style>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="purchase" />
</jsp:include>

<c:set var="sampleImgs" value="/assets/img/food1.jpg,/assets/img/food2.jpg,/assets/img/food3.jpg,/assets/img/food4.jpg,/assets/img/food5.jpg,/assets/img/food6.jpg,/assets/img/food7.jpg,/assets/img/food8.jpg,/assets/img/food9.jpg,/assets/img/food10.jpg,/assets/img/food11.jpg,/assets/img/food12.jpg"/>
<c:set var="sampleArr" value="${fn:split(sampleImgs, ',')}"/>

<main id="buy_store_detail">
    <div class="wrap">
        <!-- 왼쪽 -->
        <div class="buy_left_area">
            <!-- 가게 정보 -->
            <div class="buy_store_info">
                <c:choose>
                    <c:when test="${not empty images}">
                        <img src="${images[0].itemImageSystemName}" alt="${item.itemName}">
                    </c:when>
                    <c:otherwise>
                        <img src="${sampleArr[item.itemNumber % fn:length(sampleArr)]}" alt="${item.itemName}">
                    </c:otherwise>
                </c:choose>
                <div class="buy_store_info_detail">
                    <p class="buy_store_name">상호명 : ${item.storeName}</p>
                    <p class="buy_store_address">주소 : ${item.storeAddress}</p>
                    <p class="buy_store_tel">전화번호 : ${item.storeTel}</p>
                </div>
            </div>

            <!-- 탭 -->
            <div class="buy_food">
                <ul class="buy_food_menu_choice">
                    <li><a class="active" href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}">음식</a></li>
                    <li><a href="${pageContext.request.contextPath}/orders/ingredientDetail.or?itemNumber=${item.itemNumber}">재료</a></li>
                </ul>

                <!-- 음식 목록 -->
                <div id="buy_food_section">
                    <c:choose>
                        <c:when test="${empty itemList}">
                            <p class="no-items">판매중인 음식이 없습니다.</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="p" items="${itemList}">
                                <div class="buy_food_menu_list">
                                    <c:choose>
                                        <c:when test="${not empty p.itemImageSystemName}">
                                            <img src="${p.itemImageSystemName}" alt="${p.itemName}">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${sampleArr[p.itemNumber % fn:length(sampleArr)]}" alt="${p.itemName}">
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="buy_each_container">
                                        <p class="buy_food_stock">재고 : ${p.itemQuantity}개</p>
                                        <p class="buy_food_menu_name">${p.itemName}</p>
                                        <div class="buy_food_stock_choice">
                                            <a href="#" class="minus">-</a><p class="count">1</p><a href="#" class="plus">+</a>
                                            <form action="${pageContext.request.contextPath}/cartList/addItemOk.cl" method="post" style="display:inline;">
                                                <input type="hidden" name="itemNumber" value="${p.itemNumber}">
                                                <input type="hidden" name="quantity" value="1" class="cartQuantity">
                                                <button type="submit" class="buy_add_cart_btn">장바구니</button>
                                            </form>
                                        </div>
                                        <div class="price_menu">가격 : <fmt:formatNumber value="${p.itemPrice}" type="number"/> 원</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                <!-- 페이지네이션 -->
                <div class="pagination">
                    <c:forEach var="i" begin="1" end="${maxPage}">
                        <a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&page=${i}" class="${i == page ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- 오른쪽 -->
        <div class="buy_map_area">
            <div class="buy_back_store_list">
                <a href="${pageContext.request.contextPath}/orders/storeList.or">목록으로 돌아가기</a>
            </div>
            <div class="buy_origin">
                <ul class="buy_origin_menu">
                    <li class="buy_origin_info"><a href="#">가게정보</a></li>
                    <li class="buy_origin_info"><a href="#">원산지</a></li>
                </ul>
                <div class="buy_origin_store_info">
                    <ul><li>사업자번호: ${item.businessNumber}</li><li>소비기한: ${item.itemExpireDate}</li></ul>
                </div>
                <div class="origin_info_inactive">
                    <ul><li>원산지: ${item.itemOrigin}</li></ul>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
