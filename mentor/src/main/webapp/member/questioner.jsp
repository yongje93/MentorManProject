<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="color-theme-pink ">
<div class="page navbar-fixed mentee_programs index">
   <div class="page-content">
   		<div id="app" class="framework7-root">
    <!-- <div id="app"> -->
      <div class="panel panel-right panel-cover">
  <!-- <div class="navbar no&#45;hairline"> -->
  <!--   <div class="navbar&#45;inner"> -->
  <!--     <div class="left"> -->
  <!--       <a class="logo" type="external" href="/"> -->
  <!--         <img src="https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/logo-c99a7cbe11906a7c7a8084fbb47e605c16d0586f068ea095c19efc48f6d087e6.png" /> -->
  <!--       </a> -->
  <!--     </div> -->
  <!--   </div> -->
  <!-- </div> -->

  <div class="menu-list">
  <div class="list links-list no-hairlines-between">
    <ul>
      <li>
        <a type="external" href="/mentors">
          멘토 찾기
</a>      </li>

      <li>
        <a type="external" href="/open_mentorings">
          콘텐츠
</a>      </li>

      <!-- <li> -->
      <!--   <a type="external" href="/programs"> -->
      <!--     오프라인 멘토링 -->
      <!--   </a> -->
      <!-- </li> -->

      <li>
        <a type="external" href="/mentor_posts">
            에세이
</a>      </li>

      <li>
        <a type="external" href="/mentee_programs">
          <span>
            모임
          </span>
          <span class="beta-text">beta</span>
          <span class="badge color-red alim">18</span>
</a>      </li>

        <li>
          <a type="external" href="/mentor_requests/new">
            멘토 지원하기
</a>        </li>
    </ul>
  </div>

    <div class="list links-list no-hairlines-between">
      <ul>
        <li>
          <a type="external" href="/me/mentor_posts">
            에세이 쓰기
</a>        </li>

          <li>
            <a type="external" href="/questioner_qa_threads">
              나의 질문/답변
</a>          </li>


        <li>
          <a type="external" href="/bookmarks">
            관심 콘텐츠
</a>        </li>

        <li>
          <a type="external" href="/relationships">
            관심 멘토
</a>        </li>
      </ul>
    </div>

  <div class="list links-list no-hairlines-between">
    <ul>

        <li>
          <a type="external" href="/settings/account">
            계정 설정
</a>        </li>

        <li>
          <a type="external" rel="nofollow" data-method="delete" href="/users/sign_out">
            로그아웃
</a>        </li>
    </ul>
  </div>
</div>

</div>


      <div class="view view-main">
        <div class="navbar">
  <div class="navbar-inner">
    <div class="left">
      <a class="logo" type="external" href="/">
        <img src="https://d2ljmlcsal6xzo.cloudfront.net/assets/icons/logo-c99a7cbe11906a7c7a8084fbb47e605c16d0586f068ea095c19efc48f6d087e6.png" />
</a>    </div>

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
        멘토 찾기
</a>
      <a class="button button-big" type="external" href="/open_mentorings">
        콘텐츠
</a>
      <a class="button button-big" type="external" href="/mentor_posts?featured_mentor_post=true">
        에세이
</a>
      <div class="beta-div">
        <a class="button button-big program-button" type="external" href="/mentee_programs">
          모임
          <span class='beta-text'>beta</span>
</a>      </div>

        <a class="button button-big beta-div" type="external" href="/mentor_requests/new">
          멘토 지원하기
</a>

        <a type="internal" class="button button-big popover-open me-profile" data-popover=".js-me-popover" href="#">
          <img width="28" height="28" src="https://www.itdaa.net/rails/active_storage/representations/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBeVFFQWc9PSIsImV4cCI6bnVsbCwicHVyIjoiYmxvYl9pZCJ9fQ==--bea6cc7ebceaaee3ca5cdf29013fea0d340aced3/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaDdCem9MY21WemFYcGxTU0lOTVRBd2VERXdNQ0VHT2daRlZEb1FZWFYwYjE5dmNtbGxiblJVIiwiZXhwIjpudWxsLCJwdXIiOiJ2YXJpYXRpb24ifX0=--80976855d44dd57bc27b6da84ad9dae42a7e7a2d/profile.jpg" />
</a>    </div>
  </div>

  <div class="progress-container">
    <div class="progress-bar" id="progressBar"></div>
  </div>
  
</div>
        
<div class="page navbar-fixed questioner_qa_threads index" data-name="questioner_qa_threads-index">
  <div class="page-content">
    <div class="qa-thread-block">

      <div class="block top-block">
        <h1 class="title">
          질문 및 답변
        </h1>

        <div class="block-footer">
          로켓은 질문권을 의미합니다. 최초 3개가 충전되며, 멘토에게 답변을 받고 고맙습니다를 작성하시면 1개씩 자동 충전됩니다.
        </div>

        <div class="block-footer">
          <div class="stat-info-block">
            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>로켓 <strong class="highlight">3개</strong></span>
              </div>
            </div>

            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>질문수 <strong class="highlight">-개</strong></span>
              </div>
            </div>

            <div class="chip chip-outline no-border-radius">
              <div class="chip-label">
                <span>질문뱃지 <strong class="highlight">-개</strong></span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="row">
        
      </div>
    </div>
  </div>
</div>
      </div>
    </div>


   </div>
</div>
</body>
</html>