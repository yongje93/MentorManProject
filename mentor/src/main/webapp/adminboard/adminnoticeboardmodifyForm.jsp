<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="../js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="../admincss/adminnoticeboard.css">  
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>공지사항 수정</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
			<form method="post" id="noticeboardWriteForm" enctype="multipart/form-data">
				<input type="hidden" id="pg" value="${pg}">
				<input type="hidden" id="seq" value="${adminnoticeboardDTO.noticeboard_seq}">
				<div class="table-responsive" style="overflow:hidden;">
					<div class="row">
						<div class="col-sm-12">
							<table id="noticeboardWriteForm_table" class="noticeboardWriteForm_table">
					<tbody>
					<tr>
						<th scope="cols"><input type="text" id="noticeTitle" name="noticeTitle" placeholder="제목입력" value="${adminnoticeboardDTO.noticeboard_title }"><span id="noticeboardWriteNonTitle_error_span"></span></th>
					</tr>
				 	<tr>
				 		<td colspan="2">
				 			<div id="summernoteDiv">
             					  <textarea id="summernote" name="editordata">${adminnoticeboardDTO.noticeboard_content}</textarea>
            				</div>
				 			<div id="summernote_error_div"></div>
				 		</td>
				 	<tr>
				 	</tbody>
				 	
				</table>
						</div>
					</div> <!-- table row 끝-->
				</div> <!-- table 감싸는 div-->
				<div class="ln_solid"></div>
				<div class="form-group">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<button type="button" class="btn btn-success btn-sm noticeboerdUpdateForm_btn" >작성하기</button>
					</div>
				</div>
			</form>
				<div class="ln_solid"></div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->
<script src="../adminjs/noticeboard.js"></script>
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
      url: '/mentor/adminboard/adminnoticeboardImage',
      cache: false,
      contentType: false,
      enctype: 'multipart/form-data',
      processData: false,
      success: function(url) {
        $(el).summernote('editor.insertImage', '../storage/admin/'+url);
   	  },
   	  error: function(){
   		  alert('에러');
   	  }
  });
}
/* summernote 사진 이름 변경 */
</script>
