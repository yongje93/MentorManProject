<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="page navbar-fixed bookmarks mentor_posts" data-name="bookmarks-mentor_posts">
	<div class="page-content">
		<div class="qa-thread-block">
			<div class="block-title strong-title">나의 관심 에세이</div>

			<div class="block no-hairlines">
			</div>

			<div class="mentor-post-block">
				<div class="row no-gap">
				<c:forEach var="list" items="${list }">
					<div class="col-100 tablet-100 desktop-100">
						<div class="card mentor-post-card mentor_post_6624">
							<div class="card-header">
								<a class="color-black" type="external" href="##"> <!-- 멘토 상세주소 넣어야됨 -->
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
											<div>
												<strong class="mentor-name">${list.member_name }</strong> <small>멘토</small>
											</div>
											<div class="job">
												<small> ${list.mentor_company }</small>
											</div>
										</div>
									</div>
								</a>
								<div class="created-at">
									<small>${list.essayboard_logtime }</small>
								</div>
							</div>

							<div class="card-content card-content-padding" style="overflow: hidden; text-overflow: ellipsis; height: 200px; ">
								<input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
							    	<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${pg }&seq=${list.essayboard_seq}&mentors=${list.member_seq }">
									<div class="mentor-post-title">﻿ ${list.essayboard_title } </div>
									<div class="mentor-post-detail">
										<c:choose>
											<c:when test="${fn:length(list.essayboard_content) gt 350}">
												<c:out value='${fn:substring(list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 340)}' />...
											</c:when>
											<c:otherwise>
												<c:out value="${list.essayboard_content}"/>
											</c:otherwise>
										</c:choose>
									</div>
									</a>
							</div>
							<div class="card-footer">
								<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow" data-method="post" href="javascript:void(0);">
									<c:if test="${list.essayboard_scrapFlag == 1}">
								    <img id="${list.essayboard_seq}" src="../image/scrapOkImg.png" width="13">
								    </c:if>
								    <c:if test="${list.essayboard_scrapFlag == 0}">
								    <img id="${list.essayboard_seq}" src="../image/scrapNoImg.png" width="13">
								    </c:if>
									<span id="ScrapDiv_${list.essayboard_seq}">${list.essayboard_scrap}</span>
								  	<!-- 스크랩 끌고와야 함 -->
								  	<input type="hidden" id="scrapFlag" name="scrapFlag" value="${list.essayboard_scrapFlag}">

									<!-- <i class="fas fa-bookmark bookmarked" aria-hidden="true"></i> 6 -->
								</a>
								<div class="created-at"></div>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="pagination-block">
			<div class="page-entries-info"></div>
		</div>
	</div>
</div>
<script src="../js/essayboardList.js"></script>
