<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>밥세권 - 가게 리뷰</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/storeDetail.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="purchase"/>
</jsp:include>

<main id="buy_store_detail">
    <div class="wrap">

        <!-- 왼쪽 영역 -->
        <div class="buy_left_area">

            <!-- 가게 정보 -->
            <div class="buy_store_info">
                <c:choose>
                    <c:when test="${not empty reviews and not empty reviews[0].storeImageSystemName}">
                        <img src="${pageContext.request.contextPath}/upload/store/${reviews[0].storeImageSystemName}" 
                             alt="${reviews[0].storeImageOriginalName}">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/assets/img/store_default.png" alt="기본 이미지">
                    </c:otherwise>
                </c:choose>

                <div class="buy_store_info_detail">
                    <h2 class="buy_store_name">${storeName}</h2>
                    <p class="buy_store_address">주소 : ${storeAddress}</p>
                    <p class="buy_store_tel">전화번호 : ${storeTel}</p>
                    <p class="buy_store_bn">사업자번호 : ${businessNumber}</p>

                    <!-- 평균 별점 & 리뷰 개수 -->
                    <c:if test="${totalCount > 0}">
                        <p class="buy_store_avg_rating">
                            <span class="stars">
                                <c:forEach begin="1" end="5" var="i">
                                    <c:choose>
                                        <c:when test="${i <= avgRating}">⭐</c:when>
                                        <c:when test="${i - avgRating < 1 && i > avgRating}">⭐</c:when>
                                        <c:otherwise>☆</c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </span>
                            <span class="rating_text">
                                평균 <fmt:formatNumber value="${avgRating}" pattern="0.0"/> / 5.0
                                (리뷰 개수 : <c:out value="${totalCount}"/>개)
                            </span>
                        </p>
                    </c:if>
                </div>
            </div>

            <!-- 메뉴/재료/리뷰 탭 -->
            <div class="buy_food">
                <ul class="buy_food_menu_choice">
                    <li><a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=1&tab=FOOD">음식</a></li>
                    <li><a href="${pageContext.request.contextPath}/orders/ingredientDetail.or?itemNumber=1&tab=INGREDIENT">재료</a></li>
                    <li><a class="active" href="#">리뷰</a></li>
                </ul>

                <!-- 리뷰 리스트 -->
                <c:choose>
                    <c:when test="${empty reviews}">
                        <p style="padding:20px; text-align:center;">등록된 리뷰가 없습니다.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="r" items="${reviews}">
                            <div class="buy_review_area">
                                <div class="buy_review_detail">
                                    <div class="buy_review_detail_top">
                                        <span class="buy_review_id">${r.memberId}</span>
                                        <span class="buy_review_star">
                                            <c:forEach begin="1" end="5" var="i">
                                                <c:choose>
                                                    <c:when test="${i <= r.reviewRating}">⭐</c:when>
                                                    <c:otherwise>☆</c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </span>
                                        <span class="buy_review_date">${r.reviewCreateDate}</span>
                                    </div>
                                    <p class="buy_review_item">${r.itemName}</p>
                                    <p class="buy_review_content">${r.reviewContent}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

                <!-- 페이지네이션 -->
                <div class="pagination_container">
                    <c:forEach var="i" begin="1" end="${maxPage}">
                        <a href="${pageContext.request.contextPath}/orders/storeReview.or?businessNumber=${businessNumber}&page=${i}"
                           class="page ${i == page ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- 오른쪽 영역 -->
        <div class="buy_map_area">
            <div class="buy_back_store_list">
                <a href="${pageContext.request.contextPath}/orders/storeList.or">가게 목록으로 돌아가기</a>
            </div>
            <div class="buy_origin">
                <ul class="buy_origin_menu">
                    <li class="buy_origin_info"><a href="#">가게정보</a></li>
                    <li class="buy_origin_info"><a href="#">원산지</a></li>
                </ul>
                <div class="buy_origin_store_info">
                    <ul class="buy_origin_content">
                        <li>사업자번호: ${businessNumber}</li>
                        <li>주소: ${storeAddress}</li>
                        <li>전화번호: ${storeTel}</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>

</body>
</html>
