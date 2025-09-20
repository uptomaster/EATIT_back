<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>신고 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/reportList.css">
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
				<li class="sidebar_list" id="sidebar_list_dashboard"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
				<li class="sidebar_list" id="sidebar_list_member"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
				<li class="sidebar_list" id="sidebar_list_community"><a
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list active" id="sidebar_list_warning"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list" id="sidebar_list_customerservice"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">신고 관리</h1>

			<div class="admin_listwrapper">
				<!-- 탭 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu active"><a
							href="${pageContext.request.contextPath}/admin/report/list.ad">신고목록</a></li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/suspend/list.ad">정지목록</a></li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/blacklist/list.ad">블랙리스트</a></li>
					</ul>
				</div>

				<!-- 테이블 -->
				<div class="admin_list_whitebox">
					<ul class="admin_list_name">
						<li class="admin_list_row col-num">번호</li>
						<li class="admin_list_row col-title">제목</li>
						<li class="admin_list_row col-reason">사유</li>
						<li class="admin_list_row col-post">글번호</li>
						<li class="admin_list_row col-user">피신고자(ID)</li>
						<li class="admin_list_row col-reporter">신고자</li>
						<li class="admin_list_row col-date">신고일</li>
						<li class="admin_list_row col-action">관리</li>
					</ul>

					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty reportList}">
								<c:forEach var="r" items="${reportList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">${r.postReportNumber}</p>
										<p class="admin_list_row col-title">
											<a
												href="${pageContext.request.contextPath}/admin/${r.reportedType eq 'GENERAL' ? 'boardFree' : (r.reportedType eq 'SELLER' ? 'boardPromotion' : 'boardRecipe')}/detail.ad?postNumber=${r.postNumber}">
												<span>${r.postTitle}</span>
											</a>
										</p>
										<p class="admin_list_row col-reason">
											<span class="tag_reason ${r.postReportReason}">${r.postReportReason}</span>
										</p>
										<p class="admin_list_row col-post">${r.postNumber}</p>
										<p class="admin_list_row col-user">${r.reportedName}
											(${r.reportedId})</p>
										<p class="admin_list_row col-reporter">${r.reporterId}</p>
										<p class="admin_list_row col-date">${r.postReportDate}</p>
										<div class="admin_list_row col-action">
											<c:choose>
												<c:when test="${r.isSuspended == 1}">
													<button type="button" class="btn_suspend"
														onclick="alert('이미 정지된 회원입니다.');">정지</button>
												</c:when>
												<c:otherwise>
													<form
														action="${pageContext.request.contextPath}/admin/suspend/insertOk.ad"
														method="post" style="display: inline;">
														<input type="hidden" name="memberNumber"
															value="${r.reportedNumber}">
														<button type="submit" class="btn_suspend">정지</button>
													</form>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${r.isBlacklisted == 1}">
													<button type="button" class="btn_blacklist"
														onclick="alert('이미 블랙리스트에 등록된 회원입니다.');">블랙리스트</button>
												</c:when>
												<c:otherwise>
													<form
														action="${pageContext.request.contextPath}/admin/blacklist/insertOk.ad"
														method="post" style="display: inline;">
														<input type="hidden" name="memberNumber"
															value="${r.reportedNumber}">
														<button type="submit" class="btn_blacklist">블랙리스트</button>
													</form>
												</c:otherwise>
											</c:choose>
										</div>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">신고된 내역이 없습니다.</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

				<!-- 페이지네이션 + 검색 -->
				<div class="admin_pagenation_search">
					<ul class="admin_pagenation">
						<c:if test="${prev}">
							<li><a
								href="${pageContext.request.contextPath}/admin/report/list.ad?page=${startPage-1}&searchType=${searchType}&searchWord=${searchWord}"
								class="prev">&lt;</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/report/list.ad?page=${i}&searchType=${searchType}&searchWord=${searchWord}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/report/list.ad?page=${endPage+1}&searchType=${searchType}&searchWord=${searchWord}"
								class="next">&gt;</a></li>
						</c:if>
					</ul>

					<!-- 검색 -->
					<form
						action="${pageContext.request.contextPath}/admin/report/list.ad"
						method="get" class="admin_search">
						<select name="searchType" class="admin_notice_category">
							<option value="reported"
								${searchType == 'reported' ? 'selected' : ''}>피신고자</option>
							<option value="reporter"
								${searchType == 'reporter' ? 'selected' : ''}>신고자</option>
							<option value="reason"
								${searchType == 'reason' ? 'selected' : ''}>사유</option>
						</select> <input type="text" name="searchWord" id="search_word"
							value="${searchWord}" placeholder="검색어 입력">
						<button type="submit" class="search_btn">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
