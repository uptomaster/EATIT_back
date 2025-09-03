<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지/이벤트 작성</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/noticeWrite.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/noticeWrite.css">
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
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <a href="${pageContext.request.contextPath}/admin/dashboard.ad"><li id="sidebar_list_dashboard" class="sidebar_list">대시보드</li></a>
        <a href="${pageContext.request.contextPath}/admin/member/list.ad"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/postTradeList.ad"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/report/list.ad"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/inquiry/list.ad"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <!-- 로그아웃 버튼 -->
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <!-- 페이지 제목 -->
      <h1>공지/이벤트 작성</h1>
      <!-- 바깥 회색 영역 -->
      <div class="admin_listwrapper">
        <!-- 하얀영역 -->
        <div class="admin_whitebox">
          <form action="${pageContext.request.contextPath}/admin/notice/writeOk.ad" method="post">
            <!-- 게시글 제목 작성영역 -->
            <div class="admin_notice_titlebox">
              <!-- 카테고리 선택 -->
              <select name="noticeCategory" class="admin_notice_category" required>
                <option value="">태그 선택</option>
                <option value="공지">공지</option>
                <option value="이벤트">이벤트</option>
              </select>
              <label for="title">제목 : </label>
              <input type="text" id="title" name="postTitle" required>
            </div>
            <!-- 작성도구 api 영역 -->
            <div class="write_api"></div>
            <textarea id="admin_notice_write" name="noticeContent" placeholder="내용을 입력하세요" required></textarea>
            
            <div class="bottom_btn_area">
              <input type="file" id="admin_file" name="uploadFile">
              <!-- 작성 완료 버튼 -->
              <button type="submit" id="admin_notice_dowrite">작성완료</button>
            </div>
          </form>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>
