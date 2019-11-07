<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/include.postload.js"></script>
<script src="../js/rollbar.min.js"></script>
<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/vendor-d00d42ee38137ae39144.js"></script>
<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/application-978dcbe0c8e9f17a9e95.js"></script>
<!-- 아이콘 관련 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<link rel="shortcut icon" href="" type="image/x-icon"/>
<link rel="stylesheet" href="../css/all.css" type="text/css">
<link rel="stylesheet" href="../css/ly.css" type="text/css" />
<link rel="stylesheet" href="../css/famil.css"  type="text/css"/>
<!-- <link rel="stylesheet"  href="../css/ovw.carousel.css"  type="text/css"/>
<link rel="stylesheet"  href="../css/owl.theme.default.css"  type="text/css"/>
<link rel="stylesheet" href="../css/font-awesome.min.css" type="text/css"/>
<link rel="stylesheet" href="../css/styles.8db41f0a.css" type="text/css"/> -->
</head>
<body class="color-theme-pink">
   <div id="app" class="framework7-root">
      <div class="view view-main">
         <!-- head -->
         <div class="head">
            <jsp:include page="../template/head.jsp" />
         </div>
         
         <!-- display -->
         <div class="container" id="container">
            <jsp:include page="${display}" />
         </div>
      </div>
   </div>
   <!-- footer  -->
   <div id="foot" id="foot">
      <jsp:include page="../template/footer.jsp" />
   </div>
</body>
</html>