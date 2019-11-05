<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar">
	<div class="navbar-inner">
		<div class="left">
			<a class="logo" type="external" href="/mentor/main/index"> 
			<img src="../image/mentorlogo.jpg"/>
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
			<a class="button button-big" type="external" href="/mentors">멘토찾기</a> 
			<a class="button button-big" type="external" href="/open_mentorings">콘텐츠</a> 
			<a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">에세이</a>
			<a class="button button-big" type="external" href="/mentor/meetingboard/meetingboardList">모임</a>
			<a class="button button-big" type="external" href="/mentor_requests/new">멘토 지원하기</a> 
			<a class="button button-big" type="external" href="/mentor/member/writeForm">회원가입</a>
			<a class="button button-big" type="external" href="/mentor/member/loginForm">로그인</a>
			<!-- 일단 로그인 했을때 보이게만 하려고 임시로 둔거에요. -용제 -->
			<c:if test="${memEmail != null}">
				닉네임 : ${memNickname} 이메일 : ${memEmail}
			</c:if>
		</div>
	</div>
</div>
