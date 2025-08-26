<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원관리</title>
<script defer src="${pageContext.request.contextPath}/assets/js/admin/memberList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/memberList.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<!-- 회색영역 -->
	<div class="admin_innerwrapper">
		<!-- 좌측 사이드바 -->
		<aside class="sidebar">
			<!-- 관리자페이지 로고 -->
			<a href="${pageContext.request.contextPath}/admin/dashboard.ad">
				<img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
			</a>
			<ul class="sidebar_ul">
				<li class="sidebar_list" id="sidebar_list_dashboard">
					<a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
				</li>
				<li class="sidebar_list" id="sidebar_list_member">
					<a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
				</li>
				<li class="sidebar_list">
					<a href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글 관리</a>
				</li>
				<li class="sidebar_list">
					<a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
				</li>
				<li class="sidebar_list">
					<a href="${pageContext.request.contextPath}/admin/banner/list.ad">배너/광고</a>
				</li>
				<li class="sidebar_list">
					<a href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a>
				</li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 영역 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">회원관리</h1>
			<div class="admin_listwrapper">
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu">
							<a href="${pageContext.request.contextPath}/admin/member/list.ad">회원목록</a>
						</li>
					</ul>
				</div>

				<div class="admin_list_whitebox">
					<div class="admin_list_namebox">
						<!-- 컬럼 명 -->
						<ul class="admin_list_name">
							<li id="admin_list_num" class="admin_list_row">번호</li>
							<li id="admin_list_userid" class="admin_list_row">아이디</li>
							<li id="admin_list_username" class="admin_list_row">이름</li>
							<li id="admin_list_usercase" class="admin_list_row">유형</li>
							<li id="admin_list_warning" class="admin_list_row">누적 경고 수</li>
							<li id="admin_list_grade" class="admin_list_row">등급(포인트)</li>
						</ul>
					</div>

					<!-- 글 목록 행 -->
					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty memberList}">
								<c:forEach var="member" items="${memberList}">
									<li class="admin_list_value">
										<p id="admin_list_num_value" class="admin_list_row">
											<c:out value="${member.memberNumber}" />
										</p>
										<p id="admin_list_userid_value" class="admin_list_row">
											<!-- JSP 직접 접근해서 오류났던 곳 => 컨트롤러 타고 가야됨 -->
											<a href="${pageContext.request.contextPath}/admin/member/detail.ad?memberNumber=${member.memberNumber}"
											   class="admin_list_userid_link">
												<c:out value="${member.memberId}" />
											</a>
										</p>
										<p id="admin_list_username_value" class="admin_list_row">
											<c:out value="${member.memberName}" />
										</p>
										<p id="admin_list_usercase_value" class="admin_list_row">
											<c:out value="${member.memberType}" />
										</p>
										<p id="admin_list_warning_value" class="admin_list_row">
											<c:out value="${member.warningCount}" />
										</p>
										<p id="admin_list_grade_value" class="admin_list_row">
											<c:out value="${member.treeGrade}" />
											<img class="grade_icon" src="${pageContext.request.contextPath}/assets/img/씨앗.png" alt="">(2000)
										</p>
									</li>
								</c:forEach>
							</c:when>
						</c:choose>
					</ul>
				</div>

				<!-- 페이지네이션 -->
				<div class="admin_pagenation_search">
					<ul class="admin_pagenation">
						<c:if test="${prev}">
							<li>
								<a href="${pageContext.request.contextPath}/admin/member/list.ad?page=${startPage-1}" class="prev">&lt;</a>
							</li>
						</c:if>
						<c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
						<c:forEach var="i" begin="${realStartPage}" end="${endPage}">
							<c:choose>
								<c:when test="${!(i == page)}">
									<li>
										<a href="${pageContext.request.contextPath}/admin/member/list.ad?page=${i}">
											<c:out value="${i}" />
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li><a href="#" class="active"><c:out value="${i}" /></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${next}">
							<li>
								<a href="${pageContext.request.contextPath}/admin/member/list.ad?page=${endPage + 1}" class="next">&gt;</a>
							</li>
						</c:if>
					</ul>

					<!-- 검색 폼 -->
					<form action="${pageContext.request.contextPath}/admin/member/list.ad" method="get">
						<div class="admin_search">
							<select class="admin_notice_category" name="searchType">
								<option value="id" ${searchType == 'id' ? 'selected' : ''}>아이디</option>
								<option value="name" ${searchType == 'name' ? 'selected' : ''}>이름</option>
								<option value="type" ${searchType == 'type' ? 'selected' : ''}>회원유형</option>
							</select>
							<input type="text" id="search_word" name="searchWord" value="${searchWord}">
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
