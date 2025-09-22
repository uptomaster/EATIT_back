<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/img/favicon.ico"
	type="image/x-icon">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/footer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/join/joinAgreementSeller.css">
<script defer
	src="${pageContext.request.contextPath}/assets/js/header.js"></script>
<script defer
	src="${pageContext.request.contextPath}/assets/js/join/joinAgreementSeller.js"></script>
<title>밥세권</title>
<script>
    let headerPath = '../../header.jsp';
    let footerPath = '../../footer.jsp';
  </script>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<main>
		<div class="container">
			<!-- 회원가입 단계 동그라미 4개 -->
			<ul class="join_step">
				<li>
					<div class="join_step_user_type">회원선택</div>
				</li>
				<li>
					<div class="join_step_agreement">약관동의</div>
				</li>
				<li>
					<div class="join_step_input_info">정보입력</div>
				</li>
				<li>
					<div class="join_step_final">가입완료</div>
				</li>
			</ul>
			<!--//.join_step-->
			<!-- 약관동의 컨테이너 -->
			<div class="seller_join_agree_container">
				<div class="join_agree_title">밥세권 사업자 회원가입</div>
				<!-- 약관동의 체크 칸 -->
				<form action="${pageContext.request.contextPath}/join/sellerJoin.jo" method="post" class="join_agree">
					<div class="join_agree_all">
						<input type="checkbox" name="joinAgreeAll"
							id="join_agree_all_chkbox" class=""> <label
							for="join_agree_all_chkbox">전체 동의</label>
					</div>
					<!-- 서비스 이용 약관 -->
					<div class="join_agree_top_term_container">
						<input type="checkbox" name="essenAgree" class="essential"
							id="join_agree_top_term" required> <label
							for="join_agree_top_term">[필수] 이용 약관 동의</label>
						<button type="button" class="term-view" data-open-modal="service"
							data-title="서비스 이용 약관">전문보기</button>
					</div>
					<!-- 전자금융거래 약관 -->
					<div class="join_agree_top_financial_container">
						<input type="checkbox" name="essenAgree" class="essential"
							id="join_agree_top_financial" required> <label
							for="join_agree_top_financial">[필수] 전자금융거래 이용약관 동의</label>
						<button type="button" class="term-view"
							data-open-modal="financial" data-title="전자금융거래 이용약관">전문보기
						</button>
					</div>
					<hr>
					<div>
						<!-- 개인정보 수집 및 이용약관 - 선택 -->
						<div class="join_agree_bottom_service_continer">
							<input type="checkbox" name="agree"
								id="join_agree_bottom_service" required> <label
								for="join_agree_bottom_service">[선택] 서비스/이벤트 제공을 위한 개인정보
								수집 이용동의</label>
							<button type="button" class="term-view"
								data-open-modal="marketing" data-title="개인정보 수집·이용(선택)">전문보기
							</button>
						</div>
						<!-- 개인정보 수집 및 이용약관 - 필수 (id 오타 수정) -->
						<div class="join_agree_bottom_personal">
							<input type="checkbox" name="essenAgree"
								id="join_agree_bottom_personal" required> <label
								for="join_agree_bottom_personal">[필수] 밥세권 회원 연동을 위한 개인정보
								수집 이용 동의</label>
							<button type="button" class="term-view"
								data-open-modal="personal" data-title="개인정보 수집·이용(필수)">전문보기
							</button>
						</div>
					</div>
					<!-- 다음버튼 -->
					<div class="join_agree_next">
						<button type="button" class="join_agree_next_btn"
							onclick="goNextPage()">다음</button>
					</div>
				</form>

			</div>
			<!-- //.seller_join_agree_container -->
		</div>
		<!--//.container-->
	</main>
	<jsp:include page="${pageContext.request.contextPath}/footer.jsp" />
	<!-- 모달 -->
	<div class="modal" id="termsModal" role="dialog" aria-modal="true"
		hidden>
		<div class="modal__backdrop" data-close></div>
		<div class="modal__panel" tabindex="-1"
			aria-labelledby="termsModalTitle">
			<header class="modal__header">
				<h2 id="termsModalTitle" class="modal__title">약관</h2>
				<button type="button" class="modal__close" aria-label="닫기"
					data-close>&times;</button>
			</header>
			<div class="modal__body"></div>
			<footer class="modal__footer">
				<button type="button" class="btn btn--ghost" data-close>닫기</button>
			</footer>
		</div>
	</div>

	<!-- 약관 전문 -->
<template id="tmpl-service">
  <section aria-label="서비스 이용 약관(사업자)">
    <pre style="white-space:pre-wrap; word-break:keep-all; font-family:inherit; font-size:inherit; line-height:inherit; margin:0;">
제1조(목적)
본 약관은 밥세권(이하 “회사”)이 운영하는 플랫폼에서 판매자로 가입하여 상품·서비스를 제공하는 자(이하 “판매자”)의 권리·의무, 서비스 이용조건 및 절차를 정함을 목적으로 합니다.

제2조(정의)
1. “플랫폼”은 회사가 운영하는 웹·앱 등 일체의 온라인 서비스입니다.
2. “판매자 센터”는 상품/주문/정산/정책 관리를 위한 전용 도구입니다.
3. “상품”은 식품·식자재·간편식 등 판매자가 등록하는 물품·서비스를 말합니다.
4. “이용자(구매자)”는 플랫폼을 통해 상품을 구매하는 자를 말합니다.
5. “정책”은 본 약관 외 운영정책·가이드·공지 등을 포함합니다.

제3조(약관의 게시 및 변경)
1. 회사는 본 약관을 플랫폼에 게시합니다.
2. 약관 변경 시 시행 7일 전(중대한 변경은 30일 전)부터 공지합니다.
3. 판매자가 시행일 이후 계속 이용하는 경우 변경 약관에 동의한 것으로 봅니다.

제4조(입점 및 자격)
1. 판매자는 사업자등록증 등 회사가 요구하는 서류(식품업 인허가·통신판매업신고 포함)를 제출해야 합니다.
2. 제출 서류가 허위·위조로 확인되면 승인은 거부되거나 해지될 수 있습니다.
3. 회사는 심사 기준에 따라 입점 승인·보류·거절을 결정할 수 있습니다.

제5조(판매자의 일반 의무)
1. 상품정보(가격, 재고, 원산지, 성분·알레르기, 포장·보관, 유통기한, 주의사항)를 정확·최신으로 유지합니다.
2. 주문·클레임·환불을 성실히 처리하고 지연·품절 등 이슈는 즉시 통지합니다.
3. 관련 법령(전자상거래법, 표시광고법, 식품위생법, 개인정보보호법 등)을 준수합니다.
4. 고객 응대는 영업일 기준 ○시간 내 1차 답변, ○일 내 최종 처리(SLA)를 원칙으로 합니다.

제6조(금지행위)
1. 허위·과장·비방 광고, 키워드 스팸, 타인의 권리 침해, 외부결제 유도, 리뷰 조작, 허위주문 등 부정행위를 금지합니다.
2. 시스템 우회, 비정상 트래픽 유발, 크롤링 등 서비스 장애를 유발하는 행위를 금지합니다.

제7조(상품 품질·안전·표시)
1. 식품 등 표시의무가 있는 경우 법정 표시사항을 충실히 기재하고, 위생·온도관리 기준을 준수합니다.
2. 안전상 문제가 있는 상품은 즉시 판매중지·리콜·환불 등 필요한 조치를 시행합니다.
3. 인증·수상·후원 등의 표시는 객관적 증빙을 보유해야 합니다.

제8조(주문 처리·픽업)
1. 판매자는 주문 승인·준비·출고·픽업 준비를 기한 내 완료해야 합니다(기본 ○시간 내).
2. 픽업 정책(가능지역·시간·요금·지연 기준)을 사전에 고지합니다.
3. 파손·하자 발생 시 판매자 귀책이면 판매자가 비용을 부담합니다.

제9조(취소·교환·반품·환불)
1. 법령 및 운영정책에 따르며, 하자·표시누락·지연 등 판매자 귀책 시 비용은 판매자가 부담합니다.
2. 환불 기준·기한·절차는 서비스 화면 및 정책에 따릅니다(예: 승인 후 영업일 ○~○일 내 환불).

제10조(수수료·정산)
1. 회사는 판매대금에서 수수료·PG수수료·환불/차감·페널티 등을 공제할 수 있습니다.
2. 수수료율·정산주기·보증/예치 등 구체 조건은 “수수료 및 정산 약관” 및 정책에 따릅니다.

제11조(지식재산권)
1. 판매자는 등록 콘텐츠에 대한 적법한 권리를 보유해야 하며, 회사는 서비스 운영·홍보 목적 범위 내에서 비독점적으로 이를 사용할 수 있습니다.
2. 제3자 권리 침해 분쟁이 발생하면 판매자가 책임을 부담합니다.

제12조(개인정보 보호)
1. 판매자는 주문 이행 목적 범위에서만 구매자 정보를 이용·보관하며 목적 달성 즉시 파기합니다.
2. 상세는 “개인정보 수집·이용 동의”·개인정보처리방침 및 법령을 따릅니다.

제13조(이용 제한·계약 해지)
1. 약관·정책·법령 위반, 반복 민원, 품질·위생 문제, 정산 리스크, 부정거래가 있을 경우 회사는 경고·노출제한·판매중지·계약해지를 할 수 있습니다.
2. 해지 이후에도 정산·환불 등 기 발생 의무는 존속합니다.

제14조(손해배상·면책)
1. 귀책 당사자는 상대방 손해를 배상합니다.
2. 천재지변·정전·통신장애 등 불가항력으로 인한 손해에 회사는 책임을 지지 않습니다. 다만 고의·중과실은 예외입니다.
3. 금융기관·PG사의 귀책 손해는 해당 기관이 책임을 부담합니다.

제15조(감사·협조)
정산·분쟁·보안 사건 처리에 필요한 범위에서 회사의 자료 제출·소명 요청에 협조합니다.

제16조(준거법·관할)
대한민국 법을 준거로 하며, 회사 본점 소재지 관할법원을 전속 관할로 합니다.

부칙
본 약관은 2025-09-04부터 시행합니다.
    </pre>
  </section>
</template>

<template id="tmpl-financial">
  <section aria-label="전자금융거래 이용약관">
    <pre style="white-space:pre-wrap; word-break:keep-all; font-family:inherit; font-size:inherit; line-height:inherit; margin:0;">
제1조(목적)
본 약관은 회사가 제공하는 전자지급결제대행(PG), 간편결제, 등록결제(토큰 빌링) 등 전자금융거래 서비스 이용에 관한 권리·의무 및 책임사항을 정합니다.

제2조(정의)
1. “전자금융거래”는 전자적 장치를 통한 금융거래입니다.
2. “전자지급수단”은 카드, 계좌이체, 선불전자지급수단, 간편결제 등을 말합니다.
3. “접근매체”는 ID, 비밀번호, 인증서, 생체·기기정보 등 진정성 확인 수단입니다.
4. “오류”는 이용자 고의·과실 없이 거래가 약정 또는 거래지시에 따라 이행되지 않은 경우입니다.
5. “등록결제(빌링)”는 카드·계좌정보를 토큰화하여 반복 과금하는 방식을 말합니다.

제3조(약관의 게시·변경)
게시·변경 공지는 시행 7일 전(중대한 변경은 30일 전)부터 하며, 계속 이용 시 동의한 것으로 봅니다.

제4조(서비스 내용)
1. 카드/계좌/간편결제 대행 및 정산
2. 등록결제(토큰) 발급·보관·과금
3. 부분취소·재승인·환불 처리(수단별 정책에 따름)
4. 이상거래 탐지 및 인증 고도화

제5조(접근매체 관리)
접근매체 관리책임은 이용자에게 있으며 분실·도난·유출 시 즉시 통지해야 합니다.

제6조(거래지시의 철회)
취소 가능 시점 전에는 철회할 수 있고, 수단·PG사 정책에 따라 제한될 수 있습니다(예: 승인 후 부분취소 가능 범위).

제7조(오류의 정정)
오류 인지 시 지체 없이 정정 요구할 수 있으며, 회사는 조사·조치 후 결과를 통지합니다.

제8조(거래기록 보존)
법령에 따라 거래기록을 생성·보존합니다(원칙적으로 5년 등).

제9조(수수료)
수수료율·부과 기준은 서비스 화면 및 정책에 따릅니다(수단별 상이).

제10조(한도·인증)
한도·추가 인증은 위험도·금액·정책에 따라 달라질 수 있습니다(예: 고액 결제시 추가 본인확인).

제11조(지급보류·차단)
이상거래, 분쟁·환불 과다, 법령 위반 우려 등 합리적 사유가 있는 경우 지급을 보류하거나 거래를 제한할 수 있습니다.

제12조(환불 처리)
환불 사유·금액·기한은 정책·수단별 규정에 따르며, 승인 취소 후 카드사/은행 시스템 처리 지연이 발생할 수 있습니다(통상 영업일 ○~○일).

제13조(면책)
불가항력·통신장애·금융기관/PG사 귀책으로 인한 손해에 회사는 책임을 지지 않습니다. 다만 고의·중과실은 예외입니다.

제14조(개인정보 보호)
전자금융거래와 관련한 개인정보 처리에는 회사의 개인정보처리방침이 적용됩니다.

제15조(분쟁처리·조정)
민원은 고객센터로 접수하며 신속히 처리하고, 필요시 금융감독원 분쟁조정 등 절차를 이용할 수 있습니다.

제16조(준거법·관할)
대한민국 법을 준거로 하며, 관할법원은 회사 본점 소재지 관할법원으로 합니다.

고객센터
- 문의: http://localhost:8888/community/inquiryListOk.co
- 전화: 010-7126-1745
- 운영: 평일 09:00~18:00(공휴일 제외)

부칙
본 약관은 2025-09-04부터 시행합니다.
    </pre>
  </section>
</template>

<template id="tmpl-marketing">
  <section aria-label="[선택] 개인정보 수집·이용 동의(마케팅)">
    <pre style="white-space:pre-wrap; word-break:keep-all; font-family:inherit; font-size:inherit; line-height:inherit; margin:0;">
본 동의는 맞춤형 혜택·이벤트·프로모션·신규 서비스 안내 등 마케팅 정보 제공을 위해 필요한 사항입니다. 동의하지 않아도 기본 서비스 이용은 가능합니다.

1. 수집·이용 항목
- 연락처: 휴대전화번호, 이메일, 앱 푸시 토큰
- 이용 정보: 조회/클릭/구매/장바구니/검색 이력, 접속 로그, 쿠키·광고식별자(기기 정보 포함)
- 기본 정보: 아이디, 닉네임(선택 기재 시)

2. 이용 목적
- 쿠폰/할인/적립/이벤트 등 프로모션 정보 제공 및 참여 안내
- 관심·선호 기반 맞춤 추천(매장/상품/콘텐츠)
- 신규 기능·정책 변경·고객 혜택 안내
- 캠페인 성과 분석 및 품질 개선

3. 보유·이용 기간
- 동의 철회 시까지 보유·이용(법령상 보관 의무는 예외)

4. 위탁 및 국외이전(해당 시)
- 문자 발송, 데이터 분석/클라우드(국내·외) 사업자에 위탁될 수 있음
- 목적: 메시지 발송, 인프라 운영, 통계·분석
- 기간: 위탁계약 종료 또는 동의 철회 시까지
※ 실제 수탁사 명칭은 서비스 내 공지 또는 개인정보처리방침 별표로 안내

5. 동의 거부 권리 및 불이익
- 동의를 거부할 권리가 있으며, 거부 시 마케팅 정보 제공이 제한될 수 있음

6. 수신 설정·철회 방법
- 앱/웹 설정 > 마케팅 수신 설정에서 채널별(SMS) 변경
- 각 메시지 하단 수신거부 또는 고객센터 통해 즉시 철회
- 21:00~08:00 야간에는 사전 동의 없는 영리 목적 광고성 정보 미발송

문의: http://localhost:8888/community/inquiryListOk.co
    </pre>
  </section>
</template>

<template id="tmpl-personal">
  <section aria-label="[필수] 개인정보 수집·이용 동의(회원 연동)">
    <pre style="white-space:pre-wrap; word-break:keep-all; font-family:inherit; font-size:inherit; line-height:inherit; margin:0;">
1. 수집·이용 항목
- 회원 식별: 아이디, 비밀번호(일방향 암호화), 이름, 생년월일, 휴대폰 번호
- 판매자 정보: 상호, 대표자명, 사업자등록번호, 사업장 주소, 통신판매업신고번호(해당 시)
- 정산 정보: 은행명, 예금주, 계좌번호(계좌 소유 확인 목적 포함)
- 서비스 이용: 접속 로그, 쿠키, 기기정보, 주문/정산/클레임 이력, 본인인증 결과

2. 이용 목적
- 판매자 회원 가입/본인확인/권한 관리
- 주문 접수·이행, 고객 문의 대응, 정산/세무 처리
- 부정 이용 방지, 보안, 서비스 품질 개선
- 법령·정책 위반 행위 방지 및 분쟁 대응

3. 보유·이용 기간
- 회원 탈퇴 시까지 보유·이용
- 내부 정책에 따른 부정이용 방지 목적 보관: 탈퇴 후 최대 1년
- 법령상 보존 의무가 있는 정보는 해당 기간 보관(예: 거래기록 5년 등)

4. 위탁 및 제3자 제공
- 위탁: 본인인증, 결제/정산, 문자·이메일·푸시 발송, 클라우드·보안·백업 등
- 제3자 제공: 주문 이행 목적 범위에서 구매자 정보가 판매자에게 제공될 수 있음(주문번호, 수취인, 연락처, 수령지, 주문내역 등 최소한의 정보)
- 각 위탁사/제공처, 이전국가(해당 시), 보유기간 등은 서비스 내 공지 또는 개인정보처리방침 별표로 안내

5. 동의 거부 권리
- 필수 항목 동의가 없을 경우 판매자 회원가입 및 서비스 제공이 제한될 수 있음

6. 파기 방법
- 전자 파일은 복구 불가능한 방식으로 삭제, 종이 문서는 분쇄·소각

문의: http://localhost:8888/community/inquiryListOk.co
    </pre>
  </section>
</template>

</body>
</html>