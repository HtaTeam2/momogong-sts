<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% System.out.println("myStudy.jsp 호출확인"); %>
  
<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
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
  <!-- Header -->
  <header id="portfolio">
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
    <div class="w3-container">
	    <div class="w3-section w3-bottombar w3-padding-16">
	       
		  <img src="${pageContext.request.contextPath}/images/momogong.png" onclick='location.href="${pageContext.request.contextPath}/main.jsp"' style="width:10%" class="w3-hover-opacity">
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내 스터디</button>
	      <button class="w3-button w3-white" onclick='location.href="StdList/insert"'>스터디 생성</button>
	      <button class="w3-button w3-white" onclick='location.href="Community/커뮤니티 전체select"'>커뮤니티</button>
	      <button class="w3-button w3-white w3-hide-small" onclick='location.href="Notice/공지사항 전체select"'>공지사항</button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="StdMembers/logout"'>로그아웃</button>
	    </div>
    </div>
  </header>
=======
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
	<div class="w3-container">
		<div class="w3-section w3-bottombar w3-padding-16">

			<img src="${pageContext.request.contextPath}/images/momogong.png"
				onclick='location.href="${pageContext.request.contextPath}/main.jsp"'
				style="width: 10%" class="w3-hover-opacity">
			<button class="w3-button w3-white"
				onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내
				스터디</button>
			<button class="w3-button w3-white"
				onclick='location.href="${pageContext.request.contextPath}/lists/createStdList.jsp"'>스터디
				생성</button>
			<button class="w3-button w3-white"
				onclick='location.href="${pageContext.request.contextPath}/Community/list"'>커뮤니티</button>
			<button class="w3-button w3-white w3-hide-small"
				onclick='location.href="${pageContext.request.contextPath}/Notice/list"'>공지사항</button>
			스터디 검색 : <input type="text" id="study" name="study" value="">
			<button id="btn1" class="w3-button w3-white w3-hide-small">
				<i class="fa fa-search"></i>
			</button>
			<button class="w3-button w3-white w3-hide-small w3-right"
				onclick='location.href="${pageContext.request.contextPath}/StdMembers/logout"'>로그아웃</button>
			<button class="w3-button w3-white w3-hide-small w3-right"
				onclick='location.href="${pageContext.request.contextPath}/StdMembers/viewOne2"'>내
				정보</button>
		</div>
	</div>

>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
<!-- 비동기로 변경 -->
	<h1>&nbsp;&nbsp;<i class="fa fa-pencil-square-o" style="font-size:40px;color:#3c3c3c;"></i>내스터디</h1>
		<div class="w3-row-padding" style="margin:0 -16px">
	      <div class="w3-margin-bottom">
	      	<h2>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-flag-checkered"></i>나의 목표</h2>
	        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
	         <c:choose>
				<c:when test="${empty allData[0].goal}">
					<p align="center">
						<b><span style="font-size: 15pt;">작성된 목표가 없습니다.</span></b>
					</p>
				</c:when>
				<c:otherwise>
		        	<li class="w3-padding-30">${allData[0].goal}</li>
				</c:otherwise>
			</c:choose>
	        </ul>
	      </div>
	  	</div>
<<<<<<< HEAD
		<table border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
			<tr>
				<c:forEach items="${allData}" var="e" varStatus="status">
					<td class = "w3-center w3-container w3-third">
						<img src="${pageContext.request.contextPath}/images/profile.png" style="width:60%;" onclick='location.href="${pageContext.request.contextPath}/StdGroup/insert/${e.studyListNo}"' class="w3-container w3-hover-opacity">
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${allData}" var="e" varStatus="status">
					<td class = "w3-center w3-container w3-third"><a href="${pageContext.request.contextPath}/StdGroup/insert/${e.studyListNo}">${e.roomTitle}</a></td>
				</c:forEach>
			</tr>
			<tr >
				<c:forEach items="${allData}" var="e" varStatus="status">
					<td class = "w3-center w3-container w3-third"><a href='delete/${e.studyListNo}'>탈퇴하기</a></td>
				</c:forEach>
			</tr>
		</table>
=======
			<table border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
				<tr>
					<c:forEach items="${allData}" var="e" varStatus="status">
						<td class = "w3-center w3-container w3-third">
							<img src="${pageContext.request.contextPath}/images/profile.png" style="width:60%;" onclick='location.href="${pageContext.request.contextPath}/StdGroup/insert/${e.studyListNo}"' class="w3-container w3-hover-opacity">
						</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach items="${allData}" var="e" varStatus="status">
						<td class = "w3-center w3-container w3-third"><a href="${pageContext.request.contextPath}/StdGroup/insert/${e.studyListNo}">${e.roomTitle}</a></td>
					</c:forEach>
				</tr>
				<tr >
					<c:forEach items="${allData}" var="e" varStatus="status">
						<td class = "w3-center w3-container w3-third">
					  	<form action="${pageContext.request.contextPath}/StdGroup/delete/${e.studyListNo}" method=post >
							<button type="submit" >탈퇴</button></a></td>
						</form>
					</c:forEach>
				</tr>
			</table>
		
	<!-- axios 사용을 위한 추가 설정 -->
<!--   <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="main.js"></script> -->
>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
</body>
</html>
