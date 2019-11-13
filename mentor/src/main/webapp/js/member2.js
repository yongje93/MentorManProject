// 회원가입 정규 표현식
var userIdCheck = RegExp(/^[A-Za-z0-9_\-]{5,20}$/);
var passwdCheck = RegExp(/^[a-zA-Z0-9]{8,12}$/);
var nameCheck = RegExp(/^[가-힣]{2,6}$/);
var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);

// 회원가입 유효성 검사
$('#memberWriteBtn').click(function(){
	$("#member_nameDiv").empty();
	$("#member_nicknameDiv").empty();
	$("#member_emailDiv").empty();
	$("#member_pwdDiv").empty();
	$("#member_repwdDiv").empty();
	
	// 이름 공백 확인
	if($('#member_name').val() == '') {
		$('#member_nameDiv').text('이름을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_name').focus();
	}// 이름 유효성 검사
	else if(!nameCheck.test($('#member_name').val())) {
		$('#member_nameDiv').text('형식에 맞게 입력해주세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_name').focus();
	} // 비밀번호 공백 확인
	else if($('#member_pwd').val() == '') {
		$('#member_pwdDiv').text('비밀번호를 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_pwd').focus();
	}// 비밀번호 유효성 검사
	else if(!passwdCheck.test($('#member_pwd').val())) {
		$('#member_pwdDiv').text('비밀번호는 8자~12자리 이하입니다').css('color', 'tomato').css('font-size','8pt');
		$('#member_pwd').focus();
	}// 비밀번호 확인 공백 확인
	else if($('#member_repwd').val() == '') {
		$('#member_repwdDiv').text('비밀번호를 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_repwd').focus();
	}// 비밀번호 확인 유효성 검사
	else if(!passwdCheck.test($('#member_repwd').val())) {
		$('#member_repwdDiv').text('비밀번호는 8자~12자리 이하입니다').css('color', 'tomato').css('font-size','8pt');
		$('#member_repwd').focus();
	}// 비밀번호 같은지 확인
	else if($('#member_pwd').val() != $('#member_repwd').val()) {
		$('#member_repwdDiv').text('비밀번호가 일치하지 않습니다').css('color', 'tomato').css('font-size','8pt');
		$('#member_repwd').focus();
	} else {
		$('#writeForm').submit();
	}
	
});

// 닉네임 중복 검사
$('#member_nickname').focusout(function(){
	if($('#member_nickname').val() == '') {
		$('#member_nicknameDiv').text('닉네임을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_nickname').focus();
	} else if(!nickNameCheck.test($('#member_nickname').val())) {
		$('#member_nicknameDiv').text('닉네임은 2~8자 입력 가능합니다').css('color', 'tomato').css('font-size','8pt');
		$('#member_nickname').focus();
	} else {
		$.ajax({
			type: 'post',
			url: '/mentor/member/writeNicknamecheck',
			data: {'member_nickname': $('#member_nickname').val()},
			dataType:'text',
			success: function(data){
					if(data=="exist"){
						$('#member_nicknameDiv').text('사용가능한 닉네임입니다').css('color', 'blue').css('font-size','8pt');
					} else if(data=="not_exist"){
						$('#member_nicknameDiv').text('입력하신 닉네임은 사용중인 닉네임 입니다').css('color', 'tomato').css('font-size','8pt');
						$('#member_nickname').focus();
					}
				},
				error: function(e){
					conlose.log(e);
				}
		});
	}
});

// 이메일 중복 검사
$('#member_email').focusout(function(){
	// 이메일 공백 확인
	 if($('#member_email').val() == '') {
		$('#member_emailDiv').text('이메일을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_email').focus();
	}// 이메일 유효성 검사
	else if(!emailCheck.test($('#member_email').val())) {
		$('#member_emailDiv').text('형식에 맞게 이메일을 입력해주세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_email').focus();
	} else {
		$.ajax({
			type: 'post',
			url: '/mentor/member/writeEmailCheck',
			data: {'member_email': $('#member_email').val()},
			dataType:'text',
			success: function(data){
					if(data=="email_ok"){
						$('#member_emailDiv').text('사용가능한 이메일입니다').css('color', 'blue').css('font-size','8pt');
					} else if(data=="email_fail"){
						$('#member_emailDiv').text('입력하신 이메일은 사용중인 이메일 입니다').css('color', 'tomato').css('font-size','8pt');
						$('#member_email').focus();
					}
				},
				error: function(e){
					conlose.log(e);
				}
		});
	}
});

// 로그인 유효성검사
$('#loginBtn').click(function(){
	$('#memberEmailDiv').empty();
	$('#memberPwdDiv').empty();
	$('#loginResultDiv').empty();
	
	// 이메일 공백 확인
	if($('#member_email_Login').val() == '') {
		$('#memberEmailDiv').text('이메일을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_email_Login').focus();
	}// 이메일 유효성 검사
	else if(!emailCheck.test($('#member_email_Login').val())) {
		$('#memberEmailDiv').text('형식에 맞게 이메일을 입력해주세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_email_Login').focus();
	}// 비밀번호 공백 확인
	else if($('#member_pwd').val() == '') {
		$('#memberPwdDiv').text('비밀번호를 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#member_pwd').focus();
	}// 비밀번호 유효성 검사
	else if(!passwdCheck.test($('#member_pwd').val())) {
		$('#memberPwdDiv').text('비밀번호는 8자~12자리 이하입니다').css('color', 'tomato').css('font-size','8pt');
		$('#member_pwd').focus();
	} 
	else {
		$.ajax({
			type: 'post',
			url: '/mentor/member/login',
			data: {'member_email' : $('#member_email_Login').val() , 'member_pwd' : $('#member_pwd_Login').val() },
			dataType: 'text',
			success: function(data){
				if (data == 'login_ok') {
					location.href = '/mentor/main/index'
				} else if (data == 'login_fail') {
					$('#loginResultDiv').text('아이디 또는 비밀번호가 틀립니다').css('color', 'red')
				}
			},
			error:function(e){
				conlose.log(e);
			}
		});
	}
});
