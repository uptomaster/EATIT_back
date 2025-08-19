<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/bannerWrite.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/bannerWrite.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
  crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- íììì­ -->
  <div class="admin_innerwrapper">
    <!-- ì¢ì¸¡ ì¬ì´ëë° -->
    <aside class="sidebar">
      <!-- ê´ë¦¬ìíì´ì§ ë¡ê³  -->
      <a href="./../../app/admin/dashboard.html"><img src="./../../assets/img/admin_logo.png" alt="admin_logo" class="admin_logo"></a>
      <ul class="sidebar_ul">
        <a href="./../../app/admin/dashboard.html"><li id="sidebar_list_dashboard" class="sidebar_list">ëì¬ë³´ë</li></a>
        <a href="./../../app/admin/memberList.html"><li id="sidebar_list_member" class="sidebar_list">íìê´ë¦¬</li></a>
        <a href="./../../app/admin/postTradeList.html"><li id="sidebar_list_community" class="sidebar_list">ê²ìê¸ ê´ë¦¬</li></a>
        <a href="./../../app/admin/reportList.html"><li id="sidebar_list_warning"class="sidebar_list">ì ê³ ê´ë¦¬</li></a>
        <a href="./../../app/admin/bannerList.html"><li id="sidebar_list_banner"class="sidebar_list">ë°°ë/ê´ê³ </li></a>
        <a href="./../../app/admin/adminCustomerService.html"><li id="sidebar_list_customerservice"class="sidebar_list">ê³ ê°ì¼í°</li></a>
      </ul>
      <!-- ë¡ê·¸ìì ë²í¼ -->
      <button id="admin_logoutbtn">ë¡ê·¸ìì</button>
    </aside>
    <!-- ë©ì¸ì»¨íì¸  ìì­ -->
    <div class="admin_inner">
      <!-- íì´ì§ ì ëª© -->
      <h1>ìì±íê¸°</h1>
      <!-- ë°ê¹¥ íììì­ -->
      <div class="admin_listwrapper">
        <!-- íììì­ -->
        <div class="admin_whitebox">
          <form action="" method="post">
            <!-- ê³µì§ ìì± ì ëª©ìì­ -->
            <div class="admin_notice_titlebox">
              <!-- íê·¸ ì í -->
              <select class="admin_notice_category">
                <option value="notice">íê·¸</option>
                <option value="notice">ê³µì§ </option>
              </select>
              <!-- ì ëª© -->
              <label for="title">ì ëª© : </label>
              <input type="text" id="title">
              <!-- ë§ê°ì¼ ì í -->
              <label for="date">ë§ê°ì¼</label>
              <input type="date" id="date">
            </div>
            <!-- ìì±ëêµ¬apiìì­ -->
            <div class="write_api"></div>
            <!-- ìì±ìì­ -->
              <textarea name="" id="admin_notice_write"></textarea>
              <!-- ìëìª½ ë²í¼ ìì­ -->
            <div class="bottom_btn_area">
              <!-- íì¼ ì²¨ë¶ -->
              <input type="file" id="admin_file">
              <!-- ìì±ìë£ ë²í¼ -->
              <button type="button" id="admin_notice_dowrite">ìì±ìë£</button>
            </div>
          </form>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>