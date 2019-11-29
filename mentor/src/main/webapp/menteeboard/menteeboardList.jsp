<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="../css/menteeboardList.css">
<div class="page navbar-fixed mentee_programs index">
<div class="page-content" >
<form id="menteeboardSearch" method="post">
   <div class="block-title strong-title">멘티 게시판</div>
   <div id="searchImage_div" style="float: right;"><img id="search_image" src="../image/images.png" style="width: 20px; height: 20px; cursor:pointer;"></div>
   <div id="searchText_div" style="float: right;"><input type="text" id="search_text" name="search_text" placeholder="찾기" style="width: 200px;"></div>
   <div id="searchText_div">
   <div class="item-inner">
    <div class="item-input-wrap input-dropdown-wrap">
   	<select class="select optional" id="job_code" name="job_code">
        	<option value="job_code_n">공통 직무</option>
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
      </div>
      </div>
   </div>
   
   <div id="searchText_error_div"></div>
      <div id="boardList_div">
            <table class="noticeboardList_Table" id="boardTable">
                   <thead>
                   </thead>
                   <tbody id="inputBody">
                   <tr>
                       	<th class="noticeboardList_tbody_th" scope="row" align="center">No</th>
				       	<th class="noticeboardList_tbody_th" scope="row" align="center">직무유형</th>
				    	<th class="noticeboardList_tbody_th" scope="row" align="center">제목</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">글쓴이</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">작성일</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">조회수</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">좋아요</th>
                   </tr>
                   </tbody>
                   <tbody id="inputBody2"></tbody>
            </table>
         <br>
         <div id="page_number" style="float: left;"></div>
         <div class="noticeboardFloat_right_div">
            <a id="menteeboardWriteformBtn" class="button" type="external" href="javascript:void(0);">
				<i class="fas fa-pencil-alt"></i> 글쓰기
			</a>
         </div>
         <div id="menteeboardPagingDiv"></div>
         <input type="hidden" id="pgInput" name="pgInput" value="${pg}">
         <input type="hidden" id="heart2" name="heart2" value="">
      </div>
</form>
</div>
</div>
<script type="text/javascript" src="../js/menteeboardList.js"></script>