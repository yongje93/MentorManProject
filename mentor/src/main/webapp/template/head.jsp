<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar">
	<div class="navbar-inner">
		<div class="left">
			<a class="logo" type="external" href="/mentor/main/index"> 
				<img src="../image/mentorlogo.jpg" />
			</a>
		</div>
		<form class="searchbar" id="searchbar-autocomplete" action="/searchs">
			<div class="searchbar-inner">
				<!-- <div class="searchbar-input-wrap">
					<input type="search" name="q" value=""
						placeholder="직무, 회사, 멘토, 제목" autocomplete="off"> 
						<i class="searchbar-icon"></i> <span class="input-clear-button"></span>
				</div> -->
			</div>
		</form>
		<div class="right">
			<a class="button button-big" type="external" href="">멘토찾기</a> 
			<a class="button button-big" type="external" href="/mentor/noticeboard/noticeboardList">공지사항</a> 
			<a class="button button-big" type="external" href="/mentor/essayboard/essayboardList">에세이</a> 
			<a class="button button-big" type="external" href="/mentor/meetingboard/meetingboardList">모임</a> 
			<a class="button button-big" type="external" href="/mentor/menteeboard/menteeboardList">멘티 게시판</a> 
			<c:if test="${memDTO!=null}">
			<a class="button button-big" type="external" href="/mentor/mentorapply/mentorapplyForm">멘토 지원하기</a>
			</c:if>
			<c:if test="${memDTO.member_email == null}">
			<a class="button button-big" type="external" href="/mentor/member/writeForm">회원가입</a>
			<a class="button button-big" type="external" href="/mentor/member/loginForm">로그인</a>
			</c:if>

			<c:if test="${memDTO.member_email != null}">
			<a type="internal" class="button button-big popover-open me-profile" data-popover=".js-me-popover" href=""> 
				<c:if test="${memDTO.member_profile == 'profile.jpg'}">
				<img src="../image/profile.jpg" width="28" height="28">
				</c:if>
				<c:if test="${memDTO.member_profile != 'profile.jpg'}">
				<img src="../storage/${memDTO.member_email}/${memDTO.member_profile}" width="28" height="28">
				</c:if>
			</a>
			<div class="popover popover-flat me-popover js-me-popover">
				<div class="popover-inner">
					<div class="popover-angle on-top"></div>

					<div class="menu-list">
						<div class="list links-list no-hairlines-between">
							<ul>
								<li><a type="external" href="">멘토찾기</a></li>
								<li><a type="external" href="/mentor/noticeboard/noticeboardList">공지게시판</a></li>
								<li><a type="external" href="/mentor/essayboard/essayboardList">에세이</a></li>
								<li><a type="external" href="/mentor/meetingboard/meetingboardList">모임</a></li>
								<li><a type="external" href="/mentor/mentorapply/mentorapplyForm">멘토지원하기</a></li>
							</ul>
						</div>

						<div class="list links-list no-hairlines-between">
							<ul>
								<li><a type="external" href="">에세이 쓰기</a></li>
								<li><a type="external" href="">나의 질문/답변</a></li>
								<li><a type="external" href="">관심콘텐츠</a></li>
								<li><a type="external" href="">관심멘토</a></li>
							</ul>
						</div>

						<div class="list links-list no-hairlines-between">
							<ul>
								<li><a type="external" href="/mentor/mentee/menteeUserForm">계정설정</a></li>
								<li><a type="external" href="/mentor/member/logout">로그아웃</a></li>
							</ul>
						</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>