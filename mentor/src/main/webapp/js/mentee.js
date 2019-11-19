$('#menteeUser_Save').on('click', function(){
	$('#member_name_error').empty();
	$('#member_nickname_error').empty();
	if($('#member_name').val() == ''){
		$('#member_name').focus();
		$('#member_name_error').text('이름을 입력하세요').css('color','red');
		$('#member_name_error').css('font-size','8pt');
	}else if($('#member_nickname').val() == ''){
		$('#member_nickname').focus();
		$('#member_nickname_error').text('닉네임을 입력하세요').css('color','red');
		$('#member_nickname_error').css('font-size','8pt');
	}else{
		var menteeUserSetting = document.menteeUserSetting
		menteeUserSetting.setAttribute('action', '/mentor/mentee/mentorUserModify');
		menteeUserSetting.submit();
		return;
	}
});
//학생 info 입력 버튼
$('#menteeStudentInsert_btn').on('click', function(){
	menteeStudent();
});
//학생 info 수정 버튼
$('#menteeStudentModify_btn').on('click', function(){
	menteeStudent();
});

function menteeStudent(){
	$('#menteeStudent_major_error').empty();
	$('#menteeStudent_state_error').empty();
	$('#menteeStudent_spec_error').empty();
	
	if($('#menteeStudent_major').val()==''){
		$('#menteeStudent_major_error').text('당신의 전공은 무엇인가요?').css('color','red');
		$('#menteeStudent_major_error').css('font-size','8pt');
		$('#menteeStudent_major').focus();
	}else if($('#menteeStudent_state').val()==''){
		$('#menteeStudent_state_error').text('재학/졸업을 선택해주세요').css('color','red');
		$('#menteeStudent_state_error').css('font-size','8pt');
		$('#menteeStudent_state').focus();
	}else if($('#menteeStudent_spec').val()==''){
		$('#menteeStudent_spec_error').text('현재 스펙을 자세히 적어주세요').css('color', 'red');
		$('#menteeStudent_spec_error').css('font-size','8pt');
		$('#menteeStudent_spec').focus();
	}else{
		$.ajax({
			type: 'post',
			url: '/mentor/mentee/menteeStudentInput',
			data: $('#menteeStudentProfile').serialize(),
			success: function(data){
				location.href='/mentor/mentee/menteeStudentProfile';
			},
			error: function(){
				alert('에러');
			}
		});
	}
}
//직장인 info 입력 버튼
$('#menteeEmployeeInsert_btn').on('click', function(){
	menteeEmployee()
});
//직장인 info 수정 버튼
$('#menteeEmployeeModify_btn').on('click', function(){
	menteeEmployee()
});

function menteeEmployee(){
	$('#menteeEmployee_year_error').empty();
	$('#menteeEmployee_final_error').empty();
	$('#menteeEmployee_career_error').empty();
	if($('#menteeEmployee_year').val()==''){
		$('#menteeEmployee_year_error').text('연차를 입력해주세요').css('color','red');
		$('#menteeEmployee_year_error').css('font-size','8pt');
		$('#menteeEmployee_year').focus();
	}else if($('#menteeEmployee_final').val()==''){
		$('#menteeEmployee_final_error').text('최종학력을 입력해주세요').css('color','red');
		$('#menteeEmployee_final_error').css('font-size','8pt');
		$('#menteeEmployee_final').focus();
	}else if($('#menteeEmployee_career').val()==''){
		$('#menteeEmployee_career_error').text('주요 경력사항을 입력해주세요').css('color','red');
		$('#menteeEmployee_career_error').css('font-size','8pt');
		$('#menteeEmployee_career').focus();
	}else {
		$.ajax({
			type: 'post',
			url: '/mentor/mentee/menteeEmployeeInput',
			data: $('#menteeEmployee_profile').serialize(),
			success: function(data){
				location.href='/mentor/mentee/menteeEmployeeProfile';
			},
			error: function(){
				alert('에러');
			}
		});
	}
}

//패스워드 변경
$('#menteePassword_btn').on('click', function(){
	
	$('#currentPassword_error').empty();
	$('#member_pwd_error').empty();
	$('#member_pwd_check_error').empty();
	
	if($('#currentPassword').val()==''){
		$('#currentPassword_error').text('현재 패스워드를 입력해주세요').css('color','red');
		$('#currentPassword_error').css('font-size','8pt');
		$('#currentPassword').focus();
	}else if($('#member_pwd').val()==''){
		$('#member_pwd_error').text('새 비밀번호를 입력해주세요').css('color','red');
		$('#member_pwd_error').css('font-size','8pt');
		$('#member_pwd').focus();
	}else if($('#member_pwd_check').val() != $('#member_pwd').val()){
		$('#member_pwd_check_error').text('비밀번호가 일치하지 않습니다.').css('color','red');
		$('#member_pwd_check_error').css('font-size','8pt');
		$('#member_pwd_check').focus();
	}else if($('#member_pwd').val().length < 8 || $('#member_pwd').val().length > 15){
	    $('#member_pwd_error').text('비밀번호는 8자~15자리 이하입니다.').css('color','red');
	    $('#member_pwd_error').css('font-size','8pt');
	    $('#member_pwd').focus();
	} else {
		$.ajax({
			type: 'post',
			url:'/mentor/mentee/menteePasswordCheck',
			data: 'currentPassword='+$('#currentPassword').val(),
			dataType: 'text',
			success: function(data){
				if(data=="ok"){
					$.ajax({
						type: 'post',
						url:'/mentor/mentee/menteePasswordSave',
						data: {'member_pwd':$('#member_pwd').val()},
						success: function(){
							location.href='/mentor/member/logout';
						},
						error: function(){
							alert('에러');
						}
					});
				}else if(data=="no"){
					$('#currentPassword_error').text('현재 패스워드가 일치하지 않습니다.').css('color','red');
					$('#currentPas6	sword_error').css('font-size','8pt');
					$('#currentPassword').focus();
				}
			},
			error: function(){
				alert('에러');
			}
		});
	}
});

// 모임 작성 후기
$('#reviewWriteBtn').click(function(){
	var content = $("#review_content").val();
	if($('#review_content').val() == '') {
		var toastTop = app.toast.create({
        	text: '모임 후기를 작성해주세요.',
         	position: 'top',
         	closeButton: true,
       });
       toastTop.open();
	} else if(content.length < 15) {
		var toastTop = app.toast.create({
        	text: '모임 후기는 15자 이상 작성해주세요.',
         	position: 'top',
         	closeButton: true,
       });
       toastTop.open();
	} else {
		$('#reviewWriteForm').submit();
	}
});

