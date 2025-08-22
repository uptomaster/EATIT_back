<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminLogin.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/adminLogin.js"></script>
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_inner">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="${pageContext.request.contextPath}/app/admin/adminLogin.html"><img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo"></a>
    </aside>
    <!-- 로그인박스 바깥 영역 -->
    <div class="bigbox">
      <!-- 로그인박스 위쪽 -->
      <div class="smallbox"><p class="AdminLogin">Admin Login</p></div>
      <form id="admin_login_form" action="/admin/login.ad" method="post">
        <!-- 로그인박스 아래쪽 -->
      <div class="bottomWrapper">
        <!-- 아이디 패스워드 입력 영역 -->
          <div class="idPwBoxWrapper">
            <div class="idBox">
              <label for="adminId">ID</label>
              <input type="text" id="adminId" name="adminId"/>
            </div>
            <div class="pwBox">
              <label for="adminPw">Password</label>
              <input type="password" id="adminPw" name="adminPw"/>
              <p id="admin_loginfail">입력한 정보가 옳바르지 않습니다</p>
            </div>  
          </div>
          <!-- 로그인 버튼 -->
          <button type="submit" class="loginButton">Log in</button>
        </div>
      </form>
    </div>
  </div>
</body>
</html>