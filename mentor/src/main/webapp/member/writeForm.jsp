<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../css/mypg.css" type="text/css" />

<div class="page navbar-fixed devise registrations new" data-name="registrations-new">
	<div class="page-content" style="margin-bottom: 50px;">
		<div class="content-wrapper">
			<h1 align="center">회원가입</h1>
			<div class="block inset">
				<form class="simple_form new_user" name="writeForm" id="writeForm" method="post" enctype="multipart/form-data" action="/mentor/member/write">
					<div style="width: 100%; text-align: center;">
						<div class="member-img-circle" style="display: inline-block;">
							<img id="member_profile_img" src="../image/profile.jpg"/>
						</div>
						<div class="block">
							<div class="file-container">
								<label for="user_profile_image" class="button button-small button-inline">
              						이미지 업로드
            					</label>
            					<input class="is-valid file optional" id="user_profile_image" name="member_profile" accept=".jpg, .jpeg, .png" type="file" onchange="previewFile(this);" style="display: none;"/> 
							</div>
						</div>
					</div>
					<div class="list form-list no-hairlines no-margin-top">
						<ul>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input class="string email required" type="email" name="member_email" id="member_email" maxlength="50" placeholder="이메일 주소"/>
										<div id="member_emailDiv" class="item-input-info-Email"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input class="string required" type="text" name="member_name" id="member_name" placeholder="이름"/>
										<div id="member_nameDiv" class="item-input-info-Name"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input class="string NickName required" type="text" maxlength="20" name="member_nickname" id="member_nickname" placeholder="닉네임"/>
										<div id="member_nicknameDiv" class="item-input-info-NickName"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input class="password required" type="password" name="member_pwd" id="member_pwd" placeholder="비밀번호"/>
										<div id="member_pwdDiv" class="item-input-info-Pwd"></div>
									</div>
								</div>
							</li>
							<li class="item-content item-input">
								<div class="item-inner">
									<div class="item-input-wrap">
										<input class="password required" type="password" name="member_repwd" id="member_repwd" placeholder="비밀번호 확인"/>
										<div id="member_repwdDiv" class="item-input-info-Repwd"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="block-footer term-footer">
						<input name="" type="hidden" value="0"/> 
						<input type="checkbox" value="1" checked="checked" name="" id="user_terms_of_service"/>
						회원가입을	하면 MENTORMAN의 
						<a href="" type="external" target="_blank">이용약관</a> 및
						<a href="" type="external" target="_blank">개인정보처리방침</a> 에
						동의하는것으로 간주합니다.
					</div>
					<input type="button" id="memberWriteBtn" value="회원가입" class="btn button button-big button-fill" data-disable-with="요청중..."/>
				</form>
			</div>
			<div class="block inset text-align-center">
				<a class="color-gray" type="external" href="/mentor/member/loginForm"> 이미 계정이 있나요? 로그인 </a>
			</div>
		</div>
	</div>
</div>
<script src="../js/member2.js"></script>
<script>
$(document).ready(function(){
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
            reader.onload = function (e) {
            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                $('#member_profile_img').attr('src', e.target.result);
                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                //(아래 코드에서 읽어들인 dataURL형식)
            }                   
            reader.readAsDataURL(input.files[0]);
            //File내용을 읽어 dataURL형식의 문자열로 저장
        }
    }//readURL()--

    //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
    $("#user_profile_image").change(function(){
        //alert(this.value); //선택한 이미지 경로 표시
        readURL(this);
    });
 });
</script>