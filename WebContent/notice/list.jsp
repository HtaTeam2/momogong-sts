<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든 공지사항 보기</title>
</head>
<body>
   
<h3>모든 공지사항 보기</h3>

<c:if test="${not empty requestScope.allData}">
	<table border="1"  width="70%">
		<tr>
			<td width="20%">번호</td>
			<td width="20%">제목</td>
			<td width="40%">내용</td>
			<td width="20%">작성일</td>
			<td width="20%">조회수</td>
		</tr>	
		
		<c:forEach items="${requestScope.allData}" var="ndto">
		
			<tr>
				<td>${ndto.noticeNo}</td>
				<td>${ndto.noticeContent}</td>
				<td>${ndto.noticeRegdate}</td>
				<td>${ndto.noticeTitle}</td>
				<td>${ndto.viewCount}</td>
				<td>
 <button onclick='location.href="${pageContext.request.contextPath}/Notice/deleteNotice?noticeNo=${ndto.noticeNo}"'>
 	삭제하기
 </button>
				</td>
			</tr>
	
		</c:forEach>
		
	</table>
</c:if>

<c:if test="${empty requestScope.allData}">
	현 시점엔 글이 하나도 없습니다. <br>
</c:if>


<p>
<a href="${pageContext.request.contextPath}/main.html">메인으로 이동</a>

</body>
</html>
