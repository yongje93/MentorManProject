$(document).ready(function(){
	$.ajax({
		type : 'post' ,
		url : '/mentor/intern/getInternList',
		dataType : 'xml',
		success : function(data){
			console.log(data);
			
			let $frag = $(document.createDocumentFragment());
			$(data).find("dhsOpenEmpInfo").each(function(){
				//공개채용공고순번
				var empSeqno = $(this).find('empSeqno').text();
				//채용제목
				var empWantedTitle = $(this).find('empWantedTitle').text();
				//채용업체명
				var empBusiNm = $(this).find('empBusiNm').text();
				//채용시작일
				var empWantedStdt = $(this).find('empWantedStdt').text();
				//채용 종료일
				var empWantedEndt = $(this).find('empWantedEndt').text();
				//기업 구분명
				var coClcdNm = $(this).find('coClcdNm').text();
				//고용형탸
				var empWantedTypeNm = $(this).find('empWantedTypeNm').text();
				//고용형탸
				var regLogImgNm = $(this).find('regLogImgNm').text();
				//채용 URL
				var empWantedHomepgDetail = $(this).find('empWantedHomepgDetail').text();
				
				//console.log('empWantedStdt : ' + (empWantedEndt-empWantedStdt));
				var dDay = empWantedEndt-empWantedStdt;
				
				var jobTable = 
					`<tr>
	            	<td><img src='${regLogImgNm}'></td>
	                <td>
	                	<a onclick="window.open('https://www.work.go.kr/empInfo/empInfoSrch/list/popup/popDhsOpenEmpInfoDetail.do?empSeqno=${empSeqno}','popUpWindow','height=846,width=980,scrollbars=yes,resizable=no,toolbar=no,status=yes');">${empWantedTitle}</a>
	                	<span class="rnd_label blue ml05"></span>
	                    <p>${empWantedTypeNm}</p>
	                    
	                </td>
	                <td>
	                	${empBusiNm}	
	                    <p><i class="ico-txt v1 ico-cp-major">${coClcdNm}</i></p>
	                </td>
	                <td>${empWantedStdt} ~ ${empWantedEndt}</td>
	                <td class="d-day">D-${dDay}</td>
	            </tr>`;
				
				$frag.append($(jobTable));
			});
			//페이징 처리
			
			//총 데이터 양 
			var total = $(data).find("total").text();
			//한 페이지 갯수 
			var display = $(data).find("display").text();
			//총 블럭수 ex 32블럭
			var totalP=(Number(total)+Number(display)-1)/Number(display)
			//페이지 블럭 [1][2][3][다음 
			var pageBlock = 10;
			//현재 페이지
			var currentPage =$('#pageIndex').val();
			
			var startPage = ((Number(currentPage)-1)/Number(pageBlock))*Number(pageBlock)+1;
			var endPage = Number(startPage) + Number(pageBlock) -1;
			//담을 변수
			var atag ="";
			
			if(endPage > totalP) {
				endPage = totalP;
			}
			if(startPage>pageBlock) {
				atag += "<li><a rel='prev' data-reload='true' type='external' href='#' onclick='f_search("+(startPage-1)+"); return false;'>이전</a></li>;"
			}
			for(j =startPage; j<=endPage; j++) {
				if(j == currentPage) {
					atag += "<li class='active'><a remote='false' href='#' onclick='f_search("+j+"); return false;'>" + j + "</a></li>"
				}else {
					atag += "<li><a rel='prev' data-reload='true' type='external' href='#' onclick='f_search("+j+"); return false;'>" + j + "</a></li>"
				}
			}
			if(endPage < totalP) {
				atag += "<li class='next'><a rel='next' data-reload='true' type='external' href='#' onclick='f_search("+(endPage+1)+"); return false;' >다음</a></li>"
			}
			
			//$frag.append(atag);
			$('#inputIntern').append($frag);
			
			$('#inputIntern').append($('<ul/>', {
    			class : 'pagination'
    		}).append(atag));
			
		},
		error : function(err){
			alert('error2');
			console.log(err);
		}
	});
});


//<[CDATA[
$(function(){
	$('.excellent-comp > ul').bxSlider({
		maxSlides : 1,
		infiniteLoop : false
	});
	$('.blind-banner > ul').bxSlider({
		maxSlides : 1,
		infiniteLoop : false,
		controls : false
	});

	var slideArry = [],
	sliderConfig = {
		slideWidth : 1160,
		maxSlides : 1,
		moveSlides : 1,
		infiniteLoop : false,
		touchEnabled : false,
	};
	
	$('.recruit-list > ul').each(function(i, slider){
		slideArry[i] = $(slider).bxSlider(sliderConfig);
	});
	
	$('#latesetJobInfo a').click(function(e){
		e.preventDefault();
		$.each(slideArry, function(i, slider){
			slider.reloadSlider(sliderConfig);
		});
	});
});




//function f_resultJobsCodeSrchNew(resultObj){
//	var arrCodeNm = [];
//	var arrCodeNo = [];
//	var name = 'jobsCd';
//	var chkLng = 10;
//	
//	//console.log(resultObj[0]);
//	//console.log(resultObj[1]);
//
//	arrCertNo =  resultObj[0].split('|');
//	arrCertNm =  resultObj[1].split('|');
//
//	$("[id^='" + name + "NmArr']").remove();
//
//	var regionLength = $("[id^='" + name + "NmArr']").length;
//
//	for(var i=0; i < arrCertNm.length; i++){
//		if(regionLength + i < chkLng && $("#"+name+"NmArr"+arrCertNo[i]).length == 0 && arrCertNo[i] != ''){	//중복된 검색 제외
//			var html = $("#resultTemplate").html();
//			html = html.replace("_keyNo_", name +'NmArr'+ arrCertNo[i]);
//			html = html.replace(/_keyNm_/gi, arrCertNm[i]);
//			html = html.replace("_keyNoId_", name + arrCertNo[i]);
//			html = html.replace("_kayNmParam_", name);
//			html = html.replace("_kayNoParam_", arrCertNo[i]);
//			html = html.replace("_keyNmId_", name + arrCertNm[i]);
//			html = html.replace("_kayNmNmParam_", name+"NmArr");
//			html = html.replace("_kayNmNoParam_", arrCertNm[i]);
//			$('#sel_items').append(html);
//		}
//	}
//}

//선택조건 삭제 
function f_delSelVal(obj){
	var saveTxt = $(obj).closest('div').text().replace('삭제', '');
	
	alert($(obj).closest('div').attr('id'));
	
	$(obj).closest('div').remove();

	$('input[type="checkbox"]').each(function(){
		var chkTxt = $(this).closest('span').text();
		if(saveTxt == chkTxt){
			$(this).prop('checked', false);
			
		}
	});
	
	
}

//모든 선택 초기화
function f_removeAll(){
	
	$('.list div').each(function(){
		var saveTxt = $(this).text().replace('삭제', '');
		$(this).remove();
	}) ;
	var html = "<strong>선택조건 : </strong>";
	$('#sel_items').html(html);
	$('input[type="checkbox"]').prop('checked', false);
}

//페이징처리 함수
function f_search(pageNo){

	if(pageNo != '' && pageNo != undefined){
		$('#pageIndex').val(pageNo);
	}else{
		$('#pageIndex').val(1);
	}
	
//	alert($('#srchFrm').serialize());
	$.ajax({
		type : 'post' ,
		url : '/mentor/intern/getInternSearchList',
		data : $('#srchFrm').serialize(),
		dataType : 'xml',
		success : function(data){
			$('#inputIntern').empty();
			
			let $frag = $(document.createDocumentFragment());
			$(data).find("dhsOpenEmpInfo").each(function(){
				//공개채용공고순번
				var empSeqno = $(this).find('empSeqno').text();
				//채용제목
				var empWantedTitle = $(this).find('empWantedTitle').text();
				//채용업체명
				var empBusiNm = $(this).find('empBusiNm').text();
				//채용시작일
				var empWantedStdt = $(this).find('empWantedStdt').text();
				//채용 종료일
				var empWantedEndt = $(this).find('empWantedEndt').text();
				//기업 구분명
				var coClcdNm = $(this).find('coClcdNm').text();
				//고용형탸
				var empWantedTypeNm = $(this).find('empWantedTypeNm').text();
				//고용형탸
				var regLogImgNm = $(this).find('regLogImgNm').text();
				//채용 URL
				var empWantedHomepgDetail = $(this).find('empWantedHomepgDetail').text();
				
				var jobTable = 
				`<tr>
	            	<td><img src='${regLogImgNm}'></td>
	                <td>
	                	<a onclick="window.open('https://www.work.go.kr/empInfo/empInfoSrch/list/popup/popDhsOpenEmpInfoDetail.do?empSeqno=${empSeqno}','popUpWindow','height=846,width=980,scrollbars=yes,resizable=no,toolbar=no,status=yes');">${empWantedTitle}</a>
	                	<span class="rnd_label blue ml05"></span>
	                    <p>${empWantedTypeNm}</p>
	                    
	                </td>
	                <td>
	                	${empBusiNm}	
	                    <p><i class="ico-txt v1 ico-cp-major">${coClcdNm}</i></p>
	                </td>
	                <td>${empWantedStdt}~ ${empWantedEndt}</td>
	                <td class="d-day">D-${empWantedEndt-empWantedStdt}</td>
	            </tr>`;
				
				$frag.append($(jobTable));
			});
			
			//총 데이터 양 
			var total = $(data).find("total").text();
			//한 페이지 갯수 
			var display = $(data).find("display").text();
			//총 블럭수 ex 32블럭
			var totalP=(Number(total)+Number(display)-1)/Number(display)
			//페이지 블럭 [1][2][3][다음 
			var pageBlock = 10;
			//현재 페이지
			var currentPage =$('#pageIndex').val();
			var startPage = parseInt((currentPage-1)/pageBlock)*pageBlock+1;
			var endPage = Number(startPage)+Number(pageBlock) -1;
			
			//담을 변수
			var atag ="";
			
			if(endPage > totalP) {
				endPage = totalP;
			}
			if(startPage>pageBlock) {
				atag += "<li><a rel='prev' data-reload='true' type='external' href='#' onclick='f_search("+(startPage-1)+"); return false;'>이전</a></li>;"
			}
			for(j =startPage; j<=endPage; j++) {
				if(j == currentPage) {
					atag += "<li class='active'><a remote='false' href='#' onclick='f_search("+j+"); return false;'>" + j + "</a></li>"
				}else {
					atag += "<li><a rel='prev' data-reload='true' type='external' href='#' onclick='f_search("+j+"); return false;'>" + j + "</a></li>"
				}
			}
			if(endPage < totalP) {
				atag += "<li class='next'><a rel='next' data-reload='true' type='external' href='#' onclick='f_search("+(endPage+1)+"); return false;' >다음</a></li>"
			}
			
			$('#inputIntern').append($frag);
			
			$('#inputIntern').append($('<ul/>', {
    			class : 'pagination'
    		}).append(atag));
			
		},
		error : function(err){
			alert('errs');
			console.log(err);
		}
	});
}


//목록정렬 조회처리
//function sortOption(sortField){
//	var sortOrderBy = 'ASC';
//	if(sortField == 'DATE') {
//		sortOrderBy = 'DESC';
//		$("#sortOrderByDATE").val(sortOrderBy);
//		$("#sortOrderByCloseDt").val("");
//		$("#sortOrderByComnm").val("");
//	}
//	if(sortField == 'receiptCloseDt') {
//		$("#sortOrderByDATE").val("");
//		$("#sortOrderByCloseDt").val(sortOrderBy);
//		$("#sortOrderByComnm").val("");
//	}
//	if(sortField == 'comnm') {
//		$("#sortOrderByDATE").val("");
//		$("#sortOrderByCloseDt").val("");
//		$("#sortOrderByComnm").val(sortOrderBy);
//	}
//
//	document.getElementById('sortField').value = sortField;
//	document.getElementById('sortOrderBy').value = sortOrderBy;
//
//	var frm = document.getElementById("srchFrm");
//		frm.submit();
//}

//function f_enterSearch(){
//
//	f_setKeyWord();
//	f_search();
//	return false;
//}
//
//function f_setKeyWord(){
//	$("#keyWord").val($("#txtKeyWord").val());
//}

//function f_clear(id){
//	$("#"+id).val("");
//};

function f_goAllnewOpen(id){
	$('input[type="checkbox"]').attr('checked', false);
	document.getElementById(id).checked = true;
	f_search();

	return false;
}

//상세 팝업
function f_goDetail(seq){
	window.open("/empInfo/empInfoSrch/list/popup/popDhsOpenEmpInfoDetail.do?empSeqno="+seq, "empInfoPopup" , "width=980, height=846,scrollbars=yes,resizable=no,toolbar=no,status=yes");
}
//]]>>


//체크박스 클릭시
$(document).ready(function(){
	$('input[type="checkbox"]').click(function(){
		var selTxt = $(this).closest('span').text(); // 체크한 텍스트
		
		if($(this).is(':checked') == true) {
			id = $(this).attr('id');
			var html = '';
			html += '<span id="sp_'+ id +'">';
			html += selTxt ;
			html += '&nbsp&nbsp&nbsp</span>';
			
			$('#sel_items2').append(html);
		}else{
			var id = $(this).attr('id');
			$("span").remove("#sp_"+id);
		}
	});


//	$('input[name="coClcd"]').each(function(){
//		f_addSrchList(this);
//	});
//
//	$('input[name="empWantedCareerCd"]').each(function(){
//		f_addSrchList(this);
//	});
//
//	$('input[name="empWantedEduCd"]').each(function(){
//		f_addSrchList(this);
//	});
//
//	$('input[name="empWantedTypeCd"]').each(function(){
//		f_addSrchList(this);
//	});
});

//function f_addSrchList(obj){
//	var selTxt = '';
//	var html = '';
//	if($(obj).is(':checked') == true){
//		alert('f_addSrchList');
//		selTxt = $(obj).closest('span').text();
//		html = '';
//		html += '<div><span>';
//		html += selTxt;
//		html += '</span><button type="button" class="btn-del" onclick="f_delSelVal(this);">삭제</button>';
//		html += '</div>';
//		$('#sel_items').append(html);
//	}
//}
