<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/findId/findUserIdFail.css">
  <script defer src="./../../assets/js/findId/findUserIdFail.js"></script>
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
    <div class="findId_fail_container"> <!-- 1100px 영역 -->
      <div class="findId_fail_content_container"> <!-- 컨텐츠 영역 -->

        <div>
          <!-- 아이디 찾기 제목 -->
          <div class="findId_fail_title">밥세권 회원</div>
          <div class="findId_fail_subtitle">아이디찾기</div>
        </div>
        <div class="findId_fail_txt">
          입력하신 정보와 일치하는 정보가 없습니다.
        </div>
        <!-- 아이디 찾기에서 다른 곳으로 이동ggg -->
        <div class="findId_fail_to_container">
          <div><a href="./../findId/findUserIdAuth.html" class="findId_fail_to_findId">아이디 찾기</a></div>
          <div><a href="./../findPW/findPw.html" class="findId_fail_to_findPw">비밀번호 찾기</a></div>
          <div><a href="./../join/selectUserType.html" class="findId_fail_to_join">회원가입</a></div>
        </div>
      </div>
    </div>
    </div> <!--//.container-->
  </main>
  <footer id="footer"></footer>
</body>

</html>`