<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.noticeboardFloat_right_div{/* 공통 */
   float: left;
}

</style> 
<form id="menteeboardViewForm" name="menteeboardViewForm">
<div class="page navbar-fixed mentors show" data-name="mentors-show">
	<div class="page-content">
		<div class="block mentor-info-block">
			<div class="mentor-image-left img-circle">
				<c:if test="${menteeboardDTO.member_profile != 'profile.jpg'}">
					<img width="150" height="150" src="../storage/${menteeboardDTO.menteeboard_email}/${menteeboardDTO.member_profile}">
				</c:if>
				<c:if test="${menteeboardDTO.member_profile == 'profile.jpg'}">
					<img width="150" height="150" src="../image/profile.jpg">
				</c:if>
			</div>
			<div class="block mentor-info mentor-info-box">
				<div class="name">
					<span class="mentor-name profile-name">${menteeboardDTO.member_nickname} <small>멘티</small>
					</span>
				</div>
				<div class="detail-block">
					<div class="mentoring-info">
					</div>
					<div class="mentoring-info">
						<div class="title">직무 유형</div>
						<div class="mentoring-type-block">
							<a type="external" href="/mentors?job_type%5B%5D=7">
								<div class="chip chip-outline no-border-radius job-tags">
									<div class="chip-label">서비스 기획/UI, UX등</div>
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="block block-strong mentor-detail-block">
			<div class="block-title"><h2>${menteeboardDTO.menteeboard_title}</h2></div>
			<div class="block">
				${menteeboardDTO.menteeboard_content}
			</div>
			<div class="noticeboardFloat_right_div"><input type="button" id="viewOutBtn" class="button color-gray" value="목록"></div>
			<c:if test="${memEmail == menteeboardDTO.menteeboard_email}">
	            <div class="noticeboardFloat_right_div"><input type="button" class="button color-gray" value="수정" onclick="location.href='menteeboardModifyForm?pg=${pg}&seq=${menteeboardDTO.menteeboard_seq}'"></div>
	            <div class="noticeboardFloat_right_div"><input type="button" class="button color-gray" value="삭제" onclick="location.href='menteeboardDelete?seq=${menteeboardDTO.menteeboard_seq}'"></div>
          	</c:if>
          	<div class="noticeboardFloat_right_div"><a class="heart"><img id="heartImg" src="" width="25"></a></div>
            <div class="block wrap-share">
            	<input type="hidden" id="heartVal" name="heartVal" value="${heart}">
            </div>
		</div>
		<div class="block section thanks-block">
			<div class="title-wrap">
				<div class="block-title strong-title">댓글</div>
				<div class="count">${cnt}</div>
			</div>
			
			<!-- 댓글이 달리는 부분 -->
			<div id="inputList">
			<c:forEach items="${list}" var="list" varStatus="i">
			<c:if test="${list.menteeboardReply_flag eq 0}">
				<div class="no-gap">
					 <div id="thanks-notes">
						<div class="block mentee-detail-block thanks-note-card" hidden="" style="display: block;">
							<div class="mentee-info">
								<div class="mentee-image img-circle">
									<c:if test="${list.member_profile != 'profile.jpg'}">
										<img width="150" height="150" src="../storage/${list.menteeboardReply_email}/${list.member_profile}">
									</c:if>
									<c:if test="${list.member_profile == 'profile.jpg'}">
										<img width="150" height="150" src="../image/profile.jpg">
									</c:if> 
								</div>
								<div class="mentee-name">${list.member_nickname}</div>
								<div class="sent-date">${list.menteeboardReply_logtime}</div>
							</div>
							<div class="modifyReply_hide_${list.menteeboardReply_seq}">
							<div class="thanks-note-body content_${list.menteeboardReply_seq}">${list.menteeboardReply_content}</div>
							<c:if test="${memEmail == list.menteeboardReply_email}">
							<div class="noticeboardFloat_right_div"><input type="button" id="modifyReply${i.count}" name="${list.menteeboardReply_seq}" class="button color-gray modifyReply" value="수정" ></div>
	            			<div class="noticeboardFloat_right_div"><input type="button" id="deleteReply${i.count}" name="${list.menteeboardReply_seq}" class="button color-gray deleteReply" value="삭제" ></div>
							</c:if>
							<%-- <div class="noticeboardFloat_right_div"><input type="button" id="dapgleReply${i.count}" name="${list.menteeboardReply_seq}" class="button color-gray dapgleReply" value="답글" ></div> --%>
							</div>
							<div class="modifyReply${i.count}"></div>
						</div>
					</div> 
				</div>
				<br/>
				</c:if>
				<c:if test="${list.menteeboardReply_flag eq 1}">
				<div class="no-gap">
					 <div id="thanks-notes">
						<div class="block mentee-detail-block thanks-note-card" hidden="" style="display: block;">
							<div class="mentee-info">
								<div class="mentee-image img-circle">
									<img width="150" height="150" src="../image/profile.jpg">
								</div>
								<div class="mentee-name">관리자</div>
								<div class="sent-date">${list.menteeboardReply_logtime}</div>
							</div>
							<div class="thanks-note-body"><i class="fas fa-exclamation-circle" style="color:red;"></i>관리자에의해 삭제된 댓글입니다.</div>
						</div>
					</div> 
				</div>
				<br/>
				</c:if>
			</c:forEach>
			<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">${menteeboardPaging.pagingHTML}</div>
			<hr>
			</div> 
			
			</div>
			<div class="row no-gap">
				<div id="thanks-notes">
					<div class="block mentee-detail-block thanks-note-card" hidden="" style="display: block;">
						<div class="mentee-info">
							<div class="mentee-image img-circle">
							</div>
							<div class="">
							<div class="mentee-name">${memNickname}</div>
							</div>
						</div>
						<div class="thanks-note-body">
							<div>
								<div><textarea id="content" name="content" cols="60" rows="3" placeholder="댓글" ></textarea></div>
								<div class="right_div">
									<input type="button" id="regist"class="button color-gray" value="등록">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<input type="hidden" id="menteeboard_seq" name="menteeboard_seq" value="${menteeboardDTO.menteeboard_seq}">

<input type="hidden" id="email" name="email" value="${menteeboardDTO.menteeboard_email}">
<input type="hidden" id="nickname" name="nickname" value="${menteeboardDTO.member_nickname}">

<input type="hidden" id="memEmail" name="memEmail" value="${memEmail}">
<!-- 닉네임은 소켓에 담을때 사용한다 -->
<input type="hidden" id="memNickname" name="memNickname" value="${memNickname}">

<input type="hidden" name="pg" value="${pg}">
<input type="hidden" name="seq_trans" id="seq_trans">

</form>

<script type="text/javascript" src="../js/menteeboardView.js"></script>

