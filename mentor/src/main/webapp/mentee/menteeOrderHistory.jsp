<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="../css/order.css" type="text/css"/>
<div class="col-100 tablet-90" style="margin: auto;">
<div class="block block-strong no-hairlines hero-title" style="width: 815px;">
	<h1 class="title">결제 내역</h1>
</div>
<div class="block" style="width: 830px;">
	<table class="n-table table-col" style="table-layout: fixed;">
		<thead>
			<tr>
				<th width="40%">모임정보</th>
				<th width="15%">주문일자</th>
				<th width="24%">주문번호</th>
				<th width="10%">주문금액</th>
				<th width="12%"></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="orderHistoryDTO" items="${orderHistoryList}">
			<tr>
				<td>
					<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${orderHistoryDTO.meetingboard_seq}" target="_blank">
						${orderHistoryDTO.meetingboard_title}
					</a>
				</td>
				<td>
					<fmt:formatDate value="${orderHistoryDTO.order_date}" pattern="yyyy.MM.dd"/>
				</td>
				<td>
					<a type="external" href="/mentor/participation/paymentComplete?order_id=${orderHistoryDTO.order_id}" style="color: black;">
						${orderHistoryDTO.order_id}
					</a>
				</td>
				<td>
					<fmt:formatNumber value="${orderHistoryDTO.meetingboard_price}" pattern="#,###" />원
				</td>
				<td>
					<div class="btn-set btn-parents">
						<button type="button" class="button" onclick="location.href=''">후기작성</button>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="pagination-block">
	<div class="page-entries-info">

		${orderHistoryPaging.pagingHTML}
	</div>
</div>
</div>
<script>
$(function(){
	$('#menteeOrderHistory').attr('class', 'list-button color-gray item-link active').css('font-weight', 'bold');
});
</script>
