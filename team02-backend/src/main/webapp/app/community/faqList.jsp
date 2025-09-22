<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">
<title>고객센터</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/community/customerServiceList.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />
<script defer
	src="${pageContext.request.contextPath}/assets/js/community/faqList.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/community/mouseoverTreeIcon.js"></script>
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">

<!-- 헤더 js -->
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>

<script>
	let headerPath = './../../header.jsp';
	let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />

	<main>
		<!-- 왼쪽 사이드바 -->
		<aside class="side_bar"></aside>

		<div class="main_container">

			<!-- 법적 안내 문구 -->
			<section class="legal_notice">
				<p>※ 고객센터 문의는 소비자기본법 및 식품위생법을 준수하여 처리됩니다.</p>
			</section>

			<!-- FAQ / 문의목록 탭 -->
			<nav class="community_category">
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/community/faqListOk.co"
						class="tab_button active">자주묻는질문</a></li>
					<li><a
						href="${pageContext.request.contextPath}/community/inquiryListOk.co"
						class="tab_button">문의목록</a></li>
				</ul>
			</nav>

			<!-- FAQ 섹션 -->
			<section id="faq_section" class="community_list"
				aria-label="FAQ 게시판 목록">
				<div class="list_header_flex_row" role="rowgroup">
					<div class="col_title" role="columnheader">제목</div>
					<div class="col_author" role="columnheader">글쓴이</div>
					<div class="col_date" role="columnheader">등록일</div>
					<div class="col_views" role="columnheader">조회</div>
					<div class="col_likes" role="columnheader">추천</div>
				</div>
				<div id="faq_list_body" class="list_body" role="rowgroup">
					<!-- FAQ 목록을 동적으로 출력 -->
					<c:forEach var="faq" items="${faq}">
						<div class="list_row_flex_row" role="row">
							<div class="col_title" role="cell">
								<a
									href="${pageContext.request.contextPath}/community/faqReadOk.co?postNumber=${faq.postNumber}"><c:out
										value="${faq.getPostTitle()}" /></a>
							</div>
							<div class="col_author" role="cell">
								<img src="${pageContext.request.contextPath}/assets/img/관리자.png"
									alt="관리자" class="tree_icon" />관리자
							</div>
							<div class="col_date" role="cell">
								<c:out value="${faq.getPostCreatedDate()}" />
							</div>
							<div class="col_views" role="cell">
								<c:out value="${faq.getPostViewCount()}" />
							</div>
							<div class="col_likes" role="cell">
								<c:out value="${faq.getPostLikeCount()}" />
							</div>
						</div>
					</c:forEach>
				</div>
			</section>

			<!-- 페이지네이션 -->
			<div class="pagination_container" id="pagination">
				<c:if test="${prev}">
					<div>
						<a
							href="${pageContext.request.contextPath}/community/faqListOk.co?page=${startPage-1}"
							class="prev">&lt;</a>
					</div>
				</c:if>
				<c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
				<c:forEach var="i" begin="${realStartPage}" end="${endPage}">
					<c:choose>
						<c:when test="${!(i == page) }">
							<div class="pagination_number">
								<a
									href="${pageContext.request.contextPath}/community/faqListOk.co?page=${i}">
									<c:out value="${i}" />
								</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="pagination_number">
								<a href="#" class="active"> <c:out value="${i}" />
								</a>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${next}">
					<div class="pagination_number">
						<a
							href="${pageContext.request.contextPath}/community/faqListOk.co?page=${endPage + 1}"
							class="next">&gt;</a>
					</div>
				</c:if>
			</div>

			<!-- 검색 + 글쓰기 -->
			<div class="controls_wrapper">
				<div class="function_container">
					<div class="search_box">
						<input class="search_text" type="text" placeholder="검색어를 입력해 주세요" />
						<button class="search_btn" type="button">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</div>

		</div>

		<!-- 오른쪽 사이드바 -->
		<aside class="side_bar"></aside>
	</main>
	<!-------------------- 푸터 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

	<script>
		let memberNumber = "${sessionScope.memberNumber}";
	</script>

</body>