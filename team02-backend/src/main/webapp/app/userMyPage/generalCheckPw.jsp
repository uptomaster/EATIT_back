<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/generalCheckPw.css">
    <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/generalCheckPw.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <div class="general_edit_user_info">
      <div class="check_area">
      <!-- 페이지 제목 -->
      <h2 class="general_my_info">본인 확인</h2>
      <!-- 컨텐츠 영역 -->
      <form class="general_chk_pw" action="${pageContext.request.contextPath}/userMyPage/generalCheckPwOk.my" method="post">
        <!-- 입력 -->
        
        <div class="general_chk_pw_input">
          <h4 class="usercheck">회원정보 접근 시<br> 개인정보보호를 위해 본인확인을 진행합니다</h4>
          <div>
            <div class="general_gray_box">
              <input name="generalPw" id="general_chk_pw_info" class="general_chk_pw_info" type="password" placeholder="비밀번호를 입력해 주세요">
            </div>
            <c:if test="${not empty pwError}">
	        	<p id="general_chk_pw_warning" class="general_notice_input_wrong_info">${pwError}</p>
	        </c:if>
            <!-- 여기에 메시지 출력 -->
          </div>
          <button type="submit" id="general_chk_pw_btn">확인</button>
        </div>
        
      </form> <!-- 컨텐츠 영역 -->
     </div> 
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>