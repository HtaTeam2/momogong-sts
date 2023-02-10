<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.domain.StudyMembersDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
//view.jsp에서 세션에 저장된  객체 획득해서 update를 위한 화면에 데이타 출력

%>


<html>
<head>
<meta charset="UTF-8">
<title>개인 정보 수정</title>
</head>
<body>

<br>
<center>
<h3>관리자 정보 수정하기</h3>
<br>

<form action="StdMembers/update" method="post">
	  <!-- update Form  -->	
	  <table border="1" cellspacing="1" width="60%">
		  <tr>
			<td width=30%>관리자 아이디</td>
			<td width=70%>	
				${sessionScope.dto.id}			
			</td>
		  </tr>
		  <tr>
			<td width="30%">이름</td>
			<td width="70%">	
				${sessionScope.dto.nickname}	
			</td>
		  </tr>
		  <tr>
			<td width="30%">비밀번호 수정</td>
			<td width="70%">
				<input type="password" name="password" value="${sessionScope.dto.password}">
			</td>
		  </tr>			  
		
		  <tr>
		
			<td width="30%">이메일 주소</td>
			<td width="70%">
				<input type="type" name="email" value="${sessionScope.dto.email}">
			</td>		  
		  <tr>				  
	</table>  
	<p/>

	<%-- hidden tag완성 하세요
			 value값으로 설정해야 하는 값은? 
	--%>
	<input type="hidden" value="update"  name="command">

	<input type="submit" value="수정" > &nbsp;
	<input type="reset" value="취소">&nbsp;
	<input type="button" value="모두보기" Onclick="location.href='StdMembers/allView'">
</form>

</body>
</html>
