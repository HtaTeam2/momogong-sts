<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter"%>
<%@ page import="model.domain.entity.StudyMembers"%>
<%@ page import="model.StudyMembersDAO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>udpataeSuccess.jsp</title>
</head>
<body>
	<center>
		축하합니다. 회원정보 update 성공하셨습니다. 
		<a href="${pageContext.request.contextPath}/StdMembers/selfView">모두 보기</a>
	</center>
</body>
</html>