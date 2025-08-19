document.addEventListener("DOMContentLoaded", () => {
  // ---------------------------
  // 1. 글 유형에 따른 태그 옵션
  // ---------------------------
  const postTypeRadios = document.querySelectorAll('input[name="postType"]');
  const tagSelect = document.getElementById("tagSelect");

  const tagOptions = {
    inquiry: [
      { value: "[문의]", text: "[문의]" },
      { value: "[기타]", text: "[기타]" }
    ],
    report: [
      { value: "[위생]", text: "[위생]" },
      { value: "[서비스 불만족]", text: "[서비스 불만족]" },
      { value: "[가격/환불 문제]", text: "[가격/환불 문제]" },
      { value: "[제품 품질]", text: "[제품 품질]" },
      { value: "[안전 문제]", text: "[안전 문제]" },
      { value: "[기타]", text: "[기타]" }
    ]
  };

  function updateTagOptions(type) {
    tagSelect.innerHTML = '<option value="" disabled selected>태그를 선택하세요</option>';
    tagOptions[type].forEach(opt => {
      const optionEl = document.createElement("option");
      optionEl.value = opt.value;
      optionEl.textContent = opt.text;
      tagSelect.appendChild(optionEl);
    });
  }

  // 초기값: 문의
  updateTagOptions("inquiry");

  // 글 유형 변경 이벤트
  postTypeRadios.forEach(radio => {
    radio.addEventListener("change", (e) => {
      updateTagOptions(e.target.value);
    });
  });

  // -----------------------------------
  // 2. 작성 취소 / 작성 완료 기능
  // -----------------------------------
  const form = document.querySelector(".write_form");
  const cancelBtn = document.querySelector(".cancel_btn");
  const submitBtn = document.querySelector(".submit_btn");

  // 작성 취소
  cancelBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const confirmCancel = confirm("작성 중인 글이 취소됩니다. 정말 취소하시겠습니까?");
    if (confirmCancel) {
      window.location.href = "./customerServiceList.html"; // 레시피 게시판 목록으로 이동
    }
  });

  // 작성 완료
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const confirmSubmit = confirm("글을 작성하시겠습니까?");
    if (confirmSubmit) {
      // 실제 글 작성 처리 후, 글 번호(postId) 등을 받아와야 합니다.
      // 예시로 임의 postId = 12345 지정
      const postId = 12345;
      alert("글이 성공적으로 작성되었습니다.");
      window.location.href = `./viewOwnPost.html?postId=${postId}`; // 작성한 글 조회 페이지로 이동
    }
  });
});
