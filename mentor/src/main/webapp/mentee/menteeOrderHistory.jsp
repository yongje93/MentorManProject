<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 오늘 날짜 --%>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="today" value="${now}" pattern="yyyy/MM/dd"/>

<link rel="stylesheet" href="../css/order.css" type="text/css"/>
<div class="col-100 tablet-90" style="margin: auto;">
<div class="block block-strong no-hairlines hero-title" style="width: 815px;">
	<h1 class="title">결제 내역</h1>
</div>
<div class="block" style="width: 840px;">
<form id="menteeOrderForm">
	<table class="n-table table-col" style="table-layout: fixed;">
		<thead>
			<tr>
				<th width="35%">모임정보</th>
				<th width="13%">주문일자</th>
				<th width="20%">주문번호</th>
				<th width="9%">주문금액</th>
				<th width="11%"></th>
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
					<c:if test="${today >= meetingday}">
						<c:if test="${orderHistoryDTO.review_seq == null}">
							<div class="btn-set btn-parents">
								<button type="button" class="button" onclick="location.href='/mentor/mentee/meetingReviewWriteForm?seq=${orderHistoryDTO.meetingboard_seq}'" style="font-size: 11px;">수강후기</button>
							</div>
						</c:if>
						<c:if test="${orderHistoryDTO.review_seq != null && orderHist}">
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
				</td>
				<td> <%-- 수강취소 --%>
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
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</form>
</div>
<div class="pagination-block" style="width: 815px; text-align: right;">
	<div class="page-entries-info"></div>
	${orderHistoryPaging.pagingHTML}
</div>
</div>
<script>
$(function(){
	$('#menteeOrderHistory').attr('class', 'list-button color-gray item-link active').css('font-weight', 'bold');
});
</script>