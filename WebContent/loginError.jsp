<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 오류</title>
</head>
<body>
로그인 실패<br>
아이디 또는 비밀번호를 확인하세요<br>
	<input type="button" value="로그인하기"
		onclick="location.href='${pageContext.request.contextPath}/login.jsp'">
	<input type="button" value="뒤로가기" onclick="history.back()">
	<input type="button" value="메인화면으로"
		onclick="location.href='${pageContext.request.contextPath}/main.html'">
</body>
</html>