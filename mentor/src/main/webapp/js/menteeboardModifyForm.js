//
$('#menteeboardModifyBtn').on("click" , function(){
	if($('#menteeModifyFormSubject').val() == '' ){
	}else if($('#summernote').val() == ''){
	}else if($('#job_code_ModifyForm option:selected').val() == ''){
		 var toastTop = app.toast.create({
	            text: '직무를 선택하세요.',
	            position: 'top',
	            closeButton: true,
	          });
	          toastTop.open();
	}
	else{
		$.ajax({
			type : 'post',
			url : '/mentor/menteeboard/menteeboardModify',
			data : $('#menteeboardModifyForm').serialize(),
			success : function(){
				 var toastTop = app.toast.create({
			            text: '수정 완료 되었습니다.',
			            position: 'top',
			            closeButton: true,
			          });
			          toastTop.open();
				$(location).attr("href", "http://localhost:8080/mentor/menteeboard/menteeboardList?pg=1");
			},
			error : function(err){
				console.log(err);
			}
		});
	}
});

//썸머노트 사용
$(document).ready(function() {
    $("#summernote").summernote({
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
    
    $('#job_code_ModifyForm option[value=${menteeboardDTO.job_code}]').attr('selected','selected');
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
