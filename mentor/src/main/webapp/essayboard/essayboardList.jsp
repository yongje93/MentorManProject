<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<div class="page navbar-fixed open_mentorings index" data-name="open_mentorings-index">
	<div class="page-content">
		<div class="block job-type-block">
			<div class="block-title">직무 유형</div>
			<div class="row">
				<a id="job_code_0" class="button color-gray" type="external" href="job_code_0">인사/총무/노무</a>
	      		<a id="job_code_1" class="button color-gray" type="external" href="job_code_1">마케팅/MD</a>
				<a id="job_code_2" class="button color-gray" type="external" href="job_code_2">홍보/CSR</a>
				<a id="job_code_3" class="button color-gray" type="external" href="job_code_3">영업/영업관리</a>
				<a id="job_code_4" class="button color-gray" type="external" href="job_code_4">회계/재무/금융</a>
				<a id="job_code_5" class="button color-gray" type="external" href="job_code_5">해외/기술영업</a>
				<a id="job_code_6" class="button color-gray" type="external" href="job_code_6">유통/무역/구매</a>
				<a id="job_code_7" class="button color-gray" type="external" href="job_code_7">전략/기획</a>
				<a id="job_code_8" class="button color-gray" type="external" href="job_code_8">IT개발</a>
				<a id="job_code_9" class="button color-gray" type="external" href="job_code_9">서비스 기획/UI, UX등</a>
				<a id="job_code_10" class="button color-gray" type="external" href="job_code_10">디자인/예술</a>
				<a id="job_code_11" class="button color-gray" type="external" href="job_code_11">미디어</a>
				<a id="job_code_12" class="button color-gray" type="external" href="job_code_12">서비스</a>
				<a id="job_code_13" class="button color-gray" type="external" href="job_code_13">연구/설계</a>
				<a id="job_code_14" class="button color-gray" type="external" href="job_code_14">전문/특수</a>
				<a id="job_code_15" class="button color-gray" type="external" href="job_code_15">교육/상담/컨설팅</a>
				<a id="job_code_16" class="button color-gray" type="external" href="job_code_16">공무원/공공/비영리</a>
				<a id="job_code_17" class="button color-gray" type="external" href="job_code_17">생산/품질/제조</a>
				<a id="job_code_18" class="button color-gray" type="external" href="job_code_18">기타 사무</a>
			</div>
			<input type="hidden" id="memNick" value="${memNickname}">
		</div>
		<div class="open-mentoring-block">
			<div class="page navbar-fixed mentor_posts index" data-name="mentor_posts-index">
				<div class="mentor-post-block">
					<div class="block-title strong-title">
						<div class="essayName">신규 에세이</div>
						<div style="float: right; margin-bottom: 5px;">
						<c:if test="${memFlag == '1' }">
							<a id="essay_write_btn" class="button" type="external" href="/mentor/essayboard/essayboardWriteForm" style="display: inline-block;">
							<i class="fas fa-pencil-alt"></i> 글쓰기
							</a>
						</c:if>
						<a class="button color-gray" id="recommend_essay" type="external" href="javascript:void(0)" style="display: inline-block;">
						<i class="fas fa-pencil-alt"></i> 추천 에세이
						</a>
						</div>
					</div>
					<div class="row no-gap" id="gap">
					<!-- 멘토 리스트 생성 -->
						<c:forEach var="list" items="${list }">
						<input type="hidden" id="job_code" value="${list.job_code }">
						<div class="col-100 tablet-50 desktop-33">
							<div class="card mentor-post-card">
								<div class="card-header">
									<a class="color-black" type="external" href="/mentor/mentor/mentorInfoView?mentors=${list.member_seq }">
									<div>
										<div class="mentor-image img-circle">
											<c:if test="${list.member_profile != 'profile.jpg'}">
												<img width="50" height="50"	src="../storage/${list.mentor_email}/${list.member_profile}">
											</c:if>
											<c:if test="${list.member_profile == 'profile.jpg'}">
												<img width="50" height="50" src="../image/profile.jpg">
											</c:if>
										</div>
										<div class="mentor-info">
											<div><strong class="mentor-name">${list.member_name }</strong><small>멘토</small></div>
											<div class="job"><small> ${list.mentor_company } · ${list.mentor_department }</small></div>
										</div>
									</div>
									</a>
									<div class="created-at">
										<small>${list.essayboard_logtime}</small>
									</div>
								</div>

								<div class="card-content card-content-padding" style="overflow: hidden; text-overflow: ellipsis; height: 200px;">
									<input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
				   				<input type="hidden" id="essayNickname" name="essayNickname" value="${list.mentor_email }">
				   				<input type="hidden" id="essayName" name="essayName" value="${list.member_name }">
				   				<input type="hidden" id="memberSeq" name="memberSeq" value="${list.member_seq }">
									<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${pg }&seq=${list.essayboard_seq}&mentors=${list.member_seq }">
										<div class="mentor-post-title">
											${list.essayboard_title}
										</div>
										<div class="mentor-post-detail">
											<c:choose>
												<c:when test="${fn:length(list.essayboard_content) gt 200}">
													<c:out value='${fn:substring(list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 190)}' />...
												</c:when>
												<c:otherwise>
													<c:out value="${list.essayboard_content}"/>
												</c:otherwise>
											</c:choose>
										</div>
									</a>
								</div>

								<div class="card-footer">
									<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow"
										data-method="post" href="" style="right: 0px; position: unset; margin: 0px 0px;">
										<!-- <i class="far fa-bookmark" aria-hidden="false"></i> -->
										<c:if test="${list.essayboard_scrapFlag == 1}">
											<img id="${list.essayboard_seq}" src="../image/scrapOkImg.png" width="13">
										</c:if> <c:if test="${list.essayboard_scrapFlag == 0}">
											<img id="${list.essayboard_seq}" src="../image/scrapNoImg.png" width="13">
										</c:if>
										<span id="ScrapDiv_${list.essayboard_seq}">${list.essayboard_scrap}</span>
										<!-- 스크랩 끌고와야 함 -->
										<input type="hidden" id="scrapFlag"	name="scrapFlag" value="${list.essayboard_scrapFlag}">
									</a>

									<div class="created-at">
										<!-- <small> -->
										<!--   읽음 -->
										<!--   336 -->
										<!-- </small> -->
									</div>
								</div>
							</div>
						</div>
						</c:forEach>

						<!-- 멘토 리스트 생성 끝 -->
						<input type="hidden" id="pg" name="pg" value="${pg}">
						<!-- 스크랩버튼 클릭 방지에 사용 -->
						<input type="hidden" id="memNickname" name="memNickname" value="${memNickname}">

						<div class="col-100 tablet-50 desktop-33"></div>
						<div class="col-100 tablet-50 desktop-33"></div>
					</div>
					<input type="hidden" id="essayFlag" name="essayFlag" value="${flag}">
					<input type="hidden" id="recommend_view_essay" value="${param.essayFlag}">
					<div class="pagination-block">
						<div class="page-entries-info"></div>
						<div class="paging" id="paging">${boardpaging.pagingHTML}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js/essayboardList.js"></script>