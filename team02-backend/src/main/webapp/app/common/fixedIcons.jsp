<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 고정 아이콘 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common/fixedIcons.css">

<!-- 오른쪽 고정 아이콘 -->
<div class="icon_fixed_nav">
  <button type="button" id="treeGradeBtn" class="icon_btn" title="나무등급">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
      <path fill="#202020"
        d="M320 32C327 32 333.7 35.1 338.3 40.5L474.3 200.5C480.4 207.6 481.7 217.6 477.8 226.1C473.9 234.6 465.4 240 456 240L431.1 240L506.3 328.5C512.4 335.6 513.7 345.6 509.8 354.1C505.9 362.6 497.4 368 488 368L449.5 368L538.3 472.5C544.4 479.6 545.7 489.6 541.8 498.1C537.9 506.6 529.4 512 520 512L352 512L352 576C352 593.7 337.7 608 320 608C302.3 608 288 593.7 288 576L288 512L120 512C110.6 512 102.1 506.6 98.2 498.1C94.3 489.6 95.6 479.6 101.7 472.5L190.5 368L152 368C142.6 368 134.1 362.6 130.2 354.1C126.3 345.6 127.6 335.6 133.7 328.5L208.9 240L184 240C174.6 240 166.1 234.6 162.2 226.1C158.3 217.6 159.6 207.6 165.7 200.5L301.7 40.5C306.3 35.1 313 32 320 32z" />
    </svg>
  </button>

  <button type="button" id="location_btn" class="icon_btn" title="위치">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
      <path fill="#202020"
        d="M128 252.6C128 148.4 214 64 320 64C426 64 512 148.4 512 252.6C512 371.9 391.8 514.9 341.6 569.4C329.8 582.2 310.1 582.2 298.3 569.4C248.1 514.9 127.9 371.9 127.9 252.6zM320 320C355.3 320 384 291.3 384 256C384 220.7 355.3 192 320 192C284.7 192 256 220.7 256 256C256 291.3 284.7 320 320 320z" />
    </svg>
  </button>

  <a href="${pageContext.request.contextPath}/orders/myFavorite.or" class="icon_btn" title="찜">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
      <path fill="#202020"
        d="M305 151.1L320 171.8L335 151.1C360 116.5 400.2 96 442.9 96C516.4 96 576 155.6 576 229.1L576 231.7C576 343.9 436.1 474.2 363.1 529.9C350.7 539.3 335.5 544 320 544C304.5 544 289.2 539.4 276.9 529.9C203.9 474.2 64 343.9 64 231.7L64 229.1C64 155.6 123.6 96 197.1 96C239.8 96 280 116.5 305 151.1z" />
    </svg>
  </a>

  <a href="${pageContext.request.contextPath}/cartList/view.cl" class="icon_btn" title="장바구니">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640">
      <path fill="#202020"
        d="M320 64C326.6 64 332.9 66.7 337.4 71.5L481.4 223.5L481.9 224L560 224C577.7 224 592 238.3 592 256C592 270.5 582.4 282.7 569.2 286.7L523.1 493.9C516.6 523.2 490.6 544 460.6 544L179.3 544C149.3 544 123.3 523.2 116.8 493.9L70.8 286.7C57.6 282.8 48 270.5 48 256C48 238.3 62.3 224 80 224L158.1 224L158.6 223.5L302.6 71.5C307.1 66.7 313.4 64 320 64zM320 122.9L224.2 224L415.8 224L320 122.9zM240 328C240 314.7 229.3 304 216 304C202.7 304 192 314.7 192 328L192 440C192 453.3 202.7 464 216 464C229.3 464 240 453.3 240 440L240 328zM320 304C306.7 304 296 314.7 296 328L296 440C296 453.3 306.7 464 320 464C333.3 464 344 453.3 344 440L344 328C344 314.7 333.3 304 320 304zM448 328C448 314.7 437.3 304 424 304C410.7 304 400 314.7 400 328L400 440C400 453.3 410.7 464 424 464C437.3 464 448 453.3 448 440L448 328z" />
    </svg>
  </a>
</div>

<!-- 위치 모달 -->
<div class="location_modal" role="dialog" aria-modal="true">
  <div class="location_modal_overlay" data-close="true"></div>
  <div class="location_modal_body">
    <h2 class="my_location">나의 위치</h2>
    <input type="text" class="location_info_input" placeholder="도로명 주소 입력">
    <button class="location_modal_close" type="button">×</button>
    <button class="save_location">저장</button>
  </div>
</div>

<!-- Tree Grade Modal -->
<div id="treeGradeModal" class="grade-modal hidden" role="dialog" aria-modal="true">
  <div class="grade-modal__overlay" data-close="true"></div>
  <div class="grade-modal__panel">
    <button class="grade-modal__close" type="button" aria-label="닫기">×</button>
    <h2 class="grade-modal__title">나의 등급</h2>
    <p>등급 내용은 여기 표시됩니다.</p>
    <button class="grade-modal__close-btn" type="button">닫기</button>
  </div>
</div>

<!-- 모달 제어 JS -->
<script defer src="${pageContext.request.contextPath}/assets/js/common/gradeModal.js"></script>
<script defer src="${pageContext.request.contextPath}/assets/js/common/locationModal.js"></script>
