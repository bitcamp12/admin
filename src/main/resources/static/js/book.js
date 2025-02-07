var bookSeq = "";


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
	closeModal();
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


$(document).ready(function () {
	// 결제 상태 값 한글로 변환.
	payStatusConvert();
	payAmountDisplay();
});


function payAmountDisplay() {
	const payAmount = document.querySelectorAll("td.payAmount")
	
	for (let i = 0; i < payAmount.length; i++) {
	    const cell = payAmount[i];
	    const rawContent = cell.textContent.trim(); // 공백 제거
	    const sanitizedContent = rawContent.replace(/[^0-9]/g, ""); // 숫자만 남기기
	    const amount = parseInt(sanitizedContent, 10); // 숫자로 변환

	    if (!isNaN(amount)) {
	        const formattedAmount = new Intl.NumberFormat("ko-KR").format(amount) + "원";
	        cell.textContent = formattedAmount; // 포맷된 값으로 변경
	    } else {
	        console.warn("Invalid amount:", rawContent); // 디버깅용
	    }
	}
};	
	

// 결제 상태 값 한글로 변환
function payStatusConvert() {
	const payStatus = document.querySelectorAll("td.payStatus")
	
	payStatus.forEach(cell => {
		if(cell.textContent.trim() === 'PAID') {
			cell.textContent = '결제완료';
		} else if(cell.textContent.trim() === 'PENDING') {
			cell.textContent = '결제 전';
		} else if(cell.textContent.trim() === 'REFUND REQUESTED') {
			cell.textContent = '환불요청';
		} else if(cell.textContent.trim() === 'REFUNDED') {
			cell.textContent = '환불완료';
		}
		
	})
};	


function closeModal() {
    // 모달 닫기
    //$(".confirmModal").modal("hide");

    // Backdrop 제거
    $(".modal-backdrop").remove();

    // Body 상태 초기화
    //$("body").removeClass("modal-open").removeAttr("style");
	$('body').css('overflow', 'auto'); // 스크롤 복구
}