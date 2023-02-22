<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>

<script>
	function blank() {
		//빈칸입력시 경고
		if (f.id.value == "") {
			alert("아이디를 입력하세요.");
			f.id.focus();
			return false;
		}
		if (f.email.value == "") {
			alert("이메일을 입력하세요.");
			f.email.focus();
			return false;
		}

		f.submit();
	}
</script>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<form name="f"
				action="${pageContext.request.contextPath}/StdMembers/findPwd"
				method="post">
				<div class="w3-center w3-large w3-margin-top">
					<h3>비밀번호 찾기</h3>
				</div>
				<div>
					<p>
						<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요.">
						<input type="text" id="email" name="email"
							placeholder="이메일을 입력해주세요.">
					</p>
					<p class="w3-center">
						<input type="submit" value="비밀번호찾기" id=findPwdBtn
							onclick="blank()"> <input type="button" value="뒤로가기"
							onclick="history.back()"> <input type="button"
							value="메인화면으로"
							onclick="location.href='${pageContext.request.contextPath}/main.html'">
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
