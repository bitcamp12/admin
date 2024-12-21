function login(userType) {
	// 로그인 버튼 ID 선택
	const $tabName = $('#' + userType + '-tab');

	//전부 비우고 갱신
	$('#admin-username-error').hide();
	$('#admin-password-error').hide();
	$('#admin-match-error').hide();
	$('#seller-username-error').hide();
	$('#seller-password-error').hide();
	$('#seller-match-error').hide();

	// 로그인 중인지 완료 상태인지 확인
	if ($tabName.hasClass('active')) {
		console.log(userType);

		// 로그인 폼의 사용자 이름과 비밀번호 입력 값 가져오기
		const $username = $('#' + userType + '-username');
		const $password = $('#' + userType + '-password');

		// 경고 메시지 요소 가져오기
		const $usernameError = $('#' + userType + '-username-error');
		const $passwordError = $('#' + userType + '-password-error');
		const $matchError = $('#' + userType + '-match-error');

		// 입력이 비어있으면 경고 메시지 표시
		let valid = true;

		if (!$username.val().trim()) {
			$usernameError.show();
			valid = false;
		} else {
			$usernameError.hide();
		}

		if (!$password.val().trim()) {
			$passwordError.show();
			valid = false;
		} else {
			$passwordError.hide();
		}
		/*
		// 입력이 모두 유효하면 로그인 처리
		   if (valid) {
		     const loginData = {
		       username: $username.val(),
		       password: $password.val()
		     };

		     $.ajax({
		       type: 'POST',
		       url: '/api/secure/login/' + userType,
		       contentType: 'application/json',
		       data: JSON.stringify(loginData),
		       success: function (response) {
		         alert('성공');
		         window.location.href = '/secure/' + userType + '/index';
		       },
		       error: function (xhr, status, error) {
		         $matchError.show();
		       }
		     });
			 */
		// 입력이 모두 유효하면 로그인 처리
		if (valid) {
			
			const data = new FormData();
			data.append('username', $username.val());
			data.append('password', $password.val());

			$.ajax({
					//회원가입 진행
					type: 'post',
					url : '/api/secure/login',
					data : data,
					processData: false, // 데이터를 쿼리 문자열로 변환하지 않음
					contentType: false, // contentType을 설정하지 않음 (multipart/form-data로 자동 처리됨)
					success : function(response){
						alert('로그인이 성공하였습니다!');
						window.location.href = '/secure/'+userType+'/index';
					},
					error : function(xhr) {
						$matchError.show();
					}
				});
			}
	}
}
					
