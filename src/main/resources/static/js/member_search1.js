// 페이징 함수
function memberPaging(page) {
    const searchKeyword = $('#search').val().trim();
    if (searchKeyword) {
        performSearch(page);
    } else {
        loadPage(page);
    }
}

// 페이지 로드
function loadPage(page) {
    $.ajax({
        type: 'GET',
        url: '/api/members/list',
        data: { page: page },
        success: function(response) {
            if (response.status === 200 && response.data) {
                updateTable(response.data.members);
                if(response.data.memberPaging && response.data.memberPaging.pagingHTML) {
                    $('.pagination').html(response.data.memberPaging.pagingHTML);
                }
            }
        },
        error: function(err) {
            console.error('Error:', err);
        }
    });
}

// 검색 실행
function performSearch(page) {
    const keyword = $('#search').val().trim();
    if (!keyword) {
        loadPage(page);
        return;
    }

    $.ajax({
        type: 'GET',
        url: '/api/members/searchWithPaging',
        data: {
            keyword: keyword,
            page: page
        },
        success: function(response) {
            if (response.status === 200 && response.data) {
                updateTable(response.data.members);
                if(response.data.memberPaging && response.data.memberPaging.pagingHTML) {
                    $('.pagination').html(response.data.memberPaging.pagingHTML);
                }
            }
        },
        error: function(err) {
            console.error('Error:', err);
        }
    });
}

// 테이블 업데이트
function updateTable(members) {
    const tbody = $('#memberTableBody');
    tbody.empty();
    
    members.forEach(function(member) {
        const row = $('<tr>');
        row.append($('<td>').text(member.memberSeq));
        row.append($('<td>').text(member.id));
        row.append($('<td>').text(member.name));
        row.append($('<td>').text(member.email || ''));
        row.append($('<td>').text(member.phone || ''));
        row.append($('<td>').text(member.gender || ''));
        row.append($('<td>').text(formatDate(member.registerDate)));
        tbody.append(row);
    });
}

// 날짜 포맷팅
function formatDate(dateString) {
    if(!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit'
    });
}

// 페이지 로드 시 초기 데이터 로드
$(document).ready(function() {
    loadPage(1);
});