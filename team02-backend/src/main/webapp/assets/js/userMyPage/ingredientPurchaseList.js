document.addEventListener("DOMContentLoaded", () => {
	const pagination = document.getElementById("pagination"); 
	
	/*페이지네이션*/
	function updatePagination() {
	    pagination.innerHTML = "";
	    const totalPages = Math.ceil(filteredRows.length / rowsPerPage);
	    for (let i = 1; i <= totalPages; i++) {
	      const pageLink = document.createElement("a");
	      pageLink.href = "#";
	      pageLink.textContent = i;
	      pageLink.className = i === currentPage ? "active page" : "page";
	      pageLink.addEventListener("click", e => {
	        e.preventDefault();
	        currentPage = i;
	        displayList(currentPage);
	      });
	      pagination.appendChild(pageLink);
	    }
	  }
	  
	  
	  /* 리뷰 버튼 클릭시 */
    const reviewButtons = document.querySelectorAll(".review_button");

    reviewButtons.forEach(button => {
        button.addEventListener("click", function(event) {
            event.preventDefault(); // 링크 이동 막기
            const ordersNumber = this.dataset.ordersNumber;

            fetch(`${window.location.origin}/userMyPage/checkReview.my?ordersNumber=${ordersNumber}`)
                .then(response => response.json())
                .then(data => {
                    if (data.exists) {
                        alert("이미 작성된 리뷰가 존재합니다.");
                        window.location.href = `${window.location.origin}/userMyPage/myReviewListOk.my`;
                    } else {
                        window.location.href = `${window.location.origin}/userMyPage/writeReview.my?ordersNumber=${ordersNumber}`;
                    }
                })
                .catch(err => console.error(err));
        });
    });

	
})

