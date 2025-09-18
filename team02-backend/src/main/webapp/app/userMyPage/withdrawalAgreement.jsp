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
    <div class="withdrawalagreement_my_page_list">
      <div class="withdrawalagreement_my_page">마이 페이지</div>
      <ul class="withdrawalagreement_side_bar">
        <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
      </ul>
    </div>
    <form class="withdrawalagreement" action="${pageContext.request.contextPath}/userMyPage/withdrawOk.my" method="post">
      <div class="withdrawalagreement_agreement_page">
        <h2>회원탈퇴동의</h2>

        <div class="withdrawalagreement_two_box">
          
          <!-- 비밀번호 입력 -->
	      <div class="withdrawalagreement_box_set">
	        <div class="withdrawalagreement_password_now">비밀번호</div>
	        <div class="withdrawalagreement_gray_box">
	          <input id="passwordInput" name="password" class="withdrawalagreement_input_info" type="password" placeholder="현재 비밀번호 입력" required />
	          <c:if test="${not empty pwError}">
	            <p class="notice_input_wrong_info">${pwError}</p>
	          </c:if>
	        </div>
	      </div>
          
          <!-- 동의 라디오 -->
	      <div class="withdrawalagreement_box_set">
	        <div class="withdrawalagreement_password_now">탈퇴 동의</div>
	        <div class="withdrawalagreement_gray_box">
	          회원탈퇴에 동의하십니까?
	          <label class="agree_label">
	            <input type="radio" name="agree" value="yes"> 예, 동의합니다
	          </label>
	          <c:if test="${not empty agreeError}">
	            <p class="notice_input_wrong_info">${agreeError}</p>
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