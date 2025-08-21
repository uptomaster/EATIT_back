<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <link rel="stylesheet" href="./../../assets/css/buy/reviewList.css">
  <link rel="stylesheet" href="./../../assets/css/buy/storeDetail.css">
  <script defer src="./../../assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  <script defer src="./../../assets/js/buy/storeDetail.js"></script>
  <script defer src="./../../assets/js/buy/reviewList.js"></script>
</head>
<body>
  <header id="header"></header>

  <!-- 구매 > 가게상세 및 상품리스트 페이지(가게정보)표기 -->
  <main id="buy_store_detail">

    <div class="wrap">

      <!-- 가게 정보 및 메뉴 영역 -->
      <div class="buy_left_area">
        <div class="buy_store_info">
          <img src="./../../assets/img/store_gimbab.jpg" alt="가게 이미지">
          <div class="buy_add_heart">
            <img src="./../../assets/img/heart_inactive.png" alt="찜 버튼" id="heartBtn">
          </div>
          <div class="buy_store_info_detail">
            <p class="buy_store_name">푸드카페 김밥천국 역삼역점</p>
            <p class="buy_store_address">서울시 강남구 역삼동</p>
            <p class="buy_store_open_time">영업시간: 09:00 ~ 21:00</p>
          </div>
        </div>

        <!-- 메뉴 -->
        <div class="buy_food">
          <ul class="buy_food_menu_choice">
            <li><a class="link_menu" href="./../buy/storeDetail.html">메뉴</a></li>
            <li><a class="link_ingredient" href="./../buy/ingredientDetail.html">재료</a></li>
            <li><a class="link_review" href="#">리뷰 보기</a></li>
          </ul>

          <!-- 첫번째 리뷰 영역 -->
          <div class="buy_review_area">
            <!-- 리뷰사진 -->
            <img src="./../../assets/img/budaeJjigae.png" alt="리뷰 사진" class="buy_review_img">

            <div class="buy_review_detail">
              <div class="buy_review_detail_top">
                <ul class="buy_review_user_info">
                  <li class="buy_review_id">abc123</li>
                  <li class="buy_review_star">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                  </li>
                </ul>
                <ul class="buy_review_dates">
                  <li class="buy_review_purchase_date">구매일 2025.08.05</li>
                  <li class="buy_review_write_date">작성일 2025.08.05</li>
                </ul>
              </div>
              <h4>[best]매운돼지불백</h4>
              <p>매콤한 양념이 고기에 잘 배어 있어서 밥이랑 정말 잘 어울렸어요. 양도 넉넉하고 반찬 구성도 괜찮아서 점심 한 끼로 만족스럽습니다! 매운 거 좋아하는 분들께 추천합니다 😊</p>
            </div>
          </div>

          <!-- 두번째 리뷰 영역 -->
          <div class="buy_review_area">
            <!-- 리뷰사진 -->
            <img src="./../../assets/img/budaeJjigae.png" alt="리뷰 사진" class="buy_review_img">

            <div class="buy_review_detail">
              <div class="buy_review_detail_top">
                <ul class="buy_review_user_info">
                  <li class="buy_review_id">abc123</li>
                  <li class="buy_review_star">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                  </li>
                </ul>
                <ul class="buy_review_dates">
                  <li class="buy_review_purchase_date">구매일 2025.08.05</li>
                  <li class="buy_review_write_date">작성일 2025.08.05</li>
                </ul>
              </div>
              <h4>[best]매운돼지불백</h4>
              <p>매콤한 양념이 고기에 잘 배어 있어서 밥이랑 정말 잘 어울렸어요. 양도 넉넉하고 반찬 구성도 괜찮아서 점심 한 끼로 만족스럽습니다! 매운 거 좋아하는 분들께 추천합니다 😊</p>
            </div>
          </div>

          <!-- 세번째 리뷰 영역 -->
          <div class="buy_review_area">
            <!-- 리뷰사진 -->
            <img src="./../../assets/img/budaeJjigae.png" alt="리뷰 사진" class="buy_review_img">

            <div class="buy_review_detail">
              <div class="buy_review_detail_top">
                <ul class="buy_review_user_info">
                  <li class="buy_review_id">abc123</li>
                  <li class="buy_review_star">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                    <img src="./../../assets/img/counting_Star.png" alt="별">
                  </li>
                </ul>
                <ul class="buy_review_dates">
                  <li class="buy_review_purchase_date">구매일 2025.08.05</li>
                  <li class="buy_review_write_date">작성일 2025.08.05</li>
                </ul>
              </div>
              <h4>[best]매운돼지불백</h4>
              <p>매콤한 양념이 고기에 잘 배어 있어서 밥이랑 정말 잘 어울렸어요. 양도 넉넉하고 반찬 구성도 괜찮아서 점심 한 끼로 만족스럽습니다! 매운 거 좋아하는 분들께 추천합니다 😊</p>
            </div>
          </div>

          <!-- 페이지네이션 -->
          <div id="pagination" class="pagination_container"></div>
        </div>
      </div>

      <!-- 오른쪽 영역 -->
      <div class="buy_map_area">
        <div class="buy_back_store_list">
          <a href="./storeList.html">음식목록으로 돌아가기</a>
        </div>

        <div class="buy_map"><img src="./../../assets/img/map.jpg"></div>
        <div class="buy_origin">
          <ul class="buy_origin_menu">
            <li class="buy_origin_info"><a href="#" id="storeInfoBtn">가게정보</a></li>
            <li class="buy_origin_info"><a href="#" id="originInfoBtn">원산지</a></li>
          </ul>
          <div class="buy_origin_store_info">
            <ul class="buy_origin_content">
              <li>대표: 송은경</li>
              <li>식당 전화: 02-123-4567</li>
            </ul>
          </div>
          <div class="origin_info_inactive">
            <ul>
              <li>쌀: 국내산</li>
              <li>참치: 태국산</li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!-- 사고보상정책안내 -->
    <div class="buy_policy_toggle_section">
      <p>구매하신 상품에 이상이 있으셨나요? 아래 절차를 따라 요청을 남겨주시면 빠르게 도와드리겠습니다.</p>
      <div class="buy_policy_toggle_header">
        사고보상정책 안내 보기 ▼
      </div>
      <div class="buy_policy_toggle_content">
        <p>1. 음식을 받으신 직후 상태를 확인해주세요. 이상이 있는 부분이 있다면 사진을 촬영해주세요.</p>
        <ul>
          <li>수령 후 1일 이내 접수가 가능합니다.</li>
          <li>문제 부분을 확인할 수 있도록 3~4장 이상 촬영해주세요.</li>
          <li>사진은 이상이 있는 부분과 없는 부분 전체를 확인할 수 있어야 해요.</li>
        </ul>
        <p>2. 홈페이지 로그인 후 고객센터에서 사진과 함께 접수해주세요.</p>
        <p>3. 검토 후 처리 결과를 게시글의 댓글을 통해 확인할 수 있습니다</p>
      </div>
    </div>
    <div class="buy_policy_toggle_section">
      <div class="buy_policy_toggle_header">
        사고보상정책 안내 보기 ▼
      </div>
      <div class="buy_policy_toggle_content">
        <p>1. 음식을 받으신 직후 상태를 확인해주세요. 이상이 있는 부분이 있다면 사진을 촬영해주세요.</p>
        <ul>
          <li>수령 후 1일 이내 접수가 가능합니다.</li>
          <li>문제 부분을 확인할 수 있도록 3~4장 이상 촬영해주세요.</li>
          <li>사진은 이상이 있는 부분과 없는 부분 전체를 확인할 수 있어야 해요.</li>
        </ul>
        <p>2. 홈페이지 로그인 후 고객센터에서 사진과 함께 접수해주세요.</p>
        <p>3. 검토 후 처리 결과를 게시글의 댓글을 통해 확인할 수 있습니다</p>
      </div>
    </div>

  </main>

  <footer id="footer"></footer>
</body>

</html>