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
	href="${pageContext.request.contextPath}/assets/css/join/joinAgreementSeller.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/join/joinAgreementSeller.js"></script>
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
			<!-- 회원가입 단계 동그라미 4개 -->
			<ul class="join_step">
				<li>
					<div class="join_step_user_type">회원선택</div>
				</li>
				<li>
					<div class="join_step_agreement">약관동의</div>
				</li>
				<li>
					<div class="join_step_input_info">정보입력</div>
				</li>
				<li>
					<div class="join_step_final">가입완료</div>
				</li>
			</ul>
			<!--//.join_step-->
			<!-- 약관동의 컨테이너 -->
			<div class="seller_join_agree_container">
				<div class="join_agree_title">밥세권 사업자 회원가입</div>
				<!-- 약관동의 체크 칸 -->
				<form action="" method="post" class="join_agree">
					<div class="join_agree_all">
						<input type="checkbox" name="joinAgreeAll"
							id="join_agree_all_chkbox" class=""> <label
							for="join_agree_all_chkbox">전체 동의</label>
					</div>
					<!-- 서비스 이용 약관 -->
					<div class="join_agree_top_term_container">
						<input type="checkbox" name="essenAgree" class="essential"
							id="join_agree_top_term"> <label
							for="join_agree_top_term">[필수] 이용 약관 동의</label>
						<button type="button" class="term-view" data-open-modal="service"
							data-title="서비스 이용 약관">전문보기</button>
					</div>
					<!-- 전자금융거래 약관 -->
					<div class="join_agree_top_financial_container">
						<input type="checkbox" name="essenAgree" class="essential"
							id="join_agree_top_financial"> <label
							for="join_agree_top_financial">[필수] 전자금융거래 이용약관 동의</label>
						<button type="button" class="term-view"
							data-open-modal="financial" data-title="전자금융거래 이용약관">전문보기
						</button>
					</div>
					<hr>
					<div>
						<!-- 개인정보 수집 및 이용약관 - 선택 -->
						<div class="join_agree_bottom_service_continer">
							<input type="checkbox" name="agree"
								id="join_agree_bottom_service"> <label
								for="join_agree_bottom_service">[선택] 서비스/이벤트 제공을 위한 개인정보
								수집 이용동의</label>
							<button type="button" class="term-view"
								data-open-modal="marketing" data-title="개인정보 수집·이용(선택)">전문보기
							</button>
						</div>
						<!-- 개인정보 수집 및 이용약관 - 필수 (id 오타 수정) -->
						<div class="join_agree_bottom_personal">
							<input type="checkbox" name="essenAgree"
								id="join_agree_bottom_personal"> <label
								for="join_agree_bottom_personal">[필수] 밥세권 회원 연동을 위한 개인정보
								수집 이용 동의</label>
							<button type="button" class="term-view"
								data-open-modal="personal" data-title="개인정보 수집·이용(필수)">전문보기
							</button>
						</div>
					</div>
					<!-- 다음버튼 -->
					<div class="join_agree_next">
						<button type="button" class="join_agree_next_btn"
							onclick="goNextPage()">다음</button>
					</div>
				</form>

			</div>
			<!-- //.seller_join_agree_container -->
		</div>
		<!--//.container-->
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
	<!-- 모달 -->
	<div class="modal" id="termsModal" role="dialog" aria-modal="true"
		hidden>
		<div class="modal__backdrop" data-close></div>
		<div class="modal__panel" tabindex="-1"
			aria-labelledby="termsModalTitle">
			<header class="modal__header">
				<h2 id="termsModalTitle" class="modal__title">약관</h2>
				<button type="button" class="modal__close" aria-label="닫기"
					data-close>&times;</button>
			</header>
			<div class="modal__body"></div>
			<footer class="modal__footer">
				<button type="button" class="btn btn--ghost" data-close>닫기</button>
			</footer>
		</div>
	</div>

	<!-- 약관 전문 -->
	<template id="tmpl-service">
		<p>서비스 이용 약관 전문(사업자용) 내용…</p>
	</template>
	<template id="tmpl-financial">
		<p>전자금융거래 이용약관 전문 내용…</p>
	</template>
	<template id="tmpl-marketing">
		<p>[선택] 개인정보 수집·이용 전문 내용…</p>
	</template>
	<template id="tmpl-personal">
		<p>[필수] 개인정보 수집·이용 전문 내용…</p>
	</template>
</body>
</html>