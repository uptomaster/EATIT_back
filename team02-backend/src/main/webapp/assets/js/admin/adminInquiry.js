document.addEventListener("DOMContentLoaded", () => {
    const typeSelect = document.getElementById("searchType");
    const textInput = document.getElementById("textInput");
    const dateRange = document.getElementById("dateRange");
    const statusSelect = document.getElementById("statusSelect");

    function toggleSearchUI(type) {
        // 모든 필드 숨기고 비활성화
        textInput.style.display = "none";
        textInput.disabled = true;

        dateRange.style.display = "none";
        dateRange.querySelectorAll("input").forEach(el => el.disabled = true);

        statusSelect.style.display = "none";
        statusSelect.disabled = true;

        // 선택한 타입만 표시 + 활성화
        if (type === "title" || type === "memberId") {
            textInput.style.display = "inline-block";
            textInput.disabled = false;
        } else if (type === "date") {
            dateRange.style.display = "inline-block";
            dateRange.querySelectorAll("input").forEach(el => el.disabled = false);
        } else if (type === "status") {
            statusSelect.style.display = "inline-block";
            statusSelect.disabled = false;
        }
    }

    // 초기 실행 (기존 선택값 반영)
    toggleSearchUI(typeSelect.value);

    // 검색 타입 변경 시 UI 전환
    typeSelect.addEventListener("change", () => {
        toggleSearchUI(typeSelect.value);
    });

    // 상태 검색 드롭다운 자동 제출 (선택 즉시 검색)
    statusSelect.addEventListener("change", () => {
        if (statusSelect.value !== "") {
            statusSelect.form.submit();
        }
    });
});
