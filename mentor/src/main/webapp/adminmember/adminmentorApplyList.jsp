<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘토신청대기 리스트</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<div class="col-sm-12 memberSearch">
							<input type="text" placeholder="찾기" style="height:30px;">
							<button type="button" class="btn btn-success btn-sm">찾기</button>
						</div>
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
										<th scope="col">승인 유무</th>
										<th scope="col">회원가입 날짜</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="adminmentorDTO" items="${list }">
								<c:if test="${adminmentorDTO.mentor_flag eq '0' }">
									<tr>
										<td><input type="checkbox" class="check"value="${adminmentorDTO.mentor_seq }"></td>
										<td><img src="../image/${adminmentorDTO.member_profile }" width="30" height="30">${adminmentorDTO.member_name }</td>
										<td>${adminmentorDTO.mentor_company }</td>
										<td>${adminmentorDTO.mentor_department }</td>
										<td>${adminmentorDTO.job_type }</td>
										<td>승인대기</td>
										<td>${adminmentorDTO.mentor_logtime }</td>
									</tr>
								</c:if>
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
						<button type="button" class="btn btn-success btn-sm btn_apply_success">승인</button>
						<button type="button" class="btn btn-danger btn-sm btn_apply_reject">승인거절</button>
					</div>
				</div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script type="text/javascript" src="../adminjs/adminmentorApply.js"></script>
