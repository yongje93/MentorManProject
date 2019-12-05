<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page navbar-fixed me-relationships index"
	data-name="relationships-index">
	<div class="page-content">
		<div class="block top-block">
			<h1 class="title">나의 관심 멘토</h1>
			<div class="block-footer">관심 멘토를 추가하고 질문해 보세요.</div>
		</div>
		<div class="row no-gap">
			<c:forEach var="mentor" items="${list}" >
				<div class="col-100 tablet-50">
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
						</div>
					</div>
				</div>
			</c:forEach>



		</div>
	</div>
</div>
<script>

function mentor_question_seq(seq){
	$.ajax({
		type: 'post',
		url: '/mentor/mentor/question_flag',
		data: {'seq': seq},
		dataType: 'text',
		success: function(data){
			location.href=data;
		},
		error: function(){
			alert('에러');
		}
	});
}

</script>