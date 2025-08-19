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
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
</head>
<body>
  <header id="header"></header>

  <!-- 결제완료 성공 페이지 -->
  <div id="payment_success">
    <!-- 결제완료 > 중앙정렬 1100px 영역-->
    <div class="wrap">
      <!-- 주문완료 안내박스 -->
      <div class="box">
        <div class="check">
          <!-- 체크 아이콘-->
          <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
            <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
            <path fill="#ffffff" d="M530.8 134.1C545.1 144.5 548.3 164.5 537.9 178.8L281.9 530.8C276.4 538.4 267.9 543.1 258.5 543.9C249.1 544.7 240 541.2 233.4 534.6L105.4 406.6C92.9 394.1 92.9 373.8 105.4 361.3C117.9 348.8 138.2 348.8 150.7 361.3L252.2 462.8L486.2 141.1C496.6 126.8 516.6 123.6 530.9 134z"/>
          </svg>
        </div>
        <!-- 주문완료 메시지 -->
        <h3>고객님의 주문이 완료되었습니다</h3>
        <!-- 주문목록이동 버튼 -->
        <a href="./../../app/userMyPage/foodPurchaseList.html">
          <div class="buylist_btn">주문목록으로 이동</div>
        </a>
      </div>
    </div>
  </div>

  <footer id="footer"></footer>
</body>
</html>