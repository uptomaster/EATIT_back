<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>신고관리</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/reportList.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/reportList.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
  crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글 관리</a></li>
        <li class="sidebar_list" id="sidebar_list_warning"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a></li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">신고관리</h1>
      <div class="admin_listwrapper">
        
        <!-- 탭 메뉴 -->
        <div class="admin_list_title">
          <ul class="admin_list">
            <li id="admin_list_menu_warning" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/report/list.ad">신고목록</a>
            </li>
            <li id="admin_list_menu_ban" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/suspend/list.ad">정지회원목록</a>
            </li>
            <li id="admin_list_menu_blacklist" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/blacklist/list.ad">블랙리스트</a>
            </li>
          </ul>
        </div>

        <!-- 컬럼 헤더 -->
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <ul class="admin_list_name">
              <li class="admin_list_row">번호</li>
              <li class="admin_list_row">사유</li>
              <li class="admin_list_row">게시글번호</li>
              <li class="admin_list_row">신고대상자</li>
              <li class="admin_list_row">신고자</li>
              <li class="admin_list_row">신고일</li>
              <li class="admin_list_row">신고횟수</li>
            </ul>
          </div>

          <!-- 실제 신고 목록 반복 -->
          <ul class="admin_list_valuebox">
            <c:choose>
              <c:when test="${not empty reportList}">
                <c:forEach var="r" items="${reportList}">
                  <li class="admin_list_value">
                    <p class="admin_list_row">${r.postReportNumber}</p>
                    <p class="admin_list_row">${r.postReportReason}</p>
                    <p class="admin_list_row">${r.postNumber}</p>
                    <p class="admin_list_row">${r.memberNumber}</p> <!-- 신고대상자 -->
                    <p class="admin_list_row">신고자정보필요</p> <!-- TODO: 신고자 join 필요시 수정 -->
                    <p class="admin_list_row">${r.postReportDate}</p>
                    <p class="admin_list_row">${r.postReportCount}</p>
                  </li>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <li class="admin_list_value">
                  <p class="admin_list_row" colspan="7">신고 내역이 없습니다.</p>
                </li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>

        <!-- 페이지네이션/검색 영역 -->
        <div class="admin_pagenation_search">
          <form action="${pageContext.request.contextPath}/admin/report/list.ad" method="get">
            <div class="admin_search">
              <select class="admin_notice_category" name="searchType">
                <option value="reported">신고대상자</option>
                <option value="reporter">신고자</option>
              </select>
              <input type="text" id="search_word" name="searchWord" value="${searchWord}">
              <button class="search_btn" type="submit">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </form>
        </div>

      </div>  
    </div>
  </div>
</body>
</html>
