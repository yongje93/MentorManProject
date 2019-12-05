/**
 * 
 */

// jsp 화면 로드시 ajax통신으로 list를 들고온다
$(document).ready(function(){
	$.ajax({
		type : 'post' ,
		url : '/mentor/menteeboard/getMenteeboardList',
		data : 'pg='+$('#pgInput').val(),
		dataType : 'json',
		success : function(data){
			
			loadMenteeboardList(data);
			
			//paging 처리
			$('#menteeboardPagingDiv').append($('<ul/>', {
    			class : 'pagination'
    		}).append(data.menteeboardPaging.pagingHTML));
			
			//클릭시 뷰로 이동
			$('#boardTable').on('click' ,'#subjectA' , function(){
				
				var seq = $(this).parent().prev().prev().text();
				$(location).attr("href", "/mentor/menteeboard/menteeboardView?seq="+seq+"&pg="+$('#pgInput').val());
				
			});	
			
			//글쓰기 버튼 클릭 이벤트
			$('#menteeboardWriteformBtn').on('click' , function(){
				if(data.memNickname == null){
					var toastTop = app.toast.create({
			            text: '로그인 후 이용 가능합니다.',
			            position: 'top',
			            closeButton: true,
			          });
			          toastTop.open();
				}else{
					$(location).attr("href", "/mentor/menteeboard/menteeboardWriteForm");
				}
				return false;
			});
		},
		error : function(err){
			alert('error');
			console.log(err);
		}
	});
});


//공통직무가 변경되었을때 ajax통신으로 직무유형에 맞는 게시판 출력
$("#job_code").on("change", function(){
	$.ajax({
		type : 'get',
		url : '/mentor/menteeboard/getMenteeboardListJob',
		data : 'job_code='+$("#job_code").val()+'&pg='+$('#pgInput').val(),
		dataType : 'json',
		success : function(data){
			$('#inputBody2').empty();
			$('#menteeboardPagingDiv').empty();
			
			loadMenteeboardList(data)
			
			//paging 처리
			$('#menteeboardPagingDiv').append($('<ul/>', {
    			class : 'pagination'
    		}).append(data.menteeboardPaging.pagingHTML));
			
			//클릭시 뷰로 이동
			$('#boardTable').on('click' ,'#subjectA' , function(){
				if(data.memId == null){
				}else{
					var seq = $(this).parent().prev().prev().text();
					$(location).attr("href", "/springProject/board/boardView?seq="+seq+"&pg="+$('#pgInput').val());
				}
			});	
		},
		error : function(err) {
			console.log(err);
		}
	});
});

//job_code 페이징 트리거 
function boardSearch(pg){
	$('input[name=pgInput]').val(pg);
	$('#job_code').trigger('change','trigger');
}


//검색 버튼 클릭시
$('#search_image').on('click' , function(){
	
	if($('#search_text').val()==0){
		$('#search_text').focus();
	}else {
		$.ajax({
			type: 'post',
			url: '/mentor/menteeboard/menteeboardSearch',
			data: $('#menteeboardSearch').serialize(),
			dataType: 'json',
			success: function(data){
				$('#inputBody2').empty();
				$('#menteeboardPagingDiv').empty();
				
				loadMenteeboardList(data);
				
				//paging 처리
				$('#menteeboardPagingDiv').append($('<ul/>', {
	    			class : 'pagination'
	    		}).append(data.menteeboardPaging.pagingHTML));
				
				//클릭시 뷰로 이동
				$('#boardTable').on('click' ,'#subjectA' , function(){
					if(data.memId == null){
					}else{
						var seq = $(this).parent().prev().prev().text();
						$(location).attr("href", "/springProject/board/boardView?seq="+seq+"&pg="+$('#pgInput').val());
					}
				});	
			},
			error : function(err){
				console.log('에러');
			}
		});
	}
	
});
//검색 페이징 트리거
function boardSearch2(pg){
	$('input[name=pgInput]').val(pg);
	$('#search_image').trigger('click','trigger');
}

//list 불러오기
function loadMenteeboardList(data){
	
	$.each(data['list'], function(key, value){
		var str = '';
		
		if(value.menteeboard_reply != 0 ){
			str = '<strong class="highlight">('+ value.menteeboard_reply +')</strong>' ;
		}
		
		$('<tr/>').append($('<td/>',{
			align: 'center',
			text : value.menteeboard_seq
		})).append($('<td/>',{
			align: 'center',
			text : value.job_type,
			style: 'line-height: 40px;'
		})).append($('<td/>',{
			style: 'width: 500px;'
			}).append($('<a/>',{
					href : 'javascript:void(0)',
					id : 'subjectA',
					html : value.menteeboard_title + ' ' +str ,
					class : value.seq+"",
					style :'color : black;'
		}))).append($('<td/>',{
			align: 'center',
			text : value.member_nickname
		})).append($('<td/>',{
			align: 'center',
			text : value.menteeboard_logtime
		})).append($('<td/>',{
			align: 'center',
			text : value.menteeboard_hit
		})).append($('<td/>',{
			align: 'center',
			text : value.menteeboard_good
		})).appendTo($('#inputBody2'));
	});
	
}