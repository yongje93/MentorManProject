// 모임 신청하기 유효성
$('#participationWriteBtn').click(function(){
	$('#nameDiv').empty();
	$('#emailDiv').empty();
	$('#select_mentorDiv').empty();
	$('#addressDiv').empty();
	
	if($('#mentee_name').val() == '') {
		$('#nameDiv').text('이름을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentee_name').focus();
	} else if($('#mentee_email').val() == '') {
		$('#emailDiv').text('이메일을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentee_email').focus();
	} else if($('#mentor_name').val() == '') {
		$('#select_mentorDiv').text('멘토를 선택해주세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentor_name').focus();
	} else if($('#participation_address').val() == '') {
		$('#addressDiv').text('거주지를 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#participation_address').focus();
	} else {
		$.ajax({
			type: 'post',
			url: '/mentor/participation/participationWrite',
			data: $('#participationWriteForm').serialize(),
			success: function(){
				alert('신청완료');
				location.href='/mentor/participation/order';
			}, 
			error: function(error){
				console.log(error);
			}
		});	
	}
});