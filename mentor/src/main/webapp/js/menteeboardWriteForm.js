
//화면 로드시 썸머노트 사용
$(document).ready(function() {
	    $("#summernote").summernote({
	       placeholder: '여기를 눌러서 글을 작성 할 수 있습니다.<br>[정보통신망법에 의한 홍보 게시물 작성 금지]<br><br>1.회사/단체와 관련된 공모전,대외활동,구인,펀딩 홍보.<br>2.커뮤니티,어플,쇼핑몰,카페,블로그 홍보 및 방문유도.<br>3.토익,한자 등 어학원 홍보.<br>4.연극,영화 티켓 할인 서비스 홍보',
	       height: 300,                 // set editor height
	       minHeight: null,             // set minimum height of editor
	       maxHeight: null,             // set maximum height of editor
	       focus: true,
	       lang : 'ko-KR',
	       callbacks: {
	           onImageUpload: function(files, editor, welEditable) {
	             for (var i = files.length - 1; i >= 0; i--) {
	               sendFile(files[i], this);
	             }
	           }
	         }
	    });
    });
    
	//썸머노트 이미지 업로드시 storage에 저장
    function sendFile(file, el) {
        var form_data = new FormData();
        form_data.append('file', file);
        $.ajax({
          data: form_data,
          type: "POST",
          url: '/mentor/menteeboard/menteeboardImage',
          cache: false,
          contentType: false,
          enctype: 'multipart/form-data',
          processData: false,
          success: function(url) {
            /* $('#imageBoard > ul').append('<img src="../storage/'+url+'" width="480" height="auto"/>');  */
        	$(el).summernote('editor.insertImage', '../storage/'+url);
          },
          error : function(err){
        	  console.log(err);
          }
        });
      }
    
    
    //글작성 버튼 클릭시 ajax통신
    $('#menteeboardWriteForm_Btn').on('click' , function(){
    	if($('#menteeWriteFormSubject').val() == ''){
    	}else if($('#summernote').val() == ''){
    	}else if($('#job_code_writeForm option:selected').val() == ''){
    		 var toastTop = app.toast.create({
    	            text: '직무를 선택하세요.',
    	            position: 'top',
    	            closeButton: true,
    	          });
    	          toastTop.open();
    	}else{
    		$.ajax({
    			type : 'post',
    			url : '/mentor/menteeboard/menteeboardWrite',
    			data : $('#menteeboardWriteForm').serialize(),
    			dataType : 'text',
    			success : function(){
    				$(location).attr("href", "http://localhost:8080/mentor/menteeboard/menteeboardList?seq=1");
    			},
    			error : function(err) {
    				console.log(err);
    			}
    		});
    	}
    });