// 직무유형 버튼 전용 전역변수
var jobs = new Array();

//메인에서 더보기 링크 처리
$(document).ready(function(){
   var essayFlag = $('#recommend_view_essay').val();
   if(essayFlag != ""){
      $('.essayName').empty();
      $('#recommend_essay').attr('class', 'button');
      $('.essayName').text('추천 에세이');
      essayjobType(1 , essayFlag);
   }
});

// 에세이 직무유형 버튼
$(".row > a").on("click",function(event, data){// a태그 클릭시 작동
	// 클릭된 태그의 본래의 기능을 막음 즉, a태그 본래 기능을 막음
	event.preventDefault();
	if($('#memNick').val() == ""){
		location.href="/mentor/member/loginForm";
	} else {
		$('#gap').empty();
		$('.paging').empty();
		$('.essayName').empty();
//		var empty = true;
		var page;
		
		var flag = $('#essayFlag').val();
		
		if(flag == 0){
			$('.essayName').text('신규 에세이');
		} else if (flag == 1) {
			$('.essayName').text('추천 에세이');
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
	        
		for (var i=0; i<jobs.length; i++) {
	          if (jobs[i] != null) {
	            empty = false;
	            break;
	          }
	    }
		
//        if(!empty){
        	essayjobType(page, flag);
//        }
	}
		

  });

// 직무유형 버튼 값 처리
function essayjobType(pg , flag){
	$('#gap').empty();
	$('.paging').empty();
	
	var page = parseInt(pg);
	
	if(typeof pg == "undefined"){
		page = 1;
	}
	var Flag = parseInt(flag);
	
	$.ajax({
    	type : 'post',
    	url : '/mentor/essayboard/essayjobType',
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
    			
    			if(items.essayboard_scrapFlag == 1){
	    			var scrapFlag = "<img id="+ items.essayboard_seq +" src='../image/scrapOkImg.png' width='13'>"
	    		} else {
	    			var scrapFlag = "<img id="+ items.essayboard_seq +" src='../image/scrapNoImg.png' width='13'>"
	    		} 
    			
    			
    			
    			let essayboard = `
	<input type="hidden" id="job_code" value="${items.job_code }">
		<div class="col-100 tablet-50 desktop-33">
				<div class="card mentor-post-card mentor_post_6589">
					<div class="card-header">
					<a class="color-black" type="external" href="/mentor/essayboard/mentorInfoView?mentors=${items.member_seq }">
					<div>
						<div class="mentor-image img-circle">
					            ${profileFlag}
						</div>
						<div class="mentor-info">
			          		<div>
				            	<strong class="mentor-name">${items.member_name }</strong>
				            		<small>멘토</small>
			          		</div>
      				  		<div class="job">
        					<small>
          						${items.mentor_company } · ${items.mentor_department }
        					</small>
      						</div>
						</div>
					</div>
					</a>
		      <div class="created-at">
		        <small>${items.essayboard_logtime }</small>
		      </div>
			</div>

			<div class="card-content card-content-padding"style="overflow: hidden; text-overflow: ellipsis; height: 200px; ">
			<input type="hidden" id="seq" name="seq" value="${items.essayboard_seq }">
    				
		<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${items.pg }&seq=${items.essayboard_seq}&mentors=${items.member_seq }" >
        <div class="mentor-post-title">
        	${items.essayboard_title }
        </div>

        <div class="mentor-post-detail">
        	${items.essayboard_content }
        </div>
		</a>  
	</div>

		<div class="card-footer" style="">
	<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6589/bookmarks" style="right: 0px;
		position: unset;
		margin: 0px 0px;">
		${scrapFlag}
		<span id="ScrapDiv_${items.essayboard_seq}">${items.essayboard_scrap}</span>
	  	
	  	<input type="hidden" id="scrapFlag" name="scrapFlag" value="${items.essayboard_scrapFlag}">
	</a>

			    <div class="created-at">
			      
				</div>
				</div>
		</div>
	</div>
	
    			`;

    			$('#gap').append($(essayboard))
    			
    			
    		});
    		
    		$('#gap').append($('<div/>', {
				class : 'col-100 tablet-50 desktop-33'
			})).append($('<div/>', {
				class : 'col-100 tablet-50 desktop-33'
			}));
//			.append($('<input/>', {
//				type : 'hidden',
//				id : 'essayFlag',
//				name : 'essayFlag',
//				value : data.flag
//			}));
    		$('#essayFlag').val(data.flag);
    		
    		
    		//총 블럭수 ex 32블럭
		    var totalP=(Number(data.essayDutyTotal)+Number(data.pageSize)-1)/data.pageSize;
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
    		  atag += '<li><a id="paging" href="#" onclick="essayPaging('+(startPage-1)+', '+ data.flag +'); return false;">이전</a></li>';
    		}
    		for(j=startPage; j<=endPage; j++) { 
    		  if(j == currentPage) {
    		    atag += '<li class="active"><a id="currentPaging" href="#" onclick="essayPaging('+j+', '+ data.flag +');return false;">'+ j +'</a></li>';
    		  }else {
    		    atag += '<li><a id="paging" href="#" onclick="essayPaging('+j+', '+ data.flag +');return false;">'+ j +'</a></li>';
    		  }
    		}
    		if(endPage < totalP) {
    		  atag += '<li class="next"><a id="paging" href="#" onclick="essayPaging('+(endPage+1)+'); return false;">다음</a></li>';
    		}
    		
    		
    		
    		
    		$('.paging').append($('<ul/>', {
    			class : 'pagination'
    		}).append(atag));
    		
    		//잡코드 버튼 클릭후 스크랩 버튼 이벤트 
    		$('a[type="externalScrap"]').on('click' , function(){
    			if($('#memNickname').val()== ''){
    				 var toastTop = app.toast.create({
    			            text: '로그인 후 이용 가능 합니다.',
    			            position: 'top',
    			            closeButton: true,
    			          });
    			          toastTop.open();
    			}else{
    				var seq = $(this).closest('div').prev().children().first().val();
    				
    				
    				if($(this).children().last().val() == 0){
    					$("#"+seq).prop("src", "../image/scrapOkImg.png");
    					$(this).children().last().val(1);
    					
    				}else{
    					$("#"+seq).prop("src", "../image/scrapNoImg.png");
    					$(this).children().last().val(0);
    				}
    				var scrapFlag = $(this).children().last().val()
    				
    				
    				// seq , scrap_flag 
    				var sendData = {
    						'essayboardScrap_es_seq' : seq,
    						'scrapFlag' : scrapFlag
    					};
    				$.ajax({
    					url : '/mentor/essayboard/essayboardScrap',
    					type : 'POST',
    					data : sendData,
    					dataType : 'text',
    					success : function(data) {
    						$('#ScrapDiv_'+seq).empty();
    						$('#ScrapDiv_'+seq).html(data);
    					},
    					error : function(err){
    						console.log('에러');
    					}
    				});
    			}
    		});

    		
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
function essayPaging(paging, flag){
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



//스크랩 버튼을 누를시
$(document).ready(function() {
	$('a[type="externalScrap"]').on('click' , function(){
		if($('#memNickname').val()== ''){
			 var toastTop = app.toast.create({
		            text: '로그인 후 이용 가능 합니다.',
		            position: 'top',
		            closeButton: true,
		          });
		          toastTop.open();
		}else{
			var seq = $(this).closest('div').prev().children().first().val();
			
			if($(this).children().last().val() == 0){
				$("#"+seq).prop("src", "../image/scrapOkImg.png");
				$(this).children().last().val(1);
				//스크랩 모달
				
				var toastIcon = app.toast.create({
					  icon: app.theme === 'ios' ? '<i class="fas fa-bookmark fa-3x"></i>' : '<i class="fas fa-bookmark" style="width: 30px; height: 30px;"></i>',
					  text: '',
					  position: 'center',
					  closeTimeout: 2000,
					});
				toastIcon.open();
				
			}else{
				$("#"+seq).prop("src", "../image/scrapNoImg.png");
				$(this).children().last().val(0);
			}
			var scrapFlag = $(this).children().last().val()
			
			
			// seq , scrap_flag 
			var sendData = {
					'essayboardScrap_es_seq' : seq,
					'scrapFlag' : scrapFlag
				};
			$.ajax({
				url : '/mentor/essayboard/essayboardScrap',
				type : 'POST',
				data : sendData,
				dataType : 'text',
				success : function(data) {
					$('#ScrapDiv_'+seq).empty();
					$('#ScrapDiv_'+seq).html(data);
				},
				error : function(err){
					console.log('에러');
				}
			});
			
			
		}
	});
	
});

