<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
	session.setAttribute("id", "test1");
	session.setAttribute("password", "testpw");
%> 

<!DOCTYPE html>
<html>
<head>
<title>momogong</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6,ul,li,p,footer {font-family: "Raleway", sans-serif; font-size: 18px;}

footer {
	width: 100%; 
	bottom:0; 
}

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
</style>
</head>
<body class="w3-white w3-content" style="max-width:1600px">
​
  <!-- Header -->
  <header id="portfolio">
    <span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey" onclick="w3_open()"><i class="fa fa-bars"></i></span>
    <div class="w3-container">
	    <div class="w3-section w3-bottombar w3-padding-16">
	       
		  <img src="${pageContext.request.contextPath}/images/momogong.png" onclick='location.href="${pageContext.request.contextPath}/main.jsp"' style="width:10%" class="w3-hover-opacity">
	      <button class="w3-button w3-white" onclick='location.href="StdGroup/mystudy"'>내 스터디</button>
	      <button class="w3-button w3-white" onclick='location.href="StdList/insert"'>스터디 생성</button>
	      <button class="w3-button w3-white" onclick='location.href="Community/커뮤니티 전체select"'>커뮤니티</button>
	      <button class="w3-button w3-white w3-hide-small" onclick='location.href="Notice/공지사항 전체select"'>공지사항</button>
	      	 스터디 검색 : <input type="text" id="study" name="study" value=""><button id="btn1" class="w3-button w3-white w3-hide-small"><i class="fa fa-search"></i></button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="StdMembers/logout"'>로그아웃</button>
	    </div>
    </div>
  </header>
  
  
<!--    <div>
    <h1><b>list</b></h1>
    현재 진행중인 스터디 리스트
    <div class="w3-row-padding" style="margin:0 -16px">
      <div class="w3-third w3-margin-bottom">
        <ul class="w3-ul w3-border w3-white w3-center w3-opacity w3-hover-opacity-off">
         
          <li class="w3-padding-30">현재 로그인 상태가 아닙니다. 로그인 후 확인하여 주십시오.</li>
          
        </ul>
      </div>
  </div> -->
  
  <!-- 추천스터디 1 배열로 3개까지 나열? rownum..-->
  <div id="searchList" class="w3-row-padding">
  	 <div class="w3-section w3-bottombar w3-padding-16">
	  <span> </span>
	<p style="font-size: 25px;font-weight: bold;color:#5a5a5a;"><i class="fa fa-th-list" style="font-size:25px;"></i>카테고리별 스터디 보기</p>
      <button id="cate" class="w3-button w3-white" value="All">All</button>
      <button id="cate" class="w3-button w3-white" value="자율">자율</button>
      <button id="cate" class="w3-button w3-white" value="자격증">자격증</button>
      <button id="cate" class="w3-button w3-white" value="수능">수능</button>
      <button id="cate" class="w3-button w3-white" value="취업">취업</button>
    </div>
    <div>
	    <div class="w3-third w3-container w3-margin-bottom">
	      <img src="./images/profile.png" alt="Norway" style="width:100%" class="w3-hover-opacity">
	        <p class="w3-center"><b>추천 스터디명 1</b></p>
	    </div>
	    
	    <div class="w3-third w3-container w3-margin-bottom">
	      <img src="./images/profile.png" alt="Norway" style="width:100%" class="w3-hover-opacity">
	        <p class="w3-center"><b>추천 스터디명 1</b></p>
	    </div>
	    
	    <div class="w3-third w3-container w3-margin-bottom">
	      <img src="./images/profile.png" alt="Norway" style="width:100%" class="w3-hover-opacity">
	        <p class="w3-center"><b>추천 스터디명 1</b></p>
	    </div>
	</div>
  </div> 
    <!-- 외부로 별도로 분리해 놓은 js 파일만 include -->
  <!-- axios 사용을 위한 추가 설정 -->
  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="main.js"></script>
</body>
</html>