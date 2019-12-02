/**
 * 
 */

$(function(){
	if($('#scrapFlag').val() == 1){
		$('#scrapDiv').children().first().addClass('bookmarked fas');
		$('#scrapDiv').children().first().removeClass('far');
	}else if($('#scrapFlag').val() == 0){
		$('#scrapDiv').children().first().removeClass('bookmarked fas');
		$('#scrapDiv').children().first().addClass('far');
	}
});


//스크랩 클릭
$('#scrapDiv').on('click' , function(){
	var scrapTag = $(this).children().first();
	
	if(scrapTag.hasClass('far')){
		scrapTag.addClass('bookmarked fas');
		scrapTag.removeClass('far');
		$('#scrapFlag').val(1);
		
		scrapAjax();
		
	}else{
		scrapTag.removeClass('bookmarked fas');
		scrapTag.addClass('far');
		$('#scrapFlag').val(0);
		
		scrapAjax();
		
	}
	
});


function scrapAjax(){
	var sendData = {
			'essayboardScrap_es_seq' : $('#essayScrapDiv').data('seq'),
			'scrapFlag' : $('#scrapFlag').val()
		};
	$.ajax({
		url : '/mentor/essayboard/essayboardScrap',
		type : 'POST',
		data : sendData,
		dataType : 'text',
		success : function(data) {
			$('#essayScrapDiv').empty();
			$('#essayScrapDiv').html(data);
			
			
			//스크랩 할때 알림을 보낸다
		if($('#scrapFlag').val() == 1){
				let memNickname = $('#memNickname').val(); //스크랩을 누른사람
				let nickname = $('#menseq').data('menseq'); // 닉네임 대신에 멘토seq를 보낸다
				let receiverEmail = $('.mentor-img').data('email');    //에세이 작성자 이메일
				let scrapboard_seq = $('#essayScrapDiv').data('seq'); //에세이 seq 
				
				
				if(socket){
					let socketMsg = "scrap," + memNickname +","+ nickname +","+ receiverEmail +","+ scrapboard_seq;
					console.log("msgmsg : " + socketMsg);
					socket.send(socketMsg);
				}
				var AlarmData = {
						"myAlarm_receiverEmail" : receiverEmail,
						"myAlarm_callerNickname" : memNickname,
						"myAlarm_title" : "스크랩 알림",
						"myAlarm_content" :  memNickname + "님이 <a type='external' href='/mentor/essayboard/essayboardView?pg=1&seq="+scrapboard_seq+"&mentors="+ nickname +"'>" + scrapboard_seq + "</a>번 에세이를 스크랩 했습니다."
				};
				//스크랩 알림 DB저장
				$.ajax({
					type : 'post',
					url : '/mentor/member/saveAlarm',
					data : JSON.stringify(AlarmData),
					contentType: "application/json; charset=utf-8",
					dataType : 'text',
					success : function(data){
						
					},
					error : function(err){
						console.log(err);
					}
				});
				
			}
		},
		error : function(err){
			alert('err');
			console.log('에러');
		}
	});
}
//팔로우 버튼 체크
$(function(){
	var seq = $('#essayScrapDiv').data('seq');
	
	if($('#followVal').val() === '1'){
		$('#followA').addClass('button-fill');
	}else{
		$('#followA').removeClass('button-fill');
	}
	
	$('.mentor_'+seq).on('click' , function(){
		var followBtn = $(this);
		followAjax(followBtn);
		
	});
	
});


function followAjax(followBtn){
	
	var sendData = {
			'followed_email' : $('.mentor-img').data('email'),
			'follow' : $('#followVal').val()
		};
	$.ajax({
		url : '/mentor/mentor/mentorFollow',
		type : 'POST',
		data : sendData,
		success : function(data) {
			
			if (data == 1) {
				
				followBtn.addClass('button-fill');
				var toastIcon = app.toast.create({
					  text: '관심멘토에 등록 되었습니다',
					  position: 'center',
					  closeTimeout: 2000,
					});
				toastIcon.open();
				
				let memNickname = $('#memNickname').val(); //팔로우를 누른사람
				let nickname = $('.mentor-name').data('name');	   //팔로우 당사자
				let receiverEmail = $('.mentor-img').data('email');     //팔로우 당사자 이메일
				let member_seq = $('#essayScrapDiv').data('seq'); // seq
				//alert(memNickname+',' + nickname +',' + receiverEmail +',' + member_seq);
				//socket에 보내자
				if(socket) {
					let socketMsg = "follow," + memNickname +","+nickname +","+ receiverEmail + "," +member_seq;
					console.log("msgmsg :: " + socketMsg );
					socket.send(socketMsg);
				}
				
				var AlarmData = {
						"myAlarm_receiverEmail" : receiverEmail,
						"myAlarm_callerNickname" : memNickname,
						"myAlarm_title" : "팔로우 알림",
						"myAlarm_content" :  memNickname + "님이 팔로우를 시작 했습니다."
				};
				alert(JSON.stringify(AlarmData));
				//팔로우 알림 DB저장
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
				
				
				
				
			}else{
				followBtn.removeClass('button-fill');
				var toastIcon = app.toast.create({
					  text: '관심멘토에서 삭제 되었습니다',
					  position: 'center',
					  closeTimeout: 2000,
					});
				toastIcon.open();
			}
			
			$('#followVal').val(data);
			
		},
		error : function(){
			console.log("err");
		}
	});
	
}
