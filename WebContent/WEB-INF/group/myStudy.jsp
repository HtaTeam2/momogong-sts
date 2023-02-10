<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% System.out.println("myStudy.jsp 호출확인"); %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 스터디</title>
</head>
<body>
<!-- 비동기로 변경 -->
	<h1><i class="fas fa-cloud" style="font-size: 48px; color: skyblue"></i>내스터디</h1>
	<br>

		<table border="1" cellspacing="1" width="60%">
			<tr>
				<th width=30%>방번호</td>
				<th width="30%">방이름</th>
				<th width="30%">탈퇴<thh>
			</tr>
			<tr>
				<td width=70%>${dto.id}</td>
				<td width="70%">${dto.nickname}</td>
				<td width="70%">${dto.password}</td>
		</table>
		<br> <br>



</body>
</html>