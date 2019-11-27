//멘토 지원하기
$('#mentorapplyForm_btn').on('click',function(){
	$('#mentor_company_error').empty();
	$('#mentor_department_error').empty();
	$('#mentor_position_error').empty();

	if($('#mentor_company').val()==''){
		$('#mentor_company_error').text('회사명에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_company_error').css('font-size','8pt');
		$('#mentor_company').focus();
	}else if($('#mentor_department').val()==''){
		$('#mentor_department_error').text('부서에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_department_error').css('font-size','8pt');
		$('#mentor_department').focus();
	}else if($('#mentor_position').val()==''){
		$('#mentor_position_error').text('직급에 내용을 입력해 주세요').css('color', 'red');
		$('#mentor_position_error').css('font-size','8pt');
		$('#mentor_position').focus();
	}else {
		if($('#email').val()==''){
			$('#new_mentor_request').submit();
		}else {
			alert('이미 지원한 계정입니다.');
			location.href='/mentor/main/index';
		}
	}
});





//지원하기
$('#mentorapply_btn').on('click',function(){
	$('#mentor_name_error').empty();
	$('#mentor_company_error').empty();
	$('#mentor_department_error').empty();
	$('#mentor_position_error').empty();
	$('#mentor_job_code_error').empty();
	$('#mentor_career_error').empty();
	$('#mentoring_code_error').empty();
	$('#mentor_represent_error').empty();
	$('#mentor_info_error').empty();
	$('#mentor_email_error').empty();
	$('#mentor_businesscard_error').empty();


	if($('#mentor_name').val()==''){
		$('#mentor_name_error').text('이름을 입력해주세요').css('color', 'red');
		$('#mentor_name_error').css('font-size','8pt');
		$('#mentor_name').focus();
	}else if($('#mentor_company').val()==''){
		$('#mentor_company_error').text('회사명을 입력해 주세요').css('color', 'red');
		$('#mentor_company_error').css('font-size','8pt');
		$('#mentor_company').focus();
	}else if($('#mentor_department').val()==''){
		$('#mentor_department_error').text('부서를 입력해 주세요').css('color', 'red');
		$('#mentor_department_error').css('font-size','8pt');
		$('#mentor_department').focus();
	}else if($('#mentor_position').val()==''){
		$('#mentor_position_error').text('직급를 입력해 주세요').css('color', 'red');
		$('#mentor_position_error').css('font-size','8pt');
		$('#mentor_position').focus();
	}else if($('#job_code').val()=='0'){
		$('#mentor_job_code_error').text('직무 유형을 선택해주세요').css('color', 'red');
		$('#mentor_job_code_error').css('font-size','8pt');
		$('#job_code').focus();
	}else if($('#mentor_career').val()==''){
		$('#mentor_career_error').text('주요 경력을 입력해주세요').css('color', 'red');
		$('#mentor_career_error').css('font-size','8pt');
		$('#mentor_career').focus();
	}else if($('.mentoring_code').is(':checked')==false){
		$('#mentoring_code_error').text('멘토링 가능 분야를 선택해주세요').css('color', 'red');
		$('#mentoring_code_error').css('font-size','8pt');
		$('#mentor_represent').focus();
	}else if($('#mentor_represent').val()==''){
		$('#mentor_represent_error').text('대표 멘토링 분야를 입력해주세요').css('color', 'red');
		$('#mentor_represent_error').css('font-size','8pt');
		$('#mentor_represent').focus();
	}else if($('#mentor_info').val()==''){
		$('#mentor_info_error').text('멘토 소개를 입력해주세요').css('color', 'red');
		$('#mentor_info_error').css('font-size','8pt');
		$('#mentor_info').focus();
	}else if($('#mentor_email').val()==''){
		$('#mentor_email_error').text('이메일을 입력해주세요').css('color', 'red');
		$('#mentor_email_error').css('font-size','8pt');
		$('#mentor_email').focus();
	}else if($('#mentor_request_name_card').val()==''){
		$('#mentor_businesscard_error').text('명함 이미지를 등록해주세요').css('color', 'red');
		$('#mentor_businesscard_error').css('font-size','8pt');
		$('#mentor_request_name_card').focus();
	}else {
		$('#mentorapplyWriteForm').submit();
	}
});

//지원하기에서 뒤로가기
$('#mentorapply_backBtn').on('click',function(){
	location.href="/mentor/mentor/mentorapplyForm";
});

//멘토에게 질문하기 임시저장 버튼
$('#save_btn').on('click', function(){
	$('#question_title_error').empty();
	$('#question_content_error').empty();

	if($('#question_title').val()==''){
		$('#question_title_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_title_error').css('font-size','8pt');
		$('#question_title').focus();
	}else if($('#question_content').val()==''){
		$('#question_content_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_content_error').css('font-size','8pt');
		$('#question_content').focus();
	}else{
		$('#question_form').submit();
	}

});


function mentor_question_seq(seq, pg){
	$.ajax({
		type: 'post',
		url: '/mentor/mentor/question_flag',
		data: {'seq': seq,'pg': pg},
		dataType: 'text',
		success: function(data){
			location.href = data;
		},
		error: function(){
			alert('에러');
		}
	});
}


$('#modify_btn').on('click', function(){
	$('#question_title_error').empty();
	$('#question_content_error').empty();
	var content = $('#question_content').val();
	if($('#question_title').val()==''){
		$('#question_title_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_title_error').css('font-size','8pt');
		$('#question_title').focus();
	}else if($('#question_content').val()==''){
		$('#question_content_error').text('내용을 입력해주세요').css('color', 'red');
		$('#question_content_error').css('font-size','8pt');
		$('#question_content').focus();
	}else if(content.length > 2000){
		var toastTop = app.toast.create({
	           text: '2000자 이내의 내용을 입력해주세요',
	           position: 'top',
	           closeButton: true,
	    });
	    toastTop.open();
	}else{
		$.ajax({
			type: 'post',
			url: '/mentor/mentor/questionModify',
			data: $('#question_form').serialize(),
			dataType: 'text',
			success: function(data){
				if(data=='success'){
					location.href='/mentor/member/myQandA?pg=1';
				}
			},
			error: function(){
				alert('에러');
			}
		});
	}
});


// 팔로우 기능- 재우
$(function(){
	var seq = $('#mentor_seq').val();

	//내가 팔로우한 멘토인지 확인
	if($('#followVal').val() === '1'){
		$('#followA').addClass('button-fill');
	}else{
		$('#followA').removeClass('button-fill');
	}


	$('.mentor_'+seq).on('click' , function(){
		var followBtn = $(this);

		var sendData = {
				'followed_email' : $('#followed_email').val(),
				'follow' : $('#followVal').val()
			};

		$.ajax({
			url : '/mentor/mentor/mentorFollow',
			type : 'POST',
			data : sendData,
			success : function(data) {

				if (data == 1) {
					followBtn.addClass('button-fill');
					var toastIcon = app.toast.create({
						  text: '관심멘토에 등록 되었습니다',
						  position: 'center',
						  closeTimeout: 2000,
						});
					toastIcon.open();

					//socket에 보내자
					if(socket) {
						let socketMsg = "follow," + $('#memNicname').val() +","+$('#member_nickname').val() +","+$('#mentor_seq').val()
						console.log("msgmsg :: " + socketMsg );
						socket.send(socketMsg);
					}

				}else{
					followBtn.removeClass('button-fill');
					var toastIcon = app.toast.create({
						  text: '관심멘토에서 삭제 되었습니다',
						  position: 'center',
						  closeTimeout: 2000,
						});
					toastIcon.open();
				}
				$('#followVal').val(data);
			},
			error : function(err){
				console.log("err");
			}

		});
	});
});
//직무유형


//직무유형 버튼 전용 전역변수
var jobs = new Array();

// 에세이 직무유형 버튼
$(".row > a").on("click",function(event, data){// a태그 클릭시 작동
	// 클릭된 태그의 본래의 기능을 막음 즉, a태그 본래 기능을 막음
	event.preventDefault();
	if($('#memNick').val() == ""){
		location.href="/mentor/member/loginForm";
	} else {
		$('#gap').empty();
		$('.paging').empty();
		$('.mentor_div').empty();
		var page;
		
		var flag = $('#mentorFlag').val();
		
		if(flag == 0){
			$('.mentor_div').text('멘토');
		} else if (flag == 1) {
			$('.mentor_div').text('명예 멘토');
		}
		
		if(data != null){
			 page = data.pg;				
		}
		// 직무유형 버튼 on off 색상 변경
		if($(this).hasClass("button color-gray active")){
        	$(this).removeClass('active');
        } else {
        	$(this).addClass('active');
        }
		
		
        
        var txt = $(this).attr("href");// href에 입력된 값을 가져옴 즉 클릭된 a의 job_code를 가져옴
        var dateIndex = jobs.indexOf(txt);
        
        if (dateIndex==-1){ 
            jobs.push(txt);
        }else{ 
            jobs.splice(dateIndex, 1);
        }
		
        essayjobType(page, flag);
	}
});

// 직무유형 버튼 값 처리
function essayjobType(pg , flag){
	$('#mentor_findList').empty();
	$('.paging').empty();
	
	
	var page = parseInt(pg);
	
	if(typeof pg == "undefined"){
		page = 1;
	}
	var Flag = parseInt(flag);
	
	$.ajax({
    	type : 'post',
    	url : '/mentor/mentor/mentorjobFind',
    	data : JSON.stringify({job_code : jobs,
    						   pg : page,
    						   flag : Flag}),
    	dataType : 'json',
    	contentType : "application/json; charset=UTF-8",
    	success : function(data){
    		//alert(JSON.stringify(data));
    		let flag = $(document.createDocumentFragment());

    		$.each(data.list, function(index, items) {
    			if(items.member_profile != 'profile.jpg'){
    				var profileFlag = '<img width="50" height="50" src="../storage/' + items.mentor_email + '/' + items.member_profile + '">'
    			} else {
    				var profileFlag = "<img width='50' height='50' src='../image/profile.jpg'>"
    			}
    			
    			if(data.memberDTO != null){
    				var questionFlag = '<a class="question button button-small button-fill" id="mentorQuestions" type="external" onclick="mentor_question_seq(' + items.mentor_seq +',' + data.pg + ')">질문하기</a>'
    			} else {
    				var questionFlag = '<a class="button button-small button-fill" type="external" href="/mentor/member/loginForm">질문하기</a>'
    			}
    			
    			
//    			if(items.list != null){
    				let mentorFindForm = `
    					<div class="col-100 tablet-50 desktop-25">
    						  <div class="card mentor-card">
    						<a id="mentorProfileView" type="external" href="/mentor/mentor/mentorInfoView?pg=${data.pg}&mentors=${items.member_seq}">
    							<div style="background-image:url()" class="cover-image"></div>
    							
    						    <div class="mentor-image img-circle">
    						    	${profileFlag}
    						    </div>
    						
    						    <div class="mentor-info">
    						      <div class="name">
    						        <span class="mentor-name">${items.member_name}</span>
    						        <span class="position">멘토</span>
    						      </div>
    						      
    						      <div class="job">
    						        <div>${items.mentor_company}</div>
    						        <div>${items.mentor_department }</div>
    						      </div>
    						    </div>
    						  </a>
    						<div class="primary-mentoring-info">
    						  	<div class="title">${items.job_type}</div>
    						   	<div class="info">${items.mentor_represent}</div>
    						</div>
    						    <div class="ask-button">
    						    	${questionFlag}
    							</div>
    						</div>
    					</div>
    		    			`;
//    			}
    			

    			$('#mentor_findList').append($(mentorFindForm));
    			
    		});
    		
    		
    	
    		
    		$('#mentor_findList').append($('<div/>', {
				class : 'col-100 desktop-25'
			})).append($('<div/>', {
				class : 'col-100 desktop-25'
			})).append($('<div/>', {
				class : 'col-100 desktop-25'
			}));
    		
    		$('#mentorFlag').val(data.flag);
    		
    		//총 블럭수 ex 32블럭
		    var totalP=(Number(data.jobCodeTotal)+Number(data.pageSize)-1)/data.pageSize;
		    //페이지 블럭 [1][2][3][다음 
		    var pageBlock = 3;
	   	    //현재 페이지
		    var currentPage =data.boardpaging;
		    var startPage = parseInt((currentPage-1)/pageBlock)*pageBlock+1;
		    var endPage = Number(startPage)+Number(pageBlock) -1;
    		//담을 변수
    		var atag ="";
    		
    		if(endPage > totalP) {
    		  endPage = totalP;
    		}
    		if(startPage>pageBlock) {
    		  atag += '<li><a id="paging" href="#" onclick="mentorFindPaging('+(startPage-1)+', '+ data.flag +'); return false;">이전</a></li>';
    		}
    		for(j=startPage; j<=endPage; j++) { 
    		  if(j == currentPage) {
    		    atag += '<li class="active"><a id="currentPaging" href="#" onclick="mentorFindPaging('+j+', '+ data.flag +');return false;">'+ j +'</a></li>';
    		  }else {
    		    atag += '<li><a id="paging" href="#" onclick="mentorFindPaging('+j+', '+ data.flag +');return false;">'+ j +'</a></li>';
    		  }
    		}
    		if(endPage < totalP) {
    		  atag += '<li class="next"><a id="paging" href="#" onclick="mentorFindPaging('+(endPage+1)+'); return false;">다음</a></li>';
    		}
    		
    		$('.paging').append($('<ul/>', {
    			class : 'pagination'
    		}).append(atag));
    		
    	},
    	error : function(request,status,error){
    		alert("error code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	}
    	
    });
}

$('#recommend_essay').on('click', function(event){
	event.preventDefault();
	$('.essayName').empty();
	var flag = null;
	
	if($(this).hasClass("button color-gray")){
    	$(this).removeClass('color-gray');
    	flag = 1;
    } else {
    	$(this).addClass('color-gray');
    	flag = 0;
    }
	
	if(flag == 0){
		$('.essayName').text('신규 에세이');
	} else if (flag == 1) {
		$('.essayName').text('추천 에세이');
	}
	
	essayjobType(1, flag);
	
});


// 직무유형별 페이징 처리
function mentorFindPaging(paging, flag){
	essayjobType(paging, flag);
}


$('#essayWriteBtn').on('click', function(){
	location.href="/mentor/essayboard/essayboardWriteForm";
});

var flag = $('#flag').val();
$('#listflag').on('click', function(){
	
	if(flag == '0'){
		location.href="/mentor/essayboard/essayboardList?flag=1";
		console.log("??");
		$('#flag').val('1');
		$(this).addClass('color-gray');
	} else if(flag == '1'){
		location.href="/mentor/essayboard/essayboardList?flag=0";		
		$('#flag').val('0');
		$(this).addClass('button');
	}
	
	
});