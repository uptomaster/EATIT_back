<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>자유게시판 상세</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/boardFreeDetail.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/boardFreeDetail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
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

		<!-- 메인 컨텐츠 -->
		<main class="admin_inner">
			<h1 class="admin_pagetitle">게시글 관리 - 자유게시판</h1>
			<div class="admin_listwrapper">
				<div class="admin_whitebox">

					<!-- 게시글 제목 영역 -->
					<div class="admin_post_titlebox">
						<div class="admin_post_userid">
							<p>${freeDetail.memberId}</p>
						</div>
						<div class="admin_post_title">
							<p>${freeDetail.postTitle}</p>
						</div>
						<div class="admin_post_date">
							<p>(${freeDetail.postCreatedDate})</p>
						</div>
						<div class="admin_post_view">
							<label>조회 :</label>${freeDetail.postViewCount}</div>
						<div class="admin_post_like">
							<label>추천 :</label>${freeDetail.postLikeCount}</div>
					</div>

					<!-- 게시글 내용 -->
					<div class="admin_post_content">
						<p>
							<c:out value="${freeDetail.freeContent}" />
						</p>
					</div>

					<!-- 삭제 버튼 -->
					<div class="admin_detail_delete">
						<form
							action="${pageContext.request.contextPath}/admin/boardFree/deleteOk.ad"
							method="post">
							<input type="hidden" name="postNumber"
								value="${freeDetail.postNumber}">
							<button id="admin_deletebtn" type="submit">삭 제</button>
						</form>
					</div>

				</div>
			</div>
		</main>
	</div>
</body>
</html>
