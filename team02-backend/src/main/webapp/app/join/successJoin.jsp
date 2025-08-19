<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/join/successJoin.css">
  <script defer src="./../../assets/js/join/successJoin.js"></script>
  <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>
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
      <!-- 약관동의 컨테이너 -->
      <div class="join_inner_container">
        <div class="join_final_title">
          회원가입 완료
        </div>
        <div class="join_final_txt">
          이제부터 밥세권 서비스를 이용하실 수 있습니다.
        </div>
        <div class="join_final_btns">
          <a href="./../../main.html">
            <div class="to_main">메인으로 이동</div>
          </a>
          <a href="./../../app/logIn/login.html">
            <div class="to_login">로그인</div>
          </a>
        </div>
      </div> <!-- //.seller_join_agree_container -->
    </div> <!--//.container-->
  </main>
  <footer id="footer"></footer>
</body>

</html>`