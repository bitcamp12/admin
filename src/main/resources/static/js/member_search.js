function loadPage(page) {
	fetch(`/api/members/memberList?page=${page}`)
		.then(response => response.json())
		.then(data => {
			if (data.status === 200 && data.data) {
				updateTable(data.data.members);
				updatePagination(data.data.currentPage, data.data.totalPages);
			}
		})
		.catch(error => console.error('Error:', error));
}


function performSearch(page = 1) {
	const keyword = document.getElementById('search').value;

	 fetch(`/api/members/searchWithPaging?keyword=${encodeURIComponent(keyword)}&page=${page}`)
	        .then(response => response.json())
	        .then(data => {
				if (data.members) {
	            	updateTable(data.members);
	            	updatePagination(data.currentPage, data.totalPages, data.keyword);
	        } else {
				console.error('No members data received');
			}
		})
	        .catch(error => {
	            console.error('Error:', error);
	            alert('검색 중 오류가 발생했습니다.');
        	});
}


 function updateTable(members) {
	const tbody = document.getElementById('memberTableBody');
	tbody.innerHTML = '';
      
    members.forEach(member => {
		const row = document.createElement('tr');
		row.innerHTML = `
			<td>${member.memberSeq}</td>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email || '' }</td>
            <td>${member.phone || '' }</td>
            <td>${member.gender || ''}</td>
            <td>${formatDate(member.registerDate)}</td>
			<td>
			    <button type="button" id="setSellerBtn"
			            th:data-name="${member.role}"
			            data-bs-target="#confirmModal"
			            th:if="${member.role != 'SELLER'}"
			            class="custom-btn">
			        전환
			    </button>
			    <button type="button" id="cancelSellerBtn"
			            th:data-name="${member.role}"
			            data-bs-target="#cancelModal"
			            th:if="${member.role == 'SELLER'}"
			            class="custom-btn">
			        전환취소
			    </button>
			</td>
		`;
       	tbody.appendChild(row);
	});
}

 function updatePagination(currentPage, totalPages) {
      const pagination = document.querySelector('.pagination');
      pagination.innerHTML = '';
      
      if (currentPage > 1) {
          pagination.innerHTML += `<a href="#" onclick="loadPage(${currentPage - 1})">이전</a>`;
      }
      
      for (let i = 1; i <= totalPages; i++) {
          pagination.innerHTML += `
              <a href="#" 
                 class="${i === currentPage ? 'active' : ''}"
                 onclick="loadPage(${i})">${i}</a>
          `;
      }
      
      if (currentPage < totalPages) {
          pagination.innerHTML += `<a href="#" onclick="performSearch(${currentPage + 1})">다음 &raquo;</a>`;
      }
}

function formatDate(dateString) {
	if(!dateString) return '';
	const date = new Date(dateString);
	return date.toLocalDateString('ko-KR');
}
  
document.addEventListener('DOMContentLoaded', () => {
	loadPage(1);
});