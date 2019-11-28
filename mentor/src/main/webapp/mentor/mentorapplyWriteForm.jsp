<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<div class="page navbar-fixed mentor_request-steps show" data-name="mentor-request-steps-show">
  <div class="page-content">

    <div class="block no-hairlines text-align-center hero-title">
      <h1>
        기본 정보
      </h1>
    </div>

    <div class="mentor-request-block block">
     
        <form id="mentorapplyWriteForm" name="mentorapplyWriteForm" method="post" enctype="multipart/form-data" action="/mentor/mentor/mentorapplyWrite">
          <div class="block-title">
            <label class="string required" for="mentor_request_user_attributes_name">이름 <abbr title="required">*</abbr></label>
          </div>
          <div class="list inset no-hairlines">
            <ul>
              <li class="item-content item-input">
                <div class="item-inner">
                  <div class="item-input-wrap">
                    <input class=" is-valid string required" placeholder="이름" type="text" value="${memberDTO.member_name}" readonly="readonly" name="mentor_name" id="mentor_name">
                  </div>
                    <div id="mentor_name_error"></div>
                </div>
              </li>
            </ul>
          </div>

        <div class="block-title">
          <label class="string required" for="mentor_request_organization">회사명 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string optional" placeholder="회사명" type="text" value="${mentor_company}" name="mentor_company" id="mentor_company">
                </div>
                  <div id="mentor_company_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="string required" for="mentor_request_department">부서 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string optional" placeholder="부서" type="text" value="${mentor_department}" name="mentor_department" id="mentor_department">
                </div>
                  <div id="mentor_department_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="string required" for="mentor_request_position">직급 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string optional" placeholder="직급" type="text" value="${mentor_position}" name="mentor_position" id="mentor_position">
                </div>
                  <div id="mentor_position_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="string required" for="mentor_request_job_type">직무 유형 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap input-dropdown-wrap">
                  <select class="select optional" name="job_code" id="job_code">
                     <option value="0">직무를 선택하세요</option>
                     <option value="job_code_0">인사/총무/노무</option>
                     <option value="job_code_1">마케팅/MD</option>
                     <option value="job_code_2">홍보/csr</option>
                     <option value="job_code_3">영업/영업관리</option>
                     <option value="job_code_4">회계/재무/금융</option>
                     <option value="job_code_5">해외/기술영업</option>
                     <option value="job_code_6">유통/무역/구매</option>
                     <option value="job_code_7">전략/기획</option>
                     <option value="job_code_8">IT개발</option>
                     <option value="job_code_9">서비스 기획/UI/UX 등</option>
                     <option value="job_code_10">디자인/예술</option>
                     <option value="job_code_11">미디어</option>
                     <option value="job_code_12">서비스</option>
                     <option value="job_code_13">연구/설계</option>
                     <option value="job_code_14">전문/특수</option>
                     <option value="job_code_15">교육/상담/컨설팅</option>
                     <option value="job_code_16">공무원/공공/비영리</option>
                     <option value="job_code_17">생산/품질/제조</option>
                     <option value="job_code_18">기타 사무</option>                              
                 </select>
              </div>
              <div id="mentor_job_code_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="string optional" for="mentor_request_school_name">학교</label>
          <small class="form-text text-muted">통계 목적으로 사용되며 회원들에게 공개되지 않습니다.</small>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string optional" type="text" name="mentor_school" id="mentor_school">
                </div>
                  <div id="mentor_school_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="text required" for="mentor_request_career_experience">주요 경력 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <textarea class=" is-valid text optional" name="mentor_career" id="mentor_career"></textarea>
                </div>
                  <div id="mentor_career_error"></div>
              </div>
            </li>
          </ul>

          <div class="block-footer">
            예시) 현) 한국자동차 해외영업팀 과장<br>현) 직장인 와인동호회 부회장<br>전) 대한자동차 해외영업팀 대리<br>
          </div>
        </div>
        
        
            <div class="block no-hairlines text-align-center hero-title">
      <h1>
        멘토링 분야
      </h1>
    </div>

    <div class="mentor-request-block block">
        <div class="block-title">
          <label class="string required" for="mentor_request_mentoring_type">멘토링 가능 분야 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset mentoring-type-block">
          <ul>
            <div class="row">
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_0" class="mentoring_code" value="mentoring_code_0"  checked="checked">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">직무</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_1" class="mentoring_code" value="mentoring_code_1">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">진로</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_2" class="mentoring_code" value="mentoring_code_2">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">스펙</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_3" class="mentoring_code" value="mentoring_code_3">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">외국어</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_4" class="mentoring_code" value="mentoring_code_4">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">면접/자소서</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_5" class="mentoring_code" value="mentoring_code_5">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">회사생활</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_6" class="mentoring_code" value="mentoring_code_6">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">창업</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_7" class="mentoring_code" value="mentoring_code_7">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">이직</div>
                      </div>
                    </label>
                  </li>
                </div>
                <div class="col-33">
                  <li>
                    <label class="item-checkbox item-content">
                      <input type="checkbox" name="mentoring_code" id="mentoring_code_8" class="mentoring_code" value="mentoring_code_8">
                      <i class="icon icon-checkbox"></i>
                      <div class="item-inner">
                        <div class="item-title">기타</div>
                      </div>
                    </label>
                  </li>
                </div>
            <div id="mentoring_code_error"></div>
            </div>
          </ul>
        </div>

        <div class="block-title">
          <label class="text required" for="mentor_request_field_of_expertise">대표 멘토링 분야 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string optional" placeholder="대표 멘토링 가능 분야를 한 줄로 작성해주세요" type="text" name="mentor_represent" id="mentor_represent">
                </div>
                  <div id="mentor_represent_error"></div>
              </div>
            </li>
          </ul>

          <div class="block-footer">
            <small class="form-text text-muted item-input-info">예시) 사육사, 조련사에 대한 궁금증</small>
          </div>
        </div>

        <div class="block-title">
          <label class="text required" for="mentor_request_mentor_intro">멘토 소개 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <textarea class=" is-valid text optional" name="mentor_info" id="mentor_info"></textarea>
                </div>
                  <div id="mentor_info_error"></div>
              </div>
            </li>
          </ul>

          <div class="block-footer">
            예시) 해외를 누빌 20대의 열정을 응원합니다. 한국자동차 해외영업팀 과장...
          </div>
        </div>

        <div class="block-title">
          <label class="text optional" for="mentor_request_etc">기타 사항</label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <textarea class="text optional" placeholder="멘토로 가입한 계기 및 회사에 대한 소개 또는 기타 하고 싶은 말을 적어주세요" name="mentor_etc" id="mentor_etc"></textarea>
                </div>
              </div>
            </li>
          </ul>
        </div>   
	</div>


    <div class="block no-hairlines text-align-center hero-title">
      <h1>
        멘토링 정보
      </h1>

      <p>
      멘티 질문 도착 시 알림 받을 정보를 확인해주세요.
      </p>
    </div>

    <div class="mentor-request-block block">

        <div class="block-title">
          <label class="email required" for="mentor_request_email">이메일 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap">
                  <input class=" is-valid string email optional" readonly="readonly" value="${memberDTO.member_email}" type="email" name="mentor_email" id="mentor_email">
                </div>
                  <div id="mentor_email_error"></div>
              </div>
            </li>
          </ul>
        </div>

        <div class="block-title">
          <label class="string required" for="mentor_request_content_term">우수 멘토링 콘텐츠 공개 여부 <abbr title="required">*</abbr></label>
        </div>
        <div class="list inset no-hairlines">
          <ul>
            <li class="item-content item-input">
              <div class="item-inner">
                <div class="item-input-wrap input-dropdown-wrap">
                  <select class=" is-valid select optional" name="mentor_selectNaming" id="mentor_selectNaming">
                  <option selected="selected" value="콘텐츠 실명 공개">콘텐츠 실명 공개</option>
					<option value="콘텐츠 익명 공개">콘텐츠 익명 공개</option>
					<option value="콘텐츠 비공개">콘텐츠 비공개</option></select>
                </div>
              </div>
            </li>
          </ul>

          <div class="block-footer">
            더 많은 멘티들이 유익한 정보를 얻고, 활발하게 질문을 남길 수 있도록 우수 멘토링 사례를 선정하여 편집을 거쳐 콘텐츠로 발행하고자 합니다.
          </div>
        </div>
    </div>


    <div class="block  no-hairlines text-align-center hero-title">
      <h1>
        멘토 확인
      </h1>
    </div>

    <div class="block-title">
      <label class="file optional" for="mentor_request_name_card">명함<abbr title="required">*</abbr></label>
    </div>
    <div class="block">
      <label for="mentor_request_name_card" class="button button-small button-inline">
        이미지 업로드
      </label>
      <input class="is-valid file optional" accept=".jpg, .jpeg, .png" onchange="previewFile(this);" type="file" name="mentor_businesscard" id="mentor_request_name_card">
      <p><img id="mentor_businesscard_img" src="../image/defaultuser.png" style="width: 100px; height: 100px"></p>
      
      <div id="mentor_businesscard_error"></div>
      <div class="block-footer">
        - 직장인은 회사 명함 이미지를 등록해주세요.<br>
        - 사업자 대표는 사업자등록증을 등록해주세요.<br>
        - 등록 가능한 파일 형식은 jpg, png, gif 입니다.<br>
      </div>
    </div>
    
    </form>
</div>
    
    
    <div class="block">
		<input type="button" name="mentorapply_btn" id="mentorapply_btn" value="신청하기" class="btn button button-big button-fill" style="height: 100%;">
	</div>
	<div class="block">
		<input type="button" name="mentorapply_backBtn" id="mentorapply_backBtn" value="뒤로가기" class="btn button button-big button-fill" style="height: 100%; background: white; color: black;">
	</div>
</div>
</div>
<script src="../js/mentor.js"></script>
<script>
$(document).ready(function(){
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
            reader.onload = function (e) {
            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                $('#mentor_businesscard_img').attr('src', e.target.result);
                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                //(아래 코드에서 읽어들인 dataURL형식)
            }                   
            reader.readAsDataURL(input.files[0]);
            //File내용을 읽어 dataURL형식의 문자열로 저장
        }
    }//readURL()--

    //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
    $("#mentor_request_name_card").change(function(){
        //alert(this.value); //선택한 이미지 경로 표시
        readURL(this);
    });
 });

</script>
