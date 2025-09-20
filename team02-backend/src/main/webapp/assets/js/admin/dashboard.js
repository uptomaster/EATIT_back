// 대시보드 탭 전환 기능
document.addEventListener("DOMContentLoaded", () => {
  const tabs = document.querySelectorAll(".tab_btn");
  const contents = document.querySelectorAll(".dashboard_tab_content");

  tabs.forEach(tab => {
    tab.addEventListener("click", () => {
      tabs.forEach(t => t.classList.remove("active"));
      contents.forEach(c => c.classList.remove("active"));

      tab.classList.add("active");
      const targetId = tab.dataset.target;
      document.getElementById(targetId).classList.add("active");
    });
  });

  if (tabs.length > 0) {
    tabs[0].classList.add("active");
    contents[0].classList.add("active");
  }

  // ===== Chart.js =====
  if (typeof Chart !== "undefined") {
    // 월별 신규 가입자 (막대차트)
    const ctx1 = document.getElementById("monthlyMembersChart");
    if (ctx1) {
      new Chart(ctx1, {
        type: "bar",
        data: {
          labels: monthlyLabels,
          datasets: [{
            label: "신규 가입자 수",
            data: monthlyValues,
            backgroundColor: "#505FA9"
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: { display: false }
          }
        }
      });
    }

    // 카테고리별 매출 (도넛차트)
    const ctx2 = document.getElementById("categorySalesChart");
    if (ctx2) {
      new Chart(ctx2, {
        type: "doughnut",
        data: {
          labels: salesLabels,
          datasets: [{
            data: salesValues,
            backgroundColor: ["#505FA9", "#F39C12", "#27AE60", "#E74C3C"]
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "bottom",
              labels: { boxWidth: 15, padding: 15 }
            }
          }
        }
      });
    }
  }
});
