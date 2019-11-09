<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed mentee_participations new" data-name="mentee_participations-new">
	<div class="page-content">
		<div class="block inset main-block">
			<h1 class="title">${meetingboardDTO.meetingboard_title}</h1>
		</div>
		<form name="participationWriteForm" id="participationWriteForm" action="" accept-charset="UTF-8" method="post">
			<input type="hidden" id="seq" name="seq" value="${seq}">
			<input type="hidden" id="mentor_email" name="mentor_email" value="${mentorDTO.member_email}">
			<div class="list form-list no-hairlines">
				<ul>
					<div class="label-title">
						<label class="string required">이름<abbr title="required">*</abbr></label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<input class="string required" type="text" name="mentee_name" id="mentee_name" value="${memDTO.member_name}">
								<div id="nameDiv"></div>
							</div>
						</div>
					</li>
					
					<div class="label-title">
						<label class="string required">이메일<abbr title="required">*</abbr></label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<input class="string required" type="text" name="mentee_email" id="mentee_email" value="${memDTO.member_email}">
								<div id="emailDiv"></div>
							</div>
						</div>
					</li>

					<div class="label-title">
						<label class="string required">멘토<abbr title="required">*</abbr></label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap input-dropdown-wrap">
								<div class="form-group select required mentee_participation_selected_mentors_user">
									<select class="form-control select required" name="select_mentor" id="select_mentor">
										<option value="">선택해주세요</option>
										<option value="">
											${mentorDTO.member_name}
										</option>
									</select>
								</div>
								<div id="select_mentorDiv"></div>
							</div>
						</div>
					</li>
					
					<div class="label-title">
						<label class="string required">거주지<abbr title="required">*</abbr></label> 
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<input class="string required" placeholder="예) 서울시 종로구" type="text" name="participation_address" id="participation_address">
								<div id="addressDiv"></div>
							</div>
						</div>
					</li>
					
					<div class="label-title">
						<label class="text optional">사전질문</label></div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap">
								<textarea class="text optional"	name="participation_question" id="participation_question"></textarea>
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