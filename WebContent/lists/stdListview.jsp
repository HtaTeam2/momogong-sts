<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% System.out.println("view.jsp 실행 확인-----------"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 리스트 view</title>
</head>

<body>  

	<h3>확인용 Test</h3>
	<br>

	<form action="StdList/allView" >
	

		<table border="1" cellspacing="1" width="60%">
			<tr>
				<td width=30%>스터디 이름</td>
				<td width=70%>${sdto.roomTitle}</td>
			</tr>
			<tr>
				<td width="30%">스터디 타입</td>
				<td width="70%">${sdto.category}</td>
			</tr>
			<tr>
				<td width="30%">상세설명</td>
				<td width="70%">${sdto.roomDesc}</td>
			</tr>
			<tr>
				<td width="30%">최대 가입 인원</td>
				<td width="70%">${sdto.maxMem}</td>
			</tr>
			<tr>
				<td width="30%">비밀번호</td>
				<td width="70%">${sdto.roomPw}</td>
			</tr>
		</table>
		<br>
		<br> 
		<!-- http://ip:port/context명/update.jsp
		 ${pageContext.request.contextPath} : 코드 사용 권장
		 즉 현 jsp의 실행 위치가 어디에 있던 http://ip:port/context명/"을 의미하는 코드
		 
		 클릭했을때 update.jsp--> 
		 
		<input type="button" value="수정"  
				Onclick="location.href='${pageContext.request.contextPath}/stdListUpdate.jsp'">&nbsp;
		
		<input type="button" value="삭제"  
				Onclick="location.href='${pageContext.request.contextPath}/StdList/deleteList?roomNo=${sdto.roomNo}"'>&nbsp;
		
		
		<button onclick='location.href="${pageContext.request.contextPath}/CustomerServlet/delete?id=${cvo.id}"'>
				  	탈퇴하기
				  </button>
		
		
				 
		<input type="submit" value="allView"> 
		
	</form>


</body>
</html>
