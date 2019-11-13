<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link  rel="stylesheet" href="../css/essayboard.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<div class="page navbar-fixed mentee_programs index">
   <div class="page-content">
      <div class="block-title strong-title">에세이 수정</div>
      <div class="block inset">
         <form method="post" id="essayboardModifyForm" action="/mentor/essayboard/essayboardModify">
            <div class="list form-list no-hairlines">
               <ul>
                  <div class="label-title">
                     <label class="string required" for="title">멘토링 분야</label>
                  </div>
                  <li class="item-content item-input">
                     <div class="item-inner">
                        <div class="item-input-wrap input-dropdown-wrap">
                           <select class="select optional" name="job_code" id="job_code">
                              <option value="${essayboardDTO.job_code }">${essayboardDTO.job_type }</option>
                              <option value="job_code_0">인사/총무/노무</option>
                              <option value="job_code_1">마케팅/MD</option>
                              <option value="job_code_2">홍보/csr</option>
                              <option value="job_code_3">영업/영업관리</option>
                              <option value="job_code_4">회계/재무/금융</option>
                              <option value="job_code_5">해외/기술영업</option>
                              <option value="job_code_6">유통/무역/구매</option>
                              <option value="job_code_7">전략/기획</option>
                              <option value="job_code_8">IT개발</option>
                              <option value="job_code_9">서비스 기획/UI/UX 등</option>
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
                           <div class="job_code_error_div"></div>
                        </div>
                     </div>
                  </li>
                  <div class="label-title">
                     <label class="string required" for="title">제목</label>
                  </div>
                  <li class="item-content item-input">
                     <div class="item-inner">
                        <input type="text" name="title" id="title" value="${essayboardDTO.essayboard_title }">
                     	<div class="title_error_div"></div>
                     </div>
                  </li>

                  
                  <div class="label-title">
                     <label class="string required" for="title"></label>
                  </div>
                  <li>
                     <textarea id="summernote" name="content">${essayboardDTO.essayboard_content }</textarea>
                     <br>
                     <div class="summernote_error_div"></div>
                  </li>
                  
                  
               </ul>
            </div>
            <div style="margin-top: 50px; margin-bottom: 100px;">
               <input type="button" id="essayboardModifyBtn" value="작성 완료" class="btn button button-big button-fill" style="line-height: 0px;">
            </div>
         </form>
      </div>
   </div>
</div>
<script src="../js/essayboardModify.js"></script>
<script>
$(document).ready(function(){
	$("#summernote").summernote({
	      placeholder: "내용을 입력해주세요",
	    height: 300,
	    lang: 'ko-KR'
	});
});

</script>