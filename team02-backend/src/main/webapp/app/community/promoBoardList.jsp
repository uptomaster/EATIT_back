<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">
  <title>홍보게시판</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/promoBoardList.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <script defer src="${pageContext.request.contextPath}/assets/js/community/promoBoardList.js"></script>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/treeGrade.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/community/mouseoverTreeIcon.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/writePromoBoard.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>

<body>
  <!-- <header id="header"></header> -->
  <jsp:include page="/header.jsp" />

  <main>
    <aside class="side_bar"></aside>

    <div class="main_container">
      <!-- 카테고리 -->
      <nav class="community_category">
        <ul>
          <li><a href="${pageContext.request.contextPath}/community/communityMainOk.co" id="category_event">공지사항/이벤트</a></li>
          <li><a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co" id="category_free">자유게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/promoBoardListOk.co" id="category_advertise" class="active">홍보게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/recipeListOk.co" id="category_recipe">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시판 목록 -->
      <section class="community_list" aria-label="게시판 목록">
        <div class="list_header flex_row" role="rowgroup">
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="cell">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>
        
        
        <!-- 게시글 목록 -->
   		<div class="board-header">
   			<c:choose>
   				<c:when test="${not empty postList}">
   					<c:forEach var="post" items="${postList}">
	   					 <div class="board-row list_row flex_row">
	   					 	<%-- <div class="board-item no">
	   					 		<c:out value="${post.getPostNumber()}" />
	   					 	</div> --%>

	   					 	<div class="board-item col_title">
	   					 		<a href="${pageContext.request.contextPath}/community/promoBoardListOk.co?postNumber=${post.postNumber}">
	   					 			<c:out value="${post.postTitle}" />
	   					 		</a>
	   					 	</div>
	   					 	<div class="board-item col_author">
	   					 		<c:out value="${post.getMemberId() }" />
	   					 	</div>
	   					 	<div class="board-item col_date">
	   					 		<c:out value="${post.getPostCreatedDate() }" />
	   					 	</div>
	   					 	<div class="board-item col_views">
	   					 		<c:out value="${post.getPostViewCount() }" />
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

        <%-- <div id="postListBody" class="list_body" role="rowgroup">
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">홍대에 새로 오픈한 이탈리안 레스토랑 소개</a>
            </div>
            <div class="col_author" role="cell">
              messi
              <img src="${pageContext.request.contextPath}/assets/img/나무.png" alt="나무" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-01</div>
            <div class="col_views" role="cell">350</div>
            <div class="col_likes" role="cell">80</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">카페 여름 시즌 한정 음료 할인
                행사</a></div>
            <div class="col_author" role="cell">
              ronaldo
              <img src="${pageContext.request.contextPath}/assets/img/가지.png" alt="가지" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-07-25</div>
            <div class="col_views" role="cell">280</div>
            <div class="col_likes" role="cell">55</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">신선한 유기농 채소 식자재 공급
                시작</a></div>
            <div class="col_author" role="cell">
              neymar
              <img src="${pageContext.request.contextPath}/assets/img/잎새.png" alt="잎새" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-05</div>
            <div class="col_views" role="cell">220</div>
            <div class="col_likes" role="cell">40</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">베이커리 체험단 모집 공고</a>
            </div>
            <div class="col_author" role="cell">
              mbappe
              <img src="${pageContext.request.contextPath}/assets/img/새싹.png" alt="새싹" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-07-20</div>
            <div class="col_views" role="cell">180</div>
            <div class="col_likes" role="cell">50</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">예약 시스템 신규 도입 안내</a>
            </div>
            <div class="col_author" role="cell">
              salah
              <img src="${pageContext.request.contextPath}/assets/img/씨앗.png" alt="씨앗" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-07-15</div>
            <div class="col_views" role="cell">150</div>
            <div class="col_likes" role="cell">35</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/community/viewOtherPost.html">점심시간 스페셜 메뉴 할인 이벤트</a>
            </div>
            <div class="col_author" role="cell">
              kane
              <img src="${pageContext.request.contextPath}/assets/img/나무.png" alt="나무" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-07-10</div>
            <div class="col_views" role="cell">170</div>
            <div class="col_likes" role="cell">45</div>
          </div>
        </div> --%>

      </section>

      <!-- 페이지네이션 -->
      <div class="pagination_container" id="pagination">
        <!-- 페이지 버튼 생성됨 -->
      </div>

      <!-- 검색 & 글쓰기 -->
      <div class="controls_wrapper">
        <div class="function_container">
          <div class="search_box">
            <input class="search_text" type="text" placeholder="검색어를 입력해 주세요" />
            <button class="search_btn" type="button">
              <i class="fas fa-search"></i>
            </button>
          </div>

          <div class="write_form">
            <a href="${pageContext.request.contextPath}/community/writeFreeBoard.co?postType=PROMOTION" id="writeBtn">글쓰기</a>
          </div>
        </div>
      </div>
    </div>

    <aside class="side_bar"></aside>
  </main>

 <!--  <footer id="footer"></footer> -->
  <jsp:include page="/footer.jsp" />
</body>
<script>
		window.postNumber = "${post.postNumber}";
		window.memberNumber = "${sessionScope.memberNumber}";
</script>
</html>