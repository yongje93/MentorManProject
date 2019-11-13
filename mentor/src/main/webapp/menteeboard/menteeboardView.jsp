<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.noticeboardFloat_right_div{/* 공통 */
   float: left;
}
.right_div{

}
</style> 
<form id="menteeboardViewForm" name="menteeboardViewForm">
<div class="page navbar-fixed mentors show" data-name="mentors-show">
	<div class="page-content">
		<div class="block mentor-info-block">
			<div class="mentor-image-left img-circle">
				<img width="100" height="100" src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBc2xNIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--3d86e135036be30719b1ed74fa221737817bea2b/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTXpBd2VETXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--335722d0477df6beb4db54fcbab426022c0d08ed/KakaoTalk_20181010_022006179.jpg">
			</div>
			<div class="block mentor-info mentor-info-box">
				<div class="name">
					<span class="mentor-name profile-name">${menteeboardDTO.nickname} <small>멘티</small>
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
			<div class="block-title"><h2>${menteeboardDTO.subject}</h2></div>
			<div class="block">
				${menteeboardDTO.content}
			</div>
			<div class="noticeboardFloat_right_div"><input type="button" id="viewOutBtn" class="button color-gray" value="목록"></div>
			<c:if test="${memEmail == menteeboardDTO.email}">
	            <div class="noticeboardFloat_right_div"><input type="button" class="button color-gray" value="수정" onclick="location.href='menteeboardModifyForm?pg=${pg}&seq=${menteeboardDTO.seq}'"></div>
	            <div class="noticeboardFloat_right_div"><input type="button" class="button color-gray" value="삭제" onclick="location.href='menteeboardDelete?seq=${menteeboardDTO.seq}'"></div>
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
				<div class="row no-gap">
					 <div id="thanks-notes">
						<div class="block mentee-detail-block thanks-note-card" hidden="" style="display: block;">
							<div class="mentee-info">
								<div class="mentee-image img-circle">
									<img
										src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcmZCIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--f0d5c09d42f655ec75e2351b3a921a3266a435e5/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg">
								</div>
								<div class="mentee-name">${list.nickname}</div>
								<div class="sent-date">${list.logtime}</div>
							</div>
							<div class="modifyReply_hide_${list.seq}">
							<div class="thanks-note-body content_${list.seq}">${list.content}</div>
							<c:if test="${memEmail == list.email}">
							<div class="noticeboardFloat_right_div"><input type="button" id="modifyReply${i.count}" name="${list.seq}" class="button color-gray modifyReply" value="수정" ></div>
	            			<div class="noticeboardFloat_right_div"><input type="button" id="deleteReply${i.count}" name="${list.seq}" class="button color-gray deleteReply" value="삭제" ></div>
							</c:if>
							</div>
							<div class="modifyReply${i.count}"></div>
						</div>
					</div> 
				</div>
			</c:forEach>
			<div class="block mentee-detail-block thanks-note-card" id="menteeboardPagingDiv">${menteeboardPaging.pagingHTML}</div>
			<hr>
			</div>
			
			</div>
			<div class="row no-gap">
				<div id="thanks-notes">
					<div class="block mentee-detail-block thanks-note-card" hidden=""
						style="display: block;">
						<div class="mentee-info">
							<div class="mentee-image img-circle">
								<img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcmZCIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--f0d5c09d42f655ec75e2351b3a921a3266a435e5/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg">
							</div>
							<div class="">
							<div class="mentee-name">${menteeboardDTO.nickname}</div>
							<div class="sent-date">2019년 10월 16일</div>
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
<input type="hidden" id="menteeboard_seq" name="menteeboard_seq" value="${menteeboardDTO.seq}">
<input type="hidden" id="email" name="email" value="${menteeboardDTO.email}">
<input type="hidden" name="nickname" value="${menteeboardDTO.nickname}">
<input type="hidden" id="memEmail" name="memEmail" value="${memEmail}">
<input type="hidden" name="pg" value="${pg}">
<input type="hidden" name="seq_trans" id="seq_trans">

</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/menteeboardView.js"></script>
