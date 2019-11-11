<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="../css/order.css" type="text/css"/>

<div class="page navbar-fixed mentee_programs index">
	<div class="page-content">
		<div class="block-title strong-title">모임 바구니</div>
		<div id="orderContainer">
			<div id="order_main">
				<div class="columns">
					<div class="column is-8">
						<div class="box product_item_list" id="order_carts">
							<c:set var="total" value="0"/>
							<c:forEach var="participationDTO" items="${participationList}">
							<div class="product_item_container">
								<div class="columns is-mobile product_item">
									<div class="column is-3 thumbnail_container">
										<div class="image is_thumbnail">
											<img src="../image/job_code/${participationDTO.job_code}.jpg">
										</div>
									</div>
									<div class="column content_container">
										<div class="product_title">
											<a type="external" href="/mentor/meetingboard/meetingboardView?seq=${participationDTO.meetingboard_seq}" target="_blank" style="color: black; font-size: 18px; ">
												${participationDTO.meetingboard_title}
											</a>
										</div>
										<div class="product_meta">
											<div class="product_amount">
												<span><fmt:formatNumber value="${participationDTO.meetingboard_price}" pattern="#,###"/>원</span>
											</div>
											<div class="is-right is-grouped-right">
												<button class="button is-small remove_from_cart" onclick="orderRemove(${participationDTO.participation_seq})">
													<span>모임 삭제</span>
												</button>
											</div>
											<c:set var="total" value="${total + participationDTO.meetingboard_price}"/>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
					<div class="column is-4">
						<form class="card checkout_form">
							<div class="card-content">
								<div class="total_amount_container" id="order_total_price">
									<div class="total_amount">
										<h2>
											총계<span><fmt:formatNumber value="${total}" pattern="#,###"/>원</span>
										</h2>
									</div>
								</div>
								<div class="list form-list no-hairlines">
									<ul>
										<div class="label-title">
											<label class="string required" for="title">이름</label>
										</div>
										<li class="item-content item-input">
											<div class="item-inner">
												<input type="text" name="mentee_name" id="mentee_name" placeholder="이름을 입력하세요" value="${memDTO.member_name}">
												<div id="mentee_nameDiv"></div>
											</div>
										</li>
										<div class="label-title">
											<label class="string required" for="title">전화번호(-제외 숫자만)</label>
										</div>
										<li class="item-content item-input">
											<div class="item-inner">
												<input type="text" name="mentee_tel" id="mentee_tel" placeholder="전화번호를 입력하세요">
												<div id="mentee_telDiv"></div>
											</div>
										</li>
										<div class="label-title">
											<label class="string required" for="title">이메일 주소</label>
										</div>
										<li class="item-content item-input">
											<div class="item-inner">
												<input type="text" name="mentee_email" id="mentee_email" placeholder="이메일 주소를 입력하세요" value="${memDTO.member_email}">
												<div id="mentee_emailDiv"></div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="card-footer">
								<input type="button" id="orderBtn" value="결제하기" class="btn button button-big button-fill">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/order.js"></script>