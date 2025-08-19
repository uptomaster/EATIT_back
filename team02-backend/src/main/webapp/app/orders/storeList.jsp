<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <link rel="stylesheet" href="./../../assets/css/buy/storeList.css" />
  <link rel="stylesheet" href="./../../assets/css/cartList/paymentSuccess.css" />
  <script defer src="./../../assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="./../../assets/js/buy/storeList.js"></script>
  <script defer src="./../../assets/js/header.js"></script>
  
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon" />
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>

  <!-- 구매>음식점구매 가게 목록 리스트  -->
  <main id="buy">
    <!-- 중앙정렬 1100px 영역 -->
    <div class="wrap">
      <!-- 컨텐츠 전체영역 -->
      <div class="buy_store_list">
        <h2>음식점 구매🥐</h2>
        <form action="" method="get">
          <input type="text" name="buy_search" id="buy_search" placeholder="상호명이나 메뉴를 검색하세요" />
        </form>
        <ul class="buy_array">
          <li><a href="#">최신순</a></li>
          <li><a href="#">거리순</a></li>
        </ul>

        <!-- 음식점 정보 영역 -->
        <div class="buy_area">
          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/ddeokbokki.jpg" alt="떡볶이" />
              <div class="buy_store_info">
                <p class="buy_store_name">신승떡볶이</p>
                <p class="buy_menu_name">떡볶이</p>
                <p class="buy_open_time">영업시간 10:00~22:00</p>
                <p class="buy_price">2,300원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/dongas.jpg" alt="돈까스" />
              <div class="buy_store_info">
                <p class="buy_store_name">돈까스마을</p>
                <p class="buy_menu_name">수제돈까스</p>
                <p class="buy_open_time">영업시간 11:00~21:30</p>
                <p class="buy_price">3,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/gimbab.jpg" alt="김밥" />
              <div class="buy_store_info">
                <p class="buy_store_name">참김밥</p>
                <p class="buy_menu_name">참치김밥</p>
                <p class="buy_open_time">영업시간 09:00~20:00</p>
                <p class="buy_price">1,800원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/jjajang.jpg" alt="짜장면" />
              <div class="buy_store_info">
                <p class="buy_store_name">홍콩반점</p>
                <p class="buy_menu_name">짜장면</p>
                <p class="buy_open_time">영업시간 11:00~22:00</p>
                <p class="buy_price">2,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/chicken.jpg" alt="치킨" />
              <div class="buy_store_info">
                <p class="buy_store_name">옛날치킨</p>
                <p class="buy_menu_name">후라이드치킨</p>
                <p class="buy_open_time">영업시간 12:00~00:00</p>
                <p class="buy_price">6,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/bibim.jpg" alt="비빔밥" />
              <div class="buy_store_info">
                <p class="buy_store_name">한촌설렁탕</p>
                <p class="buy_menu_name">비빔밥</p>
                <p class="buy_open_time">영업시간 10:00~21:00</p>
                <p class="buy_price">4,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/salad.jpg" alt="샐러드" />
              <div class="buy_store_info">
                <p class="buy_store_name">그릭데이</p>
                <p class="buy_menu_name">그릭샐러드</p>
                <p class="buy_open_time">영업시간 11:00~20:00</p>
                <p class="buy_price">3,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/gimchiJjigae.jpg" alt="김치찌개" />
              <div class="buy_store_info">
                <p class="buy_store_name">한식대첩</p>
                <p class="buy_menu_name">김치찌개</p>
                <p class="buy_open_time">영업시간 10:30~22:00</p>
                <p class="buy_price">3,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <img src="./../../assets/img/gimchiJjigae.jpg" alt="김치찌개" />
              <div class="buy_store_info">
                <p class="buy_store_name">한식대첩</p>
                <p class="buy_menu_name">김치찌개</p>
                <p class="buy_open_time">영업시간 10:30~22:00</p>
                <p class="buy_price">3,000원</p>
              </div>
            </a>
          </article>
        </div>

        <!-- 페이지네이션 -->
        <div class="buy_pagenation" id="pagination">

        </div>
      </div>
  </main>

  <footer id="footer"></footer>
</body>

</html>