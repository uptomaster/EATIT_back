<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerwriteReview.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerwriteReview.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_write_review_menu">
      <div class="seller_write_review_menu_title">마이 페이지</div>
      <ul class="seller_write_review_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>


    <div class="seller_write_review_page"> <!--1100px영역-->
      <h2 class="seller_write_review_list">리뷰 작성</h2>
      <div>
        <div class="seller_write_review_top">
          <div class="seller_write_review_restaurant_name">가게명</div>
          <div class="seller_write_review_product_img">상품 이미지</div>
          <div class="seller_write_review_meal_name">음식/재료 명</div>
          <div class="seller_write_review_quantity">수량</div>
          <div class="seller_write_review_price">금액</div>
          <div class="seller_write_review_date">구매 일자</div>
        </div>
        <div class="seller_write_review_comments_list">
          <div class="seller_write_review_restaurant_name">기수베이커리</div>
          <div class="seller_write_review_product_img"><img class="seller_write_review_meal_img" src="" alt=""></div>
          <div class="seller_write_review_meal_name">식빵</div>
          <div class="seller_write_review_quantity">1</div>
          <div class="seller_write_review_price">5000원</div>
          <div class="seller_write_review_date">2025-08-02</div>
        </div>
        <div class="seller_write_review_set_rank">
                    <label>별점</label>
          <div class="review_rating" aria-label="별점 주기" role="radiogroup">
            <!-- 접근성을 위해 라디오를 사용하되, 시각적으로는 별만 보이도록 처리 -->
            <input class="review_star_input" type="radio" name="rating" id="rating-5" value="5" />
            <label class="review_star" for="rating-5" aria-label="5점">★</label>
            <input class="review_star_input" type="radio" name="rating" id="rating-4" value="4" />
            <label class="review_star" for="rating-4" aria-label="4점">★</label>
            <input class="review_star_input" type="radio" name="rating" id="rating-3" value="3" />
            <label class="review_star" for="rating-3" aria-label="3점">★</label>
            <input class="review_star_input" type="radio" name="rating" id="rating-2" value="2" />
            <label class="review_star" for="rating-2" aria-label="2점">★</label>
            <input class="review_star_input" type="radio" name="rating" id="rating-1" value="1" />
            <label class="review_star" for="rating-1" aria-label="1점">★</label>
          </div>
          <p class="review_rating_help" id="review_rating_text">별점을 선택해주세요</p>
        </div>
        <form action="">
          <div class="seller_write_review_form_group">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
          </div>
          <div class="seller_write_review_form_group">
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" placeholder="맛, 양, 친절도, 재구매 의사 등을 자유롭게 작성해주세요." required></textarea>
          </div>
          <div class="seller_write_review_form_group">
            <label for="imageUpload">사진 첨부</label>
            <input type="file" id="imageUpload" name="imageUpload" accept="image/*" multiple />
          </div>
          <div class="seller_write_review_button_group">
            <button type="reset" class="seller_write_review_cancel_btn">작성 취소</button>
            <button type="submit" class="seller_write_review_submit_btn">작성 완료</button>
          </div>
        </form>
      </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>