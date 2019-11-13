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

// 글삭제
$('.noticeListBtn').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		alert("항목을 선택하세요.");
	else{
		if(confirm("정말로 삭제하시겠습니까?")){
			$.ajax({
				type : 'post',
				url : '/mentor/adminboard/adminnoticeboardDelete',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : 'check='+check,
				success : function(){
					location.href="/mentor/adminboard/adminnoticeboardList";
				}
			});				
		}
	}
});

$('.btn-info').on('click',function(){
	location.href="/mentor/adminboard/adminnoticeboardList?pg";
});