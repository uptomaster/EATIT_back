window.addEventListener("load", function () {
    const form = document.querySelector(".withdrawalagreement");
    const agreeRadio = form.querySelector("input[name='agree']");

    form.addEventListener("submit", function (e) {
        if (!agreeRadio.checked) { // 체크 안 되어 있으면
            e.preventDefault(); // 제출 막기
            alert("회원탈퇴에 동의하셔야 진행할 수 있습니다.");
        }
    });
});
