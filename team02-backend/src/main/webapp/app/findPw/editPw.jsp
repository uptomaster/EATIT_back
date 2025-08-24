<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<<<<<<< HEAD
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/findPW/editPw.css">
  <script defer src="./../../assets/js/findPW/editPw.js"></script>
=======
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/findPw/editPw.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/findPW/editPw.js"></script>
  <title>밥세권</title>
>>>>>>> d182d8ad59917b1633fd463b5f23dabcbf9fb439
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
    <div class="findPw_edit_container"> <!-- 1100px 영역 -->
      <div class="findPw_edit_content_container"> <!-- 컨텐츠 영역 -->

        <!-- 비밀번호 찾기 제목 -->
        <div>
          <div class="findPw_edit_title">밥세권 회원</div>
          <div class="findPw_edit_subtitle">비밀번호 수정</div>
        </div>
        <!-- 비밀번호 수정 입력 폼 -->
        <form action="" method="post" class="findPw_edit_input_container">
          <div class="findPw_edit_oldPw_contanier">
            <label for="findPw_edit_oldPw">임시 비밀번호 : </label>
            <input type="password" name="" id="findPw_edit_oldPw" placeholder="">
          </div>
          <div class="findPw_edit_newPw_contaner">
            <label for="findPw_edit_newPw">새 비밀번호 : </label>
            <input type="text" name="" id="findPw_edit_newPw" placeholder=".">
          </div>
          <div class="findPw_edit_newPw_chk_contaner">
            <label for="findPw_edit_newPw_chk">새 비밀번호 확인 : </label>
            <input type="password" name="" id="findPw_edit_newPw_chk" placeholder="">
          </div>
          <div>비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다..</div>
          <!-- 경고 메시지 -->
          <div class="findPw_warning_msg" id="findPw_edit_warning_message_old"></div>
          <div class="findPw_warning_msg" id="findPw_edit_warning_message_new"></div>
          <div class="findPw_warning_msg" id="findPw_edit_warning_message_chk"></div>
        </form>
        <!-- 수정하기 버튼 -->
        <div>
          <button type="button" class="findPw_edit_btn">수정하기</button>
        </div>
        <!--메인으로, 로그인 버튼 -->
        <!-- 메인으로, 로그인 버튼 -->
        <div class="findId_clear_btns">
          <a href="./../../main.html">
            <div class="findId_clear_to_main">메인으로</div>
          </a>
          <a href="./../logIn/login.html">
            <div class="findId_clear_to_login">로그인으로</div>
          </a>
          <!-- <button type="button" id="findId_clear_to_main" href="./../../main.html">메인으로 이동</button>
          <button type="button" id="findId_clear_to_login" href="./../logIn/login.html">로그인</button> -->
        </div>
        <!-- 아이디찾기, 비밀번호찾기, 회원가입 -->
        <div class="findPw_edit_to_container">
          <div><a href="./../findId/findUserIdAuth.html" class="findPw_edit_to_findId">아이디 찾기</a></div>
          <div><a href="./../findPW/findPw.html" class="findPw_edit_to_findPw">비밀번호 찾기</a></div>
          <div><a href="./../join/selectUserType.html" class="findPw_edit_to_join">회원가입</a></div>
        </div>

      </div> <!-- //컨텐츠 영역-->
    </div> <!--//1100px 영역-->
  </main>
  <footer id="footer"></footer>
</body>

</html>`