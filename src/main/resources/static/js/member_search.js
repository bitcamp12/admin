function performSearch() {
	const searchKeyword = document.getElementById('search').value;

	fetch(`/api/members/search?keyword=${searchKeyword}`) 
		.then(response => response.json())
		.then(data => {
			if(data.status === 200) {
				updateMemberTable(data.data);
			} else {
				alert('검색 중 오류 발생 : ' + data.message);
			}
		})	
		.catch(error => {
			console.error('Error:', error);
			alert('검색 요청 중 오류 발생');
		});
}

function updateMemberTable(members){
	const tbodyData = document.querySelector('table tbody');
	tbodyData.innerHTML = '' ; // 기존 데이터 초기화
	
	members.forEach(member => {
		const row = `
			<tr>
				<td>${member.memberSeq}</td>
				<td>${member.id}</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.phone}</td>
				<td>${member.gender}</td>
				<td>${member.registerDate}</td>
			</tr>
			`;
			tbodyData.innerHTML += row;
	});
}
