<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="state" value="${state }">
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘토 리스트</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<form method="post" action="/mentor/adminmember/adminmentorSearch">
						<input type="hidden" name="pg" value="${pg }">
						<div class="col-sm-12 memberSearch">
							<input type="text" name="adminmentorKeyword" placeholder="이름을 입력하세요." style="height:30px;">
							<button type="submit" class="btn btn-success btn-sm">찾기</button>
						</div>
						</form>
					</div> <!-- 검색 row끝 -->
					<div class="row">
						<div class="col-sm-12">
							<table class="table">
								<thead>
									<tr>
										<th><input type="checkbox" id="all"></th>
										<th scope="col">멘토이름</th>
										<th scope="col">멘토 회사</th>
										<th scope="col">멘토 부서</th>
										<th scope="col">직무 유형</th>
										<th scope="col">멘토 분류&emsp;<span class="sort" style="cursor:pointer;"><i class="fas fa-sort"></i></span></th>
										<th scope="col">회원가입 날짜</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="adminmentorDTO" items="${list }">
									<tr>
										<td><input type="checkbox" class="check"value="${adminmentorDTO.mentor_seq }"></td>
										<td><img src="../image/${adminmentorDTO.member_profile }" width="30" height="30" style="border-radius: 50%;">${adminmentorDTO.member_name }</td>
										<td>${adminmentorDTO.mentor_company }</td>
										<td>${adminmentorDTO.mentor_department }</td>
										<td>${adminmentorDTO.job_type }</td>
										<c:if test="${adminmentorDTO.mentor_badge eq '0' }">
										<td>멘토</td>
										</c:if>
										<c:if test="${adminmentorDTO.mentor_badge eq '1' }">
										<td>명예멘토</td>
										</c:if>
										<td>${adminmentorDTO.mentor_logtime }</td>
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
									<li class="page-item">${adminmemberPaging.pagingHTML }</li>
								</ul>
							</nav>                                                           
						</div>
					</div> <!-- 버튼 row끝 -->
				</div> <!-- table 감싸는 div-->
			<div class="ln_solid"></div>
			<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<button type="button" class="btn btn-danger btn-sm btn_mentor_delete">멘토삭제</button>
					</div>
				</div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script >
//서치페이지
function adminmentorSearch(pg){
	$.ajax({
		type : 'post',
		url : '/mentor/adminmember/adminmentorSearch',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			pg : pg,
			adminmentorKeyword : '${adminmentorKeyword}'
		},
		success : function(){
			console.log("ok");
		}
	});	
}

//내림차순 오름차순 view
$(function(){
	const state = ${state};
	console.log(state);
	if(state==1)
		$(".sort > i").attr('class','fas fa-sort-down');
	else if(state==2)
		$(".sort > i").attr('class','fas fa-sort-up');
});
//내림차순 오름차순 List
$(".sort").click(function(){
	const state = ${state};
	if(state == 0){
		location.href="/mentor/adminmember/adminmentorList?pg=${pg}&state="+1;
		$(".sort > i").attr('class','fas fa-sort-down');
	}
	else if(state == 1){
		$(".sort > i").attr('class','fas fa-sort-up');
		location.href="/mentor/adminmember/adminmentorList?pg=${pg}&state="+2;
	}
	else if(state == 2){
		$(".sort > i").attr('class','fas fa-sort-up');
		location.href="/mentor/adminmember/adminmentorList?pg=${pg}&state="+1;
	}
});

//체크박스
$('#all').click(function(){
	if($('#all').prop('checked'))
		$('.check').prop('checked',true);
	else
		$('.check').prop('checked',false);
});

//글삭제
$('.btn_mentor_delete').click(function(){
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
</script>