<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed devise registrations new" data-name="registrations-new">
	<div class="page-content">
		<div class="content-wrapper">
			<div class="block inset social-block">
				<a class="button button-big button-fill bg-naver-color"
					type="external" href=""> 네이버 아이디로 가입 </a> 
					<a class="button button-big button-fill bg-kakao-color col"
					type="external" href=""> 카카오 아이디로 가입 </a>
			</div>

			<div class="block inset login-block">
				<form class="simple_form new_user" name="writeForm" id="writeForm"
					method="post" action="/mentor/member/write">
					<input type="hidden" name="member_flag" value="0">
					<div class="signup-or-separator">
						<span class="signup-or-separator-text">또는</span><hr>
					</div>

					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocomplete="name" class="string required"
											placeholder="이름" type="text" name="member_name"
											id="member_name" value="" onkeyup="Ifn_NameCheck();" />
										<div class="item-input-info-Name"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocorrect="off"
											autocomplete="NickName" class="string NickName required"
											placeholder="닉네임" type="text" value="" maxlength="20"
											name="member_nickname" id="member_nickname"
											onkeyup="Ifn_NickCheck();" />
										<div class="item-input-info-NickName"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocorrect="off"
											autocomplete="email" required="required"
											class="string email required" aria-required="true"
											placeholder="이메일 주소" type="email" value=""
											name="member_email" id="member_email" maxlength="50"
											onkeypress="Ifn_EmailCheck();" />
										<div class="item-input-info-Email"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" autocapitalize="off"
											autocomplete="new-password" class="password required"
											aria-required="true" placeholder="비밀번호" type="password"
											name="member_pwd" id="member_pwd" onkeyup="Ifn_PwdCheck();" />
										<div class="item-input-info-Pwd"></div>
									</div>
								</div>
							</li>

							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" class="password"
											placeholder="비밀번호 확인" type="password" name="member_repwd"
											id="member_repwd" onkeyup="Ifn_RepwdCheck();" />
										<div class="item-input-info-Repwd"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>


					<div class="block-footer term-footer">
						<input name="" type="hidden" value="0" /> <input type="checkbox"
							value="1" checked="checked" name="" id="user_terms_of_service" />
						회원가입을 하면 MENTORMAN의 <a href="" type="external" target="_blank">이용약관</a>
						및 <a href="" type="external" target="_blank">개인정보처리방침</a>에 동의하는
						것으로 간주합니다.
						<div></div>
					</div>

					<input type="submit" onclick="Ifn_write();" value="회원가입" 
					class="btn button button-big button-fill" data-disable-with="요청중..." />
				</form>
			</div>


			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="/mentor/member/loginForm"> 이미 계정이 있나요? 로그인 </a>
			</div>
		</div>
	</div>
</div>

