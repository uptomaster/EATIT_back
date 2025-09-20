<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/sellerMyPostsList.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/sellerMyPostsList.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<main>
		<!-- 좌측 사이드 메뉴 -->
		<div class="seller_myposts_menu">
			<div class="seller_myposts_menu_title">마이 페이지</div>
			<ul class="seller_myposts_menu_list">
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
				<li class="seller_myposts_menu_list_current"><a href="/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
			</ul>
		</div>


		<div class="seller_myposts_page">
			<h2 class="seller_myposts_list">내 글 관리</h2>


<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
  <div class="seller_myposts_top">
    <div class="seller_myposts_sort">게시판 종류</div>
    <!-- <div class="seller_myposts_tag">태그</div> -->
    <div class="seller_myposts_title">글 제목</div>
    <div class="seller_myposts_date">게시일</div>
    <div class="seller_myposts_comments_count">댓글수</div>
    <div class="seller_myposts_like_count">추천수</div>
  </div>

  <c:choose>
    <c:when test="${not empty myPostList}">
      <c:forEach var="post" items="${myPostList}">
        <%-- postType → 라벨/엔드포인트 매핑 --%>
        <c:choose>
          <c:when test="${post.postType eq 'NOTICE'}">
            <c:set var="typeLabel" value="공지/이벤트"/>
            <c:set var="readEndpoint" value="/community/viewOwnPostOk.co"/>
            <c:set var="listEndpoint" value="/community/communityMainOk.co"/>
          </c:when>
          <c:when test="${post.postType eq 'FREE'}">
            <c:set var="typeLabel" value="자유게시판"/>
            <c:set var="readEndpoint" value="/community/freeBoardReadOk.co"/>
            <c:set var="listEndpoint" value="/community/freeBoardListOk.co"/>
          </c:when>
          <c:when test="${post.postType eq 'PROMOTION'}">
            <c:set var="typeLabel" value="홍보게시판"/>
            <c:set var="readEndpoint" value="/community/promoBoardReadOk.co"/>
            <c:set var="listEndpoint" value="/community/promoBoardListOk.co"/>
          </c:when>
          <c:when test="${post.postType eq 'RECIPE'}">
            <c:set var="typeLabel" value="레시피"/>
            <c:set var="readEndpoint" value="/community/recipeBoardReadOk.co"/>
            <c:set var="listEndpoint" value="/community/recipeListOk.co"/>
          </c:when>
          <c:when test="${post.postType eq 'INQUIRY'}">
            <c:set var="typeLabel" value="문의"/>
            <c:set var="readEndpoint" value="/community/inquiryReadOk.co"/>
            <c:set var="listEndpoint" value="/community/inquiryListOk.co"/>
          </c:when>
          <c:when test="${post.postType eq 'FAQ'}">
            <c:set var="typeLabel" value="자주묻는질문"/>
            <c:set var="readEndpoint" value="/community/faqReadOk.co"/>
            <c:set var="listEndpoint" value="/community/faqListOk.co"/>
          </c:when>
        </c:choose>

        <%-- 컨텍스트 프리픽스 + 엔드포인트 --%>
        <c:set var="listUrl" value="${ctx}/${listEndpoint}" />
        <%-- 요청대로 상세는 ?postNumber=${memberNumber} 를 부착 --%>
        <c:set var="readUrl" value="${ctx}/${readEndpoint}?postNumber=${post.postNumber}" />

        <%-- 제목: myPostTitle 우선, 없으면 postTitle --%>
        <c:set var="titleText" value="${post.postTitle}"/>

        <div class="seller_myposts_comments_list">
          <div class="seller_myposts_sort">
            <a href="${listUrl}">${typeLabel}</a>
          </div>

          <div class="seller_myposts_title">
            <a href="${readUrl}">
              <c:out value="${titleText}"/>
            </a>
          </div>

          <div class="seller_myposts_date">
            <fmt:formatDate value="${post.postCreatedDate}" pattern="yyyy-MM-dd"/>
          </div>

          <div class="seller_myposts_comments_count">
            <c:out value="${post.commentCount}"/>
          </div>

          <div class="seller_myposts_like_count">
            <c:out value="${post.postLikeCount}"/>
          </div>
        </div>
      </c:forEach>
    </c:when>

    <c:otherwise>
      <div>
        <div colspan="5" align="center">작성한 게시글이 없습니다.</div>
      </div>
    </c:otherwise>
  </c:choose>
</div>


			 <!-- 페이지네이션 자리 (요청하신 블록: 변경 없이 그대로 삽입) -->
      <div class="pagination">
        <ul class="pagination_ul">
          <c:if test="${prev}">
            <li><a
              href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${startPage - 1}"
              class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage"
            value="${startPage < 0 ? 0 : startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
            <c:choose>
              <c:when test="${!(i == page) }">
                <li><a class="pagination_item"
                  href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${i}">
                    <c:out value="${i}" />
                </a></li>
              </c:when>
              <c:otherwise>
                <li><a href="#" class="active"> <c:out value="${i}" />
                </a></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${next}">
            <li><a
              href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se?page=${endPage + 1}"
              class="next">&gt;</a>
          </c:if>
        </ul>
      </div>
      <!-- 페이지네이션 끝 -->
		</div>
	</main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>