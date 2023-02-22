<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	System.out.println("main_res.jsp: " + request.getAttribute("data")); 
%>
${requestScope.data}