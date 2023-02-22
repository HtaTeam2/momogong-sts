<%@page import="model.domain.entity.StudyMembers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>관리자 - 전체 회원관리</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", sans-serif
}

td {
	font-family: "Raleway", sans-serif;
	font-size: 18px;
	align: center;
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

footer {
	width: 100%;
	bottom: 0;
}
</style>
</head>
<body class="w3-white w3-content" style="max-width: 1600px">

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
			<c:if test="${sessionScope.id == 'admin'}">
				<button class="w3-button w3-white w3-hide-small w3-right"
					onclick='location.href="${pageContext.request.contextPath}/StdMembers/adPage"'>관리자메뉴</button>
			</c:if>
		</div>
	</div>
<body>

	<h3>관리자 - 전체 회원관리</h3>
	<br>
	<!-- view Form  -->
	<form action="StdMembers/adAllView">
		<c:if test="${not empty requestScope.allData}">
			<table border="1" width="70%">
				<tr>
					<td width="15%">id</td>
					<td width="15%">nickname</td>
					<td width="20%">email</td>
					<td width="15%">가입일</td>
					<td width="15%">등급</td>
					<td width="20%">삭제</td>
				</tr>
				<c:forEach items="${requestScope.allData}" var="dto">
					<tr>
						<td>${allData.id}</td>
						<td>${allData.nicname}</td>
						<td>${allData.email}</td>
						<td>${allData.regdate}</td>
						<td>${allData.grade}</td>
						<td>
							<button
								onclick='location.href="${pageContext.request.contextPath}/StdMembers/delete?id=${allData.id}"'>
								삭제하기</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<%-- ArrayList의 size()=0 즉 데이터가 없을 경우에만 true --%>
		<c:if test="${empty requestScope.allData}">
		한명의 회원도 없습니다.
	</c:if>
		<!-- 
			http://ip:port/context명/update.jsp
			${pageContext.request.contextPath} : 코드 사용 권장
			즉, 현 jsp의 실행 위치가 어디에 있든 http://ip:port/context명/ 을 의미하는 코드
		 -->
		<input type="button" value="update"
			Onclick="location.href='${pageContext.request.contextPath}/update.jsp'">&nbsp;
		<input type="submit" value="allView">
	</form>
</body>
</html>
