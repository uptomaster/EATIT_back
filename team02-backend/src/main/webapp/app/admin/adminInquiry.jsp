<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객센터 - 문의</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/adminInquiry.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/adminInquiry.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="admin_innerwrapper">
		<!-- 사이드바 -->
		<aside class="sidebar">
			<a href="${pageContext.request.contextPath}/admin/dashboard.ad">
				<img
				src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
				alt="admin_logo" class="admin_logo">
			</a>
			<ul class="sidebar_ul">
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
				</li>
				<li class="sidebar_list active" id="sidebar_list_customerservice">
					<a href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a>
				</li>
			</ul>
			<!-- 로그아웃 -->
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post" class="logout_form">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인컨텐츠 -->
		<main class="admin_inner">
			<h1 class="admin_pagetitle">고객센터 - 문의</h1>
			<div class="admin_listwrapper">

				<!-- 탭 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/faq/list.ad">FAQ</a>
						</li>
						<li class="admin_list_menu active"
							id="admin_list_menu_admininquiry"><a
							href="${pageContext.request.contextPath}/admin/inquiry/list.ad">문의(INQUIRY)</a>
						</li>
					</ul>
				</div>

				<!-- 리스트 -->
				<div class="admin_list_whitebox">
					<div class="admin_list_namebox">
						<ul class="admin_list_name">
							<li class="admin_list_row col-num">번호</li>
							<li class="admin_list_row col-title">제목</li>
							<li class="admin_list_row col-user">작성자</li>
							<li class="admin_list_row col-date">등록일</li>
							<li class="admin_list_row col-status">답변상태</li>
						</ul>
					</div>

					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty inquiryList}">
								<c:forEach var="inq" items="${inquiryList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">${inq.postNumber}</p>
										<p class="admin_list_row col-title">
											<a
												href="${pageContext.request.contextPath}/admin/inquiry/detail.ad?postNumber=${inq.postNumber}"
												class="admin_list_userid_link">${inq.postTitle}</a>
										</p>
										<p class="admin_list_row col-user">
											<img class="grade_icon"
												src="${pageContext.request.contextPath}/assets/img/새싹.png"
												alt="등급아이콘"> ${inq.memberId}
										</p>
										<p class="admin_list_row col-date">${inq.postCreatedDate}</p>
										<p
											class="admin_list_row col-status
    <c:if test='${inq.inquiryStatus == "YET"}'> status-yet</c:if>
    <c:if test='${inq.inquiryStatus == "IN_PROGRESS"}'> status-progress</c:if>
    <c:if test='${inq.inquiryStatus == "COMPLETE"}'> status-complete</c:if>">
											<c:choose>
												<c:when test="${inq.inquiryStatus == 'YET'}">접수</c:when>
												<c:when test="${inq.inquiryStatus == 'IN_PROGRESS'}">처리중</c:when>
												<c:when test="${inq.inquiryStatus == 'COMPLETE'}">완료</c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose>
										</p>

									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">등록된 문의글이 없습니다.</li>
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
								href="${pageContext.request.contextPath}/admin/inquiry/list.ad?page=${startPage-1}&searchType=${searchType}&searchWord=${searchWord}&startDate=${startDate}&endDate=${endDate}">&lt;</a>
							</li>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/inquiry/list.ad?page=${i}&searchType=${searchType}&searchWord=${searchWord}&startDate=${startDate}&endDate=${endDate}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/inquiry/list.ad?page=${endPage+1}&searchType=${searchType}&searchWord=${searchWord}&startDate=${startDate}&endDate=${endDate}">&gt;</a>
							</li>
						</c:if>
					</ul>

					<!-- 검색 -->
					<!-- 검색 -->
					<div class="admin_search_area">
						<form
							action="${pageContext.request.contextPath}/admin/inquiry/list.ad"
							method="get" class="admin_search">

							<!-- 검색 타입 -->
							<select class="admin_search_select" name="searchType"
								id="searchType">
								<option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
								<option value="memberId"
									${searchType == 'memberId' ? 'selected' : ''}>작성자</option>
								<option value="date" ${searchType == 'date' ? 'selected' : ''}>등록일</option>
								<option value="status"
									${searchType == 'status' ? 'selected' : ''}>답변상태</option>
							</select>

							<!-- 텍스트 검색 -->
							<input type="text" id="textInput" name="searchWord"
								value="${searchWord}" placeholder="검색어 입력">

							<!-- 날짜 검색 -->
							<div id="dateRange" class="date-range" style="display: none;">
								<input type="date" name="startDate" value="${startDate}">
								<span class="date-separator">~</span> <input type="date"
									name="endDate" value="${endDate}">
							</div>

							<!-- 상태 검색 -->
							<select id="statusSelect" name="searchWord"
								style="display: none;">
								<option value="">-- 선택 --</option>
								<option value="YET" ${searchWord == 'YET' ? 'selected' : ''}>접수</option>
								<option value="IN_PROGRESS"
									${searchWord == 'IN_PROGRESS' ? 'selected' : ''}>처리중</option>
								<option value="COMPLETE"
									${searchWord == 'COMPLETE' ? 'selected' : ''}>완료</option>
							</select>

							<!-- 검색 버튼 -->
							<button class="search_btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</div>

				</div>

			</div>
		</main>
	</div>
</body>
</html>
