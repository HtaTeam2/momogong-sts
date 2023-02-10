<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<style>
div {padding:10px;}
</style>
<meta charset=UTF-8">
<head>
<title>모든 고객 보기</title>
</head>
<body>
<div style="background-color:graytext;">
	<h3>모든 고객 보기</h3>
</div>
<%-- 고객이 미 존재시 : 현존하는 고객이 없습니다.
			존재시 : table 출력 --%>

<c:if test="${not empty requestScope.allData}">

	<table border="1" width="70%">
		<tr>
			<th width="20%">id</th>
			<th width="20%">이름</th>
			<th width="40%">이메일 주소</th>
			<th width="20%">탈퇴</th>
			<th width="20%">마이페이지</th>
		</tr>	
		<c:forEach items="${requestScope.allData}" var="cus" >
			<tr>
				<td>${cus.id}</td>
				<td>${cus.name}</td>
				<td>${cus.email}</td>
				<td>
					<%-- <form action="CustomerServlet">
				    	<input type="hidden" name="id" value="${cus.id}"/>
				    	<input type="hidden" name="command" value="delete"/>			    
				    	<input type="submit" value="탈퇴하기"/>
			 		 </form> --%>
			 		 <!-- 이렇게 줄여줄 수 있다. method 방식 이외의 것들은 다 GET방식 처리. button은 GET방식-->
			 		 <button onclick='location.href="${pageContext.request.contextPath}/CustomerServlet/delete?id=${cus.id}"'>탈퇴하기</button>
				</td>
				<td>
				<!-- 요청로직: 삭제, 요청시 id값 제공해서 삭제 -->
					<form action="${pageContext.request.contextPath}/CustomerServlet/myPage">
				    	<input type="hidden" name="id" value="${cus.id}"/>
						<input type="submit" value="마이페이지" >
			 		 </form>
			 		 
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<c:if test="${empty requestScope.allData}">
	현 시점에 고객이 단 한명도 없습니다.
</c:if>
<p>
<a href="${pageContext.request.contextPath}/index.html">메인으로 이동</a>
<a href="${pageContext.request.contextPath}/CustomerServlet/logout">로그아웃</a>
</body>
</html>
