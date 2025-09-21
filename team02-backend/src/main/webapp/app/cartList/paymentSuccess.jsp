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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cartList/paymentSuccess.css">

  <!-- JS -->
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>

  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권 - 결제 완료</title>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />

  <!-- 결제 성공 페이지 -->
  <div id="payment_page" class="success">
    <div class="wrap">
      <div class="box">
        <div class="check">
          <!-- 체크 아이콘 -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
               width="90" height="90">
            <circle cx="256" cy="256" r="240" fill="#4caf50" />
            <polyline points="150,270 230,350 370,190"
                      fill="none" stroke="#fff" stroke-width="40"
                      stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </div>
        <h3>결제가 완료되었습니다 🎉</h3>
        <p>저희 <strong>밥세권</strong>을 이용해주셔서 감사합니다.<br>더 좋은 서비스로 보답하겠습니다.</p>

        <!-- 주문정보 -->
        <div class="order_info">
          <p>주문번호: <strong>${orderId}</strong></p>
          <p>총 결제금액: <strong>${amount}원</strong></p>
        </div>

        <!-- 버튼 영역 -->
        <div class="btn_group">
          <a href="${pageContext.request.contextPath}/orders/storeList.or" class="buylist_btn">가게목록 보기</a>
          <a href="${pageContext.request.contextPath}/cartList/view.cl" class="buylist_btn secondary">장바구니 보기</a>
          <a href="${pageContext.request.contextPath}/" class="buylist_btn tertiary">메인으로 가기</a>
        </div>
      </div>
    </div>
  </div>

	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
