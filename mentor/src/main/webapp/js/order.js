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
	var meetingboard_list = new Array();
	$("input[name=meetingboard_seq]").each(function(index, item){
		meetingboard_list.push($(item).val());
	});
	
	var participation_list = new Array();
	$("input[name=participation_seq]").each(function(index, item){
		participation_list.push($(item).val());
	});
	
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
		// 아임포트 결제
		var IMP = window.IMP; // 생략가능
	    IMP.init('imp14065552'); // 'iamport' 대신 부여받은 '가맹점 식별코드'를 사용
	    var msg;
	    
	 // IMP.request_pay(param, callback) 호출
	    IMP.request_pay({ // param
	      pg: 'kakaopay',
	      pay_method: 'card',
	      merchant_uid: 'merchant_' + new Date().getTime(),
	      name: '멘토맨 모임 결제',
	      amount: $('#total').val(),
	      buyer_email: $('#mentee_email').val(),
	      buyer_name: $('#mentee_name').val(),
	      buyer_tel: $('#mentee_tel').val(),
	      m_redirect_url:'https://www.iamport.kr/demo'	      
	    }, function(rsp) {
	    	 if ( rsp.success ) {
	                $.ajax({
	                	type: 'post',
	                	url: '/mentor/participation/orderComplete',
	                	data: JSON.stringify({ order_id : rsp.merchant_uid,
                							   order_price : rsp.paid_amount,
                							   mentee_email : rsp.buyer_email,
                							   mentee_name : rsp.buyer_name,
                							   mentee_tel : rsp.buyer_tel, 
                							   meetingboard_list : meetingboard_list,
                							   participation_list : participation_list }),
	                	contentType: 'application/json; charset=utf-8',
	                	success: function(data){
	                		location.href="/mentor/participation/paymentComplete?order_id="+rsp.merchant_uid;
	                	},
	                	error: function(error){
	                		console.log(error);
	                	}
	                });
	                
	            } else {
	                var msg = '결제에 실패하였습니다.' + '\n';
	                msg += rsp.error_msg + '\n';
	                alert(msg);
	            }
	    	 
	    });
	}
});