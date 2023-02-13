<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/f51a30e87b.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../css/join.css">
<title>회원가입 페이지</title>
</head>
<script>
	//아이디 길이 제한
	function blankCheckId(f) {
		var id = f.id.value;
		id = id.trim();
		if (id.length < 5) {
			alert("아이디는 5자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	
	//닉네임 길이 제한
	function blankCheckNick(f) {
		var id = f.id.value;
		id = id.trim();
		if (id.length < 5) {
			alert("닉네임은 4자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	
	function idCheck() {
		//새창 만들기
		window.open("check", "idwin", "width=400, height=350");
	}
</script>

<body>
	<div class="wrap">
		<div class="join">
			<h2>회원가입</h2><span style="color: red; font-weight: bold">* 필수입력</span>
			<form action="${pageContext.request.contextPath}/StdMembers/insert"
				method="post">
				<div class="join_a">
					<h4>*아이디</h4>
					<input type="text" name="id" placeholder="아이디를 입력해주세요."
						minlength="5" maxlength="20" onsubmit="return blankCheckId(this)">
					<input type="button" value="ID중복확인" onclick="idCheck()">
				</div>
				<div class="join_a">
					<h4>이메일</h4>
					<input type="text" name="email" placeholder="이메일을 입력해주세요."
						maxlength="100">
				</div>
				<div class="join_grade">
					<h4>등급</h4>
					<label><input type="radio" name="grade" value="free"
						checked> free </label> <label><input type="radio"
						name="grade" value="premium"> premium </label>
				</div>
				<div class="join_a">
					<h4>닉네임</h4>
					<input type="text" name="nickname" placeholder="닉네임을 입력해주세요."
						minlength="4" maxlength="30"
						onsubmit="return blankCheckNick(this)">
				</div>
				<div class="join_a">
					<h4>*비밀번호</h4>
					<input type="password" name="password" placeholder="비밀번호를 입력해주세요."
						maxlength="16">
				</div>
				<div class="submit">
					<input type="submit" value="회원가입">
				</div>

				<div class="text">
					<p>sns계정으로 시작하기</p>
				</div>
				<div class="join_sns">
					<li><a href=""><i class="fa-sharp fa-solid fa-n"></i></a></li>
					<li><a href=""><i class="fa-solid fa-comment"></i></a></li>
				</div>

			</form>
		</div>
	</div>
</body>
</html>