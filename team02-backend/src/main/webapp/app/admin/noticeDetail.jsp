<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/noticeDetail.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/noticeDetail.css">
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
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <!-- 페이지 제목 -->
      <h1>게시글 관리</h1>
      <!-- 바깥 회색영역 -->
      <div class="admin_listwrapper">
        <!-- 안쪽 하얀영역 -->
        <div class="admin_whitebox">
          <!-- 게시글 제목영역 -->
          <div class="admin_post_titlebox">
            <div class="admin_post_tag"><p>[공지]</p></div>
            <div class="admin_post_icon"><img src="./../../assets/img/관리자.png" alt="관리자"></div>
            <div class="admin_post_userid"><p>관리자</p></div>
            <div class="admin_post_title"><p>밥세권 이용안내</p></div>
            <div class="admin_post_date"><p>(2025-08-03 22:41)</p></div>
            <div class="admin_post_view"><label for="views">조회 :</label>0</div>
            <div class="admin_post_like"><label for="likes">추천 : </label>0</div>
          </div>
          <!-- 게시글 내용 -->
          <div class="admin_post_content"><p>사기치지말고 잘쓰자</p></div>
        </div>
        <!-- 하단 버튼 영역 -->
        <div class="admin_detail_delete">
          <button type="button" id="admin_updatebtn">수 정</button>
          <button type="button" id="admin_deletebtn" >삭 제</button>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>