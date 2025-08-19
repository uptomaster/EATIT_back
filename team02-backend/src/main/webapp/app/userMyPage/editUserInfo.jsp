<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=, initial-scale=1.0">
  <link rel="shortcut icon" href="./../../assets/img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="./../../assets/css/userMyPage/editUserInfo.css">
  <link rel="stylesheet" href="./../../assets/css/header.css">
  <link rel="stylesheet" href="./../../assets/css/footer.css">
  <script defer src="./../../assets/js/userMyPage/editUserInfo.js"></script>
    <script>
    let headerPath = '../../header.html';
    let footerPath = '../../footer.html';
  </script>
  <script defer src="../../assets/js/header.js"></script>
  <title>밥세권</title>
</head>

<body>
  <!-- 헤더 -->
  <header id="header"></header>
  <!-- 메인 -->
  <main>
      <!-- 마이페이지 전체 레이아웃 영역 -->
    <div class="my_page_list">
      <!-- 마이페이지 제목 -->
      <div class="my_page">마이 페이지</div>

      <!-- 마이페이지 사이드 메뉴 -->
      <ul class="side_bar">
        <li class="my_page_list_main"><a href="./../userMyPage/editUserInfo.html">내 정보 수정</a></li>
        <li><a href="./../userMyPage/foodPurchaseList.html">음식 구매 내역</a></li>
        <li><a href="./../userMyPage/ingredientPurchaseList.html">재료 구매 내역</a></li>
        <li><a href="./../userMyPage/manageMyPostsList.html">내 글 관리</a></li>
        <li><a href="./../userMyPage/manageMyCommentsList.html">내 댓글 관리</a></li>
        <li><a href="./../userMyPage/manageMyReviewsList.html">내 리뷰 관리</a></li>
      </ul>
    </div>

    <!-- 내 정보 수정 입력 폼 -->
    <form class="edit_user_info" action="" method="post">
      <h2 class="my_info">내 정보 수정</h2>

      <div class="info_area">

        <!-- 프로필 사진 업로드 영역 -->
        <div class="info_modify_area">
          <div class="info_menu">프로필 사진</div>
          <label class="insert_profile" id="add_ficture" for="profileImage">
            <!-- 파일 업로드 -->
            <input type="file" id="profileImage" name="profileImage" accept="image/*">
            <!-- 이미지 미리보기 -->
            <img id="preview" src="#" alt="미리보기"
              style="display:contents; width: 65px; height: 65px; border-radius: 100px; object-fit: cover;">
          </label>◀사진등록
          <p>*개인정보가 포함된 이미지 등록은 자제하여주시길 바랍니다.</p>
          <!-- 저장 버튼 -->
          <button type="button" class="info_save_buzz"><a href="#">저장</a></button>
        </div>

        <!-- 수정 불가능한 아이디 정보 -->
        <div class="info_unable_modify_area">
          <div class="info_menu">아이디</div>
          <div class="gray_box">
            <p>기존아이디</p>
            <p>*아이디는 수정 불가능합니다.</p>
          </div>
        </div>

        <!-- 현재 비밀번호 입력 -->
        <div class="info_able_modify_area">
          <div class="info_menu">비밀번호*</div>
          <div>
            <div class="gray_box">
              <input id="current_password" class="input_info" type="text" placeholder="[입력가능] 현재 비밀번호 확인">
            </div>
            <p id="current_password_error" class="notice_input_wrong_info"></p>
          </div>
        </div>

        <!-- 새 비밀번호 입력 -->
        <div class="info_able_modify_area">
          <div class="info_menu">새 비밀번호*</div>
          <div>
            <div class="gray_box">
              <input id="new_password" class="input_info" type="text" placeholder="[입력가능] 새 비밀번호를 입력해주세요">
            </div>
            <p id="new_password_error" class="notice_input_wrong_info"></p>
          </div>
        </div>

        <!-- 새 비밀번호 확인 -->
        <div class="info_able_modify_area">
          <div class="info_menu">새 비밀번호 확인*</div>
          <div>
            <div class="gray_box">
              <input id="confirm_password" class="input_info" type="text" placeholder="[입력가능] 새 비밀번호를 다시입력해주세요">
            </div>
            <p id="confirm_password_error" class="notice_input_wrong_info"></p>
          </div>
          <!-- 비밀번호 저장 버튼 -->
          <button type="button" id="password_save_btn" class="info_save_pw_buzz">저장</button>
        </div>

        <!-- 이름 (수정 불가능 or 텍스트 출력용?) -->
        <div class="info_able_modify_area">
          <div class="info_menu">이름*</div>
          <div class="gray_box">
            <p>현재 이름</p>
          </div>
        </div>

        <!-- 기존 전화번호 표시 -->
        <div class="info_unable_modify_area">
          <div class="info_menu">전화번호*</div>
          <div class="gray_box">
            <p>현재 전화번호</p>
          </div>
        </div>

        <!-- 새 전화번호 입력 -->
        <div class="info_able_modify_area">
          <div class="info_menu">새 전화번호*</div>
          <div>
            <div class="gray_box">
              <input id="new_phone" class="input_info" type="text" placeholder="[입력가능] 새 전화번호">
            </div>
            <p id="phone_error" class="notice_input_wrong_info" style="color:red;"></p>
          </div>
          <!-- 인증번호 전송 버튼 -->
          <button id="send_code_btn" class="info_send_code_buzz">인증번호 전송</button>
        </div>

        <!-- 인증번호 입력 -->
        <div class="info_able_modify_area">
          <div class="info_menu">인증번호*</div>
          <div>
            <div class="gray_box">
              <input id="code_input" class="input_info" type="text" placeholder="[입력가능] 인증번호를 입력하세요">
            </div>
            <p id="code_error" class="notice_input_wrong_info" style="color:red;"></p>
          </div>
          <!-- 인증번호 확인 버튼 -->
          <button id="check_code_btn" class="info_check_code_buzz">인증번호 확인</button>
        </div>

        <!-- 생년월일 (수정 불가) -->
        <div class="info_unable_modify_area">
          <div class="info_menu">생년월일</div>
          <div class="gray_box">
            <p>현재 생년월일</p>
          </div>
        </div>

        <!-- 광고 수신 동의 체크박스 -->
        <div class="info_able_modify_area">
          <div class="info_menu">광고 정보 수신동의</div>
          <div class="gray_box_adverisement">
            <div class="advertisement_agreement"><input type="checkbox">SMS</div>
            <div class="advertisement_agreement"><input type="checkbox">전화</div>
          </div>
        </div>
      </div>

      <!-- 전체 저장 버튼 -->
      <button class="total_info_save_buzz"><a href="#">저장</a></button>

      <!-- 회원탈퇴 링크 -->
      <div class="agreement_buzz">
        <a href="./../../app/userMyPage/withdrawalAgreement.html">회원탈퇴</a>
      </div>
    </form>
  </main>
  <!-- 푸터 -->
  <footer id="footer"></footer>
</body>

</html>