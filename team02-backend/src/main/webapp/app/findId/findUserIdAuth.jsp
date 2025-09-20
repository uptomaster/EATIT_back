<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/findId/findUserIdAuth.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/findId/findUserIdAuth.js"></script>
<title>밥세권</title>
<script>
	let headerPath = '../../header.jsp';
	let footerPath = '../../footer.jsp';
</script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<main>
		<div class="container">
			<!-- 1100px 영역 -->
			<div class="content_container">
				<!-- 컨텐츠 영역 -->
				<!-- 아이디 찾기 제목 -->
				<div class="findId_title_container">
					<div class="findId_title">밥세권</div>
					<div class="findId_subtitle">아이디찾기</div>
				</div>
				<!-- 아이디 찾기 입력 폼 -->
				<form action="${pageContext.request.contextPath}/findId/findIdOk.fi"
					method="post" class="id-form"
					data-found-id="<c:out value='${foundId}'/>"
					data-context-path="${pageContext.request.contextPath}" novalidate>

					<div class="form-grid">
						<!-- row 1 : 이름 -->
						<label for="findIdAuth_input_name" class="lbl">이름 :</label> <input
							type="text" id="findIdAuth_input_name"
							name="findIdAuth_input_name" />
						<div class="spacer"></div>
						<!-- 버튼 칼럼 비우기 -->

						<!-- row 2 : 전화번호 + 인증요청 -->
						<label for="findIdAuth_input_phone" class="lbl">전화번호 :</label> <input
							type="text" id="findIdAuth_input_phone"
							name="findIdAuth_input_phone" placeholder="-는 제외하고 입력해주세요" />
						<button type="button" class="btn ghost findIdAuth_req_auth" id="btn_req_auth">인증요청</button>

						<!-- row 3 : 인증번호 + 인증확인 -->
						<label for="findIdAuth_input_auth" class="lbl">인증번호 :</label> <input
							type="text" id="findIdAuth_input_auth"
							name="findIdAuth_input_auth" />
						<button type="button" class="btn ghost findIdAuth_chk_auth" id="btn_chk_auth">인증확인</button>
					</div>

					<!-- 경고/메시지 영역(필요 시 노출) -->
					<p class="msg findIdAuth_input_warning" id="findId_warning" style="display: none"></p>

					<!-- 제출 버튼 -->
					<div class="submit-row">
						<button type="submit" class="btn primary">아이디 찾기</button>
					</div>
				</form>
				<!-- 아이디 찾기에서 다른 곳으로 이동 -->
				<div class="findId_to_container">
					<div>
						<a
							href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp"
							class="finId_to_findId">아이디 찾기</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp"
							class="finId_to_findPw">비밀번호 찾기</a>
					</div>
					<div>
						<a
							href="${pageContext.request.contextPath}/app/join/selectUserType.jsp"
							class="finId_to_join">회원가입</a>
					</div>
				</div>
			</div>
			<!--//.content_container-->
		</div>
		<!-- 1100px 영역 끝-->
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>