// 공지 사항 이미지 미리보기 함수
function noticePreviewImage(event) {
	const input = event.target;
	const preview = document.getElementById('imagePreview');

	if (input.files && input.files[0]) {
		const reader = new FileReader();

		reader.onload = function(e) {
			preview.src = e.target.result;
			preview.style.display = 'block'; // 미리보기 이미지 보이게 설정
		};

		reader.readAsDataURL(input.files[0]);
	}
}

// 미리보기 초기화 함수
function noticeClearPreview() {
	const preview = document.getElementById('imagePreview');
	const imageInput = document.getElementById('image');
	preview.src = '';
	preview.style.display = 'none';
	imageInput.value = '';
}	