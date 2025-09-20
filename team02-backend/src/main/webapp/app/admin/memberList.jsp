<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/memberList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/memberList.css">
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
				<li class="sidebar_list" id="sidebar_list_dashboard"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
				</li>
				<li class="sidebar_list active" id="sidebar_list_member"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a>
				</li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 영역 -->
		<main class="admin_inner">
			<h1 class="admin_pagetitle">회원관리</h1>
			<div class="admin_listwrapper">

				<!-- 탭 메뉴 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu active"><a
							href="${pageContext.request.contextPath}/admin/member/list.ad">회원목록</a>
						</li>
					</ul>
				</div>

				<!-- 회원 목록 -->
				<div class="admin_list_whitebox">
					<!-- 컬럼 명 -->
					<ul class="admin_list_name">
						<li class="admin_list_row col-num">번호</li>
						<li class="admin_list_row col-id">아이디</li>
						<li class="admin_list_row col-name">이름</li>
						<li class="admin_list_row col-type">유형</li>
						<li class="admin_list_row col-warning">누적 경고 수</li>
						<li class="admin_list_row col-grade">등급(포인트)</li>
					</ul>

					<!-- 데이터 반복 -->
					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty memberList}">
								<c:forEach var="member" items="${memberList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">
											<c:out value="${member.MEMBERNUMBER}" />
										</p>
										<p class="admin_list_row col-id">
											<a
												href="${pageContext.request.contextPath}/admin/member/detail.ad?memberNumber=${member.MEMBERNUMBER}"
												class="admin_list_userid_link"> <c:out
													value="${member.MEMBERID}" />
											</a>
										</p>
										<p class="admin_list_row col-name">
											<c:out value="${member.MEMBERNAME}" />
										</p>
										<p class="admin_list_row col-type">
											<c:out value="${member.MEMBERTYPE}" />
										</p>
										<p class="admin_list_row col-warning">
											<c:out value="${member.WARNINGCOUNT}" />
										</p>
										<p class="admin_list_row col-grade">
											<c:out value="${member.TREEGRADE}" />
											<img class="grade_icon"
												src="${pageContext.request.contextPath}/assets/img/씨앗.png"
												alt="등급아이콘"> (
											<c:out value="${member.PAYMENTAMOUNT}" />
											)
										</p>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">등록된 회원이 없습니다.</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<!-- 페이지네이션 + 검색 -->
				<div class="admin_pagenation_search">
					<!-- 페이지네이션 -->
					<ul class="admin_pagenation">
						<c:if test="${prev}">
							<li><a
								href="${pageContext.request.contextPath}/admin/member/list.ad?page=${startPage-1}&searchType=${searchType}&searchWord=${searchWord}&memberType=${memberType}"
								class="prev">&lt;</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active"><c:out value="${i}" /></a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/member/list.ad?page=${i}&searchType=${searchType}&searchWord=${searchWord}&memberType=${memberType}"><c:out
												value="${i}" /></a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/member/list.ad?page=${endPage+1}&searchType=${searchType}&searchWord=${searchWord}&memberType=${memberType}"
								class="next">&gt;</a></li>
						</c:if>
					</ul>

					<!-- 검색 -->
					<form
						action="${pageContext.request.contextPath}/admin/member/list.ad"
						method="get">
						<div class="admin_search">
							<select class="admin_notice_category" name="searchType">
								<option value="id" ${searchType == 'id'   ? 'selected' : ''}>아이디</option>
								<option value="name" ${searchType == 'name' ? 'selected' : ''}>이름</option>
								<option value="type" ${searchType == 'type' ? 'selected' : ''}>회원유형</option>
							</select> <input type="text" id="search_word" name="searchWord"
								value="${searchWord}">
							<button class="search_btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</form>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
