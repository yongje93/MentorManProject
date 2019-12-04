/** 
 * @Title : 멘토신청 게시판 js파일입니다
 * @author : 안상구
 * @date : 2019. 11. 20.
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

function success_info(){
	toastr.options = {
			"positionClass": "toast-top-center",
			/*"timeOut": "2000",*/
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
			}
	toastr.info("승인하시겠습니까?<br /><br /><button type=button class=mentorSuccess >Yes</button>");
}
function reject_info(){
	toastr.options = {
			"positionClass": "toast-top-center",
			/*"timeOut": "2000",*/
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
	}
	toastr.info("거절하시겠습니까?<br /><br /><button type=button class=mentorReject >Yes</button>");
}

// 멘토승인
$('.btn_apply_success').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		mentorSuccess(check);
		
});

function mentorSuccess(check){
	success_info();
	$('.mentorSuccess').click(function(){
	$.ajax({
		type : 'post',
		url : '/mentor/adminmember/adminmentorSuccess',
		/*contentType : "application/x-www-form-urlencoded; charset=UTF-8",*/
		data : 'check='+check,
		success : function(){
			location.href="/mentor/adminmember/adminmentorApplyList";
		}
	});	
	});
}


//멘토승인거절
$('.btn_apply_reject').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		mentorReject(check);
});


function mentorReject(check){
	reject_info();
	$('.mentorReject').click(function(){
		$.ajax({
			type : 'post',
			url : '/mentor/adminmember/adminmentorReject',
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : 'check='+check,
			success : function(){
				location.href="/mentor/adminmember/adminmentorApplyList";
			}
		});		
	});
}