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
<style>
.first {
	display: block;
	position: absolute;
	width: 40%;
	height: 50%;
	background-color: gray;
}

.second {
	display: block;
	position: absolute;
	left: 40%;
	width: 40%;
	height: 50%;
	background-color: gray;
}

.third {
	display: block;
	position: absolute;
	bottom: 0px;
	width: 40%;
	height: 50%;
	background-color: gray;
}

.fourth {
	display: block;
	position: absolute;
	left: 40%;
	bottom: 0px;
	width: 40%;;
	height: 50%;
	background-color: gray;
}

#members {
	display: block;
	position: absolute;
	right: 0;
	width: 20%;
	height: 50%;
	border: 0.5px;
	background-color: none;
}

#chat {
	display: block;
	position: absolute;
	bottom: 0px;
	right: 0;
	width: 20%;
	height: 50%;
	border: 0.5px;
	background-color: none;
}

#full {
	position: absolute;
	right: 0;
	width: 94%;
	height: 800px;
	border: 3px solid;
}

#side {
	position: relative;
	width: 5%;
	height: 800px;
	border: 3px solid;
	background-color: none;
}

</style>
</head>
<body style = "width: 100%;">
	<header>
		<h1>
			<i class="fas fa-cloud" style="font-size: 48px; color: skyblue"></i>모모공
		</h1>
	</header>

	<div id="full" class="full">
	<!-- 추후 script로 변경 -->
		<div>
			<div class="first">
				<iframe width="100%" height="100%" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1" ></iframe>
			</div> 
			<div class="second">
				<iframe width="100%" height="100%" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
			</div>
			<div class="third">
				<iframe width="100%" height="100%" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
			</div> 
			<div class="fourth">
				<iframe width="100%" height="100%" src="https://www.youtube.com/embed/R7TXQF8bW_Y?autoplay=1&mute=1"></iframe>
			</div>
		</div>

		<div>
			<div id="members">Members</div> 
			<div id="chat"></div>
		</div>

	</div>
	<div id="side"></div>

	<footer>
		  <a href="main.jsp"><i class="material-icons" style="font-size:60px;color:lightblue;"></i>나가기</a>
	</footer>
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  	<script src="/group/view.js"></script>
</body>
</html>