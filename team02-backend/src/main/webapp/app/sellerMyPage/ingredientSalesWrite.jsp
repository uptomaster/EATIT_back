<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/ingredientSalesWrite.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/ingredientSalesWrite.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="foodsaleswrite_my_page_list">
			<div class="foodsaleswrite_my_page">마이 페이지</div>
			<ul class="foodsaleswrite_side_bar">
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

		<div class="foodsaleswrite_container">
			<!-- 1100px 영역-->
			<form
				action="${pageContext.request.contextPath}/sellerMyPage/addIngreOk.se"
				method="post" enctype="multipart/form-data"
				class="foodsaleswrite_content_container">
				<!-- 컨텐츠 영역 -->
				<div class="foodsaleswrite_title">재료 판매 등록</div>
				<!-- 음식 정보 등록 -->
				<!-- 음식 사진 등록 -->
				<!-- <div class="foodsaleswrite_photo_container">
					<div class="foodsaleswrite_submit_table">
						<div class="foodsaleswrite_box">
              <label for="foodsaleswrite_photo">음식 사진 등록</label>
            </div>
						<div class="form-group">
							<label for="file" class="image-label">메뉴 사진</label>

							<div class="image-upload-wrap">
							파일 넣는 부분 
								<input name="boardFile" type="file" id="file" />
														accept=".jpg, .jpeg, .png" multiple />
								<div class="image-upload-box">
									<div class="upload-text">
										<div class="upload-icon">
											<svg viewBox="50 50 650 380"><path fill-rule="evenodd" clip-rule="evenodd"
													d="M25.9087 8.12155L36.4566 18.3158C37.2603 18.7156 38.2648 18.6156 38.968 18.3158C39.6712 17.5163 39.6712 16.4169 38.968 15.7173L25.3059 2.5247C24.6027 1.8251 23.4977 1.8251 22.7945 2.5247L9.03196 15.8172C8.32877 16.5168 8.32877 17.6162 9.03196 18.3158C9.73516 19.0154 10.9406 19.0154 11.6438 18.3158L22.2922 8.12155V28.4111C22.2922 29.4106 23.0959 30.2091 24.1005 30.2091C25.105 30.2091 25.9087 29.4106 25.9087 28.4111V8.12155ZM5.61644 29.4104C5.61644 28.4109 4.81279 27.6104 3.80822 27.6104C2.80365 27.6104 2 28.5099 2 29.5093V44.202C2 45.2015 2.80365 46 3.80822 46H44.1918C45.1963 46 46 45.2015 46 44.202V29.5093C46 28.5099 45.1963 27.7113 44.1918 27.7113C43.1872 27.7113 42.3836 28.5099 42.3836 29.5093V42.3021H5.61644V29.4104Z"></path></svg>
										</div>
										<div class="upload-count">
											이미지 업로드(<span class="cnt">0</span>/1)
										</div>
									</div>
									<div class="upload-text">최대 1개까지 업로드 가능. 파일 형식 : jpg, png</div>
									<div class="upload-text">※ 이미지를 등록하면 즉시 반영됩니다.</div>
								</div>
							</div>
							<div class="img-controller-box">
								<ul class="file-list">

								</ul>
							</div>
						</div>

					</div>
				</div> -->
				<!-- //음식 사진 등록 끝 -->
				<!-- 음식 사진 등록 -->
<div class="foodsaleswrite_photo_container">
  <div class="foodsaleswrite_submit_table">

    <div class="form-group foodsaleswrite_box">

      <!-- 상단: 미리보기 -->
      <div class="photo-preview">
        <div class="img-controller-box">
          <ul class="file-list"><!-- JS로 이미지 들어옴 --></ul>
        </div>
      </div>

      <!-- 하단: 제목 + 업로드 -->
      <div class="photo-upload">
        <label for="file" class="image-label">메뉴 사진</label>
        <div class="image-upload-wrap">
          <input name="boardFile" type="file" id="file" />
          <div class="image-upload-box">
            <div class="upload-text">
              <div class="upload-icon">
                <!-- 업로드 아이콘 (SVG 그대로 유지) -->
                <svg viewBox="50 50 650 380"><path fill-rule="evenodd" clip-rule="evenodd"
                  d="M25.9087 8.12155L36.4566 18.3158C37.2603 18.7156 38.2648 18.6156 38.968 18.3158C39.6712 17.5163 39.6712 16.4169 38.968 15.7173L25.3059 2.5247C24.6027 1.8251 23.4977 1.8251 22.7945 2.5247L9.03196 15.8172C8.32877 16.5168 8.32877 17.6162 9.03196 18.3158C9.73516 19.0154 10.9406 19.0154 11.6438 18.3158L22.2922 8.12155V28.4111C22.2922 29.4106 23.0959 30.2091 24.1005 30.2091C25.105 30.2091 25.9087 29.4106 25.9087 28.4111V8.12155ZM5.61644 29.4104C5.61644 28.4109 4.81279 27.6104 3.80822 27.6104C2.80365 27.6104 2 28.5099 2 29.5093V44.202C2 45.2015 2.80365 46 3.80822 46H44.1918C45.1963 46 46 45.2015 46 44.202V29.5093C46 28.5099 45.1963 27.7113 44.1918 27.7113C43.1872 27.7113 42.3836 28.5099 42.3836 29.5093V42.3021H5.61644V29.4104Z"></path></svg>
              </div>
              <div class="upload-count">이미지 업로드(<span class="cnt">0</span>/1)</div>
            </div>
            <div class="upload-text">최대 1개까지 업로드 가능. 파일 형식 : jpg, png</div>
            <div class="upload-text">※ 이미지를 등록하면 즉시 반영됩니다.</div>
          </div>
        </div>
      </div>

    </div>
  </div>
</div>
<!-- //음식 사진 등록 끝 -->
				
				<div class="foodsaleswrite_box">
					<label for="foodsaleswrite_menu" class="foodsaleswrite_label">메뉴명</label> <input name="itemName"
						type="text" id="foodsaleswrite_munu">
					<!-- <button type="button">등록</button> -->
				</div>

				<div class="foodsaleswrite_box">
					<label for="foodsaleswrite_explain" class="foodsaleswrite_label">음식 설명</label>
					<textarea name="itemContent" id="foodsaleswrite_explain"
						maxlength="100" placeholder="100자 이내로 입력해주세요"></textarea>
					<button type="button">등록</button>
				</div>

				<div class="foodsaleswrite_box">
					<label for="foodsaleswrite_expiry" class="foodsaleswrite_label">소비기한</label> <input
						name="itemExpireDate" type="date" id="foodsaleswrite_expiry"
						placeholder="YYYY-MM-DD-MIN -SS">
					<!-- <button type="button">등록</button> -->
				</div>


					<div class="foodsaleswrite_box">
						<label for="foodsaleswrite_quantity"
							class="foodsaleswrite_label">수량</label> <input
							name="itemQuantity" type="number" id="foodsaleswrite_quantity"
							min="0" placeholder="개수"> <span
							id="foodsaleswrite_food_count">개</span>
						<!-- <button type="button">등록</button> -->
					</div>

					<div class="foodsaleswrite_box">
						<label for="foodsaleswrite_price"
							class="foodsaleswrite_label">가격</label> <input
							name="itemPrice" type="number" id="foodsaleswrite_price" min="0"
							placeholder="원단위"> <span>원</span>
						<!-- <button type="button">등록</button> -->
					</div>


				<!-- 추가된 판매 상태 라디오 버튼 영역 -->
				<div class="foodsaleswrite_box foodsaleswrite_sellstate_container">
					<label class="foodsaleswrite_label">판매상태</label>
					<div class="foodsaleswrite_sellstate_options">
						 <input type="radio" name="itemSellState" value="Y"
							checked> 판매중
						 <input type="radio" name="itemSellState" value="N"> 판매중지
					</div>
				</div>
				<!-- 끝 -->

				<button class="foodsaleswrite_buzz" type="submit">등록</button>
			</form>
			<!-- 1100px 영역 -->
		</div>
		<!-- 컨텐츠 -->
	</main>ㄴ
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>