<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
      <title>Memtorman</title>
    <link rel="shortcut icon" type="image/x-icon" href="https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/favicon-8c7fcbb073779bccf8697e126166360bfa96419c0e6f3fb90b53da7fac142625.ico" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body class="color-theme-pink ">
<div class="page navbar-fixed mentee_programs index">
   <div class="page-content">
  <div class="progress-container">
    <div class="progress-bar" id="progressBar"></div>
  </div>
  
</div>
        
<div class="page navbar-fixed open_mentorings index" data-name="open_mentorings-index">
  <div class="page-content">
    <div class="block job-type-block">
      <div class="block-title">
        직무 유형
      </div>

      <div class="row">
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_0">인사/총무/노무</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_1">마케팅/MD</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_2">홍보/CSR</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_3">영업/영업관리</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_4">회계/재무/금융</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_5">해외/기술영업</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_6">유통/무역/구매</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_7">전략/기획</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_8">IT개발</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_9">서비스 기획/UI, UX등</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_10">디자인/예술</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_11">미디어</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_12">서비스</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_13">연구/설계</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_14">전문/특수</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_15">교육/상담/컨설팅</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_16">공무원/공공/비영리</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_17">생산/품질/제조</a>
          <a class="button color-gray " type="external" href="/mentor/essayboard/essayjobType?jobType=job_code_18">기타 사무</a>
      </div>
    </div>

    <div class="open-mentoring-block">
      <div class="popover popover-flat career-popover js-career-popover">
  <div class="popover-inner">
    <div class="popover-angle"></div>

    <div class="menu-list">
      <div class="list links-list no-hairlines-between">
        <ul>
          <li>
            <a class="list-button" type="external" href="/open_mentorings?career=all">
              전체
</a>          </li>

          <li>
            <a type="external" href="/open_mentorings?career=career">
              경력
</a>          </li>

          <li>
            <a type="external" href="/open_mentorings?career=not_career">
              신입
</a>          </li>
        </ul>
      </div>
    </div>
  </div>
</div>

     <div class="page navbar-fixed mentor_posts index" data-name="mentor_posts-index">
  <div class="page-content">
    <div class="mentor-post-block">
      <div class="block-title strong-title">
          추천 에세이
          <a id="essay_write_btn" class="button" type="external" href="/mentor/essayboard/essayboardWriteForm">
            <i class="fas fa-pencil-alt"></i>
            글쓰기
</a>
          <a class="button" type="external" href="/mentor_posts">
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
    <a class="color-black" type="external" href="/mentor/essayboard/essaymentorHeadView?seq=${list.seq }&pg=${pg}">
      <!-- - ---------------------- -->
      <div>
        <div class="mentor-image img-circle">
          <img src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBcFlTIiwiZXhwIjpudWxsLCJwdXIiOiJibG9iX2lkIn19--e9618fbc0c6aff0311a1011821aef9ebeb0c85a4/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/%EC%9D%B4%EB%A0%A5%EC%84%9C%20%EC%82%AC%EC%A7%84.png" />
        </div>
	  <!-- ----------------------- -->
	  
        <div class="mentor-info">
        <!-- ----------------------- -->
          <div>
            <strong class="mentor-name">${list.name }</strong>
            <small>멘토</small>
          </div>
		<!-- ----------------------- -->
		<!-- ----------------------- -->
          <div class="job">
            <small>
              ${list.company }
            </small>
          </div>
          <!-- ----------------------- -->
        </div>
      </div>
</a>
	<!-- ----------------------- -->
      <div class="created-at">
        <small>${list.logtime }</small>
      </div>
      <!-- ----------------------- -->
  </div>

  <div class="card-content card-content-padding">
   <a class="text-decoration-underline" type="external" href="/mentor/essayboard/essayjobType?jobType=${list.job_code }">
      ${list.job_type }
</a>
    <a class="content-body" type="external" href="/mentor/essayboard/essaymentorBodyView?seq=${list.seq }&pg=${pg}">
      <div class="mentor-post-title">
        ${list.title }
      </div>

      <div class="mentor-post-detail">
        ${list.content }
      </div>
</a>  </div>

  <div class="card-footer">
    <a class="color-gray js-bookmark" type="external" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6589/bookmarks">
    <i class="far fa-bookmark" aria-hidden="true"></i>

  	<!-- 스크랩 끌고와야 함 -->
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
<%-- <input type="hidden" id="pg" name="pg" value="${pg }"> --%>


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
</div>
  </body>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="../js/essayboardList.js">
  </script>
</html>