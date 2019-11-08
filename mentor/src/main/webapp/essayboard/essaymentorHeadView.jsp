<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="page navbar-fixed mentee_programs index">
	<div class="page-content">
		<div class="view view-main">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="left">
						<a class="logo" type="external" href="/">
						<img src="https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/logo-c99a7cbe11906a7c7a8084fbb47e605c16d0586f068ea095c19efc48f6d087e6.png"/>
						</a>
					</div>
					<form class="searchbar" id="searchbar-autocomplete" action="/searchs">
						<div class="searchbar-inner">
							<div class="searchbar-input-wrap">
								<input type="search" name="q" value="" placeholder="직무, 회사, 멘토, 제목" autocomplete="off">
								<!-- jot_type, mentoring_type, hall_of_fame 별 검색
      -->
								<i class="searchbar-icon"></i>
								<span class="input-clear-button"></span>
							</div>
						</div>
					</form>
					<div class="right">
						<a class="button button-big" type="external" href="/mentors">
						멘토 찾기 </a>
						<a class="button button-big" type="external" href="/open_mentorings">
						콘텐츠 </a>
						<a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">
						에세이 </a>
						<div class="beta-div">
							<a class="button button-big program-button" type="external" href="/mentee_programs">
							모임 <span class='beta-text'>beta</span>
							</a>
						</div>
						<a class="button button-big beta-div" type="external" href="/mentor_requests/new">
						멘토 지원하기 </a>
						<a type="internal" class="button button-big popover-open me-profile" data-popover=".js-me-popover" href="#">
						<img width="28" height="28" src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBeVFFQWc9PSIsImV4cCI6bnVsbCwicHVyIjoiYmxvYl9pZCJ9fQ==--bea6cc7ebceaaee3ca5cdf29013fea0d340aced3/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg"/>
						</a>
					</div>
				</div>
				<div class="progress-container">
					<div class="progress-bar" id="progressBar">
					</div>
				</div>
			</div>
			<div class="page navbar-fixed mentors show" data-name="mentors-show">
				<div class="page-content">
					<div class="block mentor-info-block">
						<div class="mentor-image-left img-circle">
							<img width="150" height="150" src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBaFVaIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--061e0d293e7c26ff035c44e62dc362907ab020b0/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTXpBd2VETXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--335722d0477df6beb4db54fcbab426022c0d08ed/KakaoTalk_20180416_212036888.jpg"/>
						</div>
						<div class="block mentor-info mentor-info-box">
							<div class="name">
								<span class="mentor-name profile-name">
								강승훈 <small>멘토</small>
								</span>
							</div>
							<div class="job">
								 현대홈쇼핑 · 고객보호팀
							</div>
							<div class="detail-block">
								<div class="mentoring-info">
									<div class="mentoring-type-block">
										<div class="chip chip-outline no-border-radius mentor-index">
											<div class="chip-label">
												<span>답변율 <strong class="highlight">90%</strong></span>
											</div>
										</div>
										<div class="chip chip-outline no-border-radius mentor-index">
											<div class="chip-label">
												<span>답변수 <strong class="highlight">27건</strong></span>
											</div>
										</div>
										<div class="chip chip-outline no-border-radius mentor-index">
											<div class="chip-label">
												<span>뱃지 <strong class="highlight">17개</strong></span>
											</div>
										</div>
										<div class="chip chip-outline no-border-radius mentor-index">
											<div class="chip-label">
												<span>팔로워 <strong class="highlight">94명</strong></span>
											</div>
										</div>
									</div>
								</div>
								<div class="mentoring-info">
									<div class="title text-decoration-underline">
										 대표 멘토링 분야
									</div>
									<div class="mentoring-type-block">
										 핀테크/이커머스/창업/취업/자소서/면접/대외활동/스타트업/외국계/마케팅/전략기획/인하우스/에이전시/
									</div>
								</div>
								<div class="mentoring-info">
									<div class="title">
										 직무 유형
									</div>
									<div class="mentoring-type-block">
										<a type="external" href="/mentors?job_type%5B%5D=7">
										<div class="chip chip-outline no-border-radius job-tags">
											<div class="chip-label">
												 서비스 기획/UI, UX등
											</div>
										</div>
										</a>
									</div>
								</div>
								<div class="mentoring-info">
									<div class="title">
										 멘토링 분야
									</div>
									<div class="mentoring-type-block">
										<a type="external" href="/mentors?mentoring_type%5B%5D=1">
										<div class="chip chip-outline no-border-radius job-tags">
											<div class="chip-label">
												 직무
											</div>
										</div>
										</a>
									</div>
								</div>
								<div class="btn-container">
									<div class="profile-btn">
										<a class="button col js-bookmark user_14134" data-params="followed_id=14134" data-disable-with="..." type="external" data-remote="true" rel="nofollow" data-method="post" href="/relationships">
										팔로우 </a>
									</div>
									<div class="profile-btn">
										<a class="button button-fill" type="external" href="/mentors/554/questions/new">
										질문하기 </a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="block block-strong mentor-detail-block">
						<div class="block-title">
							 멘토 소개
						</div>
						<div class="block">
						</div>
						<div class="block-title">
							 주요 경력
						</div>
						<div class="block">
						</div>
						<div class="block-title">
							 기타 사항
						</div>
						<div class="block">
						</div>
					</div>
					<div class="block section thanks-block">
						<div class="title-wrap">
							<div class="block-title strong-title">
								 고맙습니다
							</div>
							<div class="count">
								 (18)
							</div>
						</div>
						<div class="row no-gap">
							<div id='thanks-notes'>
								<div class="block mentee-detail-block thanks-note-card">
									<div class="mentee-info">
										<div class="mentee-image img-circle">
											<img src="https://d2ljmlcsal6xzo.cloudfront.net/assets/fallback/temporary_profile-65c08fd0b2bb95434e40fa62b682df18417765c3b0ac165dcb5b3e9035f01b98.png"/>
										</div>
										<div class="mentee-name">
											 김경은
										</div>
										<div class="sent-date">
											 2019년 5월 2일
										</div>
									</div>
									<div class="thanks-note-body">
										 감사합니다!! 참고해서 제것도 만들어보려고 합니다 감사합니다!
									</div>
								</div>
							</div>
						</div>
						<div class="text-align-center">
							<button class="button load-more" id="loadMoreThanks">더 보기</button>
						</div>
					</div>
					<div class="block mentor-post-block section">
						<div class="title-wrap">
							<div class="block-title strong-title">
								 에세이
							</div>
							<div class="count">
								 (19)
							</div>
						</div>
						<div class="row no-gap">
							<div class="col-100 tablet-100 desktop-100">
								<div class="card mentor-post-card mentor_post_6385 shadow-card">
									<div class="card-content card-content-padding profile-card">
										<a class="content-body" type="external" href="/mentor_posts/6385">
										<div class="mentor-post-title">
											 [직무탐험대] 직무를 배우는 4가지 방법
										</div>
										<div class="mentor-post-detail">
											 취업준비에 앞서 가장 어려움을 겪는 부분이 바로 직무 이해인데요, 취준생들이 주로 사용하는 4가지 접근 채널에 대해 효과적인 활용 방법을 소개하고자 합니다. 1. 서적 : 최소 관련 서적 5권은 읽자 - 현업에 있는 사람들 조차 자신의 업무와 관련된 책 3권 이상 읽어본 사람을 찾기가 어렵습니다. 때문에 여러분들께서 관심 있는 직무, 산업, ...
										</div>
										</a>
									</div>
									<div class="card-footer">
										<a class="color-gray js-bookmark" type="external" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6385/bookmarks">
										<i class="far fa-bookmark" aria-hidden="true"></i>
										10 </a>
										<div class="created-at">
											<small>
											2019년 3월 12일 </small>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="text-align-center load-more-container">
							<button class="button load-more" id="loadMorePost">더 보기</button>
						</div>
						<div style="margin-top: 50px; margin-bottom: 100px;">
               				<input type="button" onclick="location.href='/mentor/essayboard/essayboardList'" value="목록" class="btn button button-big button-fill" style="line-height: 0px;">
           				</div>
					</div>
				</div>
			</div>
			<script>
			  let currentPage = 1;
			  const lastPage = 2
			  $(function () {
			    $('.open-mentoring-card').prop('hidden', true).slice(0, 2).show();
			    $("#loadMoreContents").on('click', function (e) {
			        e.preventDefault();
			        $(".open-mentoring-card:hidden").slice(0, 2).slideDown();
			        if ($(".open-mentoring-card:hidden").length == 0) {
			            $("#loadMoreContents").fadeOut('slow');
			        }
			    });
			  });
			  $(function () {
			    $('.mentor-post-card').prop('hidden', true).slice(0, 2).show();
			    $("#loadMorePost").on('click', function (e) {
			        e.preventDefault();
			        $(".mentor-post-card:hidden").slice(0, 2).slideDown();
			        if ($(".mentor-post-card:hidden").length == 0) {
			            $("#loadMorePost").fadeOut('slow');
			        }
			    });
			  });
			  function toggleThanksNotesText(e){
			    e.preventDefault();
			    $(this).hide();
			    $(this).prev('span.elipsis').fadeToggle(500);
			  }
			  function hideLongThanksNotesText(element){
			    const body = element.text();
			    if (body.length > 300) {
			      element.html(body.substr(0, 286) + '<span class="elipsis">' + body.substr(286) + '</span><a class="elipsis" href="#"><span class=gray>...</span> 더 보기</a>');
			    }
			  }
			  $(function () {
			    $('.thanks-note-card').prop('hidden', true).slice(0, 3).show();
			    $("#loadMoreThanks").on('click', function (e) {
			        e.preventDefault();
			        if ($(".thanks-note-card:hidden").length == 0) {       
			          $.ajax({
			            type: "GET",
			            url: "https://www.itdaa.net/mentors/14134/thanks",
			            data: { page: currentPage + 1 },
			            contentType: "application/json",
			            success: function(result){
			              currentPage += 1;
			              const final = $(result).each(function(){
			                hideLongThanksNotesText($(this).find('.thanks-note-body'));
			              });
			              final.on('click', '.thanks-note-body > a.elipsis', toggleThanksNotesText);
			              final.prop('hidden', true).appendTo('#thanks-notes');
			              $(".thanks-note-card:hidden").slice(0, 3).slideDown();
			            }
			          });
			        } else {
			          $(".thanks-note-card:hidden").slice(0, 3).slideDown();
			          if($(".thanks-note-card:hidden").length == 0 && lastPage <= currentPage){
			            $("#loadMoreThanks").fadeOut('slow');
			          }
			        }
			    });
			  });
			  $('.thanks-note-body').each(function(){
			    hideLongThanksNotesText($(this));
			  });
			  $('.thanks-note-body > a.elipsis').click(toggleThanksNotesText);
			</script>
		</div>
	</div>
</div>
</body>
</html>