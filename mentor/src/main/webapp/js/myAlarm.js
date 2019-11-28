
$(function(){
	//$('.alarmGarbage').on('click' , function(){
	$(document).on('click' , '.alarmGarbage' , function(){
		var alarm_seq = $(this).attr('id');
		
		$.ajax({
			type : 'get',
			url : '/mentor/member/deleteAlarm',
			data : 'alarm_seq='+alarm_seq,
			dataType : 'json',
			success : function(data) {
				$('#inputAlarmList').empty();
				
				$.each(data.list, function(key, value) {
					var str = `<div class="col-100 tablet-100 desktop-100">
							<div class="card mentor-post-card mentor_post_6624">
								<div class="card-header">
									<div>
										<div>
											<strong>${value.myAlarm_title }</strong>
										</div>
									</div>
									<div class="created-at">
										<small>${value.myAlarm_logtime}</small>
									</div>
								</div>
		
								<div class="card-content-padding">
										<span class="mentor-post-detail">${value.myAlarm_content} </span>
										<i class="far fa-trash-alt alarmGarbage" id="${value.myAlarm_seq}" style="float:right;"></i>
								</div>
							</div>
						</div>`;
					
					$('#inputAlarmList').append(str);
				});
			},
			error : function(err) {
				console.log(err);
				alert('err');
			}
		});
		
		
		
	});
});