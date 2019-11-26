<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="page navbar-fixed mentee_participations new" data-name="mentee_participations-new">
	<div class="page-content">
		<div class="block inset main-block">
			<h1 class="title">${participationDTO.meetingboard_title}</h1>
		</div>
		<div class="list form-list no-hairlines">
			<ul>
				<div class="label-title">
					<label class="string required">이름</label>
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class="string required" type="text" readonly="readonly" value="${participationDTO.mentee_name}">
						</div>
					</div>
				</li>
				<div class="label-title">
					<label class="string required">이메일</label>
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class="string required" type="text" readonly="readonly" value="${participationDTO.mentee_email}">
						</div>
					</div>
				</li>
				<div class="label-title">
					<label class="string required">거주지</label> 
				</div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<input class="string required" type="text" readonly="readonly" value="${participationDTO.participation_address}">
						</div>
					</div>
				</li>
				<div class="label-title">
					<label class="text optional">사전질문</label></div>
				<li class="item-content item-input">
					<div class="item-inner">
						<div class="item-input-wrap">
							<textarea class="text optional">${participationDTO.participation_question}</textarea>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<div class="block">
			<input type="button" value="확인" onclick="goBack()" class="btn button button-big button-fill">
		</div>
	</div>
</div>
<script>
function goBack() {
    window.history.back();
}
</script>