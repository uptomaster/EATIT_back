document.addEventListener("DOMContentLoaded", () => {
  // 한 페이지에 보여줄 게시글(행)의 개수
  const rowsPerPage = 10;
  // 현재 보고 있는 페이지 번호 (1부터 시작)
  let currentPage = 1;

  // 게시글 목록의 tbody 요소 (게시글 행들이 들어있는 곳)
  const listBody = document.getElementById("postListBody");
  // 게시글 행들(.list_row 클래스가 붙은 tr 요소들)을 배열로 저장
  const rows = Array.from(listBody.querySelectorAll(".list_row"));
  // 페이지 번호가 표시될 영역
  const pagination = document.getElementById("pagination");
  // 검색 입력창 요소
  const searchInput = document.querySelector(".search_text");
  // 검색 버튼 요소
  const searchBtn = document.querySelector(".search_btn");

  // 현재 필터링 된 게시글 배열 (검색 시 필터링됨)
  let filteredRows = [...rows];

  // 특정 페이지에 해당하는 게시글만 화면에 표시하는 함수
  function displayList(page) {
    // tbody 내용을 초기화(기존 게시글 모두 제거)
    listBody.innerHTML = "";
    // 보여줄 게시글의 시작 인덱스
    let start = (page - 1) * rowsPerPage;
    // 보여줄 게시글의 끝 인덱스 (start부터 rowsPerPage개)
    let end = start + rowsPerPage;

    // 필터링된 게시글 중 현재 페이지에 해당하는 부분만 tbody에 추가
    filteredRows.slice(start, end).forEach(row => listBody.appendChild(row));
    // 페이지 번호 UI를 업데이트
    updatePagination();
  }

  // 페이지 번호 버튼을 동적으로 생성하고 표시하는 함수
  function updatePagination() {
    // 기존 페이지 번호 영역 초기화
    pagination.innerHTML = "";
    // 전체 페이지 수 (게시글 수에 따라 달라짐, 최소 1페이지)
    let totalPages = Math.max(1, Math.ceil(filteredRows.length / rowsPerPage));

    // 1부터 totalPages까지 페이지 번호 버튼 생성
    for (let i = 1; i <= totalPages; i++) {
      const pageLink = document.createElement("a");
      pageLink.href = "#"; // 링크는 #으로 설정
      pageLink.textContent = i; // 버튼에 페이지 번호 표시
      // 현재 페이지는 active 클래스 추가해서 스타일링
      pageLink.className = "page" + (i === currentPage ? " active" : "");
      // 페이지 번호 클릭 시 해당 페이지로 이동하도록 이벤트 등록
      pageLink.addEventListener("click", (e) => {
        e.preventDefault(); // 링크 기본 동작 막기
        currentPage = i;     // 현재 페이지 번호 변경
        displayList(currentPage); // 게시글 다시 표시
      });
      pagination.appendChild(pageLink); // 페이지 번호 버튼을 화면에 추가
    }
  }

  // 검색어에 따라 게시글 목록을 필터링하는 함수
  function searchList() {
    // 검색창 입력값을 소문자로 변환해 가져옴 (앞뒤 공백 제거)
    const query = searchInput.value.trim().toLowerCase();
    // 모든 게시글 중에서 검색어가 포함된 것만 필터링
    filteredRows = rows.filter(row => row.textContent.toLowerCase().includes(query));
    // 검색 결과가 1페이지부터 보이도록 초기화
    currentPage = 1;
    // 필터링된 결과로 게시글 목록 표시
    displayList(currentPage);
  }

  // 검색 버튼 클릭 시 검색 함수 실행
  searchBtn.addEventListener("click", searchList);

  // 검색창에서 Enter 키 눌렀을 때 검색 함수 실행
  searchInput.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
      e.preventDefault(); // 폼 제출 등 기본 동작 막기
      searchList();
    }
  });

  // 초기 화면 로드시 1페이지 게시글을 표시
  displayList(currentPage);
});

// 별도의 DOMContentLoaded 이벤트로 작성 버튼에 클릭 이벤트 등록
document.addEventListener("DOMContentLoaded", function() {
  // 글쓰기 버튼 요소
  const writeBtn = document.getElementById("writeBtn");

  // 클릭 시 관리자만 작성 가능하다는 경고를 띄우고 현재 페이지로 다시 이동
  writeBtn.addEventListener("click", function(event) {
    event.preventDefault(); // 기본 링크 이동 막기
    alert("관리자만 작성할 수 있습니다."); // 경고 메시지
    window.location.href = window.location.pathname; // 현재 URL 경로로 새로고침
  });
});
