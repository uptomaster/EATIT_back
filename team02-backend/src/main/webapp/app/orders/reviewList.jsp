<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <!-- 헤더 -->
  <jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="purchase" />
  </jsp:include>

  <main id="buy_store_detail">
    <div class="wrap">
      <!-- 왼쪽 영역 -->
      <div class="buy_left_area">
        <!-- 가게 기본 정보 -->
        <div class="buy_store_info">
          <div class="store_image_wrapper">
            <img src="${pageContext.request.contextPath}/assets/img/store_default.jpg" alt="가게 이미지">
            <!-- 찜 버튼 -->
            <button class="favorite-btn" data-favorite="false">
              <i class="fa-regular fa-heart"></i>
            </button>
          </div>
          <div class="buy_store_info_detail">
            <p class="buy_store_name">가게 리뷰 페이지</p>
            <p class="buy_store_address">사업자번호: ${businessNumber}</p>
          </div>
        </div>

        <!-- 탭 -->
        <div class="buy_food">
          <ul class="buy_food_menu_choice">
            <li><a href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${param.itemNumber}">음식</a></li>
            <li><a href="${pageContext.request.contextPath}/orders/ingredientDetail.or?itemNumber=${param.itemNumber}">재료</a></li>
            <li><a class="active" href="#">리뷰</a></li>
          </ul>

          <!-- 리뷰 영역 -->
          <div class="buy_review_area_wrapper">
            <c:choose>
              <c:when test="${empty reviews}">
                <p class="no_reviews">등록된 리뷰가 없습니다.</p>
              </c:when>
              <c:otherwise>
                <c:forEach var="review" items="${reviews}">
                  <div class="buy_review_area">
                    <!-- 리뷰 사진 (없으면 기본 이미지) -->
                    <img src="${pageContext.request.contextPath}/assets/img/food1.jpg"
                         alt="리뷰 이미지"
                         class="buy_review_img">

                    <div class="buy_review_detail">
                      <div class="buy_review_detail_top">
                        <ul class="buy_review_user_info">
                          <li class="buy_review_id">${review.memberId}</li>
                          <li class="buy_review_star">
                            <c:forEach var="i" begin="1" end="5">
                              <c:choose>
                                <c:when test="${i <= review.reviewRating}">
                                  <img src="${pageContext.request.contextPath}/assets/img/counting_Star.png" alt="별">
                                </c:when>
                                <c:otherwise>
                                  <img src="${pageContext.request.contextPath}/assets/img/empty_Star.png" alt="빈별">
                                </c:otherwise>
                              </c:choose>
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
              <li>전화번호: 02-000-0000</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </main>

  <!-- 푸터 -->
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

  <!-- 찜 버튼 토글 -->
  <script>
  document.addEventListener("DOMContentLoaded", () => {
    const favBtn = document.querySelector(".favorite-btn");
    const favIcon = favBtn.querySelector("i");

    favBtn.addEventListener("click", () => {
      const isFav = favBtn.dataset.favorite === "true";
      if (isFav) {
        favBtn.dataset.favorite = "false";
        favIcon.classList.remove("fa-solid");
        favIcon.classList.add("fa-regular");
      } else {
        favBtn.dataset.favorite = "true";
        favIcon.classList.remove("fa-regular");
        favIcon.classList.add("fa-solid");
      }
    });
  });
  </script>
</body>
</html>
