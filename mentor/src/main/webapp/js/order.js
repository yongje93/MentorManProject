// 모임 삭제버튼
function orderRemove(seq) {	
	var toastWithCallback = app.toast.create({
		text: '수강을 취소하시겠습니까?',
		position: 'center',
		closeButton: true,
		on: {
			close: function() {
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
			}
		}
	});	
	toastWithCallback.open();
}

// 결제하기 버튼
$('#orderBtn').click(function(){
	// 모임 리스트
	var meetingboard_list = new Array();
	$("input[name=meetingboard_seq]").each(function(index, item){
		meetingboard_list.push($(item).val());
	});
	// 신청 리스트
	var participation_list = new Array();
	$("input[name=participation_seq]").each(function(index, item){
		participation_list.push($(item).val());
	});
	// 멘토 리스트
	var mentor_list = new Array();
	$("input[name=mentor_email]").each(function(index, item){
		mentor_list.push($(item).val());
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
		    merchant_uid: 'men_' + new Date().getTime(),
		    name: '멘토맨 모임 결제',
		    amount: $('#total').val(),
		    buyer_email: $('#mentee_email').val(),
		    buyer_name: $('#mentee_name').val(),
		    buyer_tel: $('#mentee_tel').val(),
		    m_redirect_url:'https://www.iamport.kr/demo'	      
	    }, function(rsp) {
	    	 if (rsp.success) {
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
	                
	                // socket에 보내기
	                if(socket) {
	                	for(var i in meetingboard_list) {
	                		// apply, 구매한사람, 멘토이메일, 모임 번호, 신청 번호
	                		let socketMsg = "apply," + rsp.buyer_name + "," + mentor_list[i] + "," + meetingboard_list[i] + "," + participation_list[i];              		
	                		console.log("socketMessage : " + socketMsg);
	                		socket.send(socketMsg);
	                	}
	                }
	                
	                
	            } else {
	                var msg = '결제에 실패하였습니다.' + '\n';
	                msg += rsp.error_msg + '\n';
	                alert(msg);
	            }
	    });
	}
});