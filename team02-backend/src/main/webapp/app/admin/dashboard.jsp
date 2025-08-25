<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 대시보드</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/dashboard.css">
<!-- Chart.js CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
				<li class="sidebar_list" id="sidebar_list_dashboard"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/banner/list.ad">배너/광고</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a>
				</li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1>대시보드</h1>

			<!-- 상단 3칸 -->
			<div class="dashboard_topWrapper">
				<div class="dashboard_topbox">
					<div class="dashboard_topbox_key">총 회원 수</div>
					<div class="dashboard_topbox_value">${totalMembers}</div>
				</div>
				<div class="dashboard_topbox">
					<div class="dashboard_topbox_key">공지사항</div>
					<div class="dashboard_topbox_value">${totalNotices}</div>
				</div>
				<div class="dashboard_topbox">
					<div class="dashboard_topbox_key">FAQ</div>
					<div class="dashboard_topbox_value">${totalFaqs}</div>
				</div>
			</div>

			<!-- 그래프 -->
			<div class="dashboard_graph">
				<div class="dashboard_graph_title">최근 신고/문의 현황</div>
				<canvas id="dashboardChart" class="dashboard_graph_img"></canvas>
			</div>

			<!-- 하단 2칸 -->
			<div class="dashboard_bottomWrapper">
				<div class="dashboard_bottombox">
					<div class="dashboard_bottombox_title">최근 문의글</div>
					<ul>
						<c:forEach var="inq" items="${recentInquiries}">
							<li class="dashboard_bottombox_usetop3">${inq.postTitle}
								(${inq.inquiryStatus})</li>
						</c:forEach>
					</ul>
				</div>
				<div class="dashboard_bottombox">
					<div class="dashboard_bottombox_title">최근 신고</div>
					<ul>
						<c:forEach var="rep" items="${recentReports}">
							<li class="dashboard_bottombox_zimtop3">[${rep.postReportReason}]
								${rep.postReportDate}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>




</body>
</html>
