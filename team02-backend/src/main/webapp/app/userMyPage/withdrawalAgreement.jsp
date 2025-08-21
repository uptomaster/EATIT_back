<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/withdrawalAgreement.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/withdrawalAgreement.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <!-- <div class="withdrawalagreement_my_page_list"> -->
    <div class="withdrawalagreement_my_page_list">
      <div class="withdrawalagreement_my_page">마이 페이지</div>
      <ul class="withdrawalagreement_side_bar">
        <li><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
        <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
      </ul>
    </div>
    <!-- 회원탈퇴동의 창 -->
    <div class="withdrawalagreement">
      <div class="withdrawalagreement_agreement_page">
        <h2>회원탈퇴동의</h2>
        <div class="withdrawalagreement_two_box">
          <div class="withdrawalagreement_box_set">
            <div class="withdrawalagreement_password_now">비밀번호</div>
            <div class="withdrawalagreement_gray_box">
              <input id="passwordInput" class="withdrawalagreement_input_info" type="text" placeholder="현재 비밀번호 입력">
              <p id="passwordError" class="notice_input_wrong_info"></p>
            </div>
          </div>
          <div class="withdrawalagreement_box_set">
            <div class="withdrawalagreement_password_now">탈퇴 동의</div>
            <div class="withdrawalagreement_gray_box">
              <div>
                회원탈퇴에 동의하십니까?
                <input type="radio" name="agree" value="yes">
              </div>
              <p id="radioError" class="notice_input_wrong_info"></p>
            </div>
          </div>
          <!-- 회원탈퇴 버튼 -->
          <div class="withdrawalagreement_buzz_set">
            <button id="withdrawBtn" class="withdrawalagreement_agreement_buzz">회원탈퇴</button>
            <div class="withdrawalagreement_cancle_buzz"><a href="./../userMyPage/editUserInfo.html">취소</a></div>
          </div>
        </div>
      </div>
  </main>
  <footer id="footer"></footer>
</body>

</html>