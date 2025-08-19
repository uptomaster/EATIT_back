<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/blacklistList.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/blacklistList.css">
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
        <a href="./../../app/admin/dashboard.html"><li id="sidebar_list_dashboard" class="sidebar_list">대쉬보드</li></a>
        <a href="./../../app/admin/memberList.html"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="./../../app/admin/postTradeList.html"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="./../../app/admin/reportList.html"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="./../../app/admin/bannerList.html"><li id="sidebar_list_banner"class="sidebar_list">배너/광고</li></a>
        <a href="./../../app/admin/adminCustomerService.html"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">신고관리</h1>
      <div class="admin_listwrapper">
        <div class="admin_list_title">
          <ul class="admin_list">
            <a href="./../../app/admin/reportList.html">
              <li id="admin_list_menu_warning" class="admin_list_menu">신고목록</li>
            </a>
            <a href="./../../app/admin/suspendedMemberList.html">
              <li id="admin_list_menu_ban" class="admin_list_menu">정지회원목록</li>
            </a>
            <a href="./../../app/admin/blacklistList.html">
              <li id="admin_list_menu_blacklist" class="admin_list_menu">블랙리스트</li>
            </a>
          </ul>
        </div>
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <!-- 컬럼 명 -->
            <ul class="admin_list_name">
              <li id="admin_list_num" class="admin_list_row">번호</li>
              <li id="admin_list_userid" class="admin_list_row">아이디</li>
              <li id="admin_list_name" class="admin_list_row">이름</li>
              <li id="admin_list_case" class="admin_list_row">유형</li>
              <li id="admin_list_warning" class="admin_list_row">누적경고수</li>
              <li id="admin_list_blackedDate" class="admin_list_row">블랙날짜</li>
            </ul>
          </div>        
          <!-- 글 목록 행 -->
          <ul class="admin_list_valuebox">
            <li class="admin_list_value">
              <p id="admin_list_num_value" class="admin_list_row">1</p>
              <a href="./../../app/admin/memberDetail.html"><p id="admin_list_userid_value" class="admin_list_row"><img class="grade_icon" src="./../../assets/img/나무.png" alt="">gisu</p></a>
              <p id="admin_list_name_value" class="admin_list_row">김기수</p>
              <p id="admin_list_case_value" class="admin_list_row">개인</p>
              <p id="admin_list_warning_value" class="admin_list_row">3</p>
              <p id="admin_list_blackedDate_value" class="admin_list_row">25-08-09</p>           
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
                  <!-- 태그 -->
              <select class="admin_notice_category">
                <option value="notice">아이디</option>
                <option value="notice">이름</option>
                <option value="notice">유형</option>
              </select>
              <!-- 검색 영역 -->
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