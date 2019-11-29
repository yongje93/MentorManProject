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

// 모임 작성 유효성 검사
$("#meetingboardWriteBtn").click(function(){
	$("#job_codeDiv").empty();
	$("#titleDiv").empty();
	$("#subtitleDiv").empty();
	$("#contentDiv").empty();
	$("#dayDiv").empty();
	$("#countDiv").empty();
	$("#hostDiv").empty();
	$("#priceDiv").empty();
	$("#addressDiv").empty();
	
	var content = $("#summernote").summernote("code");
	if($("#job_code").val() == "") {
		$("#job_codeDiv").text("직무 분야를 선택하세요").css("color", "tomato").css("font-size","8pt");
		$("#job_code").focus();
	} else if($("#title").val() == "") {
		$("#titleDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#title").focus();
	} else if($("#subtitle").val() == "") {
		$("#subtitleDiv").text("부제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subtitle").focus();
	} else if(content == '<strong>▶ 프로그램 내용</strong><br><br><strong>▶ 프로그램 진행</strong><br><br><strong>▶ 기타사항</strong><br><br><strong>▶ 참고자료</strong><br><br>') {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#summernote").summernote("focus");
	} else if($("#datepicker").val() == "") {
		$("#dayDiv").text("일시를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#datepicker").focus();
	} else if($("#starthour").val() == "") {
		$("#dayDiv").text("시작시간을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#starthour").focus();
	} else if($("#endhour").val() == "") {
		$("#dayDiv").text("종료시간을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#endhour").focus();
	} else if($("#count").val() == "") {
		$("#countDiv").text("인원 수를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#count").focus();
	} else if($("#host").val() == "") {
		$("#hostDiv").text("주최를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#host").focus();
	} else if($("#price").val() == "") {
		$("#priceDiv").text("참가비를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#price").focus();
	} else if($("#address").val() == "") {
		$("#addressDiv").text("장소를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#address").focus();
	} else if($("#buildingName").val() == "") {
		$("#addressDiv").text("건물명을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#buildingName").focus();
	} else {
		$.ajax({
			type: "post",
			url: "/mentor/meetingboard/meetingboardWrite",
			data: $("#meetingboardWriteForm").serialize(),
			success: function(){
				location.href="/mentor/meetingboard/meetingboardList";
			},
			error: function(e){
				console.log(e);
			}
		});
	}
});

// 모임글 수정폼 열기
$("#meetingboardModifyFormBtn").click(function(){
	document.meetingboardViewForm.method = "post";
	document.meetingboardViewForm.action = "/mentor/meetingboard/meetingboardModifyForm";
	document.meetingboardViewForm.submit();
});

// 모임글 수정
$("#meetingboardModifyBtn").click(function(){
	$("#job_codeDiv").empty();
	$("#titleDiv").empty();
	$("#subtitleDiv").empty();
	$("#contentDiv").empty();
	$("#dayDiv").empty();
	$("#countDiv").empty();
	$("#hostDiv").empty();
	$("#priceDiv").empty();
	$("#addressDiv").empty();
	
	var content = $("#summernote").summernote("code");
	if($("#job_code").val() == "") {
		$("#job_codeDiv").text("직무 분야를 선택하세요").css("color", "tomato").css("font-size","8pt");
		$("#job_code").focus();
	} else if($("#title").val() == "") {
		$("#titleDiv").text("제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#title").focus();
	} else if($("#subtitle").val() == "") {
		$("#subtitleDiv").text("부제목을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#subtitle").focus();
	} else if(content == '<strong>▶ 프로그램 내용</strong><br><br><strong>▶ 프로그램 진행</strong><br><br><strong>▶ 기타사항</strong><br><br><strong>▶ 참고자료</strong><br><br>') {
		$("#contentDiv").text("내용을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#summernote").summernote("focus");
	} else if($("#datepicker").val() == "") {
		$("#dayDiv").text("일시를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#datepicker").focus();
	} else if($("#starthour").val() == "") {
		$("#dayDiv").text("시작시간을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#starthour").focus();
	} else if($("#endhour").val() == "") {
		$("#dayDiv").text("종료시간을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#endhour").focus();
	} else if($("#count").val() == "") {
		$("#countDiv").text("인원 수를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#count").focus();
	} else if($("#host").val() == "") {
		$("#hostDiv").text("주최를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#host").focus();
	} else if($("#price").val() == "") {
		$("#priceDiv").text("참가비를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#price").focus();
	} else if($("#address").val() == "") {
		$("#addressDiv").text("장소를 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#address").focus();
	} else if($("#buildingName").val() == "") {
		$("#addressDiv").text("건물명을 입력하세요").css("color", "tomato").css("font-size","8pt");
		$("#buildingName").focus();
	} else {
		$.ajax({
			type: "post",
			url: "/mentor/meetingboard/meetingboardModify",
			data: $("#meetingboardModifyForm").serialize(),
			success: function(){
				location.href="/mentor/meetingboard/meetingboardList?pg="+$("#pg").val();
			},
			error : function(e){
				console.log(e);
			}
		});
	}
});

// 모임글 삭제
$("#meetingboardDeleteBtn").click(function(){
	var toastWithCallback = app.toast.create({
		text: '모임을 삭제하시겠습니까?',
		position: 'center',
		closeButton: true,
		on: {
			close: function() {
				$.ajax({
					type: 'post',
					url: '/mentor/meetingboard/meetingboardDelete',
					data: {"seq" : $("#seq").val()},
					success: function(){
						location.href="/mentor/meetingboard/meetingboardList";
					},
					error: function(error){
						console.log(error);
					}
				});
			}
		}
	});	
	toastWithCallback.open();
	
});
