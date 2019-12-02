<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="../admincss/adminessay.css">
<div class="row">
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="x_panel" style="height: 600px;">
			<div class="x_title">
				<h2>에세이 게시판</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						<li><a class="close-link"><i class="fa fa-close"></i></a></li>
					</ul>
				<div class="clearfix"></div>
			</div> <!-- x_title 끝 -->
			<div class="x_content">
			<div class="row">
				<div class="col-sm-12">
					<div class="cardWrap" style="overflow:hidden; width: 100%">
					<c:forEach var="list" items="${list }">
						<div class="card">
							<div class="card_head">
							<div>
								<div class="mentor-image img-circle">
          							  <c:if test="${list.member_profile != 'profile.jpg'}">
							            <img width="30" height="30" src="../storage/${list.mentor_email}/${list.member_profile}" style="border-radius: 50%;">
							          </c:if>
							          <c:if test="${list.member_profile == 'profile.jpg'}" >
							            <img width="30" height="30" src="../image/profile.jpg" style="border-radius: 50%;">
							          </c:if>
        						</div>
        						<div class="mentor-info">
					          		<div>
						            	<strong class="mentor-name">${list.member_name }</strong>
						            		<small>멘토</small>
					          		</div>
	          				  		<div class="job">
	            					<small>
	              						${list.mentor_company } · ${list.mentor_department }
	            					</small>
	          						</div>
        						</div>
        						</div>
        						<div class="created-at">
				       			 	<small>${list.essayboard_logtime }</small>
				    			</div>
							</div> <!-- card head -->
							<div class="card-content card-content-padding"style="overflow: hidden; text-overflow: ellipsis; height: 200px; ">
				   				<input type="hidden" id="seq" name="seq" value="${list.essayboard_seq }">
				    			<a class="content-body" type="external" href="/mentor/essayboard/essayboardView?pg=${pg }&seq=${list.essayboard_seq}&mentors=${list.member_seq }" >
						        <div class="mentor-post-title">
						        	${list.essayboard_title }
						        </div>
						        <div class="mentor-post-detail">
						        	<c:choose>
										<c:when test="${fn:length(list.essayboard_content) gt 200}">
											<c:out value='${fn:substring(list.essayboard_content.replaceAll("\\\<.*?\\\>|&nbsp;",""), 0, 190)}'/>...
										</c:when>
										<c:otherwise>
											<c:out value="${list.essayboard_content}"/>
										</c:otherwise>
									</c:choose>
						        </div>
								</a>  
							</div>
				  			<div class="card-footer" style="">
					    		<a class="color-gray js-bookmark" id="scrap" type="externalScrap" data-remote="true" rel="nofollow" data-method="post" href="/mentor_posts/6589/bookmarks" style="right: 0px;
					    			position: unset;
					    			margin: 0px 0px;">
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
				  			</div>
						</div><!-- card끝 -->
						</c:forEach>
					</div><!-- 카드전체 -->
				</div>
				</div><!-- 카드 row 끝 -->
				<div class="row a1">
						<div class="col-sm-12 searchPage">
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<li class="page-item">${essayboardPaging.pagingHTML }</li>
								</ul>
							</nav>                                                           
						</div>
					</div> <!-- 버튼 row끝 -->
				<div class="ln_solid"></div>
			</div><!-- xcontent -->
		</div><!--x_panel-->
	</div>
</div><!-- row -->