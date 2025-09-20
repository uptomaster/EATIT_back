<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>밥세권 - 찜 목록</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/myFavorite.css">
  <script defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>

<body>
  <!-- 헤더 -->
  <jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="wishlist" />
  </jsp:include>

  <!-- 찜 목록 -->
  <main id="myFavorite">
    <div class="wrap">
      <div class="favorite_store_list">
        <h2>📌 나의 찜한 가게</h2>

        <!-- 가게 리스트 -->
        <div class="favorite_area">
          <c:choose>
            <c:when test="${not empty favoriteStoreList}">
              <c:forEach var="store" items="${favoriteStoreList}">
                <article class="favorite_article">
                  <!-- 가게 상세 이동 -->
                  <a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${store.itemNumber}">
                    <div class="favorite_img_wrapper">
                      <c:choose>
                        <c:when test="${not empty store.storeImageSystemName}">
                          <img src="${pageContext.request.contextPath}/upload/${store.storeImageSystemName}" 
                               alt="${store.storeName}">
                        </c:when>
                        <c:otherwise>
                          <img src="${pageContext.request.contextPath}/assets/img/food1.jpg" alt="기본 이미지">
                        </c:otherwise>
                      </c:choose>
                    </div>
                    <div class="favorite_store_info">
                      <p class="favorite_store_name">${store.storeName}</p>
                      <p class="favorite_open_time">
                        영업시간 
                        <c:out value="${store.openTime}" /> ~ 
                        <c:out value="${store.closeTime}" />
                      </p>
                    </div>
                  </a>
                  <!-- 찜 해제 버튼 -->
                  <form action="${pageContext.request.contextPath}/orders/favoriteToggle.or" method="get" class="favorite_remove_form">
                    <input type="hidden" name="storeNumber" value="${store.businessNumber}">
                    <button type="submit" class="remove_btn">
                      <i class="fa-solid fa-heart"></i> 찜 해제
                    </button>
                  </form>
                </article>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <p class="no-favorites">찜한 가게가 없습니다.</p>
            </c:otherwise>
          </c:choose>
        </div>

        <!-- 페이지네이션 -->
        <div class="favorite_pagination" id="pagination">
          <c:forEach var="i" begin="1" end="${maxPage}">
            <a href="${pageContext.request.contextPath}/orders/myFavorite.or?page=${i}" 
               class="${i == page ? 'active' : ''}">${i}</a>
          </c:forEach>
        </div>
      </div>
    </div>
  </main>

  <!-- 푸터 -->
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp"></jsp:include>
</body>
</html>
