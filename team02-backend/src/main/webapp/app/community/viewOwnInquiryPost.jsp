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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/viewOwnInquiryPost.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/community/treeGrade.css" />
  <script defer src="${pageContext.request.contextPath}/assets/js/community/reportPostModal.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/viewOtherPost.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/darkmode.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/community/treeGradeModal.js"></script>
<!-- 파비콘 -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">

<!-- 헤더 js -->
<script defer
	src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>

<script>
	let headerPath = './../../header.jsp';
	let footerPath = './../../footer.jsp';
</script>
</head>

<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />

  <aside class="side"></aside>

  <main class="main">
	  <div class="container">
	
				<!-- 법적 안내 문구 -->
				<section class="legal_notice">
					<p>※ 고객센터 문의는 소비자기본법 및 식품위생법을 준수하여 처리됩니다.</p>
				</section>
	
				<!-- FAQ / 문의목록 탭 -->
				<nav class="community_category">
					<ul>
						<li><a
							href="${pageContext.request.contextPath}/community/faqListOk.co"
							class="tab_button">자주묻는질문</a></li>
						<li><a
							href="${pageContext.request.contextPath}/community/inquiryListOk.co"
							class="tab_button active">문의목록</a></li>
					</ul>
				</nav>
	
	    <!-- 게시글 -->
	    <article>
	      <div class="post">
	        <!-- 게시글 헤더 -->
	        <div class="post_header">
	          <div class="post_box">
	            <!-- 작성자 -->
	            <div class="author_box">
	              <img src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="관리자" class="tree_icon" />
	              <span>${post.memberId}</span>
	            </div>
	
	            <!-- 게시글 제목 -->
	            <div class="post_title">
	              <h1 class="post_title"><c:out value="${post.postTitle}" /></h1>
	            </div>
	
	            <!-- 메타 데이터 -->
	            <div class="post_meta">
	              <div class="post_date_area">
	                <p>${post.postCreatedDate}</p>
	              </div>
	              <div class="post_hit_area">
	                <span>조회</span>
	                <span><c:out value="${post.postViewCount}" /></span>
	              </div>
	            </div> <!-- .post_meta -->
	          </div> <!-- .post_box -->
	        </div> <!-- .post_header -->
	
	        <!-- 게시글 내용 -->
	        <section class="content_section">
	          <div class="view-content">
	            <h3><c:out value="${post.inquiryContent}" /></h3>
	            <c:forEach var="img" items="${postImages}">
			    <img src="${pageContext.request.contextPath}/upload/${img.postImageSystemName}" alt="${img.postImageOriginalName}" />
			  </c:forEach>
	          </div>
	          
	
	          <!-- 삭제 버튼 -->
	          <div class="post_buttons">
	            <div class="func_button">
	              <c:if test="${sessionScope.memberNumber eq post.getMemberNumber()}">
	                <button type="button" class="delete-btn delete"
	                  data-board-number="${post.postNumber}"
	                  data-member-number="${sessionScope.memberNumber}">삭제</button>
	              </c:if>
	            </div>
	          </div>
	        </section>
	
	    <!-- 댓글 영역 -->
	    <section class="comment_section">
	      <h2 class="comment_count">
	        <img src="${pageContext.request.contextPath}/assets/img/comment_box.svg" alt="댓글 아이콘" />
	        댓글
	      </h2>
	      <ul class="comment_list" id="commentList"><!-- JS가 채움 --></ul>
	    </section>
	
	    <!-- 다크 모드 버튼 -->
	    <button id="darkModeToggle" title="다크 모드 토글">🌓</button>
	
	  </div> <!-- .container -->
	</main>

  <!-- 나무 등급 정보 모달 -->
  <div id="treeInfoModal" class="tree_modal_bg" aria-hidden="true" role="dialog" aria-labelledby="treeInfoTitle"
    aria-modal="true">
    <div class="tree_modal" role="document">
      <button id="closeTreeInfoModal" aria-label="닫기" class="tree_modal_close_btn">&times;</button>
      <h2 id="treeInfoTitle">나무 등급 정보</h2>
      <div id="treeInfoContent"></div>
    </div>
  </div>



<!-------------------- 푸터 ------------------------>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
  
</body>
<script>
  window.ctx = "${pageContext.request.contextPath}";
  window.postNumber = ${post.postNumber != null ? post.postNumber : 'null'};
  window.memberNumber = ${sessionScope.memberNumber != null ? sessionScope.memberNumber : 'null'};
  window.adminNumber = ${sessionScope.adminNumber  != null ? sessionScope.adminNumber  : 'null'};
  window.postType = "INQUIRY";
  console.log("DEBUG: postNumber =", window.postNumber);
  console.log("DEBUG: memberNumber =", window.memberNumber);
</script>
</html>