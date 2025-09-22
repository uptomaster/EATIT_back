<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 대시보드</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/admin/dashboard.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="admin_innerwrapper">
        <!-- 사이드바 -->
        <aside class="sidebar">
            <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
                <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
                     alt="admin_logo" class="admin_logo">
            </a>
            <ul class="sidebar_ul">
                <li class="sidebar_list active"><a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
                <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
                <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a></li>
                <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
                <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
            </ul>
            <button id="admin_logoutbtn">로그아웃</button>
        </aside>

        <!-- 메인 -->
        <main class="admin_inner">
            <h1>📊 관리자 대시보드</h1>

            <!-- ===== 상단 통계 박스 ===== -->
            <div class="dashboard_topWrapper">
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-users"></i></div>
                    <div class="dashboard_topbox_key">총 회원 수</div>
                    <div class="dashboard_topbox_value">${totalMembers}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-user"></i></div>
                    <div class="dashboard_topbox_key">일반회원</div>
                    <div class="dashboard_topbox_value">${totalGeneralMembers}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-store"></i></div>
                    <div class="dashboard_topbox_key">판매자</div>
                    <div class="dashboard_topbox_value">${totalSellerMembers}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-user-plus"></i></div>
                    <div class="dashboard_topbox_key">오늘 신규가입</div>
                    <div class="dashboard_topbox_value">${todayMembers}</div>
                </div>
            </div>

            <div class="dashboard_topWrapper">
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-bullhorn"></i></div>
                    <div class="dashboard_topbox_key">공지사항</div>
                    <div class="dashboard_topbox_value">${totalNotices}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-question-circle"></i></div>
                    <div class="dashboard_topbox_key">FAQ</div>
                    <div class="dashboard_topbox_value">${totalFaqs}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-envelope"></i></div>
                    <div class="dashboard_topbox_key">전체 문의</div>
                    <div class="dashboard_topbox_value">${totalInquiries}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-hourglass-half"></i></div>
                    <div class="dashboard_topbox_key">미답변 문의</div>
                    <div class="dashboard_topbox_value">${unansweredInquiries}</div>
                </div>
                <div class="dashboard_topbox">
                    <div class="dashboard_topbox_icon"><i class="fas fa-flag"></i></div>
                    <div class="dashboard_topbox_key">신고 건수</div>
                    <div class="dashboard_topbox_value">${totalReports}</div>
                </div>
            </div>

            <!-- 차트 -->
            <div class="dashboard_chartsWrapper">
                <div class="dashboard_chartbox">
                    <h3>📈 월별 회원 가입 수 (이번달 vs 오늘)</h3>
                    <canvas id="monthlyMembersChart"></canvas>
                </div>

                <div class="dashboard_chartbox">
                    <h3>🍩 완제품/재료별 주문현황</h3>
                    <canvas id="categorySalesChart"></canvas>
                </div>
            </div>

            <!-- ===== 하단 최근 글/신고 ===== -->
            <div class="dashboard_bottomWrapper">
                <div class="dashboard_bottombox">
                    <h3 class="dashboard_bottombox_title">최근 문의글</h3>
                    <ul>
                        <c:forEach var="inq" items="${recentInquiries}">
                            <li>${inq.postTitle} (${inq.inquiryStatus})</li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="dashboard_bottombox">
                    <h3 class="dashboard_bottombox_title">최근 신고</h3>
                    <ul>
                        <c:forEach var="rep" items="${recentReports}">
                            <li>[${rep.postReportReason}] ${rep.postReportDate}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </main>
    </div>

    <!-- ===== Chart.js 스크립트 ===== -->
    <script>
    document.addEventListener("DOMContentLoaded", () => {
        // 원래 월별 데이터
        const monthlyLabels = [
            <c:forEach var="row" items="${monthlyMembers}" varStatus="status">
                "${row.MONTH}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>,
            "오늘" // 마지막에 오늘 라벨 추가
        ];
        const monthlyTotals = [
            <c:forEach var="row" items="${monthlyMembers}" varStatus="status">
                ${row.COUNT}<c:if test="${!status.last}">,</c:if>
            </c:forEach>,
            ${todayMembers} // 오늘 가입자 수 추가
        ];

        new Chart(document.getElementById('monthlyMembersChart'), {
            type: 'bar',
            data: {
                labels: monthlyLabels,
                datasets: [{
                    label: '신규 가입자 수',
                    data: monthlyTotals,
                    backgroundColor: monthlyLabels.map((label, idx) =>
                        idx === monthlyLabels.length - 1 ? '#36A2EB' : '#505FA9'
                    )
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: { display: false },
                    tooltip: { enabled: true }
                },
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });

        // 카테고리별 매출 (Doughnut)
        const categoryLabels = [
            <c:forEach var="row" items="${categorySales}" varStatus="status">
                "${row.CATEGORY}"<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        const categoryData = [
            <c:forEach var="row" items="${categorySales}" varStatus="status">
                ${row.SALES}<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        new Chart(document.getElementById('categorySalesChart'), {
            type: 'doughnut',
            data: { 
                labels: categoryLabels, 
                datasets: [{ 
                    data: categoryData, 
                    backgroundColor: ['#FF6384','#36A2EB','#FFCE56','#4BC0C0'] 
                }] 
            },
            options: { 
                responsive: true, 
                maintainAspectRatio: false, 
                plugins: { legend: { position: 'bottom' } } 
            }
        });
    });
    </script>
</body>
</html>
