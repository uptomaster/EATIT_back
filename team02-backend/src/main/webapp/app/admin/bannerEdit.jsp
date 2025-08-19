<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/bannerEdit.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/bannerEdit.css">
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
        <a href="./../../app/admin/bannerList.html"><li id="sidebar_list_banner"class="sidebar_list">배너/광고</li></a>
        <a href="./../../app/admin/adminCustomerService.html"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <!-- 페이지 제목 -->
      <h1>배너수정</h1>
      <!-- 제일바깥 회색 영역 -->
      <div class="admin_listwrapper">
        <!-- 하얀 영역 -->
        <div class="admin_whitebox">
          <form action="" method="post">
            <!-- 공지작성 제목 영역 -->
            <div class="admin_notice_titlebox">
              <!-- 태그 선택 -->
              <select class="admin_notice_category">
                <option value="notice">태그</option>
                <option value="notice">광고</option>
              </select>
              <!-- 제목 -->
              <label for="title">제목 : </label>
              <input type="text" id="title">
              <!-- 마감일 선택 -->
              <label for="date">마감일 : </label>
              <input type="date" id="date">
            </div>
            <!-- 작성도구api영역 -->
            <div class="write_api"></div>
              <!-- 작성 영역 -->
              <textarea name="" id="admin_notice_write"></textarea>
              <!-- 아래쪽 버튼 영역 -->
            <div class="bottom_btn_area">
              <!-- 파일첨부 -->
              <input type="file" id="admin_file">
              <!-- 작성완료 버튼 -->
              <button type="button" id="admin_notice_dowrite">작성완료</button>
            </div>
          </form>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>