<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-------------------- 헤더 ------------------------>
<header id="header">
	<nav id="header_login">
		<!-- 로그인 전 네비게이션-로그인/회원가입 -->
		<ul>
			<c:choose>
				<c:when test="${empty sessionScope.memberNumber}">
					<!-- 로그인 페이지 이동처리 -->
					<li><a
						href="${pageContext.request.contextPath}/login/login.lo">로그인</a></li>
					<!-- 회원가입 페이지 이동처리 -->
					<li><a href="/app/join/selectUserType.jsp">회원가입</a></li>
					<!-- 추후수정필요   -->
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${sessionScope.memberType eq 'GENERAL'}">
							<li><a
								href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">마이페이지</a></li>
						</c:when>
						<c:when test="${sessionScope.memberType eq 'SELLER'}">
							<li><a
								href="${pageContext.request.contextPath}/sellerMyPage/chkPw.se">마이페이지</a></li>
						</c:when>
					</c:choose>
					<li><a
						href="${pageContext.request.contextPath}/login/logoutOk.lo">로그아웃</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<!-- 헤더 중앙정렬 영역 -->
	<div id="header_wrapper">
		<!-- 메인 로고 이미지-->
		<div id="header_logo">
			<a href="${pageContext.request.contextPath}/main.jsp"> <img
				src="${pageContext.request.contextPath}/assets/img/header_logo.png"
				alt="밥세권 로고">
			</a>
		</div>
		<!-- 메인 네비게이션 -->
		<nav id="header_nav">
			<ul>
				<li><a
					href="${pageContext.request.contextPath}/orders/storeList.or">구매</a></li>
				<li><a
					href="${pageContext.request.contextPath}/community/communityMainOk.co">커뮤니티</a></li>
				<li><a
					href="${pageContext.request.contextPath}/community/customerServiceListOk.co">고객센터</a></li>
			</ul>
		</nav>
	</div>
	<!-- 메인 네비게이션 드롭다운 메뉴 -->
	<div id="header_nav_display">
		<div class="header_nav_dropdown">
			<ul class="header_nav_buy">
				<li><a
					href="${pageContext.request.contextPath}/orders/storeList.or">음식 구매</a></li>
				<li><a
					href="${pageContext.request.contextPath}/orders/ingredientList.or">재료 구매</a></li>
			</ul>
			<ul class="header_nav_commu">
				<li><a
					href="${pageContext.request.contextPath}/community/communityMainOk.co">공지사항/이벤트</a></li>
				<li><a
					href="${pageContext.request.contextPath}/community/freeBoardListOk.co">자유게시판</a></li>
				<li><a
					href="${pageContext.request.contextPath}/community/promoBoardListOk.co">홍보게시판</a></li>
				<li><a
					href="${pageContext.request.contextPath}/community/recipeListOk.co">레시피공유</a></li>
			</ul>
		</div>
	</div>
</header>