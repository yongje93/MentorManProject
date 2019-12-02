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
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->