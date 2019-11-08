$('#essayboardWriteBtn').on('click', function(){
	$('.title_error_div').empty();
	$('.company_error_div').empty();
	$('.job_code_error_div').empty();
	$('.summernote_error_div').empty();
	
	if ($('#job_code').val() == '0'){
		$('.job_code_error_div').text('직무를 선택해주세요.').css('color', 'red').css('font-size', '8pt');
	} else if ($('#title').val() == '') {
		$('.title_error_div').text('제목을 입력해주세요.').css('color', 'red').css('font-size', '8pt');
	} else if ($('#company').val() == ''){
		$('.company_error_div').text('회사 및 부서를 입력해주세요.').css('color', 'red').css('font-size', '8pt');
	} else if ($('#summernote').val() == ''){
		$('.summernote_error_div').text('내용을 입력해주세요.').css('color', 'red').css('font-size', '8pt');
	} else {
		$('#essayboardWriteForm').submit();		
	}
});