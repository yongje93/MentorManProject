<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="page navbar-fixed mentors index" data-name="mentors-index">
  <div class="page-content">
    <div class="block job-type-block">
      <div class="block-title">
        직무 유형
      </div>
      <div class="row">
         <a id="job_code_0" class="button color-gray " type="external" href="job_code_0">인사/총무/노무</a>
         <a id="job_code_1" class="button color-gray " type="external" href="job_code_1">마케팅/MD</a>
         <a id="job_code_2" class="button color-gray " type="external" href="job_code_2">홍보/CSR</a>
         <a id="job_code_3" class="button color-gray " type="external" href="job_code_3">영업/영업관리</a>
         <a id="job_code_4" class="button color-gray " type="external" href="job_code_4">회계/재무/금융</a>
         <a id="job_code_5" class="button color-gray " type="external" href="job_code_5">해외/기술영업</a>
         <a id="job_code_6" class="button color-gray " type="external" href="job_code_6">유통/무역/구매</a>
         <a id="job_code_7" class="button color-gray " type="external" href="job_code_7">전략/기획</a>
         <a id="job_code_8" class="button color-gray " type="external" href="job_code_8">IT개발</a>
         <a id="job_code_9" class="button color-gray " type="external" href="job_code_9">서비스 기획/UI, UX등</a>
         <a id="job_code_10" class="button color-gray " type="external" href="job_code_10">디자인/예술</a>
         <a id="job_code_11" class="button color-gray " type="external" href="job_code_11">미디어</a>
         <a id="job_code_12" class="button color-gray " type="external" href="job_code_12">서비스</a>
         <a id="job_code_13" class="button color-gray " type="external" href="job_code_13">연구/설계</a>
         <a id="job_code_14" class="button color-gray " type="external" href="job_code_14">전문/특수</a>
         <a id="job_code_15" class="button color-gray " type="external" href="job_code_15">교육/상담/컨설팅</a>
         <a id="job_code_16" class="button color-gray " type="external" href="job_code_16">공무원/공공/비영리</a>
         <a id="job_code_17" class="button color-gray " type="external" href="job_code_17">생산/품질/제조</a>
         <a id="job_code_18" class="button color-gray " type="external" href="job_code_18">기타 사무</a>

  	</div>
   </div>

<div class="mentor-block">
   <div class="block-title strong-title">
   <div class="mentor_div">
     멘토
   </div>
    <a class="button color-gray" type="external" id="honor_mentor">
    	<i class="fas fa-trophy"></i> 명예 멘토
	</a>

   </div>

	   <div class="row no-gap" id="mentor_findList">
		<c:if test="${list ne '[]'}">
			<c:forEach var="mentor" items="${list}" >
			<div class="col-100 tablet-50 desktop-25">
				  <div class="card mentor-card">
				<a id="mentorProfileView" type="external" href="/mentor/mentor/mentorInfoView?pg=${pg}&mentors=${mentor.member_seq}"><!-- 태형이 url -->
					<div style="background-image:url()" class="cover-image"></div>
					
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
				        <span class="mentor-name">${mentor.member_name}</span>
				        <span class="position">멘토</span>
				      </div>
				      
				      <div class="job">
				        <div>${mentor.mentor_company}</div>
				        <div>${mentor.mentor_department }</div>
				      </div>
				    </div>
				  </a>
				<div class="primary-mentoring-info">
				  	<div class="title">${mentor.job_type}</div>
				  	<div class="info">
				  	<c:choose>
						<c:when test="${fn:length(mentor.mentor_represent) gt 50}">
							<c:out value='${fn:substring(mentor.mentor_represent.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 40)}' />...
						</c:when>
						<c:otherwise>
							<c:out value='${mentor.mentor_represent.replaceAll("\\\<.*?\\\>|&nbsp;","")}' />
						</c:otherwise>
					</c:choose>
				   	</div>
				</div>
				    <div class="ask-button">
					  	<c:if test="${memDTO.member_email != mentor.mentor_email}">
						   <c:if test="${memDTO != null}">
						   		<c:if test="${memDTO.member_flag == 0}">
							   		<a class="question button button-small button-fill" id="mentorQuestions" type="external" href="/mentor/mentor/userInfoCheck">
								          질문하기
									</a>
								</c:if>
								<c:if test="${memDTO.member_flag == 1 or memDTO.member_flag == 2}">
							   		 <a class="question button button-small button-fill" id="mentorQuestions" type="external" onclick="mentor_question_seq(${mentor.mentor_seq},${pg})"><!-- pg seq 가져가라 -->
								          질문하기
									</a>
								</c:if>
							</c:if>
							<c:if test="${memDTO == null}">
						        <a class="button button-small button-fill" type="external" href="/mentor/member/loginForm"><!-- pg seq 가져가라 -->
							          질문하기
								</a>
							</c:if>
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
		<div class="pagination-block">
			<div class="page-entries-info"></div>
			<div class="paging" id="paging">${mentorfindPaging.pagingHTML }</div>
		</div>
	</div>
</div>
<input type="hidden" id="memNick" value="${memDTO.member_nickname}">
<input type="hidden" id="mentorFlag" name="mentorFlag" value="${flag}">
<input type="hidden" id="bestFlag" value="${param.bestFlag}">
<script src="../js/mentor.js"></script>