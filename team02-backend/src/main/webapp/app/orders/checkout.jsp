<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String cpath = request.getContextPath();
  String orderId = (String) request.getAttribute("orderId");
  Integer amount = (Integer) request.getAttribute("amount");
  String customerName = (String) request.getAttribute("customerName");
  if (customerName == null || customerName.isBlank()) customerName = "고객";

  // 문서용 테스트 키
  String clientKey = "여기에 넣으세요";

%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>밥세권 결제하기</title>
  <!-- Toss v2 표준 SDK -->
  <script src="https://js.tosspayments.com/v2/standard"></script>
  <style>
    body {
      font-family: "Pretendard", "Noto Sans KR", sans-serif;
      background: #f9fafb;
      color: #333;
      max-width: 600px;
      margin: 40px auto;
      padding: 0 20px;
      line-height: 1.6;
    }
    h2 {
      font-size: 1.8rem;
      font-weight: 700;
      text-align: center;
      margin-bottom: 24px;
    }
    .box {
      border: 1px solid #ddd;
      border-radius: 12px;
      padding: 18px;
      margin: 16px 0;
      background: #fff;
      box-shadow: 0 3px 8px rgba(0,0,0,0.05);
    }
    button {
      width: 100%;
      padding: 14px;
      margin-top: 14px;
      font-size: 1rem;
      font-weight: 600;
      border-radius: 10px;
      border: none;
      cursor: pointer;
    }
    #payBtn {
      background: linear-gradient(135deg,#3b82f6,#2563eb);
      color: #fff;
    }
    #payBtn:hover { background: linear-gradient(135deg,#2563eb,#1d4ed8); }
    #backBtn {
      background: #e5e7eb;
      color: #374151;
    }
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
  <button id="backBtn">돌아가기</button>

  <script>
  window.addEventListener("load", async () => {
    const orderId = "<%= orderId %>";
    const amount = Number("<%= amount == null ? "0" : amount.toString() %>");
    const customerName = "<%= customerName %>";
    const cpath = "<%= cpath %>";

    const clientKey = "<%= clientKey %>";
    const tossPayments = TossPayments(clientKey);
    const widgets = tossPayments.widgets({ customerKey: TossPayments.ANONYMOUS });

    try {
      await widgets.setAmount({ currency: "KRW", value: amount });
      await Promise.all([
        widgets.renderPaymentMethods({ selector: "#payment-method" }),
        widgets.renderAgreement({ selector: "#agreement" }),
      ]);
      document.getElementById("payBtn").disabled = false;
    } catch (e) {
      console.error("위젯 렌더링 실패:", e);
      alert("결제 위젯 로딩 실패: " + e.message);
      return;
    }

    document.getElementById("backBtn").addEventListener("click", () => {
      location.href = cpath + "/cartList/view.cl";
    });

    document.getElementById("payBtn").addEventListener("click", async () => {
      try {
        await widgets.requestPayment({
          orderId,
          orderName: "밥세권 주문",
          customerName,
          successUrl: location.origin + cpath + "/orders/paymentApproveOk.or",
          failUrl: location.origin + cpath + "/orders/paymentFail.or",
        });
      } catch (e) {
        console.error("결제 요청 실패:", e);
        alert("결제 요청 실패: " + e.message);
      }
    });
  });
  </script>
</body>
</html>
