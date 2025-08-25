<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원 상세보기</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/memberDetail.css">
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <a href="${pageContext.request.contextPath}/admin/dashboard.ad"><li class="sidebar_list">대쉬보드</li></a>
        <a href="${pageContext.request.contextPath}/admin/memberlistlist.ad"><li class="sidebar_list">회원관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/postTrade/list.ad"><li class="sidebar_list">게시글 관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/report/list.ad"><li class="sidebar_list">신고관리</li></a>
        <a href="${pageContext.request.contextPath}/admin/banner/list.ad"><li class="sidebar_list">배너/광고</li></a>
        <a href="${pageContext.request.contextPath}/admin/inquiry/list.ad"><li class="sidebar_list">고객센터</li></a>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">회원 상세보기</h1>
      <div class="admin_listwrapper">
        <!-- 회원 상세 정보 박스 -->
        
        <!-- 개인 정보 -->
        <h3>개인 정보</h3>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>아이디</p></div>
          <div class="admin_memberDetail_value">${memberDetail.memberId}</div>
          
          <div class="admin_memberDetail_key"><p>이름</p></div>
          <div class="admin_memberDetail_value">${memberDetail.memberName}</div>
          
          <div class="admin_memberDetail_key"><p>연락처</p></div>
          <div class="admin_memberDetail_value">${memberDetail.phoneNumber}</div>
        </div>

        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>회원유형</p></div>
          <div class="admin_memberDetail_value">${memberDetail.memberType}</div>

          <div class="admin_memberDetail_key"><p>경고 수</p></div>
          <div class="admin_memberDetail_value">${memberDetail.warningCount}</div>

          <div class="admin_memberDetail_key"><p>등급</p></div>
          <div class="admin_memberDetail_value">
            <img id="member_grade" src="${pageContext.request.contextPath}/assets/img/씨앗.png" alt="등급">
            (${memberDetail.treeGrade})
          </div>
        </div>

        <!-- 사업자 정보 -->
        <h3>사업자 정보</h3>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>상호</p></div>
          <div class="admin_memberDetail_company_value">${memberDetail.companyName}</div>
          <div class="admin_memberDetail_key"><p>전화번호</p></div>
          <div class="admin_memberDetail_value">${memberDetail.companyTel}</div>
        </div>

        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>주소</p></div>
          <div class="admin_memberDetail_address_value">${memberDetail.companyAddress}</div>
        </div>

        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>개업일자</p></div>
          <div class="admin_memberDetail_value">${memberDetail.openDate}</div>
          <div class="admin_memberDetail_key"><p>사업자번호</p></div>
          <div class="admin_memberDetail_value">${memberDetail.businessNumber}</div>
        </div>

        <!-- 경고/정지 정보 -->
        <h3>경고/정지 정보</h3>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>경고일</p></div>
          <div class="admin_memberDetail_value">${memberDetail.lastWarningDate}</div>
        </div>
        <div class="admin_memberDetail_rowWrapper">
          <div class="admin_memberDetail_key"><p>정지기간</p></div>
          <div class="admin_memberDetail_address_value">
            ${memberDetail.suspendStartDate} ~ ${memberDetail.suspendEndDate}
          </div>
        </div>

        <!-- 버튼 영역 -->
        <div class="admin_memberDetail_rowWrapper_btn">
          <form action="${pageContext.request.contextPath}/admin/member/suspendOk.ad" method="post">
            <input type="hidden" name="memberNumber" value="${memberDetail.memberNumber}">
            <button type="submit" id="button_warning">정지주기</button>
          </form>
          <form action="${pageContext.request.contextPath}/admin/member/blacklistOk.ad" method="post">
            <input type="hidden" name="memberNumber" value="${memberDetail.memberNumber}">
            <button type="submit" id="button_blacklist">블랙리스트</button>
          </form>
        </div>

      </div>
    </div>
  </div>
</body>
</html>
