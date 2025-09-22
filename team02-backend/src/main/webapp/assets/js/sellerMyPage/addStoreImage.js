// assets/js/sellerMyPage/addStoreImage.js
// [주의] 기존 스크립트는 삭제하지 말고 필요 없는 라인만 주석 처리할 것.

/* ===================== 공통 요소 참조 ===================== */
const $ = (sel)=>document.querySelector(sel);
const dropzone = $('#dropzone');
const fileInput = $('#storeImageInput');
const previewImg = $('#previewImage');
const previewPlaceholder = $('#previewPlaceholder');
const progressBar = $('#progressBar');
const progressText = $('#progressText');
const btnSelect = $('#btnSelect');
const btnClear = $('#btnClear');
const btnDelete = $('#btnDelete');
const frm = $('#storeImageForm');

let selectedFile = null;

/* ===================== 유틸: 미리보기 표시 ===================== */
function showPreview(file){
  if(!file){ return; }
  const url = URL.createObjectURL(file);
  previewImg.src = url;
  previewImg.style.display = 'block';
  previewPlaceholder.style.display = 'none';
}

/* ===================== 유틸: 진행바 가짜 애니 ===================== */
let fakeTimer = null;
function startFakeProgress(){
  clearInterval(fakeTimer);
  let p = 0;
  fakeTimer = setInterval(()=>{
    p = Math.min(95, p + Math.random()*8);
    progressBar.style.width = p + '%';
    progressText.textContent = Math.floor(p) + '%';
  }, 180);
}
function finishProgress(){
  clearInterval(fakeTimer);
  progressBar.style.width = '100%';
  progressText.textContent = '100%';
  setTimeout(()=>{
    progressBar.style.width = '0%';
    progressText.textContent = '0%';
  }, 600);
}

/* ===================== 파일 선택 트리거 ===================== */
btnSelect?.addEventListener('click', ()=> fileInput.click());

/* ===================== 파일 변경 시 ===================== */
fileInput?.addEventListener('change', (e)=>{
  const file = e.target.files?.[0];
  if(!file){ return; }
  // 간단 검증 (MIME/사이즈)
  if(!/^image\/(jpeg|png)$/.test(file.type)){
    alert('JPG 또는 PNG 파일만 업로드 가능합니다.');
    e.target.value = '';
    return;
  }
  if(file.size > 10 * 1024 * 1024){
    alert('파일 용량은 10MB 이하로 업로드 해주세요.');
    e.target.value = '';
    return;
  }
  selectedFile = file;
  showPreview(file);
  startFakeProgress();
});

/* ===================== 드래그앤드롭 ===================== */
['dragenter','dragover'].forEach(ev=>{
  dropzone?.addEventListener(ev, (e)=>{ e.preventDefault(); dropzone.classList.add('is-drag'); });
});
['dragleave','drop'].forEach(ev=>{
  dropzone?.addEventListener(ev, (e)=>{ e.preventDefault(); dropzone.classList.remove('is-drag'); });
});
dropzone?.addEventListener('drop', (e)=>{
  const file = e.dataTransfer?.files?.[0];
  if(!file){ return; }
  // input으로 동기화
  const dt = new DataTransfer();
  dt.items.add(file);
  fileInput.files = dt.files;
  fileInput.dispatchEvent(new Event('change'));
});

/* ===================== 초기화(선택 취소) ===================== */
btnClear?.addEventListener('click', ()=>{
  selectedFile = null;
  fileInput.value = '';
  previewImg.style.display = 'none';
  previewImg.src = '';
  previewPlaceholder.style.display = 'block';
  progressBar.style.width = '0%';
  progressText.textContent = '0%';
});

/* ===================== 서버에서 삭제 ===================== */
/* 주석: 실제 삭제 서블릿은 .se 경로로 구현해야 함 (예: /sellerMyPage/deleteStoreImage.se)
   서버에서 삭제 성공 시 현재 미리보기도 비움 */
btnDelete?.addEventListener('click', async ()=>{
  if(!confirm('서버에 저장된 대표 이미지를 정말 삭제하시겠습니까?')) return;
  try{
    const res = await fetch(`${window.contextPath||''}/sellerMyPage/deleteStoreImage.se`, {
      method: 'POST'
    });
    const data = await res.json();
    if(data?.result === 'success'){
      alert('서버 이미지가 삭제되었습니다.');
      btnClear?.click();
    }else{
      alert('삭제에 실패했습니다. 잠시 후 다시 시도해주세요.');
    }
  }catch(e){
    console.error(e);
    alert('네트워크 오류로 삭제에 실패했습니다.');
  }
});

/* ===================== 저장(폼 제출) ===================== */
/* MVC2: JSP → (Form Submit) → SellerStoreImageOkController → DAO/Mapper → DB → 리다이렉트 */
frm?.addEventListener('submit',(e)=>{
  e.preventDefault();

  // 파일이 없으면 서버에 “유지” 로직을 태우거나, 업로드 생략
  if(!selectedFile){
    if(!confirm('새로 선택한 파일이 없습니다. 현재 상태로 저장할까요?')) return;
  }

  const fd = new FormData(frm);
  if(selectedFile){ fd.set('storeImage', selectedFile); } // name="storeImage" 유지

  startFakeProgress();

  fetch(frm.action, {
    method: 'POST',
    body: fd
  })
  .then(res=>res.json())
  .then(json=>{
    // 컨트롤러에서 JSON으로 응답하도록 구성(성공/실패)
    // 예) response.setContentType("application/json; charset=UTF-8");
    //     writer.write("{\"result\":\"success\"}");
    if(json?.result === 'success'){
      finishProgress();
      alert('대표 이미지가 저장되었습니다.');
      // 상세 페이지(또는 리스트)로 이동 — 반드시 .se 경로
      window.location.href = `${window.contextPath||''}/sellerMyPage/storeInfo.se`;
    }else{
      alert('저장에 실패했습니다. 입력값을 확인해 주세요.');
    }
  })
  .catch(err=>{
    console.error(err);
    alert('서버 통신 중 오류가 발생했습니다.');
  });
});
