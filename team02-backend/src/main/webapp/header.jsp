<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-------------------- 헤더 ------------------------>
<header id="header">
  <nav id="header_login">
    <!-- 로그인 전 네비게이션-로그인/회원가입 -->
    <ul>
      <li><a href="./../../app/login/login.html">로그인</a></li>
      <li><a href="./../../app/join/selectUserType.html">회원가입</a></li>
    </ul>
  </nav>
  <!-- 헤더 중앙정렬 영역 -->
  <div id="header_wrapper">
    <!-- 메인 로고 이미지-->
    <div id="header_logo">
      <a href="./main.html"><img src="./../webapp/assets/img/header_logo.png" alt="밥세권 로고"></a>
    </div>
    <!-- 메인 네비게이션 -->
    <nav id="header_nav">
      <ul>
        <li><a href="./../webapp/app/buy/storeList.html">구매</a></li>
        <li><a href="./../webapp/app/community/communityMainGuest.html">커뮤니티</a></li>
        <li><a href="./../webapp/app/community/customerServiceList.html">고객센터</a></li>
      </ul>
    </nav>
  </div>
  <!-- 메인 네비게이션 드롭다운 메뉴 -->
  <div id="header_nav_display">
    <div class="header_nav_dropdown">
      <ul class="header_nav_buy">
        <li><a href="./../../app/buy/storeList.html">음식점 구매</a></li>
        <li><a href="./../../app/buy/ingredientList.html">재료 구매</a></li>
      </ul>
      <ul class="header_nav_commu">
        <li><a href="./../../app/community/communityMainGuest">공지사항/이벤트</a></li>
        <li><a href="./../../app/community/freeBoardListGuest.html">자유게시판</a></li>
        <li><a href="./../../app/community/promoBoardListGuest.html">홍보게시판</a></li>
        <li><a href="./../../app/community/recipeListGuest.html">레시피공유</a></li>
      </ul>
    </div>
  </div>
</header>