<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/cartList/paymentSuccess.css">
  <script defer src="./../../assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
</head>
<body>
  <header id="header"></header>

  <!-- 결제 실패 페이지 -->
  <div id="payment_success">
    <!-- 결제실패 > 중앙정렬 1100px 영역-->
    <div class="wrap">
      <!-- 주문실패 안내박스 -->
      <div class="box">
        <div class="check">
          <!-- x표 아이콘 -->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
            <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
            <path fill="#ffffff" d="M183.1 137.4C170.6 124.9 150.3 124.9 137.8 137.4C125.3 149.9 125.3 170.2 137.8 182.7L275.2 320L137.9 457.4C125.4 469.9 125.4 490.2 137.9 502.7C150.4 515.2 170.7 515.2 183.2 502.7L320.5 365.3L457.9 502.6C470.4 515.1 490.7 515.1 503.2 502.6C515.7 490.1 515.7 469.8 503.2 457.3L365.8 320L503.1 182.6C515.6 170.1 515.6 149.8 503.1 137.3C490.6 124.8 470.3 124.8 457.8 137.3L320.5 274.7L183.1 137.4z"/>
          </svg>
        </div>
        <!-- 주문실패 메시지 -->
        <h3>주문을 완료하지 못했습니다</h3>
        <!-- 장바구니이동 버튼 -->
        <a href="./../../app/cartList/shoppingList.html">
          <div class="buylist_btn">장바구니로 이동</div>
        </a>
        
      </div>
    </div>
  </div>

  <footer id="footer"></footer>
</body>
</html>