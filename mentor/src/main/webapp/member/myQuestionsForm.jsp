<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page navbar-fixed questioner_qa_threads show" data-name="questioner_qa_threads-show">
  <div class="page-content">
    <div class="qa-thread-block-container">
      <div class="block mentor-info-block">
		  <div class="mentor-image img-circle">
		      
		          <c:if test="${memberDTO.member_email == getEmail}">
		            <a type="external" onclick="location.href='/mentor/mentor/mentorInfoView?mentors=${mentorDTO.member_seq}'">
		              <c:if test="${mentorDTO.member_profile != 'profile.jpg'}">
					     <img width="50" height="50" src="../storage/${mentorDTO.mentor_email}/${mentorDTO.member_profile}">
					  </c:if>
					</a>
				  </c:if>
				  <c:if test="${memberDTO.member_email != getEmail}">
	                  <c:if test="${mentorDTO.member_profile != 'profile.jpg'}">
				         <img width="50" height="50" src="../storage/${mentorDTO.member_email}/${mentorDTO.member_profile}">
				      </c:if>
				  </c:if>
			      <c:if test="${mentorDTO.member_profile == 'profile.jpg'}">
		          	  <img width="50" height="50" src="../image/profile.jpg">
			      </c:if>
		  </div>

		  <div class="block mentor-info">
		    <div class="name">
		    <c:if test="${mentorDTO.member_flag==1}">
		      <span class="mentor-name">${mentorDTO.member_name}<small>멘토</small></span>
		        <a class="button col js-bookmark user_43427" data-params="followed_id=43427" data-disable-with="..." type="external" data-remote="true" rel="nofollow" data-method="post" href="/relationships">팔로우</a>
			</c:if>
			 <c:if test="${mentorDTO.member_flag==0}">
		      <span class="mentor-name">${mentorDTO.member_name}<small>멘티</small></span>
		     </c:if>
	        </div>
	        <c:if test="${memberDTO.member_email == getEmail}">
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
			              <div class="chip chip-outline no-border-radius" style="border-radius: 5px;">
			                <div class="chip-label">
			                 	 ${mentorDTO.job_type}
			                </div>
			              </div>
							<c:forEach var="mentor" items="${list}">
					               <div class="chip chip-outline no-border-radius" style="border-radius: 5px;">
					                 <div class="chip-label">
					                  	 ${mentor.mentoring_type}
					                 </div>
					               </div>
					   	  	</c:forEach>
						</div>
			        </div>
			      </div>
		      </c:if>
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
			<c:if test="${flag==0}">
				<c:if test="${auswerDTO.answer_content != null}">
				<div class="card qa-thread-card question-card">
				<div class="card-header">
				  <div class="left">답변</div>
				  <div class="created-at">
				    <small></small>
				  </div>
				</div>
				<div class="card-content card-content-padding">
				  <div class="content-body">

					<div class="detail trix-content">
						<pre style="white-space: pre-line; font-family: auto; background-color: white;"><c:out value="${auswerDTO.answer_content}"/></pre>
				    </div>
				  </div>
				</div>
				</c:if>
			</c:if>

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
			    	<pre style="white-space: pre-line; font-family: auto; background-color: white;"><c:out value="${mentorDTO.question_title}" /></pre>
				</div>

				<div class="detail trix-content">
					<pre style="white-space: pre-line; font-family: auto; background-color: white;"><c:out value="${mentorDTO.question_content}"/></pre>
			    </div>
			  </div>
			</div>
			<form id="mentorAnswer_from" method="post">
			<c:if test="${memberDTO.member_email == getEmail}">
			    <div class="card-footer action-block">
					<c:if test="${mentorDTO.question_flag == 0}">
				      <a class="button button-small color-black" type="external" href="/mentor/mentor/mentorQuestionsForm?pg=${pg}&seq=${seq}&qsseq=${qsseq}">수정</a>
					</c:if>

				      <a class="button button-small color-black" type="external" id="delete_question">삭제</a>
				</div>
			</c:if>
			<c:if test="${seq == mentorDTO.mentor_seq}">
			    <div class="card-footer action-block">
				      <a class="button button-small color-black" id="answer_slide" type="external" style="cursor:pointer;">답변</a>
				</div>
				<div class="list form-list no-hairlines" id="answer_div" style="margin: 20px 10px; display:none;">
		        <ul style="margin: 20px 0px;">
		          <li class="item-content item-input">
		            <div class="item-inner">
		              <div class="item-input-wrap">
		              <c:if test="${auswerDTO.answer_content != null}">
		                <textarea class="text required" name="answer_content" id="answer_content">${auswerDTO.answer_content}</textarea>
					  </c:if>
		              <c:if test="${auswerDTO.answer_content == null}">
		                <textarea class="text required" name="answer_content" id="answer_content"></textarea>
					  </c:if>
		              </div>
		              <div id="answer_content_error"></div>
		            </div>
		          </li>
		        </ul>

		        <c:if test="${auswerDTO.answer_content != null}">
   					<input type="button" id="answerModify_btn" value="수정하기" class="btn button button-big button-fill">
				</c:if>
		        <c:if test="${auswerDTO.answer_content == null}">
   					<input type="button" id="answer_btn" value="보내기" class="btn button button-big button-fill">
				</c:if>
				</div>
			</c:if>
			<input type="hidden" id="mentor_seq" name="mentor_seq" value="${seq}">
	        <input type="hidden" id="question_seq" name="question_seq" value="${qsseq}">
	        <input type="hidden" id="member_email" name="member_email" value="${mentorDTO.member_email}">
		    </form>
		  </div>
       </div>
    </div>
  </div>
</div>
<input type="hidden" id="followVal" name="followVal" value="${follow}">
<!-- 소켓 알림으로 사용 -->
<input type="hidden" id="memNicname" name="memNicname" value="${memNicname}">
<input type="hidden" id="member_nickname" name="member_nickname" value="${mentorDTO.member_nickname}">
<!-- 팔로우에 넣을 값 -->
<input type="hidden" id="followed_email" name="followed_email" value="${mentorDTO.mentor_email}">

<script src="../js/member.js"></script>
<script src="../js/mentor.js"></script>

<script type="text/javascript">
//답변 슬라이더
$("#answer_slide").on('click',function(){
    var submenu = $('#answer_div')

    if( submenu.is(":visible") ){
        submenu.slideUp();
    }else{
        submenu.slideDown();
    }
});
</script>
