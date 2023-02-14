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
	
	<form action="${pageContext.request.contextPath}/StdGroup/insert" method="get">
		<a href="StdGroup/mystudy">내 스터디로 이동</a>
		
		방번호: <input type="text" name="roomNo"/>
		<input type="submit" value="입장">
	</form>
	<a href="${pageContext.request.contextPath}/StdGroup/insert/1">1번방으로 이동</a>
	

</body>
</html>