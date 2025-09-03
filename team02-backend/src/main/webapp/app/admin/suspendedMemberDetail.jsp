<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/suspendedMemberDetail.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/suspendedMemberDetail.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
  crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="./../../app/admin/dashboard.html"><img src="./../../assets/img/admin_logo.png" alt="admin_logo" class="admin_logo"></a>
      <ul class="sidebar_ul">
        <a href="./../../app/admin/dashboard.html"><li id="sidebar_list_dashboard" class="sidebar_list">대쉬보드</li></a>
        <a href="./../../app/admin/memberList.html"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="./../../app/admin/postTradeList.html"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="./../../app/admin/reportList.html"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="./../../app/admin/adminCustomerService.html"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <div class="admin_listwrapper">
        <h3>개인 정보</h3>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>아 이 디</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>이 름</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>연 락 처</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
        </div>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>회원유형</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>경 고 수</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>등 급</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
        </div>
        <h3>사업자 정보</h3>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>상 호</p></div>
          <div class="admin_memberDetail_company_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>전화번호</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
        </div>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>주 소</p></div>
          <div class="admin_memberDetail_address_value"><p></p></div>
        </div>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>개업일자</p></div>
          <div class="admin_memberDetail_company_value"><p></p></div>
          <div class="admin_memberDetail_key"><p>사업자번호</p></div>
          <div class="admin_memberDetail_value"><p></p></div>
        </div>
        <h3>경고/정지 정보</h3>
        <div class="admin_memberDetail_rowWrapper_bottom">
          <div class="admin_memberDetail_key_bottom"><p>개업일자</p></div>
          <div class="admin_memberDetail_company_value"><p></p></div>  
        </div>
        <div class="admin_memberDetail_rowWrapper_bottom">                 
          <div class="admin_memberDetail_key_bottom"><p>개업일자</p></div>
          <div class="admin_memberDetail_company_value"><p></p></div>
        </div>
        <div class="admin_memberDetail_rowWrapper_btn">
          <form action="" method="post">                 
            <button id="button_warning" type="button">경고주기</button>
          </form>
        </div>               
      </div>  
    </div>
  </div>
</body>
</html>  