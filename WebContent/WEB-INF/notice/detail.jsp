<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
    <h2>Detail page</h2>
    <table border="1">
        <th> 정보 </th>
        <th> 데이터 </th>
        <tr>
            <td>작성일자</td>
            <td><fmt:formatDate value="${ndto.noticeRegdate}" pattern="yyyy-MM-dd" />
            </td>
        </tr>
        <tr>
            <td>글번호</td><td>${data.no}</td>
        </tr>
        <tr>
            <td>이름</td><td>${data.name}</td>
        </tr>
        <tr>
            <td>goal</td><td>${data.goal}</td>
        </tr>
        <tr><td>ID</td><td>${data.id}</td></tr>
    </table>
</body>
</html>
