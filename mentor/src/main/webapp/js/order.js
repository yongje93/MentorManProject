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
		    buyer_tel: $('#mentee_tel').val()
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

                	for(var i in meetingboard_list) {          
		                var AlarmData = {
		                		"myAlarm_receiverEmail" : mentor_list[i],
		                		"myAlarm_callerNickname" : rsp.buyer_name,
		                		"myAlarm_title" : "모임 알림",
		                		"myAlarm_content" :  rsp.buyer_name + "님이 모임을 신청했습니다. <a type='external' href='/mentor/participation/participationView?mseq="+ meetingboard_list[i] +"&pseq="+ participation_list[i] +"'>신청서 보기</a>"
		                };
                		                
		                //모임 결제 알림 DB저장
		                $.ajax({
		                	type : 'post',
		                	url : '/mentor/member/saveAlarm',
		                	data : JSON.stringify(AlarmData),
		                	contentType: "application/json; charset=utf-8",
		                	dataType : 'text',
		                	success : function(data){
		                		console.log(data);
		    	                // socket에 보내기
		    	                if(socket) {
		    	                		// apply, 구매한사람, 멘토이메일, 모임 번호, 신청 번호
		    	                		let socketMsg = "apply," + rsp.buyer_name + "," + mentor_list[i] + "," + meetingboard_list[i] + "," + participation_list[i];              		
		    	                		console.log("socketMessage : " + socketMsg);
		    	                		socket.send(socketMsg);
		    	                }
		                	},
		                	error : function(err){
		                		console.log(err);
		                	}
		                }); 
                	}
	            } else {
	                var msg = '결제에 실패하였습니다.' + '\n';
	                msg += rsp.error_msg + '\n';
	                alert(msg);
	    	 }
	    });
	    
	}
});

//기간 설정
function setPeriod(obj, type) {
	$("#startDate").val('');
	$("#endDate").val('');
	
	// 기간 부분 조건에 따라 Setting
	var now = new Date();

	var year= now.getFullYear();
	var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);
	var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

	var now_date = year + '/' + mon + '/' + day;
	var hidden_now_date = year + '-' + mon + '-' + day;

	if(type != "") {
		var start_date = setStartDate(type);
		var a_date = start_date.split("|");

		$("#endDate").val(now_date);
		$("#startDate").val(a_date[0]);
	}
}

//시작 기간 얻기
function setStartDate(type) {
	var time_val = "";
	var now = new Date();
	if(type == "1week") {
		var time_val = now.getTime() - (7 * 24 * 60 * 60 * 1000);
	} else if(type == "1month") {
		var time_val = now.getTime() - (30 * 24 * 60 * 60 * 1000);
	} else if(type == "3month") {
		var time_val = now.getTime() - (91 * 24 * 60 * 60 * 1000);
	}

	if(time_val != "") {
		now.setTime(time_val);
	}

	var year= now.getFullYear();
	var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);
	var day = now.getDate() > 9 ? '' + now.getDate() : '0' + now.getDate();

	return year + '/' + mon + '/' + day + "|" + year + '-' + mon + '-' + day;
}

// 조회 버튼
$("#orderSearchBtn").click(function(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var option = $("#condition option:selected").val();
	
	var startDateVal = startDate.replace(/\//gi, "");
	var endDateVal = endDate.replace(/\//gi, "");
	
	if(startDate == '' && endDate != '') {
		var toastTop = app.toast.create({
	           text: '시작일을 입력하세요',
	           position: 'center',
	           closeTimeout: 2000
	       });
	       toastTop.open();
	       $("#startDate").focus();
	} else if(endDate == '' && startDate != '') {
		var toastTop = app.toast.create({
	           text: '종료일을 입력하세요',
	           position: 'center',
	           closeTimeout: 2000
	       });
	       toastTop.open();
	       $("#endDate").focus();
	} else if(startDateVal > endDateVal) {
		
		var toastTop = app.toast.create({
	           text: '종료일이 잘못되었습니다',
	           position: 'center',
	           closeTimeout: 2000
	       });
	       toastTop.open();
	       $("#endDate").focus();
	} else {
		//alert(startDateVal+'시작'+endDateVal+'끝'+option+'조건');
		
		$.ajax({
			type: 'post',
			url: '/mentor/mentee/orderHistorySearch',
			data: {'startDate' : startDateVal, 'endDate' : endDateVal, 'option' : option, 'pg' : '1'},
			dataType: 'json',
			success: function(data){
				//alert(data.orderHistorySearchList);
				//alert(data.totalSearchHistory);
				
				$('#historyTable > tbody').empty();
				$('.pagination-block').empty();
				
				$.each(data.orderHistorySearchList, function(index, items){
					var today = new Date();
					
					var parseDate = items.meetingboard_day;
					var yyyy = parseDate.substr(0,4);
					var mm = parseDate.substr(5,2);
					var dd = parseDate.substr(8,2);
					
					var meetingday = new Date(yyyy, mm-1, dd);
					var nowDays = today.getTime() / (1000*60*60*24);
					var oldDays = meetingday.getTime() / (1000*60*60*24) + 1;
					
					var orderDate = items.order_date;
					
					var price = items.meetingboard_price.toLocaleString();
					
					var order_state = '';
					
					if(items.order_flag == 0) {
						order_state = '(주문취소)';
					} else if(items.order_flag == 1) {
						if(today >= meetingday) {
							if(items.review_seq == null) {
								order_state = `<div class="btn-set btn-parents">
													<button type="button" class="button" onclick="location.href='/mentor/mentee/meetingReviewWriteForm?seq=${items.meetingboard_seq}'" style="font-size: 11px;">수강후기</button>
												</div>`;
							} else {
								order_state = `<div class="btn-set btn-parents">
													<span>(작성완료)</span>
												</div>`;
							}
						} else {
							order_state = `<div class="btn-set btn-parents">
												<span>(수강전)</span>
											</div>`;
						}
					}
					
					var order_cancel = '';
					
					if(items.order_flag == 1) {
						if((oldDays - nowDays) >= 2) {
							order_cancel = `<div class="btn-set btn-parents">
												<button type="button" class="button" onclick="paymentCancel('${items.meetingboard_seq}','${items.order_id}','${items.meetingboard_price}','${items.participation_seq}')" style="font-size: 11px;">수강취소</button>
											</div>`;
						} else if((oldDays - nowDays) < 2) {
							order_cancel = `<div class="btn-set btn-parents">
												<span></span>
											</div>`;
						}
					}
					
					let orderHistory = `
						<tr>
							<td>
								<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${items.meetingboard_seq}" target="_blank">
									${items.meetingboard_title}
								</a>
							</td>
							<td>
								${orderDate}
							</td>
							<td>
								<a type="external" href="/mentor/participation/paymentComplete?order_id=${items.order_id}" style="color: black;">
									${items.order_id}
								</a>
							</td>
							<td>
								${price}원
							</td>
							<td>
								${order_state}
							</td>
							<td>
								${order_cancel}
							</td>
						</tr>
					`;
					
					$('#historyTable > tbody').append(orderHistory);
				});
				
				$('.pagination-block').append(data.orderHistoryPaging.pagingHTML);
			},
			error: function(err){
				console.log(err);
			}
		});
	}
	
	
});

// 주문내역 페이징
function orderHistoryPaging(page){
	var pg = page;
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var option = $("#condition option:selected").val();
	
	var startDateVal = startDate.replace("/", "");
	var endDateVal = endDate.replace("/", "");
	
	$.ajax({
		type: 'post',
		url: '/mentor/mentee/orderHistorySearch',
		data: {'startDate' : startDateVal, 'endDate' : endDateVal, 'option' : option, 'pg' : pg},
		dataType: 'json',
		success: function(data){
			//alert(data.orderHistorySearchList);
			//alert(data.totalSearchHistory);
			
			$('#historyTable > tbody').empty();
			$('.pagination-block').empty();
			
			$.each(data.orderHistorySearchList, function(index, items){
				var today = new Date();
				
				var parseDate = items.meetingboard_day;
				var yyyy = parseDate.substr(0,4);
				var mm = parseDate.substr(5,2);
				var dd = parseDate.substr(8,2);
				
				var meetingday = new Date(yyyy, mm-1, dd);
				var nowDays = today.getTime() / (1000*60*60*24);
				var oldDays = meetingday.getTime() / (1000*60*60*24) + 1;
				
				var orderDate = items.order_date;
				
				var price = items.meetingboard_price.toLocaleString();
				
				var order_state = '';
				
				if(items.order_flag == 0) {
					order_state = '(주문취소)';
				} else if(items.order_flag == 1) {
					if(today >= meetingday) {
						if(items.review_seq == null) {
							order_state = `<div class="btn-set btn-parents">
												<button type="button" class="button" onclick="location.href='/mentor/mentee/meetingReviewWriteForm?seq=${items.meetingboard_seq}'" style="font-size: 11px;">수강후기</button>
											</div>`;
						} else {
							order_state = `<div class="btn-set btn-parents">
												<span>(작성완료)</span>
											</div>`;
						}
					} else {
						order_state = `<div class="btn-set btn-parents">
											<span>(수강전)</span>
										</div>`;
					}
				}
				
				var order_cancel = '';
				
				if(items.order_flag == 1) {
					if((oldDays - nowDays) >= 2) {
						order_cancel = `<div class="btn-set btn-parents">
											<button type="button" class="button" onclick="paymentCancel('${items.meetingboard_seq}','${items.order_id}','${items.meetingboard_price}','${items.participation_seq}')" style="font-size: 11px;">수강취소</button>
										</div>`;
					} else if((oldDays - nowDays) < 2) {
						order_cancel = `<div class="btn-set btn-parents">
											<span></span>
										</div>`;
					}
				}
				
				let orderHistory = `
					<tr>
						<td>
							<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${items.meetingboard_seq}" target="_blank">
								${items.meetingboard_title}
							</a>
						</td>
						<td>
							${orderDate}
						</td>
						<td>
							<a type="external" href="/mentor/participation/paymentComplete?order_id=${items.order_id}" style="color: black;">
								${items.order_id}
							</a>
						</td>
						<td>
							${price}원
						</td>
						<td>
							${order_state}
						</td>
						<td>
							${order_cancel}
						</td>
					</tr>
				`;
				
				$('#historyTable > tbody').append(orderHistory);
			});
			
			$('.pagination-block').append(data.orderHistoryPaging.pagingHTML);
		},
		error: function(err){
			console.log(err);
		}
	});
}