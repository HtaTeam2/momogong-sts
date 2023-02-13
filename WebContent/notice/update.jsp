<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.domain.NoticeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>

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
