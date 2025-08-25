<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/editSellerInfo.css">
  <!-- 파비콘 -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더 js -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>   
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/editSellerInfo.js"></script>

<script>
    let headerPath = './../../header.jsp';
    let footerPath = './../../footer.jsp';
</script>
  <title>밥세권</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/editSellerInfo.css">
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/editSellerInfo.js"></script>

</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 내 정보 수정 -->
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_my_page_list">
      <div class="seller_my_page">마이 페이지</div>
      <ul class="seller_side_bar">
				<li class="store_info_menu_list_current"><a href="/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedFood.se">음식 	구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/buiedIngredient.se">재료 구매 내역</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내	글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
				<li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>
    <!-- 내 정보 수정 창 / 1100px 영역 -->
    <form class="seller_edit_user_info" action="" method="post">
      <!-- 페이지 제목 -->
      <h2 class="seller_my_info">내 정보 수정</h2>
      <!-- 컨텐츠 컨테이너 -->
      <div class="seller_info_area">
        <!-- 프로필 사진 -->
        <div class="seller_info_modify_area">
          <div class="seller_info_menu">프로필 사진</div>
          <label class="seller_insert_profile" id="add_ficture" for="profileImage">
            <input type="file" id="profileImage" name="profileImage" accept="image/*">
            <img id="preview" src="#" alt="미리보기"
              style="display:contents; width: 65px; height: 65px; border-radius: 100px; object-fit: cover;">
          </label>◀사진등록
          <p>*개인정보가 포함된 이미지 등록은 자제하여주시길 바랍니다.</p>
          <button type="button" class="seller_info_save_buzz"><a href="#">저장</a></button>
        </div>
        <!-- 아이디 -->
        <div class="seller_info_unable_modify_area">
          <div class="seller_info_menu">아이디</div>`
          <div class="seller_gray_box">
            <p>기존아이디</p>
            <p>*아이디는 수정 불가능합니다.</p>
          </div>
        </div>
        <!-- 기존 비밀번호 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">비밀번호*</div>
          <div>
            <div class="seller_gray_box">
              <input id="current_password" class="seller_input_info" type="text"
                placeholder="[입력가능] 현재 비밀번호를 입력후 enter를 입력하세요">
            </div>
            <p id="current_password_error" class="seller_notice_input_wrong_info"></p> <!-- 여기에 메시지 출력 -->
          </div>
        </div>
        <!-- 새 비밀번호 입력 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">새 비밀번호*</div>
          <div>
            <div class="seller_gray_box">
              <input id="new_password" class="seller_input_info" type="text" placeholder="현재 비밀번호를 먼저 입력해주세요">
            </div>
            <p id="new_password_error" class="seller_notice_input_wrong_info"></p>
          </div>
        </div>
        <!-- 비밀번호 확인-->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">새 비밀번호 확인*</div>
          <div>
            <div class="seller_gray_box">
              <input id="confirm_password" class="seller_input_info" type="text" placeholder="현재 비밀번호를 먼저 입력해주세요">
            </div>
            <p id="confirm_password_error" class="seller_notice_input_wrong_info"></p>
          </div>
          <button type="button" id="password_save_btn" class="seller_info_save_pw_buzz">저장</button>
        </div>
        <!-- 이름 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">이름*</div>
          <div class="seller_gray_box">
            <p>현재 이름</p>
          </div>
        </div>
        <!-- 기존 전화번호 -->
        <div class="seller_info_unable_modify_area">
          <div class="seller_info_menu">전화번호*</div>
          <div class="seller_gray_box">
            <p>현재 전화번호</p>
          </div>
        </div>
        <!-- 새 전화번호 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">새 전화번호*</div>
          <div>
            <div class="seller_gray_box">
              <input id="new_phone" class="seller_input_info" type="text" placeholder="[입력가능] 새 전화번호">
            </div>
            <p id="phone_error" class="seller_notice_input_wrong_info" style="color:red;"></p>
          </div>
          <button id="send_code_btn" class="seller_info_send_code_buzz">인증번호 전송</button>
        </div>
        <!-- 인증번호 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">인증번호*</div>
          <div>
            <div class="seller_gray_box">
              <input id="code_input" class="seller_input_info" type="text" placeholder="[입력가능] 인증번호를 입력하세요">
            </div>
            <p id="code_error" class="seller_notice_input_wrong_info" style="color:red;"></p>
          </div>
          <button id="check_code_btn" class="seller_info_check_code_buzz">인증번호 확인</button>
        </div>
        <!-- 생년월일 -->
        <div class="seller_info_unable_modify_area">
          <div class="seller_info_menu">생년월일</div>
          <div class="seller_gray_box">
            <p>현재 생년월일</p>
          </div>
        </div>
        <!-- 사업자 등록 번호 -->
        <div class="seller_info_unable_modify_area">
          <div class="seller_info_menu">사업자 등록번호</div>
          <div class="seller_gray_box">
            <p>사업자 등록번호</p>
          </div>
        </div>
        <!-- 상호명 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">상호명</div>
          <div>
            <div class="seller_gray_box">
              <input id="store_name" class="seller_input_info" type="text" placeholder="[입력가능] 상호명을 입력해주세요">
            </div>
            <p id="store_name_error" class="seller_notice_input_wrong_info"></p>
          </div>
          <button type="button" id="store_name_save_btn" class="seller_info_save_store_name_buzz">저장</button>
        </div>
        <!-- 업체 주소 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">업체 주소</div>
          <div>
            <div class="seller_gray_box">
              <input id="store_address" class="seller_input_info" type="text" placeholder="[입력가능] 업체 주소를 입력해주세요">
            </div>
            <p id="store_address_error" class="seller_notice_input_wrong_info"></p>
          </div>
          <button type="button" id="store_address_save_btn" class="seller_info_save_address_buzz">저장</button>
        </div>
        <!-- 업체 전화번호 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">업체 전화번호</div>
          <div>
            <div class="seller_gray_box">
              <input id="store_phone" class="seller_input_info" type="text" placeholder="[입력가능] 숫자만 입력해주세요">
            </div>
            <p id="store_phone_error" class="seller_notice_input_wrong_info"></p>
          </div>
          <button type="button" id="store_phone_save_btn" class="seller_info_save_phone_buzz">저장</button>
        </div>
        <!-- 업체 개업일 -->
        <div class="seller_info_unable_modify_area">
          <div class="seller_info_menu">업체 개업일</div>
          <div class="seller_gray_box">
            <p>YYYY/MM/DD</p>
          </div>
        </div>
        <!-- 광고 정보 수신 동의 -->
        <div class="seller_info_able_modify_area">
          <div class="seller_info_menu">광고 정보 수신동의</div>
          <div class="seller_gray_box_adverisement">
            <div class="seller_advertisement_agreement"><input type="checkbox">SMS</div>
            <div class="seller_advertisement_agreement"><input type="checkbox">전화</div>
          </div>
          <button type="button" class="seller_info_save_buzz"><a href="#">저장</a></button>
        </div>
      </div>
      <!-- 회원탈퇴 버튼 -->
      <button type="button" class="seller_total_info_save_buzz"><a href="#">저장</a></button>
      <div class="seller_agreement_buzz"><a href="./../../app/sellerMyPage/sellerwithdrawalAgreement.html">회원탈퇴</a>
      </div>
    </form> <!-- 1100px 영역 끝-->
  </main>
  <footer id="footer"></footer>
</body>

</html>