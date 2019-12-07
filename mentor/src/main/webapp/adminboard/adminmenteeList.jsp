<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘티게시판 리스트</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<div class="col-sm-12">
							<table class="table">
								<thead>
									<tr>
										<th><input type="checkbox" id="all"></th>
										<th scope="col">No.</th>
										<th scope="col">직무유형</th>
										<th scope="col">제목</th>
										<th scope="col">글쓴이</th>
										<th scope="col">작성일</th>
										<th scope="col">조회수</th>
										<th scope="col">좋아요</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="menteeboardDTO" items="${list }">
									<tr>
										<td><input type="checkbox" class="check"value="${menteeboardDTO.menteeboard_seq }"></td>
										<td>${menteeboardDTO.menteeboard_seq }</td>
										<td>${menteeboardDTO.job_type }</td>
										<td><a href="/mentor/menteeboard/menteeboardView?seq=${menteeboardDTO.menteeboard_seq }&pg=${pg}">${menteeboardDTO.menteeboard_title }</a></td>
										<td>${menteeboardDTO.member_nickname }</td>
										<td>${menteeboardDTO.menteeboard_logtime }</td>
										<td>${menteeboardDTO.menteeboard_hit }</td>
										<td>${menteeboardDTO.menteeboard_good }</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div> <!-- table row 끝-->
					<div class="row a1">
						<div class="col-sm-12 searchPage">
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<li class="page-item"></li>
								</ul>
							</nav>                                                           
						</div>
					</div> <!-- 버튼 row끝 -->
				</div> <!-- table 감싸는 div-->
				<div class="ln_solid"></div>
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<button type="button" class="btn btn-danger btn-sm menteeListBtn" ><i class="fa fa-trash-o"></i>게시글 삭제</button>
					</div>
				</div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script>
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

//글 삭제 
function delete_info(){
toastr.options = {
		"closeButton": true,
		"positionClass": "toast-top-center",
		"hideEasing": "linear",
		"timeOut" : 0,
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
		}
toastr.info("승인하시겠습니까?<br /><br /><button type=button class=deleteBoard style=color:black>Yes</button>");
}

function deletementeeBoard(check){
	delete_info();
	$('.deleteBoard').click(function(){
		$.ajax({
			type : 'post',
			url : '/mentor/adminboard/deleteBoard',
			data : 'check='+check,
			success : function(){
				location.href="/mentor/adminboard/adminmenteeList";
			}
		});	
	});
}

$('.menteeListBtn').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		deletementeeBoard(check)		
});
</script>