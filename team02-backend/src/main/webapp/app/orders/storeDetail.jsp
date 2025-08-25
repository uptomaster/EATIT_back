<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/assets/img/placeholder.png" var="ph"/>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>밥세권 - 가게 상세</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders/storeDetail.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/orders/storeDetail.js"></script>
</head>
<body>

  <!-------------------- 헤더 ------------------------>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp">
    <jsp:param name="active" value="purchase"/>
  </jsp:include>

  <!-- 가게 상세 -->
  <main id="buy_store_detail">
    <div class="wrap">

      <!-- 왼쪽 영역 (가게 정보 + 메뉴/재료/리뷰) -->
      <div class="buy_left_area">

        <!-- 가게 기본 정보 -->
        <div class="buy_store_info">
          <c:choose>
            <c:when test="${not empty images}">
              <img src="${images[0].itemImageSystemName}" alt="${item.itemName}">
            </c:when>
            <c:otherwise>
              <img src="${ph}" alt="기본 이미지">
            </c:otherwise>
          </c:choose>

          <div class="buy_store_info_detail">
            <p class="buy_store_name">${item.itemName}</p>
            <p class="buy_store_address">사업자번호 : ${item.businessNumber}</p>
          </div>
        </div>

        <!-- 탭: 메뉴 / 재료 / 리뷰 -->
        <div class="buy_food">
          <ul class="buy_food_menu_choice">
            <li><a class="${param.tab eq 'FOOD' ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&tab=FOOD">메뉴</a></li>
            <li><a class="${param.tab eq 'INGREDIENT' ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/orders/storeDetail.or?itemNumber=${item.itemNumber}&tab=INGREDIENT">재료</a></li>
            <li><a class="${param.tab eq 'REVIEW' ? 'active' : ''}"
                   href="${pageContext.request.contextPath}/orders/reviewList.or?itemNumber=${item.itemNumber}">리뷰 보기</a></li>
          </ul>

          <!-- 상품 출력 -->
          <div id="buy_food_section">
            <div class="buy_food_menu_list">
              <c:choose>
                <c:when test="${not empty images}">
                  <img src="${images[0].itemImageSystemName}" alt="${item.itemName}">
                </c:when>
                <c:otherwise>
                  <img src="${ph}" alt="상품 이미지">
                </c:otherwise>
              </c:choose>

              <div class="buy_each_container">
                <p class="buy_food_stock">재고 : ${item.itemQuantity}개</p>
                <p class="buy_food_menu_name">상품명 : ${item.itemName}</p>
                <div class="buy_food_stock_choice">
                  <!-- 수량 선택은 JS로 제어 -->
                  <a href="#" class="minus">-</a>
                  <p class="count">1</p>
                  <a href="#" class="plus">+</a>
                  <!-- 장바구니 담기 -->
                  <form action="${pageContext.request.contextPath}/cartList/addItemOk.cl" method="post" style="display:inline;">
                    <input type="hidden" name="itemNumber" value="${item.itemNumber}">
                    <input type="hidden" name="quantity" value="1" class="cartQuantity">
                    <button type="submit" class="buy_add_cart_btn">장바구니</button>
                  </form>
                </div>
                <div class="price_menu">
                  가격 : <fmt:formatNumber value="${item.itemPrice}" type="number"/> 원
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 오른쪽 영역 (가게정보, 원산지, 뒤로가기) -->
      <div class="buy_map_area">
        <div class="buy_back_store_list">
          <a href="${pageContext.request.contextPath}/orders/storeList.or">목록으로 돌아가기</a>
        </div>

        <!-- 원산지 & 가게정보 -->
        <div class="buy_origin">
          <ul class="buy_origin_menu">
            <li class="buy_origin_info"><a href="#" id="storeInfoBtn">가게정보</a></li>
            <li class="buy_origin_info"><a href="#" id="originInfoBtn">원산지</a></li>
          </ul>

          <!-- 가게 정보 -->
          <div class="buy_origin_store_info">
            <ul class="buy_origin_content">
              <li>사업자번호: ${item.businessNumber}</li>
              <li>소비기한: ${item.itemExpireDate}</li>
            </ul>
          </div>

          <!-- 원산지 정보 -->
          <div class="origin_info_inactive">
            <ul>
              <li>원산지: ${item.itemOrigin}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!-- 사고보상정책 안내 -->
    <div class="buy_policy_toggle_section">
      <p>구매하신 상품에 이상이 있으셨나요? 아래 절차를 따라 요청을 남겨주시면 빠르게 도와드리겠습니다.</p>
      <div class="buy_policy_toggle_header">사고보상정책 안내 보기 ▼</div>
      <div class="buy_policy_toggle_content">
        <p>1. 음식을 받으신 직후 상태를 확인해주세요. 이상이 있는 부분이 있다면 사진을 촬영해주세요.</p>
        <ul>
          <li>수령 후 1일 이내 접수가 가능합니다.</li>
          <li>문제 부분을 확인할 수 있도록 3~4장 이상 촬영해주세요.</li>
        </ul>
        <p>2. 홈페이지 로그인 후 고객센터에서 사진과 함께 접수해주세요.</p>
      </div>
    </div>

  </main>

  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />

</body>
</html>
