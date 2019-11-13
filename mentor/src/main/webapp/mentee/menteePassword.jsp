<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <div class="block block-strong no-hairlines hero-title">
          <h1 class="title">
            비밀번호 변경
          </h1>
        </div>

        <div class="block inset mentor-request-block">
  <form class="simple_form edit_user" id="menteePassword" novalidate="novalidate" action="/settings/password" accept-charset="UTF-8" method="post">
    <div class="list form-list no-hairlines">
      <ul>
        <li class="item-content item-input">
          <div class="item-inner">
            <div class="item-input-wrap">
              <input autocomplete="current-password" class="password optional" placeholder="현재 비밀번호" type="password" name="currentPassword" id="currentPassword">
            </div>
            <div id="currentPassword_error"></div>
          </div>
        </li>

        <li class="item-content item-input">
          <div class="item-inner">
            <div class="item-input-wrap">
              <input autocomplete="new-password" class="password optional" placeholder="새 비밀번호" type="password" name="member_pwd" id="member_pwd">
            </div>
            <div id="member_pwd_error"></div>
          </div>
        </li>

        <li class="item-content item-input">
          <div class="item-inner">
            <div class="item-input-wrap">
              <input autocomplete="new-password" class="password optional" placeholder="새 비밀번호 확인" type="password" name="member_pwd_check" id="member_pwd_check">
            </div>
            <div id="member_pwd_check_error"></div>
          </div>
        </li>
      </ul>
    </div>

    <input type="button" name="commit" id="menteePassword_btn" value="비밀번호 변경" class="btn button button-big button-fill" style="height: 100%;">
	
</form>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/mentee.js"></script>
<script>
$(function(){
	$('#menteePassword').attr('class', 'list-button color-gray item-link active');
});
</script>