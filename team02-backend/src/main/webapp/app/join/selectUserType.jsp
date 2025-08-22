<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/join/selectUserType.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/join/selectUserType.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <title>밥세권</title>
</head>

<body>
  <div id="header"></div>
  <main>
    <div class="container">
      <!-- 회원가입 단계 동그라미 4개 -->
      <ul class="join_step">
        <li>
          <div class="join_step_user_type">회원선택</div>
        </li>
        <li>
          <div class="join_step_agreement">약관동의</div>
        </li>
        <li>
          <div class="join_step_input_info">정보입력</div>
        </li>
        <li>
          <div class="join_step_final">가입완료</div>
        </li>
      </ul>
      <!--//.join_step-->
      <div class="select_user_type">
        <a href="${pageContext.request.contextPath}/join/generalAgreement.jo">
          <div class="join_type_user">일반 회원</div>
        </a>
        <a href="${pageContext.request.contextPath}/join/sellerAgreement.jo">
          <div class="join_type_seller">판매자 회원</div>
        </a>
      </div> <!-- 회원 유형 선택 -->

    </div>
  </main>
  <div id="footer"></div>
</body>

</html>