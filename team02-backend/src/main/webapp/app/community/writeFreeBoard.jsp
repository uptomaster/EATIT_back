<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
 <!-- 파비콘 -->
 <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
 <title>밥세권</title>
 
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/community/writeFreeBoard.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/community/treeGrade.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/community/writeFreeBoard.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/community/mouseoverTreeIcon.js"></script>
<script>
	let headerPath = '../../header.jsp';
	let footerPath = '../../footer.jsp';
</script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>

<body>
	<!-- <header id="header"></header> -->
	<jsp:include page="/header.jsp" />

	<main class="layout">
		<aside class="side"></aside>

		<section class="container">
			<div class="title_section">
				<div class="profile_tree">
					${sessionScope.memberId} 
					<img
						src="${pageContext.request.contextPath}/assets/img/나무.png"
						alt="나무" class="tree_icon author_profile" />
				</div>
				<h1 class="post_title">
					<c:choose>
						<c:when test="${postType == 'FREE'}">
		            자유게시글 작성
		        		</c:when>
						<%-- <c:when test="${postType == 'NOTICE'}">
		            공지사항 작성
		        </c:when> --%>
						<c:when test="${postType == 'PROMOTION'}">
		            프로모션 게시글 작성
		       	 		</c:when>
						<c:when test="${postType == 'RECIPE'}">
		            레시피 게시글 작성
		        		</c:when>
						<c:otherwise>
		            게시글 작성
		        		</c:otherwise>
					</c:choose>
				</h1>
			</div>

			<form
				action="${pageContext.request.contextPath}/community/writeFreeBoardOk.co"
				method="post" class="write_form" enctype="multipart/form-data">
				<c:if test="${not empty postType }">
					<input type="hidden" name="postType" value="${postType }" />
				</c:if>

				<div class="form_group">
					<label for="title">제목</label> <input type="text" id="title"
						name="postTitle" placeholder="제목을 입력하세요" required />
				</div>

				<div class="form_group">
					<label for="content">내용</label>
					<c:choose>
					    <c:when test="${postType == 'FREE'}">
					        <textarea id="content" name="freeContent" rows="10" placeholder="내용을 입력하세요" required></textarea>
					    </c:when>
					    <c:when test="${postType == 'PROMOTION'}">
					        <textarea id="content" name="promoContent" rows="10" placeholder="내용을 입력하세요" required></textarea>
					    </c:when>
					    <c:when test="${postType == 'RECIPE'}">
					        <textarea id="content" name="recipeContent" rows="10" placeholder="내용을 입력하세요" required></textarea>
					    </c:when>
					</c:choose>
				</div>
				
				<div class="form_group">
					<label for="imageUpload">사진 첨부</label>
					<input type="file" id="imageUpload" name="uploadFile" />
				</div>

				<div class="button_group">
					<c:choose>
					    <c:when test="${postType == 'FREE'}">
					        <c:url var="listUrl" value="/community/freeBoardListOk.co"/>
					    </c:when>
					    <c:when test="${postType == 'PROMOTION'}">
					        <c:url var="listUrl" value="/community/promoBoardListOk.co"/>
					    </c:when>
					    <c:when test="${postType == 'RECIPE'}">
					        <c:url var="listUrl" value="/community/recipeBoardListOk.co"/>
					    </c:when>
					    <c:otherwise>
					        <c:url var="listUrl" value="/community/freeBoardListOk.co"/>
					    </c:otherwise>
					</c:choose>
					<a class="cancel_btn" href="${listUrl}">작성 취소</a>

					<button type="submit" class="submit_btn">작성 완료</button>

				</div>
			</form>
		</section>

		<aside class="side"></aside>
	</main>

	<jsp:include page="/footer.jsp" />
	<!-- <footer id="footer"></footer> -->
</body>
<script>
	window.memberNumber = "${sessionScope.memberNumber}";
</script>
</html>