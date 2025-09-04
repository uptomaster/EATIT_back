<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지/이벤트 작성</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/noticeWrite.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/noticeWrite.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="admin_innerwrapper">
		<!-- 좌측 사이드바 -->
		<aside class="sidebar">
			<a href="${pageContext.request.contextPath}/admin/dashboard.ad">
				<img
				src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
				alt="admin_logo" class="admin_logo">
			</a>
			<ul class="sidebar_ul">
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
				<li class="sidebar_list active"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 영역 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">공지/이벤트 작성</h1>
			<div class="admin_listwrapper">
				<div class="admin_whitebox">
					<!-- 파일 업로드 가능하도록 enctype 추가 -->
					<form
						action="${pageContext.request.contextPath}/admin/notice/writeOk.ad"
						method="post" enctype="multipart/form-data">

						<!-- 제목 -->
						<div class="admin_notice_titlebox">
							<select name="noticeCategory" class="admin_notice_category"
								required>
								<option value="">태그 선택</option>
								<option value="공지">공지</option>
								<option value="이벤트">이벤트</option>
							</select> <label for="postTitle">제목 : </label> <input type="text"
								id="postTitle" name="postTitle" required>
						</div>

						<!-- 내용 -->
						<div class="admin_notice_contentbox">
							<textarea id="admin_notice_write" name="noticeContent"
								placeholder="내용을 입력하세요" required></textarea>
						</div>

						<!-- 파일 첨부 + 버튼 -->
						<div class="bottom_btn_area">
							<input type="file" id="admin_file" name="uploadFile" multiple>
							<button type="submit" id="admin_notice_dowrite">작성완료</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
