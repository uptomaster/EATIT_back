

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
			    <c:when test="${not empty originList}">
			    <!-- 데이터 있을 때 -->
			      <c:forEach var="o" items="${originList}">
			        <div class="origin-row">
			          <div>${o.originMenu}</div>
			          <div>${o.originItem}</div>
			          <div>${o.originLocation}</div>
			          <div class="origin-actions">
			            <!-- 필요 시 링크 or 모달 트리거로 변경 -->
			            <a class="btn" href="${ctx}/sellerMyPage/originList.se?edit=${o.originNumber}">수정</a>
			          </div>
			        </div>
			      </c:forEach>
			    </c:when>
			
			    <c:otherwise>
			    <!-- 데이터 없을 때 -->
			      <div class="origin-row empty">
			        <div>저장한 내용이 없습니다</div>
			      </div>
			    </c:otherwise>
			  </c:choose>

        
      <!-- [추가] 모달 -->
		  <div class="modal-backdrop" id="addModal">
		    <div class="modal">
		      <h3>원산지 추가</h3>
		      <form method="post" action="${ctx}/sellerMyPage/originAddOk.se">
		        <div>
		          <label>품목</label><br/>
		          <input name="originItem" required />
		        </div>
		        <div>
		          <label>메뉴</label><br/>
		          <input name="originMenu" required />
		        </div>
		        <div>
		          <label>원산지</label><br/>
		          <input name="originLocation" required />
		        </div>
		        <div class="right" style="margin-top:10px;">
		          <button class="btn" type="button" data-close="#addModal">취소</button>
		          <button class="btn primary" type="submit">저장</button>
		        </div>
		      </form>
		    </div>
		  </div>
		
		  <!-- [수정] 모달: 서버가 editOrigin을 세팅했을 때만 필드 채워서 표시 -->
		  <div class="modal-backdrop" id="editModal"
		       style="<c:if test='${openEditModal}'>display:block;</c:if>">
		    <div class="modal">
		      <h3>원산지 수정</h3>
		      <form method="post" action="${ctx}/sellerMyPage/originEditOk.se">
		        <input type="hidden" name="originNumber" value="${editOrigin.originNumber}" />
		        <div>
		          <label>품목</label><br/>
		          <input name="originItem" value="${editOrigin.originItem}" required />
		        </div>
		        <div>
		          <label>메뉴</label><br/>
		          <input name="originMenu" value="${editOrigin.originMenu}" required />
		        </div>
		        <div>
		          <label>원산지</label><br/>
		          <input name="originLocation" value="${editOrigin.originLocation}" required />
		        </div>
		        <div class="right" style="margin-top:10px;">
		          <a class="btn" href="${ctx}/sellerMyPage/originList.se">취소</a>
		          <button class="btn primary" type="submit">수정 저장</button>
		        </div>
		      </form>
		    </div>
		  </div>
		  </div>
    </div> <!-- 1100px 영역 끝  -->
  </main>
</body>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</html>