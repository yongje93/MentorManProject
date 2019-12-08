<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="../admincss/adminreply.css">

<fmt:formatDate var = "date" value="${menteeboardReplyDTO.menteeboardReply_logtime}" pattern="yyyy.MM.dd" />
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px; overflow: auto;">
			<div class="x_title">
				<h2>NO.&emsp;${menteeboardReplyDTO.menteeboardReply_seq }</h2>
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
        						<c:if test="${menteeboardReplyDTO.member_profile != 'profile.jpg'}">
        							<img src="../storage/${menteeboardReplyDTO.menteeboardReply_email}/${menteeboardReplyDTO.member_profile}" width="100" height="100" style="border-radius: 50%;">
								</c:if>
								<c:if test="${menteeboardReplyDTO.member_profile == 'profile.jpg'}">
									<img src="../image/profile.jpg" width="100" height="100" style="border-radius: 50%;">
								</c:if>
        					</div>
        					<div class="box2">
            					<div class="child1">
            						멘티 이름 : ${menteeboardReplyDTO.member_name }<br><br>
            						멘티 닉네임 : ${menteeboardReplyDTO.member_nickname }<br><br>
            						가입날짜 : ${date }
            					</div>
        					</div>
    					</div>
    						<div style="margin-left:200px;">댓글 내용</div>
    						<br>
    					<div class="content_wrap">
       					   	<div class="content">
       					   		${menteeboardReplyDTO.menteeboardReply_content }
       					   </div>
    					</div>
					</div>
				</div>
				<!-- table row-->
				<div class="ln_solid"></div>
			</div>
			<!-- xcontent -->
		</div>
		<!--x_panel-->
	</div>
</div>
<!-- row -->