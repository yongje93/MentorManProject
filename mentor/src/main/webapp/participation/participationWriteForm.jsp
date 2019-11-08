<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed mentee_participations new" data-name="mentee_participations-new">
	<div class="page-content">
		<div class="block inset main-block">
			<h1 class="title">${meetingboardDTO.title}</h1>
		</div>
		<form class="" id="new_mentee_participation" action="" accept-charset="UTF-8" method="post">
			<input type="hidden" id="seq" name="seq" value="${meetingboardDTO.meeting_seq}">
			<input type="hidden" id="mentor_email" name="mentor_email" value="${meetingboardDTO.email}">
			<div class="list form-list no-hairlines">
				<ul>
					<div class="label-title">
						<label class="string required">이름 <abbr title="required">*</abbr>
						</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<input class="string required" type="text" name="mentee_name" id="name" value="${memDTO.member_name}">
								<div id="nameDiv"></div>
							</div>
						</div>
					</li>

					<div class="label-title">
						<label class="string required">
							멘토 <abbr title="required">*</abbr>
						</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap input-dropdown-wrap">
								<div class="form-group select required mentee_participation_selected_mentors_user">
									<select class="form-control select required" name="mentee_participation[selected_mentors_attributes][0][user_id]"
										id="mentee_participation_selected_mentors_attributes_0_user_id">
										<option value="">선택해주세요</option>
										<option value="18995">
											오창민 | Bel Cheese Korea
										</option>
									</select>
								</div>
							</div>
						</div>
					</li>
					
					<div class="label-title">
						<label class="string required" for="mentee_participation_address">거주지
							<abbr title="required">*</abbr>
						</label> 
						<small class="form-text text-muted"> 주최하는 자치구 관내 거주자에게 우선선발의 기회를 드립니다.</small>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<input class="string required" placeholder="예) 서울시 종로구" type="text" name="mentee_participation[address]" id="mentee_participation_address">
								<div id="addressDiv"></div>
							</div>
						</div>
					</li>
					
					<div class="label-title">
						<label class="text optional" for="mentee_participation_selected_mentors_attributes_0_question">사전질문</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<textarea class="text optional"
									name="mentee_participation[selected_mentors_attributes][0][question]"
									id="mentee_participation_selected_mentors_attributes_0_question"></textarea>
							</div>
						</div>
					</li>
				</ul>
			</div>

			<div class="block">
				<input type="submit" name="commit" value="신청하기" class="btn button button-big button-fill" data-disable-with="신청하기">
			</div>
		</form>
	</div>
</div>