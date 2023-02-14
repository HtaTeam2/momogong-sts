<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
 
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<title>Insert title here</title>
 
</head>
<form action='<c:url value='/Notice/insertNotice'/>' method="post">
 
<body>
 
        <table width="500" cellpadding="0" cellspacing="0" border="1">

			
			 
<tr>
				<td width=30% align=center>글 제목</td>
				<td width=70%><input type="text" name="noticeTitle">
				</td>
			</tr>
 
	<tr>
				<td width=30% align=center>글 내용</td>
				<td width=70%><input type="text" name="noticeContent">
				</td>
			</tr>
			

 
<tr >
 
<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
 
</tr>
 
</form>
 
</table>
 
 
</body>
 
</html>