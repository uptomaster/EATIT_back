<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/dashboard.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/dashboard.css">
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="./../../app/admin/dashboard.html"><img src="./../../assets/img/admin_logo.png" alt="admin_logo" class="admin_logo"></a>
      <ul class="sidebar_ul">
        <a href="${pageContext.request.contextPath}/app/admin/dashboard.jsp"><li id="sidebar_list_dashboard" class="sidebar_list">대쉬보드</li></a>
        <a href="${pageContext.request.contextPath}/app/admin/memberList.jsp"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="${pageContext.request.contextPath}/app/admin/postTradeList.jsp"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="${pageContext.request.contextPath}/app/admin/reportList.jsp"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="${pageContext.request.contextPath}/app/admin/bannerList.jsp"><li id="sidebar_list_banner"class="sidebar_list">배너/광고</li></a>
        <a href="${pageContext.request.contextPath}/app/admin/adminCustomerService.jsp"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <div class="admin_inner">
      <!-- 페이지 제목 -->
      <h1>Dashboard</h1>
      <!-- 상단 세칸 -->
      <div class="dashboard_topWrapper">
        <div class="dashboard_topbox">
          <p class="dashboard_topbox_key">오늘 가입</p>
          <p class="dashboard_topbox_value">5</p>
        </div>
        <div class="dashboard_topbox">
          <p class="dashboard_topbox_key">총 회원 수</p>
          <p class="dashboard_topbox_value">1,250</p>
        </div>
        <div class="dashboard_topbox">
          <p class="dashboard_topbox_key">미확인 신고</p>
          <p class="dashboard_topbox_value">3</p>
        </div>
      </div>
      <!-- 그래프 -->
      <div class="dashboard_graph">
        <p class="dashboard_graph_title">구매량 지표</p>
        <img class="dashboard_graph_img" src="./../../assets/img/admin_graph.png" alt="">
      </div>
      <!-- 하단 두칸 -->
      <div class="dashboard_bottomWrapper">
        <div class="dashboard_bottombox">
          <p class="dashboard_bottombox_title">찜 순위</p>
          <ul>
            <li><p class="dashboard_bottombox_zimtop3">1등 : 맛있는 고기집</p></li>
            <li><p class="dashboard_bottombox_zimtop3">2등 : 엣헴 치킨집</p></li>
            <li><p class="dashboard_bottombox_zimtop3">3등 : 자아아 드과자집</p></li>
          </ul>
        </div>
        <div class="dashboard_bottombox">
          <p class="dashboard_bottombox_title">구매 이용 순위</p>
          <ul>
            <li><p class="dashboard_bottombox_usetop3">1등 : 김영선</p></li>
            <li><p class="dashboard_bottombox_usetop3">2등 : 짱구</p></li>
            <li><p class="dashboard_bottombox_usetop3">3등 : 철수</p></li>
          </ul>  
        </div>
      </div>
    </div>
  </div>
</body>
</html>