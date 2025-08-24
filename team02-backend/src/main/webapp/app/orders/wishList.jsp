<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/buy/wishList.css">
  <script defer src="./../../assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <script defer src="./../../assets/js/header.js"></script>
  <script defer src="./../../assets/js/buy/wishList.js"></script>
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
        <h2>📌나의 찜 모음</h2>

        <!-- 음식점 정보 영역 -->
        <div class="buy_area">
          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/ddeokbokki.jpg" alt="떡볶이">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">신승떡볶이</p>
                <p class="wish_open_time">영업시간 10:00~22:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/dongas.jpg" alt="돈까스">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">돈까스마을</p>
                <p class="wish_open_time">영업시간 11:00~21:30</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/gimbab.jpg" alt="김밥">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>  
              <div class="buy_store_info">
                <p class="wish_store_name">참김밥</p>
                <p class="wish_open_time">영업시간 09:00~20:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/jjajang.jpg" alt="짜장면">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">홍콩반점</p>
                <p class="wish_open_time">영업시간 11:00~22:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/chicken.jpg" alt="치킨">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">옛날치킨</p>
                <p class="wish_open_time">영업시간 12:00~00:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/bibim.jpg" alt="비빔밥">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">한촌설렁탕</p>
                <p class="wish_open_time">영업시간 10:00~21:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/salad.jpg" alt="샐러드">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">그릭데이</p>
                <p class="wish_open_time">영업시간 11:00~20:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/gimchiJjigae.jpg" alt="김치찌개">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">한식대첩</p>
                <p class="wish_open_time">영업시간 10:30~22:00</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/storeDetail.html">
              <div class="wish_img_wrapper">
                <img src="./../../assets/img/gimchiJjigae.jpg" alt="김치찌개">
                <img src="./../../assets/img/heart_active.png" alt="찜" class="heart_icon" >
              </div>
              <div class="buy_store_info">
                <p class="wish_store_name">한식대첩</p>>
                <p class="wish_open_time">영업시간 10:30~22:00</p>
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

</html>F