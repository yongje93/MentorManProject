<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#boardListMain_div{
    width: 100%;
    margin: 0;
    padding: 0;
}

#main_div{
   /* overflow: hidden; */
   
    width: 100%;
    margin: auto;
}

#mainImage_div{
   border: 1px solid violet;
    width: 100%;
    height: 200px;
    margin: auto;
}

#mainMeun_div{
   zoom: 1;
    background: #40b1f1;
   /*  max-width: 1180px; */
    width: 100%;
    margin: 0 auto;
    position: absolute;
    height: 55px;
    top: 255px;
    left: 0;
}

.boardMeun_li{
   list-style:none;
   padding-left:0px;
   float: left;
    width: 16.30%;
    position: relative;
}

#mainImg{
   width: 100%;
    height: 200px;
}

.tab_div1{
   height: 100px;
}
#boardBody_div{
   width: 100%;
    position: relative;
    margin-bottom: 200px;
}

#boardList_div{
    height: 600px;
    margin: auto;
}

.noticeboardList_Table {
   border: 1px solid #fff;
   width: 100%;
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;

}
.noticeboardList_thead_th{
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
.noticeboardList_tbody_th{
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
.noticeboardList_td{
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

#selectBox_th{
   text-align: right;
}

#menteeboardPagingDiv{
   text-align: center;
   margin: 2px 118px;
}
.noticeboardFloat_right_div{
   float: right;
}

#notice_seq:link    { text-decoration:none; color:#8600E4 }
#notice_seq:visited { text-decoration:none; color:#800080 }
#notice_seq:hover   { text-decoration:underline; color:#FF0000 }
#notice_seq:active  { text-decoration:none; color:#FF0000 }
</style>

<div class="page navbar-fixed mentee_programs index">
<div class="page-content" >
   <div class="block-title strong-title">공지</div>
   <div id="searchImage_div" style="float: right;"><img id="search_image" src="../image/images.png" style="width: 20px; height: 20px; cursor:pointer;"></div>
   <div id="searchText_div" style="float: right;"><input type="text" id="search_text" placeholder="찾기" style="width: 200px;"></div>
   <div id="searchText_div">
   	<select id="job_code" name="job_code">
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
				        <th class="noticeboardList_tbody_th" scope="row" align="center">작성시간</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">조회수</th>
				        <th class="noticeboardList_tbody_th" scope="row" align="center">좋아요</th>
                   </tr>
                   </tbody>
                   <tbody id="inputBody2"></tbody>
            </table>
         <br>
         <div id="page_number" style="float: left;"></div>
         <div class="noticeboardFloat_right_div">
            <button id="menteeboardWriteformBtn" class="button color-gray">글쓰기</button>
         </div>
         <div id="menteeboardPagingDiv"></div>
         <input type="hidden" id="pgInput" name="pgInput" value="${pg}">
         <input type="hidden" id="heart2" name="heart2" value="">
      </div>
</div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/menteeboard.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$.ajax({
		type : 'post' ,
		url : '/mentor/menteeboard/getMenteeboardList',
		data : 'pg='+$('#pgInput').val(),
		dataType : 'json',
		success : function(data){
			$.each(data['list'], function(key, value){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text : value.seq
				})).append($('<td/>',{
					align: 'center',
					text : value.job_type
				})).append($('<td/>',{
					}).append($('<a/>',{
							href : 'javascript:void(0)',
							id : 'subjectA',
							text : value.subject,
							class : value.seq+""
				}))).append($('<td/>',{
					align: 'center',
					text : value.nickname
				})).append($('<td/>',{
					align: 'center',
					text : value.logtime
				})).append($('<td/>',{
					align: 'center',
					text : value.hit
				})).append($('<td/>',{
					align: 'center',
					text : value.good
				})).appendTo($('#inputBody2'));
			});
			
			//paging 처리
			$('#menteeboardPagingDiv').html(data.menteeboardPaging.pagingHTML);
			
			//클릭시 뷰로 이동
			$('#boardTable').on('click' ,'#subjectA' , function(){
				var seq = $(this).parent().prev().prev().text();
				$(location).attr("href", "http://localhost:8080/mentor/menteeboard/menteeboardView?seq="+seq+"&pg="+$('#pgInput').val());
			});	
		},
		error : function(err){
			alert('error');
			console.log(err);
		}
	});
});
</script>

