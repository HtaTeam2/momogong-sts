<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty findMemId}">
			찾으시는 아이디는 <b>${findMemId.id}</b> 입니다.<br>
		</c:when>
		<c:when test="${empty findMemId}">
			찾으시는 아이디가 없습니다.<br>
		</c:when>
	</c:choose>
	<input type="button" value="로그인하기"
		onclick="location.href='${pageContext.request.contextPath}/login.jsp'">
	<input type="button" value="뒤로가기" onclick="history.back()">
	<input type="button" value="메인화면으로"
		onclick="location.href='${pageContext.request.contextPath}/main.html'">

</body>
</html>
