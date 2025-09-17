<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/userMyPage/manageMyCommentsList.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/userMyPage/manageMyCommentsList.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 사이드 메뉴바 -->
    <div class="managemycomments_my_page_list">
      <!-- 사이드 메뉴바 제목 -->
      <div class="managemycomments_my_page">마이 페이지</div>
      <!-- 사이드 메뉴바 상세 메뉴 -->
      <ul class="managemycomments_side_bar">
        <li><a href="${pageContext.request.contextPath}/userMyPage/editUserInfo.my">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/foodPurchaseListOk.my">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/ingredientPurchaseListOk.my">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/myPostListOk.my">내 글 관리</a></li>
        <li  class="managemycomments_main"><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/userMyPage/manageMyReviewsList.jsp">내 리뷰 관리</a></li>
      </ul>
    </div>
    <!-- 1100px 영역 -->
    <div class="managemycomments_page">
      <!-- 페이지 제목 -->
      <h2 class="managemycomments_list">내 댓글 목록</h2>
      <!-- 목록 제목 -->
      <div>
        <div class="managemycomments_top">
          <div class="managemycomments_sort">게시판</div>
          <div class="managemycomments_title">게시글 제목</div>
          <div class="managemycomments_comments_info">댓글 내용</div>
          <div class="managemycomments_date">작성 일자</div>
        </div>

        <c:choose>
		    <c:when test="${empty myComments}">
		      <div class="managemyposts_no_data">작성한 댓글이 없습니다.</div>
		    </c:when>
		    <c:otherwise>
		      <c:forEach var="comment" items="${myComments}">
		        <div class="managemycomments_comments_list">
		          <div class="managemycomments_sort">
		          	<c:choose>
				      <c:when test="${comment.postType == 'FREE'}">자유게시판</c:when>
				      <c:when test="${comment.postType == 'PROMOTION'}">홍보게시판</c:when>
				      <c:when test="${comment.postType == 'RECIPE'}">레시피게시판</c:when>
				    </c:choose>
		          </div>
		          <div class="managemycomments_title">
		            <c:choose>
				        <c:when test="${comment.postType == 'FREE'}">
				          <a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co?postNumber=${comment.postNumber}">
				            <c:out value="${comment.postTitle}" />
				          </a>
				        </c:when>
				        <c:when test="${comment.postType == 'PROMOTION'}">
				          <a href="${pageContext.request.contextPath}/community/promoBoardReadOk.co?postNumber=${comment.postNumber}">
				            <c:out value="${comment.postTitle}" />
				          </a>
				        </c:when>
				        <c:when test="${comment.postType == 'RECIPE'}">
				          <a href="${pageContext.request.contextPath}/community/recipeBoardReadOk.co?postNumber=${comment.postNumber}">
				            <c:out value="${comment.postTitle}" />
				          </a>
				        </c:when>
				      </c:choose>
		          </div>
		          <div class="managemycomments_comments_info">
		          	<c:out value="${comment.commentContent}" />
		          </div>
		          <div class="managemycomments_date">
		          	<c:out value="${comment.commentedDate}" />
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
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my?page=${startPage - 1}" class="prev">&lt;</a></li>
	          </c:if>
	          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
	          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
	          	<c:choose>
	          		<c:when test="${!(i == page) }">
	          			<li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my?page=${i}">
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
	          	<li><a href="${pageContext.request.contextPath}/userMyPage/myCommentsListOk.my?page=${endPage + 1}" class="next">&gt;</a></li>
	          </c:if>
	        </ul>
	     </div>
      
      
      
    </div>
  </main>
  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>

</html>