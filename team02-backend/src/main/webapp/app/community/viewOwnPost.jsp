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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/viewOwnPost.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/modal.css" /> --%>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/reportPostModal.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/viewOwnPost.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/darkmode.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/treeGradeModal.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
</head>

<body>
  <!-- 헤더 -->
  <jsp:include page="/header.jsp" />

  <aside class="side"></aside>
  
  <main class="main">
    <div class="container">
      <!-- 카테고리 메뉴 -->
      <nav class="category_container">
        <ul class="category_list">
          <li class="category_item"><a href="${pageContext.request.contextPath}/community/communityMainOk.co">공지사항/이벤트</a></li>
          <li class="category_item"><a href="${pageContext.request.contextPath}/community/freeBoardReadOk.co">자유게시판</a></li>
          <li class="category_item"><a href="${pageContext.request.contextPath}/community/promoBoardListOk.co">홍보게시판</a></li>
          <li class="category_item"><a href="${pageContext.request.contextPath}/community/recipeListOk.co">레시피</a></li>
        </ul>
      </nav>

      <!-- 게시글 헤더 -->
	  <article>
	      <div class="post">
	        <!-- 게시글 헤더 -->
	        <div class="post_header">
	          <div class="post_box">
	            <!-- 작성자 -->
	            <div class="author_box">
	              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon" />
	              <span>${notice.adminId}</span>
	            </div>
	
	            <!-- 게시글 제목 -->
	            <div class="post_title">
	              <h1 class="post_title"><c:out value="${notice.postTitle}" /></h1>
	            </div>
	
	            <!-- 메타 데이터 -->
	            <div class="post_meta">
	              <div class="post_date_area">
	                <fmt:formatDate value="${notice.postCreatedDate}" pattern="yyyy-MM-dd"/>
	              </div>
	              <div class="post_hit_area">
	                <span>조회</span>
	                <span><c:out value="${notice.postViewCount}" /></span>
	              </div>
	              <div class="post_like_area">
	                <span>추천</span>
	                <span><c:out value="${notice.postLikeCount}" /></span>
	              </div>
	            </div> <!-- .post_meta -->
	          </div> <!-- .post_box -->
	        </div> <!-- .post_header -->
	
	        <!-- 게시글 내용 -->
	        <section class="content_section">
	          <div class="view-content post_content">
	            <p><c:out value="${notice.noticeContent}" /></p>
	          </div>
			  <!-- 첨부파일 출력 -->
			  <c:forEach var="img" items="${noticeImages}">
			      <img src="${pageContext.request.contextPath}/upload/${img.adminImageSystemName}" alt="${img.adminImageOriginalName}" />
			  </c:forEach>
				
				
				
	          <!-- 수정/삭제 버튼 = 관리자만 가능 -->
	          <%-- <div class="post_buttons">
	            <div class="func_button">
	              <c:if test="${sessionScope.memberNumber eq post.getMemberNumber()}">
	                <button type="button" class="modify-btn edit"
	                  data-board-number="${post.postNumber}"
	                  data-member-number="${sessionScope.memberNumber}">수정</button>
	
	                <button type="button" class="delete-btn delete"
	                  data-board-number="${post.postNumber}"
	                  data-member-number="${sessionScope.memberNumber}">삭제</button>
	              </c:if>
	            </div>
	          </div> --%>
	        </section>
	
	        <!-- 추천/신고 버튼 -->
	        <div class="post_buttons">
	          <button class="recommend" id="recommendBtn" title="게시글 추천하기">
	            <img src="${pageContext.request.contextPath}/assets/img/like.jpg" alt="추천 버튼" />
	          </button>
	          <span class="recommend_count" id="recommendCount">추천 <c:out value="${notice.postLikeCount}" /></span>
	          <button type="button" class="report" id="openReportModal" title="신고하기">신고</button>
	        </div>
	      </div> <!-- .post -->
	    </article>

      <button id="darkModeToggle" title="다크 모드 토글">🌓</button>
    </div>
  </main>

  <!-- 신고 모달 -->
  <div class="modal_bg" id="reportModal">
    <div class="modal">
      <h2>게시글 신고하기</h2>
      <form id="reportForm">
        <div class="report_reasons">
          <label><input type="radio" name="reason" value="스팸/광고" required> 스팸/광고</label>
          <label><input type="radio" name="reason" value="욕설/비방"> 욕설/비방</label>
          <label><input type="radio" name="reason" value="음란물"> 음란물</label>
          <label><input type="radio" name="reason" value="개인정보 노출"> 개인정보 노출</label>
          <label><input type="radio" name="reason" value="기타"> 기타</label>
        </div>
        <div class="modal_buttons">
          <button type="button" id="cancelReport">취소</button>
          <button type="submit" id="submitReport">신고하기</button>
        </div>
      </form>
    </div>
  </div>

  <!-- 나무 등급 정보 모달 -->
  <div id="treeInfoModal" class="tree_modal_bg" aria-hidden="true" role="dialog" aria-labelledby="treeInfoTitle"
    aria-modal="true">
    <div class="tree_modal" role="document">
      <button id="closeTreeInfoModal" aria-label="닫기" class="tree_modal_close_btn">&times;</button>
      <h2 id="treeInfoTitle">나무 등급 정보</h2>
      <div id="treeInfoContent"></div>
    </div>
  </div>



  <!-- 푸터 -->
  <jsp:include page="/footer.jsp" />
  
  <aside class="side"></aside>
  
</body>
<script>
    window.ctx = "${pageContext.request.contextPath}";
    window.postNumber = ${notice.postNumber};
    window.memberNumber = ${sessionScope.memberNumber != null ? sessionScope.memberNumber : null};
    window.adminNumber = ${empty sessionScope.adminNumber ? 0 : sessionScope.adminNumber};
</script>
</html>