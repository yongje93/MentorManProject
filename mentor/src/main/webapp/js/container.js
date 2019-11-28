// 메인 화면 배너
$(document).ready(function(){
	$('.owl-carousel').owlCarousel({
		items: 1,
		loop: true,
		autoplay: true,
		autoplayTimeout: 5000,
		autoplayHoverPause: true,
	});
});

// 메인 화면 에세이 관련 
// 스크랩 버튼을 누를시
$(document).ready(function() {
	$('a[type="externalScrap"]').on('click' , function(){
		if($('#memNickname').val()== ''){
			 var toastTop = app.toast.create({
		            text: '로그인 후 이용 가능 합니다.',
		            position: 'top',
		            closeButton: true,
		          });
		          toastTop.open();
		} else {
			var seq = $(this).closest('div').prev().children().first().val();
			var essayboardEmail = $(this).closest('div').prev().children().first().next().val();
			var essayName = $(this).closest('div').prev().children().first().next().next().val();
			var memberSeq = $(this).closest('div').prev().children().first().next().next().next().val();
			
			if($(this).children().last().val() == 0){
				$("#"+seq).prop("src", "../image/scrapOkImg.png");
				$(this).children().last().val(1);
				
				var toastIcon = app.toast.create({
					  icon: app.theme === 'ios' ? '<i class="fas fa-bookmark fa-3x"></i>' : '<i class="fas fa-bookmark" style="width: 30px; height: 30px;"></i>',
					  text: '',
					  position: 'center',
					  closeTimeout: 2000,
					});
				toastIcon.open();
				
			} else {
				$("#"+seq).prop("src", "../image/scrapNoImg.png");
				$(this).children().last().val(0);
			}
			var scrapFlag = $(this).children().last().val()
			
			// seq , scrap_flag 
			var sendData = {
					'essayboardScrap_es_seq' : seq,
					'scrapFlag' : scrapFlag
				};
			
			$.ajax({
				url : '/mentor/essayboard/essayboardScrap',
				type : 'POST',
				data : sendData,
				dataType : 'text',
				success : function(data) {
					$('#ScrapDiv_'+seq).empty();
					$('#ScrapDiv_'+seq).html(data);
					//스크랩 할때 알림을 보낸다
				if(scrapFlag == 1){
						let memNickname = $('#memNickname').val(); //스크랩을 누른사람
						let nickname = essayName;	   //에세이 작성자
						let receiverEmail = essayboardEmail;     //에세이 작성자 이메일
						let essayboard_seq = seq; //에세이 seq
						//alert(memNickname+',' + nickname +',' + receiverEmail +',' + seq);
						
						if(socket){
							let socketMsg = "scrap," + memNickname +","+ memberSeq +","+ receiverEmail +","+ essayboard_seq;
							console.log("msgmsg : " + socketMsg);
							socket.send(socketMsg);
						}
						var AlarmData = {
								"myAlarm_receiverEmail" : receiverEmail,
								"myAlarm_callerNickname" : memNickname,
								"myAlarm_title" : "스크랩 알림",
								"myAlarm_content" :  memNickname + "님이 <a type='external' href='/mentor/essayboard/essayboardView?pg=1&seq="+essayboard_seq+"&mentors="+ memberSeq +"'>" + essayboard_seq + "</a>번 에세이를 스크랩 했습니다."
						};
						//스크랩 알림 DB저장
						$.ajax({
							type : 'post',
							url : '/mentor/member/saveAlarm',
							data : JSON.stringify(AlarmData),
							contentType: "application/json; charset=utf-8",
							dataType : 'text',
							success : function(data){
								//alert(data);
							},
							error : function(err){
								console.log(err);
							}
						});
					}
				},
				error : function(err){
					console.log('에러');
				}
			});
		}
	});
});