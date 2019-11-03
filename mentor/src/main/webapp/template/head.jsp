<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navbar">
	<div class="navbar-inner">
		<div class="left">
			<a class="logo" type="external" href="/"> 
			<img src="https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/logo-c99a7cbe11906a7c7a8084fbb47e605c16d0586f068ea095c19efc48f6d087e6.png" />
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
			<a class="button button-big" type="external" href="/mentors">
				멘토찾기 </a> <a class="button button-big" type="external" href="/open_mentorings"> 콘텐츠 </a> 
				<a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">에세이 </a>
			<div class="beta-div">
				<a class="button button-big program-button" type="external" href="/mentor/meetingboard/meetingboardList">모임</a>
			</div>
			<a class="button button-big beta-div" type="external"
				href="/mentor_requests/new"> 멘토 지원하기 </a> <a
				class="button button-big" type="external" href="/users/sign_up">회원가입</a>
			<a class="button button-big" type="external" href="../member/sign">로그인</a>
		</div>
	</div>
</div>


