<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-100 tablet-80" style="margin: auto;">
        <div class="block block-strong no-hairlines hero-title">
          <h1 class="title">
            회원탈퇴
          </h1>
        </div>

        <div class="block inset">
          <div class="block-footer">
            그 동안 잇다와 함께해 주셔서 감사합니다. 탈퇴 사유를 남겨 주시면 향후 서비스 개선에 적극적으로 반영하겠습니다.
          </div>
        </div>

        <div class="block inset mentor-request-block">
		  <form class="simple_form new_delete_record" id="new_delete_record" novalidate="novalidate" action="/settings/delete_account" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="✓"><input type="hidden" name="authenticity_token" value="TH/A5LrYrUsVnUIpy09F/vsMIsnx9ITaOdkZzLHnqbpRql/Ey7KJHQFvKAUEOBPFv8HLuEWim/vv2tu23/uqqg==">
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
		      </ul>
		    </div>
		    <input type="button" name="commit" value="회원탈퇴" class="btn button button-big button-fill" id="delete_btn">
			</form>
		</div>
</div>
<script type="text/javascript" src="../js/mentee.js"></script>
<script>
</script>
