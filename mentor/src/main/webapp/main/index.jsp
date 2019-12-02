<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>멘토맨</title>

<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/application_layout.js"></script>

<!-- 아이콘 관련 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link rel="shortcut icon" href="../image/logo.ico" type="image/x-icon"/>
<link rel="stylesheet" href="../css/ly.css" type="text/css"/>
<link rel="stylesheet" href="../css/footer.css" type="text/css"/>

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

	   	$.ajax({
			url : '/mentor/member/countAlarm',
			type : 'POST',
			dataType: 'text',
			success : function(data) {
				alert('add하러옴' + data);//0이 나온다
				if(data == '0'){
				}else{
					$('#alarmCountSpan').addClass('bell-badge-danger bell-badge')
					$('#alarmCountSpan').text(data);
				}
			},
			error : function(err){
				alert('err');
			}
	   	});

	   	// 모달 알림
	   	var toastTop = app.toast.create({
            text: "알림 : " + data + "\n",
            position: 'top',
            closeButton: true,
          });
          toastTop.open();


    };

    sock.onclose = function() {
      	console.log('connect close');
      	/* setTimeout(function(){conntectWs();} , 1000); */
    };

    sock.onerror = function (err) {console.log('Errors : ' , err);};

   }

   $(function(){
	   if($('#my_bell').hasClass('myBell')){
	   $.ajax({
			url : '/mentor/member/countAlarm',
			type : 'POST',
			dataType: 'text',
			success : function(data) {
				if(data == '0'){
				}else{
					$('#alarmCountSpan').addClass('bell-badge-danger bell-badge')
					$('#alarmCountSpan').text(data);
				}

			},
			error : function(err){
				alert('bell 값 err');
			}
	   	});
	   }
   })

   </script>
</body>
</html>
