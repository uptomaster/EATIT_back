<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 상세</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/admin/memberDetail.css">
</head>
<body>
	<div class="admin_innerwrapper">
		<!-- 좌측 사이드바 -->
		<aside class="sidebar">
			<a href="${pageContext.request.contextPath}/admin/dashboard.ad">
				<img
				src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
				alt="admin_logo" class="admin_logo">
			</a>
			<ul class="sidebar_ul">
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
				<li class="sidebar_list active" id="sidebar_list_member"><a
					href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글
						관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
				<li class="sidebar_list"><a
					href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
			</ul>
			<form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
				method="post">
				<button id="admin_logoutbtn">로그아웃</button>
			</form>
		</aside>

		<!-- 메인 컨텐츠 -->
		<div class="admin_inner">
			<h1 class="admin_pagetitle">회원 상세</h1>
			<div class="admin_listwrapper">

				<!-- 회원번호 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>회원번호</p></div>
					<div class="admin_memberDetail_value"><p>${memberDetail.memberNumber}</p></div>
				</div>

				<!-- 아이디 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>아이디</p></div>
					<div class="admin_memberDetail_value"><p>${memberDetail.memberId}</p></div>
				</div>

				<!-- 이름 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>이름</p></div>
					<div class="admin_memberDetail_value"><p>${memberDetail.memberName}</p></div>
				</div>

				<!-- 유형 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>회원 유형</p></div>
					<div class="admin_memberDetail_value"><p>${memberDetail.memberType}</p></div>
				</div>

				<!-- 경고수 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>경고 수</p></div>
					<div class="admin_memberDetail_value"><p>${memberDetail.warningCount}</p></div>
				</div>

				<!-- 등급 -->
				<div class="admin_memberDetail_rowWrapper">
					<div class="admin_memberDetail_key"><p>등급</p></div>
					<div class="admin_memberDetail_value">
						<p>
							<c:out value="${memberDetail.treeGrade}" />
							<img class="grade_icon" src="${pageContext.request.contextPath}/assets/img/씨앗.png" alt="등급아이콘">
							(<c:out value="${memberDetail.paymentAmount}" />)
						</p>
					</div>
				</div>

				<!-- 버튼 영역 -->
				<div class="admin_memberDetail_rowWrapper_btn">
					<form action="${pageContext.request.contextPath}/admin/member/warningOk.ad" method="post" style="display:inline;">
						<input type="hidden" name="memberNumber" value="${memberDetail.memberNumber}">
						<input type="hidden" name="memberType" value="${memberDetail.memberType}">
						<button type="submit" id="button_warning">⚠ 경고 주기</button>
					</form>
					<!-- 목록으로 버튼 추가 -->
					<button type="button" id="button_back" onclick="location.href='${pageContext.request.contextPath}/admin/member/list.ad'">목록으로</button>
				</div>

			</div>
		</div>
	</div>

  <!-- 알림 스크립트 -->
  <c:if test="${not empty param.warningSuccess}">
    <script>
      alert("경고를 부여했습니다.");
      location.href = "${pageContext.request.contextPath}/admin/member/list.ad";
    </script>
  </c:if>
</body>
</html>
