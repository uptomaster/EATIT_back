<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cartList/paymentFail.css">

  <!-- JS -->
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>

  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권 - 결제 실패</title>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />

  <!-- 결제 실패 페이지 -->
  <div id="payment_page" class="fail">
    <div class="wrap">
      <div class="box">
        <div class="check">
          <!-- 실패 아이콘 -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
               width="90" height="90">
            <circle cx="256" cy="256" r="240" fill="#f44336" />
            <line x1="170" y1="170" x2="342" y2="342"
                  stroke="#fff" stroke-width="40" stroke-linecap="round" />
            <line x1="342" y1="170" x2="170" y2="342"
                  stroke="#fff" stroke-width="40" stroke-linecap="round" />
          </svg>
        </div>
        <h3>주문을 완료하지 못했습니다 😢</h3>
        <p>불편을 드려 죄송합니다.<br>다시 시도해주시거나 고객센터로 문의해주세요.</p>

        <!-- 버튼 영역 -->
        <div class="btn_group">
          <a href="${pageContext.request.contextPath}/cartList/view.cl" class="buylist_btn">장바구니로 이동</a>
          <a href="${pageContext.request.contextPath}/" class="buylist_btn secondary">메인으로 가기</a>
        </div>
      </div>
    </div>
  </div>

	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
