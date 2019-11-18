<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="page navbar-fixed mentor_requests new" data-name="mentor_requests-new">
  <div class="page-content">
    <div class="block hero-title">
  <div class="text-block">
    <h1 class="title">
      멘토 지원하기
    </h1>

    <p class="description">
      가치 있는 커리어 경험을 공유해 보세요.
    </p>
  </div>
</div>


    <div class="row no-gap new-mentor-block-wrapper">
      <div class="col-100 tablet-50">
        <div class="img-gradient">
          <img src="../image/yang.png">
        </div>
      </div>

      <div class="col-100 tablet-50 text-block-wrapper">
        <div class="block tablet-inset mentor-request-block">
          <form class="simple_form new_mentor_request" id="new_mentor_request" action="/mentor/mentor/mentorapplyWriteForm" method="post">
            <div class="list no-hairlines">
              <ul>
                <li class="item-content item-input" style="margin-bottom: 30px;">
                  <div class="item-inner">
                    <div class="item-input-wrap">
                      <input class="string optional" placeholder="회사명" type="text" name="mentor_company" id="mentor_company">
                    </div>
                      <div id="mentor_company_error"></div>
                  </div>
                </li>

                <li class="item-content item-input" style="margin-bottom: 30px;">
                  <div class="item-inner">
                    <div class="item-input-wrap">
                      <input class="string optional" placeholder="부서" type="text" name="mentor_department" id="mentor_department">
                    </div>
                      <div id="mentor_department_error"></div>
                  </div>
                </li>

                <li class="item-content item-input" style="margin-bottom: 30px;">
                  <div class="item-inner">
                    <div class="item-input-wrap">
                      <input class="string optional" placeholder="직급" type="text" name="mentor_position" id="mentor_position">
                    </div>
                      <div id="mentor_position_error"></div>
                  </div>
                </li>
              </ul>
            </div>
              <input type="button" name="commit" id="mentorapplyForm_btn" value="지원하기" class="btn button button-big button-fill" style=" width: 100%; height: 100%;">
			<input type="hidden" id="email" value="${mentorapplyDTO.mentor_email}">
		</form>        
		</div>
      </div>
    </div>
  </div>
</div>
<script src="../js/mentor.js"></script>