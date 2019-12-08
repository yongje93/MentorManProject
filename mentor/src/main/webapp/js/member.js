/*  날짜 : 2019.11.01 
 * 작성자 :ginkgo1928
 * 설명 : 회원가입 JavaScript 유효성 검사*/

//Email 정규식 검사
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
//특수문자 검사
function passwordCheck(str) {
	var reg = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/;
	return reg.test(str);
}

//Name 유효성 검사
function Ifn_NameCheck(){
	var name = $('#member_name').val();
	var reg = /[ 0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@#$%&\'\"\\\(\=]/gi;
	var jCont = "";
	if (name.length==0) {
		jCont = '<div class="msg_error01">이름을 입력해주세요.</div>';
		$('.item-input-info-Name').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return false;
	}else if(reg.test(name)){
		jCont = '<div class="msg_error02">한글,영문만 입력 가능합니다.</div>';
		$('.item-input-info-Name').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return false;
	}else{
		jCont = "";
		$('.item-input-info-Name').html(jCont);
		return true;
	}
	
}
//NickName 유효성 검사(isNicknameValid : 전역변수 사용)
var isNicknameValid = false;
$(document).ready(function(){
	$('#member_nickname').focusout(function(){
		var nickName=$("#member_nickname").val();
		var jCont="";
		if(nickName.length==0){
			jCont = '<div class="msg_error">닉네임을 입력해주세요.</div>';
			$('.item-input-info-NickName').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isNicknameValid = false;
		}else if(nickName.length < 3 || nickName.length > 15){
			jCont = '<div class="msg_error">닉네임 3자~15자.이하입니다.</div>';
			$('.item-input-info-NickName').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isNicknameValid = false;
		}else{
			$.ajax({
				type:'post',
				url:'/mentor/member/writeNicknamecheck',
				data:{'member_nickname':nickName},
				dataType:'text',
				success:function(data){
					if(data=="exist"){
						jCont='<div class="msg_nickok">사용 가능한 닉네임 입니다.</div>';
						$('.item-input-info-NickName').css('color', 'blue').css('font-size','9pt').css('text-align', 'left').html(jCont);
						isNicknameValid=true;
					}else if(data=="not_exist"){
						jCont='<div class="msg_nickerror">입력하신 닉네임은 사용중인 닉네임 입니다.</div>';
						$('.item-input-info-NickName').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
						isNicknameValid=false;
						}
			},error:function(e){
				console.log(e);
			}
			});
		}	
//		isNicknameValid=true;
	});
});

//Email  유효성 검사(isEmailcheck : 전역변수 사용)
var isEmailcheck = false;
$(document).ready(function(){
	$('#member_email').focusout(function(){
		var Email=$("#member_email").val();
		var jCont="";
		if(Email.length==0){
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.item-input-info-Email').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isEmailcheck=false;	
		}else if(emailCheck(Email)){
			jCont = '<div class="msg_error">올바르지 않은 이메일 형식입니다.</div>';
			$('.item-input-info-Email').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isEmailcheck= false;
		}else{
		$.ajax({
			type:'post',
			url:'/mentor/member/writeEmailCheck',
			data:{'member_email':Email},
			dataType:'text',
			success:function(data){
			if(data=="email_ok"){
				jCont='<div class="msg_emailok">사용 가능한 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'blue').css('font-size','9pt').css('text-align', 'left').html(jCont);
				isEmailcheck=true;
			}else if(data=="email_fail"){
				jCont='<div class="msg_emailerror">입력하신 이메일은 사용중인 이메일 입니다.</div>';
				$('.item-input-info-Email').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
				isEmailcheck=false;
			}
			},error:function(e){
				console.log(e);
			}
		});
		isEmailcheck= true;
		}
	});
});


//Pwd유효성 검사
function Ifn_PwdCheck(){
	var Pwd=$('#member_pwd').val();
	var jCont="";
	if(Pwd.length==0){
		jCont='<div class="msg_pwderror">비밀번호를 입력해주세요.</div>';
		$('.item-input-info-Pwd').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return false;
	}else if(Pwd.length< 8 ||Pwd.length >15){
		jCont='<div class="msg_pwderror">비밀번호는 8자~15자리 이하입니다.</div>';
		$('.item-input-info-Pwd').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return false;
	}else {
		jCont='<div class="msg_pwdok">사용가능 합니다.</div>';
		$('.item-input-info-Pwd').css('color', 'blue').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return true;
	}
}
//Repwd유효성 검사
function Ifn_RepwdCheck(){
	if($('#member_pwd').val()!=$('#member_repwd').val()){
		jCont='<div class="msg_repwderror">비밀번호가 일치하지 않습니다.</div>';
		$('.item-input-info-Repwd').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return false;
	}else if($('#member_pwd').val()==$('#member_repwd').val()){
		jCont='<div class="msg_repwdok">일치합니다.</div>';
		$('.item-input-info-Repwd').css('color','blue').css('font-size','9pt').css('text-align', 'left').html(jCont);
		return true;
	}
}

/*회원가입 체크 박스 검사 여부 (member_write : 전역변수 사용)
체크박스 checked이며 checkbox를 해제 하면 경고메세지 발생*/
var member_write = true;
$(document).ready(function() {
	$(document).on('change',"[data-check='check']",function() {
		var isWriteCheck = $('#member_servicecheck').prop('checked');
		var jCont = "";
		if (isWriteCheck == false) {
			jCont = '<div class="error_checkbox">약관을 확인하고 체크해주세요.</div>';
			$('.item-input-info-checkbox').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			member_write = false;
		} else if (isWriteCheck != false) {	
			member_write = true;
		}
	});
});

//회원 가입
$(document).ready(function(){
	$('#writeBtn').click(function(){
		if(Ifn_NameCheck()&& isEmailcheck==true && isNicknameValid==true && Ifn_PwdCheck() && Ifn_RepwdCheck()&&member_write==true) {
			
			var form = $('#writeForm')[0];
            var formData = new FormData(form);
            //formData.append("file", $("#user_profile_image")[0].files[0]);
            
            //S3 파일 업로드 및 write 
			$.ajax({
				type:'post',
				enctype: 'multipart/form-data',
				url:'/mentor/storageIO/uploadAjax',
				processData: false,
                contentType: false,
                cache: false,
				data: formData,
				dataType: 'text',
				success:function(data){
					alert(data);
					if(data == 'success'){
						//이동
					}
				},
				error : function(err){
					alert('err');
				}
			});
			
		}
	});
});
//이메일 인증 시간 3분 제한
function $ComTimer(){
}
$ComTimer.prototype = {
    comSecond : ""
    , fnCallback : function(){}
    , timer : ""
    , domId : ""
    , fnTimer : function(){
    	debugger;
        var m = Math.floor(this.comSecond / 60) + "분 " + (this.comSecond % 60) + "초";	// 남은 시간 계산
        this.comSecond--;					// 1초씩 감소
        this.domId.innerText = m;
        if (this.comSecond < 0) {			// 시간이 종료 되었으면..
            clearInterval(this.timer);
            jCont='<div class="msg_emailok">인증 시간이 초과했습니다 다시 인증해주세요.</div>';
			$('.setPwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			$('#setpwdBtn').show();
			$('#setpwdEmailOn').hide();
			$('#setpwdOnBtn').hide()
        }    
    }//타이머
    ,fnStop : function(){
        clearInterval(this.timer);
    }
}

//비밀번호 재설정
var isSetPwdTimer=false;
$(document).ready(function(){
	$('#setpwdEmailOn').hide();
	$('#setpwdOnBtn').hide();
	$('#newpwdFormDiv').hide();
	$('#inTimer').hide();
	$('.name-setpwd-Div').empty();
	$('.email-setpwd-Div').empty();
	var jCont='';
	$('#setpwdBtn').click(function(){
		if($('#member_name').val()==''){
			jCont = '<div class="msg_error">이름을 입력해주세요.</div>';
			$('.name-setpwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isSetPwdTimer=false;
		}else if($('#member_email').val()==''){
			jCont = '<div class="msg_error">이메일을 입력해주세요.</div>';
			$('.email-setpwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
			isSetPwdTimer=false;
		}else if($('#member_name').val()!='' || $('#member_email').val()!='') {
			$('.name-setpwd-Div').remove();
			$('.email-setpwd-Div').remove();
			$('#rodingImg').removeClass('hideRoding');
			$.ajax({
				type:'post',
				url:'/mentor/member/setmemberpwd',
				data:'member_name='+$('#member_name').val()+'&member_email='+$('#member_email').val(),
				dataType:'text',
				success:function(data){
					$('#rodingImg').addClass('hideRoding');
					
					 if(data=='get_member'){
						jCont='<div class="msg_emailok">비밀번호 재설정 인증번호를 발송했습니다.</div>';
						$('.setPwd-Div').css('color', 'blue').css('font-size','9pt').css('text-align', 'left').html(jCont);
						$('#setpwdEmailOn').show();
						$('#setpwdOnBtn').show();
						$('#inTimer').show();		
						$('#setpwdBtn').hide();
						isSetPwdTimer=true;
						if(isSetPwdTimer==true){ //timer
						var AuthTimer = new $ComTimer()
						AuthTimer.comSecond = 180;
						AuthTimer.fnCallback = function(){
						}
						AuthTimer.timer =  setInterval(function(){AuthTimer.fnTimer()},1000);
						AuthTimer.domId = document.getElementById("timer");
						}//timer
					}else if(data=='not_member'){
						jCont='<div class="msg_emailfail">찾을수가 없습니다. 다시 확인 해주세요.</div>';
						$('.setPwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
						isSetPwdTimer=false;
					}
					},error:function(e){
						console.log(e);
					}
			});//aj
			
			
		}
	});
	//인증번호 입력
	$('#setpwdOnBtn').click(function(){
		if($("#set_pwdrandom").val()==''){
			jCont = '<div class="msg_error">인증번호를 입력해주세요.</div>';
			$('.setPwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
		}else if($('#set_pwdrandom').val!=''){
			$('.setPwd-Div').empty();
			$.ajax({
				type:'post',
				url:'/mentor/member/setmemberpwdrandom',
				data:'set_pwdrandom='+$('#set_pwdrandom').val(),
				dataType:'text',
				success:function(data){
				 if(data=='set_randomOk'){
					$('#setpwdEmailOn').hide();
					$('#setpwdOnBtn').hide();
					$('#setpwdForm').hide();
					$('#newpwdFormDiv').show();
				 }else if(data=='set_randomFail'){
					 jCont = '<div class="msg_error">인증번호가 틀렸습니다. 다시 확인 해주세요.</div>';
					 $('.setPwd-Div').css('color', 'tomato').css('font-size','9pt').css('text-align', 'left').html(jCont);
				 }
				},error:function(e){
					console.log(e);
				}		
			});
		}
	});
	
	//질문 삭제
	$('#delete_question').on('click', function(){
		  var toastWithCallback = app.toast.create({
		      text: '정말로 삭제하시겠습니까?',
		      position: 'center',
		      closeButton: true,
		      on: {
		         close: function() {
		        	 $.ajax({
		 				type: 'post',
		 				url: '/mentor/member/questionDelete',
		 				data: {'question_seq': $('#question_seq').val()},
		 				success: function(){
		 					location.href='/mentor/member/myQandA';
		 				},
		 				error: function(){
		 					alert('에러');
		 				}
		 			});
		         }
		      }
		   });   
		   toastWithCallback.open();
	});
	
	//답변 보내기
	$('#answer_btn').on('click', function(){
		$('#answer_content_error').empty();
		if($('#answer_content').val()==''){
			$('#answer_content_error').text('멘티에게 답변을 보내주세요').css('color', 'red');
			$('#answer_content_error').css('font-size','8pt');
			$('#answer_content').focus();
		}else {
			$.ajax({
				type: 'post',
				url: '/mentor/member/answerSuccess',
				data: $('#mentorAnswer_from').serialize() ,
				success: function(){
					location.href='/mentor/member/myQandA';
				},
				error: function(){
					
				}
			});
		}
	});
	
	//답변 수정
	$('#answerModify_btn').on('click', function(){
		$('#answer_content_error').empty();
		if($('#answer_content').val()==''){
			$('#answer_content_error').text('멘티에게 답변을 보내주세요').css('color', 'red');
			$('#answer_content_error').css('font-size','8pt');
			$('#answer_content').focus();
		}else {
			$.ajax({
				type: 'post',
				url: '/mentor/member/answerModify',
				data: $('#mentorAnswer_from').serialize() ,
				success: function(){
					location.href='/mentor/member/myQandA';
				},
				error: function(){
					
				}
			});
		}
	});
	
});
