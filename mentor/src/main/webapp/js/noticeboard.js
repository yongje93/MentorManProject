//List에서 검색 이미지 눌렀을때
$('#search_image').click(function(event, str){
	search(event, str);
});
$('#searchText').keypress(function(event){
    if ( event.which == 13 ) {
        $('#search_image').click();
        return false;
    }
});
function search(event, str){
	if(str!='trigger'){
		$('input[name=pg]').val(1);
	}
	if($('#searchText').val()==0){
		$('#searchText').focus();
		$('#searchText').attr('placeholder','찾으실 내용을 입력하세요');
	}else {
		$.ajax({
			type: 'post',
			url: '/mentor/noticeboard/noticeboardSearch',
			data: $('#noticeboardSearch').serialize(),
			dataType: 'json',
			success: function(data){
				
				$('#boardTable tr:gt(0)').remove();
	            
	            $.each(data.list, function(index,value){
	            	$('<tr/>').append($('<td/>',{
						/* class: 'noticeboardList_td', */
						align:'center',
						text: value.noticeboard_seq
						})).append($('<td/>',{
						align:'center',
						style: 'overflow: hidden; text-overflow: ellipsis; white-space: nowrap; width: 300px; display: block; line-height: 40px;'
						}).append($('<a/>',{
						href: 'javascript:void(0)',
						text: '[공지]'+value.noticeboard_title,
						id: 'notice_seq',
						class: value.noticeboard_seq+''
					}))).append($('<td/>',{
						/* class: 'noticeboardList_td', */
						align:'center',
						text: '[관리자]'
					})).append($('<td/>',{
					/* class: 'noticeboardList_td', */
						align:'center',
						text: value.noticeboard_logtime
					})).append($('<td/>',{
						/* class: 'noticeboardList_td', */
						align:'center',
						text: value.noticeboard_hit
					})).appendTo($('#boardTable'));
	            });
	            
	           
	            
	            //페이징
	            $('#noticeboardList_pg').html(data.boardPaging.pagingHTML);
	         },
	         error: function(err){
	            console.log(err);
	         }			
		});
	}
}




//List에서 글쓰기로 넘어갈때 버튼
$('#noticeboardWriteButton').on('click', function(){
	location.href='/mentor/noticeboard/noticeboardWriteForm';
});

//글쓰기
function error(){
	$('#summernote').focus();
	$('#summernote_error_div').text('내용을 입력해주세요');
	$('#summernote_error_div').css('color','red');
}
$('#noticeboardWriteForm_Btn').on('click', function(){
	$('#noticeboardWriteNonTitle_error_span').empty();
	$('#summernote_error_div').empty();
	if($('#noticeTitle').val()==''){
		$('#noticboardWriteTitle').focus();
		$('#noticeboardWriteNonTitle_error_span').text('제목을 입력해주세요');
		$('#noticeboardWriteNonTitle_error_span').css('color','red');
	}else if($('#summernote').val()==''){
		error();
	}else if($('#summernote').val()=='<p class=""><br></p>'){
		error();
	}else if($('#summernote').val()=='<p><br></p>'){
		error();
	}else {
		$.ajax({
			type: 'post',
			url: '/mentor/noticeboard/noticeboardWrite',
			data: {'noticeboard_title':$('#noticeTitle').val(), 'noticeboard_content':$('#summernote').val()},
			success: function(){
				location.href='/mentor/noticeboard/noticeboardList?pg='+1;
			},
			error: function(){
				alert('에러');
			}
		});
	}
});

//글쓰기에서  뒤로가기
$('#noticeboerdWriteForm_backBtn').on('click',function(){
	location.href="/mentor/noticeboard/noticeboardList?pg="+1;
});

//view에서 뒤로가기
$('#noticeboardBack_btn').on('click',function(){
	location.href="/mentor/noticeboard/noticeboardList?pg="+$('#pg').val();
});

//List수정 화면
$('#noticeboardUpdate_btn').on('click',function(){
	location.href="/mentor/noticeboard/noticeboardUpdateForm?pg="+$('#pg').val()+"&seq="+$('#seq').val();
});

//수정 화면에서 다시 view로
$('#noticeboerdUpdateForm_backBtn').on('click',function(){
	location.href="/mentor/noticeboard/noticeboardView?pg="+$('#pg').val()+"&seq="+$('#seq').val();
});
//Update
$('#noticeboerdUpdateForm_btn').on('click',function(){
	$('#noticeboardWriteNonTitle_error_span').empty();
	$('#summernote_error_div').empty();
	if($('#noticeTitle').val()==''){
		$('#noticboardWriteTitle').focus();
		$('#noticeboardWriteNonTitle_error_span').text('제목을 입력해주세요');
		$('#noticeboardWriteNonTitle_error_span').css('color','red');
	}else if($('#summernote').val()==''){
		error();
	}else if($('#summernote').val()=='<p class=""><br></p>'){
		error();
	}else if($('#summernote').val()=='<p><br></p>'){
		error();
	}else {
		$.ajax({
			type: 'post',
			url: '/mentor/noticeboard/noticeboardUpdate',
			data: {'noticeboard_title':$('#noticeTitle').val(), 'noticeboard_content':$('#summernote').val(),'seq':$('#seq').val()},
			success: function(){
				location.href='/mentor/noticeboard/noticeboardList?pg='+$('#pg').val();
			},
			error: function(){
				alert('에러');
			}
		});
	}
});
