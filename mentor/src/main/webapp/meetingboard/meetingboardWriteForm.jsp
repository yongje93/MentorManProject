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
			<form method="post" action="" onSubmit="return false;">
				<div class="list form-list no-hairlines">
					<ul>
						<div class="label-title">
							<label class="string required" for="title">멘토링 분야</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<div class="item-input-wrap input-dropdown-wrap">
									<select class="select optional" name="mentoring_code" id="mentoring_code">
										<option value="">멘토링 분야를 선택하세요</option>
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
								<div id="mentoring_codeDiv"></div>
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">제목</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="title" id="title" placeholder="제목을 입력하세요">
							</div>
							<div id="titleDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">부제목</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="subtitle" id="subtitle" placeholder="부제목을 입력하세요">
							</div>
							<div id="subtitleDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">내용</label>
						</div>
						<li>
							<textarea id="summernote" name="content"></textarea>
							<div id="contentDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">일시</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" id="datepicker" name="day" class="datepicker-here" placeholder="날짜" style="width: 170px; display: inline-block;">
								<input type="text" id="startHour" name="starthour" class="only-time" placeholder="시작시간" style="width: 170px; display: inline-block;">
								<input type="text" id="endHour" name="endhour" class="only-time" placeholder="종료시간" style="width: 170px; display: inline-block;">
							</div>
							<div id="dayDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">모집인원</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="count" id="count" placeholder="모집인원을 입력하세요">
							</div>
							<div id="countDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">주최자</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="host" id="host" placeholder="주최자를 입력하세요">
							</div>
							<div id="hostDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">참가비</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="price" id="price" placeholder="참가비를 입력하세요">
							</div>
							<div id="priceDiv"></div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">장소</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="address" id="address" placeholder="장소를 검색하세요" readonly="readonly">
							</div>
							<div id="addressDiv"></div>
						</li>
						<li>
							<button class="button color-gray" style="width: 100px;">
								<a class="color-gray" type="external" href="javascript:void(0);" onclick="execDaumPostcode();">장소 검색</a>
							</button>
							<div id="map" style="width: 400px; height: 400px; margin-top: 10px; display: none"></div>
							<input type="hidden" id="buildingname" name="buildingname">
							<input type="hidden" id="address_y" name="address_y">
							<input type="hidden" id="address_x" name="address_x">								
						</li>
					</ul>
				</div>
				<div style="margin-top: 50px; margin-bottom: 100px;">
					<input type="submit" id="meetingboardWriteBtn" value="작성 완료" class="btn button button-big button-fill" style="line-height: 0px;">
				</div>
			</form>
		</div>
	</div>
</div>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89c3afe322de0763fb20750b2bf6b62a&libraries=services"></script>
<script>
	// summernote 관련
	$(function(){
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
		
		var markupStr = '<strong>▶ 프로그램 내용</strong><br><br><strong>▶ 프로그램 진행</strong><br><br><strong>▶ 기타사항</strong><br><br><strong>▶ 참고자료</strong><br><br>';
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
	
    // 카카오맵 api 관련
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {					// y,       x
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    // 지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    // 주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    // 마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });
	// 다음 도로명 주소
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수
				var buildingName = data.buildingName; // 건물명
				
				$("#buildingName").val(buildingName);
				
                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용
						
                        $("#address_y").val(result.y);	// 위도
                        $("#address_x").val(result.x);	// 경도
                       
                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
	
	// air datepicker 관련
	$("#datepicker").datepicker({
		language : "ko",
		minDate : new Date()
	});
	
	var start = new Date(),
    prevDay,
    startHours = 9;

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
	
	// 모임 작성 유효성 검사
	function meetingboardWriteFormSubmit() {
		$("#mentoring_codeDiv").empty();
		$("#titleDiv").empty();
		$("#subtitleDiv").empty();
		$("#contentDiv").empty();
		$("#dayDiv").empty();
		$("#countDiv").empty();
		$("#hostDiv").empty();
		$("#priceDiv").empty();
		$("#addressDiv").empty();
		
		

	}
</script>