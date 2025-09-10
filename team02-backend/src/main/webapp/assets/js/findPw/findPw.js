// /assets/js/findPw/findPw.js
document.addEventListener("DOMContentLoaded", function () {
  const form       = document.querySelector(".findPw_input_container");
  if (!form) return;

  const base       = form.dataset.contextPath || "";
  const idInput    = document.getElementById("findPw_input_id");
  const phoneInput = document.getElementById("findPw_input_phone");
  const codeInput  = document.getElementById("findPwAuth_input_auth");
  const reqBtn     = document.querySelector(".findPwAuth_req_auth"); // 인증요청
  const chkBtn     = document.querySelector(".findPwAuth_chk_auth");  // 인증완료

  const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;
  const codeRegex  = /^\d{6}$/;
  const onlyDigits = s => (s || "").replace(/\D+/g, "");

  // ==== 문자 전송 (네가 준 버전 그대로) ====
  reqBtn.addEventListener("click", function () {
    const phoneNumber = (phoneInput.value || "").trim();
    if (!phoneRegex.test(phoneNumber)) {
      alert("핸드폰 번호를 입력해주세요.");
      return;
    }
    fetch(`${base}/join/sendSMS.jo?memberPhoneNumber=${encodeURIComponent(phoneNumber)}`, {
      method: "GET",
      headers: {
        "Accept": "text/plain",
        "X-Requested-With": "XMLHttpRequest" // 전체 리로드 방지
      }
    })
      .then(res => {
        if (!res.ok) throw new Error("발송 실패: " + res.status);
        return res.text();
      })
      .then(msg => {
        alert(msg); // "인증번호가 발송되었습니다." 등
        codeInput.disabled = false;
        codeInput.focus();
      })
      .catch(err => {
        alert("SMS 발송 중 오류가 발생했습니다.\n" + err);
      });
  });

  // ==== 인증완료: 코드 검증 → memberNumber 조회 → 세션 저장(startReset) → editPw.jsp 이동 ====
  chkBtn.addEventListener("click", async function () {
    const memberId = (idInput.value || "").trim();
    const phoneRaw = (phoneInput.value || "").trim();
    const phoneDig = onlyDigits(phoneRaw);
    const code     = (codeInput.value || "").trim();

    // 기본 입력 검증
    if (!memberId) { alert("아이디를 입력해주세요."); idInput.focus(); return; }
    if (!phoneRegex.test(phoneRaw)) { alert("핸드폰 번호를 정확히 입력해주세요."); phoneInput.focus(); return; }
    if (!codeRegex.test(code)) { alert("인증번호 6자리를 정확히 입력해주세요."); codeInput.focus(); return; }

    try {
      // 1) 인증번호 서버 검증
      const vres = await fetch(`${base}/join/sendSMSOK.jo?verificationCode=${encodeURIComponent(code)}`, {
        method: "GET",
        headers: { "Accept": "text/plain", "X-Requested-With": "XMLHttpRequest" }
      });
      const vtxt = (await vres.text()).trim();
      if (!vres.ok || !vtxt.includes("성공")) {
        alert(vtxt || "인증번호가 일치하지 않습니다.");
        codeInput.focus();
        return;
      }

      // 2) 아이디+전화로 memberNumber 조회 (기존 컨트롤러 사용)
      const r1 = await fetch(`${base}/findPw/findPwOk.fp`, {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
          "Accept": "application/json"
        },
        body: new URLSearchParams({
          findPw_input_id: memberId,
          findPw_input_phone: phoneDig
        }).toString()
      });
      if (!r1.ok) throw new Error("확인 실패: " + r1.status);
      const data1 = await r1.json();
      if (!data1 || !data1.ok || !data1.memberNumber) {
        alert("입력하신 아이디와 전화번호가 일치하지 않습니다.");
        return;
      }

      // 3) 세션에 대상 저장 (StartResetController)
      const r2 = await fetch(`${base}/findPw/startReset.fp`, {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
          "Accept": "application/json"
        },
        body: new URLSearchParams({ memberNumber: String(data1.memberNumber) }).toString()
      });
      const data2 = await r2.json().catch(() => ({ ok: false }));
      if (!r2.ok || !data2.ok) {
        alert("세션 설정 중 오류가 발생했습니다. 다시 시도해주세요.");
        return;
      }

      // 4) 완료 → 비밀번호 수정 페이지로 이동
      alert("인증이 완료되었습니다. 비밀번호 수정 페이지로 이동합니다.");
      location.href = `${base}/app/findPw/editPw.jsp`;
    } catch (e) {
      console.error(e);
      alert("요청 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
    }
  });
});
