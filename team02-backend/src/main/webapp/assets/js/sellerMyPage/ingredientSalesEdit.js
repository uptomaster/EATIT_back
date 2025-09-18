$(document).ready(function () {
    let $fileInput = $('#file');
    let $fileList = $('.file-list');
    let $cnt = $('.cnt');
    let cnt = 0;

    console.log($fileInput);

    // 기존 이미지 삭제 기능
    $(".delete-file-btn").on("click", function () {
        let fileName = $(this).data("filename");
        let confirmDelete = confirm("정말로 이 이미지를 삭제하시겠습니까?");
        
        if (confirmDelete) {
            $(this).closest(".img-box").remove(); // UI에서 삭제
            $("<input>")
                .attr("type", "hidden")
                .attr("name", "deletedFiles")
                .val(fileName)
                .appendTo("#update-form"); // 삭제된 파일 정보 추가
        }
    });

    // 새 파일 추가 및 미리보기
    $fileInput.on('change', function () {
        let files = this.files;

        // 기존 미리보기 초기화
        $fileList.html('');

        // 파일 개수 제한 (1개)
        if (files.length > 1) {
            alert("파일은 최대 1개까지만 첨부 가능합니다.");
            let dt = new DataTransfer();
            this.files = dt.files;
            $cnt.text(0);
            return;
        }

        for (let i = 0; i < files.length; i++) {
            let src = URL.createObjectURL(files[i]);
            $fileList.append(`<li>
                <div class="show-img-box">
                    <img src=${src}>
                </div>
                <div class="btn-box">
                    <button type='button' class='img-cancel-btn' data-name='${files[i].name}'>삭제</button>
                </div>
            </li>`);
        }	

        $cnt.text(files.length);

        // 삭제 버튼 처리
        $(".img-cancel-btn").on("click", function () {
            let fileName = $(this).data("name");
            let files = $fileInput[0].files;
            let dt = new DataTransfer();

            for (let i = 0; i < files.length; i++) {
                if (files[i].name !== fileName) {
                    dt.items.add(files[i]);
                }
            }

            $fileInput[0].files = dt.files;
            $(this).closest("li").remove();
            $cnt.text($fileInput[0].files.length);
        });
    });

    // 취소 버튼 처리
    $(".cancel-btn").on("click", function () {
        let boardNumber = $("#boardNumber").val();
        window.location.href = `/board/read?boardNumber=${boardNumber}`;
    });

    // 폼 제출 시 확인창
    $("#update-form").on("submit", function (e) {
        let confirmSubmit = confirm("게시글을 수정하시겠습니까?");
        if (!confirmSubmit) {
            e.preventDefault();
        }
    });
});

const fileInput = document.getElementById('file');
const fileList = document.querySelector('.file-list');
const cntElement = document.querySelector('.cnt');
const cancelButton = document.querySelector('.cancle-btn');
const writeForm = document.getElementById('write-form');

// 파일 첨부 미리보기 및 관리 핸들러
fileInput.addEventListener('change', (event) => {
    const files = event.target.files;

    // 기존 미리보기 초기화
    fileList.innerHTML = '';

    // 파일이 1개 초과인 경우 초기화하고 경고창 띄우기
    if (files.length > 1) {
        const dataTransfer = new DataTransfer();
        fileInput.files = dataTransfer.files;
        alert("파일은 최대 1개까지만 첨부 가능합니다.");
        cntElement.textContent = 0;
        return;
    }

    // 선택된 파일들을 순회하며 미리보기 생성
    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const src = URL.createObjectURL(file);

        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <div class="show-img-box">
                <img src="${src}" alt="${file.name}">
            </div>
            <div class="btn-box">
                <button type='button' class='img-cancel-btn' data-name='${file.name}'>삭제</button>
            </div>
        `;
        fileList.appendChild(listItem);
    }

    // 파일 개수 업데이트
    cntElement.textContent = files.length;
    
    // 새로 생성된 삭제 버튼에 클릭 이벤트 리스너 추가
    const cancelButtons = document.querySelectorAll('.img-cancel-btn');
    cancelButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            const fileName = e.target.dataset.name;
            const currentFiles = fileInput.files;
            const dataTransfer = new DataTransfer();

            // 삭제할 파일을 제외하고 새로운 FileList 생성
            for (let i = 0; i < currentFiles.length; i++) {
                if (currentFiles[i].name !== fileName) {
                    dataTransfer.items.add(currentFiles[i]);
                }
            }

            // 파일 입력(input)의 files 속성을 업데이트
            fileInput.files = dataTransfer.files;

            // DOM에서 미리보기 요소 제거
            e.target.closest('li').remove();

            // 파일 개수 업데이트
            cntElement.textContent = fileInput.files.length;
        });
    });
});


// 음식 설명 글자 수 표시
const description = document.getElementById('food_edit_explain');
const charCount = document.getElementById('food_edit_char_count');

description.addEventListener('input', function () {
  charCount.textContent = `${description.value.length}/100`;
});