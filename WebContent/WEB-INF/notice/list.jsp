<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모든 공지사항 보기</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">	
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
td{font-family: "Raleway", sans-serif; font-size: 18px; align:center;}
input[type=text] {
	width: 500px;
	height: 32px;
	font-size: 15px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}
footer {
	width: 100%; 
	bottom:0; 
}
</style>
</head>
<body>
	<!-- header -->
	<header>
	<div class="w3-container">
	    <div class="w3-section w3-bottombar w3-padding-16">
		  <img src="${pageContext.request.contextPath}/images/momogong.png" onclick='location.href="${pageContext.request.contextPath}/main.jsp"' style="width:10%" class="w3-hover-opacity">
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내 스터디</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/lists/createStdList.jsp"'>스터디 생성</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/Community/list"'>커뮤니티</button>
	      <button class="w3-button w3-white w3-hide-small" onclick='location.href="${pageContext.request.contextPath}/Notice/list"'>공지사항</button>
	      	 스터디 검색 : <input type="text" id="study" name="study" value=""><button id="btn1" class="w3-button w3-white w3-hide-small"><i class="fa fa-search"></i></button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/logout"'>로그아웃</button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/viewOne2"'>내 정보</button>
			<c:if test="${sessionScope.id == 'admin'}">
				<button class="w3-button w3-white w3-hide-small w3-right" 
				 	onclick='location.href="${pageContext.request.contextPath}/StdMembers/adPage"'>관리자메뉴</button>
			 </c:if>	    
		</div>
	</div>
	</header>
<div id="searchList" class="w3-row-padding">   
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
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>	
</body>
</html>
