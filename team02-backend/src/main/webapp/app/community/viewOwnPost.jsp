<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>자신 게시글 보기</title>
  <link rel="stylesheet" href="./../../assets/css/community/viewOwnPost.css" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <link rel="stylesheet" href="./modal.css" />
  <script defer src="./../../assets/js/community/reportPostModal.js"></script>
  <script defer src="./../../assets/js/community/viewOwnPost.js"></script>
  <script defer src="./../../assets/js/community/darkmode.js"></script>
  <script defer src="./../../assets/js/community/treeGradeModal.js"></script>
  <script defer src="./../../assets/js/community/EditDeletePost.js"></script>
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script>
    let headerPath = '../../header_login.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
</head>

<body>
  <!-- 헤더 -->
  <header id="header"></header>

  <aside class="side"></aside>

  <main class="main">
    <div class="container">
      <!-- 카테고리 메뉴 -->
      <nav class="category_container">
        <ul class="category_list">
          <li class="category_item"><a href="./../community/communityMainUser.html">공지사항/이벤트</a></li>
          <li class="category_item"><a href="./../community/freeBoardList.html">자유게시판</a></li>
          <li class="category_item"><a href="./../community/promoBoardList.html">홍보게시판</a></li>
          <li class="category_item"><a href="./../community/recipeList.html">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시글 헤더 -->
      <article class="post">
        <div class="post_header">
          <span class="tag">[잡담]</span>
          <div class="author_box">
            <img class="author_profile" src="./../../assets/img/가지.png" alt="가지" />
            <span class="author_name">gisu</span>
          </div>
          <h1 class="post_title">1빠요 ㅋㅋㅋ</h1>
          <div class="post_meta">
            <time datetime="2025-08-03T14:13:02">[2025. 8. 3. 오전 12:22:53]</time>
            <span class="views">조회 12</span>
            <span class="likes">추천 0</span>
          </div>
        </div>


        <!-- 게시글 내용 -->
        <section class="content_section">
          <div class="post_content">
            ㅋㅋㅋ 내가 1빠임ㅋ
          </div>
          <div class="post_buttons">
            <div class="func_button">
              <button class="edit">수정</button>
              <button class="delete">삭제</button>
            </div>
          </div>
        </section>

        <!-- 게시글 버튼 -->
        <div class="post_buttons">
          <button class="recommend" id="recommendBtn" title="게시글 추천하기">
            <img src="./../../assets/img/like.jpg" alt="추천 버튼" />
          </button>
          <span class="recommend_count" id="recommendCount">추천 0</span>
        </div>
      </article>

      <!-- 댓글 영역 -->
      <section class="comment_section">
        <h2 class="comment_count">
          <img src="./../../assets/img/comment_box.svg" alt="댓글 아이콘" />
          댓글(1)
        </h2>

        <ul class="comment_list">
          <li class="comment_item">
            <div class="comment_profile_container">
              <img class="comment_profile" src="./../../assets/img/잎새.png" alt="잎새" />
              <div class="comment_info">
                <span class="comment_author">seojin</span>
                <time class="comment_timeline" datetime="2025-08-03T15:22">[2025-08-03 15:22]</time>
                <p class="comment_text">ㅋㅋㅋ 이 빠요 잘 와준듯</p>
              </div>
            </div>
            <div class="comment_recommend_container">
              <button class="recommend" title="댓글 추천">
                <img src="./../../assets/img/like.jpg" alt="댓글 추천 버튼" />
              </button>
              <span class="recommend_count" id="recommendCount">0</span>
            </div>
          </li>
        </ul>

        <!-- 댓글 작성 폼 -->
        <form class="comment_form" action="#" method="post">
          <img class="comment_profile" src="./../../assets/img/나무.png" alt="나무" />
          <span class="comment_author">namhyuk</span>
          <input type="text" name="comment" placeholder="댓글을 입력하세요" required />
          <button type="submit">등록</button>
        </form>
      </section>
      <button id="darkModeToggle" title="다크 모드 토글">🌓</button>
    </div>
  </main>

  <!-- 신고 모달 -->
  <div class="modal_bg" id="reportModal">
    <div class="modal">
      <h2>게시글 신고하기</h2>
      <form id="reportForm">
        <div class="report_reasons">
          <label><input type="radio" name="reason" value="스팸/광고" required> 스팸/광고</label>
          <label><input type="radio" name="reason" value="욕설/비방"> 욕설/비방</label>
          <label><input type="radio" name="reason" value="음란물"> 음란물</label>
          <label><input type="radio" name="reason" value="개인정보 노출"> 개인정보 노출</label>
          <label><input type="radio" name="reason" value="기타"> 기타</label>
        </div>
        <div class="modal_buttons">
          <button type="button" id="cancelReport">취소</button>
          <button type="submit" id="submitReport">신고하기</button>
        </div>
      </form>
    </div>
  </div>

  <!-- 나무 등급 정보 모달 -->
  <div id="treeInfoModal" class="tree_modal_bg" aria-hidden="true" role="dialog" aria-labelledby="treeInfoTitle"
    aria-modal="true">
    <div class="tree_modal" role="document">
      <button id="closeTreeInfoModal" aria-label="닫기" class="tree_modal_close_btn">&times;</button>
      <h2 id="treeInfoTitle">나무 등급 정보</h2>
      <div id="treeInfoContent"></div>
    </div>
  </div>



  <!-- 푸터 -->
  <footer id="footer">
    <!-- 푸터 중앙정렬 영역 -->
    <div id="footer_wrapper">
      <!-- 푸터 고객센터 영역 -->
      <div class="footerbox_left">
        <p class="footer_customer_center">고객행복센터</p>
        <span>1588-5890<p>월~금 오전 9시 - 오후 6시</p></span>
        <p class="footer_email">비회원 문의 : abcdefg@koreait.com</p>
        <div class="footer_sns">
          <ul>
            <li><a href="#"><img src="./../../assets/img/sns_insta.png" alt=""></a></li>
            <li><a href="#"><img src="./../../assets/img/sns_facebook.png" alt=""></a></li>
            <li><a href="#"><img src="./../../assets/img/sns_twitter.png" alt=""></a></li>
          </ul>
        </div>
      </div>
      <!-- 푸터 사업자정보 영역 -->
      <div class="footerbox_right">
        <ul>
          <li><a href="">회사소개</a></li>
          <li><a href="">인재채용</a></li>
          <li><a href="">이용약관</a></li>
          <li><a href="">개인정보처리방침</a></li>
          <li><a href="">이용안내</a></li>
        </ul>
        <p>법인명 : 주식회사 밥세권 | 사업자등록번호 : 123-12-123456</p>
        <p>주소 : 서울 강남구 테헤란로 146 | 대표이사 : 이정훈</p>
        <p>팩스 : 070-123-1234</p>
        <p> &copy; 2025 web dev team project.</p>
      </div>
    </div>
  </footer>
</body>

</html>