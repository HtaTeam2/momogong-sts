<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	System.out.println("view.jsp실행 확인 -----------");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>

<body>

	<h3>공지사항</h3>
	<br>

	<form action="allView">

		<table border="1" cellspacing="1" width="60%">
			<tr>
				<td width=30%>글 번호</td>
				<td width=70%>${ndto.noticeNo}</td>
			</tr>
			<tr>
				<td width="30%">글 내용</td>
				<td width="70%">${ndto.noticeContent}</td>
			</tr>
			<tr>
				<td width="30%">등록시간</td>
				<td width="70%">${ndto.noticeRegdate}</td>
			</tr>
			<tr>
				<td width="30%">글 제목</td>
				<td width="70%">${ndto.noticeTitle}</td>
			</tr>
			<tr>
				<td width="30%">조회수</td>
				<td width="70%">${ndto.viewCount}</td>
			</tr>
		</table>
		<br> <br> <input type="button" value="update"
			Onclick="location.href='${pageContext.request.contextPath}/update.jsp'">&nbsp;



		<input type="submit" value="allView">
		<input type="button" value="write"
			Onclick="location.href='write.html'">
	</form>


</body>
</html>