<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main-content" class="span9">
  <div class="contentWrapper">                  
	<hgroup id="categoryHead">
    <h1 class="title">${catalog}</h1>
    <p class="descrip"></p>
</hgroup><!--/categoryHead-->
    <ul class="articleList">
    <c:forEach items="${list}" var="data" varStatus="count">
       <li class="faq_title_li"><a class="faq_title_${count.index}" onclick="faqTitle_click('content_div_${count.index}')" style="cursor: pointer;"><i class="icon-article-doc"></i><span>${data.faq_title}</span></a></li>
       <div class="detail trix-content" id="content_div_${count.index}" style="display:none;">
			<pre style="white-space: pre-line; font-family: auto; background-color: white;"><c:out value="${data.faq_content}"/></pre>
	   </div>
    </c:forEach>
    </ul><!--/articleList-->
  </div><!--/contentWrapper-->
</section>
<script src="../js/jquery-3.4.1.min.js"></script>
<script>
function faqTitle_click(data){
	var submenu = $('#'+data);

    if( submenu.is(":visible") ){
        submenu.slideUp();
    }else{
        submenu.slideDown();
    }	
}

</script>