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
	
})