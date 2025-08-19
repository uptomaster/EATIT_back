window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header_login.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('header').innerHTML = data;
    });

  // 푸터 불러오기
  fetch('./../../footer.html')
    .then(response => response.text())
    .then(data => {
      document.getElementById('footer').innerHTML = data;
    });

});


// 이미지 크기 체크 함수
function checkImageSize(file) {
  return new Promise((resolve) => {
    const img = new Image();
    const reader = new FileReader();

    reader.onload = (e) => {
      img.src = e.target.result;

      img.onload = () => {
        resolve({
          isValid: img.width <= 700 && img.height <= 400,
          width: img.width,
          height: img.height
        });
      };
    };

    reader.readAsDataURL(file);
  });
}

// 파일 선택 시 처리
document.getElementById('food_edit_photo').addEventListener('change', async function (e) {
  const file = e.target.files[0];
  const errorMessage = document.getElementById('error-message');

  if (!file) return;

  // 이미지 크기 확인
  const { isValid, width, height } = await checkImageSize(file);

  if (!isValid) {
    errorMessage.style.display = 'block';
    errorMessage.textContent = `* ${width}×${height}px 크기의 이미지는 너무 큽니다.\n700px × 400px 이하의 이미지만 업로드 가능합니다.`;
    this.value = ''; // 입력값 초기화
    return;
  }

  // 크기가 적절한 경우 미리보기 업데이트
  const reader = new FileReader();
  reader.onload = function (e) {
    document.getElementById('food_edit_image_preview').src = e.target.result;
    errorMessage.style.display = 'none';
  };
  reader.readAsDataURL(file);
});


// 음식 설명 글자 수 표시
const description = document.getElementById('food_edit_explain');
const charCount = document.getElementById('food_edit_char_count');

description.addEventListener('input', function () {
  charCount.textContent = `${description.value.length}/100`;
});

// 삭제버튼
document.addEventListener("DOMContentLoaded", function () {
  const deleteButtons = document.querySelectorAll(".food_delete_buzz");

  deleteButtons[0].addEventListener("click", function () {
    if (confirm("메뉴를 정말 삭제하시겠습니까??") == true) {    //확인
      location.replace('./../sellerMyPage/storeInfo.html');
      document.removefrm.submit();
    } else {   //취소
      return false;
    }
  });
});

// 수정 버튼
document.addEventListener("DOMContentLoaded", function () {
  const saveButtons = document.querySelectorAll(".food_edit_buzz");

  saveButtons[0].addEventListener("click", function () {
    alert("정보가 정상적으로 수정되었습니다.");
  });
});

