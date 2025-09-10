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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/findPw/editPw.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/findPw/editPw.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath='../../header.jsp', footerPath='../../footer.jsp';
  </script>
</head>
<body>
<jsp:include page="/header.jsp" />
<main>
  <div class="findPw_edit_container">
    <div class="findPw_edit_content_container">
      <div>
        <div class="findPw_edit_title">밥세권 회원</div>
        <div class="findPw_edit_subtitle">비밀번호 수정</div>
      </div>

      <form action="${pageContext.request.contextPath}/findPw/updatePwOk.fp"
            method="post"
            class="findPw_edit_input_container"
            data-context-path="${pageContext.request.contextPath}">
        <div class="findPw_edit_newPw_contaner">
          <label for="findPw_edit_newPw">새 비밀번호 : </label>
          <input type="password" name="findPw_edit_newPw" id="findPw_edit_newPw" placeholder="">
        </div>
        <div class="findPw_edit_newPw_chk_contaner">
          <label for="findPw_edit_newPw_chk">새 비밀번호 확인 : </label>
          <input type="password" name="findPw_edit_newPw_chk" id="findPw_edit_newPw_chk" placeholder="">
        </div>

        <div class="findPw_warning_msg" id="findPw_edit_warning_message_new"></div>
        <div class="findPw_warning_msg" id="findPw_edit_warning_message_chk"></div>

        <div>
          <button type="submit" class="findPw_edit_btn">수정하기</button>
        </div>
      </form>

      <div class="findPw_edit_to_container">
        <div><a href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp" class="findPw_edit_to_findId">아이디 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp" class="findPw_edit_to_findPw">비밀번호 찾기</a></div>
        <div><a href="${pageContext.request.contextPath}/app/join/selectUserType.jsp" class="findPw_edit_to_join">회원가입</a></div>
      </div>
    </div>
  </div>
</main>
<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
