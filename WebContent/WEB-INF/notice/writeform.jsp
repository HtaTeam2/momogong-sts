<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
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
<form name="form" method="post" action="${path}/Notice/insert">
    <div>
<h2>게시글 작성</h2>
        제목
        <input name="noticeTitle" size="80" placeholder="제목을 입력해주세요">
    </div>
    <div>
        내용
        <textarea name="noticeContent"  rows="4" cols="80" placeholder="내용을 입력해주세요"></textarea>
    </div>

    <div style="width:650px; text-align: center;">
        <button type="button" id="btnSave">확인</button>
        <button type="reset">취소</button>
    </div>
</form>
    </div>
    <!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>	
</body>
</html>
