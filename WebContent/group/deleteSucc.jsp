<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% System.out.println("myStudy.jsp 호출확인"); %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 스터디</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
​<jsp:include page="/header.jsp"></jsp:include>
  
<h1>탈퇴가 완료되었습니다.</h1>

<a href='${pageContext.request.contextPath}/main.jsp'>메인으로 이동하기</a>

</body>
</html>
