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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerMyCommentsList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="seller_mycomments_menu">
			<div class="seller_mycomments_menu_title">마이 페이지</div>
			<ul class="seller_mycomments_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내 글 관리</a></li>
				<li class="store_info_menu_list_current"><a
					href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
		</div>


		<div class="seller_mycomments_page">
			<h2 class="seller_mycomments_list">내 댓글 목록</h2>
			<div>
				<div class="seller_mycomments_top">
					<div class="seller_mycomments_sort">게시판 종류</div>
				<!-- 	<div class="seller_mycomments_tag">태그</div> -->
					<div class="seller_mycomments_title">게시글 제목</div>
					<div class="seller_mycomments_title">게시글 작성자</div>
					<div class="seller_mycomments_comments_info">댓글 내용</div>
					<div class="seller_mycomments_date">작성 일자</div>
					<!-- <div class="seller_mycomments_like_count">추천수</div> -->
				</div>
				<c:choose>
					<c:when test="${not empty myCommentList}">
						<c:forEach var="comment" items="${myCommentList}">
							<div class="seller_mycomments_comments_list">
								<!-- 게시판 종류 -->
								<div class="seller_mycomments_sort">
									<!-- <a href="./../../app/community/freeBoardList.html"></a> -->
								    <c:choose>
								      <c:when test="${comment.postType eq 'INQUIRY'}">문의</c:when>
								      <c:when test="${comment.postType eq 'FAQ'}">자주묻는질문</c:when>
								      <c:when test="${comment.postType eq 'NOTICE'}">공지/이벤트</c:when>
								      <c:when test="${comment.postType eq 'FREE'}">자유게시판</c:when>
								      <c:when test="${comment.postType eq 'PROMOTION'}">홍보게시판</c:when>
								      <c:when test="${comment.postType eq 'RECIPE'}">레시피</c:when>
								      <c:otherwise><c:out value="${comment.postType}"/></c:otherwise>
								    </c:choose>
								</div>
								<!-- 게시글 제목 -->
								<div class="seller_mycomments_title">
									<!-- <a href="./../../app/community/viewOwnPost.html"></a> -->
									<c:out value="${comment.postTitle}"/>
								</div>
								<div class="seller_mycomments_title">
									<!-- <a href="./../../app/community/viewOwnPost.html"></a> -->
									<c:out value="${comment.memberId}"/>
								</div>
								<!-- 댓글 내용 -->
								<div class="seller_mycomments_comments_info"><c:out value="${comment.commentContent}"/></div>
								<!-- 작성일자 -->
								<div class="seller_mycomments_date"><c:out value="${comment.commentedDate}"/></div>
							</div>
							</c:forEach>
						</c:when>
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
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>