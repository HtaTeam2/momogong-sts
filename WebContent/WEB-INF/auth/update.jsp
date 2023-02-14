<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>

<%@ page import="java.io.PrintWriter"%>
<%@ page import="model.domain.entity.StudyMembers"%>
<%@ page import="model.StudyMembersDAO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<title>개인 정보 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<br>
	<center>
	<h3>개인 정보 수정하기</h3>
	<br>
	<form action="${pageContext.request.contextPath}/StdMembers/update" method="post">
		  <!-- update Form  -->	
		  <table border="1" cellspacing="1" width="60%">
			  <tr>
				<td width=30%>아이디</td>
				<td width=70%>	
					${allData.id}		
				</td>
			  </tr>
			  <tr>
				<td width="30%">등급</td>
				<td width="70%">	
					${allData.grade}	
				</td>
			  </tr>
			  <tr>
				<td width="30%">비밀번호 수정</td>
				<td width="70%">
					<input type="password" name="password" value="${allData.password}">
				</td>
			  </tr>			  
			
			  <tr>
			
				<td width="30%">이메일 수정</td>
				<td width="70%">
					<input type="text" name="email" value="${allData.email}">
				</td>		  
			  <tr>	
			  <tr>
			
				<td width="30%">닉네임 수정</td>
				<td width="70%">
					<input type="text" name="nickname" value="${allData.nickname}">
				</td>		  
			  <tr>		
			  <tr>
			
				<td width="30%">목표 수정</td>
				<td width="70%">
					<input type="text" name="goal" value="${allData.goal}">
				</td>		  
			  <tr>			  
		</table>  
		<p/>
	
		<%-- hidden tag완성 하세요
				 value값으로 설정해야 하는 값은? 
		--%>
	
		<input type="submit" value="수정" > &nbsp;
		<input type="reset" value="취소">&nbsp;
	</form>

</body>
</html>