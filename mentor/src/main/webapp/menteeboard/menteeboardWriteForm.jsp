<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="../js/summernote-ko-KR.js"></script>
<link href="../css/menteeboardWriteForm.css" rel="stylesheet">

<div class="page navbar-fixed mentee_programs index">
<div class="page-content" >
   <div class="block-title strong-title">멘티 글쓰기</div>
   <div calss="block inset">
         <form method="post" id="menteeboardWriteForm" name="menteeboardWriteForm">
            <table id="menteeboardWriteForm_table" class="menteeboardWriteForm_table">
               <thead>
               </thead>
               <tbody>
               <tr>
                  <th scope="cols"><input type="text" id="menteeWriteFormSubject" name="menteeWriteFormSubject" placeholder="제목을 입력하세요"  style="width:941px"></th>
               	  <th>
               	  	<select id="job_code_writeForm" name="job_code_writeForm">
		        		<option value="">직무 선택</option>
		            	<option value="job_code_0">인사/총무/노무</option>
		                <option value="job_code_1">마케팅/MD</option>
		                <option value="job_code_2">홍보/CSR</option>
		                <option value="job_code_3">영업/영업관리</option>
		                <option value="job_code_4">회계/재무/금융</option>
		                <option value="job_code_5">해외/기술영업</option>
		                <option value="job_code_6">유통/무역/구매</option>
		                <option value="job_code_7">전략/기획</option>
		           		<option value="job_code_8">IT개발</option>
		           		<option value="job_code_9">서비스 기획/UI/UX</option>
		           		<option value="job_code_10">디자인/예술</option>
		           		<option value="job_code_11">미디어</option>
		           		<option value="job_code_12">서비스</option>
		           		<option value="job_code_13">연구/설계</option>
		           		<option value="job_code_14">전문/특수</option>
		           		<option value="job_code_15">교육/상담/컨설팅</option>
		           		<option value="job_code_16">공무원/공공/비영리</option>
		           		<option value="job_code_17">생산/품질/제조</option>
		           		<option value="job_code_18">기타 사무</option>
	            	</select>
               	  </th>
               </tr>
                <tr>
                   <td colspan="2">
                      <div id="summernoteDiv">
                              <textarea id="summernote" name="summernote"></textarea>
                      </div>
                      <div id="summernote_error_div"></div>
                   </td>
                <tr>
                </tbody>
                <tr align="center">
                   <td colspan="2">
                      <div class="menteeboardFloat_right_div"><input type="reset" id="resetBtn" class="button color-gray" value="취소"></div>
                      <div class="menteeboardFloat_right_div"><input type="button"  id="menteeboardWriteForm_Btn" class="button color-gray" value="작성하기"></div>
                   </td>
                </tr>
            </table>
         </form>
   </div>
</div>
</div> 

<script type="text/javascript" src="../js/menteeboard.js"></script>
<script type="text/javascript" src="../js/menteeboardWriteForm.js"></script>
