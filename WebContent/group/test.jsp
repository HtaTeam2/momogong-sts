<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%	
	//세션-jsp 내장 객체, server 메모리에 데이터 저장
	session.setAttribute("id", "test1");
%>   

	<a href="StdGroup/mystudy">내 스터디로 이동</a>

</body>
</html>