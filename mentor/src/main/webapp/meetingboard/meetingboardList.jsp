<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<input type="hidden" id="pg" value="${pg}">
<input type="hidden" id="seq" value="${seq}">

<div class="page navbar-fixed mentee_programs index">
	<div class="page-content">
		<div class="pogram-block">
			<div class="block-title strong-title">
				모임
				<%-- 접속한 유저가 멘토일 때 모임 작성 버튼 --%>
				<%-- <c:if test="memEmail != null && memFlag == 2"> --%>
				<button class="button color-gray">
					<a class="color-gray" type="external" href="/mentor/meetingboard/meetingboardWriteForm"> 모임 작성 </a>
				</button>
				<%-- </c:if> --%>
			</div>
			<%--모임 리스트 뿌려주는 영역--%>
			<div class="row no-gap">
				<c:forEach var="meetingboardDTO" items="${meetingboardList}">
					<div class="col-100 tablet-50 desktop-33">
						<div class="card program-card">
							<div class="thumbnail">
								<a type="external" href="/mentor/meetingboard/meetingboardView?pg=${pg}&seq=${meetingboardDTO.meeting_seq}">
									<img src="../image/job_code/${meetingboardDTO.job_code}.jpg" style="height: 210px;">
								</a>
							</div>
							<div class="card-content card-content-padding">
								<h3 class="title">${meetingboardDTO.title}</h3>
								<div class="description">${meetingboardDTO.subtitle}</div>
								<div class="list">
									<ul>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">장소</div>
													<div class="item-after">${meetingboardDTO.address}</div>
												</div>
											</div>
										</li>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">일시</div>
													<div class="item-after">${meetingboardDTO.day}</div>
												</div>
											</div>
										</li>
										<li>
											<div class="item-content">
												<div class="item-inner">
													<div class="item-title">주최</div>
													<div class="item-after">${meetingboardDTO.host}</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
								<div class="mentor-profile">
									<a class="mentor-info" type="external" href="">
										<div class="mentor-image img-circle">
											<img src="">
										</div>
										<div class="mentor-name">
											<span>멘토이름</span>
											<small>멘토</small>
										</div>
										<div class="job">
											<small>직장</small>
										</div>
									</a>
									<c:if test="${meetingboardDTO.state == 0 }">
										<span class="badge ongoing-badge">
											<div>모집중</div>
										</span>
									</c:if>
									<c:if test="${meetingboardDTO.state == 1 }">
										<span class="badge">
											<div>모집완료</div>
										</span>
									</c:if>
									<c:if test="${meetingboardDTO.state == 2 }">
										<span class="badge">
											<div>종료</div>
										</span>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>	
			</div>
		</div>
		<div class="pagination-block">
			<div class="page-entries-info"></div>
			${meetingboardPaging.pagingHTML}
		</div>
	</div>
</div>