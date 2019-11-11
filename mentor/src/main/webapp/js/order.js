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
	      buyer_name: $('#mentee_email').val(),
	      buyer_tel: $('#mentee_tel').val(),
	      buyer_addr: '',
	      buyer_postcode: ''
	    }, function(rsp) {
	        if ( rsp.success ) {
	        	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
	        	jQuery.ajax({
	        		url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
	        		type: 'POST',
	        		dataType: 'json',
	        		data: {
	    	    		imp_uid : rsp.imp_uid,
	    	    		merchant_uid: rsp.merchant_uid
	        		}
	        	}).done(function(data) {
	        		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
	        		if ( everythings_fine ) {
	        			var msg = '결제가 완료되었습니다.';
	        			msg += '\n고유ID : ' + rsp.imp_uid;
	        			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	        			msg += '\결제 금액 : ' + rsp.paid_amount;
	        			msg += '카드 승인번호 : ' + rsp.apply_num;
	        			
	        			alert(msg);
	        		} else {
	        			//[3] 아직 제대로 결제가 되지 않았습니다.
	        			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
	        		}
	        	});
	        } else {
	            var msg = '결제에 실패하였습니다.';
	            msg += '에러내용 : ' + rsp.error_msg;
	            
	            alert(msg);
	        }
	    });
	}
});