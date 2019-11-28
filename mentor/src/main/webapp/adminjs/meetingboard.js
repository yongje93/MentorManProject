/**
 * 
 */

//전체 체크박스 체크/논 체크
$('#all').click(function(){
		if($('#all').prop('checked'))
			$('.check').prop('checked',true);
		else
			$('.check').prop('checked',false);
});

//글삭제
$('.meetingListBtn').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		alert("항목을 선택하세요.");
	else{
		if(confirm("정말로 삭제하시겠습니까?")){
			$.ajax({
				type : 'post',
				url : '/mentor/adminboard/adminmeetingboardDelete',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : 'check='+check,
				success : function(){
					location.href="/mentor/adminboard/adminmeetingboardList";
				}
			});				
		}
	}
});