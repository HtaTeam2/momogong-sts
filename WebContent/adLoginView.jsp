<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% System.out.println("adLoginView.jsp 실행 확인 ---------- "); %>
<html>
<head>
<meta charset="UTF-8">
<title>adLoginView.jsp</title>
</head>

<body>

	<h3>관리자 모드 메인화면</h3>
	<br>
	<a href="${pageContext.request.contextPath}/StdMembers/logout.html">로그아웃</a><br>
	회원 정보 보기
	<!-- view Form  -->
	<form action="/allView">
	
	<%--
		새로운 이론 (그냥 외워)
		- EL tag의 특징
			: ${cvo.id} 내부적인 실행 흐름
				${requestScope.cvo.id} 가 무효한 경우 즉 request에 cvo가 없을 경우
				${sessionScope.cvo.id} 순으로 자동 변환하면서 출력
				
				아 그래서 그냥 단순하게 저렇게만 해도 된다..ㅇㅋ
				=> import customerVo 필요없어짐
	 --%>
		<!-- 개인 정보 수정하는 update.jsp에서 활용될 데이타를 hidden tag로 설정 -->

		<table border="1" cellspacing="1" width="60%">
			<tr>
				<td width=30%>사용자 아이디</td>
				<td width=70%>${dto.id}</td>
			</tr>
			<tr>
				<td width="30%">비밀번호</td>
				<td width="70%">${dto.password}</td>
			</tr>
			<tr>
				<td width="30%">닉네임</td>
				<td width="70%">${dto.nickname}</td>
			</tr>
			<tr>
				<td width="30%">이메일 주소</td>
				<td width="70%">${dto.email}</td>
			</tr>
		</table>
		<br>
		<br>
		<!-- 
			http://ip:port/context명/update.jsp
			${pageContext.request.contextPath} : 코드 사용 권장
			즉, 현 jsp의 실행 위치가 어디에 있든 http://ip:port/context명/ 을 의미하는 코드
		 -->
		<input type="button" value="update" 
				Onclick="location.href='${pageContext.request.contextPath}/update.jsp'">&nbsp;
		<input type="submit" value="allView">
	</form>
</body>
</html>
