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
toastr.options = {
		  "progressBar": true,
		  "positionClass": "toast-top-center",
		  "timeOut": "2000",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
};

// 글삭제
$('.btn_apply_success').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	console.log(check);
	if(cnt==0)
		toastr.warning("항목을선택하세요");
	else{
		if(confirm("정말로 승인하시겠습니까?")){
			$.ajax({
				type : 'post',
				url : '/mentor/adminmember/adminmentorSuccess',
				/*contentType : "application/x-www-form-urlencoded; charset=UTF-8",*/
				data : 'check='+check,
				success : function(){
					location.href="/mentor/adminmember/adminmentorApplyList";
					toastr.success("멘토승인 완료");
				}
			});				
		}
	}
});

$('.btn_apply_reject').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		alert("항목을 선택하세요.");
	else{
		if(confirm("정말로 거절하시겠습니까?")){
			$.ajax({
				type : 'post',
				url : '/mentor/adminmember/adminmentorReject',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : 'check='+check,
				success : function(){
					location.href="/mentor/adminmember/adminmentorApplyList";
				}
			});				
		}
	}
});