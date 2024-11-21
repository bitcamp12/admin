$(document).ready(function() {
    smartEditor();
	loadDaumPostcodeScript();

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


// 네이버 스마트 에디터 2.0 API 
let oEditors = [];

function smartEditor() {
    console.log("Naver SmartEditor");
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "editorTxt",
        sSkinURI: "/static/smarteditor/SmartEditor2Skin.html",
        fCreator: "createSEditor2"
    });
}

(function() {
    'use strict';
    window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
        Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
	

	// 글쓰기 버튼 클릭 액션 - 폼제출
	$('#playRegisterBtn').click(function(){
		$.ajax({
			type: 'post',
			url: '/playRegisterWrite',
			data: $('#writeForm').serialize(), //변수=값&변수=값...
			success: function(){
				location.href='/seller/index';
			},
			error: function(e){
				console.log(e);
			}
		});//$.ajax
	});
	
})();


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

// Daum 우편번호 API
function daumPostCodeSearch() {
	new daum.Postcode({
	    oncomplete: function(data) {
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수
			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}
			
			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if(data.userSelectedType === 'R'){
			    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
			    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
			        extraAddr += data.bname;
			    }
			    // 건물명이 있고, 공동주택일 경우 추가한다.
			    if(data.buildingName !== '' && data.apartment === 'Y'){
			        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			    }
			    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			    if(extraAddr !== ''){
			        extraAddr = ' (' + extraAddr + ')';
			    }
			    // 조합된 참고항목을 해당 필드에 넣는다.
			    document.getElementById("address").value = extraAddr;

			} else {
			    document.getElementById("address").value = '';
			}
			
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('postcode').value = data.zonecode;
			document.getElementById("address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("address").focus();
			
	    }
	}).open();
}

// Daum 주소 API를 동적으로 로드
function loadDaumPostcodeScript() {
    const script = document.createElement('script'); // 새로운 <script> 태그 생성
    script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js'; // 스크립트 URL 설정
    script.async = true; // 비동기 로드 설정
    script.onload = function () {
        console.log('Daum Postcode API 스크립트가 성공적으로 로드되었습니다.');
        // 여기에서 Daum API 초기화 또는 관련 작업을 수행
    };
    script.onerror = function () {
        console.error('Daum Postcode API 스크립트를 로드하는 동안 오류가 발생했습니다.');
    };
    document.head.appendChild(script); // <head>에 스크립트 태그 추가
}

