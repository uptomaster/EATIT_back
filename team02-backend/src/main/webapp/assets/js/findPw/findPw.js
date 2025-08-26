document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".findPw_input_container");
  const btn  = document.querySelector(".findPw_btn");
  if (!form || !btn) return;

  const base = form.dataset.contextPath || "";
  const idEl = document.getElementById("findPw_input_id");
  const phoneEl = document.getElementById("findPw_input_phone");
  const warnPhone = document.getElementById("warning_message_phone");

  const normalizePhone = s => (s || "").replace(/\D+/g, "");
  const isPhoneValid = s => /^\d{10,11}$/.test(s);

  btn.addEventListener("click", async () => {
    const id = (idEl.value || "").trim();
    const phone = normalizePhone(phoneEl.value || "");

    if (!id) { alert("아이디를 입력하세요."); idEl.focus(); return; }
    if (!isPhoneValid(phone)) {
      if (warnPhone) warnPhone.textContent = "전화번호를 정확히 입력해주세요. (예: 01012345678)";
      phoneEl.focus(); return;
    } else if (warnPhone) { warnPhone.textContent = ""; }

    try {
      // 1) 아이디+휴대폰 확인
      const r1 = await fetch(`${base}/findPw/findPwOk.fp`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded", "Accept": "application/json" },
        body: new URLSearchParams({
          findPw_input_id: id,
          findPw_input_phone: phone
        }).toString()
      });
      if (!r1.ok) throw new Error(r1.status);
      const data1 = await r1.json();
      if (!data1.ok || !data1.memberNumber) {
        alert("일치하는 회원이 없습니다.");
        return;
      }

      // 2) 임시비번 생성
	  function generatePolicyTemp() {
	    const upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    const lower = "abcdefghijklmnopqrstuvwxyz";
	    const digits = "0123456789";
	    const special = "!@#$%^&*";
	    const all = upper + lower + digits + special;
		// str에서 문자를 무작위로 n개(중복 허용) 뽑아 이어 붙인 길이 n의 문자열을 만들어 반환한다.
	    function pick(str, n){ return Array.from({length:n},()=>str[Math.floor(Math.random()*str.length)]).join(''); }
	    // 최소조건 충족 + 랜덤 채우기 (10자)
	    let pw = pick(upper,1) + pick(lower,1) + pick(digits,1) + pick(special,1) + pick(all,6);
	    // 섞기
	    pw = pw.split('').sort(()=>Math.random()-0.5).join('');
	    return pw;
	  }
	  
	  
      const tempPw = generatePolicyTemp();

      // 3) 임시비번 저장 (★ FrontController 매핑: /findPw/tempPw.fi)
      const r2 = await fetch(`${base}/findPw/tempPw.fp`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded", "Accept": "application/json" },
        body: new URLSearchParams({
          memberNumber: String(data1.memberNumber),
          tempPw
        }).toString()
      });
      if (!r2.ok) throw new Error(r2.status);
      const data2 = await r2.json();
      if (!data2.ok) {
        alert(`임시 비밀번호 저장 실패: ${data2.reason || ''}`);
        return;
      }

      // 4) 안내 후 수정 페이지로 이동
      alert(`임시 비밀번호: ${tempPw}\n확인 후 비밀번호 수정 페이지로 이동합니다.`);
      location.href = `${base}/app/findPw/editPw.jsp`;
    } catch (e) {
      console.error(e);
      alert("처리 중 오류가 발생했습니다.");
    }
  });
});