<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% System.out.println("roomView.jsp 호출확인"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디룸</title>
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karma">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">	

<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

td{font-family: "Raleway", sans-serif; font-size: 18px; align:center;}

input[type=text] {
	width: 500px;
	height: 32px;
	font-size: 15px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}

footer {
	width: 100%; 
	bottom:0; 
}
</style>

</head>
<body style = "width: 100%;">
	<jsp:include page="../../header.jsp"></jsp:include>
	<div id="full" class="w3-row w3-padding-64" style="max-width:100%;max-height:900px;margin-top:100px">
	<!-- 추후 script로 변경 -->
		<div class="w3-col m9 w3-padding-large">
			<!-- <div class="w3-row"> -->
			<c:forEach items="${requestScope.allGroup}" var="e" varStatus="status">
				<div class="w3-half">
					<iframe width="100%" height="400" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1" ></iframe>
				</div> 
			</c:forEach>
				<!-- <div class="w3-half">
					<iframe width="100%" height="400" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
				</div>
			</div>
			<div id="sec" class="w3-row">
				<div id="third" class="w3-half">
					<iframe width="100%" height="400" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
				</div> 
				<div id="fourth" class="w3-half">
					<iframe width="100%" height="400" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
				</div> -->
			<!-- </div> -->
		</div>
		<div id="side" class="w3-col m5 w3-padding-large w3-hide-small" style="width:20%">
			<div id="mem" style="height:50%;">
				<ul class="w3-xlarge">MEMBERS
					<c:forEach items="${requestScope.allGroup}" var="e" varStatus="status">
						<li class="w3-large">${e.nickname}</li>
					</c:forEach>
				</ul>
			</div>
			<br><hr><br>
			<div id="chat" style="height:50%;"><ul class="w3-xlarge">Chatting</ul></div>
		</div> 
	</div>


  <footer class="w3-row-padding w3-padding-32">
    <div class="w3-third">
   	 <button id="exit" class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/main.jsp"'>나가기</button>
    </div>
  </footer>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  	<script src="/group/view.js"></script>
</body>
</html>
