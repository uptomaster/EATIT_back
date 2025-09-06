<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>신고관리</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/reportList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/reportList.css">
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
				<li class="sidebar_list active"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">신고관리</h1>
			<div class="admin_listwrapper">

				<!-- 탭 메뉴 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu active"><a
							href="${pageContext.request.contextPath}/admin/report/list.ad">신고목록</a>
						</li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/suspend/list.ad">정지회원목록</a>
						</li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/blacklist/list.ad">블랙리스트</a>
						</li>
					</ul>
				</div>

				<!-- 신고 목록 -->
				<div class="admin_list_whitebox">
					<!-- 컬럼 헤더 -->
					<ul class="admin_list_name">
						<li class="admin_list_row col-num">번호</li>
						<li class="admin_list_row col-reason">사유</li>
						<li class="admin_list_row col-post">게시글번호</li>
						<li class="admin_list_row col-user">신고대상자</li>
						<li class="admin_list_row col-reporter">신고자</li>
						<li class="admin_list_row col-date">신고일</li>
						<li class="admin_list_row col-count">신고횟수</li>
					</ul>

					<!-- 데이터 반복 -->
					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty reportList}">
								<c:forEach var="r" items="${reportList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">${r.postReportNumber}</p>
										<p class="admin_list_row col-reason">
											<span class="tag_reason ${r.postReportReason}">${r.postReportReason}</span>
										</p>
										<p class="admin_list_row col-post">${r.postNumber}</p>
										<p class="admin_list_row col-user">${r.memberNumber}</p>
										<p class="admin_list_row col-reporter">신고자정보필요</p>
										<p class="admin_list_row col-date">${r.postReportDate}</p>
										<p class="admin_list_row col-count">${r.postReportCount}</p>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">신고 내역이 없습니다.</li>
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
								href="${pageContext.request.contextPath}/admin/report/list.ad?page=${startPage-1}"
								class="prev">&lt;</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/report/list.ad?page=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/report/list.ad?page=${endPage+1}"
								class="next">&gt;</a></li>
						</c:if>
					</ul>

					<!-- 검색 (오른쪽) -->
					<form
						action="${pageContext.request.contextPath}/admin/report/list.ad"
						method="get">
						<div class="admin_search">
							<select class="admin_notice_category" name="searchType">
								<option value="reported" ${searchType=='reported'?'selected':''}>신고대상자</option>
								<option value="reporter" ${searchType=='reporter'?'selected':''}>신고자</option>
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
