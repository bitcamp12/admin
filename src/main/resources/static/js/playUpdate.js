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