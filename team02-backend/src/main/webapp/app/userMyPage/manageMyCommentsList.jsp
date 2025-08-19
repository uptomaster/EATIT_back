<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/manageMyCommentsList.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/manageMyCommentsList.js"></script>
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
    <!-- 사이드 메뉴바 -->
    <div class="managemycomments_my_page_list">
      <!-- 사이드 메뉴바 제목 -->
      <div class="managemycomments_my_page">마이 페이지</div>
      <!-- 사이드 메뉴바 상세 메뉴 -->
      <ul class="managemycomments_side_bar">
        <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
        <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
        <li  class="managemycomments_main"><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
      </ul>
    </div>
    <!-- 1100px 영역 -->
    <div class="managemycomments_page">
      <!-- 페이지 제목 -->
      <h2 class="managemycomments_list">내 댓글 목록</h2>
      <!-- 목록 제목 -->
      <div>
        <div class="managemycomments_top">
          <div class="managemycomments_sort">게시판 종류</div>
          <div class="managemycomments_tag">태그</div>
          <div class="managemycomments_title">게시글 제목</div>
          <div class="managemycomments_comments_info">댓글 내용</div>
          <div class="managemycomments_date">작성 일자</div>
          <div class="managemycomments_like_count">추천수</div>
        </div>
        <!-- 목록 예시 -->
        <div class="managemycomments_comments_list">
          <div class="managemycomments_sort">자유게시판</div>
          <div class="managemycomments_tag">[잡담]</div>
          <div class="managemycomments_title"><a href="./../../app/community/viewOwnPost.html">여기 개맛있음</a></div>
          <div class="managemycomments_comments_info">맛집ㅇㅈ</div>
          <div class="managemycomments_date">2505-08-01</div>
          <div class="managemycomments_like_count">1</div>
        </div>
        <div class="managemycomments_comments_list">
          <div class="managemycomments_sort">레시피공유</div>
          <div class="managemycomments_tag">[베이커리]</div>
          <div class="managemycomments_title"><a href="./../../app/community/viewOwnPost.html">기수바게트</a></div>
          <div class="managemycomments_comments_info">기수바게트 개꿀맛</div>
          <div class="managemycomments_date">2505-08-04</div>
          <div class="managemycomments_like_count">3</div>
        </div>
      </div>
      <!-- 페이지네이션 -->
      <div class="managemycomments_pagination">
        <a href="#" class="managemycomments_page_active">1</a>
        <a href="#" class="managemycomments_page">2</a>
        <a href="#" class="managemycomments_page">3</a>
        <a href="#" class="managemycomments_page">4</a>
        <a href="#" class="managemycomments_page">5</a>
      </div>
    </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>