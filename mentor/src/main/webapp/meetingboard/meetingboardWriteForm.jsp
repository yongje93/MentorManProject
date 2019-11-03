<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page navbar-fixed mentee_programs index">
	<div class="page-content">
		<div class="block-title strong-title">모임 작성</div>
		<div class="block inset" style="border: 1px solid blue;">
			<form id="meetingboardWriteForm" method="post" action="">
				<div class="list form-list no-hairlines">
					<ul>
						<div class="label-title">
							<label class="string required" for="title">제목</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="title" id="title" placeholder="제목을 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">부제목</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="subtitle" id="subtitle" placeholder="부제목을 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">내용</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="content" id="content" placeholder="내용을 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">일시</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="date" id="date" placeholder="일시를 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">모집인원</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="count" id="count" placeholder="모집인원을 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">주최</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="host" id="host" placeholder="주최자를 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">참가비</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="title" id="title" placeholder="참가비를 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">기타사항</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="etc" id="etc" placeholder="기타사항을 입력하세요">
							</div>
						</li>
						<div class="label-title">
							<label class="string required" for="title">장소</label>
						</div>
						<li class="item-content item-input">
							<div class="item-inner">
								<input type="text" name="address" id="address" placeholder="장소를 검색하세요" readonly="readonly">
							</div>
						</li>
						<li>
							<button class="button color-gray" style="width: 100px;">
								<a class="color-gray" type="external" href="javascript:void(0);" onclick="execDaumPostcode()">장소 검색</a>
							</button>
							<div id="map" style="width: 400px; height: 400px; margin-top: 10px; display: none"></div>
							<input type="hidden" id="buildingName">
							<input type="hidden" id="address_y">
							<input type="hidden" id="address_x">								
						</li>
					</ul>
				</div>
				<div style="margin-top: 50px; margin-bottom: 100px;">
					<input type="button" id="meetingboardWriteBtn" value="작성 완료" class="btn button button-big button-fill" style="line-height: 0px;">
				</div>
			</form>
		</div>
	</div>
</div>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=89c3afe322de0763fb20750b2bf6b62a&libraries=services"></script>
<script>
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {					// y,       x
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });


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
</script>