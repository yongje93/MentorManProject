<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link  rel="stylesheet" href="../css/noticeboard.css">
<div class="page navbar-fixed open_mentorings show" data-name="open_mentorings-show">
  <div class="page-content">
    <div class="open-mentoring-block-container" id="openMentoringBlockContainer">
      <div class="notice-block">
		<input type="hidden" id="pg" value="${pg}">
		<input type="hidden" id="seq" value="${noticeboardDTO.noticeboard_seq}">
        <h1 class="notice-title">
       		공지사항
        </h1>
		<table class="noticeboardTable" cellspacing="0" cellpadding="0" style="width: 100%; margin-top: 20px;">
			<tbody>
				<tr class="noticeTopLine">
					<th scope="row">
						<p>제목</p>
					</th>
					<td colspan="3" style="font-weight: bold;" >${noticeboardDTO.noticeboard_title}</td>
				</tr>
				<tr class="noticeMiddleLine">
					<th scope="row">
						<p>날짜</p>
					</th>
					<td>${noticeboardDTO.noticeboard_logtime}</td>
					<th scope="row">
						<p>조회수</p>
					</th>
					<td>${noticeboardDTO.noticeboard_hit}</td>
				</tr>
				<tr class="noticeUnderLine">
					<td colspan="4">${noticeboardDTO.noticeboard_content}</td>
				</tr>
			</tbody>
		</table>
      </div>
    <div style="margin-top: 1px; margin-bottom: 100px; width: 60px; height: 5px; float: right;">
    	<input type="button" onclick="location.href='/mentor/noticeboard/noticeboardList?pg=${pg}'" value="목록" class="btn button button-big button-fill" style="line-height: 0px;">
    </div>
    </div>
  </div>
</div>
<script src="../js/noticeboard.js"></script>



