/** 
 * @Title : 공지사항 게시판 js파일입니다
 * @author : 안상구
 * @date : 2019. 11. 5.
 * 이해 안가시는 부분이있으면 연락주세요
 */

//전체 체크박스 체크/논 체크
$('#all').click(function(){
		if($('#all').prop('checked'))
			$('.check').prop('checked',true);
		else
			$('.check').prop('checked',false);
});

//항목을선택해라
function toastr_wran(){
	toastr.options = {
			"progressBar": true,
			"positionClass": "toast-top-center",
			"timeOut": "2000",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
	};
	toastr.warning("항목을선택하세요");
}
//지우겠습니까
function delete_notice(){
	toastr.options = {
			"closeButton": true,
			"positionClass": "toast-top-center",
			"timeOut": 0,
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
			}
	toastr.info("삭제하시겠습니까?<br /><br /><button type=button class=nodelete style=color:black>Yes</button>");
}


// 글삭제
$('.noticeListBtn').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		notice_delete(check);
});

//지우자
function notice_delete(check){
	delete_notice();
	$('.nodelete').click(function(){
		$.ajax({
			type : 'post',
			url : '/mentor/adminboard/adminnoticeboardDelete',
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : 'check='+check,
			success : function(){
				location.href="/mentor/adminboard/adminnoticeboardList";
			}
		});
	});
}

//view단에서 뒤로가기
$('.btn-info').on('click',function(){
	location.href="/mentor/adminboard/adminnoticeboardList";
});

//글 등록
$('.btn_noticeboardinsert').on('click',function(){
	location.href="/mentor/adminboard/adminnoticeboardWriteForm";
});

$('.btn_modifyForm').click(function(){
	var seq = $('.seq').val();
	var pg = $('.pg').val();
	location.href="/mentor/adminboard/adminnoticeboardmodifyForm?seq="+seq+"&pg="+pg;
});

//글쓰기에서  뒤로가기
$('.noticeboerdWriteForm_backBtn').on('click',function(){
	location.href="/mentor/adminboard/adminnoticeboardList";
});

//글쓰기
function error(){
	$('#summernote').focus();
	$('#summernote_error_div').text('내용을 입력해주세요');
	$('#summernote_error_div').css('color','red');
}
$('.noticeboardWriteForm_Btn').on('click', function(){
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
			url: '/mentor/adminboard/adminnoticeboardWrite',
			data: {'noticeboard_title':$('#noticeTitle').val(), 'noticeboard_content':$('#summernote').val()},
			success: function(){
				location.href='/mentor/adminboard/adminnoticeboardList';
			},
			error: function(){
				alert('에러');
			}
		});
	}
});

//Update
$('.noticeboerdUpdateForm_btn').on('click',function(){
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
			url: '/mentor/adminboard/noticeboardModify',
			data: {'title':$('#noticeTitle').val(), 'content':$('#summernote').val(),'seq':$('#seq').val()},
			success: function(){
				location.href='/mentor/adminboard/adminnoticeboardList?pg='+$('#pg').val();
			}
		});
	}
});
