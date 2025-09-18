<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/buy/wishList.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/buy/wishList.js"></script>
  <title>밥세권 - 찜 목록</title>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
</head>

<body>
  <!-- 헤더 -->
  <jsp:include page="${pageContext.request.contextPath}/header.jsp">
      <jsp:param name="active" value="wishlist" />
  </jsp:include>

  <!-- 구매>찜한 가게 목록 -->
  <main id="buy">
    <div class="wrap">
      <div class="buy_store_list">
        <h2>📌 나의 찜 모음</h2>

        <!-- 가게 리스트 -->
        <div class="buy_area">
          <c:choose>
            <c:when test="${not empty favoriteStoreList}">
              <c:forEach var="store" items="${favoriteStoreList}">
                <article class="buy_food_article">
                  <a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${store.itemNumber}">
                    <div class="wish_img_wrapper">
                      <c:choose>
                        <c:when test="${not empty store.storeImageSystemName}">
                          <img src="${pageContext.request.contextPath}/upload/${store.storeImageSystemName}" alt="${store.storeName}">
                        </c:when>
                        <c:otherwise>
                          <img src="${pageContext.request.contextPath}/assets/img/food1.jpg" alt="기본 이미지">
                        </c:otherwise>
                      </c:choose>
                      <!-- 찜 아이콘 -->
                      <img src="${pageContext.request.contextPath}/assets/img/heart_active.png" alt="찜" class="heart_icon">
                    </div>
                    <div class="buy_store_info">
                      <p class="wish_store_name">${store.storeName}</p>
                      <p class="wish_open_time">영업시간 ${store.openTime}~${store.closeTime}</p>
                    </div>
                  </a>
                </article>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <p class="no-favorites">찜한 가게가 없습니다.</p>
            </c:otherwise>
          </c:choose>
        </div>

        <!-- 페이지네이션 -->
        <div class="buy_pagenation" id="pagination">
          <c:forEach var="i" begin="1" end="${maxPage}">
            <a href="${pageContext.request.contextPath}/wishlist/list.or?page=${i}" class="${i == page ? 'active' : ''}">${i}</a>
          </c:forEach>
        </div>
      </div>
    </div>
  </main>

  <!-- 푸터 -->
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
</body>
</html>
