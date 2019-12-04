<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<link rel="stylesheet" href="../admincss/adminListAll.css">
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘티 리스트</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<form method="post" action="/mentor/adminmember/adminmenteeSearch">
						<input type="hidden" name="pg" value="${pg }">
						<div class="col-sm-12 memberSearch">
							<input type="text" name="adminmenteeKeyword" placeholder="이름을 입력하세요." style="height:30px;">
							<button type="submit" class="btn btn-success btn-sm">찾기</button>
						</div>
						</form>
					</div> <!-- 검색 row끝 -->
					<div class="row">
						<div class="col-sm-12">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">회원 이름</th>
										<th scope="col">회원 닉네임</th>
										<th scope="col">회원 메일</th>
										<th scope="col">회원 구분</th>
										<th scope="col">회원가입 날짜 </th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="adminmemberDTO" items="${list }">
									<tr>
										<td><img src="../image/${adminmemberDTO.member_profile }" width="30" height="30" style="border-radius: 50%;">${adminmemberDTO.member_name }</td>
										<td>${adminmemberDTO.member_nickname }</td>
										<td>${adminmemberDTO.member_email }</td>
										<c:if test="${adminmemberDTO.member_flag eq '0'}">
										<td>멘티</td>
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
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script>
function adminmenteeSearch(pg){
	$.ajax({
		type : 'post',
		url : '/mentor/adminmember/adminmenteeSearch',
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			pg : pg,
			adminmenteeKeyword : '${adminmenteeKeyword}'
		},
		success : function(){
			console.log("ok");
		}
	});	
	/* location.href="/mentor/adminmember/adminmemberSearch?pg="+pg
				+"&adminmemberKeyword="+encodeURIComponent("${adminmemberKeyword}"); */
}
</script>