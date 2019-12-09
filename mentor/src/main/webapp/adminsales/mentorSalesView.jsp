<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>멘토 월별 환급금</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<div class="col-sm-8">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th scope="col">환급 금액</th>
										<th scope="col">월별</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="viewDTO" items="${list }">
									<tr class="success">
										<td>${viewDTO.sales }</td>
										<td>${viewDTO.order_date }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div> <!-- table row 끝-->
				</div> <!-- table 감싸는 div-->
				<div class="ln_solid"></div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->