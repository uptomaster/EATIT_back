<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerwithdrawalAgreement.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerwithdrawalAgreement.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_withdraw_menu">
      <div class="seller_withdraw_menu_title">마이 페이지</div>
      <ul class="seller_withdraw_menu_list">
				<li class="store_info_menu_list_current"><a href="/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

    <!-- 회원탈퇴동의 창 -->
    <div class="seller_withdrawalagreement">
      <div class="seller_withdrawalagreement_agreement_page">
        <h2>회원탈퇴동의</h2>
        <div class="seller_withdrawalagreement_two_box">
          <div class="seller_withdrawalagreement_box_set">
            <div class="seller_withdrawalagreement_password_now">비밀번호</div>
            <div class="seller_withdrawalagreement_gray_box">
              <input id="seller_passwordInput" class="seller_withdrawalagreement_input_info" type="text"
                placeholder="현재 비밀번호 입력">
              <p id="seller_passwordError" class="notice_input_wrong_info"></p>
            </div>
          </div>
          <div class="seller_withdrawalagreement_box_set">
            <div class="seller_withdrawalagreement_password_now">탈퇴 동의</div>
            <div class="seller_withdrawalagreement_gray_box">
              <div>
                회원탈퇴에 동의하십니까?
                <input type="radio" name="agree" value="yes">
              </div>
              <p id="seller_radioError" class="notice_input_wrong_info"></p>
            </div>
          </div>
          <!-- 회원탈퇴 버튼 -->
          <div class="seller_withdrawalagreement_buzz_set">
            <button id="seller_withdrawBtn" class="seller_withdrawalagreement_agreement_buzz">회원탈퇴</button>
            <div class="seller_withdrawalagreement_cancle_buzz"><a href="./../sellerMyPage/editSellerInfo.html">취소</a>
            </div>
          </div>
        </div>
      </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>