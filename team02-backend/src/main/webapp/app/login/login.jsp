<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login/login.css"/>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/login/login.js"></script>
  <title>밥세권</title>
</head>
<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
<body>
  <header id="header"></header>
  <main>
    <div class="login_container"> <!-- 1100px 영역 -->
      <div class="login_content_container"> <!-- 컨텐츠 영역 -->

        <div>
          <!--로그인 제목 -->
          <div class="login_title">밥세권 회원</div>
          <div class="login_subtitle">로그인</div>
        </div>
        <!--로그인 입력 폼 -->
        <form action="/login/loginOk.lo" method="post" id="login_form" class="login_input_container">
          <div class="login_input_id_contanier">
            <label for="login_input_id">아이디 : </label>
            <input type="text" name="login_input_id" id="login_input_id" placeholder="아이디를 입력해주세요">
          </div>
          <div class="login_input_pw_contaner">
            <label for="login_input_pw">비밀번호 : </label>
            <input type="password" name="login_input_pw" id="login_input_pw" placeholder="비밀번호를 입력해주세요.">
          </div>
          <!-- 경고메시지 -->
          <p id="login_warning_message">아이디 혹은 비밀번호를 다시 확인해주세요</p>
          <button type="submit" id="login_btn">로그인</button>
        </form>
        <!-- 로그인 버튼 -->
        <!-- 아이디 찾기,비밀번호 찾기, 회원가입 -->
        <div class="login_to_container">
          <div><a href="./../findId/findUserIdAuth.html" class="login_to_findId">아이디 찾기</a></div>
          <div><a href="./../findPW/findPw.html" class="login_to_findPw">비밀번호 찾기</a></div>
          <div><a href="./../join/selectUserType.html" class="login_to_join">회원가입</a></div>
        </div>
      </div>
    </div>
    </div> <!--//.container-->
  </main>
  <footer id="footer"></footer>
</body>
</html>