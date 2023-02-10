<%@page import="model.StudyMembersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	System.out.println("viewOne.jsp실행 확인 -----------");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 보기</title>
</head>

<body>

	<h3>내 개인 정보 보기</h3>
	<br>
	<!-- view Form  -->
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
		<!--
			 http://ip:port/context명/update.jsp  
		${pageContext.request.contextPath} : 코드 사용 권장
		즉 현 jsp의 실행 위치가 어디에 있던 "http://ip:port/context명/" 을 의미하는 코드
		
		-->
		<input type="button" value="update"
			Onclick="location.href='${pageContext.request.contextPath}/update.jsp'">&nbsp;

		<button
			onclick='location.href="${pageContext.request.contextPath}/StdMembers/delete?id=${dto.id}"'>
			탈퇴하기</button>

	</form>


</body>
</html>
