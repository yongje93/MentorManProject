<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
    <link rel="stylesheet" media="all" href="https://d2ljmlcsal6xzo.cloudfront.net/assets/application-ec82e4fd3581863fb7280ad4cb1183138cef57405f46a2d44eb51efb8a40a133.css" data-turbolinks-track="reload" />
	<!-- 스크랩 이미지 -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

<div class="page navbar-fixed mentor_posts index" style="border: 1px solid red;">
	<div class="page-content">
		<div class="view view-main">
			<div class="page navbar-fixed mentor_posts show" data-name="mentor_posts-show">
				<div class="page-content">
						
					<div class="post-block-container" style="border: 1px solid blue;">
						
						
						<div class="mentor-post-detail-block">
							<h1 class="mentor-post-title" style="display : flex">
								${essayboardDTO.essayboard_title} 
								</h1>
							<div class="trix-content">
								${essayboardDTO.essayboard_content }
							</div>
							
							<div class="block button-wrap">
							<div style="display: inline-block;">
							<a id="essay_write_btn" style="width: 100px; float: right;" class="button" type="external" href="/mentor/essayboard/essayboardDelete?seq=${seq }">
		            			<i class="fas fa-pencil-alt"></i>
		            			글삭제
		            		</a>
							<a id="essay_write_btn" style="width: 100px; float: right;" class="button" type="external" href="/mentor/essayboard/essayboardModifyForm?seq=${seq }&pg=${pg}">
			            		<i class="fas fa-pencil-alt"></i>
			            			글수정
							</a>
						</div>
								<div class="action-block text-align-center mentor_post_6618">
									<a class="color-gray" type="external" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6618/bookmarks">
									<div class="block button button-big button-inline color-gray js-bookmark">
										<i class="far fa-bookmark" aria-hidden="true"></i>
										${essayboardDTO.essayboard_scrap }
									</div>
									</a>
								</div>
								
								
							</div>
						</div>
						<div class="mentor-block block block-strong">
							<div class="block mentor-info-block">
								<div class="mentor-image img-circle">
									<a type="external" href="/mentors/14134">
									<!-- 멘토 이미지 -->
									<img width="100" height="100" src=""/>
									</a>
								</div>
								<div class="block mentor-info">
									<div class="name">
										<span class="mentor-name">
										${essayboardDTO.member_name } <small>멘토</small>
										</span>
										<a class="button col js-bookmark user_14134" data-params="followed_id=14134" data-disable-with="..." type="external" data-remote="true" rel="nofollow" data-method="post" href="/relationships">
										팔로우 </a>
										<a class="button button-small button-fill" type="external" href="/mentors/14134/questions/new">
										질문하기 </a>
									</div>
									<div class="job">
										 현대홈쇼핑 · 고객보호팀
									</div>
									<div class="detail-block">
										<div class="mentoring-info">
											<div class="title text-decoration-underline">
												 대표 멘토링 분야
											</div>
											<div class="mentoring-type-block">
												 ${essayboardDTO.mentor_represent }
											</div>
										</div>
										<div class="mentoring-info">
											<div class="title">
												 멘토링 분야
											</div>
											<div class="mentoring-type-block">
												<a type="external" href="/mentors?job_type%5B%5D=7">
												<div class="chip chip-outline no-border-radius">
													<div class="chip-label">
														 ${essayboardDTO.job_type }
													</div>
												</div>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div style="margin-top: 50px; margin-bottom: 100px;">
               				<input type="button" onclick="location.href='/mentor/essayboard/essayboardList'" value="목록" class="btn button button-big button-fill" style="line-height: 0px;">
           				</div>
					</div>
				</div>
				<script type='text/javascript'>
					  $(document).on('click', '.js-kakao-share-btn', function(e) {
					    Kakao.Link.sendCustom({
					      templateId: 13821,
					      templateArgs: {
					        'title': '[취업 멘탈 살롱] 취준 중 멘붕, 나는 이렇게 극복했다',
					        'description': '취업은 철저하게 멘탈과 인내의 싸움입니다.한 번에 취업이 된다면 좋겠지만 한 시즌에 취업하기란 요즘 같은 세상엔 찾아보기 힘든 사례이기도 합니다.자소서와 면접 등 취업과 관련된 다양',
					        'image': 'https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/brand_og-ba663d36a81305d2c0ed38e9568b24dcc49ec4d0914eece5c0baa0265815f717.jpg',
					        'path': '/mentor_posts/6618',
					      }
					    });
					  });
					  $(document).on('click', '.js-facebook-share-btn', function(e) {
					    FB.ui({
					      method: 'share',
					      href: 'https://www.itdaa.net/mentor_posts/6618',
					    }, function(response){});
					  });
				</script>
			</div>
		</div>
	</div>
</div>