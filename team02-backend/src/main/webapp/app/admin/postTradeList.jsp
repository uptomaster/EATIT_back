<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/postTradeList.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/postTradeList.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
  crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="./../../app/admin/dashboard.html"><img src="./../../assets/img/admin_logo.png" alt="admin_logo" class="admin_logo"></a>
      <ul class="sidebar_ul">
        <a href="${pageContext.request.contextPath}/admin/dashboard.ad"><li id="sidebar_list_dashboard" class="sidebar_list">대쉬보드</li></a>
        <a href="${pageContext.request.contextPath}/admin/member/list.ad"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/notice/list.ad"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/report/list.ad"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/banner/list.ad"><li id="sidebar_list_banner"class="sidebar_list">배너/광고</li></a>
        <a href="${pageContext.request.contextPath}/admin/faq/list.ad"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">게시글 관리</h1>
      <div class="admin_listwrapper">
        <div class="admin_list_title">
          <ul class="admin_list">
            <a href="${pageContext.request.contextPath}/admin/postTradeList.html">
              <li id="admin_list_menu_trade" class="admin_list_menu">구매/판매</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/noticeList.html">
              <li id="admin_list_menu_notice" class="admin_list_menu">공지사항</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/boardFreeList.html">
              <li id="admin_list_menu_free" class="admin_list_menu">자유게시판</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/boardPromotionList.html">
              <li id="admin_list_menu_propaganda" class="admin_list_menu">홍보게시판</li>
            </a>
            <a href="${pageContext.request.contextPath}/admin/boardRecipeList.html">
              <li id="admin_list_menu_recipe" class="admin_list_menu">레시피</li>
            </a>
          </ul>
        </div>
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <!-- 컬럼 명 -->
            <ul class="admin_list_name">
              <li id="admin_list_num" class="admin_list_row">번호</li>
              <li id="admin_list_title" class="admin_list_row"><a href="" class="admin_list_userid_link">제목</a></li>
              <li id="admin_list_shopname" class="admin_list_row">상호명</li>
              <li id="admin_list_sellcase" class="admin_list_row">유형</li>
              <li id="admin_list_userid" class="admin_list_row">아이디</li>
              <li id="admin_list_date" class="admin_list_row">날짜</li>
            </ul>
          </div>
          <!-- 글 목록 행 -->
          <ul class="admin_list_valuebox">
            <li class="admin_list_value">
              <p id="admin_list_num_value" class="admin_list_row">1</p>
              <a href="${pageContext.request.contextPath}/admin/postTradeDetail.html" class="admin_list_userid_link"><p id="admin_list_title_value" class="admin_list_row">남는 양파 팔아요 @@</p></a>
              <p id="admin_list_shopname_value" class="admin_list_row">맛있는 고기집</p>
              <p id="admin_list_sellcase_value" class="admin_list_row">재료</p>
              <a href="${pageContext.request.contextPath}/admin/memberDetail.html" class="admin_list_userid_link"><p id="admin_list_userid_value" class="admin_list_row"><img class="grade_icon" src="./../../assets/img/씨앗.png" alt="">gogi</p></a>
              <p id="admin_list_date_value" class="admin_list_row">25-08-03</p>            
            </li>
          </ul>
        </div> 
        <!-- 페이지네이션, 검색창 -->
        <div class="admin_pagenation_search">
          <div class="admin_pagenation">
            <a href="#">
            1
           </a>
            <a href="#"> 
            2
            </a>
            <a href="#">
            3
           </a>
           <a href="#">
            4
          </a>
          </div>
          <form action="" method="get">
            <div class="admin_search">
              <!-- 태그 선택 -->
              <select class="admin_notice_category">
                <option value="notice">상호명</option>
                <option value="notice">제목</option>
                <option value="notice">유형</option>
                <option value="notice">아이디</option>
              </select>
              <input type="text" id="search_word" name="search_word">
              <button class="search_btn" type="submit">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </form>
        </div> 
      </div>  
    </div>
  </div>
</body>
</html>