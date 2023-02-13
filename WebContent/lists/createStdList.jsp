<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>momogong</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
<title>스터디 생성</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="stdlist.css" />

</head>

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
	<section class="wrapper style2 align-center">
		<div class="inner">
			<div class="index align-left">
				<!-- 내용 -->
				<section>
					<header>
						<h3> 스터디 등급</h3>
					</header>
					<div class="content">
						<form name="writeForm" action="StdList/insertList" method="post" onSubmit='return checkValid()'>
							<div class="fields">
								<div class="field">
									<ul class="actions">
										<li><a href="#" class="button primary" name=grade>premium</a></li>
										<li><a href="#" class="button" name=grade>free</a></li>
									</ul>
								</div>
				</section>
				<section>
					<header>
						<h3>스터디 이름</h3>
					</header>
					<div class="content">
						<div class=fields>
							<div class="field">
								<label for="roomTitle" /> <input type="text" name="roomTitle"
									id="roomTitle" placeholder="스터디 이름을 입력하세요." />
							</div>
						</div>
					</div>
				</section>
				<section>
					<header>
						<h3>스터디 타입</h3>
					</header>
					<div class="content">
						<div class="field">
							<label for="category" /> <select name="category" id="category">
								<option value="">타입을 지정해주세요.</option>
								<option value="1">자격증</option>
								<option value="2">수능</option>
								<option value="3">취업</option>
								<option value="4">자율</option>
							</select>
						</div>
					</div>
				</section>
				<section>
					<header>
						<h3>상세설명</h3>
					</header>
					<div class="content">
						<div class="field">
							<label for="roomDesc" />
							<textarea name="roomDesc" id="roomDesc" rows="6"></textarea>
						</div>
					</div>
				</section>
				<section>
					<header>
						<h3>최대 가입 인원</h3>
					</header>
					<div class="content">
						<div class="field">
							<label for="maxMem" /> <select name="maxMem" id="maxMem">
								<option value="4">4</option>
								<option value="8">8</option>
							</select>
						</div>
					</div>
				</section>
				<section>
					<header>
						<h3>상세설명</h3>
					</header>
					<div class="content">
						<div class="field">
							<label for="roomDesc" />
							<textarea name="roomDesc" id="roomDesc" rows="6"></textarea>
						</div>
					</div>
				</section>

				<section>
					<header>
						<h3>스터디 비밀번호</h3>
					</header>
					<div class="content">
						<div class=fields>
							<div class="field">
								<label for="roomPw" /> <input type="password" name="roomPw"
									id="roomPw" placeholder="스터디 비밀번호를 입력하세요." />
							</div>
						</div>
					</div>
				</section>
			</div>

			<div class='align-center'>
				<input type="submit" values="스터디 만들기" class="button primary fit">
			</div>

			</form>
		</div>
	</section>

</body>
</html>
