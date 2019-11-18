<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="../js/member.js"></script>
<!-- Search PWD Form -->
<div class="page navbar-fixed devise passwords new" data-page="passwords-new">
	<div class="page-content" id="setpwdForm">
		<div class="content-wrapper">
			<div class="block inset hero-title">
				<h1 class="title">비밀번호 재설정</h1>
				<div class="description">계정으로 사용하는 이메일 주소를 입력하시면, 비밀번호 재설정 링크를전송해 드립니다.</div>
			</div>

			<div class="block inset">
				<form class="simple_form new_user" id="new_user" name="setrepwdform" novalidate="novalidate" action="" method="post">
					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" class="string email optional" placeholder="이름" type="email" value="" name="member_name" id="member_name" />
									</div>
								</div>
							</li>
							<div class="name-setpwd-Div"></div>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" class="string email optional" placeholder="이메일 주소" type="email" value="" name="member_email" id="member_email" />
									</div>
								</div>
							</li>
							<div class="email-setpwd-Div"></div>
							
							<div id="inTimer" style="color:red">인증시간 :<span id="timer" style="color: red"></span></div>	
							<li class="item-content item-input" id="setpwdEmailOn"> 
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocapitalize="off" class="string email optional" placeholder="인증번호 입력" type="text" value="" name="set_pwdrandom" id="set_pwdrandom" />
									</div>
								</div>
							</li>
							<input type="button" name="setpwdOnBtn" id="setpwdOnBtn" value="확인" class="btn button button-big button-fill submit-button">
						</ul>
						<div class="setPwd-Div"></div>
					</div>
					<input type="button" name="setpwdBtn" id="setpwdBtn" value="비밀번호 재설정 메일 발송" class="btn button button-big button-fill submit-button" data-disable-with="요청중..." />
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 인증번호 OK 경우 Form  -->
<div class="page navbar-fixed devise passwords edit" data-page="passwords-edit" id="newpwdFormDiv">
	<div class="page-content">
		<div class="content-wrapper">
			<div class="block inset hero-title">
				<h1 class="title">비밀번호 변경</h1>
			</div>
			<div class="block inset">
				<form class="simple_form new_user" id="new_user" novalidate="novalidate" action=""  method="post">
					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocomplete="new-password" class="password optional" placeholder="새 비밀번호" type="password" name="member_pwd" id="member_pwd" />
									</div>
								</div>
							</li>
							<div class="new-newpwd-Div"></div>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input autocomplete="off" class="password optional" placeholder="새 비밀번호 확인" type="password" name="member_repwd" id="member_repwd" />
									</div>
								</div>
							</li>
							<div class="new-newrepwd-Div"></div>
						</ul>
					</div>
					<input type="button" id="newpwdBtn" value="비밀번호 변경하기" class="btn button button-big button-fill submit-button" data-disable-with="저장중..." />
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
$('#newpwdBtn').click(function(){
	var newPwd=$('#member_pwd').val();
	var newRepwd=$('#member_repwd').val();
	var jCont='';
	if(newPwd.length==0){
		jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
		$('.new-newpwd-Div').css('color', 'red').html(jCont);
	}else if(newPwd.length < 8 || newPwd.length > 15){
		jCont = '<div class="msg_error">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.new-newpwd-Div').css('color', 'red').html(jCont);
	}else if(newRepwd.length==0){
		jCont = '<div class="msg_error">비밀번호를 입력해주세요.</div>';
		$('.new-newrepwd-Div').css('color', 'red').html(jCont);
	}else if(newPwd.length != newRepwd.length){
		jCont = '<div class="msg_error">비밀번호가 일치 하지 않습니다.</div>';
		$('.new-newrepwd-Div').css('color', 'red').html(jCont);
	}else if(newPwd.length==newRepwd.length){
		$('.new-newrepwd-Div').remove();
		$('.new-newpwd-Div').remove();
		$.ajax({
			type:'post',
			url:'/mentor/member/newPwdCommit',
			data:'member_name='+$('#member_name').val()+'&member_email='+$('#member_email').val()+'&member_pwd='+$('#member_pwd').val(),
			success:function(){
			          var toastTop = app.toast.create({
			            text: '비밀번호가 변경되었습니다.',
			            position: 'top',
			          });
			          toastTop.open();
				location.href='/mentor/main/index';
			},error:function(e){
				conlose.log(e);
			}
		});
	}
	});
});
</script>