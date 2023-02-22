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
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="../css/viewOne.css">

<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", sans-serif;
	font-size: 18px;
}

input[type=text] {
	width: 300px;
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
<body>
	<!-- header -->
	<header>
		<div class="w3-container">
			<div class="w3-section w3-bottombar w3-padding-16">

				<img src="${pageContext.request.contextPath}/images/momogong.png"
					onclick='location.href="${pageContext.request.contextPath}/main.html"'
					style="width: 10%" class="w3-hover-opacity">
				<button class="w3-button w3-white"
					onclick='location.href="../login.jsp"'>내 스터디</button>
				<button class="w3-button w3-white"
					onclick='location.href="../login.jsp"'>스터디 생성</button>
				<button class="w3-button w3-white"
					onclick='location.href="../login.jsp"'>커뮤니티</button>
				<button class="w3-button w3-white w3-hide-small"
					onclick='location.href="../login.jsp"'>공지사항</button>
				스터디 검색 : <input type="text" id="study" name="study" value="">
				<button id="btn1" class="w3-button w3-white w3-hide-small">
					<i class="fa fa-search"></i>
				</button>
				<button class="w3-button w3-white w3-hide-small w3-right"
					onclick='location.href="../login.jsp"'>로그인</button>
				<c:if test="${sessionScope.id == 'admin'}">
					<button class="w3-button w3-white w3-hide-small w3-right"
						onclick='location.href="${pageContext.request.contextPath}/StdMembers/adPage"'>관리자메뉴</button>
				</c:if>
			</div>
		</div>
	</header>


	<div id="searchList" class="w3-row-padding" align="center">
		<form action="StdMembers/viewOne">

			<table border="1" cellspacing="1" width="60%">
				<tr>
					<td width=30%>아이디</td>
					<td width=70%>${dto.id}</td>
				</tr>
				<tr>
					<td width="30%">닉네임</td>
					<td width="70%">${dto.nickname}</td>
				</tr>
				<tr>
					<td width="30%">비밀번호</td>
					<td width="70%">${dto.password}</td>
				</tr>
				<tr>
					<td width="30%">이메일 주소</td>
					<td width="70%">${dto.email}</td>
				</tr>
				<tr>
					<td width="30%">등급</td>
					<td width="70%">${dto.grade}</td>
				</tr>
				<tr>
					<td width="30%">목표</td>
					<td width="70%">${dto.goal}</td>
				</tr>
				<tr>
					<td width="30%">가입일</td>
					<td width="70%">${dto.regdate}</td>
				</tr>
			</table>
			<br> <br>

		</form>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="/../team_studyroom/select.js"></script>
</body>
</html>
