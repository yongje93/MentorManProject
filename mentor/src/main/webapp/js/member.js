/* 
 * 날짜 : 2019.11.01 
 * 작성자 :ginkgo1928
 * 설명 : 회원가입 JavaScript
 */
//Email 유효성 검사
function emailCheck(strVal, text) {
	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	if(strVal.length == 0)	{
		return true;
	}
	if (!strVal.match(regExp))	{
		return true;
	}
	return false;
}
//Name 유효성 검사
function Ifn_NameCheck() {
	var name = $('#member_name').val();
	var jCont = "";
	
	if (name.length==0) {
		jCont = '<div class="msg_error">이름을 입력해주세요.</div>';
		$('.item-input-info-Name').css('color', 'red').html(jCont);
		$('#member_name').focus();
		$('#member_name').addClass("error");
		return false;
	}else {
		jCont = "";
		$('#member_name').removeClass("error");
		$('.item-input-info-Name').html(jCont);
		return true;
	}
	
}

//NickName유효성 검사
function Ifn_NickCheck(){
	var nickName=$("#member_nickname").val();
	var jCont="";
	
	if(nickName.length==0){
		jCont = '<div class="msg_error 01">닉네임을 입력해주세요.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
		return false;
		
	}else if(nickName.length< 3 || nickName.length>22){
		jCont = '<div class="msg_error 02">닉네임 3자~22자. 이하입니다.</div>';
		$('.item-input-info-NickName').css('color', 'red').html(jCont);
		$('#member_nickname').focus();
		$('#member_nickname').addClass("error");
		return false;
	}else{
		$.ajax({
			type:'post',
			url:'/mentor/member/writeNicknamecheck',
			data:{'member_nickname':nickName},
			dataType:'text',
			success:function(data){
				if(data=="exist"){
				jCont='<div class="msg_nickok">사용 가능한 닉네임 입니다.</div>';
				$('.item-input-info-NickName').css('color', 'blue').html(jCont);
				}else if(data=="not_exist"){
				jCont='<div class="msg_nickerror">입력하신 닉네임은 사용중인 닉네임 입니다.</div>';
				$('.item-input-info-NickName').css('color', 'red').html(jCont);
				}
			},error:function(e){
				conlose.log(e);
			}
		});
	}	
	return true;
	
}
//Email 유효성 검사
function Ifn_EmailCheck(){
	var Email=$("#member_email").val();
	var jCont="";
	if(Email.length==0){
		jCont = '<div class="msg_error 03">이메일을 입력해주세요.</div>';
		$('.item-input-info-Email').css('color', 'red').html(jCont);
		$('#member_email').focus();
		$('#member_email').addClass("error");
		return false;	
	}else if(emailCheck(Email)){
		jCont = '<div class="msg_error 03">올바르지 않은 이메일 형식입니다.</div>';
		$('.item-input-info-Email').css('color', 'red').html(jCont);
		$('#member_email').focus();
		$('#member_email').addClass("error");
		return false;
	}else{
		$.ajax({
			type:'post',
			url:'/mentor/member/writeEmailCheck',
			data:{'member_email':Email},
			dataType:'text',
			success:function(data){
			if(data=="email_ok"){
				jCont='<div class="msg_emailok">사용 가능한 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'blue').html(jCont);
			}else if(data=="email_fail"){
				jCont='<div class="msg_emailerror">입력하신 이메일은 사용중인 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'red').html(jCont);
			}
			},error:function(e){
				conlose.log(e);
			}
		});
	}
	return true;
}
//Pwd유효성 검사
function Ifn_PwdCheck(){
	var Pwd=$('#member_pwd').val();
	var jCont="";
	if(Pwd.length==0){
		jCont='<div class="msg_pwderror">비밀번호를 입력해주세요.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
		return false;
	}else if(Pwd.length< 8 ||Pwd.length >15){
		jCont='<div class="msg_pwderror">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.item-input-info-Pwd').css('color','red').html(jCont);
		$('#member_pwd').focus();
		$('#member_pwd').addClass("error");
		return false;
	}else {
		jCont='<div class="msg_pwdok">사용가능 합니다.</div>';
		$('.item-input-info-Pwd').css('color','blue').html(jCont);
		$('#member_pwd').removeClass("error");
		return true;
	}
}
//Repwd유효성 검사
function Ifn_RepwdCheck(){
	if($('#member_pwd').val()!=$('#member_repwd').val()){
		jCont='<div class="msg_repwderror">비밀번호가 일치하지 않습니다.</div>';
		$('.item-input-info-Repwd').css('color','red').html(jCont);
		$('#member_repwd').focus();
		$('#member_repwd').addClass("error");
		return false;
	}else if($('#member_pwd').val()==$('#member_repwd').val()){
		jCont='<div class="msg_repwdok">일치합니다.</div>';
		$('.item-input-info-Repwd').css('color','blue').html(jCont);
		$('#member_repwd').removeClass("error");
		return true;
	}
}

//정보 입력 후  submit으로 값을 넘긴다.
function Ifn_write(){
 if (Ifn_NameCheck()&&Ifn_NickCheck()&&Ifn_EmailCheck()&&Ifn_PwdCheck()&&Ifn_RepwdCheck()) {
	 $('#writeForm').submit();
	}
}

//Login
$(document).ready(function(){
	$('#loginBtn').click(function(){
		var jCont="";
		if($('#member_email').val()==''){
			jCont = '<div class="msg_error 11">이메일을 입력해주세요.</div>';
			$('.login-member-email-Div').css('color', 'red').html(jCont);
			$('#member_email').focus();
			$('#member_email').addClass("error");
		}else if($('#member_pwd').val()==''){
			jCont = '<div class="msg_error 22">비밀번호를 입력해주세요.</div>';
			$('.login-member-pwd-Div').css('color', 'red').html(jCont);
			$('#member_pwd').focus();
			$('#member_pwd').addClass("error");
		}else{
			$.ajax({
				type:'post',
				url:'/mentor/member/login',
				data:'member_email=' + $('#member_email').val() + '&member_pwd=' + $('#member_pwd').val(),
				dataType:'text',
				success(data){
					if (data == 'login_ok') {
						location.href = '/mentor/main/index'
					} else if (data == 'login_fail') {
						$('.login-Div').text('아이디 또는 비밀번호가 틀립니다').css('color', 'red')
					}
				},error:function(e){
					conlose.log(e);
					
				}
			});
			
			$('#member_email').removeClass("error");
			$('#member_pwd').removeClass("error");
		}
	});
});

