<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>고객센터</title>
  <link rel="stylesheet" href="./../../assets/css/community/customerServiceList.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <script defer src="./../../assets/js/community/customerServiceList.js"></script>
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script defer src="./../../assets/js/community/mouseoverTreeIcon.js"></script>
  <script>
    let header_path = '../../header_login.html';
    let footer_path = '../../footer.html';
  </script>
</head>

<body>
  <header id="header"></header>

  <main>
    <!-- 왼쪽 사이드바 -->
    <aside class="side_bar"></aside>

    <div class="main_container">

      <!-- 법적 안내 문구 -->
      <section class="legal_notice">
        <p>※ 고객센터 문의는 소비자기본법 및 식품위생법을 준수하여 처리됩니다.</p>
      </section>

      <!-- FAQ / 문의목록 탭 -->
      <nav class="community_category">
        <ul>
          <li><a href="#" id="faq_tab" class="tab_button active">자주묻는질문</a></li>
          <li><a href="#" id="inquiries_tab" class="tab_button">문의목록</a></li>
        </ul>
      </nav>

      <!-- FAQ 섹션 -->
      <section id="faq_section" class="community_list" aria-label="FAQ 게시판 목록">
        <div class="list_header_flex_row" role="rowgroup">
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>
        <div id="faq_list_body" class="list_body" role="rowgroup">
          <div class="list_row_flex_row" role="row">
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">회원가입은 어떻게 하나요?</a></div>
            <div class="col_author" role="cell">
              <img src="./../../assets/img/관리자.png" alt="관리자" class="tree_icon" />
              관리자
            </div>
            <div class="col_date" role="cell">25-07-20</div>
            <div class="col_views" role="cell">150</div>
            <div class="col_likes" role="cell">25</div>
          </div>
          <div class="list_row_flex_row" role="row">
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">상품 배송은 얼마나 걸리나요?</a></div>
            <div class="col_author" role="cell">
              <img src="./../../assets/img/관리자.png" alt="관리자" class="tree_icon" />
              관리자
            </div>
            <div class="col_date" role="cell">25-07-25</div>
            <div class="col_views" role="cell">90</div>
            <div class="col_likes" role="cell">10</div>
          </div>
        </div>
      </section>

      <!-- 문의목록 섹션 -->
      <section id="inquiries_section" class="community_list" aria-label="문의 게시판 목록" style="display:none;">
        <div class="list_header_flex_row" role="rowgroup">
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_status" role="columnheader">답변상태</div>
        </div>

        <div id="inquiry_list_body" class="list_body" role="rowgroup">
          <div class="list_row_flex_row" role="row">
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">비밀번호를 잊어버렸어요. 어떻게 하나요?</a>
            </div>
            <div class="col_author" role="cell">
              <img src="./../../assets/img/새싹.png" alt="관리자" class="tree_icon" />
              gisu
            </div>
            <div class="col_date" role="cell">25-07-22</div>
            <div class="col_status" role="cell"><span class="status received">접수</span></div>
          </div>

          <div class="list_row_flex_row" role="row">
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">판매자 불친절 관련 신고</a></div>
            <div class="col_author" role="cell">
              <img src="./../../assets/img/나무.png" alt="관리자" class="tree_icon" />
              namhyuk
            </div>
            <div class="col_date" role="cell">25-07-23</div>
            <div class="col_status" role="cell"><span class="status completed">답변완료</span></div>
          </div>

          <div class="list_row_flex_row" role="row">
            <div class="col_title" role="cell"><a href="./../community/viewOtherPost.html">앱 오류 문의</a></div>
            <div class="col_author" role="cell">
              <img src="./../../assets/img/잎새.png" alt="관리자" class="tree_icon" />
              seojin
            </div>
            <div class="col_date" role="cell">25-07-25</div>
            <div class="col_status" role="cell"><span class="status completed">답변완료</span></div>
          </div>
          
        </div>
      </section>

      <!-- 페이지네이션 -->
      <div class="pagination_container" id="pagination"></div>

      <!-- 검색 + 글쓰기 -->
      <div class="controls_wrapper">
        <div class="function_container">
          <div class="search_box">
            <input class="search_text" type="text" placeholder="검색어를 입력해 주세요" />
            <button class="search_btn" type="button">
              <i class="fas fa-search"></i>
            </button>
            <div class="write_form">
              <a href="./../community/writeCustomerService.html" id="writeBtn" class="member-only">글쓰기</a>
            </div>
          </div>
        </div>
      </div>

    </div>

    <!-- 오른쪽 사이드바 -->
    <aside class="side_bar"></aside>
  </main>

  <footer id="footer"></footer>

  <script>
    // 탭 전환 기능
    document.addEventListener("DOMContentLoaded", () => {
      const faqTab = document.getElementById("faq_tab");
      const inquiriesTab = document.getElementById("inquiries_tab");
      const faqSection = document.getElementById("faq_section");
      const inquiriesSection = document.getElementById("inquiries_section");

      faqTab.addEventListener("click", e => {
        e.preventDefault();
        faqSection.style.display = "block";
        inquiriesSection.style.display = "none";
        faqTab.classList.add("active");
        inquiriesTab.classList.remove("active");
      });

      inquiriesTab.addEventListener("click", e => {
        e.preventDefault();
        faqSection.style.display = "none";
        inquiriesSection.style.display = "block";
        inquiriesTab.classList.add("active");
        faqTab.classList.remove("active");
      });
    });
  </script>

</body>