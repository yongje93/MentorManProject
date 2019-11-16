<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed devise sessions new" data-name="sessions-new">
	<div class="page-content">
		<div class="content-wrapper">
			<h1 align="center">로그인</h1>
			<div class="block inset social-block">
				<div id="kakao_id_login" style="text-align: center">
				<a type="external" href="${kakaoUrl}">
					<img width="" src="../image/kakao_account_login_btn_medium_wide.png"/>
				</a>
				</div>
				<a class="button button-big button-fill bg-naver-color" type="external" href=""> 네이버 아이디로 로그인</a> 
			</div>
			<div class="block inset login-block">
				<form class="simple_form new_user" id="new_user" novalidate="novalidate" action="" method="post">
					<div class="signup-or-separator">
						<span class="signup-or-separator--text">또는</span>
						<hr>
					</div>
					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocomplete="email" class="string email required" placeholder="이메일 주소" type="email" name="member_email" id="member_email_Login" />
										<div id="memberEmailDiv" class="login-member-email-Div"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" class="password required" placeholder="비밀번호" type="password" name="member_pwd" id="member_pwd_Login"/>
										<div id="memberPwdDiv" class="login-member-pwd-Div"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="block-footer forgot-password text-align-right">
						<a class="color-gray" type="external" href="">비밀번호를 잊으셨나요?</a>
					</div>
					<input type="button" id="loginBtn" value="로그인" class="btn button button-big button-fill submit-button"	data-disable-with="요청중..."/>
					<div id="loginResultDiv"></div>
				</form>
			</div>
			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="/mentor/member/writeForm">
					계정이 없으세요? 회원가입
				</a>
			</div>
		</div>
	</div>
</div>
<script src="../js/member2.js"></script>