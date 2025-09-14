

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/originList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/originList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="origin_menu">
      <div class="origin_menu_title">마이 페이지</div>
      <ul class="origin_menu_list">
			<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				 <li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <div class="origin_container"> <!-- 1100px 영역-->
      <!-- 페이지 제목/ 추가 버튼 -->
      <div class="origin_title_container">
        <!-- 페이지 제목 -->
        <h2 class="origin_title"> 원산지 </h2>
        <button type="button" id="add_origin_btn">원산지 추가</button>
        <button type="button" id="back_to_storeInfo">사업장 관리로</button>
      </div>
      
      <!-- 원산지 목록 -->
			<div class="origin-list">
			  <!-- 헤더 -->
			  <div class="origin-row origin-head">
			    <div>메뉴</div>
			    <div>품목</div>
			    <div>원산지</div>
			    <div>수정</div>
			  </div>
			
			  <c:choose>
			    <!-- 데이터 있을 때 -->
			    <c:when test="${not empty originList}">
			      <c:forEach var="o" items="${originList}">
			        <div class="origin-row">
			          <div>${o.originMenu}</div>
			          <div>${o.originItem}</div>
			          <div>${o.originLocation}</div>
			          <div class="origin-actions">
			            <!-- 필요 시 링크 or 모달 트리거로 변경 -->
			            <a class="btn" href="${pageContext.request.contextPath}/sellerMyPage/originList.se?edit=${o.originNumber}">수정</a>
			          </div>
			        </div>
			      </c:forEach>
			    </c:when>
			
			    <!-- 데이터 없을 때 -->
			    <c:otherwise>
			      <div class="origin-row empty">
			        <div>저장한 내용이 없습니다</div>
			      </div>
			    </c:otherwise>
			  </c:choose>

        
      <!-- 추가버튼 눌렀을때 추가될 영역 -->
      <div id="origin_list_more"></div>
     </div><!-- 목록 끝 -->
      <!-- 모달창 -->
      <div id="editModal" class="modal">
        <div class="modal_content">
          <span class="close_btn" id="closeModal">&times;</span>
          <h2>원산지 수정</h2>
          <form id="editForm">
            <label for="product">표시품목</label>
            <input type="text" id="product" name="product" required>

            <label for="origin">원산지</label>
            <input type="text" id="origin" name="origin" required>

            <label for="foodName">음식명</label>
            <input type="text" id="foodName" name="foodName" required>

            <button type="submit" class="save_btn">저장</button>
            <button type="button" id="delete_btn" class="delete_btn">삭제</button>
          </form>
        </div>
      </div> <!-- 모달창 끝-->
    </div> <!-- 1100px 영역 끝  -->
  </main>
</body>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</html>