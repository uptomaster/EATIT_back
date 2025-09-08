<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>밥세권</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/treeGrade.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/freeBoardList.css" />
  <script defer src="${pageContext.request.contextPath}/assets/js/community/mouseoverTreeIcon.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/freeBoardList.js"></script>
</head>

<body>
<!--   <header id="header"></header> -->
  <jsp:include page="/header.jsp" />

  <main>
    <aside class="side_bar"></aside>

    <div class="main_container">
      <!-- 카테고리 -->
      <nav class="community_category">
        <ul>
          <li><a href="${pageContext.request.contextPath}/community/communityMainOk.co" id="category_event">공지사항/이벤트</a></li>
          <li><a href="${pageContext.request.contextPath}/community/freeBoardListOk.co" id="category_free" class="active">자유게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/promoBoardListOk.co" id="category_advertise">홍보게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/recipeListOk.co" id="category_recipe">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시판 목록 -->
      <section class="community_list" aria-label="게시판 목록">
		<div class="list_header flex_row" role="rowgroup">
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>
	
		<div id="postListBody" class="list_body" role="rowgroup">
		
			<!-- 게시글 목록 -->
	   		<div class="board-header">
	   			<c:choose>
	   				<c:when test="${not empty postList}">
	   					<c:forEach var="post" items="${postList}">
		   					 <div class="board-row list_row flex_row" data-post-number="${post.postNumber}">
		   					 	<div class="board-item col_title">
		   					 		<a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co?postNumber=${post.postNumber}">
		   					 			<c:out value="${post.postTitle}" />
		   					 		</a>
		   					 	</div>
		   					 	<div class="board-item col_author">
		   					 		<c:out value="${post.getMemberId() }" />
		   					 	</div>
		   					 	<div class="board-item col_date">
		   					 		<fmt:formatDate value="${post.postCreatedDate}" pattern="yyyy-MM-dd"/>
		   					 	</div>
		   					 	<div class="board-item col_views">
		   					 		<c:out value="${post.getPostViewCount() }" />
		   					 		
		   					 	</div>
		   					 	<div class="board-item col_likes">
								    <c:out value="${post.getPostLikeCount()}" />
								</div>
		   					 </div>
	   					 </c:forEach>
	   				</c:when>
	   				<c:otherwise>
	   					<div>
	   						<div colspan="5" align="center">등록된 게시물이 없습니다.</div>
	   					</div>
	   				</c:otherwise>
	   			</c:choose>
	   		</div>
		</div>
      </section>

      <!-- 페이지네이션 들어가는 자리 -->
      <div class="pagination">
        <ul>
          <c:if test="${prev}">
          	<li><a href="${pageContext.request.contextPath}/community/freeBoardListOk.co?page=${startPage - 1}" class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
          	<c:choose>
          		<c:when test="${!(i == page) }">
          			<li><a href="${pageContext.request.contextPath}/community/freeBoardListOk.co?page=${i}">
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
          	<li><a href="${pageContext.request.contextPath}/community/freeBoardListOk.co?page=${endPage + 1}" class="next">&gt;</a></li>
          </c:if>
        </ul>
      </div>
      


      <!-- 검색 & 글쓰기 -->
      <div class="controls_wrapper">
        <div class="function_container">
          <!-- 검색 박스 -->
          <div class="search_box">
		    <input class="search_text" type="text" placeholder="검색어를 입력해 주세요">
		    <button type="button" class="search_btn">
		      <i class="fas fa-search"></i>
		    </button>
		  </div>

		  <!-- 글쓰기 -->
          <div class="write_form">
            <a href="${pageContext.request.contextPath}/community/writeFreeBoard.co?postType=FREE" id="writeBtn">글쓰기</a>
          </div>
        </div>
      </div>
    </div>

    <aside class="side_bar"></aside>
  </main>
  
  <jsp:include page="/footer.jsp" />
  <!-- <footer id="footer"></footer> -->
</body>
<script>
		console.log("조회수 스크립트 실행됨!");
		console.log("postNumber:", postNumber);
		console.log("viewCountEl:", viewCountEl);
		window.memberNumber = "${sessionScope.memberNumber}";
</script>

</html>