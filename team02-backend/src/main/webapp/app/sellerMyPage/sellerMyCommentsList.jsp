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
					<div class="seller_mycomments_id">게시글 작성자</div>
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
								    <c:set var="ctx" value="${pageContext.request.contextPath}" />

						        <%-- postType → 라벨/엔드포인트 매핑 --%>
						        <c:choose>
						          <c:when test="${comment.postType eq 'NOTICE'}">
						            <c:set var="typeLabel" value="공지/이벤트"/>
						            <c:set var="readEndpoint" value="/community/viewOwnPostOk.co"/>
						            <c:set var="listEndpoint" value="/community/communityMainOk.co"/>
						          </c:when>
						          <c:when test="${comment.postType eq 'FREE'}">
						            <c:set var="typeLabel" value="자유게시판"/>
						            <c:set var="readEndpoint" value="/community/freeBoardReadOk.co"/>
						            <c:set var="listEndpoint" value="/community/freeBoardListOk.co"/>
						          </c:when>
						          <c:when test="${comment.postType eq 'PROMOTION'}">
						            <c:set var="typeLabel" value="홍보게시판"/>
						            <c:set var="readEndpoint" value="/community/promoBoardReadOk.co"/>
						            <c:set var="listEndpoint" value="/community/promoBoardListOk.co"/>
						          </c:when>
						          <c:when test="${comment.postType eq 'RECIPE'}">
						            <c:set var="typeLabel" value="레시피"/>
						            <c:set var="readEndpoint" value="/community/recipeBoardReadOk.co"/>
						            <c:set var="listEndpoint" value="/community/recipeListOk.co"/>
						          </c:when>
						          <c:when test="${comment.postType eq 'INQUIRY'}">
						            <c:set var="typeLabel" value="문의"/>
						            <c:set var="readEndpoint" value="/community/inquiryReadOk.co"/>
						            <c:set var="listEndpoint" value="/community/inquiryListOk.co"/>
						          </c:when>
						          <c:when test="${comment.postType eq 'FAQ'}">
						            <c:set var="typeLabel" value="자주묻는질문"/>
						            <c:set var="readEndpoint" value="/community/faqReadOk.co"/>
						            <c:set var="listEndpoint" value="/community/faqListOk.co"/>
						          </c:when>
						        </c:choose>
						
						        <%-- 컨텍스트 프리픽스 + 엔드포인트 --%>
						        <c:set var="listUrl" value="${ctx}${listEndpoint}" />
						        <%-- 요청대로 상세는 ?postNumber=${memberNumber} 를 부착 --%>
						        <c:set var="readUrl" value="${ctx}${readEndpoint}?postNumber=${comment.postNumber}" />

								</div>
								<!-- 게시글 제목 -->
								<div class="seller_mycomments_sort">
									 <a href="${listUrl}">${typeLabel}</a>
								</div>
								<!-- 게시글 제목 -->
								<div class="seller_mycomments_title">
									<a href="${readUrl}"> <c:out value="${comment.postTitle}"/> </a>
								</div>
								<div class="seller_mycomments_id">
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