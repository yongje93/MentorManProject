$('#essay_write_btn').on('click', function(){
	$.ajax({
		type : 'post',
		url : '/mentor/essayboard/essayboardDelete'
	});
});