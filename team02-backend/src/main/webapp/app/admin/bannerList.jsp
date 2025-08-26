<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>배너 관리</title>
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/bannerList.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/bannerList.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script>
	const ctx = "${pageContext.request.contextPath}";
</script>

<!-- 반드시 ctx 선언 후 JS 로드 -->
<script defer
	src="${pageContext.request.contextPath}/assets/js/admin/bannerList.js"></script>

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
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대쉬보드</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/post/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
				</li>
				<li class="sidebar_list active"><a
					href="${pageContext.request.contextPath}/admin/banner/list.ad">배너/광고</a>
				</li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a>
				</li>
			</ul>

			<button id="admin_logoutbtn">로그아웃</button>
		</aside>

		<!-- 메인 컨텐츠 -->
		<main class="admin_inner">
			<h1 class="admin_pagetitle">배너/광고</h1>
			<div class="admin_listwrapper">
				<div class="admin_list_title">
					<ul class="admin_list">
						<li class="admin_list_menu">배너/광고 목록</li>
					</ul>
				</div>

				<div class="admin_list_whitebox">
					<div class="admin_list_namebox">
						<ul class="admin_list_name">
							<li class="admin_list_row">번호</li>
							<li class="admin_list_row">배너 제목</li>
							<li class="admin_list_row">작성자</li>
							<li class="admin_list_row">게시일</li>
							<li class="admin_list_row">마감일</li>
						</ul>
					</div>

					<!-- 목록 반복 -->
					<ul class="admin_list_valuebox">
						<c:forEach var="b" items="${bannerList}">
							<li class="admin_list_value">
								<p class="admin_list_row">${b.bannerNumber}</p> <a
								href="${pageContext.request.contextPath}/admin/banner/detail.ad?bannerNumber=${b.bannerNumber}"
								class="admin_list_userid_link">
									<p class="admin_list_row">${b.bannerTitle}</p>
							</a>
								<p class="admin_list_row">
									<img class="grade_icon"
										src="${pageContext.request.contextPath}/assets/img/관리자.png"
										alt=""> 관리자
								</p>
								<p class="admin_list_row">${b.bannerCreatedDate}</p>
								<p class="admin_list_row">${b.bannerEndDate}</p>
							</li>
						</c:forEach>
					</ul>
				</div>

				<!-- 페이지네이션/검색 -->
				<div class="admin_pagenation_search">
					<!-- 작성하기 버튼 수정 -->
					<form
						action="${pageContext.request.contextPath}/admin/banner/write.ad"
						method="get">
						<button id="prepare_notice_btn" type="submit">작성하기</button>
					</form>

					<div class="admin_pagenation">
						<a href="#">1</a> <a href="#">2</a> <a href="#">3</a>
					</div>

					<form
						action="${pageContext.request.contextPath}/admin/banner/list.ad"
						method="get">
						<div class="admin_search">
							<select class="admin_notice_category">
								<option value="title">제목</option>
							</select> <input type="text" id="search_word" name="search_word"
								value="${param.search_word}">
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
