<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page navbar-fixed questioner_qa_threads index" data-name="questioner_qa_threads-index">
  <div class="page-content">
    <div class="qa-thread-block">

      <div class="block top-block">
        <h1 class="title">
          질문 및 답변
        </h1>

      <div class="row">
	      <c:if test="${mentor_questionList ne '[]'}">
	      	<c:forEach var="member" items="${mentor_questionList}" >
		        <div class="col-100">
				  <div class="card questioner-qa-thread-card">
					  <div class="card-header">
					      <a type="external" ><!-- 멘티 로그인 -->
					        <div>
					          <div class="mentor-image img-circle">
						           
									 <c:if test="${flag==1}">
						              <c:if test="${member.member_profile != 'profile.jpg'}">
									     <img width="50" height="50" src="../storage/${member.member_email}/${member.member_profile}">
									  </c:if>
									</c:if>
								    <c:if test="${member.member_profile == 'profile.jpg'}">
							        	<img width="50" height="50" src="../image/profile.jpg">
								    </c:if>
						          </div>
						
						          <div class="mentor-info">
						            <div>
						              <strong class="mentor-name">${member.member_name}</strong>
						              <c:if test="${flag==0}">
						             	 <small>멘토</small>
						              </c:if>
						              <c:if test="${flag==1}">
						             	 <small>멘티</small>
						              </c:if>
						            </div>
						
						            <div class="job">
						            <c:if test="${flag==0}">
						              <small>
						                ${member.mentor_company} · ${member.mentor_department}
						              </small>
						            </c:if>
						            <c:if test="${flag==1}">
						              <small>
						              <fmt:formatDate value="${member.question_logtime}" pattern="yyyy년MM월dd일" />
						              </small>
						            </c:if>
					            </div>
					          </div>
					        </div>
						</a>  
						</div>
		
							 <div class="card-content card-content-padding">
							    <a class="content-body" type="external" onclick="flag_content(${pg},${member.mentor_seq},${member.question_seq})">
							      <div class="title">
								      <c:choose>
							           <c:when test="${fn:length(member.question_title) > 60}">
							            <c:out value="${fn:substring(member.question_title,0,59)}"/>...
							           </c:when>
							           <c:otherwise>
							            <c:out value="${member.question_title}"/>
							           </c:otherwise> 
						         	 </c:choose>
							      </div>
							
							      <div class="body">
							       <c:choose>
							           <c:when test="${fn:length(member.question_content) > 200}">
							            <c:out value="${fn:substring(member.question_content,0,199)}"/>...
							           </c:when>
							           <c:otherwise>
							            <c:out value="${member.question_content}"/>
							           </c:otherwise> 
						         	 </c:choose>
							      </div>
								 
								</a>  
							</div>
					
						  <div class="card-footer">
						    <div>
						    <c:if test="${flag==1}">
						      <span class="badge color-red qa_label" style=" background-color: #ffe500; color: black;"><small>받은 질문</small></span>
						    </c:if>
						  		    
						    <c:if test="${member.question_flag eq 1}">	
						      <span class="badge color-red qa_label"><small>답변 완료</small></span>
						    </c:if>			    
						    </div>
						  </div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			
			 <c:if test="${all_questionList ne '[]'}">
	      	<c:forEach var="member" items="${all_questionList}" >
		        <div class="col-100">
				  <div class="card questioner-qa-thread-card">
					  <div class="card-header">
					      <a type="external" onclick="location.href='/mentor/mentor/mentorInfoView?mentors=${member.member_seq}'"><!-- 멘티 로그인 -->
					        <div>
					          <div class="mentor-image img-circle">
					                <c:if test="${member.member_profile != 'profile.jpg'}">
								       <img width="50" height="50" src="../storage/${member.mentor_email}/${member.member_profile}">
								    </c:if>
								    <c:if test="${member.member_profile == 'profile.jpg'}">
							        	<img width="50" height="50" src="../image/profile.jpg">
								    </c:if>
						          </div>
						
						          <div class="mentor-info">
						            <div>
						              <strong class="mentor-name">${member.member_name}</strong>
						             	 <small>멘토</small>
						            </div>
						
						            <div class="job">
						              <small>
						                ${member.mentor_company} · ${member.mentor_department}
						              </small>
					            </div>
					          </div>
					        </div>
						</a>  
						</div>
		
							 <div class="card-content card-content-padding">
							    <a class="content-body" type="external" onclick="flag_content(${pg},${member.mentor_seq},${member.question_seq})">
							      <div class="title">
								      <c:choose>
							           <c:when test="${fn:length(member.question_title) > 60}">
							            <c:out value="${fn:substring(member.question_title,0,59)}"/>...
							           </c:when>
							           <c:otherwise>
							            <c:out value="${member.question_title}"/>
							           </c:otherwise> 
						         	 </c:choose>
							      </div>
							
							      <div class="body">
							       <c:choose>
							           <c:when test="${fn:length(member.question_content) > 200}">
							            <c:out value="${fn:substring(member.question_content,0,199)}"/>...
							           </c:when>
							           <c:otherwise>
							            <c:out value="${member.question_content}"/>
							           </c:otherwise> 
						         	 </c:choose>
							      </div>
								 
								</a>  
							</div>
					
						  <div class="card-footer">
						    <div>
						    <c:if test="${member.question_flag eq 0}">	
						      <span class="badge color-red qa_label" style="background-color: #838383; color: white;"><small>답변 대기</small></span>
						    </c:if>			    
						    <c:if test="${member.question_flag eq 1}">	
						      <span class="badge color-red qa_label"><small>답변 완료</small></span>
						    </c:if>			    
						    </div>
						  </div>
						</div>
					</div>
				</c:forEach>
			</c:if>
	      </div>
		<div style="float: right;">${QandAPag.pagingHTML}</div>
    </div>
  </div>
</div>
<script type="text/javascript">

function flag_content(pg,seq,question_seq){
	location.href="/mentor/member/myQuestionsForm?pg="+pg+"&seq="+seq+"&qsseq="+question_seq;
}

</script>