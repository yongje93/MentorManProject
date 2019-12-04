<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" media="all" href="https://d2ljmlcsal6xzo.cloudfront.net/assets/application-ec82e4fd3581863fb7280ad4cb1183138cef57405f46a2d44eb51efb8a40a133.css" data-turbolinks-track="reload" />
<div class="page navbar-fixed mentor_posts show" data-name="mentor_posts-show">
	<div class="page-content">
		<div class="post-block-container">
			<div class="mentor-post-detail-block">
				<h1 class="mentor-post-title" style="display : flex">
					${essayboardDTO.essayboard_title}
				</h1>
				<div class="trix-content">
					${essayboardDTO.essayboard_content }
				</div>
				<c:if test="${memDTO.member_seq == member_seq || memDTO.member_nickname == 'admin'}">
				<div style="display: inline-block; padding-top: 30px;">
					<a id="essay_delete_btn" style="width: 100px; float: right;" class="button" type="external" onclick="essayDeleteCheck('${seq}')">
            			<i class="fas fa-pencil-alt"></i>
            			글삭제
            		</a>
					<a id="essay_modify_btn" style="width: 100px; float: right;" class="button" type="external" href="/mentor/essayboard/essayboardModifyForm?seq=${seq }&pg=${pg}">
	            		<i class="fas fa-pencil-alt"></i>
	            		글수정
					</a>
				</div>
				</c:if>
				<div class="block button-wrap">
					<div class="action-block text-align-center mentor_post_6618">
						<a class="color-gray" type="external" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6618/bookmarks" >
						<div class="block button button-big button-inline color-gray js-bookmark" id="scrapDiv">
							<i class="far fa-bookmark" aria-hidden="true"></i>
							<span id="essayScrapDiv" data-seq="${seq}">${essayboardDTO.essayboard_scrap }</span>
							<input type="hidden" id="scrapFlag" name="scrapFlag" value="${essayboardDTO.essayboard_scrapFlag}">
						</div>
						</a>
					</div>
				</div>
			</div>
			<div class="mentor-block block block-strong">
				<div class="block mentor-info-block">
					<div class="mentor-image img-circle mentor-img" data-email="${mentorDTO.mentor_email}">
						<a type="external" href="/mentor/mentor/mentorInfoView?pg=${pg }&mentors=${member_seq }">
						<!-- 멘토 이미지 -->
						  <c:if test="${mentorDTO.member_profile != 'profile.jpg'}">
				            <img width="50" height="50" src="../storage/${mentorDTO.mentor_email}/${mentorDTO.member_profile}">
				          </c:if>
				          <c:if test="${mentorDTO.member_profile == 'profile.jpg'}">
				            <img width="50" height="50" src="../image/profile.jpg">
				          </c:if>
						</a>
					</div>
					<div class="block mentor-info">
						<div class="name">
							<div class="mentor-name" data-name="${mentorDTO.member_name }">
								${mentorDTO.member_name }<span id="menseq" data-menseq="${member_seq}"><small>멘토</small></span>
							</div>
							<a class="button col js-bookmark mentor2" id="followA" data-disable-with="..." type="external" data-remote="true" rel="nofollow" data-method="post" href="#"><!-- 주소 수정 -->
							팔로우 </a>
							<a class="button button-small button-fill" type="external" href="#"><!-- 주소 수정 -->
							질문하기 </a>
						</div>
						<div class="job">
							 ${mentorDTO.mentor_company } · ${mentorDTO.mentor_department }
						</div>
						<div class="detail-block">
							<div class="mentoring-info">
								<div class="title text-decoration-underline">
									 대표 멘토링 분야
								</div>
								<div class="mentoring-type-block">
									 ${mentorDTO.mentor_represent }
								</div>
							</div>
							<div class="mentoring-info">
								<div class="title">
									 멘토링 분야
								</div>
								<div class="mentoring-type-block">
								<div class="chip chip-outline no-border-radius">
									<div class="chip-label">
										${mentorDTO.job_type }
									</div>
								</div>
								<c:forEach var="mentoring" items="${mentoringList }">
									<div class="chip chip-outline no-border-radius">
										<div class="chip-label">
											 ${mentoring.mentoring_type }
										</div>
									</div>
								</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div style="margin-top: 50px; margin-bottom: 100px;">
            	<input type="button" onclick="location.href='/mentor/essayboard/essayboardList?pg=${pg}'" value="목록" class="btn button button-big button-fill" style="line-height: 0px;">
        	</div>
		</div>
	</div>
</div>
<input type="hidden" name="memNickname" id="memNickname" value="${memDTO.member_nickname}" />
<input type="hidden" id="followVal" name="followVal" value="${follow}">

<script src="../js/deleteEssayboard.js"></script>
<script src="../js/essayboardView.js"></script>
