<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>공지사항</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">	
</head>
<body>
	<!-- header -->
	<header>
		<div class="w3-container">
			<div class="w3-section w3-bottombar w3-padding-16">
				<img src="${pageContext.request.contextPath}/images/momogong.png"
					onclick='location.href="${pageContext.request.contextPath}/main.jsp"'
					style="width: 10%" class="w3-hover-opacity">
				<button class="w3-button w3-white"
					onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내
					스터디</button>
				<button class="w3-button w3-white"
					onclick='location.href="${pageContext.request.contextPath}/lists/createStdList.jsp"'>스터디
					생성</button>
				<button class="w3-button w3-white"
					onclick='location.href="${pageContext.request.contextPath}/Community/list"'>커뮤니티</button>
				<button class="w3-button w3-white w3-hide-small"
					onclick='location.href="${pageContext.request.contextPath}/Notice/list"'>공지사항</button>
				스터디 검색 : <input type="text" id="study" name="study" value="">
				<button id="btn1" class="w3-button w3-white w3-hide-small">
					<i class="fa fa-search"></i>
				</button>
				<button class="w3-button w3-white w3-hide-small w3-right"
					onclick='location.href="${pageContext.request.contextPath}/StdMembers/logout"'>로그아웃</button>
				<button class="w3-button w3-white w3-hide-small w3-right"
					onclick='location.href="${pageContext.request.contextPath}/StdMembers/viewOne2"'>내
					정보</button>
			<c:if test="${sessionScope.id == 'admin'}">
				<button class="w3-button w3-white w3-hide-small w3-right" 
				 	onclick='location.href="${pageContext.request.contextPath}/StdMembers/adPage"'>관리자메뉴</button>
			 </c:if>
			</div>
		</div>
	</header>

	<div id="searchList" class="w3-row-padding" div
		style="text-align: center;">


		<form action="${pageContext.request.contextPath}/Notice/list"
			method="get">


			<!-- <form action="Notice/list"> -->
			<div class="page-wrapper">
				<div class="container-fluid">
					<div class="col-lg-8">
						<!--게시판 넓이 -->
						<div class="col-lg-12">
							<!--  	<h1 class="page-header">게시글 작성</h1>-->
						</div>
						<div class="row">
							<div class="col-lg-12"></div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">게시글 작성</div>

							<div class="panel-body">

									<div>
										제목 <input name="noticeTitle" size="80"
											placeholder="제목을 입력해주세요">
									</div>
									<div>
										내용
										<textarea name="noticeContent" rows="4" cols="80"
											placeholder="내용을 입력해주세요"></textarea>
									</div>

									<div style="width: 650px; text-align: right;">

										<input type="button" value="확인" Onclick="location.href='${pageContext.request.contextPath}/Notice/insertNotice'"  method="post">
										<input type="button" value="목록으로" Onclick="location.href='${pageContext.request.contextPath}/Notice/list'">
								</form>
							</div>
						</div>
						<!-- axios 사용을 위한 추가 설정 -->
						<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
						<script src="main.js"></script>
					</div>
				</div>
			</div>
	</div>
	</div>
	</div>
