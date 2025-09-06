// NodeList → Array로 안전하게
const checkboxs  = Array.from(document.querySelectorAll("input[type='checkbox']"));
const essenAgrees = Array.from(document.querySelectorAll("input[name='essenAgree']"));
const agreeAll   = document.querySelector('input[name="joinAgreeAll"]');
const agreeAdvs  = Array.from(document.querySelectorAll('input.join_agree_adv'));
const agreeAdv   = document.querySelector('input#join_agree_bottom_adv');

// 전체 동의
agreeAll?.addEventListener('change', () => {
  checkboxs.forEach(cb => { if (cb !== agreeAll) cb.checked = agreeAll.checked; });
});

// 광고성 동의 (있을 때만)
if (agreeAdv) {
  agreeAdv.addEventListener('change', () => {
    agreeAdvs.forEach(adv => adv.checked = agreeAdv.checked);
  });
}

// 개별 체크 변화 → 전체 동의 갱신
checkboxs.forEach(cb => {
  cb.addEventListener('change', () => {
    const targets = checkboxs.filter(x => x !== agreeAll);
    agreeAll.checked = targets.every(x => x.checked);
  });
});

function goNextPage() {
  const form = document.querySelector('form.join_agree');
  const allEssentialChecked = essenAgrees.every(cb => cb.checked);

  if (!allEssentialChecked) {
    alert("필수 약관에 동의해야 다음단계로 이동할 수 있습니다.");
    return;
  }
  form?.submit(); // 폼 설정에 맞춰 이동(POST)
}

/* ===== 모달 ===== */
document.addEventListener("DOMContentLoaded", () => {
  const modal   = document.getElementById("termsModal");
  const bodyEl  = modal?.querySelector(".modal__body");
  const titleEl = modal?.querySelector(".modal__title");

  document.addEventListener("click", (e) => {
    const btn = e.target.closest(".term-view[data-open-modal]");
    if (!btn) return;

    const key   = btn.dataset.openModal;     // service | financial | marketing | personal
    const title = btn.dataset.title || "약관";
    const tpl   = document.getElementById(`tmpl-${key}`);

    if (!modal || !bodyEl || !tpl) return;

    titleEl.textContent = title;
    bodyEl.innerHTML = tpl.innerHTML;
    openModal(modal);
  });

  modal?.addEventListener("click", (e) => {
    if (e.target.matches("[data-close]")) closeModal(modal);
  });

  document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && !modal.hidden) closeModal(modal);
  });

  function openModal(m) {
    m.hidden = false;
    document.documentElement.style.overflow = "hidden";
    m.querySelector(".modal__panel")?.focus();
  }
  function closeModal(m) {
    m.hidden = true;
    document.documentElement.style.overflow = "";
  }
});