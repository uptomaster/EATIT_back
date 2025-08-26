<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
  <!-------------------- 헤더 ------------------------>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />

  <main id="shopping_list">
    <div id="wrap">
      <!-- 상단 제목 영역 -->
      <div class="shopping_header">장바구니 🛒</div>

      <!-- content 전체 영역 -->
      <div class="shopping_content">
        <!-- 왼쪽 장바구니 목록 영역 -->
        <div class="shopping_cart_area">

          <!-- 전체선택+삭제 -->
          <div class="shopping_select_all">
            <input type="checkbox" id="selectAll"> 전체선택
            <button type="button" id="deleteSelected" class="shopping_select_delete">선택삭제</button>
          </div>

          <!-- 장바구니 항목 출력 -->
          <div class="shopping_cart_list">
            <c:choose>
              <c:when test="${empty items}">
                <p class="empty">장바구니가 비어 있습니다.</p>
              </c:when>
              <c:otherwise>
                <c:forEach var="item" items="${items}">
                  <div class="shopping_cart_item">
                    <!-- 선택 체크박스 -->
                    <input type="checkbox" class="shopping_item_check"
                           name="cartItemNumber" value="${item.cartItemNumber}">

                    <!-- 이미지 -->
                    <c:url value="/assets/img/placeholder.png" var="placeholder" />
                    <c:url value="/upload/item/${item.imagePath}" var="itemImg" />
                    <img src="${empty item.imagePath ? placeholder : itemImg}" alt="${item.itemName}">

                    <!-- 상품 정보 -->
                    <div class="shopping_item_info">
                      <div class="shopping_item_name">${item.itemName}</div>
                      <div class="shopping_item_price">
                        <fmt:formatNumber value="${item.cartItemPrice}" type="number" /> 원
                      </div>
                    </div>

                    <!-- 수량 증감 -->
                    <div class="shopping_item_cnt">
                      <a href="${pageContext.request.contextPath}/cartList/updateOk.cl?cartItemNumber=${item.cartItemNumber}&quantity=${item.cartItemQuantity - 1}">➖</a>
                      <span>${item.cartItemQuantity}</span>
                      <a href="${pageContext.request.contextPath}/cartList/updateOk.cl?cartItemNumber=${item.cartItemNumber}&quantity=${item.cartItemQuantity + 1}">➕</a>
                    </div>

                    <!-- 단건 삭제 -->
                    <div class="shopping_item_delete">
                      <a href="${pageContext.request.contextPath}/cartList/deleteItemOk.cl?cartItemNumber=${item.cartItemNumber}">삭제</a>
                    </div>
                  </div>
                </c:forEach>
              </c:otherwise>
            </c:choose>
          </div>
        </div>

        <!-- 오른쪽 결제 영역 -->
        <div class="shopping_payment_area">
          <!-- 결제 전체를 하나의 폼으로 묶어서 서버로 POST -->
          <form action="${pageContext.request.contextPath}/orders/paymentReady.or" method="post" id="paymentForm">
            <!-- 결제수단 선택 -->
            <div class="shopping_payment_method">
              <div class="shopping_payment_title">결제수단</div>
              <label><input type="radio" name="payment" value="card" required> 신용/체크카드</label>
              <label><input type="radio" name="payment" value="bank"> 무통장입금</label>
              <label><input type="radio" name="payment" value="naver"> 네이버페이</label>
              <label><input type="radio" name="payment" value="kakao"> 카카오페이</label>
            </div>

            <!-- 결제금액 요약 -->
            <div class="shopping_payment_summary">
              <div class="shopping_payment_title">결제금액</div>
              <div class="shopping_price_row">
                <span>결제예정금액</span>
                <span><fmt:formatNumber value="${totalAmount}" type="number" /> 원</span>
              </div>
            </div>

            <!-- 결제 버튼 (폼 내부) -->
            <button type="submit" class="shopping_payment_btn" ${empty items ? 'disabled' : ''}>
              결제하기
            </button>

            <!-- 주의: 서버에서 금액을 재계산하므로 hidden으로 금액을 신뢰하지 않습니다. 필요 시 참고용으로만 전송하세요.
            <input type="hidden" name="clientTotal" value="${totalAmount}">
            -->
          </form>
        </div>
      </div>
    </div>
  </main>

  <footer id="footer"></footer>

  <!-- 간단 방어 로직 -->
  <script>
    (function () {
      const form = document.getElementById('paymentForm');
      if (!form) return;

      form.addEventListener('submit', function (e) {
        const hasItems = ${empty items ? 'false' : 'true'};
        if (!hasItems) {
          alert('장바구니가 비어 있습니다.');
          e.preventDefault();
          return;
        }
        const checked = form.querySelector('input[name="payment"]:checked');
        if (!checked) {
          alert('결제수단을 선택해주세요.');
          e.preventDefault();
        }
      });
    })();
  </script>
</body>
</html>
