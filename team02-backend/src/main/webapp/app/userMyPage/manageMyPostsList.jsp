<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <!-- css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/manageMyPostsList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/editUserInfo.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  
  <!-- js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/manageMyPostsList.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <div class="my_page_list">
      <!-- 마이페이지 제목 -->
      <div class="my_page">마이 페이지</div>

      <!-- 마이페이지 사이드 메뉴 -->
      <ul class="side_bar">
        <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
  		<li class="active"><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
  		<li><a href="${pageContext.request.contextPath}/userMyPage/myReviewListOk.my">내 리뷰 관리</a></li>
      </ul>
    </div>
    <!-- 1100px 영역 -->
    <div class="managemyposts_page">
      <!-- 페이지 제목 -->
      <h2 class="managemyposts_list">내 글 관리</h2>
      <!-- 목록 제목 -->
      <div>
        <div class="managemyposts_top">
          <div class="managemyposts_sort">게시판 종류</div>
          <div class="managemyposts_title">글 제목</div>
          <div class="managemyposts_date">게시일</div>
          <div class="managemyposts_comments_count">댓글수</div>
          <div class="managemyposts_like_count">추천수</div>
        </div>
        
        <c:choose>
		    <c:when test="${empty myPosts}">
		      <div class="managemyposts_no_data">작성한 글이 없습니다.</div>
		    </c:when>
		    <c:otherwise>
		      <c:forEach var="post" items="${myPosts}">
		        <div class="managemyposts_comments_list">
		          <div class="managemyposts_sort">
		          	<c:choose>
				      <c:when test="${post.postType == 'FREE'}">자유게시판</c:when>
				      <c:when test="${post.postType == 'PROMOTION'}">홍보게시판</c:when>
				      <c:when test="${post.postType == 'RECIPE'}">레시피게시판</c:when>
				    </c:choose>
		          </div>
		          <div class="managemyposts_title">
		            <c:choose>
				        <c:when test="${post.postType == 'FREE'}">
				          <a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co?postNumber=${post.postNumber}">
				            <c:out value="${post.postTitle}" />
				          </a>
				        </c:when>
				        <c:when test="${post.postType == 'PROMOTION'}">
				          <a href="${pageContext.request.contextPath}/community/promoBoardReadOk.co?postNumber=${post.postNumber}">
				            <c:out value="${post.postTitle}" />
				          </a>
				        </c:when>
				        <c:when test="${post.postType == 'RECIPE'}">
				          <a href="${pageContext.request.contextPath}/community/recipeBoardReadOk.co?postNumber=${post.postNumber}">
				            <c:out value="${post.postTitle}" />
				          </a>
				        </c:when>
				      </c:choose>
		          </div>
		          <div class="managemyposts_date">
		          	<fmt:formatDate value="${post.postCreatedDate}" pattern="yyyy-MM-dd"/>
		          </div>
		          <div class="managemyposts_comments_count">
		          	<c:out value="${post.commentCount}" />
		          </div>
		          <div class="managemyposts_like_count">
		          	<c:out value="${post.postLikeCount}" />
		          </div>
		        </div>
		      </c:forEach>
		    </c:otherwise>
		  </c:choose>
       
      </div>
      <!-- 페이지네이션 (하단 페이지 넘기기) -->
	    <div class="pagination">
	        <ul>
	          <c:if test="${prev}">
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my?page=${startPage - 1}" class="prev">&lt;</a></li>
	          </c:if>
	          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
	          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
	          	<c:choose>
	          		<c:when test="${!(i == page) }">
	          			<li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my?page=${i}">
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
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my?page=${endPage + 1}" class="next">&gt;</a></li>
	          </c:if>
	        </ul>
	     </div>
	     
    </div> 
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>