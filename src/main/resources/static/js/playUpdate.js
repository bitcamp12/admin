$(function(){
	/*
	$.ajax({
		//회원가입 진행
		type: 'post',
		url : '/member/write',
		data : $('#writeForm').serialize(), //변수=값&변수=값...
		success : function(data) {
			// alert
			alert('회원가입 완료');
			location.href='/member/list';
		},
		error : function(e) {
			console.log(e);
   		}
    }); // ajax
	*/
});



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
})();
function previewImage(event) {
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