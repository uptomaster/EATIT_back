<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/sellerMyPage/foodSalesEdit.css">
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">

<!-- 헤더 js -->
<script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/sellerMyPage/foodSalesEdit.js"></script>

<script>
	let headerPath = './../../header.jsp';
	let footerPath = './../../footer.jsp';
</script>
<title>밥세권</title>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="foodsalesedit_my_page_list">
			<div class="seller_my_page">마이 페이지</div>
			<ul class="foodsalesedit_side_bar">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
        <li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
		</div>
		<!-- 1100px 영역-->
		<div class="food_edit_container">
			<!-- 컨텐츠 영역 -->
			<form
				action="${pageContext.request.contextPath}/sellerMyPage/editFoodOk.se"
				method="post" enctype="multipart/form-data"
				class="food_edit_content_container"
				>
				<!-- 페이지 제목 -->
				<div class="food_edit_title">음식 판매 수정</div>
				<!-- 음식 정보 수정 -->
				<!-- 숨겨진 값 -->
        <input type="hidden" name="itemNumber" value="${item.itemNumber}">
				<!-- 음식 사진 -->
				<div class="food_edit_photo_container">
					<c:if test="${not empty itemImage}">
					    <div class="food_image_box">
					        <img src="${pageContext.request.contextPath}/upload/${itemImage.itemImageSystemName}" 
					             alt="${itemImage.itemImageOriginalName}" />
					    </div>
					</c:if>
					<div class="image-upload-wrap">
					<label for="food_edit_photo">음식 사진</label>
						<div class="image-upload-box">
							<!-- <div class="upload-text">
									이미지 업로드(<span class="cnt">0</span>/1)
							</div> -->
							<div class="upload-text">최대 1개까지 업로드 가능</div>
							<div class="upload-text">파일 형식 : jpg, png</div>
							<div class="upload-text">※ 이미지를 등록하면 즉시 반영됩니다.</div>
						</div> <!--  //image-upload-box -->
						<input type="file" id="food_edit_photo" name="itemImage" />
					</div>
					<%-- 크게 보이는 메인 미리보기 --%>
					<img id="image-preview" src="${pageContext.request.contextPath}/assets/img/store.jpg" alt="미리보기">
					<ul class="file-list"><%-- (선택 파일 썸네일들 표기 영역: 선택 사항) --%></ul>
				</div>
				<!-- 메뉴명 -->
				<div class="food_edit_box">
					<label for="food_edit_menu">메뉴명</label> <input name="itemName"
						type="text" id="food_edit_munu" required
						value="<c:out value="${item.itemName}"/>">
				</div>
				<!-- 음식 설명 -->
				<div class="food_edit_box">
					<label for="food_edit_explain">음식 설명</label>
					<textarea name="itemContent" id="food_edit_explain" maxlength="100"
						placeholder="100자 이내로 입력해주세요" required>
           <c:out value="${item.getItemContent()}" /></textarea>
					<!-- <span id="food_edit_char_count">0/100</span> -->
				</div>
				<!-- 소비기한 -->
				<div class="food_edit_box">
					<label for="food_edit_expiry">소비기한</label> <input required
						name="itemExpireDate" type="date" id="food_edit_expiry"
						value="<c:out value="${item.getItemExpireDate()}"/>"
						placeholder="YYYY-MM-DD">
				</div>
				<!-- 수량 & 가격 -->
				<div class="food_edit_box">
					<!-- 수량 -->
					<div class="food_edit_quantitiy_container">
						<label for="food_edit_quantity" class="food_edit_small_label">수량</label>
						<input name="itemQuantity" type="number" id="food_edit_quantity" required
							value="<c:out value="${item.getItemQuantity()}"/>" min="0"
							placeholder="개수"> <span class="food_edit_food_count">개</span>
					</div>
					<!-- 가격 -->
					<div class="food_edit_box">
						<label class="food_edit_small_label" class="food_edit_small_label">가격</label>
						<input name="itemPrice" type="number" id="food_edit_price" min="0" required
							placeholder="원단위" value="<c:out value="${item.getItemPrice()}"/>">
						<span class="food_edit_food_count">원</span>
					</div>
				</div>
				<!-- 추가된 판매 상태 라디오 버튼 영역 -->
				<div class="food_edit_box foodsaleswrite_sellstate_container">
					<label class="foodsaleswrite_small_label">판매상태</label>
					<div class="foodsaleswrite_sellstate_options">
					<label>
					  <input type="radio" name="itemSellState" value="Y"
					         <c:if test="${item.itemSellState eq 'Y'}">checked</c:if>> 판매중
					</label>
					<label>
					  <input type="radio" name="itemSellState" value="N"
					         <c:if test="${item.itemSellState eq 'N'}">checked</c:if>> 판매중지
					</label>
					</div>
				</div>
				<!-- 라디오버튼 끝 -->

				<!-- 삭제 저장 버튼 -->
				<div class="food_edit_btn_container">
					<button class="food_delete_buzz" type="button" id="food_delete_btn"
                onclick="location.href='${pageContext.request.contextPath}/sellerMyPage/deleteFoodOk.se?itemNumber=${item.itemNumber}'">
                삭제</button>
					<button class="food_edit_btn" type="submit">수정 완료</button>
					<button class="food_cancel_buzz" type="button" class="cancle-btn" onclick="history.back();">취소</button>
						
       </div>
			</form>
		<!-- 컨텐츠 영역 끝 -->
		</div>
		<!-- 1100px 영역 끝 -->
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
	<script>
		document.getElementById('food_edit_photo')?.addEventListener('change', (e)=>{
	    const f=e.target.files?.[0]; if(!f) return;
	    const img=document.getElementById('image-preview');
	    img.src = URL.createObjectURL(f);  // 선택 즉시 확대 미리보기
	  });
	</script>
</body>
</html>