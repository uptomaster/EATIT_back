<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>자유게시판 - 게시글 작성</title>
  <link rel="stylesheet" href="./../../assets/css/community/writeFreeBoard.css" />
  <link rel="stylesheet" href="./../../assets/css/header.css" />
  <link rel="stylesheet" href="./../../assets/css/footer.css" />
  <link rel="stylesheet" href="./../../assets/css/community/treeGrade.css">
  <script defer src="./../../assets/js/community/loadHeaderFooter.js"></script>
  <script defer src="./../../assets/js/community/writeFreeBoard.js"></script>
  <script defer src="./../../assets/js/community/mouseoverTreeIcon.js"></script>
  <script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
  <script defer src="../../assets/js/header.js"></script>
</head>

<body>
  <header id="header"></header>

  <main class="layout">
    <aside class="side"></aside>

    <section class="container">
      <div class="title_section">
        <div class="tag">자유 게시판</div>
        <div class="profile_tree">
          namhyuk
          <img src="./../../assets/img/나무.png" alt="나무" class="tree_icon author_profile" />
        </div>
        <h1 class="post_title">자유게시글 작성</h1>
      </div>

      <form action="/submit_post" method="post" class="write_form" enctype="multipart/form-data">
        <div class="form_group">
          <label for="tagSelect">태그</label>
          <select id="tagSelect" name="tagSelect" required>
            <option value="" disabled selected>태그를 선택하세요</option>
            <option value="[잡담]">[잡담]</option>
            <option value="[질문]">[질문]</option>
            <option value="[정보공유]">[정보공유]</option>
            <option value="[후기]">[후기]</option>
            <option value="[일상]">[일상]</option>
            <option value="[건의사항]">[건의사항]</option>
            <option value="[이벤트참여]">[이벤트참여]</option>
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