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

					} else {
						$('#heartImg').prop("src", "../image/blackheart.png");
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
				texthtml = '<div class="noticeboardFloat_right_div"><input type="text" class="modifyText" name="modifyText" value="'+inputContent +'" autofocus><input type="button" id="sec_'+btnId+'" name="'+seq+'" class="button color-gray modifyReply" value="2번째수정" ></div>'
				$('.'+btnId).append(texthtml);
			}
			
			
			$('.'+btnId).show();  
			$('#seq_trans').val(seq); //수정할 댓글의 seq를 hidden에 담는다
			
			//2번째 수정버튼 클릭시
			$(document).on('click','#sec_'+btnId,function(){
				var seq2 = $(this).attr('name'); //댓글 seq
				$('.modifyReply_hide_' + seq2).hide();
				
				if($('.modifyText').val() == ''){
					alert('수정할 내용을 입력하세요');
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
							
							let $frag = $(document.createDocumentFragment());
							$.each(data['list'], function(key, value){
								var str = '<div class="row no-gap">'+
								'<div id="thanks-notes">'+
								'<div class="block mentee-detail-block thanks-note-card" hidden="" '+
									'style="display: block;">'+
									'<div class="mentee-info">'+
										'<div class="mentee-image img-circle">'+
											'<img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcmZCIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--f0d5c09d42f655ec75e2351b3a921a3266a435e5/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg">' +
										'</div>'+
										'<div class="mentee-name">'+ value.menteeboardReply_nickname +','+value.menteeboardReply_seq+'</div>' +
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
								'</div>';
								
								 $frag.append(str);
							});
							$frag.append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
							$('#inputList').append($frag);
							
							$('.modifyReply_hide_' + seq).show();
						},
						error : function(){
							alert('수정 실패');
						}
					});
				}
			});
			
		});
		
		$(document).on('click','.dapgleReply',function(){
			alert($(this).attr('name')+' , 곧 구현');
			
			
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
					
					let $frag = $(document.createDocumentFragment());
					$.each(data['list'], function(key, value){
						var str = '<div class="row no-gap">'+
						'<div id="thanks-notes">'+
						'<div class="block mentee-detail-block thanks-note-card" hidden="" '+
							'style="display: block;">'+
							'<div class="mentee-info">'+
								'<div class="mentee-image img-circle">'+
									'<img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcmZCIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--f0d5c09d42f655ec75e2351b3a921a3266a435e5/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg">' +
								'</div>'+
								'<div class="mentee-name">'+ value.menteeboardReply_nickname +','+value.menteeboardReply_seq+'</div>' +
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
						'</div>';
						 $frag.append(str);
					});
					$frag.append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
					$('#inputList').append($frag);
				},
				error : function(){
					alert('삭제 실패');
				}
			}); 
		});
	
	
		
		

	//댓글 등록
	$(document).on("click", "#regist", function(){
		if($('#content').val() == ''){
			alert('댓글을 입력하세요');
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
					
					$.each(data['list'], function(key, value){
						var str = '<div class="row no-gap">'+
						'<div id="thanks-notes">'+
						'<div class="block mentee-detail-block thanks-note-card" hidden="" '+
							'style="display: block;">'+
							'<div class="mentee-info">'+
								'<div class="mentee-image img-circle">'+
									'<img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcmZCIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--f0d5c09d42f655ec75e2351b3a921a3266a435e5/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg">' +
								'</div>'+
								'<div class="mentee-name">'+ value.menteeboardReply_nickname +','+value.menteeboardReply_seq+'</div>' +
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
						'</div>';
						 $frag.append(str);
					});
					$frag.append('<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">'+data.menteeboardPaging.pagingHTML+'</div><hr>');
					$('#inputList').append($frag);
				},
				error : function(){
					alert('실패');
				}
			}); 
		}
	});