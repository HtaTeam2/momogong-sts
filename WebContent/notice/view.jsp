<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.domain.entity.Notice"%>
<%@ page import="model.domain.NoticeDTO"%>
<%@ page import="model.NoticeDAO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	<jsp:include page="/header.jsp"></jsp:include>
	<form action="Notice/list">
		<div class="page-wrapper">
			<div class="container-fluid">
				<div class="col-lg-8">
					<!--게시판 넓이 -->
					<div class="col-lg-12">
						<h1 class="page-header">공지사항</h1>
					</div>
					<div class="row">
						<div class="col-lg-12">
	
							</button>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">공지사항</div>
                          
						<div class="panel-body">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>글번호</th>
										<th>글내용</th>
										<th>작성일</th>
										<th>글제목</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.list}" var="n">
										<tr>


											<td><a
												href="${pageContext.request.contextPath}/Notice/view/${n.noticeNo}">${n.noticeNo}</a></td>
											<td>${n.noticeContent}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
													value="${n.noticeRegdate}" /></td>
											<td>${n.noticeTitle}</td>
											<td>${n.viewCount}</td>

										</tr>
									</c:forEach>
									
								</tbody>
								
                          
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>