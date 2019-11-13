<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link  rel="stylesheet" href="../css/noticeboard.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="../js/summernote-ko-KR.js"></script>
<div class="page navbar-fixed mentee_programs index">
<div class="page-content" >
	<div class="block-title strong-title">공지 글쓰기</div>
	<div calss="block inset">
			<form method="post" id="noticeboardWriteForm" enctype="multipart/form-data">
				<table id="noticeboardWriteForm_table" class="noticeboardWriteForm_table">
					<thead>
					</thead>
					<tbody>
					<tr>
						<th scope="cols"><input type="text" id="noticeTitle" name="noticeTitle" placeholder="제목입력"><span id="noticeboardWriteNonTitle_error_span"></span></th>
					</tr>
				 	<tr>
				 		<td colspan="2">
				 			<div id="summernoteDiv">
             					  <textarea id="summernote" name="editordata"></textarea>
            				</div>
				 			<div id="summernote_error_div"></div>
				 		</td>
				 	<tr>
				 	</tbody>
				 	<tr align="center">
				 		<td colspan="2">
				 			<div class="noticeboardFloat_right_div"><input type="button" id="noticeboerdWriteForm_backBtn" class="button color-gray" value="뒤로가기"></div>
				 			<div class="noticeboardFloat_right_div"><input type="button"  id="noticeboardWriteForm_Btn" class="button color-gray" value="작성하기"></div>
				 		</td>
				 	</tr>
				</table>
			</form>
	</div>
</div>
</div>
<script src="../js/noticeboard.js"></script>
<script>

/* summernote 사진 이름 변경 */
$(function(){
	$("#summernote").summernote({
	   	placeholder: "내용을 입력하세요",
	    height: 400,
	    lang: 'ko-KR',
	    disableResizeEditor: true,
	    callbacks: {
         onImageUpload: function(files, editor, welEditable) {
           for (var i = files.length - 1; i >= 0; i--) {
             sendFile(files[i], this);
           }
         }
       }
	});
});

function sendFile(file, el) {
    var form_data = new FormData();
    form_data.append('file', file);
    $.ajax({
      data: form_data,
      type: 'post',
      url: '/mentor/noticeboard/noticeboardImage',
      cache: false,
      contentType: false,
      enctype: 'multipart/form-data',
      processData: false,
      success: function(url) {
        $(el).summernote('editor.insertImage', '../storage/'+url);
   	  },
   	  error: function(){
   		  alert('에러');
   	  }
  });
}
/* summernote 사진 이름 변경 */
</script>