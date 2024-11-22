$(document).ready(function() {

	// 할인율 유효성 체크
	$('#maxDiscountInput').focusout(validateDiscounts);
	$('#minDiscountInput').focusout(validateDiscounts);
	
	// 날짜 유효성 체크
	$('#start_date').focusout(validateDate);
	$('#end_date').focusout(validateDate);

	// 공연시간 유효성 체크
	$('#start_time').focusout(validateTime);
	$('#end_time').focusout(validateTime);
	
	// 할인시간 유효성 체크
	$('#start_dis_time').focusout(validateDisTime);
	$('#end_dis_time').focusout(validateDisTime);
});


// 날짜 유효성 체크
function validateDate() {
	const start_date = document.getElementById('start_date').value; 
	const end_date = document.getElementById('end_date').value; 
	
	const endDateInput = document.getElementById('end_date');
	const endDateFeedback = document.getElementById('end_date_invalid-feedback');
	
	if (start_date >= end_date || end_date == null || end_date === "" ) {
			// 오류 메시지를 추가하고 'is-invalid' 클래스 추가
			endDateFeedback.classList.add('is-invalid');
			endDateFeedback.classList.add('invalid-feedback');
			
			if(end_date != null && end_date != "") {
				endDateFeedback.innerHTML = '공연 시작 날짜보다 빠릅니다.';
			}

			// 입력 필드에도 'is-invalid' 클래스 추가
			endDateInput.classList.add('is-invalid');
			$('#end_date').val(''); // 입력 값을 비웁니다.
		} else {
			// 오류 메시지 초기화 및 'is-invalid' 클래스 제거
			endDateFeedback.innerHTML = ''; // 메시지 비우기
			endDateFeedback.classList.remove('is-invalid');
			
			// 입력 필드의 'is-invalid' 클래스 제거 및 'is-valid' 클래스 추가
			endDateInput.classList.remove('is-invalid');
			endDateInput.classList.add('is-valid');
		}
}


// 할인시간 유효성 체크
function validateDisTime() {
	const start_dis_time = document.getElementById('start_dis_time').value; 
	const end_dis_time = document.getElementById('end_dis_time').value; 

	const endDisTimeInput = document.getElementById('end_dis_time');
	const endDisTimeFeedback = document.getElementById('end_dis_time_invalid-feedback');

	if (start_dis_time >= end_dis_time || end_dis_time == null || end_dis_time === "" ) {
			// 오류 메시지를 추가하고 'is-invalid' 클래스 추가
			endDisTimeFeedback.classList.add('is-invalid');
			endDisTimeFeedback.classList.add('invalid-feedback');
			
			if(end_dis_time != null && end_dis_time != "") {
				endDisTimeFeedback.innerHTML = '할인 시작 시간보다 빠릅니다.';
			}

			// 입력 필드에도 'is-invalid' 클래스 추가
			endDisTimeInput.classList.add('is-invalid');
			$('#end_dis_time').val(''); // 입력 값을 비웁니다.
		} else {
			// 오류 메시지 초기화 및 'is-invalid' 클래스 제거
			endDisTimeFeedback.innerHTML = ''; // 메시지 비우기
			endDisTimeFeedback.classList.remove('is-invalid');
			
			// 입력 필드의 'is-invalid' 클래스 제거 및 'is-valid' 클래스 추가
			endDisTimeInput.classList.remove('is-invalid');
			endDisTimeInput.classList.add('is-valid');
		}
}


// 공연시간 유효성 체크
function validateTime() {
	const start_time = document.getElementById('start_time').value; 
	const end_time = document.getElementById('end_time').value; 

	const endTimeInput = document.getElementById('end_time');
	const endTimeFeedback = document.getElementById('end_time_invalid-feedback');

	if (start_time >= end_time || end_time == null || end_time === "" ) {
			// 오류 메시지를 추가하고 'is-invalid' 클래스 추가
			endTimeFeedback.classList.add('is-invalid');
			endTimeFeedback.classList.add('invalid-feedback');
			
			if(end_time != null && end_time != "") {
				endTimeFeedback.innerHTML = '공연 시작 시간보다 빠릅니다.';
			}

			// 입력 필드에도 'is-invalid' 클래스 추가
			endTimeInput.classList.add('is-invalid');
			$('#end_time').val(''); // 입력 값을 비웁니다.
		} else {
			// 오류 메시지 초기화 및 'is-invalid' 클래스 제거
			endTimeFeedback.innerHTML = ''; // 메시지 비우기
			endTimeFeedback.classList.remove('is-invalid');
			
			// 입력 필드의 'is-invalid' 클래스 제거 및 'is-valid' 클래스 추가
			endTimeInput.classList.remove('is-invalid');
			endTimeInput.classList.add('is-valid');
		}
}



// 최소 및 최대 할인율 입력값 제한
function limitDiscountInput(event) {
    const input = event.target; // 이벤트가 발생한 입력 요소
    const value = parseInt(input.value, 10); // 입력 값을 정수로 변환

    // 값이 100 이상이면 99로 제한
    if (value > 99) {
        input.value = 99;
    }

    // 값이 음수라면 0으로 제한
    if (value < 0) {
        input.value = 0;
    }
}


// 할인율 유효성 체크
function validateDiscounts() {
	const minDis = document.getElementById('minDiscountInput').value; 
	const maxDis = document.getElementById('maxDiscountInput').value; 
	const maxDiscountInput = document.getElementById('maxDiscountInput');
    const maxDiscountFeedback = document.getElementById('max_discount_invalid-feedback');

	if (parseFloat(minDis) >= parseFloat(maxDis) || maxDis == null || maxDis === "") {
		// 오류 메시지를 추가하고 'is-invalid' 클래스 추가
		maxDiscountFeedback.classList.add('is-invalid');
		maxDiscountFeedback.classList.add('invalid-feedback');
		
		if(maxDis != null && maxDis != "") {
			maxDiscountFeedback.innerHTML = '최대 할인율은 최소 할인율 보다 작을 수 없습니다.';
		}

		// 입력 필드에도 'is-invalid' 클래스 추가
		maxDiscountInput.classList.add('is-invalid');
		$('#maxDiscountInput').val(''); // 입력 값을 비웁니다.
	} else {
		// 오류 메시지 초기화 및 'is-invalid' 클래스 제거
		maxDiscountFeedback.innerHTML = ''; // 메시지 비우기
		maxDiscountFeedback.classList.remove('is-invalid');
		
		// 입력 필드의 'is-invalid' 클래스 제거 및 'is-valid' 클래스 추가
		maxDiscountInput.classList.remove('is-invalid');
		maxDiscountInput.classList.add('is-valid');
	}
};



// 포스터 이미지 미리보기 
function previewImage() {
    const fileInput = document.getElementById('posterUpload');
    const preview = document.getElementById('posterPreview');
    
    const file = fileInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            preview.style.display = 'block';
        }
        reader.readAsDataURL(file);
        document.getElementById("posterPreviewMsg").innerHTML = "<p style='color: grey;'> 포스터 미리보기 </p>";
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
}
