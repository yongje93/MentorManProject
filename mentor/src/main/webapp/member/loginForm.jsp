<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="../js/member.js"></script>

<div class="page navbar-fixed devise sessions new"
	data-name="sessions-new">
	<div class="page-content">
		<div class="content-wrapper">
			<div class="block inset social-block">
				<a class="button button-big button-fill bg-naver-color"
					type="external" href=""> 네이버 아이디로 로그인 </a> 
					<a class="button button-big button-fill bg-kakao-color col"
					type="external" href=""> 카카오 아이디로 로그인 </a>
			</div>

			<div class="block inset login-block">
				<form class="simple_form new_user" id="new_user"
					novalidate="novalidate" action=""
					accept-charset="UTF-8" method="post">
					<input name="utf8" type="hidden" value="&#x2713;" />
					<div class="signup-or-separator">
						<span class="signup-or-separator--text">또는</span>
						<hr>
					</div>

					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocorrect="off"
											autocomplete="email" required="required"
											class="string email required" aria-required="true"
											placeholder="이메일 주소" type="email" value="" name="member_email"
											id="member_email" />
											<div class="login-member-email-Div"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" autocapitalize="off"
											autocomplete="current-password" class="password required"
											aria-required="true" placeholder="비밀번호" type="password"
											name="member_pwd" id="member_pwd"  />
											<div class="login-member-pwd-Div"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="login-Div"></div>
					<div class="block-footer forgot-password text-align-right">
						<a class="color-gray" type="external" href="">
							비밀번호를 잊으셨나요? </a>
					</div>

					<input type="button" id="loginBtn" value="로그인" class="btn button button-big button-fill submit-button"
						data-disable-with="요청중..." />
				</form>
			</div>


			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="../member/writeForm"> 계정이 없으세요? 회원가입 </a>
			</div>
		</div>
	</div>
</div>