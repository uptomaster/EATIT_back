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
  <div id="payment_fail">
    <div class="wrap">
      <div class="box">
        <div class="check">
          <!-- X 아이콘 -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
            <path fill="#ffffff" d="M183.1 137.4C170.6 124.9 150.3 124.9 137.8 137.4C125.3 149.9 125.3 170.2 137.8 182.7L275.2 320L137.9 457.4C125.4 469.9 125.4 490.2 137.9 502.7C150.4 515.2 170.7 515.2 183.2 502.7L320.5 365.3L457.9 502.6C470.4 515.1 490.7 515.1 503.2 502.6C515.7 490.1 515.7 469.8 503.2 457.3L365.8 320L503.1 182.6C515.6 170.1 515.6 149.8 503.1 137.3C490.6 124.8 470.3 124.8 457.8 137.3L320.5 274.7L183.1 137.4z"/>
          </svg>
        </div>
        <h3>주문을 완료하지 못했습니다</h3>
        
        <!-- 장바구니로 이동 -->
        <a href="${pageContext.request.contextPath}/cartList/view.cl">
          <div class="buylist_btn">장바구니로 이동</div>
        </a>
      </div>
    </div>
  </div>

  <footer id="footer"></footer>
</body>
</html>
