<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <!-- css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/editUserInfo.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  
  <!-- js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/editUserInfo.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <!-- 메인 -->
  <main>
      <!-- 마이페이지 전체 레이아웃 영역 -->
    <div class="my_page_list">
      <!-- 마이페이지 제목 -->
      <div class="my_page">마이 페이지</div>

      <!-- 마이페이지 사이드 메뉴 -->
      <ul class="side_bar">
        <li class="my_page_list_main"><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
      </ul>
    </div>

	<!-- 내 정보 수정 입력 폼 -->
	<form class="edit_user_info" method="post">
	  <input type="hidden" name="mode" id="mode_input" value="">
	  <h2 class="my_info">내 정보 수정</h2>
	
	  <!-- 아이디(수정 불가) -->
	  <div class="info_unable_modify_area">
	    <div class="info_menu">아이디</div>
	    <div class="gray_box">
	      <p>${myPage.memberId}</p>
	      <p>*아이디는 수정 불가능합니다.</p>
	    </div>
	  </div>

	
	  <!-- 현재 비밀번호 입력 -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">비밀번호*</div>
	    <div>
	      <div class="gray_box">
	        <input id="current_password" name="currentPassword" class="input_info" type="password" placeholder="[입력가능] 현재 비밀번호 확인">
	      </div>
	      <p id="current_password_error" class="notice_input_wrong_info"></p>
	    </div>
	  </div>
	
	  <!-- 새 비밀번호 입력 -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">새 비밀번호*</div>
	    <div>
	      <div class="gray_box">
	        <input id="new_password" name="newPassword" class="input_info" type="password" placeholder="현재 비밀번호 확인 후 입력 가능" 
	         value="${param.newPassword != null && param.newPassword != 'null' ? param.newPassword : ''}">
	      </div>
	      <!-- 비밀번호 불일치시 안내글 공간  -->
	      <p id="new_password_error" class="notice_input_wrong_info"></p>
	    </div>
	  </div>
	
	
	
	  <!-- 새 비밀번호 확인 -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">새 비밀번호 확인*</div>
	    <div>
	      <div class="gray_box">
	        <input id="confirm_password" name="confirmPassword" class="input_info" type="password" placeholder="변경할 비밀번호를 다시입력해주세요">
	      </div>
	      <p id="confirm_password_error" class="notice_input_wrong_info"></p>
	    </div>
	  </div>
	
	  <!-- 이름 표시(수정 불가라면 그대로 p로 표시) -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">이름*</div>
	    <div class="gray_box">
	      <p>${myPage.generalName}</p>
	    </div>
	  </div>
	
	  <!-- 기존 전화번호 표시 -->
	  <div class="info_unable_modify_area">
	    <div class="info_menu">전화번호*</div>
	    <div class="gray_box">
	      <p>${myPage.generalPhoneNumber}</p>
	    </div>
	  </div>
	
	  <!-- 새 전화번호 입력 -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">새 전화번호*</div>
	    <div>
	      <div class="gray_box">
	        <input id="new_phone" name="newPhone" class="input_info" type="text" placeholder="[입력가능] 새 전화번호"
	       	 value="${param.newPhone != null && param.newPhone != 'null' ? param.newPhone : ''}">
	      </div>
	      <p id="phone_error" class="notice_input_wrong_info" style="color:red;"></p>
	    </div>
         <button type="button"
		        formaction="${pageContext.request.contextPath}/userMyPage/userMyPageSmsSend.my" value="send"
		        id="send_code_btn" class="info_send_code_buzz">인증번호 전송
		</button>
	  </div>
	
	  <!-- 인증번호 입력 -->
	  <div class="info_able_modify_area">
	    <div class="info_menu">인증번호*</div>
	    <div>
	      <div class="gray_box">
	        <input id="code_input" name="phoneCode" class="input_info" type="text" placeholder="[입력가능] 인증번호를 입력하세요">
	      </div>
	      
	      <p id="code_error" class="notice_input_wrong_info" style="color:red;"></p>
	    </div>
		<button type="button"
		        formaction="${pageContext.request.contextPath}/userMyPage/userMyPageSmsSend.my" value="check"
		        id="check_code_btn" class="info_check_code_buzz">인증번호 확인
		</button>
	  </div>
	  
	  <!-- 생년월일 -->
	  <div class="info_unable_modify_area">
	    <div class="info_menu">생년월일</div>
	    <div class="gray_box">
	      <p>
	        <c:choose>
	          <c:when test="${not empty myPage.generalBirthDate}">
	            ${myPage.generalBirthDate}
	          </c:when>
	          <c:otherwise>-</c:otherwise>
	        </c:choose>
	      </p>
	    </div>
	  </div>
	
	  <!-- 전체 저장 버튼 -->
	  <div class="bottom_btn_container">
	  	  <div class="agreement_buzz">
		    <a href="${pageContext.request.contextPath}/userMyPage/withdrawalAgreement.my">회원탈퇴</a>
		  </div>
		  <button type="button" class="total_info_save_buzz" data-member-number="${member.memberNumber}">저장</button>
	  </div>
	</form>
  </main>
  <!-- 푸터 -->
  <jsp:include page="/footer.jsp" />
</body>
</html>