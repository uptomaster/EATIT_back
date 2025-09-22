<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/storeReview.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/storeReview.js"></script>
  <script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
  </script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  
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
	          <%-- <img src="${pageContext.request.contextPath}/assets/img/store.jpg" alt="가게 이미지 추가하기"> --%>
	          <%-- <img src="${pageContext.request.contextPath}/upload/${images.storeImageSystemName}" alt="가게 이미지 추가하기"> --%> 
						<img src="${pageContext.request.contextPath}/upload/${images.storeImageSystemName}" 
			     alt="${storeInfo.storeName}"
			     onerror="this.src='${pageContext.request.contextPath}/assets/img/store.jpg'">
						
          <div class="store_info_store_info_detail">
            <p class="store_info_store_name">${storeInfo.storeName }</p>
            <p class="store_info_store_address"><c:out value="${storeInfo.storeAddress} ${ storeInfo.storeAddressDetail}"/></p>
            <p class="store_info_store_open_time">영업시간  ${storeInfo.storeOpenTime } ~ ${storeInfo.storeCloseTime }</p>
          	<!-- 지도 api에서 사용할 가게 주소, 이름  -->
          	<input type="hidden" id="storeName" value="${storeInfo.storeName }"/>
          	<input type="hidden" id="roadAddr" value="${storeInfo.storeAddress }"/>
          	<!-- 위도 경도 추가 예정 -->
          	<input type="hidden" id="storeLatitude" value="${storeInfo.storeLatitude }"/>
          	<input type="hidden" id="storeLongitude" value="${storeInfo.storeLongitude }"/>
          </div>
          <!-- 가게정보, 이미지 수정 버튼 -->
          <div class="store_info_edit_btns">
            <!-- <input type="file" id="edit_store_img" accept="image/*"> -->
            <a href="${pageContext.request.contextPath}/sellerMyPage/storeImage.se?businessNumber=${storeInfo.businessNumber}"
            class="edit_store_info_btn">
              가게 이미지
            </a>
            <!-- <button type="button" id="edit_store_info_btn"
            onclick="location.href='/sellerMyPage/editSellerInfo.se'">가게정보수정</button> -->
          </div>
        </div>

        <!-- 음식 메뉴 영역 -->
        <div class="store_info_food">
          <!-- 메뉴/재료/리뷰 탭바 -->
          <ul class="buy_food_menu_choice">
            <li><a class="link_menu" href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">메뉴</a></li>
            <li><a class="link_ingredient" href="${pageContext.request.contextPath}/sellerMyPage/storeIngre.se">재료</a></li>
            <li><a class="link_review" href="${pageContext.request.contextPath}/sellerMyPage/storeReview.se" style = "color : #444;">리뷰 보기</a></li>
          </ul>
          <!-- 리뷰 리스트 -->
           <c:choose>
               <c:when test="${empty reviews}">
                   <p style="padding:20px; text-align:center;">등록된 리뷰가 없습니다.</p>
               </c:when>
               <c:otherwise>
                   <c:forEach var="r" items="${reviews}">
                       <div class="buy_review_area">
                           <div class="buy_review_detail">
                               <div class="buy_review_detail_top">
                                   <span class="buy_review_id">${r.memberId}</span>
                                   <span class="buy_review_star">
                                       <c:forEach begin="1" end="5" var="i">
                                           <c:choose>
                                               <c:when test="${i <= r.reviewRating}">⭐</c:when>
                                               <c:otherwise>☆</c:otherwise>
                                           </c:choose>
                                       </c:forEach>
                                   </span>
                                   <span class="buy_review_date">${r.reviewCreateDate}</span>
                               </div>
                               <p class="buy_review_item">${r.itemName}</p>
                               <p class="buy_review_content">${r.reviewContent}</p>
                           </div>
                       </div>
                   </c:forEach>
               </c:otherwise>
           </c:choose>
          </div> <!--  //메뉴 출력 끝 -->
           <!-- 페이지네이션 자리 -->
		      <!-- 페이지네이션 -->
           <div class="pagination">
               <c:forEach var="i" begin="1" end="${maxPage}">
                   <a href="${pageContext.request.contextPath}/sellerMyPage/storeReview.se?page=${i}"
                      class="page ${i == page ? 'active' : ''}">${i}</a>
               </c:forEach>
           </div>
		      <!-- 페이지네이션 끝 -->
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
            <ul class="store_info_content" id="store_detail_info">
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
           <!--  <button type="button" id="edit_store_origin_btn">원산지 수정</button> -->
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
        <div id="storeReview_map">
        
        </div>

      </div> <!-- //오른쪽 영역 -->

    </div> <!-- 1100px 영역 끝 -->
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
<!-- 카카오맵 API (JavaScript 키 사용) -->
<script type="text/javascript" 
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey= &libraries=services&autoload=false"></script>

	
</body>

</html>
