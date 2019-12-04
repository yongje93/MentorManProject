<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 오늘 날짜 --%>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="today" value="${now}" pattern="yyyy/MM/dd"/>
<!-- datepicker -->
<link rel="stylesheet" href="../css/order.css" type="text/css"/>
<link href="../css/datepicker.min.css" rel="stylesheet" type="text/css">
<script src="../js/datepicker.min.js"></script>
<script src="../js/datepicker.ko.js"></script>

<div class="col-100 tablet-90" style="margin: auto;">
<div class="block no-hairlines hero-title" style="width: 835px;">
	<h1 class="title">결제 내역</h1>
</div>
<div class="block hero-title" style="width: 900px;">
	<div class="n-table-filter">
		<div class="n-radio-tab">
			<input type="radio" id="radioTabGuide0" name="radioCheck" onclick="setPeriod(this,'1week');">
			<label for="radioTabGuide0">1주일</label>
	
			<input type="radio" id="radioTabGuide1" name="radioCheck" onclick="setPeriod(this,'1month');">
			<label for="radioTabGuide1">1개월</label>
	
			<input type="radio" id="radioTabGuide2" name="radioCheck" onclick="setPeriod(this,'3month');">
			<label for="radioTabGuide2">3개월</label>
	
			<input type="radio" id="radioTabGuide3" name="radioCheck" onclick="setPeriod(this,'');" checked="">
			<label for="radioTabGuide3">전체 시기</label>
		</div>
		<div class="n-datepicker sb" style="border: 1px solid #777777; padding-left: 5px;">
			<input type="text" class="n-input hasDatepicker" name="startDate" id="startDate" placeholder="시작" value="">
		</div>
		<div class="n-datepicker" style="border: 1px solid #777777; padding-left: 5px;">
			<input type="text" class="n-input hasDatepicker" name="endDate" id="endDate" placeholder="종료" value="">
		</div>
		<div class="n-select" style="position: absolute; top:-5px; right: 158px;">
			<select id="condition" name="option">
				<option value="all" selected="selected">전체상태</option>
				<option value="complete">주문완료</option>
				<option value="cancel">주문취소</option>
			</select>
		</div>
		<input type="button" class="n-btn btn-sm btn-accent" id="orderSearchBtn" style="margin-left: 35px;" value="조회">
	</div>
</div>
<div class="block" style="width: 890px;">
<form id="menteeOrderForm">
	<table id="historyTable" class="n-table table-col" style="table-layout: fixed;">
		<thead>
			<tr>
				<th width="35%">모임정보</th>
				<th width="13%">주문일자</th>
				<th width="20%">주문번호</th>
				<th width="10%">주문금액</th>
				<th width="10%"></th>
				<th width="10%"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="orderHistoryDTO" items="${orderHistoryList}">
			<fmt:parseDate var="parseDate" value="${orderHistoryDTO.meetingboard_day}" pattern="yyyy/MM/dd"/>
			<fmt:formatDate var="meetingday" value="${parseDate}" pattern="yyyy/MM/dd"/>
			<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="nowDays"/>
			<fmt:parseNumber value="${parseDate.time / (1000*60*60*24) + 1 }" integerOnly="true" var="oldDays"/>
			<tr>
				<td> <%-- 모임정보 --%>
					<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${orderHistoryDTO.meetingboard_seq}" target="_blank">
						${orderHistoryDTO.meetingboard_title}
					</a>
				</td>
				<td> <%-- 주문일자 --%>
					<fmt:formatDate value="${orderHistoryDTO.order_date}" pattern="yyyy.MM.dd"/>
				</td>
				<td> <%-- 주문번호 --%>
					<a type="external" href="/mentor/participation/paymentComplete?order_id=${orderHistoryDTO.order_id}" style="color: black;">
						${orderHistoryDTO.order_id}
					</a>
				</td>
				<td> <%-- 주문금액 --%>
					<fmt:formatNumber value="${orderHistoryDTO.meetingboard_price}" pattern="#,###"/>원
				</td>
				<td> <%-- 수강 후기 --%>
				<c:if test="${orderHistoryDTO.order_flag == 0}">
					(주문취소)
				</c:if>
				<c:if test="${orderHistoryDTO.order_flag == 1}">
					<c:if test="${today >= meetingday}">
						<c:if test="${orderHistoryDTO.review_seq == null}">
							<c:if test=""></c:if>
							<div class="btn-set btn-parents">
								<button type="button" class="button" onclick="location.href='/mentor/mentee/meetingReviewWriteForm?seq=${orderHistoryDTO.meetingboard_seq}'" style="font-size: 11px;">수강후기</button>
							</div>
						</c:if>
						<c:if test="${orderHistoryDTO.review_seq != null}">
							<div class="btn-set btn-parents">
								<span>(작성완료)</span>
							</div>
						</c:if>						
					</c:if>
					<c:if test="${today < meetingday}">
					<div class="btn-set btn-parents">
						<span>(수강전)</span>
					</div>
					</c:if>
				</c:if>
				</td>
				<td> <%-- 수강취소 --%>
				<c:if test="${orderHistoryDTO.order_flag == 1}">
					<c:if test="${(oldDays - nowDays) >= 2}">
						<div class="btn-set btn-parents">
							<button type="button" class="button" onclick="paymentCancel('${orderHistoryDTO.meetingboard_seq}','${orderHistoryDTO.order_id}','${orderHistoryDTO.meetingboard_price}','${orderHistoryDTO.participation_seq}')" style="font-size: 11px;">수강취소</button>
						</div>
					</c:if>
					<c:if test="${(oldDays - nowDays) < 2}"> <%-- 취소기간 지난거 --%>
						<div class="btn-set btn-parents">
							<span></span>
						</div>
					</c:if>
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>
</div>
<div class="pagination-block" style="width: 870px; text-align: right;">
	<div class="page-entries-info"></div>
	${orderHistoryPaging.pagingHTML}
</div>
</div>
<script src="../js/order.js"></script>
<script>
$(function(){
	$('#menteeOrderHistory').attr('class', 'list-button color-gray item-link active').css('font-weight', 'bold');
	
	// air datepicker 관련
    $("#startDate").datepicker({
    	language : "ko"
    });
	
    $("#endDate").datepicker({
    	language : "ko"
    });
});
</script>