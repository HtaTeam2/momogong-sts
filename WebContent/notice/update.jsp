<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.domain.NoticeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
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
 	<jsp:include page="/header.jsp"></jsp:include>
	<br>
	<center>
		<h3>글 수정</h3>
		<br>

		<form action="${pageContext.request.contextPath}/Notice/update/${ndto.noticeNo}" method="post">
			<!-- update Form  -->
			<table border="1" cellspacing="1" width="60%">
				<tr>
					<td width=30%>글 번호</td>
					<td width=70%> ${ndto.noticeNo}</td>
				</tr>
				<tr>
					<td width="30%">글 내용</td>
					<td width="70%"><input type="text" name="noticeContent" value="${ndto.noticeContent}"></td>
				</tr>
				<tr>
					<td width="30%">등록시간</td>
					<td width="70%"><input type="date" name="noticeRegdate" value="${ndto.noticeRegdate}"></td>
				</tr>
				<tr>
					<td width="30%">글 제목</td>
					<td width="70%"><input type="text" name="noticeTitle" value="${ndto.noticeTitle}"></td>
				</tr>
				<tr>
					<td width="30%">조회수</td>
					<td width="70%">${ndto.viewCount}</td>
				</tr>
			</table>
			<p />

			<input type="submit" value="수정"> &nbsp; 
			<input type="button" value="목록으로"
				Onclick="location.href='${pageContext.request.contextPath}/Notice/list'">
		</form>
</body>
</html>