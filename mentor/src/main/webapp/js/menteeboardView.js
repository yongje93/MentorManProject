$(document).ready(function() {
		var heartval = $('#heartVal').val();
		//heartval의 값에따라 이미지 변경
		if (heartval != 0) {
			$("#heartImg").prop("src", "../image/redheart.png");
			$(".heart").prop('name', heartval);
		} else {
			$("#heartImg").prop("src", "../image/blackheart.png");
			$(".heart").prop('name', heartval);
		}
		
		//목록 버튼 클릭
		$('#viewOutBtn').on('click' , function(){
			$(location).attr('href', '/mentor/menteeboard/menteeboardList?pg=1');
		});
		
		//좋아요 버튼 클릭
		$('.heart').on('click', function() {
			//하트의 이름을 넣는다!
			var sendData = {
				'menteeboard_seq' : $('#menteeboard_seq').val(),
				'heart' : $('#heartVal').val()
			};
			
			$.ajax({
				url : '/mentor/menteeboard/menteeboardLike',
				type : 'POST',
				data : sendData,
				success : function(data) {
					$(".heart").prop('name', data);
					if (data == 1) {
						$('#heartImg').prop("src", "../image/redheart.png");
						
						var toastIcon = app.toast.create({
							  icon: '<i class="fas fa-heart fa-3x"></i>',
							  text: '',
							  position: 'center',
							  closeTimeout: 2000,
							});
						toastIcon.open();
						
					} else {
						$('#heartImg').prop("src", "../image/blackheart.png");
						var toastIcon = app.toast.create({
							  icon: '<i class="far fa-heart fa-3x"></i>',
							  text: '',
							  position: 'center',
							  closeTimeout: 2000,
							});
						toastIcon.open();
					}
					$('#heartVal').val(data);
				},
				error : function(err) {
					alert('error');
				}
			});
		});
});
		

		//댓글 수정버튼 클릭시
		$(document).on('click','.modifyReply',function(){
			var btnId = $(this).attr('id'); // modifyReply${i.count}
			var seq = $(this).attr('name'); //댓글 seq
			var divContent = $('#divContent_'+btnId);
			
			$('.modifyReply_hide_' + seq).hide(); //내용 , 수정 ,삭제 버튼 가리기
			
			if($('.modifyText').length == 0){
				var inputContent = $('.content_'+seq).text();
				texthtml = '<div class="noticeboardFloat_right_div"><input type="text" class="modifyText" name="modifyText" value="'+inputContent +'" autofocus><input type="button" id="sec_'+btnId+'" name="'+seq+'" class="button color-gray modifyReply" value="수정" ></div>'
				$('.'+btnId).append(texthtml);
			}
			
			
			$('.'+btnId).show();  
			$('#seq_trans').val(seq); //수정할 댓글의 seq를 hidden에 담는다
			
			//2번째 수정버튼 클릭시
			$(document).on('click','#sec_'+btnId,function(){
				var seq2 = $(this).attr('name'); //댓글 seq
				$('.modifyReply_hide_' + seq2).hide();
				
				if($('.modifyText').val() == ''){
					var toastTop = app.toast.create({
			            text: '수정할 내용을 입력하세요.',
			            position: 'top',
			            closeButton: true,
			          });
			          toastTop.open();
				}else{
					$.ajax({
						type : 'post',
						url : '/mentor/menteeboardReply/menteeboardReplyModify',
						data : $('#menteeboardViewForm').serialize(),
						dataType : 'json',
						success : function(data){
							$('.modifyText').val(data);
							//divContent.html(data.trim());
							$('.'+btnId).hide();
							$('.modifyText').val('');
							$('#inputList').empty();
							
							menteeboardViewList(data);
							
							$('#inputList').append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
							$('.modifyReply_hide_' + seq).show();
						},
						error : function(){
							console.log('수정 실패')
						}
					});
				}
			});
			
		});
		
		$(document).on('click','.dapgleReply',function(){
			//alert($(this).attr('name')+' , 곧 구현');
		});
		
		//댓글 삭제 버튼 클릭시
		$(document).on('click','.deleteReply',function(){
			var btnId = $(this).attr('id');
			var seq = $(this).attr('name');
			$('#seq_trans').val(seq);
			
			jQuery.ajaxSettings.traditional = true;
			
			$.ajax({
				type : 'post', 
				url : '/mentor/menteeboardReply/menteeboardReplyDelete',
				data :   $('#menteeboardViewForm').serialize(),
				dataType : 'json',
				success : function(data){
					$('#inputList').empty();
					
					menteeboardViewList(data);
					
					$('#inputList').append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
				},
				error : function(){
					console.log('삭제 실패');
				}
			}); 
		});
	
	
		
		

	//댓글 등록
	$(document).on("click", "#regist", function(){
		if($('#content').val() == ''){
			var toastTop = app.toast.create({
	            text: '댓글을 입력하세요.',
	            position: 'top',
	            closeButton: true,
	          });
	          toastTop.open();
		}else{
			$.ajax({
				type : 'post',
				url : '/mentor/menteeboardReply/menteeboardReplyWrite',
				data : $('#menteeboardViewForm').serialize(),
				dataType : 'json',
				success : function(data){
					$('#inputList').empty();
					$('#content').val(''); 
					let $frag = $(document.createDocumentFragment());
					
					menteeboardViewList(data);
					
					$('#inputList').append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
					
					
					let memNickname = $('#memNickname').val();
					let nickname = $('#nickname').val();
					let receiverEmail = $('#email').val();
					let menteeboard_seq = $('#menteeboard_seq').val();
					
					
					
					var AlarmData = {
							"myAlarm_receiverEmail" : receiverEmail,
							"myAlarm_callerNickname" : memNickname,
							"myAlarm_title" : "댓글 알림",
							"myAlarm_content" :  memNickname + "님이 <a type='external' href='/mentor/menteeboard/menteeboardView?seq="+menteeboard_seq+"&pg=1'>" + menteeboard_seq + "</a> 번 게시글에 댓글을 남겼습니다."
					};
					//알림 저장
					$.ajax({
						type : 'post',
						url : '/mentor/member/saveAlarm',
						data : JSON.stringify(AlarmData),
						contentType: "application/json; charset=utf-8",
						dataType : 'text',
						success : function(data){
							//socket에 보내자
							if(socket) {
								let socketMsg = "reply," + memNickname +","+ nickname +","+ receiverEmail +","+ menteeboard_seq;
								console.log("msgmsg : " + socketMsg);
								socket.send(socketMsg);
							}
						},
						error : function(err){
							console.log('알림저장 실패');
						}
					});
					
				},
				error : function(err){
					alert('실패2');
					console.log(err);
				}
			}); 
		}
	});
	
	
	
	
	function menteeboardViewList(data,$frag) {
		$.each(data['list'], function(key, value){
			var str = '';
			if(value.menteeboardReply_flag == 0){
				str = '<div class="no-gap">'+
				'<div id="thanks-notes">'+
				'<div class="block mentee-detail-block thanks-note-card" hidden="" '+
					'style="display: block;">'+
					'<div class="mentee-info">'+
						'<div class="mentee-image img-circle">';
					if(value.member_profile != 'profile.jpg'){
						str += '<img width="150" height="150" src="../storage/'+ value.menteeboardReply_email +'/'+value.member_profile+'">';
					}
					if(value.member_profile == 'profile.jpg'){
						str +='<img width="150" height="150" src="../image/profile.jpg">';
					}
					str +='</div>'+
					'<div class="mentee-name">'+ value.member_nickname+'</div>' +
					'<div class="sent-date">'+value.menteeboardReply_logtime+'</div>'+
				'</div>' +
				'<div class="modifyReply_hide_'+value.menteeboardReply_seq +'">'+
				'<div class="thanks-note-body content_'+value.menteeboardReply_seq+'">'+value.menteeboardReply_content+'</div>'; 
			if( $('#memEmail').val() === value.menteeboardReply_email){
				str += '<div class="noticeboardFloat_right_div"><input type="button" id="modifyReply'+(key+1)+'" name="'+value.menteeboardReply_seq+'" class="button color-gray modifyReply" value="수정" ></div>'+
    			'<div class="noticeboardFloat_right_div"><input type="button" id="deleteReply'+(key+1)+'" name="'+value.menteeboardReply_seq+'" class="button color-gray deleteReply" value="삭제" ></div>';
			}
			str += '</div>'+
				'<div class="modifyReply'+(key+1)+'"></div>'+
			'</div>' +
			'</div>' +
			'</div><br/>';
			$('#inputList').append(str);
			}else{
				str += '<div class="no-gap">'+
					'<div id="thanks-notes">'+
						'<div class="block mentee-detail-block thanks-note-card" hidden="" style="display: block;">'+
							'<div class="mentee-info">'+
								'<div class="mentee-image img-circle">'+
									'<img width="150" height="150" src="../image/profile.jpg">'+
								'</div>'+
								'<div class="mentee-name">관리자</div>'+
								'<div class="sent-date">'+ value.menteeboardReply_logtime +'</div>'+
							'</div>'+
							'<div class="thanks-note-body"><i class="fas fa-exclamation-circle" style="color:red;"></i>관리자에의해 삭제된 댓글입니다.</div>'+
						'</div>'+
					'</div> '+
				'</div><br/>';
				$('#inputList').append(str);
			}
		});
	}
	
	
	
	
	
	