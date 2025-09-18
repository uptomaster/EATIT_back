<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>밥세권 | 내 정보 수정</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sellerMyPage/editSellerInfo.css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico" type="image/x-icon">

  <!-- 헤더/푸터 및 페이지 스크립트 -->
  <script defer src="${pageContext.request.contextPath}/assets/js/main.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/header.js"></script>
  <script defer src="${pageContext.request.contextPath}/assets/js/sellerMyPage/editSellerInfo.js"></script>

  <script>
  let headerPath = './../../header.jsp';
  let footerPath = './../../footer.jsp';
  </script>
</head>

<body>
  <jsp:include page="${pageContext.request.contextPath}/header.jsp" />
  <main>
    <!-- 좌측 사이드 메뉴 -->
    <div class="seller_my_page_list">
      <div class="seller_my_page">마이 페이지</div>
      <ul class="seller_side_bar">
				<li class="store_info_menu_list_current"><a href="${pageContext.request.contextPath}/sellerMyPage/editSellerInfo.se">내 정보 수정</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/sellerfoodPurchaseList.se">음식 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/selleringredientPurchaseList.se">재료 구매 내역</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myPosts.se">내 글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myComments.se">내 댓글 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/myReviews.se">내 리뷰 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/storeInfo.se">사업장관리</a></li>
        <li><a href="${pageContext.request.contextPath}/sellerMyPage/todaySaleList.se">판매 내역</a></li>
      </ul>
    </div>

    <!-- 내 정보 수정 (1100px 영역) -->
    <form class="seller_edit_user_info"
          action="${pageContext.request.contextPath}/sellerMyPage/editSellerInfoOk.se"
          method="post"
          enctype="multipart/form-data">

      <!-- 바인딩된 데이터 없을 때 가드 -->
      <c:if test="${empty sellerInfo}">
        <div class="seller_info_area">
          <p style="padding:16px;color:#d00;">조회할 사용자 정보가 없습니다. 다시 시도해 주세요.</p>
        </div>
      </c:if>

      <c:if test="${not empty sellerInfo}">
        <!-- 숨은 값들: 서버에서 식별용 -->
        <input type="hidden" name="memberNumber" value="${sellerInfo.memberNumber}">
        <input type="hidden" name="businessNumber" value="${sellerInfo.businessNumber}">

        <!-- 페이지 제목 -->
        <h2 class="seller_my_info">내 정보 수정</h2>

        <div class="seller_info_area">
           <!-- 프로필 사진 -->
        <!--  <div class="seller_info_modify_area">
            <div class="seller_info_menu">프로필 사진</div>
            <label class="seller_insert_profile" id="add_ficture" for="profileImage">
              <input type="file" id="profileImage" name="profileImage" accept="image/*">
              <img id="preview" src="#" alt="미리보기"
                   style="display:contents;width:65px;height:65px;border-radius:100px;object-fit:cover;">
            </label>◀사진등록
            <p>*개인정보가 포함된 이미지 등록은 자제하여주시길 바랍니다.</p>
            <button type="button" class="seller_info_save_buzz">저장</button>
          </div> -->

          <!-- 아이디 (수정 불가) -->
          <div class="seller_info_unable_modify_area">
            <div class="seller_info_menu">아이디</div>
            <div class="seller_gray_box">
              <p><c:out value="${sellerInfo.memberId}" /></p>
              <p>*아이디는 수정 불가능합니다.</p>
            </div>
          </div>

          <!-- 비밀번호 변경 영역 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">현재 비밀번호*</div>
            <div>
              <div class="seller_gray_box">
                <input id="current_password" class="seller_input_info" type="password"
                       placeholder="[입력가능] 현재 비밀번호를 입력후 Enter" name="currentPassword">
              </div>
              <p id="current_password_error" class="seller_notice_input_wrong_info"></p>
            </div>
          </div>
          
	  			<!-- 새 비밀번호 입력 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">새 비밀번호*</div>
            <div>
              <div class="seller_gray_box">
                <input id="new_password" class="seller_input_info" type="password"
                       placeholder="현재 비밀번호 확인 후 입력 가능" name="newPassword"
                       value="${param.newPassword != null && param.newPassword != 'null' ? param.newPassword : ''}">
              </div>
              <!-- 비밀번호 불일치시 안내글 공간  -->
              <p id="new_password_error" class="seller_notice_input_wrong_info"></p>
            </div>
          </div>
	 				
	 				<!-- 새 비밀번호 확인 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">새 비밀번호 확인*</div>
            <div>
              <div class="seller_gray_box">
                <input id="confirm_password" class="seller_input_info" type="password"
                       placeholder="현재 비밀번호 확인 후 입력 가능" name="confirmPassword">
              </div>
              <p id="confirm_password_error" class="seller_notice_input_wrong_info"></p>
            </div>
            <!-- 서버로 보낼 실제 필드: 컨트롤러에서는 memberPassword 파라미터로 받음 -->
            <input type="hidden" name="memberPassword" id="memberPasswordHidden" value="">
          </div>

          <!-- 이름 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">이름*</div>
            <div class="seller_gray_box">
              <input name="sellerName" class="seller_input_info" type="text"
                     value="<c:out value='${sellerInfo.sellerName}'/>" placeholder="[입력가능] 이름">
            </div>
          </div>

          <!-- 기존 전화번호(표시만) -->
          <div class="seller_info_unable_modify_area">
            <div class="seller_info_menu">현재 전화번호*</div>
            <div class="seller_gray_box">
              <p><c:out value="${sellerInfo.sellerPhoneNumber}" /></p>
            </div>
          </div>

          <!-- 새 전화번호 입력 + 인증 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">새 전화번호*</div>
            <div>
              <div class="seller_gray_box">
                <input id="new_phone" class="seller_input_info" type="text" placeholder="[입력가능] 숫자만" 
                			 name="newPhone"
                       value="${param.newPhone != null && param.newPhone != 'null' ? param.newPhone : ''}">
              </div>
              <p id="phone_error" class="seller_notice_input_wrong_info" style="color:red;"></p>
            </div>
            <button type="button" id="send_code_btn" class="seller_info_send_code_buzz">인증번호 전송</button>
          </div>

          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">인증번호*</div>
            <div>
              <div class="seller_gray_box">
                <input id="code_input" name="phoneCode" class="seller_input_info" type="text" placeholder="[입력가능] 인증번호 입력">
              </div>
              <p id="code_error" class="seller_notice_input_wrong_info" style="color:red;"></p>
            </div>
            <!-- 인증 성공 시 서버로 보낼 실제 필드 -->
            <input type="hidden" name="sellerPhoneNumber" id="verifiedPhoneHidden" value="${sellerInfo.sellerPhoneNumber}">
						<!-- 인증번호 요청 버튼 -->
						<button type="button"
							        formaction="${pageContext.request.contextPath}/sellerMyPage/sellerMyPageSmsSend.se" value="check"
							        id="check_code_btn" class="info_check_code_buzz">인증번호 확인 
		        </button>
         </div>

          <!-- 생년월일 (표시만) -->
          <div class="seller_info_unable_modify_area">
            <div class="seller_info_menu">생년월일</div>
            <div class="seller_gray_box">
              <p>
				        <c:choose>
				          <c:when test="${not empty sellerInfo.sellerBirthdate}">
				            ${sellerInfo.sellerBirthdate}
				          </c:when>
				          <c:otherwise>-</c:otherwise>
				        </c:choose>
				      </p>
            </div>
          </div>

          <!-- 사업자 등록 번호 (표시만) -->
          <div class="seller_info_unable_modify_area">
            <div class="seller_info_menu">사업자 등록번호</div>
            <div class="seller_gray_box">
              <p><c:out value="${sellerInfo.businessNumber}" /></p>
            </div>
          </div>

          <!-- 상호명 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">상호명</div>
            <div>
              <div class="seller_gray_box">
              	<p><c:out value="${sellerInfo.storeName}" /></p>
                <%-- <input id="store_name" name="storeName" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeName}'/>"> --%>
              </div>
              <p id="store_name_error" class="seller_notice_input_wrong_info"></p>
            </div>
            <!-- <button type="button" id="store_name_save_btn" class="seller_info_save_store_name_buzz">저장</button> -->
          </div>

          <!-- 업체 주소 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">업체 주소</div>
            <div>
              <div class="seller_gray_box">
              	<p><c:out value="${sellerInfo.storeAddress}" /></p>
                <%-- <input id="store_address" name="storeAddress" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeAddress}'/>"> --%>
              </div>
              <p id="store_address_error" class="seller_notice_input_wrong_info"></p>
            </div>
 	 <!-- 	 <button type="button" id="store_address_save_btn" class="seller_info_save_address_buzz">저장</button> -->
          </div>

          <!-- 업체 상세 주소 & 우편번호 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">상세 주소 / 우편번호</div>
            <div>
              <div class="seller_gray_box">
              	<p><c:out value="${sellerInfo.storeAddressDetail}+'  /  '+${sellerInfo.storeZipCode}" /></p>
                <%-- <input name="storeAddressDetail" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeAddressDetail}+"  /  "+${sellerInfo.storeZipCode}'/>"> --%>
              </div>
              <%-- <div class="seller_gray_box" >
                <input name="storeZipCode" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeZipCode}'/>" placeholder="[입력가능] 우편번호">
              </div> --%>
            </div>
          </div>

          <!-- 업체 전화번호 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">업체 전화번호</div>
            <div>
              <div class="seller_gray_box">
              	<p><c:out value="${sellerInfo.storeTel}" /></p>
                <%-- <input id="store_phone" name="storeTel" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeTel}'/>" placeholder="[입력가능] 숫자만 입력"> --%>
              </div>
              <p id="store_phone_error" class="seller_notice_input_wrong_info"></p>
            </div>
           <!--  <button type="button" id="store_phone_save_btn" class="seller_info_save_phone_buzz">저장</button> -->
          </div>

          <!-- 업체 개업일 (표시만) -->
          <div class="seller_info_unable_modify_area">
            <div class="seller_info_menu">업체 개업일</div>
            <div class="seller_gray_box">
              <p><c:out value="${sellerInfo.storeOpenDate}" /></p>
            </div>
          </div>

          <!-- 영업 시간 -->
          <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">영업 시작 시간</div>
            <div>
              <div class="seller_gray_box">
                <input name="storeOpenTime" id="store_open_time" class="seller_input_info" type="text"
                       value="<c:out value='${sellerInfo.storeOpenTime}'/>" placeholder="오픈(HH:mm)">
              </div>
              <p id="store_open_time_error" class="seller_notice_input_wrong_info"></p>
            </div>
          </div>
         <div class="seller_info_able_modify_area">
         <div class="seller_info_menu">영업 시작 시간</div>
          <div class="seller_gray_box">
            <input name="storeCloseTime" id="store_close_time" class="seller_input_info" type="text"
                   value="<c:out value='${sellerInfo.storeCloseTime}'/>" placeholder="마감(HH:mm)">
           </div>
           <p id="store_close_time_error" class="seller_notice_input_wrong_info"></p>
          </div>
         </div>

           <!-- 광고 정보 수신 동의 (UI만; 서버 반영은 추후 확장) -->
        <!--  <div class="seller_info_able_modify_area">
            <div class="seller_info_menu">광고 정보 수신동의</div>
            <div class="seller_gray_box_adverisement">
              <div class="seller_advertisement_agreement"><input type="checkbox" id="agree_sms">SMS</div>
              <div class="seller_advertisement_agreement"><input type="checkbox" id="agree_tel">전화</div>
            </div>
            <button type="button" class="seller_info_save_buzz">저장</button>
          </div>
        </div> -->
			</c:if>
       <!-- 전체 저장 버튼 -->
			  <div class="bottom_btn_container">
			  	  <div class="agreement_buzz">
				    <a href="${pageContext.request.contextPath}/sellerMyPage/withdrawalAgreement.se">회원탈퇴</a>
				  </div>
				  <button type="button" class="total_info_save_buzz" data-member-number="${member.memberNumber}">저장</button>
			  </div>

    </form>
  </main>

  <jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
</body>
</html>
