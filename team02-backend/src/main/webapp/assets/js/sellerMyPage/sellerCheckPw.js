
document.addEventListener("DOMContentLoaded", () => {
	// 예시: 올바른 비밀번호 (실제로는 서버에서 확인해야 함)
	const correctPassword = "pw_seller01";

	// 정보 입력 전 비밀번호 확인
	const pwInfo = document.getElementById('seller_chk_pw_info');
	const chkPwBtn = document.getElementById('seller_chk_pw_btn');
	const Warning = document.getElementById('seller_chk_pw_warning');

/*	chkPwBtn.addEventListener("click", () => {
		let Password = pwInfo.value;

		if (Password !== correctPassword) {
			Warning.textContent = "입력하신 비밀번호와 일치하지 않습니다.";
			Warning.style.color = "red";
			// console.log('wrong');
			// console.log('current', correctPassword);
			// console.log('input', typeof(Password), Password);
 
		} else {
			console.log('correct');
			Warning.textContent = "";
			window.location.href = './editSellerInfo.html';
		}
	})*/
// DB에 있는 비밀번호와 비교
pwInfo.addEventListener("change", function() {
		const Password = pwInfo.value.trim();
		if (!Password) {
			Warning.textContent = "비밀번호를 입력해주세요.";
			Warning.style.color = "red";
			return;
		}
		fetch(`${base}/sellerMyPage/chkPwOk.se?Password=${encodeURIComponent(Password)}`, {
			headers: { "Accept": "application/json" }
		})
			.then(r => { if (!r.ok) throw new Error(r.status); return r.json(); })
			.then(data => {
				if (data.available) { // 내 비밀번호와 동일할때
					Warning.textContent = "마이페이지에 진입합니다.";
					Warning.style.color = "green";
				} else {
					Warning.textContent = "틀린 비밀번호 입니다.";
					Warning.style.color = "red";
				}
			})
			.catch(() => {
				Warning.textContent = "아이디 중복 검사 중 오류가 발생했습니다.";
				Warning.style.color = "red";
			});
	})

	let withdraw = document.getElementById('seller_withdraw_buzz');
	withdraw.addEventListener('click', () => {
		location.replace('./../sellerMyPage/sellerwithdrawalAgreement.html');
	});