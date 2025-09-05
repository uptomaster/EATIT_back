<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/noticeDetail.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/noticeDetail.css">
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

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">게시글 관리 - 공지사항</h1>
			<div class="admin_listwrapper">
				<div class="admin_whitebox">

					<!-- 게시글 제목 영역 -->
					<div class="admin_post_titlebox">
						<div class="admin_post_tag">
							<p>[공지]</p>
						</div>
						<div class="admin_post_icon">
							<img src="${pageContext.request.contextPath}/assets/img/관리자.png"
								alt="관리자">
						</div>
						<div class="admin_post_userid">
							<p>${notice.adminId}</p>
						</div>
						<div class="admin_post_title">
							<p>${notice.postTitle}</p>
						</div>
						<div class="admin_post_date">
							<p>(${notice.postCreatedDate})</p>
						</div>
						<div class="admin_post_view">
							<label>조회 :</label> ${notice.postViewCount}
						</div>
						<div class="admin_post_like">
							<label>추천 :</label> ${notice.postLikeCount}
						</div>
					</div>

					<!-- 게시글 내용 -->
					<div class="admin_post_content">
						<p>
							<c:out value="${notice.noticeContent}" />
						</p>
					</div>

					<!-- 이미지 영역 -->
					<c:if test="${not empty images}">
						<div class="admin_post_images">
							<c:forEach var="img" items="${images}">
								<img
									src="${pageContext.request.contextPath}/upload/${img.adminImageSystemName}"
									alt="${img.adminImageOriginalName}" class="notice_img">
							</c:forEach>
						</div>
					</c:if>

					<!-- 하단 버튼 영역 -->
					<div class="admin_detail_delete">
						<!-- 수정 버튼 -->
						<form
							action="${pageContext.request.contextPath}/admin/notice/edit.ad"
							method="get">
							<input type="hidden" name="postNumber"
								value="${notice.postNumber}">
							<button type="submit" id="admin_updatebtn">수 정</button>
						</form>

						<!-- 삭제 버튼 -->
						<form
							action="${pageContext.request.contextPath}/admin/notice/deleteOk.ad"
							method="post">
							<input type="hidden" name="postNumber"
								value="${notice.postNumber}">
							<button type="submit" id="admin_deletebtn">삭 제</button>
						</form>

						<!-- 목록으로 버튼 -->
						<form
							action="${pageContext.request.contextPath}/admin/notice/list.ad"
							method="get">
							<button type="submit" id="admin_listbtn">목 록</button>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>
