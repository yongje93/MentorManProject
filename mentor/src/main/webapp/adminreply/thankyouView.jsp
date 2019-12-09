<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="../admincss/adminreply.css">

<fmt:formatDate var = "date" value="${reviewDTO.review_date}" pattern="yyyy.MM.dd" />
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px; overflow: auto;">
			<div class="x_title">
				<h2>NO.${reviewDTO.review_seq } &emsp; 모임번호: ${reviewDTO.meetingboard_seq }</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a></li>
					<li><a class="close-link"> <i class="fa fa-close"></i>
					</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<div class="row">
					<div class="col-sm-12">
					<div class="wrap">
        					<div class="box1">
        						<c:if test="${reviewDTO.member_profile != 'profile.jpg'}">
        							<img src="../storage/${reviewDTO.mentee_email}/${reviewDTO.member_profile}" width="100" height="100">
								</c:if>
								<c:if test="${reviewDTO.member_profile == 'profile.jpg'}">
									<img src="../image/profile.jpg" width="100" height="100">
								</c:if>
        					</div>
        					<div class="box2">
            					<div class="child1">
            					<br>
            						멘티 이름 : ${reviewDTO.member_name }
            					</div>
            					 <div class="child2">
            					 	<br>
            					 	멘티 닉네임 : ${reviewDTO.member_nickname }
            					 </div>
            					 <div class="child3">
            					 	<br>
            					 	가입날짜 : ${date }
            					 </div>
        					</div>
    					</div>
    					<div class="wrap">
    					<div style="width: 10%; border-left: 1px solid black;">
    						<div style="font-weight: bold;">댓글 내용</div>
    					</div>
    					<div style="width: 30%; border-right: 1px solid black;">
    					</div>
    					</div>
    					<div class="wrap">
       					   <div class="content">
       					   <br>
       					   		${reviewDTO.review_content }
       					   </div>
    					</div>
					</div>
				</div>
				<!-- table row-->
				<div class="ln_solid"></div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div>
<!-- row -->