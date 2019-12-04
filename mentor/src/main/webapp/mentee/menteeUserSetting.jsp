<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-100 tablet-80" style="margin: auto;">
<div class="block no-hairlines hero-title">
	<h1 class="title">내 정보</h1>
</div>
<div class="block inset">
	<form class="simple_form edit_user" id="menteeUserSetting" name="menteeUserSetting" novalidate="novalidate" enctype="multipart/form-data" accept-charset="UTF-8" method="post">
		<div class="list form-list no-hairlines">
			<ul>
				<div class="label-title">
					<label class="string required" for="user_name">이름
						<abbr title="required">*</abbr>
					</label>
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class=" is-valid string required" placeholder="이름" type="text" value="${memberDTO.member_name}" name="member_name" id="member_name">
							<!-- 데이터 받아와야됨 -->
						</div>
						<div id="member_name_error"></div>
					</div>
				</li>
				<div class="label-title">
					<label class="email required" for="user_email">이메일 
						<abbr title="required">*</abbr>
					</label>
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class=" is-valid string email optional" readonly="readonly" placeholder="이메일 주소" type="text" value="${memberDTO.member_email}" name="member_email" id="member_email">
							<!-- 데이터 받아와야됨 -->
						</div>
						<div id="member_email_error"></div>
					</div>
				</li>
				<div class="label-title">
					<label class="tel optional">닉네임
						<abbr title="required">*</abbr>
					</label>
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class="string tel optional" placeholder="변경하실 닉네임을 입력하세요" type="text" value="${memberDTO.member_nickname}" name="member_nickname" id="member_nickname">
						</div>
						<div id="member_nickname_error"></div>
					</div>
				</li>
				<div class="label-title">
					<label class="file optional" for="user_profile_image">프로필 사진</label>
				</div>
				<div class="block no-margin no-padding">
					<div class="file-container">
						<label for="user_profile_image" class="button button-small button-inline"> 이미지 업로드 </label> 
						<input class=" is-valid file optional" accept=".jpg, .jpeg, .png" type="file" name="member_profile" id="user_profile_image">
						<!-- 이미지 받아서 value에 넣기 -->
					</div>
					<c:if test="${memberDTO.member_profile == 'profile.jpg'}">
			             <p><img id="user_profile_image_img" src="../image/profile.jpg" style="width: 100px; height: 100px;"></p>
			        </c:if>
			        <c:if test="${memberDTO.member_profile != 'profile.jpg'}">
			             <p><img id="user_profile_image_img" src="../storage/${memberDTO.member_email}/${memberDTO.member_profile}" style="width: 100px; height: 100px;"></p>
			        </c:if>
					<div id="member_profile_error"></div>
					<!-- member에서 가져온 이메일 + 이미지 -->
					<div class="block-footer">
						- 얼굴이 포함된 사진을 등록해주세요.<br> 
						- 해당 사진의 최적 사이즈는 800 x 800px 입니다.<br>
						- 등록 가능한 파일 형식은 jpg, png, gif 입니다.<br>
					</div>
				</div>
			</ul>
		</div>
	</form>
</div>
<div class="block block-strong inset">
	<div class="block-footer">
		<div>
			<a id="menteeUser_Save" class="button button-inline color-gray delete-account-button"
				type="external" style="border-color: #ff2d55; color: #ff2d55;"> 설정 저장하기 </a> 
			<a id="menteeUser_Withdrawal" class="button button-inline color-gray delete-account-button"
				type="external"> 회원 탈퇴 </a>
		</div>
	</div>
</div>
</div>
<input type="hidden" id="nickname" value="${memberDTO.member_nickname}"> 
<script>
$(document).ready(
	function() {
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
				reader.onload = function(e) {
					//파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
					$('#user_profile_image_img').attr('src', e.target.result);
					//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
					//(아래 코드에서 읽어들인 dataURL형식)
				}
				reader.readAsDataURL(input.files[0]);
				//File내용을 읽어 dataURL형식의 문자열로 저장
			}
		}//readURL()--

		//file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
		$("#user_profile_image").change(function() {
			//alert(this.value); //선택한 이미지 경로 표시
			readURL(this);
		});

		$('#menteeUserSetting').attr('class', 'list-button color-gray item-link active').css('font-weight', 'bold');
	});
</script>