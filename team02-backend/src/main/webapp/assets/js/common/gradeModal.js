(function() {
  const $ = (sel, el = document) => el.querySelector(sel);

  const base = document.body.dataset.contextPath || "";
  const treeBtn = document.getElementById('treeGradeBtn');
  const modal = $("#treeGradeModal");
  const overlay = $(".grade-modal__overlay", modal);
  const btnClose1 = $(".grade-modal__close", modal);
  const btnClose2 = $(".grade-modal__close-btn", modal);
  const memberNumber = document.body.dataset.member;
  const isLoggedIn = memberNumber && memberNumber.trim() !== "";
  const elGradeName = $("#gradeName", modal);
  const elTotal = $("#totalPayment", modal);
  const elNext = $("#nextGradeText", modal);
  const elBar = $("#gradeProgress", modal);
  const elIcon = $("#gradeIcon", modal);

  const GRADES = [
    { key: "SEED", name: "씨앗", min: 0, max: 100000, icon: "/assets/img/씨앗.png" },
    { key: "SPROUT", name: "새싹", min: 100001, max: 300000, icon: "/assets/img/새싹.png" },
    { key: "LEAF", name: "잎새", min: 300001, max: 700000, icon: "/assets/img/잎새.png" },
    { key: "BRANCH", name: "가지", min: 700001, max: 1500000, icon: "/assets/img/가지.png" },
    { key: "TREE", name: "나무", min: 1500001, max: Infinity, icon: "/assets/img/나무.png" },
  ];

  function currency(n) {
    return Number(n).toLocaleString('ko-KR') + "원";
  }

  function findGrade(total) {
    return GRADES.find(g => total >= g.min && total <= g.max) || GRADES[0];
  }

  function calcProgress(total) {
    const cur = findGrade(total);
    const idx = GRADES.findIndex(g => g.key === cur.key);
    const next = GRADES[Math.min(idx + 1, GRADES.length - 1)];
    if (cur.key === "TREE") return { pct: 100, nextText: "최고 등급입니다 🌳" };
    const span = next.min - cur.min;
    const done = total - cur.min;
    const pct = Math.max(0, Math.min(100, Math.floor((done / span) * 100)));
    const need = next.min - total;
    return { pct, nextText: `다음 등급(${next.name})까지 ${need.toLocaleString('ko-KR')}원 남았어요.` };
  }

  async function openModal() {
    modal.classList.remove("hidden");
    if (!isLoggedIn) {
      elGradeName.textContent = "로그인이 필요합니다";
      elTotal.textContent = "-";
      elNext.textContent = "우측 상단 로그인 후 다시 확인하세요.";
      elBar.style.width = "0%";
      elIcon.src = base + "/assets/img/씨앗.png";
      return;
    }
    let total = 0;
    try {
      const res = await fetch(`${base}/main/gradeInfo.ma`, { headers: { "Accept": "application/json" } });
      if (res.ok) {
        const data = await res.json();
        total = Number(data.totalPayment) || 0;
      }
    } catch (_) {}
    const g = findGrade(total);
    const p = calcProgress(total);
    elGradeName.textContent = g.name;
    elTotal.textContent = currency(total);
    elNext.textContent = p.nextText;
    elBar.style.width = p.pct + "%";
    elIcon.src = base + (g.icon || "/assets/img/씨앗.png");
  }

  function closeModal() { modal.classList.add("hidden"); }

  if (treeBtn) treeBtn.addEventListener("click", e => { e.preventDefault(); openModal(); });
  [overlay, btnClose1, btnClose2].forEach(el => el && el.addEventListener("click", closeModal));
  document.addEventListener("keydown", e => { if (e.key === "Escape" && !modal.classList.contains("hidden")) closeModal(); });
})();
