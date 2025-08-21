<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/writeReview.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/writeReview.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
  <main>
    <div class="writereview_my_page_list">
      <ul class="writereview_my_page">마이 페이지</ul>
      <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
      <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>
      <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
      <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
      <li><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
      <li class=><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
    </div>
    <div class="writereview_page">
      <h2 class="writereview_list">리뷰 작성</h2>
      <div>
        <div class="writereview_top">
          <div class="writereview_restaurant_name">가게명</div>
          <div class="writereview_product_img">상품 이미지</div>
          <div class="writereview_meal_name">음식/재료 명</div>
          <div class="writereview_quantity">수량</div>
          <div class="writereview_price">금액</div>
          <div class="writereview_date">구매 일자</div>
        </div>
        <div class="writereview_comments_list">
          <div class="writereview_restaurant_name">기수베이커리</div>
          <div class="writereview_product_img"><img class="writereview_meal_img" src="" alt=""></div>
          <div class="writereview_meal_name">식빵</div>
          <div class="writereview_quantity">1</div>
          <div class="writereview_price">5000원</div>
          <div class="writereview_date">2025-08-02</div>
        </div>
        <div class="writereview_set_rank">
          <div>별점주기</div>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="./../../assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="./../../assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="./../../assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="./../../assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
          <button class="writereview_counting_Star_button">
            <img class="writereview_counting_Star" src="./../../assets/img/gray_shake_it_ya.png" alt="별점">
          </button>
        </div>
        <form action="">
          <div class="writereview_form_group">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
          </div>
          <div class="writereview_form_group">
            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
          </div>
          <div class="writereview_form_group">
            <label for="imageUpload">사진 첨부</label>
            <input type="file" id="imageUpload" name="imageUpload" accept="image/*" multiple />
          </div>
          <div class="writereview_button_group">
            <button type="reset" class="writereview_cancel_btn">작성 취소</button>
            <button type="submit" class="writereview_submit_btn">작성 완료</button>
          </div>
        </form>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>