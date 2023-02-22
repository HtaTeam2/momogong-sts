<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
<title>스터디 생성</title>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lists/stdlist.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">


</head>
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", sans-serif
}

td {
	font-family: "Raleway", sans-serif;
	font-size: 18px;
	align: center;
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

footer {
	width: 100%;
	bottom: 0;
}
</style>
<script type="text/javascript">
	function checkValid() {
		var f = window.document.writeForm;

		if (f.roomTitle.value == "") {
			alert("스터디 이름을 입력해 주세요.");
			return false;
		}
		if (f.category.value == "") {
			alert("스터디 타입을 선택해 주세요.");
			return false;
		}

		return true;
	}
</script>


<body class="is-preload">
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

		<section class="wrapper style2 align-center">
			<div class="inner">
				<div class="index align-left">
					<!-- 내용 -->
					<section>
						<div class="content">
							<form name="writeForm"
								action="${pageContext.request.contextPath}/StdList/insertList"
								method="post" accept-charset="UTF-8"
								onSubmit='return checkValid()'>
								<div class="fields">
									<div class="field">
										<table border="1" cellspacing="1" width="100%">
											<tr>
												<td>스터디등급</td>
												<td><label><input type="radio" name="grade"
														value="free" checked> free </label> <label><input
														type="radio" name="grade" value="premium"> premium
												</label></td>
												</div>
											<tr>
												<td>스터디 이름</td>
												<td>
													<div class="content">
														<div class=fields>
															<div class="field">
																<label for="roomTitle" /> <input type="tel"
																	name="roomTitle" id="roomTitle"
																	placeholder="스터디 이름을 입력하세요." />
															</div>
														</div>
												</td>
											</tr>
											<tr>
												<td>스터디 타입</td>
												<td>
													<div class="content">
														<div class="field">
															<label for="category" /> <select name="category"
																id="category">
																<option value="">타입을 지정해주세요.</option>
																<option value="자격증">자격증</option>
																<option value="수능">수능</option>
																<option value="취업">취업</option>
																<option value="자율">자율</option>
															</select>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>상세설명</td>
												<td>
													<div class="content">
														<div class="field">
															<label for="roomDesc" />
															<textarea name="roomDesc" id="roomDesc" rows="6"
																placeholder="스터디 상세 설명을 입력하세요."></textarea>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>최대 가입 인원</td>
												<td>
													<div class="content">
														<div class="field">
															<label for="maxMem" /> <select name="maxMem" id="maxMem">
																<option value="4">4</option>
																<option value="8">8</option>
															</select>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td>스터디 비밀번호</td>
												<td>
													<div class="content">
														<div class=fields>
															<div class="field">
																<label for="roomPw" /> <input type="password"
																	name="roomPw" id="roomPw"
																	placeholder="스터디 비밀번호를 입력하세요." />
															</div>
														</div>
													</div>
												</td>
											</tr>

										</table>

										<div class='align-center'>


											<input type="submit" value="스터디 만들기"
												class="button primary fit">
										</div>
							</form>
						</div>
					</section>
				</div>
				<!-- axios 사용을 위한 추가 설정 -->
				<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
				<script src="/../team_studyroom/select.js"></script>
</body>
</html>
