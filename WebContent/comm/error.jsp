<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/comm/table.css"
	type="text/css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", sans-serif;
	font-size: 18px;
}

input[type=text] {
	width: 300px;
	height: 32px;
	font-size: 15px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}

img {
	width: 17px;
	top: 10px;
	right: 12px;
	margin: 0;
}
</style>
<title>검색결과 없음</title>
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

	<div id="searchList" class="w3-row-padding">
		<div class="page-title">
			<h1>커뮤니티</h1>
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
								<p align="center">
									<b><span style="font-size: 15pt;">${requestScope.errorMsg}</span></b>
								</p>
							</c:when>

							<c:otherwise>
								<c:forEach items="${requestScope.list}" var="c">
									<tr>
										<td>${c.comNo}</td>
										<td>${c.subject}</td>
										<td><a
											href="${pageContext.request.contextPath}/Community/view/${c.comNo}">${c.comTitle}</a></td>
										<td>${c.studymembers.id}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${c.comRegdate}" /></td>
										<td>${c.comViewCount}</td>
									</tr>


								</c:forEach>


							</c:otherwise>

						</c:choose>


					</tbody>
				</table>
			</div>
		</div>

		<!-- board search area -->
		<div id="comm-search" class="container">
			<form method="get" name="search"
				action="${pageContext.request.contextPath}/Community/search">
				<select name="searchType">
					<option value="title" selected="selected">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select> <input type="text" placeholder="검색어 입력" name="searchText">
				<button type="submit">
					<img
						src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</button>

			</form>

			<span style='float: right'>
				<button type="button" id="list"
					onclick="location.href='${pageContext.request.contextPath}/Community/list'">목록</button>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/Community/writeform'">글쓰기</button>
			</span>
		</div>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>

</body>
</html>
