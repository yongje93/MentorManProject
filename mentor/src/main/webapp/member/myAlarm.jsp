<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page navbar-fixed bookmarks mentor_posts" data-name="bookmarks-mentor_posts">
	<div class="page-content">
		<div class="qa-thread-block">
			<div class="block-title strong-title">나의 알림</div>
			<div class="block no-hairlines"></div>
			<div class="mentor-post-block">
				<div class="row no-gap" id="inputAlarmList">
				<c:forEach var="list" items="${list}">
					<div class="col-100 tablet-100 desktop-100">
						<div class="card mentor-post-card">
							<div class="card-header">
								<div>
									<div>
										<strong>${list.myAlarm_title }</strong>
									</div>
								</div>
								<div class="created-at">
									<small>${list.myAlarm_logtime}</small>
								</div>
							</div>
							<div class="card-content-padding">
								<span class="mentor-post-detail">${list.myAlarm_content} </span>
								<i class="far fa-trash-alt alarmGarbage" id="${list.myAlarm_seq}" style="float:right;"></i>
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
<script type="text/javascript" src="../js/myAlarm.js"></script>