window.addEventListener('DOMContentLoaded', () => {
  // 헤더 불러오기
  fetch('./../../header.html')
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

//전체동의
NodeList.prototype.map = Array.prototype.map;
NodeList.prototype.filter = Array.prototype.filter;

let checkboxs = document.querySelectorAll("input[type='checkbox']");
let essenAgrees = document.querySelectorAll("input[name='essenAgree']");
let agreeAll = document.querySelector('input[name="joinAgreeAll"]');
let agreeAdvs = document.querySelectorAll('input.join_agree_adv');
let agreeAdv = document.querySelector('input#join_agree_bottom_adv');

console.log(agreeAll);
console.log(essenAgrees);
console.log(checkboxs);
console.log(agreeAdvs);
console.log(agreeAdv);

//전체동의 체크박스를 클릭할 때 마다 실행되는 이벤트 리스너
agreeAll.addEventListener('click', () => {
  checkboxs.forEach((checkbox) => {
    console.log(checkbox);
    checkbox.checked = agreeAll.checked;
  });
});

//광고성정보 수신동의 체크박스를 클릭할 때 마다 실행되는 이벤트 리스너
agreeAdv.addEventListener('click', () => {
  agreeAdvs.forEach((adv) => {
    console.log(adv);
    adv.checked = agreeAdv.checked;
  });
});

//약관동의 체크박스를 클릭할 때마다 실행되는 이벤트 리스터
checkboxs.forEach((checkbox) => {
  checkbox.addEventListener('click', () =>{
    agreeAll.checked = checkboxs.map((checkbox) =>
      checkbox.checked).filter((checked) => checked).length === checkboxs.length;
  });
});

function goNextPage() {
  let essenCount = 0;
  // 필수동의 여부 확인
  essenAgrees.forEach((essenAgree)=>{
    if(essenAgree.checked){
      essenCount++;
    }
  });
  console.log(essenCount);
  if(essenCount === essenAgrees.length ){
    //모든 체크박스 체크 시 다음 페이지로 이동
    location.href = "sellerInfoInput.html";
    return;
  }
  alert("필수 약관에 동의해야 다음단계로 이동할 수 있습니다.");
}