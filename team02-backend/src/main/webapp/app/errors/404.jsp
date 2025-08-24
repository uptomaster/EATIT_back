<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>밥세권 | 페이지를 찾을 수 없습니다</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="<c:url value='/assets/css/errors/404.css'/>">
  <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">
</head>
<body>
  <div class="wrap">
    <div class="logo">
      <span class="logo-badge">🍚</span> 밥세권
    </div>

	<!-- c:url value로 JSP에서 안전하게 URL을 생성할 수 있음.
		 컨텍스트 루트경로를 자동으로 포함함.
		 세션 URL을 재작성하는 것을 지원함.
		 쿼리스트링 안전하게 인코딩.
		 상대/절대/외부 링크 처리를 지원함.  -->

    <h1>404</h1>
    <p class="desc">
      요청하신 페이지를 찾을 수 없어요.<br/>
    </p>

    <div class="actions">
      <a class="btn btn-primary" href="<c:url value='/'/>">홈으로 가기</a>
      <a class="btn" href="<c:url value='/cartList/view.cl'/>">장바구니 보기</a>
      <!-- 이전 페이지: 히스토리 없으면 홈으로 폴백 -->
      <a class="btn" href="javascript:(document.referrer?history.back():location.href='<c:url value='/'/>' )">이전 페이지</a>
    </div>

	<p class="tips">
  		계속 문제가 발생하나요? <a href="<c:url value='/inquiry/list.bo'/>">고객센터</a>에 문의해주세요.
	</p>

  </div>
</body>
</html>
