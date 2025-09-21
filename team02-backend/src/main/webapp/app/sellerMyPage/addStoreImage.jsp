<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/addStoreImage.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
	<script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/addStoreImage.js"></script>


<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>

</head>
<body>
	<jsp:include page="${pageContext.request.contextPath}/header.jsp" />
	<!-- 메인 컨텐츠 -->
	<!-- ==================== 가게 이미지 수정: 카드 컨테이너 ==================== -->
<main>
  <section class="store-image-card">

    <div class="store-image-header">
      <h2 class="store-image-title">가게 대표 이미지 수정</h2>
      <p class="store-image-sub">JPG·PNG, 10MB 이하 권장 · 가로 1200px 이상 권장</p>
    </div>

    <!-- ========== 드래그앤드롭 + 미리보기 박스 ========== -->
    <div class="store-image-dropzone" id="dropzone">
      <!-- 좌측: 미리보기 -->
      <div class="store-image-preview" aria-live="polite">
        <!-- 기존 이미지가 있으면 서버에서 내려주는 경로로 출력 -->
        <img id="previewImage" alt="선택한 이미지 미리보기">
        <div class="placeholder" id="previewPlaceholder">
          아직 선택된 이미지가 없습니다.<br>
          우측의 <b>파일 선택</b> 버튼 또는 이 상자를 <b>클릭/드래그</b>해 이미지를 올려주세요.
        </div>
      </div>

      <!-- 우측: 가이드/버튼 -->
      <div class="store-image-side">
        <div class="store-image-help">
          <b>TIP</b><br>
          1) 파일 선택 후 아래 진행바가 완료되면,<br>
          2) <b>저장</b> 버튼으로 서버에 반영합니다.<br>
          3) 이미지가 마음에 들지 않으면 <b>초기화</b> 또는 <b>삭제</b>를 사용할 수 있어요.
        </div>

        <!-- 기존 input/file 이 있으면 삭제하지 말고 주석처리만 -->
        <!-- 기존 -->
        <!-- <input type="file" id="storeImage" name="storeImage"> -->

        <!-- 접근성 유지: 드래그영역 전체 클릭 → 파일 선택 -->
        <input type="file" id="storeImageInput" name="storeImage" accept=".jpg,.jpeg,.png">

        <div class="store-image-actions">
          <button type="button" class="btn btn-ghost" id="btnSelect">파일 선택</button>
          <button type="button" class="btn btn-danger" id="btnClear">초기화</button>
          <button type="button" class="btn btn-danger" id="btnDelete">서버에서 삭제</button>
        </div>

        <div class="progress-wrap" aria-hidden="true">
          <div class="progress"><span id="progressBar"></span></div>
          <small id="progressText">0%</small>
        </div>
      </div>
    </div>

    <!-- 실제 제출 폼: 반드시 .se 서블릿 경로 -->
    <form id="storeImageForm" method="post" 
          action="${pageContext.request.contextPath}/sellerMyPage/storeImageOk.se" 
          enctype="multipart/form-data">
      <!-- 기존 hidden 값들(세션/키 등)이 있다면 여기에 유지 -->
      <!-- 예: <input type="hidden" name="businessNumber" value="${sessionScope.businessNumber}"> -->

      <!-- 파일 인풋은 위에서 선택하지만, form 전송 시 함께 전달 -->
      <!-- 같은 id를 두 번 쓰지 않도록 위 input을 폼 내부로 옮길 수도 있음 -->
      <!-- 여기서는 JS가 선택한 File 객체를 FormData로 담아 보냄 -->
      <div class="store-image-footer">
        변경사항을 저장하려면 아래 <b>저장</b>을 눌러주세요.
      </div>

      <div class="store-image-actions">
        <button type="submit" class="btn btn-primary" id="btnSave">저장</button>
        <a class="btn btn-ghost" href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">취소</a>
      </div>
    </form>

    <!-- (옵션) 과거 업로드 썸네일 라인업 -->
    <div class="thumb-list" aria-label="이전에 업로드했던 이미지들">
      <c:forEach var="thumb" items="${prevImages}">
        <div class="thumb"><img src="${pageContext.request.contextPath}/upload/${thumb.storeImageSystemName}" alt="이전 이미지 썸네일"></div>
      </c:forEach>
      <c:if test="${empty prevImages}">
        <small style="color:#aaa;">이전 이미지 이력이 없습니다.</small>
      </c:if>
    </div>

  </section>
</main>

	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>