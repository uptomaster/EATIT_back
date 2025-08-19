<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/sellerMyPage/sellerMyPostsList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/sellerMyPage/sellerMyPostsList.js"></script>
  <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_myposts_menu">
      <div class="seller_myposts_menu_title">마이 페이지</div>
      <ul class="seller_myposts_menu_list">
        <li><a href="./../sellerMyPage/editSellerInfo.html">내 정보 수정</a></li>
        <li><a href="./../sellerMyPage/sellerfoodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../sellerMyPage/selleringredientPurchaseList.html">재료 구매 내역</a></li>
        <li class="seller_myposts_menu_list_current"><a href="./../sellerMyPage/sellerMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../sellerMyPage/sellerMyReviewsList.html">내 리뷰 관리</a></li>
        <li><a href="./../sellerMyPage/storeInfo.html">사업장 관리</a></li>
        <li><a href="./../sellerMyPage/todaySaleList.html">판매 내역</a></li>
      </ul>
    </div>


    <div class="seller_myposts_page">
      <h2 class="seller_myposts_list">내 글 관리</h2>
      <div>
        <div class="seller_myposts_top">
          <div class="seller_myposts_sort">게시판 종류</div>
          <div class="seller_myposts_tag">태그</div>
          <div class="seller_myposts_title">글 제목</div>
          <div class="seller_myposts_date">게시일</div>
          <div class="seller_myposts_comments_count">댓글수</div>
          <div class="seller_myposts_like_count">추천수</div>
        </div>
        <div class="seller_myposts_comments_list">
          <div class="seller_myposts_sort">자유게시판</div>
          <div class="seller_myposts_tag">[잡담]</div>
          <div class="seller_myposts_title"><a href="./../../app/community/viewOwnPost.html">이집 개추요ㅋㅋㅋ</a></div>
          <div class="seller_myposts_date">2025-07-31</div>
          <div class="seller_myposts_comments_count">5</div>
          <div class="seller_myposts_like_count">10</div>
        </div>
        <div class="seller_myposts_comments_list">
          <div class="seller_myposts_sort">래시피공유</div>
          <div class="seller_myposts_tag">[베이커리]</div>
          <div class="seller_myposts_title"><a href="./../../app/community/viewOwnPost.html">기수바게트 만들기</a></div>
          <div class="seller_myposts_date">2025-08-01</div>
          <div class="seller_myposts_comments_count">2</div>
          <div class="seller_myposts_like_count">3</div>
        </div>
      </div>
      <div class="seller_myposts_pagination">
        <a href="#" class="seller_myposts_page_active">1</a>
        <a href="#" class="seller_myposts_page">2</a>
        <a href="#" class="seller_myposts_page">3</a>
        <a href="#" class="seller_myposts_page">4</a>
        <a href="#" class="seller_myposts_page">5</a>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>