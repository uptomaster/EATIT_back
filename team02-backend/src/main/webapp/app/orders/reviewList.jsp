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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/reviewList.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>
<body>

  <jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="purchase" />
  </jsp:include>

  <main id="buy_store_detail">
    <div class="wrap">
      <!-- 왼쪽 영역 -->
      <div class="buy_left_area">
        <div class="buy_store_info">
          <img src="${pageContext.request.contextPath}/assets/img/store_default.jpg" alt="가게 이미지">
          <div class="buy_store_info_detail">
            <p class="buy_store_name">${store.storeName}</p>
            <p class="buy_store_address">사업자번호: ${store.businessNumber}</p>
            <p class="buy_store_tel">전화번호: ${store.storeTel}</p>
          </div>
        </div>

        <!-- 리뷰 탭 -->
        <div class="buy_food">
          <ul class="buy_food_menu_choice">
            <li><a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${param.itemNumber}&tab=FOOD">음식</a></li>
            <li><a href="${pageContext.request.contextPath}/orders/ingredientDetail.or?itemNumber=${param.itemNumber}&tab=INGREDIENT">재료</a></li>
            <li><a class="active" href="#">리뷰</a></li>
          </ul>

          <c:choose>
            <c:when test="${empty reviews}">
              <div class="no_reviews">아직 등록된 리뷰가 없습니다.</div>
            </c:when>
            <c:otherwise>
              <c:forEach var="review" items="${reviews}">
                <div class="buy_review_area">
                  <img src="${pageContext.request.contextPath}/assets/img/food1.jpg" alt="리뷰 이미지" class="buy_review_img">
                  <div class="buy_review_detail">
                    <div class="buy_review_detail_top">
                      <ul class="buy_review_user_info">
                        <li class="buy_review_id">${review.memberId}</li>
                        <li class="buy_review_star">
                          <c:forEach var="i" begin="1" end="${review.reviewRating}">
                            <img src="${pageContext.request.contextPath}/assets/img/counting_Star.png" alt="★">
                          </c:forEach>
                        </li>
                      </ul>
                      <ul class="buy_review_dates">
                        <li class="buy_review_write_date">작성일 ${review.reviewCreateDate}</li>
                      </ul>
                    </div>
                    <h4>[${review.itemName}]</h4>
                    <p>${review.reviewContent}</p>
                  </div>
                </div>
              </c:forEach>
            </c:otherwise>
          </c:choose>
        </div>
      </div>

      <!-- 오른쪽 영역 -->
      <div class="buy_map_area">
        <div class="buy_back_store_list">
          <a href="${pageContext.request.contextPath}/orders/storeList.or">가게 목록으로 돌아가기</a>
        </div>
      </div>
    </div>
  </main>

  <jsp:include page="${pageContext.request.contextPath}/footer.jsp"/>
</body>
</html>
