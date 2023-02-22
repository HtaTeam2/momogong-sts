<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/comm/table.css"
	type="text/css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
<script src='https://kit.fontawesome.com/a076d05399.js'
	crossorigin='anonymous'></script>
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
</style>
<title>커뮤니티 글 상세페이지</title>


<script type="text/javascript">
	//수정
	function sendUpdate() {
		var pw = prompt("게시글의 비밀번호를 입력하세요.");

		if (pw) {
			document.requestForm.action = "${pageContext.request.contextPath}/Community/updateform";
			document.requestForm.comPw.value = pw;
			document.requestForm.submit();

		} else {
			return false;
		}
	}

	//삭제
	function sendDelete() {
		var pw = prompt("게시글의 비밀번호를 입력하세요.");

		if (pw) {
			document.requestForm.action = "${pageContext.request.contextPath}/Community/delete";
			document.requestForm.comPw.value = pw;
			document.requestForm.submit();
		} else {
			return false;
		}
	}
</script>

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
		<div class="container">
			<hr>
			<div class="row">
				<div class="col-md-10">
					<table class="table table-condensed">
						<thead>
							<tr align="center">
								<th width="10%">[${requestScope.dto.subject}]</th>
								<th width="60%">${requestScope.dto.comTitle}<span
									style='float: right'>글번호 : ${requestScope.dto.comNo}</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>등록일</td>
								<td>${requestScope.dto.comRegdate}</td>
							</tr>
							<tr>
								<td>글쓴이</td>
								<td><span style='float: right'>추천수 :
										${requestScope.dto.recommCount}</span> ${requestScope.dto.memberid}<span
									style='float: right'>조회수 :
										${requestScope.dto.comViewCount} &nbsp; &nbsp;</span></td>
								<%-- <td>${request.memberid}<span style='float:right'>댓글 : ${requestScope.comViewCount}</span></td> --%>


							</tr>

							<tr>
								<td colspan="2">
									<p>${requestScope.dto.comContent}</p>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- <table id="commentTable" class="table table-condensed"></table>
       <table class="table table-condensed">
           <tr>
               <td>
                   <span class="form-inline" role="form">
                       <p>
                           <div class="form-group">
                               <input type="text" id="commentParentName" name="commentParentName" class="form-control col-lg-2" data-rule-required="true" placeholder="이름" maxlength="10">
                           </div>
                           <div class="form-group">
                               <input type="password" id="commentParentPassword" name="commentParentPassword" class="form-control col-lg-2" data-rule-required="true" placeholder="패스워드" maxlength="10">
                           </div>
                           <div class="form-group">
                               <button type="button" id="commentParentSubmit" name="commentParentSubmit" class="btn btn-default">확인</button>
                           </div>
                       </p>
                           <textarea id="commentParentText" class="form-control col-lg-12" style="width:100%" rows="4"></textarea>
                   </span>
               </td>
           </tr>
       </table> -->
					<table class="table table-condensed">
						<thead>
							<tr>
								<td>

									<form name="requestForm" method="post" action="">
										<input type="hidden" name="comNo"
											value="${requestScope.dto.comNo}"> <input
											type="hidden" name="comPw" value=""> <input
											type="button" value="수정" onclick="sendUpdate()"> <input
											type="button" value="삭제" onclick="sendDelete()">
									</form>


								</td>
								<td><span style="float: none;"><button
											id="recommBtn"></button></span></td>
								<td><span style='float: right'>
										<button type="button" id="list"
											onclick="location.href='${pageContext.request.contextPath}/Community/list'">목록</button>
										<button type="button"
											onclick="location.href='${pageContext.request.contextPath}/Community/writeform'">글쓰기</button>
								</span></td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<hr />
		</div>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="../../select.js"></script>
	<script>
		let commid = '${requestScope.dto.comNo}';
		let memid = '${sessionScope.id}';
		let recommcheck = '${requestScope.recommcheck}';
	</script>
	<script src="${pageContext.request.contextPath}/comm/recomm.js"></script>
</body>
</html>
