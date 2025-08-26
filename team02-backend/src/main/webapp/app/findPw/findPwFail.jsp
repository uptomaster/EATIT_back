<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/findPw/findPwFail.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/findPw/findPwFail.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>

<body>
<jsp:include page="/header.jsp" />
  <main>
    <div class="findPw_fail_container"> <!-- 1100px 영역 -->
      <div class="findPw_fail_content_container"> <!-- 컨텐츠 영역 -->
        <div>
          <!-- 비밀번호 찾기 제목 -->
          <div class="findPw_fail_title">밥세권 회원</div>
          <div class="findPw_fail_subtitle">비밀번호찾기</div>
        </div>
        <div class="findPw_fail_txt">
          입력하신 정보와 일치하는 정보가 없습니다.
        </div>
        <!-- 비밀번호 찾기에서 다른 곳으로 이동ggg -->
        <div class="findPw_fail_to_container">
          <div><a href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp" class="findPw_to_findId">아이디 찾기</a></div>
          <div><a href="${pageContext.request.contextPath}/app/findpw/findPw.jsp" class="findPw_fail_to_findPw">비밀번호 찾기</a></div>
          <div><a href="${pageContext.request.contextPath}/app/join/selectUserType.jsp" class="findPw_fail_to_join">회원가입</a></div>
        </div>
      </div>
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>