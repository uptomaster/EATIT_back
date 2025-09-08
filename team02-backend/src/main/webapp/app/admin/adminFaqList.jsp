<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>FAQ 관리</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/faqList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/adminFaqList.css">
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
					href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list active"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post" class="logout_form">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 컨텐츠 -->
		<main class="admin_inner">
			<h1 class="admin_pagetitle">고객센터 - FAQ</h1>
			<div class="admin_listwrapper">
				<!-- 탭 메뉴 -->
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu active"><a
							href="${pageContext.request.contextPath}/admin/faq/list.ad">FAQ</a></li>
						<li class="admin_list_menu"><a
							href="${pageContext.request.contextPath}/admin/inquiry/list.ad">문의</a></li>
					</ul>
				</div>

				<!-- 리스트 -->
				<div class="admin_list_whitebox">
					<!-- 컬럼 헤더 -->
					<ul class="admin_list_name">
						<li class="admin_list_row col-num">번호</li>
						<li class="admin_list_row col-title">제목</li>
						<li class="admin_list_row col-user">작성자</li>
						<li class="admin_list_row col-date">등록일</li>
						<li class="admin_list_row col-views">조회</li>
						<li class="admin_list_row col-likes">추천</li>
					</ul>

					<!-- 데이터 반복 -->
					<ul class="admin_list_valuebox">
						<c:choose>
							<c:when test="${not empty faqList}">
								<c:forEach var="faq" items="${faqList}">
									<li class="admin_list_value">
										<p class="admin_list_row col-num">${faq.postNumber}</p>
										<p class="admin_list_row col-title">
											<a
												href="${pageContext.request.contextPath}/admin/faq/detail.ad?postNumber=${faq.postNumber}"
												class="admin_list_userid_link">${faq.postTitle}</a>
										</p>
										<p class="admin_list_row col-user">
											<img class="grade_icon"
												src="${pageContext.request.contextPath}/assets/img/관리자.png"
												alt="관리자"> ${faq.adminId}
										</p>
										<p class="admin_list_row col-date">${faq.postCreatedDate}</p>
										<p class="admin_list_row col-views">${faq.postViewCount}</p>
										<p class="admin_list_row col-likes">${faq.postLikeCount}</p>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li class="admin_list_value empty">등록된 FAQ가 없습니다.</li>
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
								href="${pageContext.request.contextPath}/admin/faq/list.ad?page=${startPage-1}&searchType=${searchType}&searchWord=${searchWord}">&lt;</a>
							</li>
						</c:if>

						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:choose>
								<c:when test="${i == page}">
									<li><a href="#" class="active">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath}/admin/faq/list.ad?page=${i}&searchType=${searchType}&searchWord=${searchWord}">${i}</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${next}">
							<li><a
								href="${pageContext.request.contextPath}/admin/faq/list.ad?page=${endPage+1}&searchType=${searchType}&searchWord=${searchWord}">&gt;</a>
							</li>
						</c:if>
					</ul>

					<!-- 검색 + 작성 버튼 -->
					<div class="admin_search_area">
						<form
							action="${pageContext.request.contextPath}/admin/faq/list.ad"
							method="get" class="admin_search">
							<select class="admin_notice_category" name="searchType">
								<option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
								<option value="adminId"
									${searchType == 'adminId' ? 'selected' : ''}>작성자</option>
							</select> <input type="text" id="searchWord" name="searchWord"
								value="${searchWord}">
							<button class="search_btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</form>
						<form
							action="${pageContext.request.contextPath}/admin/faq/write.ad"
							method="get">
							<button id="prepare_notice_btn" type="submit">FAQ 작성</button>
						</form>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
