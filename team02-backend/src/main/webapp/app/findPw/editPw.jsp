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
	href="${pageContext.request.contextPath}/assets/css/findPw/editPw.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/findPw/editPw.js"></script>
<title>밥세권</title>
<script>
	let headerPath = '../../header.jsp', footerPath = '../../footer.jsp';
</script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<main>
		<div class="findPw_edit_container">
			<div class="findPw_edit_content_container">
				<form
					action="${pageContext.request.contextPath}/findPw/updatePwOk.fp"
					method="post" class="findPw_edit_form"
					data-context-path="${pageContext.request.contextPath}">

					<div class="findPw_edit_titles">
						<div class="findPw_edit_title">밥세권 회원</div>
						<div class="findPw_edit_subtitle">비밀번호 수정</div>
					</div>

					<!-- [라벨 | 인풋 | (비움)] 3열 그리드 -->
					<div class="form-grid">
						<!-- 새 비밀번호 -->
						<label for="findPw_edit_newPw" class="lbl">새 비밀번호</label> <input
							type="password" id="findPw_edit_newPw" name="findPw_edit_newPw" />
						<div class="spacer"></div>
						<!-- 인풋 아래 메시지 -->
						<div id="findPw_edit_warning_message_new" class="field-msg"
							role="status" aria-live="polite"></div>

						<!-- 새 비밀번호 확인 -->
						<label for="findPw_edit_newPw_chk" class="lbl">새 비밀번호 확인</label>
						<input type="password" id="findPw_edit_newPw_chk"
							name="findPw_edit_newPw_chk" />
						<div class="spacer"></div>
						<!-- 인풋 아래 메시지 -->
						<div id="findPw_edit_warning_message_chk" class="field-msg"
							role="status" aria-live="polite"></div>
					</div>

					<!-- 제출 버튼 -->
					<div class="submit-row">
						<button type="submit" class="btn primary">수정하기</button>
					</div>
				</form>

				<div class="findPw_edit_to_container">
					<div>
						<a
							href="${pageContext.request.contextPath}/app/findId/findUserIdAuth.jsp"
							class="findPw_edit_to_findId">아이디 찾기</a>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/app/findPw/findPw.jsp"
							class="findPw_edit_to_findPw">비밀번호 찾기</a>
					</div>
					<div>
						<a
							href="${pageContext.request.contextPath}/app/join/selectUserType.jsp"
							class="findPw_edit_to_join">회원가입</a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
