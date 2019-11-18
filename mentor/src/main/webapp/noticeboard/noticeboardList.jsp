<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link  rel="stylesheet" href="../css/noticeboard.css">
<div class="page navbar-fixed mentee_programs index">
<div class="page-content" >
	<form id="noticeboardSearch" method="post">
	<div class="block-title strong-title">공지</div>
	<div id="searchImage_div" style="float: right;"><img id="search_image" src="../image/images.png" style="width: 20px; height: 20px; cursor:pointer;"></div>
	<div id="searchText_div" style="float: right;"><input type="text" name="searchText" id="searchText" placeholder="찾기" value="${searchText}" style="width: 200px;"></div>
	<input type="hidden" name="pg" value="1">
	<div id="searchText_error_div"></div>
	</form>
		<div id="boardList_div">
		<form id="noticeboardWriteForm" name="noticeboardWriteForm" method="post" action="/mentor/noticeboard/noticeboardDelete">
		<input type="hidden" id="pg" value="${pg}">
				<table class="noticeboardList_Table" id="boardTable">
					    <thead>
					    </thead>
					    <tbody>
					    <tr>
					        <th class="noticeboardList_tbody_th" scope="row" align="center">No</th>
					        <th class="noticeboardList_tbody_th" scope="row" align="center">제목</th>
					        <th class="noticeboardList_tbody_th" scope="row" align="center">글쓴이</th>
					        <th class="noticeboardList_tbody_th" scope="row" align="center">작성시간</th>
					        <th class="noticeboardList_tbody_th" scope="row" align="center">조회수</th>
					    </tr>
				   		</tbody>
				</table>
		</form>
			<br>
			<div id="page_number" style="float: left;"></div>
			<div id="noticeboardList_pg"></div>
		</div>
</div>
</div>
<script src="../js/noticeboard.js"></script>
<script type="text/javascript">
/* 엔터 x */
/* $(document).ready(function(){
	  $(document).keypress(function(e){
	    if(e.keyCode==13) return false;
	  });
	}); */
//찾은 후 페이징
function boardSearch(pg){
	$('input[name=pg]').val(pg);
	$('#search_image').trigger('click','trigger');
}

$(function(){
	noticeList();
});
function noticeList(){
	$('#boardTable tr:gt(1)').remove();
	$.ajax({
		type: 'post',
		url: '/mentor/noticeboard/getBoardList',
		data: {'pg': $('#pg').val()},
		dataType: 'json',
		success: function(data){
			/* alert(JSON.stringify(data)); */
			$.each(data.list, function(index, value){
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
			
			$('#noticeboardList_pg').html(data.boardPaging.pagingHTML);
			
			
			
			$('#boardTable').on('click','#notice_seq',function(){
				location.href='/mentor/noticeboard/noticeboardView?seq='+$(this).attr('class')+'&pg='+$('#pg').val();
			});
			
		},
		error: function(err){
			alert('에러');
		}
	});
	$('#all').on('click', function(){
		if($('#all').prop('checked')){
			$('.check').prop('checked', true);
		}else {
			$('.check').prop('checked', false);
		}
	});
	
	$('#noticeboardDeleteButton').on('click', function(){
		var count = $('.check:checked').length;
		if(count==0){
			alert('삭제할 글을 선택해주세요');
		}else {
			if(confirm('삭제하시겠습니까?')){
				$('#noticeboardWriteForm').submit();
			}
		}
	});
}

</script>