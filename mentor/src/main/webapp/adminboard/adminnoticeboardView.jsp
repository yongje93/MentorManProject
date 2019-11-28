<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="../admincss/adminnoticeboard.css">
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px; overflow: auto;">
			<div class="x_title">
				<h2>멘토맨회원 리스트</h2>
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
						<div class="viewWrap">
							<div class="notice-title">
								<h1>제목 : [공지] ${adminnoticeboardDTO.noticeboard_title }</h1>
							</div>
							<div class="notice-content">
								${adminnoticeboardDTO.noticeboard_content }
							</div>
						</div>
						<!-- view전체 감싸줄거-->
					</div>
				</div>
				<!-- table row-->
				<div class="ln_solid"></div>
				<div class="notice-btn">
					<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-12">
							<button type="button" class="btn btn-info btn-sm">뒤로</button>
							<button type="button" class="btn btn-success btn-sm">수정</button>
							<button type="button" class="btn btn-danger btn-sm">
								<i class="fa fa-trash-o"></i>삭제
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- xcontent -->
		</div>
		<!--x_panel-->
	</div>
</div>
<script src="../adminjs/noticeboard.js"></script>
<!-- row -->