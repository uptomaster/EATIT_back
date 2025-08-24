<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Admin Login</title>

  <!-- 정적 리소스 -->
  <link rel="stylesheet" href="<c:url value='/assets/css/admin/adminLogin.css'/>">
<%--   <script defer src="<c:url value='/assets/js/admin/adminLogin.js'/>"></script> --%>
</head>
<body>
  <!-- 회색 영역 -->
  <div class="admin_inner">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고: 컨트롤러 경로로 이동 -->
      <a href="<c:url value='/admin/login.ad'/>">
        <img src="<c:url value='/assets/img/admin_logo.png'/>" alt="관리자 페이지 로고">
      </a>
    </aside>

    <!-- 로그인 박스 바깥 영역 -->
    <div class="bigbox">
      <!-- 로그인 박스 상단 -->
      <div class="smallbox">
        <p class="AdminLogin">Admin Login</p>
      </div>

      <!-- 로그인 폼 -->
		<form id="admin_login_form" action="${pageContext.request.contextPath}/admin/loginOk.ad" method="post">
        <!-- 로그인 박스 하단 -->
        <div class="bottomWrapper">
          <!-- 아이디/비밀번호 입력 -->
          <div class="idPwBoxWrapper">
            <div class="idBox">
              <label for="adminId">ID</label>
              <input type="text" id="adminId" name="adminId" value="${fn:escapeXml(inputAdminId)}" placeholder="관리자 아이디" required autocomplete="username" />
            </div>

            <div class="pwBox">
              <label for="adminPw">Password</label>
              <input type="password" id="adminPw" name="adminPw" placeholder="비밀번호" requiredautocomplete="current-password" />

              <!-- 에러 메시지: 기본은 숨김, 서버에서 loginError 내려오면 노출 -->
              <p id="admin_loginfail" class="error"
                 style="display:${empty loginError ? 'none' : 'block'};">
                <c:out value="${loginError}" />
              </p>
            </div>
          </div>

          <!-- 로그인 버튼 -->
          <button type="submit" class="loginButton">Log in</button>
        </div>
      </form>
    </div>
  </div>

  <noscript>
    <p style="color:#c00; padding:12px;">이 페이지는 자바스크립트가 필요합니다. 브라우저 설정에서 자바스크립트를 활성화하세요.</p>
  </noscript>
</body>
</html>
