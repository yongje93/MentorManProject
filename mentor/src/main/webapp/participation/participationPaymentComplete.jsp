<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="../css/order.css" type="text/css" />

<div class="page navbar-fixed mentee_participations">
	<div class="page-content">
		<div class="block-title strong-title">결제 완료</div>
		<div id="orderContainer">
			<div id="order_main">
				<div class="columns">
					<div class="column is-10">
						<div class="box product_item_list" id="order_carts">
							<c:set var="order_price" value="0"/>
							<c:set var="order_date" value=""/>
							<c:forEach var="orderDTO" items="${orderList}">
								<c:set var="order_price" value="${orderDTO.order_price}" />
								<c:set var="order_date" value="${orderDTO.order_date}"/>
								<input type="hidden" name="meetingboard_seq"
									value="${orderDTO.meetingboard_seq}">
								<input type="hidden" name="participation_seq"
									value="${orderDTO.participation_seq}">
								<div class="product_item_container">
									<div class="columns is-mobile product_item">
										<div class="column is-3 thumbnail_container">
											<div class="image is_thumbnail">
												<img src="../image/job_code/${orderDTO.job_code}.jpg">
											</div>
										</div>
										<div class="column content_container">
											<div class="product_title">
												<a type="external"
													href="/mentor/meetingboard/meetingboardView?seq=${orderDTO.meetingboard_seq}"
													target="_blank" style="color: black; font-size: 18px;">
													${orderDTO.meetingboard_title} </a>
											</div>
											<div class="product_meta">
												<div class="product_amount">
													<span><fmt:formatNumber
															value="${orderDTO.meetingboard_price}" pattern="#,###" />원</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="column is-10">
							<div class="box product_item_list" id="order_carts">
								<div class="product_item_container">
									<div class="columns is-mobile product_item">
										<div class="column is-3 thumbnail_container">
											<div class="image is_thumbnail">주문번호</div>
										</div>
										<div class="column content_container">
											<div class="product_title">${order_id}</div>
										</div>
									</div>
								</div>
								<div class="product_item_container">
									<div class="columns is-mobile product_item">
										<div class="column is-3 thumbnail_container">
											<div class="image is_thumbnail">주문일자</div>
										</div>
										<div class="column content_container">
											<div class="product_title">
												<fmt:formatDate value="${order_date}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
											</div>
										</div>
									</div>
								</div>
								<div class="product_item_container">
									<div class="columns is-mobile product_item">
										<div class="column is-3 thumbnail_container">
											<div class="image is_thumbnail">주문금액</div>
										</div>
										<div class="column content_container">
											<div class="product_title">
												<span><fmt:formatNumber value="${order_price}" pattern="#,###"/>원</span>
											</div>
										</div>
									</div>
								</div>
								<div style="float: right; margin-top: 5px;">
									<button class="button" style="display: inline-block;">
										<a type="external" href="/mentor/main/index">홈으로 가기</a>
									</button>
									<button class="button" id="meetingboardDeleteBtn" style="display: inline-block;">
										<a type="external" href="">결제내역</a>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>