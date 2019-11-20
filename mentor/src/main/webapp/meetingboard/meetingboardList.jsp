<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 오늘 날짜 --%>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="today" value="${now}" pattern="yyyy/MM/dd"/>

<input type="hidden" id="pg" value="${pg}">
<input type="hidden" id="seq" value="${seq}">

<div class="page navbar-fixed mentee_programs index">
	<div class="page-content">
		<div class="pogram-block">
			<div class="block-title strong-title">
				모임
				<%-- 접속한 유저가 멘토일 때 모임 작성 버튼 --%>
				<c:if test="${memDTO.member_email != null && memDTO.member_flag == 1}">
				<button class="button">
					<a type="external" href="/mentor/meetingboard/meetingboardWriteForm"> 모임 작성 </a>
				</button>
				</c:if>
			</div>
			<%--모임 리스트 뿌려주는 영역--%>
			<div class="row no-gap">
				<c:forEach var="meetingboardDTO" items="${meetingboardList}">
					<fmt:parseDate var="parseDate" value="${meetingboardDTO.meetingboard_day}" pattern="yyyy/MM/dd"/>
					<fmt:formatDate var="meetingday" value="${parseDate}" pattern="MM월 dd일 (E)"/>
					<fmt:formatDate var="meetingdayCompare" value="${parseDate}" pattern="yyyy/MM/dd"/>	
					<div class="col-100 tablet-50 desktop-33">
						<div class="card program-card">
							<div class="thumbnail">
								<a type="external" href="/mentor/meetingboard/meetingboardView?pg=${pg}&seq=${meetingboardDTO.meetingboard_seq}">
									<img src="../image/job_code/${meetingboardDTO.job_code}.jpg" style="height: 210px;">
								</a>
							</div>
							<div class="card-content card-content-padding">
								<h3 class="title">${meetingboardDTO.meetingboard_title}</h3>
								<div class="description">${meetingboardDTO.meetingboard_subtitle}</div>
								<div class="list">
									<ul>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">장소</div>
													<div class="item-after">${meetingboardDTO.meetingboard_address}</div>
												</div>
											</div>
										</li>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">일시</div>
													<div class="item-after">${meetingday}</div>
												</div>
											</div>
										</li>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">주최</div>
													<div class="item-after">${meetingboardDTO.meetingboard_host}</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
								<div class="mentor-profile">
									<a class="mentor-info" type="external" href="/mentor/mentor/mentorInfoView?mentors=${meetingboardDTO.member_seq}">
										<div class="mentor-image img-circle">
											<c:if test="${meetingboardDTO.member_profile == 'profile.jpg'}">
											<img src="../image/profile.jpg" width="28" height="28">
											</c:if>
											<c:if test="${meetingboardDTO.member_profile != 'profile.jpg'}">
											<img src="../storage/${meetingboardDTO.member_email}/${meetingboardDTO.member_profile}" width="28" height="28">
											</c:if>
										</div>
										<div class="mentor-name">
											<span>${meetingboardDTO.member_name}</span>
											<small>멘토</small>
										</div>
										<div class="job">
											<small>${meetingboardDTO.mentor_company},${meetingboardDTO.mentor_department}</small>
										</div>
									</a>
									<c:if test="${today <= meetingdayCompare}">
										<c:if test="${meetingboardDTO.meetingboard_state == 0 }">
											<span class="badge ongoing-badge">
												<div>모집중</div>
											</span>
										</c:if>
										<c:if test="${meetingboardDTO.meetingboard_state == 1 }">
											<span class="badge">
												<div>모집완료</div>
											</span>
										</c:if>
									</c:if>
									<c:if test="${today > meetingdayCompare}">
										<span class="badge">
											<div>종료</div>
										</span>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="col-100 tablet-50 desktop-33"></div>
				<div class="col-100 tablet-50 desktop-33"></div>
			</div>
		</div>
		<div class="pagination-block">
			<div class="page-entries-info"></div>
			${meetingboardPaging.pagingHTML}
		</div>
	</div>
</div>