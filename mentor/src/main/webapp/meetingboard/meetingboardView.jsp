<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page navbar-fixed mentee_programs show">
	<div class="page-content" style="width: 1000px;">
		<div class="block main-block">
			<div>
				<img src="../image/job_code/${meetingboardDTO.job_code}.jpg" style="width: 100%; height: 500px;">
			</div>
			<h1 class="title" style="color: black;">${meetingboardDTO.title}</h1>
			<div class="description" style="font-size: 19px;">${meetingboardDTO.subtitle}</div>
		</div>
		<div class="block-title">기본정보</div>
		<div class="list basic-block">
			<ul>
				<li>
					<div class="item-inner">
						<div class="item-title">&nbsp;일시</div>
						<div class="item-schedule">
							<div class="item-after">
								${meetingboardDTO.day} ${meetingboardDTO.starthour} ~ ${meetingboardDTO.endhour}
							</div>
						</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title">&nbsp;장소</div>
						<div class="item-after">${meetingboardDTO.buildingname}</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title">&nbsp;모집인원</div>
						<div class="item-after">${meetingboardDTO.count}명</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title">&nbsp;주최</div>
						<div class="item-after">${meetingboardDTO.host}</div>
					</div>
				</li>
				<li>
					<div class="item-inner">
						<div class="item-title">&nbsp;참가비</div>
						<div class="item-after">${meetingboardDTO.price}원</div>
					</div>
				</li>
			</ul>
		</div>

		<div class="block-title">참여멘토</div>
		<div class="mentor-block block block-strong">
			<div class="block mentor-info-block">
				<div class="mentor-image img-circle">
					<a target="_blank" type="external" href=""> <img width="150" height="150" src="">
					</a>
				</div>
				<div class="block mentor-info">
					<div class="name">
						<span class="mentor-name"> <a target="_blank" type="external" href="">멘토이름</a> <small>멘토</small>
						</span>
					</div>
					<div class="job">멘토잡</div>
					<div class="detail-block">
						<div class="mentoring-info">
							<div class="mentoring-type-block">
								<div class="chip chip-outline no-border-radius mentor-index">
									<div class="chip-label">
										<span>답변율 <strong class="highlight">!!!!!수정</strong></span>
									</div>
								</div>

								<div class="chip chip-outline no-border-radius mentor-index">
									<div class="chip-label">
										<span>답변수 <strong class="highlight">!!!!!수정</strong></span>
									</div>
								</div>

								<div class="chip chip-outline no-border-radius mentor-index">
									<div class="chip-label">
										<span>뱃지 <strong class="highlight">!!!!!수정</strong></span>
									</div>
								</div>

								<div class="chip chip-outline no-border-radius mentor-index">
									<div class="chip-label">
										<span>팔로워 <strong class="highlight">!!!!!수정</strong></span>
									</div>
								</div>
							</div>
						</div>

						<div class="mentoring-info">
							<div class="title text-decoration-underline">대표 멘토링 분야</div>
							<div class="mentoring-type-block">!!!!!수정</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="block mentor-detail-block">
			<h4 class="title">멘토 소개</h4>
			<div>멘토소개가져오기</div>
			<h4 class="title">주요 경력</h4>
			<div>경력사항가져오기</div>
		</div>
		<div class="block-title">프로그램 내용</div>
		<div class="block block-strong trix-content froala-content">
			${meetingboardDTO.content}
		</div>
		<div class="block-title">안내사항 !!!이거 디비에서 갖고오는걸로 바꾸기</div>
		<div class="block block-strong">
			<ul>
				<li><i class="fas fa-check"></i>확정된 분들에게는 참여 확정 안내 메일이 발송됩니다.</li>
				<li><i class="fas fa-check"></i>사전 취소는 2일 전까지 가능합니다.</li>
				<li><i class="fas fa-check"></i>무단 No-Show 시에는 참여 신청이 제한됩니다.</li>
				<li><i class="fas fa-check"></i>주차지원은 불가능하니 대중교통을 이용해 주세요.</li>
			</ul>
		</div>
		<div class="block-title location">장소</div>
		<div class="block block-strong">
	        <div>
	          	${meetingboardDTO.buildingname}
	        </div>
	
	        <div class="block-footer">
	          <div>
	            ${meetingboardDTO.address}, ${meetingboardDTO.buildingname}
	          </div>
	          <div id="map" style="width: 970px; height: 300px; margin-top: 10px;"></div>
	        </div>
	      </div>
		<div class="block button-block">
	      <a class="button button-big button-fill" type="external" href="/mentee_programs/189/mentee_participations/new">신청하기</a>
	    </div>
	</div>
</div>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89c3afe322de0763fb20750b2bf6b62a&libraries=services"></script>
<script>
	//이미지 지도에 표시할 마커입니다
	//이미지 지도에 표시할 마커를 아래와 같이 배열로 넣어주면 여러개의 마커를 표시할 수 있습니다 
	var markers = 
	 {
	     position: new kakao.maps.LatLng(${meetingboardDTO.address_y}, ${meetingboardDTO.address_x}), 
	     text: '${meetingboardDTO.buildingname}' // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다     
	 };
	
	
	var staticMapContainer = document.getElementById("map"), // 이미지 지도를 표시할 div  
	 staticMapOption = { 
	     center: new kakao.maps.LatLng(${meetingboardDTO.address_y}, ${meetingboardDTO.address_x}), // 이미지 지도의 중심좌표
	     level: 3, // 이미지 지도의 확대 레벨
	     marker: markers // 이미지 지도에 표시할 마커 
	 };    
	
	//이미지 지도를 생성합니다
	var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
</script>