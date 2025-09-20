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
        <div class="findPw_title">밥세권</div>
        <div class="findPw_subtitle">비밀번호 찾기</div>
      </div>

      <!-- ★ 폼 클래스만 일관되게 변경 -->
      <form action="${pageContext.request.contextPath}/findPw/findPwOk.fp"
            method="post"
            class="pw-form"
            data-context-path="${pageContext.request.contextPath}">

        <!-- [라벨 | 인풋 | 버튼] 3열 그리드 -->
        <div class="form-grid">
          <!-- 아이디 -->
          <label for="findPw_input_id" class="lbl">아이디 :</label>
          <input type="text" id="findPw_input_id" name="findPw_input_id" placeholder="아이디를 입력해주세요" required>
          <div class="spacer"></div> <!-- 첫 줄은 버튼 없으니 빈칸 -->

          <!-- 전화번호 + 인증요청 -->
          <label for="findPw_input_phone" class="lbl">전화번호 :</label>
          <input type="text" id="findPw_input_phone" name="findPw_input_phone" placeholder="-는 제외하고 입력해주세요" required>
          <button type="button" class="btn ghost findPwAuth_req_auth">인증요청</button>

          <!-- 인증번호 + 인증완료 -->
          <label for="findPwAuth_input_auth" class="lbl">인증번호 :</label>
          <input type="text" id="findPwAuth_input_auth" name="findPwAuth_input_auth" required>
          <button type="button" class="btn ghost findPwAuth_chk_auth">인증완료</button>
        </div>

        <!-- 경고 메시지 (같은 축에 맞춰 배치) -->
        <div class="warning_space">
          <span class="warning_empty"></span>
          <div class="warning_message" id="warning_message_phone"></div>
          <div class="warning_empty"></div>
        </div>
      </form>

      <!-- 페이지 이동 링크 -->
      <div class="findPw_to_container">
        <div><a href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp">아이디 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp">비밀번호 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/join/selectUserType.jsp">회원가입</a></div>
      </div>

    </div>
  </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
