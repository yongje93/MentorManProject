<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-100 tablet-80" style="margin: auto;">
	<div class="block no-hairlines hero-title">
		<h1 class="title">회원탈퇴</h1>
	</div>
        <div class="block inset mentor-request-block">
		  <form class="simple_form new_delete_record" id="new_delete_record" novalidate="novalidate" accept-charset="UTF-8" method="post">
		    <div class="list form-list no-hairlines">
		      <ul>
		       <li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input autocomplete="current-password" class="password optional" placeholder="현재 비밀번호" type="password" name="currentPassword" id="currentPassword">
						</div>
						<div id="currentPassword_error"></div>
					</li>
				</ul>
			</div>
			<input type="button" name="commit" value="회원탈퇴" class="btn button button-big button-fill" id="delete_btn">
		</form>
	</div>
</div>
