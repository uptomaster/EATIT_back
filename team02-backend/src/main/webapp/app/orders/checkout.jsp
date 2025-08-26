<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String cpath = request.getContextPath();
  String clientKey = (String) request.getAttribute("tossClientKey");
  String orderId = (String) request.getAttribute("orderId");
  Integer amount = (Integer) request.getAttribute("amount");
  String customerName = (String) request.getAttribute("customerName");
  if (customerName == null || customerName.isBlank()) customerName = "고객";
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>결제하기</title>
  <!-- ⚠️ 반드시 한 번만 포함되어야 합니다 -->
  <script src="https://js.tosspayments.com/v1/payment-widget"></script>
  <style>
    body { font-family: system-ui, sans-serif; max-width: 560px; margin: 40px auto; }
    .box { border: 1px solid #e5e7eb; border-radius: 12px; padding: 16px; margin: 12px 0; }
    button[disabled] { opacity: .6; cursor: not-allowed; }
  </style>
</head>
<body>
  <h2>밥세권 결제</h2>

  <div class="box">
    <div>주문번호: <strong><%= orderId %></strong></div>
    <div>결제금액: <strong><%= (amount == null ? "0" : String.format("%,d", amount)) %>원</strong></div>
    <div>구매자: <strong><%= customerName %></strong></div>
  </div>

  <div id="payment-method" class="box"></div>
  <div id="agreement" class="box"></div>
  <button id="payBtn" disabled>결제하기</button>

  <script>
    window.addEventListener("load", async () => {
      const cpath = "<%= cpath %>";
      const clientKey = "<%= clientKey == null ? "" : clientKey %>";
      const amountRaw = "<%= amount == null ? "" : amount.toString() %>";
      const amount = Number(amountRaw);
      const orderId = "<%= orderId == null ? "" : orderId %>";
      const customerName = "<%= customerName %>";
      const payBtn = document.getElementById("payBtn");

      console.log("[CHECKOUT] clientKey?", !!clientKey, "orderId:", orderId, "amount:", amount);

      if (!clientKey) {
        alert("결제 키가 설정되지 않았습니다. 관리자에게 문의하세요.");
        location.href = cpath + "/cartList/view.cl";
        return;
      }
      if (!orderId || !Number.isFinite(amount) || amount <= 0) {
        alert("결제 준비 정보가 부족합니다. 장바구니로 돌아갑니다.");
        location.href = cpath + "/cartList/view.cl";
        return;
      }

      let widget;
      try {
        widget = PaymentWidget(clientKey, PaymentWidget.ANONYMOUS);
      } catch (e) {
        console.error("PaymentWidget 생성 실패:", e);
        alert("결제 위젯 초기화에 실패했습니다.");
        return;
      }

      try {
        // 렌더가 끝나기 전엔 결제 버튼 비활성화
        await widget.renderPaymentMethods("#payment-method", { value: amount }, { variantKey: "DEFAULT" });
        await widget.renderAgreement("#agreement");
        // 렌더 완료! 버튼 활성화
        payBtn.disabled = false;
      } catch (e) {
        console.error("위젯 렌더 실패:", e);
        alert("결제 UI 로딩에 실패했습니다.\n" + (e?.message || e));
        return;
      }

      payBtn.addEventListener("click", async () => {
        // 혹시라도 비동기 지연이 있으면 한 번 더 가드
        if (payBtn.disabled) {
          alert("결제 UI가 아직 로딩 중입니다. 잠시 후 다시 시도해주세요.");
          return;
        }
        try {
          await widget.requestPayment({
            orderId,
            orderName: "밥세권 주문",
            customerName,
            successUrl: location.origin + cpath + "/orders/paymentSuccess.or",
            failUrl:    location.origin + cpath + "/orders/paymentFail.or"
          });
        } catch (e) {
          console.error("결제창 호출 실패:", e);
          // 토스가 던지는 대표 에러: AGREEMENT_NOT_CHECKED, PAYMENT_WIDGET_NOT_RENDERED 등
          alert("결제창 호출에 실패했습니다.\n" + (e?.message || e));
        }
      });
    });
  </script>
</body>
</html>
