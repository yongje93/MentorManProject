<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="page navbar-fixed mentee_programs index" data-name="mentee_programs-index">
	<div class="page-content">
		<div class="pogram-block">
			<div class="block-title strong-title">
				모임
				<%-- 접속한 유저가 멘토일 때 모임 작성 버튼 --%>
				<%-- <c:if test="memEmail != null && memFlag == 2"> --%>
				<button class="button color-gray">
					<a class="color-gray" type="external" href="/mentor/meetingboard/meetingboardWriteForm"> 모임 작성 </a>
				</button>
				<%-- </c:if> --%>
			</div>
			<%--모임 리스트 뿌려주는 영역--%>
			<div class="row no-gap"> 
			
			</div>
		</div>
	</div>
</div>