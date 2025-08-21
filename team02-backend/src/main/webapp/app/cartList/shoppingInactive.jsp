<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/cartList/shoppingInactive.css">
  <!-- js -->
  <script defer src="./../../assets/js/cartList/shoppingList.js"></script>
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

  <main id="shopping_list">
    <div id="wrap">
      <!-- 상단 제목 영역 -->
      <div class="shopping_header">장바구니🛒</div>

      <!-- content 전체 영역 -->
      <div class="shopping_content">
        <!-- 왼쪽 전체 감싸는 영역  -->
        <div class="shopping_cart_area">

          <!-- 전체선택+체크박스 영역 -->
          <div class="shopping_select_all">
            <!-- 전체 선택 체크박스 -->
            <form action="" method="get">
              <input type="checkbox"> 전체선택
            </form>
            
            <!-- 선택 삭제 btn -->
            <button type="submit" class="shopping_select_delete">선택삭제</button>
          </div>
          
        </div>

        <!-- 오른쪽 전체 감싸는 영역 -->
        <div class="shopping_payment_area">

          <!-- 결제수단 선택 -->
          <div class="shopping_payment_method">
            <div class="shopping_payment_title">결제수단</div>
            <!-- 결제 api 공간 -->
            <div class="shopping_payment_api">
              <form action="" method="post">
                <label><input type="radio" name="payment"> 신용/체크카드</label>
                <label><input type="radio" name="payment"> 무통장입금</label>
                <label><input type="radio" name="payment"> 네이버페이</label>
                <label><input type="radio" name="payment"> 카카오페이</label>
              </form>
            </div>
          </div>

          <!-- 결제금액 요약 -->
          <div class="shopping_payment_summary">
            <div class="shopping_payment_title">결제금액</div>
            <div class="shopping_price_row">
              <span>결제예정금액</span>
              <span>6,000원</span>
            </div>
          </div>

          <!-- 결제하기 btn -->
          <a href="#">
            <div class="shopping_payment_btn">결제하기</div>
          </a>
        </div>

      </div>
    </div>
  </main>

  <footer id="footer"></footer>
</body>

</html>