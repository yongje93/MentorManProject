<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script src="../js/summernote-ko-KR.js"></script> -->
    <meta charset="utf-8">
    <title>자주하는 질문</title>
    <link rel="stylesheet" href="../css/faq.css">
    <link rel="stylesheet" href="//d3eto7onm69fcz.cloudfront.net/assets/stylesheets/launch-1573051559617.css">
    
    <style>
        body { background: #ffffff; }
        .navbar .navbar-inner { background: #ffffff; }
        .navbar .nav li a, 
        .navbar .icon-private-w  { color: #585858; }
        .navbar .brand, 
        .navbar .nav li a:hover, 
        .navbar .nav li a:focus, 
        .navbar .nav .active a, 
        .navbar .nav .active a:hover, 
        .navbar .nav .active a:focus  { color: #ff2d59; }
        .navbar a:hover .icon-private-w, 
        .navbar a:focus .icon-private-w, 
        .navbar .active a .icon-private-w, 
        .navbar .active a:hover .icon-private-w, 
        .navbar .active a:focus .icon-private-w { color: #ff2d59; }
        #serp-dd .result a:hover,
        #serp-dd .result > li.active,
        #fullArticle strong a,
        #fullArticle a strong,
        .collection a,
        .contentWrapper a,
        .most-pop-articles .popArticles a,
        .most-pop-articles .popArticles a:hover span,
        .category-list .category .article-count,
        .category-list .category:hover .article-count { color: #585858; }
        #fullArticle, 
        #fullArticle p, 
        #fullArticle ul, 
        #fullArticle ol, 
        #fullArticle li, 
        #fullArticle div, 
        #fullArticle blockquote, 
        #fullArticle dd, 
        #fullArticle table { color:#585858; }
        th {style="float: left; text-align: left;}
    </style>
    
    <link rel="stylesheet" href="//s3.amazonaws.com/helpscout.net/docs/assets/5afcd47d2c7d3a640ed6edc1/attachments/5afcdf9b042863158411de9e/help.css">
    
    <link rel="apple-touch-icon-precomposed" href="//d33v4339jhl8k0.cloudfront.net/docs/assets/5afcd47d2c7d3a640ed6edc1/images/5b811bb02c7d3a03f89e1e3e/KakaoTalk_20180808_093443631.png">
   <!-- 멘토멘 로고 넣기  <link rel="shortcut icon" type="image/png" href="//d33v4339jhl8k0.cloudfront.net/docs/assets/5afcd47d2c7d3a640ed6edc1/images/5b6a42af0428631d7a89c074/KakaoTalk_20180808_093443631.png"> -->
</head>
    <body>
        
<header id="mainNav" class="navbar">
<div class="navbar-inner">
    <div class="container-fluid">
        <a class="brand" href="/mentor/faq/faqIndex">
            <img src="../image/logo.png" alt="자주하는 질문" style="width: 160px; height: 70px;">
        </a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        <div class="nav-collapse collapse">
            <nav role="navigation"><!-- added for accessibility -->
            <ul class="nav">
               <li id="contact"><a class="contactUs" id="contactUs" style="line-height: 70px; cursor: pointer;">문의하기</a></li>
            </ul>
            </nav>
        </div><!--/.nav-collapse -->
    </div><!--/container-->
</div><!--/navbar-inner-->
</header>
<jsp:include page="${display}" />
    <!--문의 하기 모달 <script src="//d3eto7onm69fcz.cloudfront.net/assets/javascripts/app3.min.js"></script> -->
	<div id="my-dialog">
	    <section id="contentArea" class="container-fluid">
			<div class="page navbar-fixed mentee_programs index">
				<div class="page-content" >
					<div class="block-title strong-title" id="faq_title">문의하기</div>
					<div class="block inset">
							<form method="post" id="faqToContactWriteForm" action="/mentor/faq/faqToCntactWrite" enctype="multipart/form-data" accept-charset="UTF-8" >
								<table id="faqToContactWriteForm" class="faqToContactWriteForm">
									<thead>
									</thead>
									<tbody>
									<!-- <tr>
										<th scope="cols" style="float: left; text-align: left;"><input type="text" id="faqToContact_name" name="faqToContact_name" placeholder="이름">
									</tr> -->
									<tr>
										<th scope="cols" style="float: left; text-align: left;"><input type="text" id="faqToContact_email" name="faqToContact_email" placeholder="이메일">
										<div id="faqToContact_email_error_div"></div></th>
									</tr>
								 	<tr>
								 		<td colspan="2">
								 			<div id="faqToContact_content_div">
				             					  <textarea id="faqToContact_content" name="faqToContact_content" style="width: 400px; height: 180px;"></textarea>
				            				</div>
								 			<div id="faqToContact_content_div_error"></div>
								 		</td>
								 	<tr>
								 	</tbody>
								 	
								 	<tr align="center">
								 		
								 		<td colspan="2">
								 			<div class="faqToContact_right_div" style="float: left;margin-right: 10px;"><input type="button"  id="faqToContact_Btn" class="button color-gray" value="작성하기"></div>
								 			<div class="faqToContact_right_div" style="float: left;"><input type="button" id="faqForm_backBtn" class="button color-gray" value="뒤로가기"></div>
								 		</td>
								 	</tr>
								</table>
							</form>
					</div>
				</div>
			</div>
		</section>
	</div>
<div id="dialog-background"></div>

<script src='//code.jquery.com/jquery.min.js'></script>
<script>
$(function(){
	$("#contactUs,#faqForm_backBtn,#dialog-background,#btn-close-dialog").click(function () {
		$("#my-dialog,#dialog-background").toggle();
	});
	
	$('#faqToContact_Btn').on('click', function(){
		$('#faqToContact_email_error_div').empty();
		$('#faqToContact_content_div_div').empty();
		
		if($('#faqToContact_email').val() == ''){
			$('#faqToContact_email_error_div').text('이메일을 입력해주세요').css('color', 'red');
			$('#faqToContact_email_error_div').css('font-size','8pt');
			$('#faqToContact_email').focus();
			
		}else if($('#faqToContact_content').val() == ''){
			$('#faqToContact_content_div_error').text('내용을 입력해주세요').css('color', 'red');
			$('#faqToContact_content_div_error').css('font-size','8pt');
			$('#faqToContact_content').focus();
			
		}else {
			$('#faqToContactWriteForm').submit();	
		}
	});

});
</script>