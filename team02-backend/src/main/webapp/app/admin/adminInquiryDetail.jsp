<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/adminInquiryDetail.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/adminInquiryDetail.css">
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
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <!-- 페이지 제목 -->
      <h1>고객센터</h1>
      <!-- 바깥 회색영역 -->
      <div class="admin_listwrapper">
        <!-- 안쪽 하얀영역 -->
        <div class="admin_whitebox">
          <!-- 게시글 제목영역 -->
          <div class="admin_post_titlebox">
            <div class="admin_post_tag"><p>[문의]</p></div>
            <div class="admin_post_icon"><img src="./../../assets/img/새싹.png" alt="새싹"></div>
            <div class="admin_post_userid"><p>gisu</p></div>
            <div class="admin_post_title"><p>비밀번호를 잊어버렸어요. 어떻게 하나요?</p></div>
            <div class="admin_post_date"><p>(2025-08-03 22:41)</p></div>
            <div class="admin_post_status"><span id="post_status_text">접수</span></div>
          </div>
          <!-- 게시글 내용 -->
          <div class="admin_post_content"><p>어떻게 하냐고</p></div>
          <div class="admin_reply_section">
            <h2 class="reply_title">관리자 답변</h2>
             <!-- 기존 답변 리스트 -->
            <ul id="reply_list" class="reply_list"></ul>
              <!-- 답변 작성 폼 -->
            <form id="reply_form" class="reply_form" autocomplete="off">
              <label for="reply_text" class="sr-only">답변 내용</label>
              <textarea id="reply_text" placeholder="답변을 입력하세요" required></textarea>
              <div class="reply_actions">
                <button type="submit" id="reply_submit">등록</button>
              </div>
            </form>
          </div>
        </div>
        <!-- 하단 버튼 영역 -->
        <div class="admin_detail_delete">
          <button type="button" id="admin_deletebtn" >삭 제</button>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>