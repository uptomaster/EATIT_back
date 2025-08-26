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
  <title>밥세권</title>

  <!-- css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/communityMainUser.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />

  <!-- 스크립트 -->
  <script defer src="${pageContext.request.contextPath}/assets/js/community/communityMainUser.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/mouseoverTreeIcon.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  
</head>

<body>
  <!-- 헤더 자리 -->
  <jsp:include page="/header.jsp" />
  <!-- <header id="header"></header> -->

  <main>
    <!-- 왼쪽 사이드바 -->
    <aside class="side_bar"></aside>

    <!-- 메인 컨텐츠 감싸는 박스 -->
    <div class="main_container">

      <!-- 카테고리 네비 -->
      <nav class="community_category">
        <ul>
          <li><a href="${pageContext.request.contextPath}/community/communityMainOk.co" id="category_event" class="active">공지사항/이벤트</a></li>
          <li><a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co" id="category_free">자유게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/promoBoardListOk.co" id="category_advertise">홍보게시판</a></li>
          <li><a href="${pageContext.request.contextPath}/community/recipeListOk.co" id="category_recipe">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시판 리스트 영역 -->
      <section class="community_list" aria-label="게시판 목록">
        <!-- 리스트 제목 부분 -->
        <div class="list_header flex_row" role="rowgroup">
          <div class="col_title" role="columnheader">제목</div>
          <div class="col_author" role="columnheader">글쓴이</div>
          <div class="col_date" role="columnheader">등록일</div>
          <div class="col_views" role="columnheader">조회</div>
          <div class="col_likes" role="columnheader">추천</div>
        </div>

        <!-- 게시글 내용 들어가는 곳 -->
        <div id="postListBody" class="list_body" role="rowgroup">
        	<!-- 게시글 목록 -->
   			<div class="board-body">
	   			<c:choose>
	   				<c:when test="${not empty postList}">
	   					<c:forEach var="post" items="${postList}">
		   					 <div class="board-row">
		   					 	<div class="board-item title">
		   					 		<c:out value="${post.getPostTitle()}" />
		   					 	</div>
		   					 	<div class="board-item author">
		   					 		<c:out value="${post.getMemberId() }" />
		   					 	</div>
		   					 	<div class="board-item date">
		   					 		<c:out value="${post.getPostCreatedDate() }" />
		   					 	</div>
		   					 	<div class="board-item hit">
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
   		
   	
          <!-- 게시글 한 줄 -->
		<%--<div class="list_row flex_row" role="row">
             <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">밥세권 앱 신규 기능 업데이트 안내</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon" />
            </div>
            <div class="col_date" role="cell">25-08-01</div>
            <div class="col_views" role="cell">120</div>
            <div class="col_likes" role="cell">35</div>
          </div> --%>

          <!-- 계속 복붙된 게시글들 -->
         <%-- <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">신규 입점 업체 ‘맛있는 반찬’
                소개</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-03</div>
            <div class="col_views" role="cell">95</div>
            <div class="col_likes" role="cell">18</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">여름맞이 대규모 할인 이벤트
                진행!</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-05</div>
            <div class="col_views" role="cell">180</div>
            <div class="col_likes" role="cell">45</div>
          </div>

         <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">사용자 리뷰 작성 가이드 및 혜택
                안내</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-07</div>
            <div class="col_views" role="cell">75</div>
            <div class="col_likes" role="cell">22</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>

          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
          
          <div class="list_row flex_row" role="row">
            <div class="col_title" role="cell"><a href="${pageContext.request.contextPath}/app/community/viewOtherPost.jsp">친구 초대하고 쿠폰 받자! 추천
                이벤트</a></div>
            <div class="col_author" role="cell">
              관리자
              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon author_profile" />
            </div>
            <div class="col_date" role="cell">25-08-09</div>
            <div class="col_views" role="cell">130</div>
            <div class="col_likes" role="cell">38</div>
          </div>
        </div> --%>
      </section> 

      <!-- 페이지네이션 들어가는 자리 -->
      <div class="pagination">
        <ul>
          <c:if test="${prev}">
          	<li><a href="${pageContext.request.contextPath}/community/communityMainOk.co?page=${startPage - 1}" class="prev">&lt;</a></li>
          </c:if>
          <c:set var="realStartPage" value="${startPage < 0 ? 0 : startPage}" />
          <c:forEach var="i" begin="${realStartPage}" end="${endPage}">
          	<c:choose>
          		<c:when test="${!(i == page) }">
          			<li><a href="${pageContext.request.contextPath}/community/communityMainOk.co?page=${i}">
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
          	<li><a href="${pageContext.request.contextPath}/community/communityMainOk.co?page=${endPage + 1}" class="next">&gt;</a>
          </c:if>
        </ul>
      </div>

      <!-- 검색창과 글쓰기 버튼 영역 -->
      <div class="controls_wrapper">
        <div class="function_container">
          <!-- 검색 박스 -->
          <div class="search_box">
            <input class="search_text" type="text" placeholder="검색어를 입력해 주세요" />
            <button class="search_btn" type="button">
              <i class="fas fa-search"></i>
            </button>
          </div>

          <!-- 글쓰기 버튼/ 공지사항은 관리자만 작성가능 -->
          <!-- <div class="write_form">
            <a href="./../community/writeRecipeBoard.html" id="writeBtn">글쓰기</a>
          </div> -->
        </div>
      </div>
    </div>

    <!-- 오른쪽 사이드바 -->
    <aside class="side_bar"></aside>
  </main>

  <!-- 푸터 자리 -->
  <jsp:include page="/footer.jsp" />
  <!-- <footer id="footer"></footer> -->
</body>
<script>
		window.postNumber = "${post.postNumber}";
		window.memberNumber = "${sessionScope.memberNumber}";
</script>
</html>