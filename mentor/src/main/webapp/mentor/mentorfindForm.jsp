<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page navbar-fixed mentors index" data-name="mentors-index">
  <div class="page-content">
    <!-- <div class="block job-type-block">
      <div class="block-title">
        직무 유형
      </div>

      <div class="row">
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=13">인사/총무/노무</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=9">마케팅/MD</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=19">홍보/CSR</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=12">영업/영업관리</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=14">회계/재무/금융</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=20">해외/기술영업</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=6">유통/무역/구매</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=18">전략/기획</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=17">IT개발</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=7">서비스 기획/UI, UX등</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=11">디자인/예술</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=4">미디어</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=5">서비스</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=8">연구/설계</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=2">전문/특수</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=16">교육/상담/컨설팅</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=15">공무원/공공/비영리</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=3">생산/품질/제조</a>
          <a class="button color-gray " type="external" href="/mentors?job_type%5B%5D=1">기타 사무</a>
      </div>
    </div> -->

    <!-- <div class="block job-type-block">
      <div class="block-title">
        멘토링 유형
      </div>

      <div class="row">
        <div class="row">
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=1">직무</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=7">진로</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=4">스펙</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=3">외국어</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=5">면접/자소서</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=15">회사생활</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=10">창업</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=17">이직</a>
            <a class="button color-gray " type="external" href="/mentors?mentoring_type%5B%5D=8">기타</a>
        </div>
      </div>
    </div> -->

<div class="mentor-block">
   <div class="block-title strong-title">
       멘토
    <a class="button color-gray" type="external" href="/mentors?hall_of_fame=true">
    	<i class="fas fa-trophy"></i> 명예 멘토
	</a>
   </div>

   <div class="row no-gap">
<c:if test="${list ne '[]'}">
	<c:forEach var="mentor" items="${list}" >
	<div class="col-100 tablet-50 desktop-25">
		  <div class="card mentor-card">
		<a id="mentorProfileView" type="external" href="/mentors/50121"><!-- 태형이 url -->
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
		   	<div class="info">${mentor.mentor_represent}</div>
		</div>
		    <div class="ask-button">
		        <a class="button button-small button-fill" type="external" href="/mentor/mentor/mentorQuestionsForm?pg=${pg}&seq=${mentor.mentor_seq}"><!-- pg seq 가져가라 -->
			          질문하기
				</a>    
			</div>
		</div>
	</div>
	</c:forEach>
</c:if>		
	        <div class="col-100 desktop-25"></div>
	        <div class="col-100 desktop-25"></div>
	        <div class="col-100 desktop-25"></div>
	        
      </div>
		<div style="float: right;">${mentorfindPaging.pagingHTML}</div>
    </div>
  </div>
</div>