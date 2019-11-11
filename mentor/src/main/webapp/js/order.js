// 모임 삭제버튼
function orderRemove(seq) {
	if(confirm('모임 바구니에서 삭제하시겠습니까?') == true) {
		$.ajax({
			type: 'post',
			url: '/mentor/participation/orderDelete',
			data: {seq: seq},
			success: function(){
				location.href='/mentor/participation/order';
			},
			error: function(error){
				console.log(error);
			}
		});
	} else 
		return false;
}

$('#orderBtn').click(function(){
	$('#mentee_nameDiv').empty();
	$('#mentee_telDiv').empty();
	$('#mentee_emailDiv').empty();
	
	if($('#mentee_name').val() == '') {
		$('#mentee_nameDiv').text('이름을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentee_name').focus();
	} else if($('#mentee_tel').val() == '') {
		$('#mentee_telDiv').text('전화번호를 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentee_tel').focus();
	} else if($('#mentee_email').val() == '') {
		$('#mentee_emailDiv').text('이메일을 입력하세요').css('color', 'tomato').css('font-size','8pt');
		$('#mentee_email').focus();
	} else {
		alert('결제시작');
	}
});