document.addEventListener("DOMContentLoaded", () => {
  const locationModal = document.querySelector('.location_modal');
  const locationBtn = document.querySelector('#location_btn');
  const locationClose = document.querySelector('.location_modal_close');
  const locationOverlay = document.querySelector('.location_modal_overlay');
  const saveLocation = document.querySelector(".save_location");

  if (locationBtn) {
    locationBtn.addEventListener("click", () => {
      locationModal.style.display = "flex";
    });
  }

  [locationClose, locationOverlay].forEach(btn => {
    btn && btn.addEventListener("click", () => {
      locationModal.style.display = "none";
    });
  });

  saveLocation && saveLocation.addEventListener("click", e => {
    e.preventDefault();
    const roadAddress = document.getElementById("sample4_roadAddress").value;
    if (!roadAddress) {
      alert("주소를 입력해주세요.");
      return;
    }
    const url = `${window.location.origin}/main.ma?address=${encodeURIComponent(roadAddress)}`;
    window.location.href = url;
  });
});
