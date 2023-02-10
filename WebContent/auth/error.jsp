<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러 처리 page</title>
</head>
<body>

	문제 발생. 잠시후 재 시도해 주세요<br>
	<a href="${pageContext.request.contextPath}/main.html">메인 화면으로 이동</a>
	
	-${requestScope.errorMsg}-
</body>
</html>