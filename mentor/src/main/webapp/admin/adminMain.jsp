<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!-- 관리자페이지 메인화면 입니다 -->
<!DOCTYPE html>
<html lang="ko" class="fa-events-icons-ready">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="../admincss/bootstrap.min.css">
<link rel="stylesheet" href="../admincss/adminMain.css">
<link rel="stylesheet" href="../admincss/reset.css">
<script src="https://kit.fontawesome.com/70114e98dc.js" crossorigin="anonymous"></script>
<script src="../adminjs/bootstrap.min.js"></script>
<script src="../adminjs/adminMain.js"></script>
<title>mentorMan AdminPage</title>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- 왼쪽 메뉴 전체-->
			<div class="col-md-3 left_col">
				<div class="left_col">
					<div class="navbar nav_title" style="border: 0;">
						<a href="" class="site_title"> <i class="fa fa-paw"></i><span>관리자페이지</span></a>
					</div>
					<div class="clearfix"></div>
					<!-- menu prile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src=""class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>안녕하세요!</span>
							<h2></h2>
						</div>
					</div> <!-- 프로필 -->
					
					<!-- /menu prile quick info -->
					<br>
					<!-- sidebar menu -->
					<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>관리자님</h3>
							<ul class="nav side-menu">
								<li class=""><a><i class="fa fa-user"></i>회원관리<span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none;">
										<li><a href="/mentor/adminmember/adminmemberList">모든회원 리스트</a></li>
										<li><a href="/mentor/adminmember/adminmentoList">멘토 리스트</a></li>
										<li><a href="/mentor/adminmember/adminmenteeList">멘티 리스트</a></li>
									</ul>
								</li>
								<li class=""><a><i class="fas fa-clipboard-list"></i>게시판관리<span class="fa fa-chevron-down"></span></a>
									<ul class="nav child_menu" style="display: none;">
										<li><a href="/mentor/adminboard/adminnoticeboardList">공지사항 게시판</a></li>
										<li><a href="/mentor/adminboard/adminessayList">에세이</a></li>
										<li><a href="/mentor/adminboard/admincommuList">자유게시판</a></li>
										<li><a href="/mentor/adminboard/admingroupList">모임</a></li>
									</ul>
								</li>
							</ul>
						</div>
					</div> <!-- sidebar menu 끝-->
					<div class="sidebar-footer hidden-small"></div>
				</div>
			</div> <!-- 왼쪽메뉴 전체 -->
			<!--메뉴 -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"></a> <span class=" fa fa-angle-down"></span>

								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href=""><i class="fa fa-sign-out pull-right"></i>
											Log Out</a></li>
								</ul></li>
						</ul>

					</nav>
				</div>
			</div>
			<!--상단메뉴-->
			<div class="right_col" role="main" style="min-height: 765px;">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Index Page</h3>
						</div>

						<div class="title_right"></div>
					</div>
					<div class="clearfix"></div>
					<jsp:include page="${display}" />
				</div>
			</div>
			<!-- 내용 들어가는곳-->
			<footer>
				<div class="pull-right">
					Developed by <a href="">MenTorMan</a>
				</div>
				<div class="clearfix"></div>
			</footer>
		</div>
	</div>
</body>
</html>