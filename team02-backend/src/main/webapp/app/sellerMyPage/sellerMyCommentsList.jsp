<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=, initial-scale=1.0">
<link rel="shortcut icon" href="./../../assets/img/favicon.ico"
	type="image/x-icon">
<link rel="stylesheet"
	href="./../../assets/css/sellerMyPage/sellerMyCommentsList.css">
<link rel="stylesheet" href="./../../assets/css/header.css">
<link rel="stylesheet" href="./../../assets/css/footer.css">
<script defer
	src="./../../assets/js/sellerMyPage/sellerMyCommentsList.js"></script>
<script>
	let headerPath = '../../header.jsp';
	let footerPath = '../../footer.jsp';
</script>
<script defer src="../../assets/js/header.js"></script>
<title>밥세권</title>
</head>

<body>
	<header id="header"></header>
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="seller_mycomments_menu">
			<div class="seller_mycomments_menu_title">마이 페이지</div>
			<ul class="seller_mycomments_menu_list">
				<li><a href="/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="/sellerMyPage/buiedFood.se">음식 구매 내역</a></li>
				<li><a href="/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="/sellerMyPage/myPosts.se">내 글 관리</a></li>
				<li class="store_info_menu_list_current"><a
					href="/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
		</div>


		<div class="seller_mycomments_page">
			<h2 class="seller_mycomments_list">내 댓글 목록</h2>
			<div>
				<div class="seller_mycomments_top">
					<div class="seller_mycomments_sort">게시판 종류</div>
				<!-- 	<div class="seller_mycomments_tag">태그</div> -->
					<div class="seller_mycomments_title">게시글 제목</div>
					<div class="seller_mycomments_comments_info">댓글 내용</div>
					<div class="seller_mycomments_date">작성 일자</div>
					<!-- <div class="seller_mycomments_like_count">추천수</div> -->
				</div>
				<c:choose>
					<c:when test="${not empty myCommentList}">
						<c:forEach var="comment" items="${myCommentList}">
							<div class="seller_mycomments_comments_list">
								<div class="seller_mycomments_sort">
									<a href="./../../app/community/freeBoardList.html">
									<c:out value="${comment.getPostType() }"/></a>
								</div>
<!-- 								<div class="seller_mycomments_tag">
									<c:out value="${ }" />
								</div> -->
								<div class="seller_mycomments_title">
									<a href="./../../app/community/viewOwnPost.html"><c:out value="${comment.getCommentNumber() }"/></a>
								</div>
								<div class="seller_mycomments_comments_info"><c:out value="${comment.getCommentContent() }"/></div>
								<div class="seller_mycomments_date"><c:out value="${comment.getCommentedDate() }"/></div>
								<div class="seller_mycomments_like_count"><c:out "${comment.getlikecount }"/></div>
							</div>
							</c:forEach>
						</c:when>]
					</c:choose>
			</div>
			<div class="seller_mycomments_pagination">
				<a href="#" class="seller_mycomments_page_active">1</a>
				<a href="#"	class="seller_mycomments_page">2</a> 
				<a href="#"	class="seller_mycomments_page">3</a> 
				<a href="#"	class="seller_mycomments_page">4</a> 
				<a href="#" class="seller_mycomments_page">5</a>
			</div>
		</div>
	</main>
	<footer id="footer"></footer>
</body>

</html>