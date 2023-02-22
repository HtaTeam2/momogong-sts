<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter"%>
<%@ page import="model.domain.entity.StudyMembers"%>
<%@ page import="model.StudyMembersDAO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>내 정보보기</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/viewOne.css">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

<style>
body, h1, h2, h3, h4, h5, h6, ul, li, p, footer {
	font-family: "Raleway", sans-serif;
	font-size: 18px;
}

footer {
	width: 100%;
	bottom: 0;
}

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
</style>

</head>



<body class="w3-white w3-content" style="max-width: 1600px">
	<!-- header -->
	<header>
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
				<c:if test="${sessionScope.id == 'admin'}">
					<button class="w3-button w3-white w3-hide-small w3-right"
						onclick='location.href="${pageContext.request.contextPath}/StdMembers/adPage"'>관리자메뉴</button>
				</c:if>
			</div>
		</div>
	</header>

	<div id="searchList" class="w3-row-padding" align="left">

		<table border="1" cellspacing="1" width="60%">
			<tr>
				<td width=30%>아이디</td>
				<td width=70%>${allData.id}</td>
			</tr>
			<tr>
				<td width="30%">닉네임</td>
				<td width="70%">${allData.nickname}</td>
			</tr>
			<tr>
				<td width="30%">비밀번호</td>
				<td width="70%">${allData.password}</td>
			</tr>
			<tr>
				<td width="30%">이메일 주소</td>
				<td width="70%">${allData.email}</td>
			</tr>
			<tr>
				<td width="30%">등급</td>
				<td width="70%">${allData.grade}</td>
			</tr>
			<tr>
				<td width="30%">목표</td>
				<td width="70%">${allData.goal}</td>
			</tr>
			<tr>
				<td width="30%">가입일</td>
				<td width="70%">${allData.regdate}</td>
			</tr>
		</table>
		<br> <br>
		<!--
			 http://ip:port/context명/update.jsp  
		${pageContext.request.contextPath} : 코드 사용 권장
		즉 현 jsp의 실행 위치가 어디에 있던 "http://ip:port/context명/" 을 의미하는 코드
		
		-->
		<div class="btn">
			<input class="btnBox" type="button" value="수정하기"
				Onclick="location.href='${pageContext.request.contextPath}/StdMembers/updatepage?id=${allData.id}'">&nbsp;
			<br> <br>
			<button class="btnBox" id="delete_btn"
				onclick='location.href="${pageContext.request.contextPath}/StdMembers/delete?id=${allData.id}"'>
				탈퇴하기</button>
		</div>

	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="/../team_studyroom/select.js"></script>
</body>
</html>
