<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/storeInfo.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/storeIngre.js"></script>
	<!-- 지도 api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=  "></script>
	<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=  &libraries=services,clusterer,drawing"></script>
	
  <script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
  </script>
</head>

<body>
	<!-- ✅ header 시작 -->
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<!-- ✅ header 끝 -->

  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="store_info_menu">
      <div class="store_info_menu_title">마이 페이지</div>
      <ul class="store_info_menu_list">
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

    <div class="store_info_container">
      <!-- 1100px 영역-->
      <!-- 가게 정보 및 메뉴 영역 -->
      <div class="store_info_left_area">

        <div class="store_info_store_info">
          <!-- 가게 이미지 -->
					<img src="${pageContext.request.contextPath}/upload/${storeInfo.storeImageSystemName}" 
			     alt="${item.storeName}"
			     onerror="this.src='${pageContext.request.contextPath}/assets/img/store.jpg'">
						
          <div class="store_info_store_info_detail">
            <p class="store_info_store_name">${storeInfo.storeName }</p>
            <p class="store_info_store_address"><c:out value="${storeInfo.storeAddress} ${ storeInfo.storeAddressDetail}"/></p>
            <p class="store_info_store_open_time">영업시간  ${storeInfo.storeOpenTime } ~ ${storeInfo.storeCloseTime }</p>
          	<input type="hidden" id="storeName" value="${storeInfo.storeName }"/>
          	<input type="hidden" id="roadAddr" value="${storeInfo.storeAddress }"/>
          	<!-- 위도 경도 추가 예정 -->
          	<input type="hidden" id="storeLatitude" value="${storeInfo.storeLatitude }"/>
          	<input type="hidden" id="storeLongitude" value="${storeInfo.storeLongitude }"/>
          </div>
          <!-- 가게정보, 이미지 수정 버튼 -->
          <div class="store_info_edit_btns">
            <!-- <input type="file" id="edit_store_img" accept="image/*"> -->
            <a href="${pageContext.request.contextPath}/sellerMyPage/storeImage.se?businessNumber=${storeInfo.businessNumber}">
              가게 이미지
            </a>
            <!-- <button type="button" id="edit_store_info_btn"
            onclick="location.href='/sellerMyPage/storeImage.se'">가게 사진</button> -->
            <button type="button" id="edit_store_info_btn"
            onclick="location.href='/sellerMyPage/editSellerInfo.se'">가게정보수정</button>
          </div>
        </div>

        <!-- 음식 메뉴 영역 -->
        <div class="store_info_food">
          <!-- 메뉴/재료/리뷰 탭바 -->
          <ul class="buy_food_menu_choice">
            <li><a class="link_menu" href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">메뉴</a></li>
            <li><a class="link_ingredient" href="${pageContext.request.contextPath}/sellerMyPage/storeIngre.se" style = "color : #444;">재료</a></li>
            <li><a class="link_review" href="${pageContext.request.contextPath}/sellerMyPage/storeReview.se">리뷰 보기</a></li>
          </ul>
          <ul class="store_food_list">
            <li class="store_food_list_count">총 개수 :<c:out value="${ingreListCount}"/>개</li>
            <li class="store_food_list_add">
              <a href="${pageContext.request.contextPath}/sellerMyPage/addIngre.se">새 메뉴 등록</a>
            </li>
          </ul>
          <div class="store_info_food_menu">
            <c:choose>
              <c:when test="${not empty ingreList}">
                <c:forEach var="ingre" items="${ingreList}" varStatus="status">
                  <form action="${pageContext.request.contextPath}/sellerMyPage/editIngre.se" method="post" class="editFoodForm">
                    <div class="store_info_ingredient_menu_list">
                      <!-- 음식 사진 출력 -->
                      <div class="img-box">
                      <%-- <c:choose>
											  <c:when test="${not empty ingre.itemImageSystemName}">
											    <img src="${pageContext.request.contextPath}/upload/${ingre.getItemImageSystemName()}" 
											         alt="가게 이미지"
											         onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/ingredeient2.jpg';">
											  </c:when>
											  <c:otherwise>
											    <img src="${pageContext.request.contextPath}/assets/img/ingredient1.jpg" alt="기본 가게 이미지">
											  </c:otherwise>
											</c:choose> --%>
                      	<img src="${pageContext.request.contextPath}/upload/${ingre.itemImageSystemName}" 
										     alt="${ingre.itemName}"
										     onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/assets/img/ingredient${status.index % 10}.jpg'">
                      </div>
                      <!-- 사진 옆 정보부분 -->
                      <div class="store_info_ingredient_menu_info_stock">
                        <!-- 최상단 : 수량, 수정 버튼, 상세 버튼 -->
                        <div class="store_info_btns">
                          <!-- 수량 -->
                          <div class="store_info_ingredient_stock">
                            <c:out value="${ingre.itemQuantity}" /> 개 남음
                          </div>
                          <%--  
                          <button href="${pageContext.request.contextPath}/sellerMyPage/detailFood.se
                          class="store_info_ingredient_view_btn"
                          data-food-itemNumbe="${food.itemNumber}">
                          상세보기</button> --%>
                          <!-- 수정 버튼 -->
                          <a href="${pageContext.request.contextPath}/sellerMyPage/editIngre.se?itemNumber=${ingre.itemNumber}">
                            <div class="store_info_ingredient_edit_btn">수정</div>
                          </a>
                          <!-- 상세 버튼 -->
                          <!-- 2번 방식 a태그 안에 서블릿 경로 넣기 -->
                          <a href="${pageContext.request.contextPath}/sellerMyPage/detailIngreOk.se?itemNumber=${ingre.itemNumber}">
                            <div class="store_info_ingredient_edit_btn">상세</div>
                          </a>
                        </div>
                        <!-- 하단부 : 이름, 소비기한, 가격 -->
                        <div class="store_info_ingredient_menu_info">
                          <h3><c:out value="${ingre.itemName}" /></h3>
                          <p>소비기한: <c:out value="${ingre.itemExpireDate}" /></p>
                          <h3><c:out value="${ingre.itemPrice}" />원</h3>
                        </div>
                      </div>
                    </div>
                    <hr>
                  </form>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <div>등록된 재료가 없습니다.</div>
              </c:otherwise>
            </c:choose>
          </div> <!--  //메뉴 출력 끝 -->

           <!-- 페이지네이션 자리 -->
		      <div class="pagination">
		        <ul class="pagination_ul">
		          <c:if test="${prev}">
		            <li><a
		              href="${pageContext.request.contextPath}/sellerMyPage/storeIngre.se?page=${startPage - 1}"
		              class="prev">&lt;</a></li>
		          </c:if>
		          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
		          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
		          	<c:choose>
		          		<c:when test="${!(i == page) }">
		          			<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeIngre.se?page=${i}">
		          				<c:out value="${i}" />
		          			</a></li>
		          		</c:when>
		          		<c:otherwise>
		          			<li><a href="#" class="active">
		          				<c:out value="${i}" />
		          			</a></li>
		          		</c:otherwise>
		          	</c:choose>
		          </c:forEach>
		          <c:if test="${next}">
		            <li><a
		              href="${pageContext.request.contextPath}/sellerMyPage/storeIngre.se?page=${endPage + 1}"
		              class="next">&gt;</a>
		          </c:if>
		        </ul>
		      </div>
		      <!-- 페이지네이션 끝 -->
        </div>
      </div> <!--  //왼쪽 -->

      <!-- 오른쪽 부분 -->
      <div class="store_info_right_area">

        <!-- 사업자 정보 정보 표기 -->
        <div class="store_info_detail_container">
          <div class="store_info_detail_title">
            가게정보
          </div>
          <div class="store_info_detail_list">
            <ul class="store_info_content">
              <li>상호명</li>
              <li>사업자 등록 번호</li>
              <li>주소</li>
              <li>가게 전화전호</li>
              <li>개업일</li>
            </ul>
            <ul class="store_info_content">
              <li>${storeInfo.storeName }</li>
              <li>${storeInfo.businessNumber }</li>
              <li><c:out value="${storeInfo.storeAddress } ${storeInfo.storeAddressDetail }"/></li>
              <li>${storeInfo.storeTel }</li>
              <li>${storeInfo.storeOpenDate }</li>
            </ul>
          </div>
        </div> <!--  //가게 상세 정보  -->

        <!-- 원산지 정보 표기 -->
        <div class="store_info_detail_container">
          <div class="store_info_detail_title">
            원산지
            <button type="button" id="edit_store_origin_btn">원산지 수정</button>
          </div>
          <div class="store_info_detail_list">
            <ul class="store_info_content">
              <li>음식명</li>
              <li>김치</li>
              <li>스테이크</li>
              <li>불고기</li>
              <li>페퍼로니</li>
            </ul>
            <ul class="store_info_content">
              <li>원산지</li>
              <li>국내산</li>
              <li>국내산</li>
              <li>미국,벨기에,스페인 등</li>
              <li>외국산</li>
            </ul>
            <ul class="store_info_content">
              <li>표시품목</li>
              <li>배추</li>
              <li>돼지고기</li>
              <li>돼지고기</li>
              <li>국내산</li>
            </ul>
          </div>
        </div> <!-- //원산지  -->

        <!-- 지도 영역 -->
        <div id="storeIngre_map">
        
        </div>

      </div> <!-- //오른쪽 영역 -->

    </div> <!-- 1100px 영역 끝 -->
  </main>

	<!-- ✅ footer 시작 -->
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
	<!-- ✅ footer 끝 -->

	<script>
		window.foodItemNumber = "${food.itemNumber}";
		let itemNumber = "${sessionScope.itemNumber}";
		console.log(itemNumber);
	</script>
</body>
</html>
