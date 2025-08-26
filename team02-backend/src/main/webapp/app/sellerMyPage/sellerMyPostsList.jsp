<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerMyPostsList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerMyPostsList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="seller_myposts_menu">
			<div class="seller_myposts_menu_title">마이 페이지</div>
			<ul class="seller_myposts_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li class="store_info_menu_list_current"><a href="/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
		</div>


		<div class="seller_myposts_page">
			<h2 class="seller_myposts_list">내 글 관리</h2>
			<div>
				<div class="seller_myposts_top">
					<div class="seller_myposts_sort">게시판 종류</div>
					<!-- <div class="seller_myposts_tag">태그</div> -->
					<div class="seller_myposts_title">글 제목</div>
					<div class="seller_myposts_date">게시일</div>
					<div class="seller_myposts_comments_count">댓글수</div>
					<div class="seller_myposts_like_count">추천수</div>
				</div>
				<c:choose>
					<c:when test="${not empty myPostList}">
						<c:forEach var="post" items="${myPostList}">
							<div class="seller_myposts_comments_list">
								
								<div class="seller_myposts_sort">
									<c:out value="${post.getPostType() }" />
								</div>
								<div class="seller_myposts_title">
									<a href="${pageContext.request.contextPath}/app/community/viewOwnPost.jsp"><c:out
											value="${post.getPostTitle() }" /></a>
								</div>
								<div class="seller_myposts_date">
									<c:out value="${post.getPostCreatedDate() }" />
								</div>
								<div class="seller_myposts_comments_count">
									<c:out value="${post.getCommentCount() }" />
								</div>
								<div class="seller_myposts_like_count">
									<c:out value="${post.getPostLikeCount() }" />
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>
							<div colspan="5" align="center">작성한 게시글이 없습니다.</div>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
			<!-- 페이지네이션 들어가는 자리 -->
			<div class="seller_myposts_pagination">
				<ul class="seller_myposts_pagination_ul">
					<c:if test="${prev}">
						<li><a
							href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${startPage - 1}"
							class="prev">&lt;</a></li>
					</c:if>
					<c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
					<c:forEach var="i" begin="${realStartPage}" end="${endPage}">
						<c:choose>
							<c:when test="${!(i == page) }">
								<li><a class="pagination_item"
									href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${i}">
										<c:out value="${i}" />
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="#" class="active"> <c:out value="${i}" />
								</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${next}">
						<li><a
							href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${endPage + 1}"
							class="next">&gt;</a>
					</c:if>
				</ul>
			</div>
			<!-- 페이지네이션 끝 -->
		</div>
	</main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>