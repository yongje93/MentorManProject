<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link  rel="stylesheet" href="../css/noticeboard.css">
<div class="page navbar-fixed open_mentorings show" data-name="open_mentorings-show">
  <div class="page-content">
    <div class="open-mentoring-block-container" id="openMentoringBlockContainer">
      <div class="question-block">
		<input type="hidden" id="pg" value="${pg}">
		<input type="hidden" id="seq" value="${noticeboardDTO.noticeboard_seq}">
        <h1 class="question-title">
       		${noticeboardDTO.noticeboard_title}
        </h1>

        <div class="trix-content froala-content">${noticeboardDTO.noticeboard_content}</div>
      </div>
      

      <div class="answer-block">
        <div class="trix-content froala-content">


          <div class="block wrap-share">
           <div class="noticeboardFloat_right_div">
				<input type="button" id="noticeboardBack_btn" class="button color-gray" value="뒤로가기">
			</div>
          </div>
        </div>
      </div>
     </div>
  </div>
</div>
<script src="../js/noticeboard.js"></script>s



