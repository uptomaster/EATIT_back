<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>고객센터</title>
  <link rel="stylesheet" href="./../../assets/css/community/customerServiceListGuest.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <script defer src="./../../assets/js/community/customerServiceList.js"></script>
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script>
    let header_path = '../../header.html';
    let footer_path = '../../footer.html';
  </script>
</head>

<body>
  <header id="header"></header>

  <main>
    <aside class="side_bar"></aside>

    <div class="main_container">
      <nav class="community_category">
        <ul>
          <li><a href="./../community/customer_service_list.html" id="category_customer_service" class="active">고객센터</a></li>
        </ul>
      </nav>

      <!-- 게시판 목록 가장 위 각 제목들 -->
      <section class="community_list" aria-label="고객센터 게시판 목록">
        <div class="list_header_flex_row" role="rowgroup">
          <div class="col_tag" role="columnheader">태그</div>
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>

        <!-- 게시판 한 행 역할을 감싸는 컨테이너 -->
        <div id="post_list_body" class="list_body" role="rowgroup">

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">회원가입은 어떻게 하나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-07-20</div>
            <div class="col_views" role="cell">150</div>
            <div class="col_likes" role="cell">25</div>
          </div>

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">비밀번호를 잊어버렸어요. 어떻게 하나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-07-22</div>
            <div class="col_views" role="cell">120</div>
            <div class="col_likes" role="cell">18</div>
          </div>

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">상품 배송은 얼마나 걸리나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-07-25</div>
            <div class="col_views" role="cell">90</div>
            <div class="col_likes" role="cell">10</div>
          </div>

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">주문 취소 및 환불 절차는 어떻게 되나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-07-28</div>
            <div class="col_views" role="cell">85</div>
            <div class="col_likes" role="cell">12</div>
          </div>

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">회원 탈퇴는 어떻게 하나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-07-30</div>
            <div class="col_views" role="cell">75</div>
            <div class="col_likes" role="cell">8</div>
          </div>

          <!-- 게시판 한 행 -->
          <div class="list_row_flex_row" role="row">
            <div class="col_tag" role="cell">공지</div>
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">이벤트 쿠폰은 어떻게 사용하나요?</a></div>
            <div class="col_author" role="cell">관리자</div>
            <div class="col_date" role="cell">25-08-01</div>
            <div class="col_views" role="cell">60</div>
            <div class="col_likes" role="cell">9</div>
          </div>
        </div>
      </section>

      <!-- 페이지네이션 -->
      <div class="pagination_container" id="pagination"></div>

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

    <!-- 오른쪽 사이드바 -->
    <aside class="side_bar"></aside>
  </main>

  <!-- 푸터 -->
  <footer id="footer"></footer>
</body>
</html>
