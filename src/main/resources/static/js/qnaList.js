//등록시 사용하기 위해 전역변수로 사용.
var qnaSeq = "";
var qnaYn = "";
let isRequestInProgress = false;

$('#confirmBtn').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // 버튼을 클릭한 요소
	var seq = button.data('seq'); // data-seq 값 가져오기
	$('#confirmBtn').data('seq', seq); // 삭제 버튼에 seq 값을 저장
});

function showModal(message) {
    // 메시지를 동적으로 설정
    document.getElementById('modalMessage').textContent = message;

    // 부트스트랩 모달 띄우기
    const Modal = new bootstrap.Modal(document.getElementById('Modal'));
    Modal.show();
}

$(document).on('click', '#qnaReplyBtn', function() {
    qnaSeq =  ""; // 클릭할 때마다 값 초기화
    qnaSeq =  $(this).data('seq'); // data-seq 가져오기
	qnaYn = ""; // 클릭할 때마다 값 초기화
	qnaYn = $(this).data('name');
	
	$.ajax({
		type: 'GET',
		url : `/api/qnas/qnaDetail/${qnaSeq}`,
		contentType : 'application/json',
		success : function(response) {
			$('#playName').val(response.playName);
			$('#memberName').val(response.memberName);
			$('#createdDate').val(response.createdDate);
			$('#title').val(response.title);
			$('#content').val(response.content);
			$('#replyContent').val(response.replyContent);
		},
		error : function (error) {
			console.error('데이터를 가지고 오는데 실패했습니다.')
		}
	});//ajax
}); //click qnaReplyBtn

$(document).on('click', '#confirmBtn', function() {
    const replyContent = document.getElementById('replyContent').value;

    if(!replyContent.trim()) {
    	('답변 내용을 입력하세요.');
    	return;
    }
    
    if(qnaYn != '' && qnaYn == 'checked') {
		if (isRequestInProgress) return; // 중복 요청 방지
	    isRequestInProgress = true;
			
    	$.ajax({
			type: 'POST',
			url : '/api/replys/replyUpdate',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify({
				qnaSeq : qnaSeq,
				content : replyContent
			}),
			success : function(response) {
		    	showModal('답변이 수정되었습니다.');
				refresh();
			},
			error : function (error) {
				console.error('데이터를 전송하는데 실패했습니다.')
			},
			complete: function() {
		           isRequestInProgress = false; // 요청 완료 후 플래그 초기화
		    }
		});//ajax
    } else {
		if (isRequestInProgress) return; // 중복 요청 방지
	    isRequestInProgress = true;
			
		$.ajax({
			type: 'POST',
			url : '/api/replys/replyWrite',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify({
				qnaSeq : qnaSeq,
				content : replyContent
			}),
			success : function(response) {
				showModal("답변이 등록되었습니다.");
				refresh();
			},
			error : function (error) {
				console.error('데이터를 전송하는데 실패했습니다.')
			},
			complete: function() {
		           isRequestInProgress = false; // 요청 완료 후 플래그 초기화
		    }
		});//ajax
    }
});//click qnaReplyBtn


function refresh() {
	// 모달 닫기 
	closeModal();
	
	loadBodyForm('qnaList');
}


function closeModal() {
    // 모달 닫기
    //$(".confirmModal").modal("hide");

    // Backdrop 제거
    $(".modal-backdrop").remove();

    // Body 상태 초기화
    $("body").removeClass("modal-open").removeAttr("style");
}