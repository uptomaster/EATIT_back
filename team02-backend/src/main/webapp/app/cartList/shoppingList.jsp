<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/cartList/shoppingList.css">
  <!-- js -->
  <script defer src="./../../assets/js/cartList/shoppingList.js"></script>
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

          <!-- 장바구니 음식(재료)리스트 감싸는 전체 영역 -->
          <div class="shopping_cart_list">
            
            <!-- 상품 사진, 상호명, 가격 등 상세 정보 전체 영역 -->
            <div class="shopping_cart_store">

              <!-- 가게 전체 체크박스 -->
              <div class="shopping_store_header">
                <p>담뿍화로된장찌개 역삼점</p>
              </div>
              <hr>

              <!-- 상품 정보 첫번째 영역 -->
              <div class="shopping_cart_item">
                <!-- 상품 개별 선택 체크박스 -->
                <form action="" method="get">
                  <input type="checkbox" class="shopping_item_check">
                </form>
                
                <!-- 음식/재료 이미지 영역 -->
                <img src="./../../assets/img/doenjangJjigae.png" alt="">
                <!-- 메뉴명, 소비기한,가격 표기 -->
                <div class="shopping_item_info">
                  <div class="shopping_stock">3개 남음</div>
                  <div class="shopping_item_name">화로 된장찌개</div>
                  <div class="shopping_item_expiry">소비기한: 2025년 08월 15일</div>
                  <div class="shopping_item_price">5,000원</div>
                </div>

                <!-- 음식 수량 증감 버튼 -->
                <div class="shopping_item_cnt">
                  <!-- minus icon -->
                  <a href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                      <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                      <path fill="#202020"
                        d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM232 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296z" />
                    </svg>
                  </a>
                  <span>1</span>
                  <!-- plus icon -->
                  <a href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                      <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                      <path fill="#202020"
                        d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM296 408L296 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296L296 296L296 232C296 218.7 306.7 208 320 208C333.3 208 344 218.7 344 232L344 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L344 344L344 408C344 421.3 333.3 432 320 432C306.7 432 296 421.3 296 408z" />
                    </svg>
                  </a>
                </div>
              </div>

              <!-- 상품 정보 2번째 영역  -->
              <div class="shopping_cart_item">
                <!-- 상품 개별 선택 체크박스 -->
                <form action="" method="get">
                  <input type="checkbox" class="shopping_item_check">
                </form>
                
                <!-- 음식/재료 이미지 영역 -->
                <img src="./../../assets/img/bab.jpg" alt="">
                <!-- 메뉴명, 소비기한,가격 표기 -->
                <div class="shopping_item_info">
                  <div class="shopping_stock">10개 남음</div>
                  <div class="shopping_item_name">공기밥</div>
                  <div class="shopping_item_expiry">소비기한: 2025년 08월 15일</div>
                  <div class="shopping_item_price">1,000원</div>
                </div>

                <!-- 음식 수량 증감 버튼 -->
                <div class="shopping_item_cnt">
                  <!-- minus icon -->
                  <a href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                      <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                      <path fill="#202020"
                        d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM232 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296z" />
                    </svg>
                  </a>
                  <span>1</span>
                  <!-- plus icon -->
                  <a href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                      <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                      <path fill="#202020"
                        d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM296 408L296 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296L296 296L296 232C296 218.7 306.7 208 320 208C333.3 208 344 218.7 344 232L344 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L344 344L344 408C344 421.3 333.3 432 320 432C306.7 432 296 421.3 296 408z" />
                    </svg>
                  </a>
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