<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- 정적 리소스 (contextPath 활용) -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cartList/shoppingList.css">

  <!-- JS -->
  <script defer src="${pageContext.request.contextPath}/assets/js/cartList/shoppingList.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>

  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권 - 장바구니</title>
</head>

<body>
  <header id="header"></header>

  <main id="shopping_list">
    <div id="wrap">
      <!-- 상단 제목 영역 -->
      <div class="shopping_header">장바구니🛒</div>

      <!-- content 전체 영역 -->
      <div class="shopping_content">
        <!-- 왼쪽 전체 감싸는 영역 -->
        <div class="shopping_cart_area">

          <!-- 전체선택+체크박스 영역 -->
          <div class="shopping_select_all">
            <!-- 전체 선택 체크박스 -->
            <form action="${pageContext.request.contextPath}/cartList/selectAll.cl" method="post">
              <input type="checkbox"> 전체선택
              <button type="submit" class="shopping_select_delete">선택삭제</button>
            </form>
          </div>

          <!-- 장바구니 음식(재료)리스트 감싸는 전체 영역 -->
          <div class="shopping_cart_list">
            
            <!-- 예시 데이터 (실제는 DB에서 불러와서 JSTL/EL 반복문으로 출력 예정) -->
            <div class="shopping_cart_store">
              <!-- 가게명 -->
              <div class="shopping_store_header">
                <p>담뿍화로된장찌개 역삼점</p>
              </div>
              <hr>

              <!-- 상품 정보 첫번째 영역 -->
              <div class="shopping_cart_item">
                <!-- 상품 개별 선택 체크박스 -->
                <form action="${pageContext.request.contextPath}/cartList/delete.cl" method="post">
                  <input type="checkbox" class="shopping_item_check" name="cartItemId" value="1">
                </form>
                
                <!-- 음식/재료 이미지 영역 -->
                <img src="${pageContext.request.contextPath}/assets/img/doenjangJjigae.png" alt="된장찌개">

                <!-- 메뉴명, 소비기한,가격 -->
                <div class="shopping_item_info">
                  <div class="shopping_stock">3개 남음</div>
                  <div class="shopping_item_name">화로 된장찌개</div>
                  <div class="shopping_item_expiry">소비기한: 2025년 08월 15일</div>
                  <div class="shopping_item_price">5,000원</div>
                </div>

                <!-- 수량 증감 버튼 -->
                <div class="shopping_item_cnt">
                  <a href="${pageContext.request.contextPath}/cartList/decrease.cl?itemId=1">➖</a>
                  <span>1</span>
                  <a href="${pageContext.request.contextPath}/cartList/increase.cl?itemId=1">➕</a>
                </div>
              </div>

              <!-- 상품 정보 두번째 영역 -->
              <div class="shopping_cart_item">
                <form action="${pageContext.request.contextPath}/cartList/delete.cl" method="post">
                  <input type="checkbox" class="shopping_item_check" name="cartItemId" value="2">
                </form>
                
                <img src="${pageContext.request.contextPath}/assets/img/bab.jpg" alt="공기밥">
                <div class="shopping_item_info">
                  <div class="shopping_stock">10개 남음</div>
                  <div class="shopping_item_name">공기밥</div>
                  <div class="shopping_item_expiry">소비기한: 2025년 08월 15일</div>
                  <div class="shopping_item_price">1,000원</div>
                </div>

                <div class="shopping_item_cnt">
                  <a href="${pageContext.request.contextPath}/cartList/decrease.cl?itemId=2">➖</a>
                  <span>1</span>
                  <a href="${pageContext.request.contextPath}/cartList/increase.cl?itemId=2">➕</a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 오른쪽 전체 감싸는 영역 -->
        <div class="shopping_payment_area">
          <!-- 결제수단 선택 -->
          <div class="shopping_payment_method">
            <div class="shopping_payment_title">결제수단</div>
            <form action="${pageContext.request.contextPath}/cartList/checkout.cl" method="post">
              <label><input type="radio" name="payment" value="card"> 신용/체크카드</label>
              <label><input type="radio" name="payment" value="bank"> 무통장입금</label>
              <label><input type="radio" name="payment" value="naver"> 네이버페이</label>
              <label><input type="radio" name="payment" value="kakao"> 카카오페이</label>
              <button type="submit" class="shopping_payment_btn">결제하기</button>
            </form>
          </div>

          <!-- 결제금액 요약 -->
          <div class="shopping_payment_summary">
            <div class="shopping_payment_title">결제금액</div>
            <div class="shopping_price_row">
              <span>결제예정금액</span>
              <span>6,000원</span>
            </div>
          </div>
        </div>

      </div>
    </div>
  </main>

  <footer id="footer"></footer>
</body>
</html>
