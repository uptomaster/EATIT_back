<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>자유게시판(비회원)</title>
  <link rel="stylesheet" href="./../../assets/css/community/freeBoardListGuest.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <link rel="stylesheet" href="./../../assets/css/community/treeGrade.css">
  <script defer src="./../../assets/js/community/freeBoardListGuest.js"></script>
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script defer src="./../../assets/js/community/gotoLogin.js"></script>
  <script defer src="./../../assets/js/community/mouseoverTreeIcon.js"></script>
  <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
</head>
<!-- dd -->

<body>
  <header id="header"></header>

  <main>
    <aside class="side_bar"></aside>

    <div class="main_container">
      <!-- 카테고리 -->
      <nav class="community_category">
        <ul>
          <li><a href="./../../app/community/communityMainGuest.html" id="category_event">공지사항/이벤트</a></li>
          <li><a href="./../../app/community/freeBoardListGuest.html" id="category_free" class="active">자유게시판</a></li>
          <li><a href="./../../app/community/promoBoardListGuest.html" id="category_advertise">홍보게시판</a></li>
          <li><a href="./../../app/community/recipeListGuest.html" id="category_recipe">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시판 목록 -->
      <section class="community_list" aria-label="게시판 목록">
        <div class="list_header flex_row" role="rowgroup">
          <div class="col_tag" role="columnheader">태그</div>
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>

        <div id="postListBody" class="list_body" role="rowgroup">
          <div class="list_row flex_row" role="row">
            <div class="col_tag" role="cell">잡담</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">최근 자유게시판 소식 안내</a></div>
            <div class="col_author" role="cell">
              kisu
              <img src="./../../assets/img/잎새.png" alt="잎새" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-03</div>
            <div class="col_views" role="cell">250</div>
            <div class="col_likes" role="cell">30</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_tag" role="cell">질문</div>
            <div class="col_title" role="cell"><a href="./../../app/community/viewOtherPost.html">자유게시판 이용 방법 문의</a>
            </div>
            <div class="col_author" role="cell">
              seojin
              <img src="./../../assets/img/씨앗.png" alt="씨앗" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-03</div>
            <div class="col_views" role="cell">180</div>
            <div class="col_likes" role="cell">22</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_tag" role="cell">이벤트참여</div>
            <div class="col_title" role="cell"><a href="./../../app/community/viewOtherPost.html">자유게시판 이벤트 참여 안내</a>
            </div>
            <div class="col_author" role="cell">
              eunkyoung
              <img src="./../../assets/img/새싹.png" alt="새싹" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-05</div>
            <div class="col_views" role="cell">150</div>
            <div class="col_likes" role="cell">40</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_tag" role="cell">건의사항</div>
            <div class="col_title" role="cell"><a href="./../../app/community/viewOtherPost.html">서버 점검 요청 건의</a></div>
            <div class="col_author" role="cell">
              namhyuk
              <img src="./../../assets/img/가지.png" alt="가지" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-07</div>
            <div class="col_views" role="cell">95</div>
            <div class="col_likes" role="cell">15</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_tag" role="cell">정보공유</div>
            <div class="col_title" role="cell"><a href="./../../app/community/viewOtherPost.html">유용한 팁 공유</a></div>
            <div class="col_author" role="cell">
              junghoon
              <img src="./../../assets/img/잎새.png" alt="잎새" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-07</div>
            <div class="col_views" role="cell">80</div>
            <div class="col_likes" role="cell">12</div>
          </div>
        </div>

      </section>

      <!-- 페이지네이션 -->
      <div class="pagination_container" id="pagination">
        <!-- 페이지 버튼 생성됨 -->
      </div>

      <!-- 검색 -->
      <div class="controls_wrapper">
        <div class="function_container">
          <div class="search_box">
            <input class="search_text" type="text" placeholder="검색어를 입력해 주세요" />
            <button class="search_btn" type="button">
              <i class="fas fa-search"></i>
            </button>
          </div>
        </div>
      </div>
    </div>

    <aside class="side_bar"></aside>
  </main>

  <footer id="footer"></footer>
</body>

</html>