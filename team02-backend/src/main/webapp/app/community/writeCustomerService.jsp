<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/community/writeCustomerService.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css" />
<link rel="stylesheet" href="./../../assets/css/community/treeGrade.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/community/writeCustomerServiceList.js"></script>
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">

<!-- 헤더 js -->
<script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/gradeModal.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>

<script>
	let headerPath = './../../header.jsp';
	let footerPath = './../../footer.jsp';
</script>
<title>고객센터 - 문의글 작성</title>
</head>

<body>
	<jsp:include page="/header.jsp" />

	<main class="layout">
		<aside class="side"></aside>

		<section class="container">
			<div class="title_section">
				<div class="profile_tree">
					<img src="./../../assets/img/나무.png" alt="나무"
						class="tree_icon author_profile" />
						<c:out value="${inquiry.MemberId}" />
				</div>
				<h1 class="post_title">문의글 작성</h1>
			</div>

			<form action="/community/writeInquiryOk.co" method="post"
				class="write_form" enctype="multipart/form-data">
				<div class="form_group">
					<label for="title">제목</label> <input type="text" id="postTitle"
						name="postTitle" placeholder="제목을 입력하세요" required />
				</div>

				<div class="form_group">
					<label for="content">내용</label>
					<textarea id="inquiryContent" name="inquiryContent" rows="10"
						placeholder="내용을 입력하세요" required></textarea>
				</div>

				<div class="form_group">
					<label for="imageUpload">사진 첨부</label> 
					<input type="file" id="imageUpload" name="uploadFile" />
				</div>

				<div class="button_group">
					<button type="reset" class="cancel_btn">작성 취소</button>
					<button type="submit" class="submit_btn">작성 완료</button>
				</div>
			</form>
		</section>

		<aside class="side"></aside>
	</main>
	<!-------------------- 푸터 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

	<script>
		let memberNumber = "${sessionScope.memberNumber}";
	</script>

</body>