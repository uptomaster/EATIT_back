<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FAQ 상세</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/adminFaqDetail.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/adminFaqDetail.css">
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
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list" id="sidebar_list_warning"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list active" id="sidebar_list_customerservice"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post" class="logout_form">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">고객센터 - FAQ 상세</h1>
			<div class="admin_listwrapper">
				<div class="admin_whitebox">

					<!-- 게시글 제목 영역 -->
					<div class="admin_post_titlebox">
						<div class="admin_post_tag">
							<p>[FAQ]</p>
						</div>
						<div class="admin_post_icon">
							<img src="${pageContext.request.contextPath}/assets/img/관리자.png"
								alt="관리자">
						</div>
						<div class="admin_post_userid">
							<p>${faqDetail.adminId}</p>
						</div>
						<div class="admin_post_title">
							<p>${faqDetail.postTitle}</p>
						</div>
						<div class="admin_post_date">
							<p>(${faqDetail.postCreatedDate})</p>
						</div>
						<div class="admin_post_view">
							<label>조회 :</label> ${faqDetail.postViewCount}
						</div>
						<div class="admin_post_like">
							<label>추천 :</label> ${faqDetail.postLikeCount}
						</div>
					</div>

					<!-- 게시글 내용 -->
					<div class="admin_post_content">
						<p>
							<c:out value="${faqDetail.faqContent}" />
						</p>
					</div>

				</div>

				<!-- 하단 버튼 영역 -->
				<div class="admin_detail_btns">
					<!-- 수정 버튼 -->
					<form action="${pageContext.request.contextPath}/admin/faq/edit.ad"
						method="get" style="display: inline;">
						<input type="hidden" name="postNumber"
							value="${faqDetail.postNumber}">
						<button type="submit" id="admin_updatebtn">수 정</button>
					</form>

					<!-- 삭제 버튼 -->
					<form
						action="${pageContext.request.contextPath}/admin/faq/deleteOk.ad"
						method="post" style="display: inline;"
						onsubmit="return confirm('정말 삭제하시겠습니까?');">
						<input type="hidden" name="postNumber"
							value="${faqDetail.postNumber}">
						<button type="submit" id="admin_deletebtn">삭 제</button>
					</form>

					<!-- 목록으로 버튼 -->
					<button type="button" id="admin_listbtn"
						onclick="location.href='${pageContext.request.contextPath}/admin/faq/list.ad'">목
						록</button>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
