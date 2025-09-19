<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String cpath = request.getContextPath();
  String serverClientKey = (String) request.getAttribute("tossClientKey"); // 있으면 사용
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
  <!-- v2 표준 SDK -->
  <script src="https://js.tosspayments.com/v2/standard"></script>
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
  <button id="backBtn" disabled>돌아가기</button>

  <script>
  window.addEventListener("load", async () => {
    const cpath = "<%= cpath %>";
    // 서버에서 내려준 키가 없으면 문서용 테스트 키로 폴백 (허용 도메인 불필요)
    const DOCS_GCK = "";
    const clientKey = "<%= serverClientKey == null ? "" : serverClientKey %>" || DOCS_GCK;

    const orderId = "<%= orderId == null ? "" : orderId %>";
    const amount = Number("<%= amount == null ? "" : amount.toString() %>");
    const customerName = "<%= customerName %>";
    const payBtn = document.getElementById("payBtn");
    const backBtn = document.getElementById("backBtn");

    console.log("[CHECKOUT v2] clientKey startsWith(test_gck)?", clientKey.startsWith("test_gck_"), "orderId:", orderId, "amount:", amount);

    if (!orderId || !Number.isFinite(amount) || amount <= 0) {
      alert("결제 준비 정보가 부족합니다.");
      location.href = cpath + "/cartList/view.cl";
      return;
    }

    // v2: TossPayments → widgets
    const tossPayments = TossPayments(clientKey);
    // 비회원이면 ANONYMOUS, 회원이면 사용자별 고유 customerKey 사용
    const widgets = tossPayments.widgets({ customerKey: TossPayments.ANONYMOUS });

    try {
      // 금액 설정
      await widgets.setAmount({ currency: "KRW", value: amount });
      // UI 렌더 (v2는 selector/variantKey 형태)
      await Promise.all([
        widgets.renderPaymentMethods({ selector: "#payment-method", variantKey: "DEFAULT" }),
        widgets.renderAgreement({ selector: "#agreement", variantKey: "AGREEMENT" }),
      ]);
      payBtn.disabled = false;
      backBtn.disabled = false;
    } catch (e) {
      console.error("위젯 렌더 실패:", e);
      alert("결제 UI 로딩에 실패했습니다.\n" + (e?.message || e));
      return;
    }
	
    backBtn.addEventListener("click", async()=>{
      // 장바구니 화면으로 이동 (backBtn 관련 다른 부분은 수정하지 않음)
      location.href = cpath + "/cartList/view.cl";
    });
    
    payBtn.addEventListener("click", async () => {
      if (payBtn.disabled) return;
      try {
        await widgets.requestPayment({
          orderId,
          orderName: "밥세권 주문",
          customerName,
          successUrl: location.origin + cpath + "/orders/paymentSuccess.or",
          failUrl:    location.origin + cpath + "/orders/paymentFail.or",
        });
      } catch (e) {
        console.error("결제창 호출 실패:", e);
        alert("결제창 호출에 실패했습니다.\n" + (e?.message || e));
      }
    });
  });
  </script>
</body>
</html>
