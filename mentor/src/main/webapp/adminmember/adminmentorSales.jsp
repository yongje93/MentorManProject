<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘토 매출 리스트</h2>
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
										<th scope="col">멘토이름</th>
										<th scope="col">매출</th>
										<th scope="col">멘토가 쓴 모임 글 수</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="salesDTO" items="${salesList }">
									<tr>
										<td><input type="checkbox" class="check"value="${salesDTO.mentor_seq }"></td>
										<td><img src="../image/${salesDTO.member_profile }" width="20" height="20" style="border-radius: 50%;">${salesDTO.member_name }</td>
										<td>${salesDTO.sales}</td>
										<c:forEach var="salesBoardDTO" items="${boardList }">
										<c:if test="${salesBoardDTO.member_name eq salesDTO.member_name }">
										<td>${salesBoardDTO.cnt }</td>
										</c:if>
										</c:forEach>
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
						<button type="button" class="btn btn-success btn-sm btn_honor_success">명예 멘토승인</button>
					</div>
				</div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script src="../adminjs/honorApply.js"></script>