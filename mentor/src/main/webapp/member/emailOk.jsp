<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="page navbar-fixed devise after_signup show" data-name="after_signup-show">
	<div class="page-content" style="margin-bottom: 100px;">
		<div class="content-wrapper">
			<div class="block inset hero-title">
				<h1 class="title">인증 완료</h1>
				<p class="description">
					<strong>${member_email}</strong>의 이메일 인증이 완료되었습니다.<br>
					이제 멘토맨의 모든 서비스들을 마음껏 즐겨보세요!
				</p>
			</div>
			<div class="block inset text-center next-button-block">
				<a class="button button-big button-fill" type="external" href="/mentor/member/loginForm?status=true">로그인 하기</a>
			</div>
		</div>
	</div>
</div>
   