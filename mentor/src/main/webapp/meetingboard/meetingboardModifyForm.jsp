<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- summernote -->	
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="../js/summernote-ko-KR.js"></script>

<!-- datepicker -->
<link href="../css/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="../js/datepicker.min.js"></script>
<script src="../js/datepicker.ko.js"></script>

<div class="page navbar-fixed mentee_programs index">
<div class="page-content">
	<div class="block-title strong-title">모임 작성</div>
	<div class="block inset">
		<form id="meetingboardModifyForm" onSubmit="return false;">
			<input type="hidden" id="pg" name="pg" value="${pg}">
			<input type="hidden" id="seq" name="meetingboard_seq" value="${seq}">
			<div class="list form-list no-hairlines">
				<ul>
					<div class="label-title">
						<label class="string required" for="title">직무 분야</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<div class="item-input-wrap input-dropdown-wrap">
								<select class="select optional" name="job_code" id="job_code">
									<option value="">직무 분야를 선택하세요</option>
									<option value="job_code_0">인사/총무/노무</option>
									<option value="job_code_1">마케팅/MD</option>
									<option value="job_code_2">홍보/csr</option>
									<option value="job_code_3">영업/영업관리</option>
									<option value="job_code_4">회계/재무/금융</option>
									<option value="job_code_5">해외/기술영업</option>
									<option value="job_code_6">유통/무역/구매</option>
									<option value="job_code_7">전략/기획</option>
									<option value="job_code_8">IT개발</option>
									<option value="job_code_9">서비스 기획/UI/UX 등</option>
									<option value="job_code_10">디자인/예술</option>
									<option value="job_code_11">미디어</option>
									<option value="job_code_12">서비스</option>
									<option value="job_code_13">연구/설계</option>
									<option value="job_code_14">전문/특수</option>
									<option value="job_code_15">교육/상담/컨설팅</option>
									<option value="job_code_16">공무원/공공/비영리</option>
									<option value="job_code_17">생산/품질/제조</option>
									<option value="job_code_18">기타 사무</option>										
								</select>
							</div>
							<div id="job_codeDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">제목</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_title" id="title" value="${meetingboardDTO.meetingboard_title}" placeholder="제목을 입력하세요">
							<div id="titleDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">부제목</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_subtitle" id="subtitle" value="${meetingboardDTO.meetingboard_subtitle}" placeholder="부제목을 입력하세요">
							<div id="subtitleDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">내용</label>
					</div>
					<li>
						<textarea id="summernote" name="meetingboard_content"></textarea>
						<div id="contentDiv"></div>
					</li>
					<div class="label-title">
						<br>
						<label class="string required" for="title">일시</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" id="datepicker" name="meetingboard_day" class="datepicker-here" placeholder="날짜" style="width: 170px; display: inline-block;">
							<input type="text" id="startHour" name="meetingboard_starthour" class="only-time" value="${meetingboardDTO.meetingboard_starthour}" placeholder="시작시간" style="width: 170px; display: inline-block;">
							<input type="text" id="endHour" name="meetingboard_endhour" class="only-time" value="${meetingboardDTO.meetingboard_endhour}" placeholder="종료시간" style="width: 170px; display: inline-block;">
							<div id="dayDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">모집인원</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_count" id="count" value="${meetingboardDTO.meetingboard_count}" placeholder="모집인원을 입력하세요">
							<div id="countDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">주최자</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_host" id="host" value="${meetingboardDTO.meetingboard_host}" placeholder="주최자를 입력하세요">
							<div id="hostDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">참가비</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_price" id="price" value="${meetingboardDTO.meetingboard_price}" placeholder="참가비를 입력하세요">
							<div id="priceDiv"></div>
						</div>
					</li>
					<div class="label-title">
						<label class="string required" for="title">장소</label>
					</div>
					<li class="item-content item-input">
						<div class="item-inner">
							<input type="text" name="meetingboard_address" id="address" value="${meetingboardDTO.meetingboard_address}" placeholder="장소를 검색하세요" readonly="readonly">
							<button class="button" style="width: 100px;">
								<a type="external" href="javascript:void(0);" onclick="execDaumPostcode();">장소 검색</a>
							</button>
							<input type="text" id="buildingName" name="meetingboard_buildingname" value="${meetingboardDTO.meetingboard_buildingname}" placeholder="건물명">
							<div id="addressDiv"></div>
						</div>
					</li>
					<li>
						<div id="map" style="width: 400px; height: 400px; margin-top: 10px;"></div>
						<input type="hidden" id="address_y" name="meetingboard_address_y">
						<input type="hidden" id="address_x" name="meetingboard_address_x">								
					</li>
				</ul>
			</div>
			<div style="margin-top: 50px; margin-bottom: 100px;">
				<input type="button" id="meetingboardModifyBtn" value="수정 완료" class="btn button button-big button-fill" style="line-height: 0px;">
			</div>
		</form>
	</div>
</div>
</div>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89c3afe322de0763fb20750b2bf6b62a&libraries=services"></script>
<script src="../js/meetingboard.js"></script>
<script>
	$(function(){
		$("select option[value='${meetingboardDTO.job_code}']").attr("selected", true);
			
		$("#summernote").summernote({
			placeholder: "▶ 프로그램 내용 <br> ▶ 프로그램 진행 <br> ▶ 기타사항 <br> ▶ 참고자료",
       		height: 400,
       		lang: "ko-KR",
       		disableResizeEditor: true,
       		callbacks: {
         		onImageUpload: function(files, editor, welEditable) {
           			for (var i = files.length - 1; i >= 0; i--) {
             			sendFile(files[i], this);
           			}
         		}
       		}
   		});
		
		var markupStr = '${meetingboardDTO.meetingboard_content}';
		$("#summernote").summernote('code', markupStr);
	});

	// summernote callback 메소드(imageupload)
    function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append("file", file);
		$.ajax({
			data: form_data,
			type: "post",
			url: "/mentor/meetingboard/meetingboardImage",
			cache: false,
			contentType: false,
			enctype: "multipart/form-data",
			processData: false,
			success: function(url) {
				$(el).summernote("editor.insertImage", "../storage/"+url);
			},	
			error: function(){
				alert("에러");
			}
		});
    }
	
    var address_x = ${meetingboardDTO.meetingboard_address_x};	// 경도
	var address_y = ${meetingboardDTO.meetingboard_address_y};	// 위도
	
 	// 카카오맵 api 관련
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {					// y,       x
            center: new daum.maps.LatLng(address_y, address_x), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    // 주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    // 마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(address_y, address_x),
        map: map
    });
    
    //--------------------------------------------------------------
 	// air datepicker 관련
    $("#datepicker").datepicker({
    	language : "ko",
    	minDate : new Date()
    });
 	
    var dayString = '${meetingboardDTO.meetingboard_day}';
    var daySplit = dayString.split("/");
    var year = daySplit[0];
    var month = daySplit[1];
    var day = daySplit[2];
    var setDate = month+'/'+day+'/'+year;
    
    var dp = $('#datepicker').datepicker().data('datepicker');
    dp.selectDate(new Date(setDate));
  						//월/일/년
    // timepicker 관련
    var start = new Date(), prevDay, startHours = 9;
    // 09:00 AM
    start.setHours(9);
    start.setMinutes(0);

    $(".only-time").datepicker({
    	dateFormat: " ",
    	timepicker: true,
    	timeFormat: 'hh:ii aa',
    	startDate: start,
    	minHours: startHours,
    	maxHours: 22,
    	classes: "only-timepicker"
    });
</script>