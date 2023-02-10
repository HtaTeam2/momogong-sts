<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.domain.StudyMembersDTO, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	System.out.println("list.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view/auth/list.jsp</title>
</head>
<body>
	<h3>모든 회원 리스트</h3>
	<%-- 고객이 미존재시 : 존재하는 고객이 없습니다. 존재할 경우 table 출력 --%>
	<c:if test="${not empty requestScope.allData}">
		<table border="1" width="70%">
			<tr>
				<td width="10%">id</td>
				<td width="15%">닉네임</td>
				<td width="30%">이메일 주소</td>
				<td width="10%">회원 등급</td>
				<td width="20%">가입일</td>
				<td width="15%">회원 삭제</td>
			</tr>
			<c:forEach items="${requestScope.allData}" var="dto">
				<c:if test="${dto.grade != admin}">
					<tr>
						<td>${dto.id}</td>
						<td>${dto.nickname}</td>
						<td>${dto.email}</td>
						<td>${dto.grade}</td>
						<td>${dto.regdate}</td>
						<td>
							<button
								onclick='location.href="${pageContext.request.contextPath}/StdMembers/delete?id=${dto.id}"'>
								삭제하기</button>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
	<%-- ArrayList의 size()=0 즉 데이터가 없을 경우에만 true --%>
	<c:if test="${empty requestScope.allData}">
		한명의 회원도 없습니다.
	</c:if>
	<a href="${pageContext.request.contextPath}/index.html">메인으로 이동</a>
	<a href="${pageContext.request.contextPath}/StdMembers/logout.html">로그아웃</a>
</body>
</html>
