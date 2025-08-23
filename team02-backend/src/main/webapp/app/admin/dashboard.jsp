<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>

  <!-- 정적 리소스로 컨텍스트경로 로드 -->
  <link rel="stylesheet" href="<c:url value='/assets/css/admin/dashboard.css'/>">
  <script defer src="<c:url value='/assets/js/admin/dashboard.js'/>"></script>
</head>
<body>
  <!-- ADMIN 아니면 로그인으로 -->
  <c:if test="${sessionScope.memberType ne 'ADMIN'}">
    <c:redirect url="/admin/login.ad"/>
  </c:if>

  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 로고 클릭 시 컨트롤러 경로로 -->
      <a href="<c:url value='/admin/dashboard.ad'/>">
        <img src="<c:url value='/assets/img/admin_logo.png'/>" alt="admin_logo" class="admin_logo">
      </a>

      <!-- 사이드 메뉴쪽 a태그 전부 *.ad 경로로 교체함 -->
      <ul class="sidebar_ul">
        <li class="sidebar_list" id="sidebar_list_dashboard">
          <a href="<c:url value='/admin/dashboard.ad'/>">대시보드</a>
        </li>

        <!-- (회원관리/게시글관리 컨트롤러) -->
		<li class="sidebar_list"><a href="#">회원관리</a></li>
		<li class="sidebar_list"><a href="#">게시글 관리</a></li> 

        <li class="sidebar_list" id="sidebar_list_warning">
          <a href="<c:url value='/admin/report/list.ad'/>">신고관리</a>
        </li>
        <li class="sidebar_list" id="sidebar_list_banner">
          <a href="<c:url value='/admin/banner/list.ad'/>">배너/광고</a>
        </li>
        <li class="sidebar_list" id="sidebar_list_customerservice">
          <a href="<c:url value='/admin/inquiry/list.ad'/>">고객센터</a>
        </li>
        <li class="sidebar_list">
          <a href="<c:url value='/admin/notice/list.ad'/>">공지</a>
        </li>
        <li class="sidebar_list">
          <a href="<c:url value='/admin/faq/list.ad'/>">FAQ</a>
        </li>
        <li class="sidebar_list">
          <a href="<c:url value='/admin/suspend/list.ad'/>">정지 회원</a>
        </li>
        <li class="sidebar_list">
          <a href="<c:url value='/admin/blacklist/list.ad'/>">블랙리스트</a>
        </li>
      </ul>

      <form action="<c:url value='/admin/logoutOk.ad'/>" method="post" style="margin-top:12px;">
        <button type="submit" id="admin_logoutbtn">로그아웃</button>
      </form>
      
    </aside>

    <!-- 본문 -->
    <div class="admin_inner">
      <h1>Dashboard</h1>

      <!-- 상단 박스 3개 (샘플 값) -->
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
        <img class="dashboard_graph_img" src="<c:url value='/assets/img/admin_graph.png'/>" alt="graph">
      </div>

      <!-- 하단 2칸 -->
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
