// /assets/js/findPw/findPw.js
document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".pw-form");
  if (!form) {
    console.warn("[findPw] .pw-form not found");
    return;
  }

  const base       = form.dataset.contextPath || "";
  const idInput    = document.getElementById("findPw_input_id");
  const phoneInput = document.getElementById("findPw_input_phone");
  const codeInput  = document.getElementById("findPwAuth_input_auth");

  const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;
  const codeRegex  = /^\d{6}$/;
  const onlyDigits = s => (s || "").replace(/\D+/g, "");

  // -------------------------------------------------------
  // 1) 이벤트 위임: 버튼을 못 잡아도 form 클릭에서 처리
  // -------------------------------------------------------
  form.addEventListener("click", async (e) => {
    const target = e.target;

    // === [인증요청] ===
    if (target.matches(".findPwAuth_req_auth")) {
      e.preventDefault();

      const phoneNumber = (phoneInput?.value || "").trim();
      console.log("[findPw] 인증요청 클릭, phone:", phoneNumber);

      if (!phoneRegex.test(phoneNumber)) {
        alert("핸드폰 번호를 입력해주세요.");
        phoneInput?.focus();
        return;
      }

      try {
        target.disabled = true; // 중복 클릭 방지
        const res = await fetch(`${base}/join/sendSMS.jo?memberPhoneNumber=${encodeURIComponent(phoneNumber)}`, {
          method: "GET",
          headers: {
            "Accept": "text/plain",
            "X-Requested-With": "XMLHttpRequest" // 전체 리로드 방지
          }
        });
        const msg = await res.text();
        console.log("[findPw] sendSMS response:", res.status, msg);

        if (!res.ok) throw new Error(`발송 실패: ${res.status}`);
        alert(msg); // "인증번호가 발송되었습니다." 등

        if (codeInput) {
          codeInput.disabled = false;
          codeInput.focus();
        }
      } catch (err) {
        console.error(err);
        alert("SMS 발송 중 오류가 발생했습니다.\n" + err.message);
      } finally {
        target.disabled = false;
      }
    }

    // === [인증완료] ===
    if (target.matches(".findPwAuth_chk_auth")) {
      e.preventDefault();

      const memberId = (idInput?.value || "").trim();
      const phoneRaw = (phoneInput?.value || "").trim();
      const phoneDig = onlyDigits(phoneRaw);
      const code     = (codeInput?.value || "").trim();

      console.log("[findPw] 인증완료 클릭", { memberId, phoneRaw, code });

      // 기본 입력 검증
      if (!memberId) { alert("아이디를 입력해주세요."); idInput?.focus(); return; }
      if (!phoneRegex.test(phoneRaw)) { alert("핸드폰 번호를 정확히 입력해주세요."); phoneInput?.focus(); return; }
      if (!codeRegex.test(code)) { alert("인증번호 6자리를 정확히 입력해주세요."); codeInput?.focus(); return; }

      try {
        target.disabled = true;

        // 1) 인증번호 서버 검증
        const vres = await fetch(`${base}/join/sendSMSOK.jo?verificationCode=${encodeURIComponent(code)}`, {
          method: "GET",
          headers: { "Accept": "text/plain", "X-Requested-With": "XMLHttpRequest" }
        });
        const vtxt = (await vres.text()).trim();
        console.log("[findPw] sendSMSOK response:", vres.status, vtxt);

        if (!vres.ok || !vtxt.includes("성공")) {
          alert(vtxt || "인증번호가 일치하지 않습니다.");
          codeInput?.focus();
          return;
        }

        // 2) 아이디+전화로 memberNumber 조회
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
        const data1 = await r1.json().catch(() => ({}));
        console.log("[findPw] findPwOk response:", r1.status, data1);

        if (!r1.ok || !data1 || !data1.ok || !data1.memberNumber) {
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
        console.log("[findPw] startReset response:", r2.status, data2);

        if (!r2.ok || !data2.ok) {
          alert("세션 설정 중 오류가 발생했습니다. 다시 시도해주세요.");
          return;
        }

        // 4) 완료 → 비밀번호 수정 페이지로 이동
        alert("인증이 완료되었습니다. 비밀번호 수정 페이지로 이동합니다.");
        location.href = `${base}/app/findPw/editPw.jsp`;
      } catch (e2) {
        console.error(e2);
        alert("요청 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
      } finally {
        target.disabled = false;
      }
    }
  });

  // 로딩 시점에 버튼을 직접 붙이는 기존 방식도 함께 시도(있으면 OK)
  const reqBtn = form.querySelector(".findPwAuth_req_auth");
  const chkBtn = form.querySelector(".findPwAuth_chk_auth");
  if (!reqBtn) console.warn("[findPw] .findPwAuth_req_auth not found (위임으로 처리)");
  if (!chkBtn) console.warn("[findPw] .findPwAuth_chk_auth not found (위임으로 처리)");
});
