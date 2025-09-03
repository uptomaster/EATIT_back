<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>adminLogin</title>
  <script defer src="./../../assets/js/admin/postTradeDetail.js"></script>
  <link rel="stylesheet" href="./../../assets/css/admin/postTradeDetail.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
  integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
  crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
      <a href="./../../app/admin/dashboard.html"><img src="./../../assets/img/admin_logo.png" alt="admin_logo" class="admin_logo"></a>
      <ul class="sidebar_ul">
        <a href="./../../app/admin/dashboard.html"><li id="sidebar_list_dashboard" class="sidebar_list">대쉬보드</li></a>
        <a href="./../../app/admin/memberList.html"><li id="sidebar_list_member" class="sidebar_list">회원관리</li></a>
        <a href="./../../app/admin/postTradeList.html"><li id="sidebar_list_community" class="sidebar_list">게시글 관리</li></a>
        <a href="./../../app/admin/reportList.html"><li id="sidebar_list_warning"class="sidebar_list">신고관리</li></a>
        <a href="./../../app/admin/adminCustomerService.html"><li id="sidebar_list_customerservice"class="sidebar_list">고객센터</li></a>
      </ul>
      <button id="admin_logoutbtn">로그아웃</button>
    </aside>
    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1>게시글 관리</h1>
      <div class="admin_listwrapper">
        <div class="admin_whitebox">
          <div class="wrap">
            <!-- 가게 정보 및 메뉴 영역 -->
            <div class="buy_left_area">
              <div class="buy_store_info">
                <!-- 가게 이미지 -->
                <img src="./../../assets/img/store.jpg" alt="가게 이미지 추가하기">
                <div class="buy_add_heart">
                  <div class="buy_store_info_detail">
                    <p class="buy_store_name">가게명</p>
                    <p class="buy_store_address">가게 주소</p>
                    <p class="buy_store_open_time">영업시간 00:00~00:00</p>
                  </div>
                  <a href="#">
                    <img src="./../../assets/img/heart_inactive.png" alt="">
                  </a>
                </div>
              </div>
              <!-- 구매> 메뉴 영역 -->
              <div class="buy_food">
                <ul>
                  <li class="buy_food_menu_choice"><a href="#">메뉴</a></li>
                  <li class="buy_food_menu_choice"><a href="#">재료</a></li>
                  <li class="buy_food_menu_choice"><a href="#">리뷰(00)</a></li>
                </ul>
                <div class="buy_food_menu">
                  <div class="buy_food_menu_list">
                    <img src="./../../assets/img/store.jpg" alt="">
                    <div class="buy_food_menu_info_stock">
                      <div class="buy_food_stock">N개 남음</div>
                      <div class="buy_food_menu_info">
                        <h3>[best]메뉴명</h3>
                        <p>소비기한:2025년00월00일</p>
                        <h3>0,000원</h3>
                      </div>
                      <div class="buy_food_stock_choice">
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM232 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296z"/>
                            </svg>
                          </a>
                        </div>
                        <p>1</p>
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM296 408L296 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296L296 296L296 232C296 218.7 306.7 208 320 208C333.3 208 344 218.7 344 232L344 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L344 344L344 408C344 421.3 333.3 432 320 432C306.7 432 296 421.3 296 408z"/>
                            </svg>
                          </a>
                        </div>
                        <a href="#">
                          <div class="buy_add_cart_btn">장바구니 담기</div>
                        </a>
                      </div>
                    </div>
                  </div>
                  <hr>
                  <div class="buy_food_menu_list">
                    <img src="./../../assets/img/store.jpg" alt="">
                    <div class="buy_food_menu_info_stock">
                      <div class="buy_food_stock">N개 남음</div>
                      <div class="buy_food_menu_info">
                        <h3>[best]메뉴명</h3>
                        <p>소비기한:2025년00월00일</p>
                        <h3>0,000원</h3>
                      </div>
                      <div class="buy_food_stock_choice">
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM232 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296z"/>
                            </svg>
                          </a>
                        </div>
                        <p>1</p>
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM296 408L296 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296L296 296L296 232C296 218.7 306.7 208 320 208C333.3 208 344 218.7 344 232L344 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L344 344L344 408C344 421.3 333.3 432 320 432C306.7 432 296 421.3 296 408z"/>
                            </svg>
                          </a>
                        </div>
                        <a href="#">
                          <div class="buy_add_cart_btn">장바구니 담기</div>
                        </a>
                      </div>
                    </div>
                  </div>
                  <hr>
                  <div class="buy_food_menu_list">
                    <img src="./../../assets/img/store.jpg" alt="">
                    <div class="buy_food_menu_info_stock">
                      <div class="buy_food_stock">N개 남음</div>
                      <div class="buy_food_menu_info">
                        <h3>[best]메뉴명</h3>
                        <p>소비기한:2025년00월00일</p>
                        <h3>0,000원</h3>
                      </div>
                      <div class="buy_food_stock_choice">
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM232 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296z"/>
                            </svg>
                          </a>
                        </div>
                        <p>1</p>
                        <div class="buy_food_stock_choice_box">
                          <a href="#">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
                              <!--!Font Awesome Free v7.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.-->
                              <path fill="#202020" d="M320 576C461.4 576 576 461.4 576 320C576 178.6 461.4 64 320 64C178.6 64 64 178.6 64 320C64 461.4 178.6 576 320 576zM296 408L296 344L232 344C218.7 344 208 333.3 208 320C208 306.7 218.7 296 232 296L296 296L296 232C296 218.7 306.7 208 320 208C333.3 208 344 218.7 344 232L344 296L408 296C421.3 296 432 306.7 432 320C432 333.3 421.3 344 408 344L344 344L344 408C344 421.3 333.3 432 320 432C306.7 432 296 421.3 296 408z"/>
                            </svg>
                          </a>
                        </div>
                        <a href="#">
                          <div class="buy_add_cart_btn">장바구니 담기</div>
                        </a>
                      </div>
                    </div>
                  </div>
                  <!-- 페이지네이션 -->
                  <div class="buy_pagenation">
                    <ul>
                      <li class="buy_pagenation_box"><a href="#">&lt;</a></li>
                      <li class="buy_pagenation_box"><a href="#">1</a></li>
                      <li class="buy_pagenation_box"><a href="#">2</a></li>
                      <li class="buy_pagenation_box"><a href="#">3</a></li>
                      <li class="buy_pagenation_box"><a href="#">4</a></li>
                      <li class="buy_pagenation_box"><a href="#">5</a></li>
                      <li class="buy_pagenation_box"><a href="#">&gt;</a></li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
            <!-- 가게 지도 -->
            <div class="buy_map_area">
              <!-- 지도 api 영역-->
              <div class="buy_map"></div>
              <!-- 사업자 정보 및 원산지 정보 표기 -->
              <div class="buy_origin">
                <ul class="buy_origin_menu">
                  <li class="buy_origin_info"><a href="#">가게정보</a></li>
                  <li class="buy_origin_info"><a href="#">원산지</a></li>
                </ul>
                <div class="buy_origin_store_info">
                  <ul class="buy_origin_content">
                    <li>사업자 등록 번호</li>
                    <li>상호명</li>
                    <li>주소</li>
                    <li>가게 전화전호</li>
                    <li>개업일</li>
                  </ul> 
                  <ul class="buy_origin_seller">
                    <li>000-00-00000</li>
                    <li>상호명</li>
                    <li>서울시 강남구</li>
                    <li>02-000-0000</li>
                    <li>2025년 08월 00일</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="admin_detail_delete">
          <button id="admin_deletebtn" type="button">삭 제</button>
        </div>       
      </div>  
    </div>
  </div>
</body>
</html>