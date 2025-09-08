<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>정지회원목록</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/suspendedMemberList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/suspendedMemberList.css">
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
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list active" id="sidebar_list_warning"><a
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
			<h1 class="admin_pagetitle">신고관리 - 정지회원목록</h1>
			<div class="admin_listwrapper">

				<!-- 탭 메뉴 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/report/list.ad">신고목록</a></li>
						<li class="admin_list_menu active" id="admin_list_menu_suspend"><a
							href="${pageContext.request.contextPath}/admin/suspend/list.ad">정지회원목록</a></li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/blacklist/list.ad">블랙리스트</a></li>
					</ul>
				</div>

				<!-- 컬럼 헤더 -->
				<div class="admin_list_whitebox">
					<ul class="admin_list_name">
						<li class="admin_list_row col-num">번호</li>
						<li class="admin_list_row col-usernum">회원번호</li>
						<li class="admin_list_row col-username">이름</li>
						<li class="admin_list_row col-usertype">회원유형</li>
						<li class="admin_list_row col-start">정지 시작일</li>
						<li class="admin_list_row col-end">정지 종료일</li>
						<li class="admin_list_row col-count">누적 신고 수</li>
					</ul>

					<!-- 실제 정지회원 목록 -->
					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty suspendList}">
								<c:forEach var="s" items="${suspendList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">${s.memberNumber}</p>
										<p class="admin_list_row col-usernum">${s.memberNumber}</p>
										<p class="admin_list_row col-username">${s.memberName}</p>
										<p class="admin_list_row col-usertype">${s.memberType}</p>
										<p class="admin_list_row col-start">${s.suspendStartDate}</p>
										<p class="admin_list_row col-end">${s.suspendEndDate}</p>
										<p class="admin_list_row col-count">${s.suspendReportCount}</p>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">정지된 회원이 없습니다.</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<!-- 페이지네이션 + 검색 -->
				<div class="admin_pagenation_search">
					<!-- 페이지네이션 (왼쪽) -->
					<ul class="admin_pagenation">
						<c:if test="${prev}">
							<li><a
								href="${pageContext.request.contextPath}/admin/suspend/list.ad?page=${startPage-1}"
								class="prev">&lt;</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/suspend/list.ad?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/suspend/list.ad?page=${endPage+1}"
								class="next">&gt;</a></li>
						</c:if>
					</ul>

					<!-- 검색 (오른쪽) -->
					<form
						action="${pageContext.request.contextPath}/admin/suspend/list.ad"
						method="get">
						<div class="admin_search">
							<select class="admin_notice_category" name="searchType">
								<option value="id">아이디</option>
								<option value="name">이름</option>
								<option value="type">유형</option>
							</select> <input type="text" id="search_word" name="searchWord"
								value="${searchWord}">
							<button class="search_btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
