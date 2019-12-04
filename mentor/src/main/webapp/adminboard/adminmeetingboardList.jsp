<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 오늘 날짜 --%>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="today" value="${now}" pattern="yyyy/MM/dd"/>

<input type="hidden" id="pg" value="${pg}">
<input type="hidden" id="seq" value="${seq}">
<!-- 공지사항 리스트 -->
<link rel="stylesheet" href="../admincss/adminListAll.css">
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>모임 게시판</h2>
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
										<th scope="col">글 번호</th>
										<th scope="col">멘토</th>
										<th scope="col">제목</th>
										<th scope="col">장소</th>
										<th scope="col">일시</th>
										<th scope="col">주최</th>
										<th scope="col">회사/부서</th>
										<th scope="col">모집상황</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="meetingboardDTO" items="${meetingboardList }">
								<fmt:parseDate var="parseDate" value="${meetingboardDTO.meetingboard_day}" pattern="yyyy/MM/dd"/>
								<fmt:formatDate var="meetingday" value="${parseDate}" pattern="MM월 dd일 (E)"/>
								<fmt:formatDate var="meetingdayCompare" value="${parseDate}" pattern="yyyy/MM/dd"/>	
									<tr>
										<td>${meetingboardDTO.meetingboard_seq }</td>
										<td>
											<c:if test="${meetingboardDTO.member_profile == 'profile.jpg'}">
											<img src="../image/profile.jpg" width="28" height="28">
											</c:if>
											<c:if test="${meetingboardDTO.member_profile != 'profile.jpg'}">
											<img src="../storage/${meetingboardDTO.mentor_email}/${meetingboardDTO.member_profile}" width="28" height="28">
											</c:if>
											${meetingboardDTO.member_name }
										</td>
										<td><a href="/mentor/meetingboard/meetingboardView?pg=${pg}&seq=${meetingboardDTO.meetingboard_seq}">${meetingboardDTO.meetingboard_title }</a></td>
										<td>${meetingboardDTO.meetingboard_address }</td>
										<td>${meetingday }</td>
										<td>${meetingboardDTO.meetingboard_host }</td>
										<td>${meetingboardDTO.mentor_company},${meetingboardDTO.mentor_department}</td>
										<td>
											<c:if test="${today <= meetingdayCompare}">
												<c:if test="${meetingboardDTO.meetingboard_state == 0 }">
													모집중
												</c:if>
												<c:if test="${meetingboardDTO.meetingboard_state == 1 }">
													모집완료
												</c:if>
											</c:if>
											<c:if test="${today > meetingdayCompare}">
												종료
											</c:if>
										</td>
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
									<li class="page-item">${meetingboardPaging.pagingHTML }</li>
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
<script src="../adminjs/meetingboard.js"></script>