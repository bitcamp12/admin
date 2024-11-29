var memberSeq = "";

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


function showModal(message) {
	document.getElementById('modalMessage').textContent = message;
	const Modal = new bootstrap.Modal(document.getElementById('Modal'));
	Modal.show();
}

$('#setSellerBtn').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // 버튼을 클릭한 요소
	var seq = button.data('seq'); // data-seq 값 가져오기
	$('#setSellerBtn').data('seq', seq); // 삭제 버튼에 seq 값을 저장
	console.log("seq 설정 완료: " + seq);

});

$('#cancelSellerBtn').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // 버튼을 클릭한 요소
	var seq = button.data('seq'); // data-seq 값 가져오기
	$('#cancelSellerBtn').data('seq', seq); // 삭제 버튼에 seq 값을 저장
});

$(document).on('click', '#setSellerBtn', function(event) {
    var button = $(event.currentTarget); // 클릭된 버튼
    var seq = button.data('seq'); // data-seq 값 가져오기
    $('#setSellerConfirmBtn').data('seq', seq); // #setSellerBtn에 seq 설정
	
	console.log("seq : " + seq);
});

$(document).on('click', '#cancelSellerBtn', function(event) {
    var button = $(event.currentTarget); // 클릭된 버튼
    var seq = button.data('seq'); // data-seq 값 가져오기
    $('#cancelSellerConfirmBtn').data('seq', seq); // #cancelSellerBtn에 seq 설정
});


$(document).on('click', '#setSellerConfirmBtn', function() {
	memberSeq = "";
	memberSeq =  $(this).data('seq'); // data-seq 가져오기
	
	$.ajax({
		type: 'POST',
		url: '/api/members/setSeller',
		data: { memberSeq: memberSeq },
		success : function(response) {
			showModal("전환 되었습니다.");
			refresh();
		},
		error : function (error) {
			console.error('데이터를 전송하는데 실패했습니다.')
		},
		complete: function() {
	           isRequestInProgress = false; // 요청 완료 후 플래그 초기화
	    }
	});//ajax
});

$(document).on('click', '#cancelSellerConfirmBtn', function() {
	memberSeq = "";
	memberSeq =  $(this).data('seq'); // data-seq 가져오기
	
	$.ajax({
		type: 'POST',
		url: '/api/members/cancelSeller',
		data: { memberSeq: memberSeq },
		success : function(response) {
			showModal("전환 취소 되었습니다.");
			refresh();
		},
		error : function (error) {
			console.error('데이터를 전송하는데 실패했습니다.')
		},
		complete: function() {
	           isRequestInProgress = false; // 요청 완료 후 플래그 초기화
	    }
	});//ajax
});

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
		row.append($('<td>').text(member.memberSeq).attr('data-seq', member.memberSeq));
        row.append($('<td>').text(member.id));
        row.append($('<td>').text(member.name));
        row.append($('<td>').text(member.email || ''));
        row.append($('<td>').text(member.phone || ''));
        row.append($('<td>').text(member.gender || ''));
        row.append($('<td>').text(formatDate(member.registerDate)));
		
		// 조건에 따라 버튼 추가
		const actionCell = $('<td>');
		if (member.role !== 'SELLER') {
		    actionCell.append(
		        $('<button>')
		            .attr('type', 'button')
		            .attr('id', 'setSellerBtn')
					.attr('data-seq', member.memberSeq) // data-seq 추가
		            .attr('data-name', member.role)
					.attr('data-bs-toggle', 'modal')
					.attr('data-bs-target', '#confirmModal')
		            .addClass('custom-btn')
		            .text('전환')
		    );
		} else {
		    actionCell.append(
		        $('<button>')
		            .attr('type', 'button')
		            .attr('id', 'cancelSellerBtn')
					.attr('data-seq', member.memberSeq) // data-seq 추가
		            .attr('data-name', member.role)
					.attr('data-bs-toggle', 'modal')
					.attr('data-bs-target', '#cancelModal')
		            .addClass('custom-btn')
		            .text('전환취소')
		    );
		}
		row.append(actionCell);
		
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




function refresh() {
	// 모달 닫기 
	closeModal();
	
	loadBodyForm('memberList');
}


function closeModal() {
    // 모달 닫기
    //$(".confirmModal").modal("hide");

    // Backdrop 제거
    $(".modal-backdrop").remove();

    // Body 상태 초기화
    //$("body").removeClass("modal-open").removeAttr("style");
	$('body').css('overflow', 'auto'); // 스크롤 복구
}