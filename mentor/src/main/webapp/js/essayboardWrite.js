$('#essayboardWriteBtn').on('click', function(){
	$('.essayboard_title_error_div').empty();
	$('.job_code_error_div').empty();
	$('.essayboard_content_error_div').empty();
	
	if ($('#job_code').val() == ''){
		$('.job_code_error_div').text('직무를 선택해주세요.').css('color', 'red').css('font-size', '8pt');
	} else if ($('#title').val() == '') {
		$('.essayboard_title_error_div').text('제목을 입력해주세요.').css('color', 'red').css('font-size', '8pt');
	} else if ($('#summernote').val() == ''){
		$('.essayboard_content_error_div').text('내용을 입력해주세요.').css('color', 'red').css('font-size', '8pt');
	} else {
		$.ajax({
			type : 'post',
			url : '/mentor/essayboard/essayboardWrite',
			data : $('#essayboardWriteForm').serialize(),
			success : function(data){
				location.href="/mentor/essayboard/essayboardList";
			},
			error : function(){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});		
	}
});