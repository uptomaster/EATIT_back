<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/faqWrite.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/faqWrite.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
</head>
<body>
	<div class="admin_innerwrapper">
		<!-- 사이드바 -->
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
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list active" id="sidebar_list_customerservice"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post" class="logout_form">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">FAQ 작성</h1>
			<div class="admin_listwrapper">
				<div class="admin_whitebox">
					<!-- 작성 폼 -->
					<form
						action="${pageContext.request.contextPath}/admin/faq/writeOk.ad"
						method="post">

						<!-- 제목 -->
						<div class="admin_notice_titlebox">
							<label for="postTitle">제목 :</label> <input type="text"
								id="postTitle" name="postTitle" required>
						</div>

						<!-- 내용 -->
						<div class="admin_notice_contentbox">
							<textarea id="faqContent" name="faqContent"
								placeholder="내용을 입력하세요" required></textarea>
						</div>

						<!-- 버튼 -->
						<div class="bottom_btn_area">
							<button type="submit" id="admin_notice_dowrite">작성완료</button>
							<button type="button" id="admin_listbtn"
								onclick="location.href='${pageContext.request.contextPath}/admin/faq/list.ad'">취
								소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
