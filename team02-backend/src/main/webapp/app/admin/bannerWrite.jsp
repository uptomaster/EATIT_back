<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>배너 작성</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/bannerWrite.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/bannerWrite.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/dashboard.ad">대쉬보드</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/post/list.ad">게시글 관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
        <li class="sidebar_list active"><a href="${pageContext.request.contextPath}/admin/banner/list.ad">배너/광고</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a></li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn" type="submit">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 -->
    <div class="admin_inner">
      <h1>배너 작성</h1>
      <div class="admin_listwrapper">
        <div class="admin_whitebox">

          <!-- 작성 폼 -->
          <form action="${pageContext.request.contextPath}/admin/banner/writeOk.ad"
                method="post" enctype="multipart/form-data">
            
            <div class="admin_notice_titlebox">
              <label for="title">제목 : </label>
              <input type="text" id="title" name="bannerTitle" required>

              <label for="date">마감일</label>
              <input type="date" id="date" name="bannerEndDate" required>

              <label for="active">활성화 여부</label>
              <select id="active" name="bannerIsActive">
                <option value="Y">활성</option>
                <option value="N">비활성</option>
              </select>
            </div>

            <!-- 이미지 업로드 -->
            <div class="admin_notice_titlebox">
              <label for="admin_file">배너 이미지</label>
              <input type="file" id="admin_file" name="uploadFile" accept="image/*" required>
            </div>

            <div class="bottom_btn_area">
              <!-- 작성완료: writeOk.ad 로 전송 -->
              <button type="submit" id="admin_notice_dowrite">작성완료</button>
            </div>
          </form>

          <!-- 취소: list.ad 로 이동 -->
          <form action="${pageContext.request.contextPath}/admin/banner/list.ad" method="get">
            <button type="submit">취소</button>
          </form>

        </div>
      </div>
    </div>
  </div>
</body>
</html>
