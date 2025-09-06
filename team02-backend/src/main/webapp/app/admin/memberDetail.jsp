<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/memberDetail.css">
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
				<li class="sidebar_list active" id="sidebar_list_member"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">회원 상세</h1>
			<div class="admin_listwrapper admin_whitebox">

				<!-- 회원번호 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">회원번호</div>
					<div class="admin_memberDetail_value">${memberDetail.memberNumber}</div>
				</div>

				<!-- 아이디 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">아이디</div>
					<div class="admin_memberDetail_value">${memberDetail.memberId}</div>
				</div>

				<!-- 이름 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">이름</div>
					<div class="admin_memberDetail_value">${memberDetail.memberName}</div>
				</div>

				<!-- 유형 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">회원 유형</div>
					<div class="admin_memberDetail_value">${memberDetail.memberType}</div>
				</div>

				<!-- 경고수 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">경고 수</div>
					<div class="admin_memberDetail_value">${memberDetail.warningCount}</div>
				</div>

				<!-- 등급 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key">등급</div>
					<div class="admin_memberDetail_value">
						${memberDetail.treeGrade} <img class="grade_icon"
							src="${pageContext.request.contextPath}/assets/img/씨앗.png"
							alt="등급아이콘"> (${memberDetail.paymentAmount})
					</div>
				</div>

				<!-- 버튼 영역 -->
				<div class="admin_memberDetail_buttons">
					<form
						action="${pageContext.request.contextPath}/admin/member/warningOk.ad"
						method="post" onsubmit="return confirm('정말 경고를 부여하시겠습니까?');">
						<input type="hidden" name="memberNumber"
							value="${memberDetail.memberNumber}"> <input
							type="hidden" name="memberType"
							value="${memberDetail.memberType}">
						<button type="submit" class="btn btn-warning">
							<i class="fas fa-exclamation-triangle"></i> 경고 주기
						</button>
					</form>
					<button type="button" class="btn btn-back"
						onclick="location.href='${pageContext.request.contextPath}/admin/member/list.ad'">
						<i class="fas fa-arrow-left"></i> 목록으로
					</button>
				</div>


			</div>
		</div>
	</div>

	<!-- 알림 스크립트 -->
	<c:if test="${not empty param.warningSuccess}">
		<script>
			alert("경고를 부여했습니다.");
			location.href = "${pageContext.request.contextPath}/admin/member/list.ad";
		</script>
	</c:if>
</body>
</html>
