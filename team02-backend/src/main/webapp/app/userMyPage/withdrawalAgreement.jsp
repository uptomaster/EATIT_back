<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/withdrawalAgreement.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/withdrawalAgreement.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <title>밥세권</title>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <form class="withdrawalagreement" action="${pageContext.request.contextPath}/userMyPage/withdrawOk.my" method="post">
      <div class="withdrawalagreement_agreement_page">
        <h2>회원탈퇴동의</h2>

        <div class="withdrawalagreement_two_box">
          
          <!-- 동의 체크박스  -->
          <div class="withdraw_check">
            <h3>탈퇴 진행 전 다음 내용을 꼭 확인해 주세요</h3>
            <p class="customer_info">고객 정보 및 서비스 이용 기록은 개인 정보보호 처리 방침 기준에 따라 삭제됩니다</p>
            <p class="customer_agree">회원 탈퇴 시 더 이상 밥세권 서비스 이용이 불가능합니다</p>
            <label class="agree_label">
              <input type="checkbox" name="agree" value="yes"> 안내 사항을 모두 확인하였으며, 이에 동의합니다
            </label>
            <c:if test="${not empty agreeError}">
              <p class="notice_input_wrong_info">${agreeError}</p>
            </c:if>
          </div>

	      <!-- 비밀번호 입력 -->
	      <div class="withdrawalagreement_box_set">
	        <div class="withdrawalagreement_password_now">비밀번호</div>
	        <div class="withdrawalagreement_gray_box">
	          <input id="passwordInput" name="password" class="withdrawalagreement_input_info" type="password" placeholder="비밀번호 입력" required />
	          <c:if test="${not empty pwError}">
	            <p class="notice_input_wrong_info">${pwError}</p>
	          </c:if>
	        </div>
	      </div>
	   
          <!-- 버튼 -->
          <div class="withdrawalagreement_buzz_set">
            <button type="submit" id="withdrawBtn" class="withdrawalagreement_agreement_buzz">회원탈퇴</button>
            <div class="withdrawalagreement_cancle_buzz">
              <a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">취소</a>
            </div>
          </div>
    
    
      	</div>
      </div>
    </form>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>