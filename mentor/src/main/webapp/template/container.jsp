<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 오늘 날짜 --%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy/MM/dd" />
<style>
.badge {
	right: 0;
	position: absolute;
	margin: 10px 5px 10px 10px;
	vertical-align: middle;
}
</style>
<!-- 배너 관련 -->
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">

<div class="page navbar-fixed intros index">
	<div class="page-content">
	<%-- 배너 시작 --%>
	<div class="owl-carousel owl-theme">
		<a class="banner" type="external" href="/mentor/mentor/mentorapplyForm">
			<div class="photo">
				<div class="cover-image" style="background-image: url('../image/slider/banner01.png')"></div>
				<div class="hello-content">
					<h1 class="title">가치 있는 커리어 경험을 나눠보세요</h1>
					<div class="description">멘토 지원하기</div>
				</div>
			</div>
		</a>
		<a class="banner" type="external" href="/mentor/intern/internMain">
			<div class="photo">
				<div class="cover-image" style="background-image: url('../image/slider/banner02.png')"></div>
				<div class="hello-content">
					<h1 class="title">취업/이직을 하고 싶으신가요?</h1>
					<div class="description">공채 정보 확인</div>
				</div>
			</div>
		</a>
		<a class="banner" type="external" href="/mentor/meetingboard/meetingboardList">
			<div class="photo">
				<div class="cover-image" style="background-image: url('../image/slider/banner03.png')"></div>
				<div class="hello-content">
					<h1 class="title">온/오프라인 멘토링으로 정보를 공유해보세요</h1>
					<div class="description">멘토맨 클래스</div>
				</div>
			</div>
		</a>
	</div>

		<%-- 모임 영역 시작 --%>
	<div class="open-mentoring-block">
		<div class="block-title strong-title">
			모임 <a type="external" href="/mentor/meetingboard/meetingboardList">더 보기</a>
		</div>
		<%--모임 리스트 뿌려주는 영역--%>
		<div class="row no-gap">
			<c:forEach var="meetingboardDTO" items="${meetingboardList}">
				<fmt:parseDate var="parseDate" value="${meetingboardDTO.meetingboard_day}" pattern="yyyy/MM/dd"/>
				<fmt:formatDate var="meetingday" value="${parseDate}" pattern="MM월 dd일 (E)"/>
				<fmt:formatDate var="meetingdayCompare" value="${parseDate}" pattern="yyyy/MM/dd"/>
				<div class="col-100 tablet-50 desktop-33">
					<div class="card open-mentoring-card">
						<div class="thumbnail">
							<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${meetingboardDTO.meetingboard_seq}">
								<img src="../image/job_code/${meetingboardDTO.job_code}.jpg" style="height: 210px;">
							</a>
						</div>
						<div class="card-content card-content-padding">
							<h3 class="title">${meetingboardDTO.meetingboard_title}</h3>
							<div class="description" style="height: 80px;">
							<c:choose>
								<c:when test="${fn:length(meetingboardDTO.meetingboard_subtitle) gt 55}">
									<c:out value="${fn:substring(meetingboardDTO.meetingboard_subtitle, 0, 54)}"/>...
								</c:when>
								<c:otherwise>
									<c:out value="${meetingboardDTO.meetingboard_subtitle}"/>
								</c:otherwise>
							</c:choose>
							</div>
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
											<img src="../storage/${meetingboardDTO.mentor_email}/${meetingboardDTO.member_profile}" width="28" height="28">
										</c:if>
									</div>
									<div class="mentor-name">
										<span>${meetingboardDTO.member_name}</span><small>멘토</small>
									</div>
									<div class="job">
										<small>${meetingboardDTO.mentor_company},${meetingboardDTO.mentor_department}</small>
									</div>
								</a>
								<c:if test="${today <= meetingdayCompare}">
									<c:if test="${meetingboardDTO.meetingboard_state == 0 }">
										<span class="badge ongoing-badge" style="background-color: #ff2d55;">
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
	<%-- 모임 영역 끝 --%>

	<%-- 신규 멘토 영역 시작 --%>
	<div class="mentor-block">
		<div class="block-title strong-title">
			신규 멘토 <a type="external" href="/mentor/mentor/mentorfindForm">더 보기</a>
		</div>
		<div class="row no-gap">
			<c:if test="${mentorList ne '[]'}">
				<c:forEach var="mentor" items="${mentorList}">
					<div class="col-100 tablet-50 desktop-25">
						<div class="card mentor-card">
							<a type="external" href="/mentor/mentor/mentorInfoView?mentors=${mentor.member_seq}">
								<div style="background-image: url()" class="cover-image"></div>
								<div class="mentor-image img-circle">
									<c:if test="${mentor.member_profile != 'profile.jpg'}">
										<img width="50" height="50" src="../storage/${mentor.mentor_email}/${mentor.member_profile}">
									</c:if>
									<c:if test="${mentor.member_profile == 'profile.jpg'}">
										<img width="50" height="50" src="../image/profile.jpg">
									</c:if>
								</div>
								<div class="mentor-info">
									<div class="name">
										<span class="mentor-name">${mentor.member_name}</span> <span class="position">멘토</span>
									</div>
									<div class="job">
										<div>${mentor.mentor_company}</div>
										<div>${mentor.mentor_department}</div>
									</div>
								</div>
							</a>
							<div class="primary-mentoring-info">
								<div class="title">${mentor.job_type}</div>
								<div class="info">${mentor.mentor_represent}</div>
							</div>
							<div class="ask-button">
							<c:if test="${memDTO != null}">
						    	<c:if test="${mentor.mentor_email != memberDTO.member_email}">
								<a class="question button button-small button-fill" id="mentorQuestions" type="external" onclick="mentor_question_seq(${mentor.mentor_seq},${pg})"><!-- pg seq 가져가라 -->
								질문하기
								</a>
								</c:if>
							</c:if>	
							<c:if test="${memDTO == null}">
						        <a class="button button-small button-fill" type="external" href="/mentor/member/loginForm">
							         질문하기
								</a>
							</c:if>	
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			<div class="col-100 desktop-25"></div>
			<div class="col-100 desktop-25"></div>
			<div class="col-100 desktop-25"></div>
		</div>
	</div>
	<%-- 신규 멘토 영역 끝 --%>

	<%-- 명예 멘토 영역 시작 --%>
	<div class="mentor-block">
		<div class="block-title strong-title">명예 멘토 <a type="external" href="/mentor/mentor/mentorfindForm?pg=1&bestFlag=1">더 보기</a>
		</div>
		<div class="row no-gap">
			<c:forEach var="honorMentor" items="${honorMentorList}">
			<div class="col-100 tablet-50 desktop-25">
				<div class="card mentor-card">
					<a type="external" href="/mentor/mentor/mentorInfoView?mentors=${honorMentor.member_seq}">
						<div style="background-image: url()" class="cover-image"></div>
						<div class="mentor-image img-circle">
							<c:if test="${honorMentor.member_profile != 'profile.jpg'}">
								<img width="50" height="50" src="../storage/${honorMentor.mentor_email}/${honorMentor.member_profile}">
							</c:if>
							<c:if test="${honorMentor.member_profile == 'profile.jpg'}">
								<img width="50" height="50" src="../image/profile.jpg">
							</c:if>
						</div>
						<div class="mentor-info">
							<div class="name">
								<span class="mentor-name">${honorMentor.member_name}</span> <span class="position">멘토</span>
								<i class="fas fa-trophy highlight"></i>
							</div>
							<div class="job">
								<div>${honorMentor.mentor_company}</div>
								<div>${honorMentor.mentor_department}</div>
							</div>
						</div>
					</a>
					<div class="primary-mentoring-info">
						<div class="title">${honorMentor.job_type}</div>
						<div class="info">${honorMentor.mentor_represent}</div>
					</div>
					<div class="ask-button">
				   	<c:if test="${memDTO != null}">
				    	<c:if test="${honorMentor.mentor_email != memberDTO.member_email}">
					        <a class="question button button-small button-fill" id="mentorQuestions" type="external" onclick="mentor_question_seq(${honorMentor.mentor_seq},${pg})">
						          질문하기
							</a>
						</c:if>
					</c:if>	
					<c:if test="${memDTO == null}">
				        <a class="button button-small button-fill" type="external" href="/mentor/member/loginForm">
					          질문하기
						</a>
					</c:if>	
					</div>
				</div>
			</div>
			</c:forEach>
			<div class="col-100 desktop-25"></div>
			<div class="col-100 desktop-25"></div>
			<div class="col-100 desktop-25"></div>
		</div>
	</div>
	<%-- 명예 멘토 영역 끝 --%>

	<%-- 중간 이미지 시작 --%>
	<div class="row no-gutter new-mentor-block-wrapper text-align-center">
		<div class="col-100 tablet-50">
			<div class="img-gradient">
				<img src="../image/mentor_hero-fb0fabb03ac9a924cc639d018d7f1520d49c3f0f1bef7ef871a6c5141658a781.jpg"/>
			</div>
		</div>
		<div class="col-100 tablet-50 text-block-wrapper">
			<div class="text-block">
				<h2 class="title">
					가치 있는<br>커리어 경험을<br>공유해 보세요
				</h2>
				<div class="block button-block">
					<a class="button button-big button-fill button-inline" type="external" href="/mentor/mentor/mentorapplyForm">멘토 지원하기</a>
				</div>
			</div>
		</div>
	</div>
	<%-- 중간 이미지 끝 --%>

	<%-- 추천 에세이 시작 --%>
	<div class="mentor-post-block">
		<div class="block-title strong-title">
			추천 에세이 <a type="external" href="/mentor/essayboard/essayboardList?essayFlag=1">더 보기</a>
		</div>
		<div class="row no-gap">
		<c:forEach var="list" items="${bestEssayList}">
			<input type="hidden" id="job_code" value="${list.job_code}">
			<div class="col-100 tablet-50 desktop-33">
				<div class="card mentor-post-card">
					<div class="card-header">
						<a class="color-black" type="external" href="/mentor/mentor/mentorInfoView?mentors=${list.member_seq}">
							<div>
								<div class="mentor-image img-circle">
									<c:if test="${list.member_profile != 'profile.jpg'}">
										<img width="50" height="50"	src="../storage/${list.mentor_email}/${list.member_profile}">
									</c:if>
									<c:if test="${list.member_profile == 'profile.jpg'}">
										<img width="50" height="50" src="../image/profile.jpg">
									</c:if>
								</div>

								<div class="mentor-info">
									<div><strong class="mentor-name">${list.member_name}</strong><small>멘토</small></div>
									<div class="job"><small> ${list.mentor_company} · ${list.mentor_department}</small></div>
								</div>
							</div>
						</a>
						<div class="created-at">
							<small>${list.essayboard_logtime}</small>
						</div>
					</div>
					<div class="card-content card-content-padding" style="overflow: hidden; text-overflow: ellipsis; height: 200px;">
						<input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
						<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${pg }&seq=${list.essayboard_seq}&mentors=${list.member_seq }">
							<div class="mentor-post-title">${list.essayboard_title}
							</div>
							<div class="mentor-post-detail">
								<c:choose>
									<c:when test="${fn:length(list.essayboard_content) gt 200}">
										<c:out value='${fn:substring(list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 190)}' />...
									</c:when>
									<c:otherwise>
										<c:out value='${list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;","")}' />
									</c:otherwise>
								</c:choose>
							</div>
						</a>
					</div>
					<div class="card-footer">
						<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow" 
							data-method="post" href="" style="right: 0px; position: unset; margin: 0px 0px;"> 
							<!-- <i class="far fa-bookmark" aria-hidden="false"></i> -->
							<c:if test="${list.essayboard_scrapFlag == 1}">
								<img id="${list.essayboard_seq}" src="../image/scrapOkImg.png" width="13">
							</c:if> <c:if test="${list.essayboard_scrapFlag == 0}">
								<img id="${list.essayboard_seq}" src="../image/scrapNoImg.png" width="13">
							</c:if> 
							<span id="ScrapDiv_${list.essayboard_seq}">${list.essayboard_scrap}</span>
							<!-- 스크랩 끌고와야 함 --> 
							<input type="hidden" id="scrapFlag"	name="scrapFlag" value="${list.essayboard_scrapFlag}">
						</a>
						<div class="created-at"></div>
					</div>
				</div>
			</div>
			</c:forEach>
			<div class="col-100 tablet-50 desktop-33"></div>
			<div class="col-100 tablet-50 desktop-33"></div>
		</div>
	</div>
	<%-- 추천 에세이 끝 --%>

	<%-- 신규 에세이 시작 --%>
	<div class="mentor-post-block">
		<div class="block-title strong-title">
			신규 에세이 <a type="external" href="/mentor/essayboard/essayboardList">더 보기</a>
		</div>
		<div class="row no-gap">
			<c:forEach var="list" items="${newEssayList }">
				<input type="hidden" id="job_code" value="${list.job_code}">
				<div class="col-100 tablet-50 desktop-33">
					<div class="card mentor-post-card">
						<div class="card-header">
							<a class="color-black" type="external" href="/mentor/mentor/mentorInfoView?mentors=${list.member_seq }">
								<div>
									<div class="mentor-image img-circle">
										<c:if test="${list.member_profile != 'profile.jpg'}">
											<img width="50" height="50" src="../storage/${list.mentor_email}/${list.member_profile}">
										</c:if>
										<c:if test="${list.member_profile == 'profile.jpg'}">
											<img width="50" height="50" src="../image/profile.jpg">
										</c:if>
									</div>
									<div class="mentor-info">
										<div>
											<strong class="mentor-name">${list.member_name }</strong> <small>멘토</small>
										</div>
										<div class="job">
											<small> ${list.mentor_company } · ${list.mentor_department } </small>
										</div>
									</div>
								</div>
							</a>
							<div class="created-at">
								<small>${list.essayboard_logtime }</small>
							</div>
						</div>

						<div class="card-content card-content-padding" style="overflow: hidden; text-overflow: ellipsis; height: 200px;">
							<input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
							<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${pg }&seq=${list.essayboard_seq}&mentors=${list.member_seq }">
								<div class="mentor-post-title">${list.essayboard_title }</div>
								<div class="mentor-post-detail">
								<c:choose>
									<c:when test="${fn:length(list.essayboard_content) gt 200}">
										<c:out value='${fn:substring(list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 190)}' />...
									</c:when>
									<c:otherwise>
										<c:out value='${list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;","")}' />
									</c:otherwise>
								</c:choose>
								</div>
							</a>
						</div>
						<div class="card-footer">
							<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow"
								data-method="post" href="" style="right: 0px; position: unset; margin: 0px 0px;"> <!-- <i class="far fa-bookmark" aria-hidden="false"></i> -->
								<c:if test="${list.essayboard_scrapFlag == 1}">
									<img id="${list.essayboard_seq}" src="../image/scrapOkImg.png" width="13">
								</c:if> <c:if test="${list.essayboard_scrapFlag == 0}">
									<img id="${list.essayboard_seq}" src="../image/scrapNoImg.png" width="13">
								</c:if> 
								<span id="ScrapDiv_${list.essayboard_seq}">${list.essayboard_scrap}</span>
							 	<input type="hidden" id="scrapFlag"	name="scrapFlag" value="${list.essayboard_scrapFlag}">
							</a>
							<div class="created-at"></div>
						</div>
					</div>
				</div>
			</c:forEach>
			<div class="col-100 tablet-50 desktop-33"></div>
			<div class="col-100 tablet-50 desktop-33"></div>
			<input type="hidden" id="memNickname" name="memNickname" value="${memDTO.member_name}">
		</div>
	</div>
	<%-- 신규 에세이 끝 --%>
	</div>
</div>
<script src="../js/mentor.js"></script>
<script src="../js/container.js"></script>
<script src="../js/owl.carousel.min.js"></script>