<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/findPw/findPw.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/findPw/findPw.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>

<body>
<jsp:include page="/header.jsp" />
<main>
  <div class="findPw_container">
    <div class="findPw_content_container">

      <div class="findPw_title_container">
        <div class="findPw_subtitle">비밀번호 찾기</div>
      </div>

      <form action="${pageContext.request.contextPath}/findPw/findPwOk.fp"
            method="post"
            class="findPw_input_container"
            data-context-path="${pageContext.request.contextPath}">

        <!-- 아이디 -->
        <div class="findPw_input_id_contanier">
          <label for="findPw_input_id">아이디 : </label>
          <input type="text" name="findPw_input_id" id="findPw_input_id" placeholder="아이디를 입력해주세요" required />
        </div>

        <!-- 전화번호 + 인증요청(같은 줄) -->
        <div class="findPw_input_phone_contaner">
          <label for="findPw_input_phone">전화번호 : </label>
          <input type="text" name="findPw_input_phone" id="findPw_input_phone" placeholder="전화번호를 입력해주세요." required />
          <button type="button" class="findPwAuth_req_auth">인증요청</button>
        </div>

        <!-- 인증번호 + 인증완료(그 아래 줄) -->
        <div class="findIdAuth_input_auth_box">
          <label for="findPwAuth_input_auth">인증번호 : </label>
          <input type="text" name="findPwAuth_input_auth" id="findPwAuth_input_auth" required />
          <button type="button" class="findPwAuth_chk_auth">인증완료</button>
        </div>

        <!-- 경고 메시지 -->
        <div class="warning_space">
          <span class="warning_empty"></span>
          <div class="warning_message" id="warning_message_phone"></div>
        </div>

        <!-- ※ 아래에 있던 추가 버튼 제거됨 -->

      </form>

      <!-- 페이지 이동 링크 -->
      <div class="findPw_to_container">
        <div><a href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp" class="findPw_to_findId">아이디 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp" class="findPw_to_findPw">비밀번호 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/join/selectUserType.jsp" class="findPw_to_join">회원가입</a></div>
      </div>

    </div>
  </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
