<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 스터디</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<body>
	<!-- header -->
	<header>
	<div class="w3-container">
	    <div class="w3-section w3-bottombar w3-padding-16">
		  <img src="${pageContext.request.contextPath}/images/momogong.png" onclick='location.href="${pageContext.request.contextPath}/main.jsp"' style="width:10%" class="w3-hover-opacity">
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내 스터디</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/lists/createStdList.jsp"'>스터디 생성</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/Community/list"'>커뮤니티</button>
	      <button class="w3-button w3-white w3-hide-small" onclick='location.href="${pageContext.request.contextPath}/Notice/list"'>공지사항</button>
	      	 스터디 검색 : <input type="text" id="study" name="study" value=""><button id="btn1" class="w3-button w3-white w3-hide-small"><i class="fa fa-search"></i></button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/logout"'>로그아웃</button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/viewOne2"'>내 정보</button>
	    </div>
	</div>
	</header>
	
	<div id="searchList" class="w3-row-padding">
		<h3>-예외 발생: ${errorMsg}-</h3>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>	
</body>
</html>
