<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제 진행중...</title>
  <script src="https://js.tosspayments.com/v1/payment"></script>
</head>
<body>
<%
  String origin = request.getScheme() + "://" + request.getServerName()
      + ((request.getServerPort()==80 || request.getServerPort()==443) ? "" : ":" + request.getServerPort());
  String base = origin + request.getContextPath(); // 예: http://localhost:8080/프로젝트명
%>

<script>
  const clientKey    = "<%= (String)request.getAttribute("tossClientKey") %>";
  const orderId      = "<%= (String)request.getAttribute("orderId") %>";
  const amount       = <%= (Integer)request.getAttribute("amount") %>;
  const customerName = "<%= (String)request.getAttribute("customerName") %>";

  const tossPayments = TossPayments(clientKey);
  tossPayments.requestPayment("카드", {
    amount: amount,
    orderId: orderId,
    orderName: "밥세권 장바구니 결제",
    customerName: customerName,
    successUrl: "<%= base %>/orders/paymentApproveOk.or",
    failUrl: "<%= base %>/orders/paymentCancelOk.or"
  }).catch(function (err) {
    location.href = "<%= base %>/orders/paymentCancelOk.or?orderId="
      + encodeURIComponent(orderId)
      + "&message=" + encodeURIComponent(err.message || '사용자 취소');
  });
</script>

</body>
</html>
