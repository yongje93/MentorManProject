<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page navbar-fixed questioner_qa_threads index" data-name="questioner_qa_threads-index">
  <div class="page-content">
    <div class="qa-thread-block">

      <div class="block top-block">
        <h1 class="title">
          질문 및 답변
        </h1>

     <!--    <div class="block-footer">
          로켓은 질문권을 의미합니다. 최초 3개가 충전되며, 멘토에게 답변을 받고 고맙습니다를 작성하시면 1개씩 자동 충전됩니다.
        </div>

        <div class="block-footer">
          <div class="stat-info-block">
            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>로켓 <strong class="highlight">3개</strong></span>
              </div>
            </div>

            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>질문수 <strong class="highlight">-개</strong></span>
              </div>
            </div>

            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>질문뱃지 <strong class="highlight">-개</strong></span>
              </div>
            </div>
          </div>
        </div> -->
      </div>
      <div class="row">
      <c:if test="${list ne '[]'}">
      	<c:forEach var="member" items="${list}" >
	        <div class="col-100">
			  <div class="card questioner-qa-thread-card">
			  <div class="card-header">
			      <a type="external" href="/mentors/43427">
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
				    <a class="content-body" type="external" href="/questioner_qa_threads/29326">
				      <div class="title">
				       ${member.question_title}
				      </div>
				
				      <div class="body">
				       ${member.question_content}
				      </div>
					</a>  
				</div>
				
					  <div class="card-footer">
					    <div>
					    <c:if test="${member.question_flag eq 0}">	
					      <span class="badge color-red qa_label"><small>질문 작성 중</small></span>
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
    </div>
  </div>
</div>