// ===== 체크박스 제어 =====
const checkboxs   = Array.from(document.querySelectorAll("input[type='checkbox']"));
const essenAgrees = Array.from(document.querySelectorAll("input[name='essenAgree']"));
const agreeAll    = document.querySelector('input[name="joinAgreeAll"]');
const agreeAdvs   = Array.from(document.querySelectorAll('input.join_agree_adv')); // 선택(광고) 채널 개별 체크들(있을 때만)
const agreeAdv    = document.querySelector('#join_agree_bottom_adv');               // 마스터 광고동의(있을 때만)

// 전체 동의 → 나머지 동기화
agreeAll?.addEventListener('change', () => {
  checkboxs.forEach(cb => { if (cb !== agreeAll) cb.checked = agreeAll.checked; });
});

// 광고성 정보 수신(있을 때만)
if (agreeAdv) {
  agreeAdv.addEventListener('change', () => {
    agreeAdvs.forEach(adv => adv.checked = agreeAdv.checked);
  });
}

// 개별 변화 → 전체 동의 상태 갱신
checkboxs.forEach(cb => {
  cb.addEventListener('change', () => {
    const targets = checkboxs.filter(x => x !== agreeAll);
    agreeAll.checked = targets.length > 0 && targets.every(x => x.checked);
  });
});

// 다음 단계(필수 확인 후 제출)
function goNextPage() {
  const form = document.querySelector('form.join_agree');
  const allEssentialChecked = essenAgrees.every(cb => cb.checked);

  if (!allEssentialChecked) {
    alert("필수 약관에 동의해야 다음단계로 이동할 수 있습니다.");
    return;
  }
  // 폼 action은 JSP에서 이미 지정되어 있다고 가정
  form?.submit();
}

/* ===== 약관 '전문보기' 모달 ===== */
document.addEventListener("DOMContentLoaded", () => {
  const modal   = document.getElementById("termsModal");
  if (!modal) return;

  const bodyEl  = modal.querySelector(".modal__body");
  const titleEl = modal.querySelector(".modal__title");
  const panelEl = modal.querySelector(".modal__panel");

  // '전문보기' 버튼 클릭 → template 주입 후 모달 열기
  document.addEventListener("click", (e) => {
    const btn = e.target.closest(".term-view[data-open-modal]");
    if (!btn || !bodyEl || !titleEl) return;

    const key   = btn.dataset.openModal;   // service | financial | marketing | personal | ...
    const title = btn.dataset.title || "약관";
    const tpl   = document.getElementById(`tmpl-${key}`);

    if (!tpl || !(tpl instanceof HTMLTemplateElement)) {
      console.error(`약관 템플릿을 찾을 수 없습니다: #tmpl-${key}`);
      alert(`약관 템플릿이 없습니다: ${key}`);
      return;
    }

    // 제목 세팅
    titleEl.textContent = title;

    // template은 content를 clone해서 넣어야 함
    bodyEl.replaceChildren(tpl.content.cloneNode(true));

    openModal(modal, panelEl);
  });

  // 닫기: X 버튼 / 푸터 버튼 / 배경 클릭
  modal.addEventListener("click", (e) => {
    if (e.target.matches("[data-close]") || e.target.classList.contains("modal__backdrop")) {
      closeModal(modal, bodyEl);
    }
  });

  // ESC로 닫기
  document.addEventListener("keydown", (e) => {
    if (e.key === "Escape" && !modal.hidden) {
      closeModal(modal, bodyEl);
    }
  });

  function openModal(m, panel) {
    m.hidden = false;
    document.documentElement.style.overflow = "hidden";
    panel?.focus();
  }

  function closeModal(m, body) {
    m.hidden = true;
    document.documentElement.style.overflow = "";
    // 주입된 내용 비우기(메모리/포커스 관리)
    body?.replaceChildren();
  }
});
