<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>장바구니 가게 변경 확인</title>
  <link rel="shortcut icon" href="<c:url value='/assets/img/favicon.ico'/>" type="image/x-icon">
  <style>
    body { font-family: system-ui, sans-serif; margin:0; padding:40px; background:#fafafa; }
    .card { max-width:720px; margin:0 auto; background:#fff; border-radius:16px; box-shadow:0 8px 24px rgba(0,0,0,.08); padding:28px; }
    h1 { margin:0 0 8px; font-size:22px; }
    .muted { color:#666; margin:0 0 20px; line-height:1.6; }
    .panel { background:#f6f7f9; border-radius:12px; padding:16px; margin:16px 0; }
    .row { display:flex; gap:16px; align-items:center; }
    .btns { display:flex; gap:12px; margin-top:20px; }
    .btn { display:inline-block; padding:10px 16px; border-radius:10px; text-decoration:none; border:1px solid #d0d5dd; }
    .btn.primary { background:#111; color:#fff; border-color:#111; }
  </style>
</head>
<body>
  <div class="card">
    <h1>카트를 비우고 가게를 변경할까요?</h1>
    <p class="muted">
      현재 장바구니에는 다른 가게의 상품이 담겨 있습니다.<br/>
      계속 진행하면 <strong>기존 장바구니가 모두 비워지고</strong> 선택하신 가게로 변경된 뒤 해당 상품을 담습니다.
    </p>

    <div class="panel">
      <div class="row">
        <div>
          <div><strong>담으려는 상품</strong></div>
          <div>상품명: <strong><c:out value="${snap.itemName}"/></strong></div>
          <div>가격: <strong><c:out value="${snap.itemPrice}"/></strong> 원</div>
          <div>사업자번호: <c:out value="${snap.businessNumber}"/></div>
          <div>수량: <c:out value="${quantity}"/></div>
        </div>
      </div>
    </div>

    <form action="<c:url value='/cartList/changeStoreOk.cl'/>" method="post" class="btns">
      <input type="hidden" name="itemNumber" value="${itemNumber}">
      <input type="hidden" name="quantity" value="${quantity}">
      <button type="submit" class="btn primary">네, 변경하고 담기</button>
      <a class="btn" href="<c:url value='/cartList/view.cl'/>">아니요, 장바구니로</a>
    </form>
  </div>
</body>
</html>
