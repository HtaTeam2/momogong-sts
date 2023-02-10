<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 처리 page</title>
</head>
<body>

	문제 발생 잠시후 재시도 해주세요! <br>
	<a href="main.html">첫 화면으로 이동</a>
	
	-${requestScope.errorMsg}-

</body>
</html>