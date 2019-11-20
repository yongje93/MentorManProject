<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed settings accounts show"
	data-name="accounts-show">
	<div class="page-content" style="overflow: inherit;">
		<div class="row">
			<div class="col-100 tablet-100">
				<div class="block block-strong no-hairlines question-block">
					<div class="block hero-title">
						<h1 class="title">수강 후기 작성</h1>
						<p class="description">모임 수강 후기를 작성해 보세요. 작성하신 후기는 멘토에게 전달	됩니다.</p>
					</div>
					<div class="block">
						<form class="simple_form new_question" id="reviewModifyForm" action="/mentor/mentee/meetingReviewModify" accept-charset="UTF-8" method="post">
							<input type="hidden" name="mentors" value="${mentor_seq}">
							<input type="hidden" name="review_seq" value="${reviewDTO.review_seq}">
							<div class="list form-list no-hairlines"
								style="padding-left: 120px; padding-right: 120px;">
								<ul>
									<li class="item-content item-input">
										<div class="item-inner">
											<div class="item-input-wrap">
												<textarea class="text required" name="review_content" id="review_content" placeholder="내용을 입력해주세요">${reviewDTO.review_content }</textarea>
											</div>
										</div>
									</li>
								</ul>
								<input type="button" id="reviewModifyBtn" value="수정완료" class="btn button button-big button-fill" data-disable-with="저장중" style="margin-top: 35px;">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="../js/mentee.js"></script>