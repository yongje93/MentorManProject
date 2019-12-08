<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
										<th scope="col">멘토이름</th>
										<th scope="col">멘토분류</th>
										<th scope="col">매출</th>
										<th scope="col">총 환급금</th>										
									</tr>
								</thead>
								<tbody>
								<c:forEach var="salesDTO" items="${salesList }">
									<tr>
										<c:if test="${salesDTO.member_profile != 'profile.jpg'}">
											<td><img src="../storage/${salesDTO.mentor_email}/${salesDTO.member_profile}" width="30" height="30" style="border-radius: 50%;">${salesDTO.member_name }</td>
										</c:if>
										<c:if test="${salesDTO.member_profile == 'profile.jpg'}">
											<td><img src="../image/profile.jpg" width="30" height="30" style="border-radius: 50%;">${salesDTO.member_name }</td>
										</c:if>
										<c:if test="${salesDTO.mentor_badge eq '0'}">
										<td>멘토</td>
										</c:if>
										<c:if test="${salesDTO.mentor_badge eq '1'}">
										<td>명예멘토</td>
										</c:if>
										<td><fmt:formatNumber value="${salesDTO.sales}" type="number"/></td>
										<c:if test="${salesDTO.mentor_badge eq '0'}">
										<td><fmt:parseNumber value="${salesDTO.sales * 0.9}" integerOnly="true"/></td>
										</c:if>
										<c:if test="${salesDTO.mentor_badge eq '1'}">
										<td><fmt:parseNumber value="${salesDTO.sales * 0.95}" integerOnly="true"/></td>
										</c:if>
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
<script src="../adminjs/honorApply.js"></script>