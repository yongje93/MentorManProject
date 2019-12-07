<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘토맨회원 리스트</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<form method="post"  action="/mentor/adminmember/adminmemberSearch">
						<input type="hidden" name="pg" value="${pg }">
						<div class="col-sm-12 memberSearch">
							<input type="text" name="adminmemberKeyword" placeholder="이름을 입력하세요." style="height:30px;">
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
										<th scope="col">회원 이름</th>
										<th scope="col">회원 닉네임</th>
										<th scope="col">회원 메일</th>
										<th scope="col">회원 구분&emsp;<span class="sort" style="cursor:pointer;"><i class="fas fa-sort"></i></span></th>
										<th scope="col">회원가입 날짜 </th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="adminmemberDTO" items="${list }">
									<tr>
										<td><input type="checkbox" class="check"value="${adminmemberDTO.member_seq }"></td>
										<td><img src="../image/${adminmemberDTO.member_profile }" width="30" height="30" style="border-radius: 50%;">${adminmemberDTO.member_name }</td>
										<td>${adminmemberDTO.member_nickname }</td>
										<td>${adminmemberDTO.member_email }</td>
										<c:if test="${adminmemberDTO.member_flag eq '0'}">
										<td>회원</td>
										</c:if>
										<c:if test="${adminmemberDTO.member_flag eq '1'}">
										<td>멘토</td>
										</c:if>
										<c:if test="${adminmemberDTO.member_flag eq '2'}">
										<td>멘티</td>
										</c:if>
										<c:if test="${adminmemberDTO.member_flag eq '3'}">
										<td>관리자</td>
										</c:if>
										<td>${adminmemberDTO.logtime }</td>
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
						<button type="button" class="btn btn-danger btn-sm memberDeleteBtn" ><i class="fa fa-trash-o"></i>회원 삭제</button>
					</div>
				</div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script>
function adminmemberSearch(pg){
	$.ajax({
		type : 'post',
		url : '/mentor/adminmember/adminmemberSearch',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			pg : pg,
			adminmemberKeyword : '${adminmemberKeyword}'
		},
		success : function(){
			console.log("ok");
		}
	});	
}

//내림차순 오름차순 view
$(function(){
	const state = ${state};
	if(state==1)
		$(".sort > i").attr('class','fas fa-sort-down');
	else if(state==2)
		$(".sort > i").attr('class','fas fa-sort-up');
});
//내림차순 오름차순 List
$(".sort").click(function(){
	const state = ${state};
	if(state == 0){
		location.href="/mentor/adminmember/adminmemberList?pg=${pg}&state="+1;
		$(".sort > i").attr('class','fas fa-sort-down');
	}
	else if(state == 1){
		$(".sort > i").attr('class','fas fa-sort-up');
		location.href="/mentor/adminmember/adminmemberList?pg=${pg}&state="+2;
	}
	else if(state == 2){
		$(".sort > i").attr('class','fas fa-sort-up');
		location.href="/mentor/adminmember/adminmemberList?pg=${pg}&state="+1;
	}
});
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
toastr.info("승인하시겠습니까?<br /><br /><button type=button class=deleteMember style=color:black>Yes</button>");
}

function deletemember(check){
	delete_info();
	$('.deleteMember').click(function(){
		$.ajax({
			type : 'post',
			url : '/mentor/adminmember/deleteMember',
			data : 'check='+check,
			success : function(){
				location.href="/mentor/adminmember/adminmemberList";
			}
		});	
	});
}

$('.memberDeleteBtn').click(function(){
	var cnt = $('.check:checked').length;
	var check = Array();
	$('.check:checked').each(function(idx){
		check[idx] = $(this).val();
	});
	if(cnt==0)
		toastr_wran();
	else
		deletemember(check);		
});
</script>