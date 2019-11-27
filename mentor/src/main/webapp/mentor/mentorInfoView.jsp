<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="page navbar-fixed mentors show" data-name="mentors-show">
	<div class="page-content">
		<div class="block mentor-info-block">
			<div class="mentor-image-left img-circle">
				<c:if test="${mentorDTO.member_profile != 'profile.jpg'}">
					<img width="150" height="150" src="../storage/${mentorDTO.mentor_email}/${mentorDTO.member_profile}">
				</c:if>
				<c:if test="${mentorDTO.member_profile == 'profile.jpg'}">
					<img width="150" height="150" src="../image/profile.jpg">
				</c:if>
			</div>
			<div class="block mentor-info mentor-info-box">
				<div class="name">
					<span class="mentor-name profile-name">
						${mentorDTO.member_name} <small>멘토</small>
					</span>
				</div>
				<div class="job">${mentorDTO.mentor_company} · ${mentorDTO.mentor_department}</div>
				<div class="detail-block">
					<div class="mentoring-info">
						<div class="mentoring-type-block">
							<div class="chip chip-outline no-border-radius mentor-index">
								<div class="chip-label">
									<span>답변율 <strong class="highlight"><fmt:formatNumber value="${questionPercent}" pattern="0%"/></strong></span>
								</div>
							</div>
							<div class="chip chip-outline no-border-radius mentor-index">
								<div class="chip-label">
									<span>답변수 <strong class="highlight">${mentor_answer}</strong></span>
								</div>
							</div>
							<div class="chip chip-outline no-border-radius mentor-index">
								<div class="chip-label">
									<span>팔로워 <strong class="highlight">!!!수정</strong></span>
								</div>
							</div>
						</div>
					</div>
					<div class="mentoring-info">
						<div class="title text-decoration-underline" style="height: 15px;">대표 멘토링 분야</div>
						<div class="mentoring-type-block">
							${mentorDTO.mentor_represent}
						</div>
					</div>
					<div class="mentoring-info">
						<div class="title" style="height: 15px;">직무 유형</div>
						<div class="mentoring-type-block">
							<div class="chip chip-outline no-border-radius" style="border-radius: 5px;">
								<div class="chip-label">${mentorDTO.job_type}</div>
							</div>
						</div>
					</div>
					<div class="mentoring-info">
						<div class="title" style="height: 15px;">멘토링 분야</div>
						<div class="mentoring-type-block">
							<c:forEach var="mentoring" items="${mentoringList}">
								<div class="chip chip-outline no-border-radius" style="border-radius: 5px;">
									<div class="chip-label">${mentoring.mentoring_type}</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- [멘토 찾기에서 수정] 로그아웃 상태에서 멘토 프로필 확인 할 때 질문이나 팔로우는 로그인 창으로 보내기  -->
					<c:if test="${email_check == null}">
						<div class="btn-container">
							<div class="profile-btn">
								<a class="button col js-bookmark" type="external" data-remote="true" rel="nofollow" data-method="post" href="/mentor/member/loginForm"> 팔로우 </a>  <%--주소 수정 --%>
							</div>
							<c:if test="${mentorDTO.mentor_email != email_check}">
							<div class="profile-btn">
								<a class="button button-fill" type="external" href="/mentor/member/loginForm">질문하기</a>  <%--주소 수정 --%>
							</div>
							</c:if>
						</div>
					</c:if>
					<c:if test="${email_check != null}">
						<div class="btn-container">
							<c:if test="${mentorDTO.mentor_email != email_check}">
								<div class="profile-btn">
									<a class="button col js-bookmark" type="external" data-remote="true" rel="nofollow" data-method="post" href=""> 팔로우 </a>  <%--주소 수정 --%>
								</div>
								<div class="profile-btn">
									<a class="button button-fill" type="external" onclick="mentor_question_seq(${mentorDTO.mentor_seq},${pg})">질문하기</a>  <%--주소 수정 --%>
								</div>
							</c:if>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="block block-strong mentor-detail-block">
			<div class="block-title">멘토 소개</div>
			<div class="block">${mentorDTO.mentor_info}</div>
			<div class="block-title">주요 경력</div>
			<div class="block">${mentorDTO.mentor_career}</div>
			<c:if test="${mentorDTO.mentor_etc != null}">
				<div class="block-title">기타 사항</div>
				<div class="block">${mentorDTO.mentor_etc}</div>
			</c:if>
		</div>
		<c:if test="${reviewTotal > 0 }">
		<div class="block section thanks-block">
			<div class="title-wrap">
				<div class="block-title strong-title">고맙습니다</div>
				<div class="count">(${reviewTotal})</div>
			</div>
			<div class="row no-gap">
				<div id='thanks-notes'>
					<c:forEach var="review" items="${reviewList}">
					<fmt:formatDate var="review_date" value="${review.review_date}" pattern="yyyy년 MM월 dd일"/>
						<div class="block mentee-detail-block thanks-note-card">
							<div class="mentee-info">
								<div class="mentee-image img-circle">
									<c:if test="${review.member_profile != 'profile.jpg'}">
										<img width="38" height="38" src="../storage/${review.mentee_email}/${review.member_profile}">
									</c:if>
									<c:if test="${review.member_profile == 'profile.jpg'}">
										<img width="38" height="38" src="../image/profile.jpg">
									</c:if>
								</div>
								<div class="mentee-name">${review.mentee_name}</div>
								<div class="sent-date">${review_date}</div>
							</div>
							<div class="thanks-note-body">${review.review_content}</div>
							
							<c:if test="${memDTO.member_email == review.mentee_email}">
								<div style="float: right; margin-bottom: 5px;">
									<button class="button" onclick="location.href='/mentor/mentee/meetingReviewModifyForm?seq=${review.review_seq}&mentors=${mentor_seq}'" style="display: inline-block;">수정</button>
									<button class="button" onclick="reviewDelete(${review.review_seq})" style="display: inline-block;">삭제</button>
								</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
			<c:if test="${reviewTotal > 3 }">
			<div class="text-align-center">
				<button class="button load-more" id="loadMoreThanks">더 보기</button>
			</div>
			</c:if>
		</div>
		</c:if>
		<c:if test="${essayTotal > 0 }">
		<div class="block mentor-post-block section">
			<div class="title-wrap">
				<div class="block-title strong-title">에세이</div>
				<div class="count">(${essayTotal})</div>
			</div>
			<div class="row no-gap">
				<c:forEach var="essayList" items="${essayList}">
				<fmt:formatDate var="essayboard_date" value="${essayList.essayboard_logtime}" pattern="yyyy년 MM월 dd일"/>
					<div class="col-100 tablet-100 desktop-100">
						<div class="card mentor-post-card shadow-card" hidden>
							<div class="card-content card-content-padding profile-card">
								<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?"> 		<%--주소 수정 --%>
									<div class="mentor-post-title">
										${essayList.essayboard_title}
									</div>
									<div class="mentor-post-detail">
										<c:choose>
											<c:when test="${fn:length(essayList.essayboard_content) gt 300}">
												<c:out value='${fn:substring(essayList.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 290)}'/>...
											</c:when>
											<c:otherwise>
												<c:out value="${essayList.essayboard_content}"/>
											</c:otherwise>
										</c:choose>
										
									</div>
								</a>
							</div>
							<div class="card-footer">
								<a class="color-gray js-bookmark" type="external" data-remote="true" rel="nofollow" data-method="post"
									href="" style="right: 0px; position: unset; margin: 0px 0px;">   <%--주소 수정 --%>
								<i class="far fa-bookmark" aria-hidden="true"></i>
									${essayList.essayboard_scrap}
								</a>
								<div class="created-at">
									<small> ${essayboard_date}</small>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="${essayTotal > 2 }">
			<div class="text-align-center load-more-container">
				<button class="button load-more" id="loadMorePost">더 보기</button>
			</div>
			</c:if>
		</div>
		</c:if>
	</div>
</div>
<script src="../js/mentor.js"></script>
<script>
	let currentPage = 1;
	const lastPage = 1;
	
	$(function() {
		$('.mentor-post-card').prop('hidden', true).slice(0, 2).show();
		$("#loadMorePost").on('click', function(e) {
			e.preventDefault();
			$(".mentor-post-card:hidden").slice(0, 2).slideDown();
			if ($(".mentor-post-card:hidden").length == 0) {
				$("#loadMorePost").fadeOut('slow');
			}
		});
	});
	
	function toggleThanksNotesText(e) {
		e.preventDefault();
		$(this).hide();
		$(this).prev('span.elipsis').fadeToggle(500);
	}
	
	function hideLongThanksNotesText(element) {
		const body = element.text();
		if (body.length > 300) {
			element.html(body.substr(0, 286)
							+ '<span class="elipsis">'
							+ body.substr(286)
							+ '</span><a class="elipsis" href="#"><span class=gray>...</span> 더 보기</a>');
		}
	}
	
	$(function() {
		$('.thanks-note-card').prop('hidden', true).slice(0, 3).show();
		$("#loadMoreThanks").on( 'click', function(e) {
			e.preventDefault();
			$(".thanks-note-card:hidden").slice(0, 3).slideDown();
			if ($(".thanks-note-card:hidden").length == 0 && lastPage <= currentPage) {
				$("#loadMoreThanks").fadeOut('slow');
			}
		});
	});
	
	$('.thanks-note-body').each(function() {
		hideLongThanksNotesText($(this));
	});
	
	$('.thanks-note-body > a.elipsis').click(toggleThanksNotesText);
	
	function reviewDelete(review_seq){
		var toastWithCallback = app.toast.create({
			text: '후기를 삭제하시겠습니까?',
			position: 'center',
			closeButton: true,
			on: {
				close: function() {
					location.href='/mentor/mentee/meetingReviewDelete?seq='+review_seq+'&mentors=${mentor_seq}';
					var toastCenter = app.toast.create({
						  text: '삭제완료',
						  position: 'center',
						  closeTimeout: 2000
					});
					toastCenter.open();
				}
			}
		});	
		toastWithCallback.open();
	}
</script>