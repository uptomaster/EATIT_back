<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지 수정</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/noticeEdit.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/noticeEdit.css">
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

		<!-- 메인컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">공지 수정</h1>

			<div class="admin_listwrapper">
				<div class="admin_whitebox">
					<!-- 수정 폼 -->
					<form
						action="${pageContext.request.contextPath}/admin/notice/updateOk.ad"
						method="post" enctype="multipart/form-data">

						<!-- 글 번호 -->
						<input type="hidden" name="postNumber"
							value="${notice.postNumber}" />

						<!-- 제목 -->
						<div class="admin_notice_titlebox">
							<label for="postTitle">제목 :</label> <input type="text"
								id="postTitle" name="postTitle" value="${notice.postTitle}"
								required />
						</div>

						<!-- 내용 -->
						<div class="admin_notice_contentbox">
							<label for="noticeContent">내용 :</label>
							<textarea id="noticeContent" name="noticeContent" required>${notice.noticeContent}</textarea>
						</div>

						<!-- 첨부파일 -->
						<div class="admin_notice_filebox">
							<label for="uploadFile">첨부파일 :</label> <input type="file"
								id="uploadFile" name="uploadFile" />
							<p class="notice_file_hint">※ 수정 시 기존 이미지는 삭제되고 새 파일만 적용됩니다.</p>
						</div>

						<!-- 버튼 영역 -->
						<div class="bottom_btn_area">
							<button type="submit" id="admin_notice_updatebtn">수정완료</button>
							<button type="button"
								onclick="location.href='${pageContext.request.contextPath}/admin/notice/detail.ad?postNumber=${notice.postNumber}'">취소</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
