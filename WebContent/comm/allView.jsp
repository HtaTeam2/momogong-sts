<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  <link rel="stylesheet" href="list.css"/> -->
<title>커뮤니티 목록</title>
</head>
<body>
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h2>커뮤니티</h2>
			</div>
		</div>



		<!-- board list area -->
		<div id="board-list">
			<div class="container">
				<table class="board-table">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">구분</th>
							<th scope="col">제목</th>
							<th scope="col">글쓴이</th>
							<th scope="col">등록일</th>
							<th scope="col">조회수</th>
							<th scope="col">추천수</th>
						</tr>
					</thead>
					<tbody>
					
					<c:choose>
						<c:when test="${empty requestScope.list}">
							<p align="center"><b><span style="font-size:20pt;">등록된 커뮤니티 글이 없습니다.</span></b></p>
						</c:when>
						
						<c:otherwise>
							<c:forEach items="${requestScope.list}" var="c">
								<tr>
									<td>${c.comNo}</td>
									<td>${c.subject}</td>
									<td><a href="${pageContext.request.contextPath}/Community/view/${c.comNo}">${c.comTitle}</a></td>
									<td>${c.studymembers.id}</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${c.comRegdate}"/></td>
									<td>${c.comViewCount}</td>
								</tr>
							
							
							</c:forEach>
						
						
						</c:otherwise>
					
					</c:choose>
					
					
					</tbody>
				</table>
			</div>
		</div>

		<!-- board seach area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap">
							<label for="search" class="blind">커뮤니티 내용 검색</label> <input
								id="search" type="search" name="" placeholder="검색어를 입력해주세요."
								value="">
							<button type="submit" class="btn btn-dark">검색</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>
</body>
</html>