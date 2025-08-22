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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/join/joinAgreementUser.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/join/joinAgreementUser.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
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
      <!-- 약관동의 체크 칸 -->
      <form action="${pageContext.request.contextPath}/join/generalJoin.jo" method="post" class="join_agree">
        <div class="join_agree_all">
          <input type="checkbox" name="joinAgreeAll" id="join_agree_all_chkbox" class="">
          <label for="join_agree_all_chkbox">전체 동의</label>
        </div>
        <!-- 서비스 이용동의 약관 -->
        <div>
          <div class="join_agree_top_title">서비스 이용 약관 </div>
          <div class="join_agree_top_term_container">
            <input type="checkbox" name="essenAgree" class="essential" id="join_agree_top_term" required />
            <label for="join_agree_top_term">[필수] 이용 약관 동의</label>
            <!-- <span class="full-agreement">&gt;</span> -->
            <div></div>
          </div>
          <div class="join_agree_top_financial_container">
            <input type="checkbox" name="essenAgree" class="essential" id="join_agree_top_financial" required />
            <label for="join_agree_top_financial">[필수] 전자금융거래 이용약관 동의</label>
            <!-- <span>&gt;</span>/ -->
            <div></div>
          </div>
        </div>
        <hr>
        <!-- 개인정보 수집 및 이용약관 -->
        <div>
          <div class="join_agree_bottom_title">개인정보 수집 및 이용 약관</div>
          <div class="join_agree_bottom_service_continer">
            <input type="checkbox" name="agree" id="join_agree_bottom_service" class="">
            <label for="join_agree_bottom_service">[선택] 서비스/이벤트 제공을 위한 개인정보 수집 이용동의</label>
            <!-- <span class="full-agreement">&gt;</span> -->
          </div>
          <div class="join_agree_bottom_personal">
            <input type="checkbox" name="essenAgree" class="essential" id="join_agree_bottom_peraonal" class="">
            <label for="join_agree_bottom_personal">[필수] 밥세권 회원 연동을 위한 개인정보 수집 이용 동의</label>
            <!-- <span class="full-agreement">&gt;</span> -->
          </div>
        </div>
        <!-- 다음버튼 -->
        <div class="join_agree_next">
          <button type="button" class="join_agree_next_btn" onclick="goNextPage()">다음</button>
        </div>
      </form>
    </div> <!--//.container-->
  </main>
  <footer id="footer"></footer>
</body>

</html>