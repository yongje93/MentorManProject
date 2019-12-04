<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page navbar-fixed bookmarks mentor_posts" data-name="bookmarks-mentor_posts">
	<div class="page-content">
		<div class="qa-thread-block">
			<div class="block-title strong-title myAlarmTitle">나의 알림 (${getTotalAlarm })</div>
			<div class="block no-hairlines"></div>
			<div class="mentor-post-block">
				<div class="row no-gap" id="inputAlarmList">
				
				<c:set var="count" value="0" />
				<c:forEach var="list" items="${list}">
					<div class="col-100 tablet-100 desktop-100">
						<div class="card mentor-post-card">
							<div class="card-header">
								<div>
									<div>
										<strong>${list.myAlarm_title }  </strong>
										
										<c:if test="${newSize > count}">
										<span class="badge color-red alim">new</span>
										</c:if>
										
									</div>
								</div>
								<div class="created-at">
									<small>${list.myAlarm_logtime}</small>
								</div>
							</div>
							<div class="card-content-padding">
								<span class="mentor-post-detail">${list.myAlarm_content} </span>
								<i class="far fa-trash-alt alarmGarbage" id="${list.myAlarm_seq}" style="float:right; cursor: pointer;"></i>
							</div>
						</div>
					</div>
					<c:set var="count" value="${count+1}"/>
				</c:forEach>
				</div>
				<c:if test="${getTotalAlarm > 7 }">
				<div class="text-align-center load-more-container">
					<button class="button load-more" id="loadMorePost" style="margin: 0 auto;">더 보기</button>
				</div>
				</c:if>
				
			</div>
		</div>
		<div class="pagination-block">
			<div class="page-entries-info"></div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/myAlarm.js"></script>
