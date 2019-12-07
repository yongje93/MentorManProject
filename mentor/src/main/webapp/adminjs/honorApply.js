/** 
 * @Title : 명예멘토신청 게시판 js파일입니다
 * @author : 안상구
 * @date : 2019. 11. 29.
 * 이해 안가시는 부분이있으면 연락주세요
 */

//전체 체크박스 체크/논 체크
$('#all').click(function(){
		if($('#all').prop('checked'))
			$('.check').prop('checked',true);
		else
			$('.check').prop('checked',false);
});

//아무것도 선택하지않았을때
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

//명예멘토 승낙
function success_info(){
	toastr.options = {
			"closeButton": true,
			"positionClass": "toast-top-center",
			"hideEasing": "linear",
			"timeOut" : 0,
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut"
			}
	toastr.info("승인하시겠습니까?<br /><br /><button type=button class=mentorSuccess style=color:black>Yes</button>");
}

function honorSuccess(check){
	success_info();
	$('.mentorSuccess').click(function(){
		$.ajax({
			type : 'post',
			url : '/mentor/adminmember/honorMentor',
			data : 'check='+check,
			success : function(){
				location.href="/mentor/adminmember/adminmentorList";
			}
		});	
	});
}

$('.btn_honor_success').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		honorSuccess(check)		
});