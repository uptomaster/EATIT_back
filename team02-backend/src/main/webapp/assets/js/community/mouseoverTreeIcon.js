document.addEventListener('DOMContentLoaded', () => {
  const treeDescriptions = {
    '씨앗': '씨앗 등급 : 새로운 시작을 의미하며, 가능성과 잠재력을 상징합니다.',
    '새싹': '새싹 등급 : 성장의 초기 단계로, 꾸준한 노력을 통해 앞으로 나아가는 단계입니다.',
    '잎새': '잎새 등급 : 자연을 상징하며 생명의 힘을 나타냅니다. 환경 보호에 기여한 분들에게 주어집니다.',
    '가지': '가지 등급 : 성장과 확장의 상징으로, 공동체에 활력을 더하는 역할을 합니다.',
    '나무': '나무 등급 : 튼튼하고 성실한 나무처럼 꾸준히 성장하는 등급입니다.',
    '관리자': '관리자 등급 : 커뮤니티를 관리하며 전체 운영을 책임지는 특별한 등급입니다.'
  };

  const container = document.getElementById('postListBody');
  let tooltip;

  container.addEventListener('mouseover', (event) => {
    const img = event.target.closest('.tree_icon');
    if (!img) return;

    const desc = treeDescriptions[img.alt.trim()];
    if (!desc) return;

    // 툴팁 생성
    tooltip = document.createElement('div');
    tooltip.className = 'tree-tooltip show';
    tooltip.textContent = desc;
    document.body.appendChild(tooltip);

    const rect = img.getBoundingClientRect();
    tooltip.style.left = rect.right + 8 + 'px';
    tooltip.style.top = rect.top + window.scrollY + 'px';
  });

  container.addEventListener('mouseout', (event) => {
    const related = event.relatedTarget;
    const img = event.target.closest('.tree_icon');
    if (!img) return;

    // 마우스가 툴팁이나 같은 이미지로 이동하면 닫지 않음
    if (related && (related === tooltip || tooltip?.contains(related))) return;

    if (tooltip) {
      tooltip.remove();
      tooltip = null;
    }
  });
});
