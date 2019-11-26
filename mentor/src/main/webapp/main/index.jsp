<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="../js/jquery-3.4.1.min.js"></script>
<!-- <script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/vendor-d00d42ee38137ae39144.js"></script> -->
<script src="https://d2ljmlcsal6xzo.cloudfront.net/packs/js/application-a92a237ea64ef641a7be.js"></script>

<!-- 아이콘 관련 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="shortcut icon" href="../image/mentorlogo.ico" type="image/x-icon"/>
<link rel="stylesheet" href="../css/all.css" type="text/css">
<link rel="stylesheet" href="../css/ly.css" type="text/css" />
<link rel="stylesheet" href="../css/famil.css"  type="text/css"/>

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
   
   <!-- 웹소켓 작동 -->
   <script type="text/javascript" src="../js/sockjs.js"></script>
   <script type="text/javascript">
   //전역변수 선언-모든 홈페이지에서 사용 할 수 있게 index에 저장
   var socket = null;
   
   $(document).ready(function (){
	   connectWs();
   });

   function connectWs(){
   	sock = new SockJS( "<c:url value="/echo"/>" );
   	//sock = new SockJS('/replyEcho');
   	socket = sock;
   	
   	sock.onopen = function() {
           console.log('info: connection opened.');
     };
       
    sock.onmessage = function(evt) {
	 	var data = evt.data;
	   	console.log("ReceivMessage : " + data + "\n");
	   	$('#inputSocket').html("알림 : " + data);
	   	
	   	var toastTop = app.toast.create({
            text: "알림 : " + data + "\n",
            position: 'top',
            closeButton: true,
          });
          toastTop.open();
		//var obj = JSON.parse(data)   
	   	//console.log(obj)
	   	//appendMessage(obj.message_content);
    };
    
    sock.onclose = function() {
      	console.log('close2');
      	setTimeout(function(){conntect();} , 1000);
    };
	
    sock.onerror = function (err) {console.log('Errors : ' , err);};
   	
   }
   </script>
</body>

</html>