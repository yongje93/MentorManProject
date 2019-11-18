<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

<div class="page navbar-fixed mentee_programs index">
   <div class="page-content">
		<div class="page navbar-fixed open_mentorings index" data-name="open_mentorings-index">
    		<div class="block job-type-block">
      			<div class="block-title">
        			직무 유형
      			</div>
      			<div class="row">
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType">인사/총무/노무</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType">마케팅/MD</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType">홍보/CSR</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType">영업/영업관리</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType">회계/재무/금융</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_5">해외/기술영업</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_6">유통/무역/구매</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_7">전략/기획</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_8">IT개발</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_9">서비스 기획/UI, UX등</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_10">디자인/예술</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_11">미디어</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_12">서비스</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_13">연구/설계</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_14">전문/특수</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_15">교육/상담/컨설팅</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_16">공무원/공공/비영리</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_17">생산/품질/제조</a>
		          <a id="" class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?job_code=job_code_18">기타 사무</a>
      			</div>
      			<script>
      			$(function(){
      				$('.button').on('click' , function(){
      					$(this).addClass("active");
      				});
      			});
      			</script>
    		</div>
    <div class="open-mentoring-block">
    <div class="page navbar-fixed mentor_posts index" data-name="mentor_posts-index">
    	<div class="mentor-post-block">
      		<div class="block-title strong-title">
          		추천 에세이
	          	<a id="essay_write_btn" class="button" type="external" href="/mentor/essayboard/essayboardWriteForm">
	            	<i class="fas fa-pencil-alt"></i>
	           		 글쓰기
				</a>
				<input type="hidden" id="flag" value="${flag }">
	          	<a class="button" id="listflag" type="external" href="javascript:void(0)">
	            	<i class="fas fa-pencil-alt"></i>
	            	추천 에세이
				</a>
      		</div>
			<div class="row no-gap">
			<!-- 멘토 리스트 생성 -->
			<c:forEach var="list" items="${list }">
				<div class="col-100 tablet-50 desktop-33">
  					<div class="card mentor-post-card mentor_post_6589">
  						<div class="card-header">
    						<a class="color-black" type="external" href="/mentor/essayboard/essaymentorHeadView?seq=${list.essayboard_seq }&pg=${pg}&memtors=${list.member_seq }">
      					<div>
        						<div class="mentor-image img-circle">
          							<img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcFlTIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--e9618fbc0c6aff0311a1011821aef9ebeb0c85a4/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/%EC%9D%B4%EB%A0%A5%EC%84%9C%20%EC%82%AC%EC%A7%84.png" />
        						</div>
        						<div class="mentor-info">
					          		<div>
						            	<strong class="mentor-name">${list.member_name }</strong>
						            		<small>멘토</small>
					          		</div>
	          				  		<div class="job">
	            					<small>
	              						${list.mentor_company }
	            					</small>
	          						</div>
        						</div>
      					</div>
							</a>
				      <div class="created-at">
				        <small>${list.essayboard_logtime }</small>
				      </div>
  				</div>

  <div class="card-content card-content-padding"style="overflow: hidden; text-overflow: ellipsis; height: 200px; ">
   <input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
   <a class="text-decoration-underline" type="externalScrap" href="/mentor/essayboard/essayjobType?jobType=${list.job_code }">
      ${list.job_type }
</a>
	<!-- 로그인 했을 경우 View로 넘김 -->
	<c:if test="${memNickname != null }">
    	<a class="content-body" type="external" href="/mentor/essayboard/essaymentorBodyView?pg=${pg }&seq=${list.essayboard_seq}" >
	</c:if>
	<!-- 로그인 안 했을경우 로그인 폼 으로 넘김 -->
	<c:if test="${memNickname == null }">
		<a class="content-body" type="external" href="/mentor/member/loginForm" >
	</c:if>

      <div class="mentor-post-title">
        ${list.essayboard_title }
      </div>

      <div class="mentor-post-detail">
        ${list.essayboard_content }
      </div>
</a>  </div>

  <div class="card-footer" style="">
    <a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6589/bookmarks" style="right: 0px;
    position: unset;
    margin: 0px 0px;">
    <!-- <i class="far fa-bookmark" aria-hidden="false"></i> -->
    <c:if test="${list.essayboard_scrapFlag == 1}">
    <img id="${list.essayboard_seq}" src="../image/scrapOkImg.png" width="13">
    </c:if>
    <c:if test="${list.essayboard_scrapFlag == 0}">
    <img id="${list.essayboard_seq}" src="../image/scrapNoImg.png" width="13">
    </c:if>
	<span id="ScrapDiv_${list.essayboard_seq}">${list.essayboard_scrap}</span>
  	<!-- 스크랩 끌고와야 함 -->
  	<input type="hidden" id="scrapFlag" name="scrapFlag" value="${list.essayboard_scrapFlag}">

	</a>

    <div class="created-at">
      <!-- <small> -->
      <!--   읽음 -->
      <!--   336 -->
      <!-- </small> -->
    </div>
  </div>

</div>

</div>
</c:forEach>

<!-- 멘토 리스트 생성 끝 -->
<input type="hidden" id="pg" name="pg" value="${pg }">
<!-- 스크랩버튼 클릭 방지에 사용 -->
<input type="hidden" id="memNickname" name="memNickname" value="${memNickname }">



        <div class="col-100 tablet-50 desktop-33"></div>
        <div class="col-100 tablet-50 desktop-33"></div>
      </div>

      <div class="pagination-block">
          <div class="page-entries-info">
          </div>
    		${boardpaging.pagingHTML }

  </nav>

      </div>
    </div>
  </div>
</div>
      </div>
    </div>

  </div>
  <script src="../js/essayboardList.js"></script>
