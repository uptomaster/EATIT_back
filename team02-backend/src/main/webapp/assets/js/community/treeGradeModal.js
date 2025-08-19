document.addEventListener('DOMContentLoaded', () => {
  // 'comment_profile' 또는 'author_profile' 클래스 가진 모든 img 선택
  const treeImages = document.querySelectorAll('.comment_profile, .author_profile');
  const modal = document.getElementById('treeInfoModal');
  const modalContent = document.getElementById('treeInfoContent');
  const closeBtn = document.getElementById('closeTreeInfoModal');

  const treeData = {
    '씨앗': {
      img: './../../assets/img/씨앗.png',
      name: '씨앗 등급',
      description: '새로운 시작을 의미하며, 가능성과 잠재력을 상징합니다.'
    },
    '새싹': {
      img: './../../assets/img/새싹.png',
      name: '새싹 등급',
      description: '성장의 초기 단계로, 꾸준한 노력을 통해 앞으로 나아가는 단계입니다.'
    },
    '잎새': {
      img: './../../assets/img/잎새.png',
      name: '잎새 등급',
      description: '자연을 상징하며 생명의 힘을 나타냅니다. 환경 보호에 기여한 분들에게 주어집니다.'
    },
    '가지': {
      img: './../../assets/img/가지.png',
      name: '가지 등급',
      description: '성장과 확장의 상징으로, 공동체에 활력을 더하는 역할을 합니다.'
    },
    '나무': {
      img: './../../assets/img/나무.png',
      name: '나무 등급',
      description: '튼튼하고 성실한 나무처럼 꾸준히 성장하는 등급입니다.'
    },
    '관리자': {
      img: './../../assets/img/관리자.png',
      name: '관리자 등급',
      description: '커뮤니티를 관리하며 전체 운영을 책임지는 특별한 등급입니다.'
    }
  };

  // 커서 포인터 표시
  treeImages.forEach(img => {
    img.style.cursor = 'pointer';

    img.addEventListener('click', () => {
      const alt = img.alt.trim();
      const data = treeData[alt];
      if (data) {
        modalContent.innerHTML = `
          <img src="${data.img}" alt="${data.name}" />
          <h3>${data.name}</h3>
          <p>${data.description}</p>
        `;
        modal.classList.add('show');
        modal.setAttribute('aria-hidden', 'false');
      }
    });
  });

  closeBtn.addEventListener('click', () => {
    modal.classList.remove('show');
    modal.setAttribute('aria-hidden', 'true');
  });

  modal.addEventListener('click', (e) => {
    if (e.target === modal) {
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
    }
  });
});
