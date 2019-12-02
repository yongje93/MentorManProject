<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="page navbar-fixed devise sessions new" data-name="sessions-new">
	<div class="page-content">
		<div class="content-wrapper">
			<h1 align="center">로그인</h1>
			<div class="block inset social-block">
				<a class="button button-big button-fill bg-naver-color" type="external" href="${naverUrl}">네이버 아이디로 로그인</a>
				<a class="button button-big button-fill bg-kakao-color col" type="external" href="${kakaoUrl}">카카오 아이디로 로그인</a>
			</div>
			<div class="block inset login-block">
				<input type="hidden" id="flag" value="${flag}">
				<form class="simple_form new_user" id="memberLoginForm"
					name="memberLoginForm" action="<c:url value="/member/login"/>" method="post">
					<div class="signup-or-separator">
						<span class="signup-or-separator--text">또는</span>
						<hr>
					</div>
					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" autocomplete="email" class="string email required" placeholder="이메일 주소" type="email" name="member_email" id="member_email" />
										<div class="login-member-email-Div"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input required="required" class="password required" placeholder="비밀번호" type="password" name="member_pwd" id="member_pwd" />
										<div class="login-member-pwd-Div"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="login-Div"></div>
					<div class="block-footer forgot-password text-align-right">
						<input type="checkbox" id="cheboxid" name="cheboxid" value="" data-check="check" style="position: absolute; left: 16px;">
						<div style="position: absolute; left: 32px;">이메일 저장</div>
						<a class="color-gray" type="external" href="../member/setpwdForm">비밀번호를 잊으셨나요? </a>
					</div>
					<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}" />
					<input type="button" id="loginBtn" value="로그인"	class="btn button button-big button-fill submit-button" data-disable-with="요청중..." />
				</form>
			</div>
			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="../member/writeForm">
					계정이 없으세요? 회원가입 </a>
			</div>
		</div>
	</div>

</div>
<script type="text/javascript">
/* Email 저장을 눌렀을 JavaScript 쿠키 세팅*/
//Cookie Set
function setCookie(cookieName, value, exdays){
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + exdays);
  var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
  document.cookie = cookieName + "=" + cookieValue;
}
//Cookie Delete
function deleteCookie(cookieName){
  var expireDate = new Date();
  expireDate.setDate(expireDate.getDate() - 1);
  document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
//Cookie Get
function getCookie(cookieName) {
  cookieName = cookieName + '=';
  var cookieData = document.cookie;
  var start = cookieData.indexOf(cookieName);
  var cookieValue = '';
  if(start != -1){
      start += cookieName.length;
      var end = cookieData.indexOf(';', start);
      if(end == -1)end = cookieData.length;
      cookieValue = cookieData.substring(start, end);
  }
  return unescape(cookieValue);
}
//Login 로그인 (아이디 저장을 누르면 JavaScript Cookie에서 저장)
$(document).ready(function() {
   var member_Email = getCookie('Cookie_email'); // --> 저장된 저장된 Cookie의 값
   $('#member_email').val(member_Email); // Cookie담는다.
      if ($('#member_email').val() != '') { // --> 전에 이메일 저장을 눌렀을 경우 그대로 둔다.
         $('#cheboxid').attr('checked', true);
      }
   $('#cheboxid').change(function() { // --> Checkbox 변화 발생시
      if ($('#cheboxid').is(':checked')) {
         var member_Email = $('#member_email').val();
         setCookie('Cookie_email', member_Email, 7); // -->7일 동안 Cookie 보관
      } else {
            deleteCookie('Cookie_email');
      }
   });
   $('#member_email').keyup(function() {// Checkbox을 누르고 Email을 입력할때도 Cookie저장
      if ($('#cheboxid').is(':checked')) {
         var member_Email = $('#member_email').val();
         setCookie('Cookie_email', member_Email, 7);
      }
   });
   $('#loginBtn').click(function(){
      var jCont = '';
      var email = $('#member_email').val();
      var pwd = $('#member_pwd').val();
      if (email.length == 0) {
         jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
         $('.login-member-email-Div').css('color', 'tomato').css('font-size','9pt').html(jCont);
      } else if (pwd.length == 0) {
         jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
         $('.login-member-pwd-Div').css('color', 'tomato').css('font-size','9pt').html(jCont);
      } else if (email != 0 && pwd != 0) {
         $('.login-member-pwd-Div').remove();
         $('.login-member-email-Div').remove();
         $('form[name=memberLoginForm]').submit();
      }
   });
});
</script>
<script>
if('${ERRORMSG}' == '1') {
    document.addEventListener("DOMContentLoaded", function(event) {
       var toastTop = app.toast.create({
           text: '아이디 또는 비밀번호를 잘못 입력했습니다.',
            position: 'top',
            closeButton: true
       });
       toastTop.open();
    });
} else if('${ERRORMSG}'  == '2') {
    document.addEventListener("DOMContentLoaded", function(event) {
       var toastTop = app.toast.create({
           text: '계정이 존재하지 않습니다.',
            position: 'top',
            closeButton: true
       });
       toastTop.open();
    });
} else if('${ERRORMSG}'  == '3') {
    document.addEventListener("DOMContentLoaded", function(event) {
       var toastTop = app.toast.create({
           text: '이메일 인증을 해주세요.',
            position: 'top',
            closeButton: true
       });
       toastTop.open();
    });
} else if('${ERRORMSG}'  == '4') {
    document.addEventListener("DOMContentLoaded", function(event) {
       var toastTop = app.toast.create({
           text: '이미 로그인 중입니다.',
            position: 'top',
            closeButton: true

       });
       toastTop.open();
 	});
} else if('${status}' != 'true') {
 	document.addEventListener("DOMContentLoaded", function(event) {
    	var toastTop = app.toast.create({
        	text: '먼저 로그인 해주세요.',
         	position: 'top',
         	closeButton: true
       });
       toastTop.open();
    });
}
</script>
