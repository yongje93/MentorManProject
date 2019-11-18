/**
 * 
 */



////공통직무가 변경되었을때 ajax통신으로 직무유형에 맞는 게시판 출력
//$("#job_code").on("change", function(){
//	$.ajax({
//		type : 'get',
//		url : '/mentor/menteeboard/getMenteeboardListJob',
//		data : 'job_code='+$("#job_code").val()+'&pg='+$('#pgInput').val(),
//		dataType : 'json',
//		success : function(data){
//			$('#inputBody2').empty();
//			$('#menteeboardPagingDiv').empty();
//			
//			$.each(data['list'], function(key, value){
//				$('<tr/>').append($('<td/>',{
//					align: 'center',
//					text : value.seq
//				})).append($('<td/>',{
//					align: 'center',
//					text : value.job_type
//				})).append($('<td/>',{
//					}).append($('<a/>',{
//							href : 'javascript:void(0)',
//							id : 'subjectA',
//							text : value.subject,
//							class : value.seq+""
//				}))).append($('<td/>',{
//					align: 'center',
//					text : value.id
//				})).append($('<td/>',{
//					align: 'center',
//					text : value.logtime
//				})).append($('<td/>',{
//					align: 'center',
//					text : value.hit
//				})).append($('<td/>',{
//					align: 'center',
//					text : value.good
//				})).appendTo($('#inputBody2'));
//			});
//			//paging 처리
//			$('#menteeboardPagingDiv').html(data.menteeboardPaging.pagingHTML);
//			
//			//클릭시 뷰로 이동
//			$('#boardTable').on('click' ,'#subjectA' , function(){
////				if(data.memId == null){
////					alert('로그인 해라');
////				}
////				else{
//				var seq = $(this).parent().prev().prev().text();
//					//var seq = $(this).attr('class'); 이렇게 사용해도 된다
////					$(location).attr("href", "http://localhost:8080/springProject/board/boardView?seq="+seq+"&pg="+$('#pgInput').val());
////				}
//				$(location).attr("href", "http://localhost:8080/mentor/menteeboard/menteeboardView?seq="+seq+"&pg="+$('#pgInput').val());
//			
//			});	
//		},
//		error : function(err) {
//			alert('error');
//			console.log(err);
//		}
//	});
//});

//job_code 페이징 트리거 
//function boardSearch(pg){
//	$('input[name=pgInput]').val(pg);
//	$('#job_code').trigger('change','trigger');
//}

//$('#menteeboardModifyBtn').on("click" , function(){
//	if($('#menteeModifyFormSubject').val() == '' ){
//	}else if($('#summernote').val() == ''){
//	}else if($('#job_code_ModifyForm option:selected').val() == ''){
//		alert('직무를 선택하세요');
//	}
//	else{
//		$.ajax({
//			type : 'post',
//			url : '/mentor/menteeboard/menteeboardModify',
//			data : $('#menteeboardModifyForm').serialize(),
//			success : function(){
//				alert('수정완료');
//				$(location).attr("href", "http://localhost:8080/mentor/menteeboard/menteeboardList?pg=1");
//			},
//			error : function(err){
//				console.log(err);
//			}
//		});
//	}
//});
