<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>고객센터 - 신고글 작성</title>
  <link rel="stylesheet" href="./../../assets/css/community/writeCustomerService.css" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <link rel="stylesheet" href="./../../assets/css/community/treeGrade.css">
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script defer src="./../../assets/js/community/writeCustomerServiceList.js"></script>
  <script>
    let headerPath = '../../header_login.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
</head>

<body>
  <header id="header"></header>

  <main class="layout">
    <aside class="side"></aside>

    <section class="container">
      <div class="title_section">
        <div class="tag">문의/신고글 작성</div>
        <div class="profile_tree">
          <img src="./../../assets/img/나무.png" alt="나무" class="tree_icon author_profile" />
          namhyuk
        </div>
        <h1 class="post_title">문의/신고글 작성</h1>
      </div>

      <form action="/submit_post" method="post" class="write_form" enctype="multipart/form-data">
        <!-- 글 유형 선택 -->
        <div class="form_group_type">
          <label>글 유형</label><br><br>
          <input type="radio" name="postType" value="inquiry" id="typeInquiry" checked>
          <label for="typeInquiry">문의</label>
          <input type="radio" name="postType" value="report" id="typeReport">
          <label for="typeReport">신고</label>
        </div>

        <!-- 태그 선택 -->
        <div class="form_group">
          <label for="tagSelect">태그</label>
          <select id="tagSelect" name="tagSelect" required>
            <!-- 문의 태그 -->
            <option value="[문의]" class="inquiry">[문의]</option>
            <option value="[기타]" class="inquiry">[기타]</option>

            <!-- 신고 태그 -->
            <option value="[위생]" class="report">[위생]</option>
            <option value="[서비스 불만족]" class="report">[서비스 불만족]</option>
            <option value="[가격/환불 문제]" class="report">[가격/환불 문제]</option>
            <option value="[제품 품질]" class="report">[제품 품질]</option>
            <option value="[안전 문제]" class="report">[안전 문제]</option>
            <option value="[기타]" class="report">[기타]</option>
          </select>
        </div>

        <div class="form_group">
          <label for="title">제목</label>
          <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
        </div>

        <div class="form_group">
          <label for="content">내용</label>
          <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
        </div>

        <div class="form_group">
          <label for="imageUpload">사진 첨부</label>
          <input type="file" id="imageUpload" name="imageUpload" accept="image/*" multiple />
        </div>

        <div class="button_group">
          <button type="reset" class="cancel_btn">작성 취소</button>
          <button type="submit" class="submit_btn">작성 완료</button>
        </div>
      </form>
    </section>

    <aside class="side"></aside>
  </main>

  <footer id="footer"></footer>
</body>

</html>