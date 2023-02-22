<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="${pageContext.request.contextPath}/comm/ckeditor/ckeditor.js"></script>
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
</style>
<title>Insert title here</title>
</head>
<script>
	function checkValid() {
		var f = window.document.updateForm;

		if (f.comTitle.value == "") {
			alert("제목을 입력해 주세요.");
			return false;
		}
		if (f.subject.value == "") {
			alert("카테고리를 선택해 주세요.");
			return false;
		}
		if (f.comPw.value == "") {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if (CKEDITOR.instances.content.getData() == "") {
			alert("글 내용을 입력해 주세요.");
			return false;
		}
		return true;
	}
</script>
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
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h2 class="text-center">게시글 수정</h2>
				<form name="updateForm"
					action="${pageContext.request.contextPath}/Community/update"
					accept-charset="utf-8" method="post" onsubmit="return checkValid()">
					<input type="hidden" name="comNo" value="${requestScope.dto.comNo}">
					<table class="table table-striped">
						<tr>
							<td>작성자</td>
							<td>${sessionScope.id}<input type="hidden" name="memberid"
								value="${sessionScope.id}"></td>

						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="comTitle"
								value="${requestScope.dto.comTitle}"></td>
						</tr>
						<tr>
							<td>카테고리</td>
							<td><select name="subject">
									<option value="">-----선 택-----</option>
									<option value="시험 후기"
										<c:if test="${requestScope.dto.subject eq '시험 후기'}">selected</c:if>>시험
										후기</option>
									<option value="잡담"
										<c:if test="${requestScope.dto.subject eq '잡담'}">selected</c:if>>잡담</option>
									<option value="스터디원 모집"
										<c:if test="${requestScope.dto.subject eq '스터디원 모집'}">selected</c:if>>스터디원
										모집</option>
									<option value="정보 공유"
										<c:if test="${requestScope.dto.subject eq '정보 공유'}">selected</c:if>>정보
										공유</option>
							</select></td>
						</tr>

						<tr>
							<td>비밀번호</td>
							<td><input type="password" class="form-control" name="comPw"
								value="${requestScope.dto.comPw}"></td>
						</tr>

						<tr>
							<td>글내용</td>
							<td><textarea rows="10" cols="50" id="content"
									name="comContent" class="form-control">${requestScope.dto.comContent}</textarea>
								<script type="text/javascript">
									//글쓰기editor, 사진업로드 기능
									//CKEDITOR 적용 
									CKEDITOR
											.replace(
													'content',
													{
														filebrowserUploadUrl : '${pageContext.request.contextPath}/Community/fileUpload.do',
														width : '100%',
														height : '350'

													});
								</script></td>
						</tr>
						<tr>

							<td colspan="2" class="text-center"><input type="submit"
								value="수정" class="btn btn-success"> <!-- <input type="reset" value="다시작성" class="btn btn-warning"> -->
								<button type="button"
									onclick="location.href='${pageContext.request.contextPath}/Community/list'">전체
									게시글보기</button></td>
						</tr>

					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>
</body>
</html>
