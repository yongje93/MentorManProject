<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 회원가입 후 사이트 -->    
<div class="page navbar-fixed devise after_signup show" data-name="after_signup-show">
	<div class="page-content" style="margin-bottom: 100px;">
		<div class="content-wrapper">
			<div class="block inset hero-title">
				<h1 class="title">계정 확인</h1>

				<p class="description">
					<strong>${member_email}</strong>으로 전송된 이메일을 확인하여 가입 절차를 완료해주세요.
					이메일은 아이디/비밀번호 찾기 등 고객지원을 위해 사용됩니다. 전송된 이메일을 꼭 확인하신 후 인증 링크를 클릭해주세요.
				</p>
			</div>

			<div class="block inset text-center next-button-block">
				<a class="button button-big button-fill" type="external" href="../main/index">홈으로 돌아가기</a>
			</div>

			<div class="block inset">
				인증 메일을 못 받으셨나요? <a type="external" href="">인증 메일 재전송 </a>
			</div>
		</div>
	</div>
</div>
