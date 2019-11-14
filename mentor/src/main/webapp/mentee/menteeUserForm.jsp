<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="view view-main">
	<div class="page navbar-fixed settings accounts show" data-name="accounts-show">
		<div class="page-content" style="overflow: inherit;">
			<div class="row">
				<div class="col-100 tablet-20">
					<div class="list no-hairlines no-hairlines-between">
						<ul>
							<li>
								<a class="list-button color-gray item-link" id="menteeUserSetting" 
									type="external" href="/mentor/mentee/menteeUserForm">내 정보</a>
							</li>
							<li>
								<a class="list-button color-gray item-link" id="menteeProfile" 
									type="external" href="/mentor/mentee/menteeStudentProfile">멘티 정보</a>
							</li>
							<li>
								<a class="list-button color-gray item-link" id="menteeOrderHistory" 
									type="external"	href="/mentor/mentee/menteeOrderHistory">결제 내역</a>
							</li>
							<li>
								<a class="list-button color-gray item-link" id="menteePassword" 
									type="external"	href="/mentor/mentee/menteePassword">비밀번호 설정</a>
							</li>
						</ul>
					</div>
				</div>

				<div class="col-100 tablet-80">
					<jsp:include page="${display2}" />
				</div>
			</div>
		</div>
	</div>
</div>