document.addEventListener("DOMContentLoaded", function() {
	const form = document.getElementById("sellerJoinForm") || document.querySelector("form");
	const base = (form && form.dataset.contextPath) ? form.dataset.contextPath : "";

	const idInput = document.getElementById("seller_input_id");
	const passwordInput = document.getElementById("seller_input_pw");
	const passwordConfirmInput = document.getElementById("seller_input_chk_pw");
	const sendSMSBtn = document.getElementById("btn_seller_input_phone");
	const phoneNumberInput = document.getElementById("seller_input_phone");

	const checkIdMsg = document.getElementById("warning_message_chk_id");
	const checkPwMsg = document.getElementById("warning_message_pw");
	const checkPwConfirmMsg = document.getElementById("warning_message_chk_pw");
	const verificationCodeInput = document.getElementById("seller_input_chk_phone");
	const verificationStatus = document.getElementById("warning_message_chk_phone");
	console.log("[seller] sellerInfoInput.js loaded", new Date().toISOString());
	window.addEventListener("error", e => console.error("[seller] GlobalError:", e.error || e.message));
	window.addEventListener("unhandledrejection", e => console.error("[seller] UnhandledRejection:", e.reason));
	idInput.addEventListener("change", function() {
		const memberId = idInput.value.trim();
		if (!memberId) {
			checkIdMsg.textContent = "아이디를 입력해주세요.";
			checkIdMsg.style.color = "red";
			return;
		}
		fetch(`${base}/join/checkId.jo?memberId=${encodeURIComponent(memberId)}`, {
			headers: { "Accept": "application/json" }
		})
			.then(r => { if (!r.ok) throw new Error(r.status); return r.json(); })
			.then(data => {
				if (data.available) {
					checkIdMsg.textContent = "사용 가능한 아이디입니다.";
					checkIdMsg.style.color = "green";
				} else {
					checkIdMsg.textContent = "이미 사용 중인 아이디입니다.";
					checkIdMsg.style.color = "red";
				}
			})
			.catch(() => {
				checkIdMsg.textContent = "아이디 중복 검사 중 오류가 발생했습니다.";
				checkIdMsg.style.color = "red";
			});
	});
	// ===== 비밀번호 유효성/일치 =====
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,16}$/;

	passwordInput.addEventListener("blur", function() {
		const pw = passwordInput.value.trim();
		if (passwordRegex.test(pw)) {
			checkPwMsg.textContent = "사용 가능한 비밀번호입니다.";
			checkPwMsg.style.color = "green";
		} else {
			checkPwMsg.textContent = "영문, 숫자, 특수문자를 포함하여 8자 이상 입력해야 합니다.";
			checkPwMsg.style.color = "red";
		}
	});

	passwordConfirmInput.addEventListener("blur", function() {
		const pw = passwordInput.value.trim();
		const pw2 = passwordConfirmInput.value.trim();
		if (pw && pw === pw2) {
			checkPwConfirmMsg.textContent = "비밀번호가 일치합니다.";
			checkPwConfirmMsg.style.color = "green";
		} else {
			checkPwConfirmMsg.textContent = "비밀번호가 일치하지 않습니다.";
			checkPwConfirmMsg.style.color = "red";
		}
	});


	// ===== SMS 발송 (임시 인증번호 생성) =====

	const phoneRegex = /^01[016789]-?\d{3,4}-?\d{4}$/;
	(function() {
		const p = new URLSearchParams(location.search);
		if (p.get('dupPhone') === '1') alert('이미 가입된 휴대폰번호입니다. 로그인 또는 아이디/비밀번호 찾기를 이용해주세요.');
		if (p.get('addr') === '1') alert('가게 주소의 좌표(위도/경도)를 찾지 못했습니다. 주소를 다시 확인한 뒤 우편번호 검색 → 주소 선택을 다시 진행해주세요.');
	})();
	sendSMSBtn.addEventListener("click", async function() {
		const phoneNumber = phoneNumberInput.value.trim();
		if (!phoneRegex.test(phoneNumber)) {
			alert("핸드폰 번호를 입력해주세요.");
			return;
		}
		try {
			const r = await fetch(`${base}/join/checkPhone.jo?phone=${encodeURIComponent(phoneNumber)}`,
				{ headers: { "Accept": "application/json" } });
			const j = await r.json();
			if (!j.available) {
				const phoneMsg = document.getElementById("warning_message_phone");
				if (phoneMsg) {
					phoneMsg.textContent = "이미 가입된 휴대폰번호입니다. 로그인 또는 아이디/비밀번호 찾기를 이용해주세요.";
					phoneMsg.style.color = "red";
				}
				return;
			}
		} catch (e) {
			alert("휴대폰 중복 확인 중 오류가 발생했습니다.");
			return;
		}
		fetch(`${base}/join/sendSMS.jo?memberPhoneNumber=${encodeURIComponent(phoneNumber)}`, {
			method: "GET",
			headers: {
				"Accept": "text/plain",
				"X-Requested-With": "XMLHttpRequest" // 이걸 추가해야 서버를 다시로드 하지 않고 인증번호를 받을 수 있음
			}
		})
			.then(res => {
				if (!res.ok) throw new Error("발송 실패: " + res.status);
				return res.text(); // text 형식으로 받음
			})
			.then(msg => {
				// 서버가 성공적으로 처리했을 때만 실행
				alert(msg);               // 발송 메시지
			})
			.catch(err => {
				// 실패했을 때
				alert("SMS 발송 중 오류가 발생했습니다.\n" + err);
			});
	});

	// ===== 인증번호 확인 (서버 대신 로컬 비교) =====
	verificationCodeInput.addEventListener("blur", async function() {
		const code = (verificationCodeInput.value || "").trim();

		if (!code) {
			verificationStatus.textContent = "인증번호를 입력해주세요.";
			verificationStatus.style.color = "red";
			verificationCodeInput.dataset.verified = "false";
			return;
		}
		if (!/^\d{6}$/.test(code)) {
			verificationStatus.textContent = "인증번호 6자리를 정확히 입력해주세요.";
			verificationStatus.style.color = "red";
			verificationCodeInput.dataset.verified = "false";
			return;
		}

		try {
			const res = await fetch(`${base}/join/sendSMSOK.jo?verificationCode=${encodeURIComponent(code)}`, {
				method: "GET",
				headers: { "Accept": "text/plain", "X-Requested-With": "XMLHttpRequest" }
			});
			const msg = (await res.text()).trim();

			if (res.ok && msg.includes("성공")) {
				verificationStatus.textContent = "인증에 성공했습니다.";
				verificationStatus.style.color = "green";
				verificationCodeInput.dataset.verified = "true";
			} else {
				verificationStatus.textContent = msg || "인증번호가 일치하지 않습니다.";
				verificationStatus.style.color = "red";
				verificationCodeInput.dataset.verified = "false";
			}
		} catch (e) {
			verificationStatus.textContent = "요청 중 오류가 발생했습니다.";
			verificationStatus.style.color = "red";
			verificationCodeInput.dataset.verified = "false";
		}
	});

	// 사용자가 코드를 다시 입력하면 검증 상태 초기화
	verificationCodeInput.addEventListener("input", function() {
		verificationCodeInput.dataset.verified = "false";
	});
	// 번호를 바꾸면 인증상태 초기화 + 메시지 지우기
	phoneNumberInput.addEventListener("input", function() {
		verificationCodeInput.dataset.verified = "false";
		verificationStatus.textContent = "";
		const phoneMsg = document.getElementById("warning_message_chk_phone") /* 일반 */
			|| document.getElementById("warning_message_phone");   /* 판매자 */
		if (phoneMsg) phoneMsg.textContent = "";
	});
	// ===== 사업자등록번호 (3-2-5) 자동 하이픈 & 검증 =====
	const bizNoInput = document.getElementById('seller_input_store_number');

	if (bizNoInput) {
		// 입력 중 자동 하이픈
		bizNoInput.addEventListener('input', () => {
			let v = bizNoInput.value.replace(/\D/g, '').slice(0, 10); // 숫자만, 최대 10자리
			if (v.length > 5) v = `${v.slice(0, 3)}-${v.slice(3, 5)}-${v.slice(5)}`;
			else if (v.length > 3) v = `${v.slice(0, 3)}-${v.slice(3)}`;
			bizNoInput.value = v;
		});
	}
	// Kakao SDK 로드 완료를 기다리는 함수 (최대 5초)
	function waitForKakao(maxMs = 10000, interval = 50) {
		return new Promise((resolve, reject) => {
			const start = Date.now();
			(function check() {
				try {
					if (window.kakao && kakao.maps) {
						// autoload=false면 load()로 초기화
						if (typeof kakao.maps.load === "function") {
							return kakao.maps.load(() => {
								if (kakao.maps.services && typeof kakao.maps.services.Geocoder === "function") {
									resolve();
								} else {
									reject(new Error("Kakao services not loaded"));
								}
							});
						}
						// autoload=true인 경우
						if (kakao.maps.services && typeof kakao.maps.services.Geocoder === "function") {
							return resolve();
						}
					}
				} catch (_) { }
				if (Date.now() - start > maxMs) return reject(new Error("Kakao SDK not ready"));
				setTimeout(check, interval);
			})();
		});
	}

	let geocoder; // 전역 유지
	async function getGeocoder() {
		await waitForKakao();
		if (!geocoder) geocoder = new kakao.maps.services.Geocoder();
		return geocoder;
	}

	function setLatLng(lat, lng) {
		const latEl = document.getElementById("seller_input_store_lat");
		const lngEl = document.getElementById("seller_input_store_lng");
		if (latEl && lngEl) {
			latEl.value = (lat ?? "").toString();
			lngEl.value = (lng ?? "").toString();
		}
	}

	// 주소 → 좌표 변환
	async function geocodeAddress(address) {
		console.log("[seller] geocodeAddress:start", address);
		const gc = await getGeocoder();
		return new Promise((resolve, reject) => {
			gc.addressSearch(address, (result, status) => {
				console.log("[seller] addressSearch:status", status, result && result[0]);
				if (status === kakao.maps.services.Status.OK && result && result[0]) {
					const lat = parseFloat(result[0].y);
					const lng = parseFloat(result[0].x);
					setLatLng(lat.toFixed(6), lng.toFixed(6));
					console.log("[seller] set lat/lng", lat, lng);
					resolve({ lat, lng });
				} else {
					reject(new Error("주소 좌표 변환 실패: " + status));
				}
			});
		});
	}

	// 주소검색 버튼
	const searchBtn = document.getElementById("searchPostcodeBtn");
	if (searchBtn) {
		searchBtn.addEventListener("click", function() {
			new daum.Postcode({
				oncomplete: async function(data) {
					console.log("[seller] postcode:oncomplete", data);

					// 1) 우편번호
					document.getElementById("seller_input_store_zip").value = data.zonecode || "";

					// 2) 메인주소(도로명/지번) + 참고항목
					const isRoad = data.userSelectedType === "R";
					const base = isRoad ? (data.roadAddress || "") : (data.jibunAddress || "");
					let extra = "";
					if (isRoad) {
						if (data.bname && /[동|로|가]$/.test(data.bname)) extra += data.bname;
						if (data.buildingName && data.apartment === "Y") {
							extra += (extra ? ", " : "") + data.buildingName;
						}
					}
					const main = (base + (extra ? " (" + extra + ")" : "")).trim();
					document.getElementById("seller_input_store_address").value = main;

					// 3) 상세주소 포커스
					const detailEl = document.getElementById("seller_input_store_address_detail");
					detailEl && detailEl.focus();

					// 4) 메인주소 기준 1차 지오코딩 (상세주소 입력 전)
					try {
						await geocodeAddress(main);
					} catch (e) {
						console.warn("[seller] geocode fail on main:", e);
						setLatLng("", "");
					}
				}
			}).open({ popupTitle: "우편번호 검색" });
		});
	}

	// 상세주소 입력 후 재계산
	const mainAddrEl = document.getElementById("seller_input_store_address");
	const detailAddrEl = document.getElementById("seller_input_store_address_detail");
	if (mainAddrEl && detailAddrEl) {
		const recompute = async () => {
			const combo = `${(mainAddrEl.value || "").trim()} ${(detailAddrEl.value || "").trim()}`.trim();
			if (!combo) return;
			try { await geocodeAddress(combo); } catch (e) { /* 제출 시 한 번 더 시도 */ }
		};
		detailAddrEl.addEventListener("blur", recompute);
	}

	// 제출 전 최종 검증 (하나의 submit 리스너만!)
	form.addEventListener("submit", async function(e) {
		console.log("[seller] submit:start");

		// 휴대폰 인증 확인
		const verificationCodeInput = document.getElementById("seller_input_chk_phone");
		if (verificationCodeInput?.dataset.verified !== "true") {
			e.preventDefault();
			alert("휴대폰 인증을 완료해주세요.");
			verificationCodeInput.focus();
			return;
		}

		// 좌표 없으면 전체주소로 재지오코딩
		let lat = document.getElementById("seller_input_store_lat").value.trim();
		let lng = document.getElementById("seller_input_store_lng").value.trim();

		if (!lat || !lng) {
			const full = `${(document.getElementById("seller_input_store_address").value || "").trim()} ${(document.getElementById("seller_input_store_address_detail").value || "").trim()}`.trim();
			console.log("[seller] submit:lat/lng empty, retry geocode", full);
			try {
				await geocodeAddress(full);
				lat = document.getElementById("seller_input_store_lat").value.trim();
				lng = document.getElementById("seller_input_store_lng").value.trim();
			} catch (e2) {
				console.warn("[seller] submit:retry geocode failed", e2);
				e.preventDefault();
				alert("가게 주소의 좌표(위도/경도)를 찾지 못했습니다. 주소를 다시 확인해 주세요.");
				return;
			}
		}

		console.log("[seller] submit:continue (lat/lng exist)", lat, lng);
	});
});