// 페이지 로드
function loadPage(page) {
    fetch(`/api/members/memberList?page=${page}`)
        .then(response => response.json())
        .then(data => {
            if (data.data) {
                updateTable(data.data.members);
                updatePagination(data.data.currentPage, data.data.totalPages);
            } else {
                console.error('No data received');
                showErrorMessage('데이터를 불러오는데 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showErrorMessage('페이지를 불러오는데 실패했습니다.');
        });
}

// 검색 실행
function performSearch(page = 1) {
    const keyword = document.getElementById('search').value.trim();
    if (keyword === '') {
        loadPage(page);
        return;
    }

    fetch(`/api/members/searchWithPaging?keyword=${encodeURIComponent(keyword)}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            if (data.data) {
                updateTable(data.data.members);
                updatePagination(data.data.currentPage, data.data.totalPages, true);
                
                // 검색 결과가 없는 경우
                if (data.data.members.length === 0) {
                    showMessage('검색 결과가 없습니다.');
                }
            } else {
                console.error('No search results received');
                showErrorMessage('검색 결과를 불러오는데 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showErrorMessage('검색 중 오류가 발생했습니다.');
        });
}

// 테이블 업데이트
function updateTable(members) {
    const tbody = document.getElementById('memberTableBody');
    tbody.innerHTML = '';

    if (!members || members.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="7" class="text-center">데이터가 없습니다.</td>';
        tbody.appendChild(row);
        return;
    }

    members.forEach(member => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${member.memberSeq}</td>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email || ''}</td>
            <td>${member.phone || ''}</td>
            <td>${member.gender || ''}</td>
            <td>${formatDate(member.registerDate)}</td>
        `;
        tbody.appendChild(row);
    });
}

// 페이지네이션 업데이트
function updatePagination(currentPage, totalPages, isSearch = false) {
    const pagination = document.querySelector('.pagination');
    pagination.innerHTML = '';

    if (totalPages <= 0) return;

    // 이전 페이지 버튼
    if (currentPage > 1) {
        const prevButton = createPageLink(currentPage - 1, '이전', isSearch);
        pagination.appendChild(prevButton);
    }

    // 페이지 번호 버튼
    for (let i = 1; i <= totalPages; i++) {
        const pageButton = createPageLink(i, i, isSearch);
        if (i === currentPage) {
            pageButton.classList.add('active');
        }
        pagination.appendChild(pageButton);
    }

    // 다음 페이지 버튼
    if (currentPage < totalPages) {
        const nextButton = createPageLink(currentPage + 1, '다음', isSearch);
        pagination.appendChild(nextButton);
    }
}

// 페이지 링크 생성 헬퍼 함수
function createPageLink(page, text, isSearch) {
    const link = document.createElement('a');
    link.href = '#';
    link.textContent = text;
    link.onclick = (e) => {
        e.preventDefault();
        isSearch ? performSearch(page) : loadPage(page);
    };
    return link;
}

// 날짜 포맷팅 헬퍼 함수
function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
    });
}

// 메시지 표시 함수
function showMessage(message) {
    // 메시지를 표시할 요소가 있다고 가정
    const messageElement = document.getElementById('messageArea') || createMessageArea();
    messageElement.textContent = message;
    messageElement.style.color = '#666';
    messageElement.style.display = 'block';
    setTimeout(() => {
        messageElement.style.display = 'none';
    }, 3000);
}

// 에러 메시지 표시 함수
function showErrorMessage(message) {
    const messageElement = document.getElementById('messageArea') || createMessageArea();
    messageElement.textContent = message;
    messageElement.style.color = '#ff0000';
    messageElement.style.display = 'block';
    setTimeout(() => {
        messageElement.style.display = 'none';
    }, 3000);
}

// 메시지 영역 생성 함수
function createMessageArea() {
    const messageArea = document.createElement('div');
    messageArea.id = 'messageArea';
    messageArea.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 10px 20px;
        background: #fff;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        display: none;
    `;
    document.body.appendChild(messageArea);
    return messageArea;
}

// 페이지 로드 시 초기 데이터 로드
document.addEventListener('DOMContentLoaded', () => {
    loadPage(1);
});