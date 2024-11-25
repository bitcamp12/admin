function loadPage(page) {
	fetch(`/api/members/memberList?page=${page}`)
		.then(response => response.text())
		.then(html => {
			document.getElementById('mainContent').innerHTML = html;
		})
		.catch(error => console.error('Error:', error));
}

// 페이지 로드 시 초기 데이터 로드

    loadPage(1);



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

/* function searchMembers(page) {
      const keyword = document.getElementById('search').value;
      
      fetch(`/api/members/search?keyword=${keyword}&page=${page}`)
          .then(response => response.json())
          .then(data => {
              updateTable(data.members);
              updatePagination(data.currentPage, data.totalPages);
          })
          .catch(error => console.error('Error:', error));
  }*/

  function updateTable(members) {
      const tbody = document.getElementById('memberTableBody');
      tbody.innerHTML = '';
      
      members.forEach(member => {
          const row = document.createElement('tr');
          row.innerHTML = `
              <td>${member.memberSeq}</td>
              <td>${member.id}</td>
              <td>${member.name}</td>
              <td>${member.email}</td>
              <td>${member.phone}</td>
              <td>${member.gender || ''}</td>
              <td>${new Date(member.registerDate).toLocaleDateString()}</td>
          `;
          tbody.appendChild(row);
      });
  }

  function updatePagination(currentPage, totalPages, keyword) {
      const pagination = document.querySelector('.pagination');
      pagination.innerHTML = '';
      
      if (currentPage > 1) {
          pagination.innerHTML += `
              <a href="#" onclick="performSearch(${currentPage - 1})">&laquo; 이전</a>
          `;
      }
      
      for (let i = 1; i <= totalPages; i++) {
          pagination.innerHTML += `
              <a href="#" 
                 class="${i === currentPage ? 'active' : ''}"
                 onclick="performSearch(${i})">${i}</a>
          `;
      }
      
      if (currentPage < totalPages) {
          pagination.innerHTML += `
              <a href="#" onclick="performSearch(${currentPage + 1})">다음 &raquo;</a>
          `;
      }
  }
