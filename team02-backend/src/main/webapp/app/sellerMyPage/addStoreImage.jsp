<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<!-- 메인 컨텐츠 -->
	<main class="inner">
		<h1 class="pagetitle">공지 수정</h1>

		<div class="listwrapper">
			<div class="whitebox">
				<!-- 수정 폼 -->
				<form action="/sellerMyPage/storeImageOk.se" method="post"
					enctype="multipart/form-data">

					<!-- 첨부파일 -->
					<div class="storeimage_filebox">
						<label for="uploadFile">첨부파일</label> 
						<input type="file" id="uploadFile" name="uploadFile" />
						<p class="file_hint">※ 수정 시 기존 이미지는 삭제되고 새 파일만 적용됩니다.</p>
					</div>

					<!-- 버튼 영역 -->
					<div class="bottom_btn_area">
						<button type="submit" id="notice_updatebtn">수정완료</button>
						<button type="button" id="notice_cancelbtn"
							onclick="location.href='/sellerMyPage/storeInfo.se'">취소</button>
					</div>

				</form>
			</div>
		</div>
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>