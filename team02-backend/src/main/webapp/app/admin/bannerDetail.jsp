<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>배너 상세</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/bannerDetail.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/bannerDetail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="admin_innerwrapper">
		<!-- 좌측 사이드바 -->
		<aside class="sidebar">
			<a href="${pageContext.request.contextPath}/app/admin/dashboard.jsp">
				<img
				src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
				alt="admin_logo" class="admin_logo">
			</a>
			<ul class="sidebar_ul">
				<a href="${pageContext.request.contextPath}/app/admin/dashboard.jsp"><li
					class="sidebar_list">대쉬보드</li></a>
				<a
					href="${pageContext.request.contextPath}/app/admin/memberList.jsp"><li
					class="sidebar_list">회원관리</li></a>
				<a
					href="${pageContext.request.contextPath}/app/admin/postTradeList.jsp"><li
					class="sidebar_list">게시글 관리</li></a>
				<a
					href="${pageContext.request.contextPath}/app/admin/reportList.jsp"><li
					class="sidebar_list">신고관리</li></a>
				<a href="${pageContext.request.contextPath}/admin/banner/list.ad"><li
					class="sidebar_list active">배너/광고</li></a>
				<a
					href="${pageContext.request.contextPath}/app/admin/adminCustomerService.jsp"><li
					class="sidebar_list">고객센터</li></a>
			</ul>
			<button id="admin_logoutbtn">로그아웃</button>
		</aside>

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1>배너/광고 상세</h1>

			<div class="admin_listwrapper">
				<div class="admin_whitebox">
					<!-- 배너/광고 정보 -->
					<div class="admin_post_titlebox">
						<div class="admin_post_icon">
							<img src="${pageContext.request.contextPath}/assets/img/관리자.png"
								alt="관리자">
						</div>
						<div class="admin_post_userid">
							<p>관리자</p>
						</div>
						<div class="admin_post_title">
							<p>${banner.bannerTitle}</p>
						</div>
						<div class="admin_post_startdate">
							<label>게시일 :</label> ${banner.bannerCreatedDate}
						</div>
						<div class="admin_post_enddate">
							<label>마감일 :</label> ${banner.bannerEndDate}
						</div>
					</div>

					<!-- 배너 이미지 -->
					<div class="admin_post_content">
						<c:if test="${not empty banner.adminImageSystemName}">
							<img
								src="${pageContext.request.contextPath}/upload/admin/${banner.adminImageSystemName}"
								alt="${banner.adminImageOriginalName}"
								style="max-width: 600px; border: 1px solid #ccc;">
						</c:if>
					</div>
				</div>

				<!-- 수정/삭제 버튼 -->
				<div class="admin_detail_delete">
					<button type="button" id="admin_updatebtn"
						onclick="location.href='${pageContext.request.contextPath}/admin/banner/updateOk.ad?bannerNumber=${banner.bannerNumber}'">
						수정</button>
					<form action="/admin/banner/deleteOk.ad?bannerNumber=${banner.bannerNumber}" method="get">
						<button type="button" id="admin_deletebtn">삭제</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
