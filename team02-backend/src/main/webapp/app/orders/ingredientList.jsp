<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="./../../assets/css/header.css" >
  <link rel="stylesheet" href="./../../assets/css/footer.css" >
  <link rel="stylesheet" href="./../../assets/css/buy/ingredientList.css">
  <link rel="stylesheet" href="./../../assets/css/cartList/paymentSuccess.css">
  <script defer src="./../../assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="./../../assets/js/buy/ingredientList.js"></script>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
</head>

<body>
  <header id="header"></header>

  <!-- 구매>재료구매 가게 목록 리스트  -->
  <main id="buy">
    <!-- 중앙정렬 1100px 영역 -->
    <div class="wrap">
      <!-- 컨텐츠 전체영역 -->
      <div class="buy_store_list">
        <h2>재료 구매🥕</h2>
        <form id="searchForm" action="#" method="get">
          <input type="text" name="buy_search" id="buy_search" placeholder="재료나 가게를 찾아보세요!" autocomplete="off" />
        </form>
        <ul class="buy_array">
          <li><a href="#">최신순</a></li>
          <li><a href="#">거리순</a></li>
        </ul>

        <!-- 음식점 정보 영역 -->
        <div class="buy_area" id="buy_area">
          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/sigumchee.jpg" alt="시금치" />
              <div class="buy_store_info">
                <p class="buy_store_name">김밥천국 역삼점</p>
                <p class="buy_menu_name">시금치</p>
                <p class="buy_open_time">영업시간 09:00~21:00</p>
                <p class="buy_price">1,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/carrot.jpg" alt="당근" />
              <div class="buy_store_info">
                <p class="buy_store_name">김밥천국 역삼점</p>
                <p class="buy_menu_name">당근</p>
                <p class="buy_open_time">영업시간 10:00~22:00</p>
                <p class="buy_price">1,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/baechu.jpg" alt="배추" />
              <div class="buy_store_info">
                <p class="buy_store_name">배추공장장</p>
                <p class="buy_menu_name">배추</p>
                <p class="buy_open_time">영업시간 08:00~20:00</p>
                <p class="buy_price">2,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/onion.jpg" alt="양파" />
              <div class="buy_store_info">
                <p class="buy_store_name">양파집</p>
                <p class="buy_menu_name">양파</p>
                <p class="buy_open_time">영업시간 11:00~23:00</p>
                <p class="buy_price">2,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/potato.jpg" alt="감자" />
              <div class="buy_store_info">
                <p class="buy_store_name">감자집</p>
                <p class="buy_menu_name">감자</p>
                <p class="buy_open_time">영업시간 07:00~19:00</p>
                <p class="buy_price">3,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/sweetpotato.jpg" alt="고구마" />
              <div class="buy_store_info">
                <p class="buy_store_name">고구마집</p>
                <p class="buy_menu_name">고구마</p>
                <p class="buy_open_time">영업시간 12:00~00:00</p>
                <p class="buy_price">3,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/cucumber.jpg" alt="오이" />
              <div class="buy_store_info">
                <p class="buy_store_name">오이는 싫어요</p>
                <p class="buy_menu_name">오이</p>
                <p class="buy_open_time">영업시간 09:00~22:00</p>
                <p class="buy_price">4,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/lettuce.jpg" alt="상추" />
              <div class="buy_store_info">
                <p class="buy_store_name">한우사랑</p>
                <p class="buy_menu_name">상추</p>
                <p class="buy_open_time">영업시간 10:00~21:00</p>
                <p class="buy_price">4,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/green_onion.jpg" alt="파" />
              <div class="buy_store_info">
                <p class="buy_store_name">마제소바 역삼점</p>
                <p class="buy_menu_name">파</p>
                <p class="buy_open_time">영업시간 09:00~22:00</p>
                <p class="buy_price">4,000원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/garlic.jpg" alt="마늘" />
              <div class="buy_store_info">
                <p class="buy_store_name">더이상 아는 가게가 없다</p>
                <p class="buy_menu_name">마늘</p>
                <p class="buy_open_time">영업시간 10:00~21:00</p>
                <p class="buy_price">4,500원</p>
              </div>
            </a>
          </article>

          <article class="buy_food_article">
            <a href="./../buy/ingredientDetail.html">
              <img src="./../../assets/img/garlic.jpg" alt="마늘" />
              <div class="buy_store_info">
                <p class="buy_store_name">더이상 아는 가게가 없다</p>
                <p class="buy_menu_name">마늘</p>
                <p class="buy_open_time">영업시간 10:00~21:00</p>
                <p class="buy_price">4,500원</p>
              </div>
            </a>
          </article>

        </div>

        <!-- 페이지네이션 -->
        <div class="buy_pagenation" id="pagination"></div>

      </div>
    </div>
  </main>

  <footer id="footer"></footer>

  <script src="./../../assets/js/buy/storeList.js"></script>
</body>

</html>