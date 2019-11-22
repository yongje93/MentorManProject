<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page navbar-fixed questioner_qa_threads show" data-name="questioner_qa_threads-show">
  <div class="page-content">
    <div class="qa-thread-block-container">
      <div class="block mentor-info-block">
		  <div class="mentor-image img-circle">
		      <a type="external" href="/mentors/43427">
		        <c:if test="${mentorDTO.member_profile != 'profile.jpg'}">
			      <img width="100" height="100" src="../storage/${mentor.mentor_email}/${mentor.member_profile}">
			    </c:if>
			    <c:if test="${mentorDTO.member_profile == 'profile.jpg'}">
			      <img width="100" height="100" src="../image/profile.jpg">
			    </c:if>
			  </a>  
		  </div>

		  <div class="block mentor-info">
		    <div class="name">
		      <span class="mentor-name">${mentorDTO.member_name}<small>멘토</small></span>
		
		        <a class="button col js-bookmark user_43427" data-params="followed_id=43427" data-disable-with="..." type="external" data-remote="true" rel="nofollow" data-method="post" href="/relationships">팔로우</a>
		
		        <a class="button button-small button-fill" type="external" href="/mentor/member/myQuestionsForm?pg=${pg}&seq=${seq}&qsseq=${qsseq}">질문하기</a></div>
		
		    <div class="job">
		      ${mentorDTO.mentor_company} · ${mentorDTO.mentor_department}
		    </div>
		
		      <div class="detail-block">
		        <div class="mentoring-info">
		          <div class="title text-decoration-underline">
		            	대표 멘토링 분야
		          </div>
		
		          <div class="mentoring-type-block">
		            	${mentorDTO.mentor_represent}
		          </div>
		        </div>
		
		        <div class="mentoring-info">
		          <div class="title">
		            멘토링 분야
		          </div>
		
		          <div class="mentoring-type-block">
		            <a type="external" href="/mentors?job_type%5B%5D=18">
		              <div class="chip chip-outline no-border-radius">
		                <div class="chip-label">
		                 	 ${mentorDTO.job_type}
		                </div>
		              </div>
					</a>
					<c:forEach var="mentor" items="${list}">
		            <a type="external" href="/mentors?mentoring_type%5B%5D=1">
		               <div class="chip chip-outline no-border-radius">
		                 <div class="chip-label">
		                  	 ${mentor.mentoring_type}
		                 </div>
		               </div>
			   	  	</a>     
			   	  	</c:forEach>   
					</div>
		        </div>
		      </div>
		  </div>
		</div>

		<div class="question-answer-block">
		   <div class="separator-block text-align-center">
		       <h3 class="separator-text">
		         <span class="line-center">
		          	 나의 질문/답변
		         </span>
		       </h3>
		   </div>

		   <div class="card qa-thread-card question-card">
			<div class="card-header">
			  <div class="left">질문</div>
			  <div class="created-at">
			    <small></small>
			  </div>
			</div>
			<div class="card-content card-content-padding">
			  <div class="content-body">
			    <div class="title">
			    	<pre style="white-space: pre-line;"><c:out value="${mentorDTO.question_title}" /></pre>
				</div>
			
				<div class="detail trix-content">
					<pre style="white-space: pre-line;"><c:out value="${mentorDTO.question_content}"/></pre>	
			    </div>
			  </div>
			</div>
		    <div class="card-footer action-block">
			      <a class="button button-small color-black" type="external" href="/mentor/mentor/mentorQuestionsForm?pg=${pg}&seq=${seq}&qsseq=${qsseq}">수정</a>
			      <a class="button button-small color-black" type="external" id="delete_question">삭제</a> 
			</div>
		  </div>
       </div>
    </div>
  </div>
</div>
<input type="hidden" id="question_seq" value="${qsseq}">
<script src="../js/member.js"></script>