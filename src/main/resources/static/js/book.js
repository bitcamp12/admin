var bookSeq = "";

function memberPaging(page){
	if($('#value').val() == '')
		location.href = "/member/list?page=" + (page-1);
	else {
		//let columnName = $('#columnName').val();
		//let value = $('#value').val();
		//location.href = "/member/getSearchList?columnName="+columnName+"&value="+value+"&page=" + (page-1);
		$('#page').val(page-1);
		$('#searchListBtn').trigger('click');
	}
}

function showModal(message) {
	document.getElementById('modalMessage').textContent = message;
	const Modal = new bootstrap.Modal(document.getElementById('Modal'));
	Modal.show();
}

$('#refundBtn').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // 버튼을 클릭한 요소
	var seq = button.data('seq'); // data-seq 값 가져오기
	$('#refundBtn').data('seq', seq); // 삭제 버튼에 seq 값을 저장
	
	console.log("seq : " + seq);
});

// #refundBtn의 클릭 이벤트
$(document).on('click', '#refundBtn', function(event) {
    var button = $(event.currentTarget); // 클릭된 버튼
    var seq = button.data('seq'); // data-seq 값 가져오기
    $('#refundConfirmBtn').data('seq', seq); // #refundConfirmBtn에 seq 설정
    
    console.log("seq 설정 완료: " + seq);
});


$(document).on('click', '#refundConfirmBtn', function() {
	bookSeq =  ""; // 클릭할 때마다 값 초기화
	bookSeq =  $(this).data('seq'); // data-seq 가져오기
	
	console.log("bookSeq : " + bookSeq);
	
	$.ajax({
		type: 'POST',
		url : '/api/books/bookDelete?bookSeq=' + bookSeq,
		success : function(response) {
			showModal("환불 처리 되었습니다.");
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



function refresh() {
	// 모달 닫기 
	closeModal();
	
	loadBodyForm('bookList');
}


function closeModal() {
    // 모달 닫기
    $(".confirmModal").modal("hide");

    // Backdrop 제거
    $(".modal-backdrop").remove();

    // Body 상태 초기화
    $("body").removeClass("modal-open").removeAttr("style");
}