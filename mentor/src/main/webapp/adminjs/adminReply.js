/** 
 * @Title : 댓글관리 게시판 js파일입니다
 * @author : 안상구
 * @date : 2019. 12. 03.
 * 이해 안가시는 부분이있으면 연락주세요
 */

//전체 체크박스 체크/논 체크
$('#all').click(function(){
		if($('#all').prop('checked'))
			$('.check').prop('checked',true);
		else
			$('.check').prop('checked',false);
});

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

function delete_re(){
	toastr.options = {
			"closeButton": true,
			"positionClass": "toast-top-center",
			"timeOut": 0,
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
			}
	toastr.info("삭제하시겠습니까?<br /><br /><button type=button class=redelete style=color:black>Yes</button>");
}

//고맙습니다 댓글 삭제
$('.btn_thankyou_delete').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		thankyou(check);
});

function thankyou(check){
	delete_re();
	$('.redelete').click(function(){
	$.ajax({
		type : 'post',
		url : '/mentor/adminreply/meetingReviewDelete',
		/*contentType : "application/x-www-form-urlencoded; charset=UTF-8",*/
		data : 'check='+check,
		success : function(){
			location.href="/mentor/adminreply/adminThankyou";
		}
	});	
	});
}
//--------------------------------------------------------------------------------------------------------
//멘티게시판 댓글 삭제
$('.btn_menteeReply_delete').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		menteeReply(check);
});

function menteeReply(check){
	delete_re();
	$('.redelete').click(function(){
	$.ajax({
		type : 'post',
		url : '/mentor/adminreply/menteeReplyDelete',
		/*contentType : "application/x-www-form-urlencoded; charset=UTF-8",*/
		data : 'check='+check,
		success : function(){
			location.href="/mentor/adminreply/adminmenteeReply";
		}
	});	
	});
}