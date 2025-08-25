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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/findPw/findPw.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/findPW/findPw.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>

<body>
<jsp:include page="/header.jsp" />
  <main> 
    <div class="findPw_container"> <!-- 1100px 영역 -->
      <div class="findPw_content_container"> <!-- 컨텐츠 영역 -->

        <div class="findPw_title_container">
          <!-- 비밀번호 찾기 제목 -->
          <div class="findPw_title">밥세권</div>
          <div class="findPw_subtitle">비밀번호 찾기</div>
        </div>
        <!-- 비밀번호 찾기 입력 폼 -->
        <form action="" method="post" class="findPw_input_container">
          <div class="findPw_input_id_contanier">
            <label for="findPw_input_id">아이디 : </label>
            <input type="text" name="" id="findPw_input_id" placeholder="아이디를 입력해주세요">
          </div>
          <div class="findPw_input_phone_contaner">
            <label for="findPw_input_phone">전화번호 : </label>
            <input type="text" name="" id="findPw_input_phone" placeholder="전화번호를 입력해주세요.">
          </div>
          <div class="warning_space"> <!-- 경고메시지 -->
              <span class="warning_empty"></span>
              <div class="warning_message" id="warning_message_phone"></div>
            </div>
        <!-- 임시비밀번호 발송 버튼 -->
        <div>
          <button type="button" class="findPw_btn">임시 비밀번호 발송</button>
        </div>
        </form>
        <!-- 아이디 찾기, 비밀번호 찾기, 회원가입 -->
        <div class="findPw_to_container">
          <div><a href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp" class="findPw_to_findId">아이디 찾기</a></div>
          <div><a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp" class="findPw_to_findPw">비밀번호 찾기</a></div>
          <div><a href="${pageContext.request.contextPath}/app/join/selectUserType.jsp" class="findPw_to_join">회원가입</a></div>
        </div>
      </div>
    </div>
    </div> <!--//.container-->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>`