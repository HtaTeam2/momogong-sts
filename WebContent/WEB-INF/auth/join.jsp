<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.domain.entity.StudyMembers"%>
<%@ page import="model.StudyMembersDAO"%>
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
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="./css/join.css">
<title>회원가입 페이지</title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

</head>
<script>

	var check = false;
	let checkData;
	
	//아이디 길이 제한
	function checkId(f) {
		var id = f.id.value;
		id = id.trim();
		if (id.length < 5) {
			alert("아이디는 5자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	
	//닉네임 길이 제한
	function checkNick(f) {
		var id = f.id.value;
		id = id.trim();
		if (id.length < 5) {
			alert("닉네임은 4자 이상 입력해주십시오.");
			return false;
		}
		return true;
	}
	
	function idCheck() {
		
		var resData = document.getElementById('id').value ;
		
		if(f.id.value == ""){
			alert("아이디를 입력하세요..!");
			f.id.focus();
			return false;
		}
		
		if (f.id.value.length < 5) {
			alert("아이디는 5자 이상 입력해주십시오.");
			return false;
		}
		
		axios({
			method : 'GET',
			url : "StdMembers/checkOk?id="+resData
		
		}).then(function(resData){
			checkData =  resData.data;
			console.log("넘어온 데이터" + checkData);
			if(checkData){
				alert("사용 가능한 아이디입니다.");
			}else{
				alert("이미 사용중인 아이디입니다.");
			}
			check = true;
		}).catch(function (error) {
		    console.error(error);
		    alert("내부적인 오류로 조회할 수 없습니다. 재시도 해주십시오.");
		}); 
		
		
	}
	
	function blank() {
		//아이디 빈칸이라면 경고
		if(f.id.value == ""){
			alert("아이디를 입력하세요.");
			f.id.focus();
			return false;
		}
		//이메일 빈칸이라면 경고
		if(f.email.value == ""){
			alert("이메일을 입력하세요.");
			f.email.focus();
			return false;
		}
		//닉네임 빈칸이라면 경고
		if(f.nickname.value == ""){
			alert("닉네임을 입력하세요.");
			f.nickname.focus();
			return false;
		}
		//비밀번호 빈칸이라면 경고
		if(f.password.value == ""){
			alert("비밀번호를 입력하세요.");
			f.password.focus();
			return false;
		}
		
		//중복 체크
		if(check == false){
			alert("아이디 중복확인 해주세요.");
			return false;
		}
		
		f.submit();
		
	}
</script>

<body>
	<div class="wrap">
		<div class="join">
			<h2>회원가입</h2>
			<span style="color: red; font-weight: bold">* 필수입력</span>
			<form name="f" action="${pageContext.request.contextPath}/StdMembers/insert" method="post">
				<div class="join_a">
					<h4>*아이디</h4>
					<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요."minlength="5" maxlength="20" onsubmit="return checkId(this)">
					<input type="button" value="ID중복확인" onclick="idCheck()">
				</div>
				<div class="join_a">
					<h4>*이메일</h4>
					<input type="text" name="email" placeholder="이메일을 입력해주세요." maxlength="100">
				</div>
				<div class="join_grade">
					<h4>등급</h4>
					<label><input type="radio" name="grade" value="free"
						checked> free </label> <label><input type="radio"
						name="grade" value="premium"> premium </label>
				</div>
				<div class="join_a">
					<h4>*닉네임</h4>
					<input type="text" name="nickname" placeholder="닉네임을 입력해주세요."
						minlength="4" maxlength="30" onsubmit="return checkNick(this)">
				</div>
				<div class="join_a">
					<h4>*비밀번호</h4>
					<input type="password" name="password" placeholder="비밀번호를 입력해주세요."
						maxlength="16">
				</div>
				<div class="submit">
					<input type="button" value="회원가입" onclick="blank()">
				</div>

				<div class="text">
					<p>sns계정으로 시작하기</p>
				</div>
				<div class="join_sns">
					<li><a
						href="https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com"><i
							class="fa-sharp fa-solid fa-n"></i></a></li>
					<li><a
						href="https://accounts.kakao.com/login/?continue=https%3A%2F%2Fmy.kakao.com%2Fproduct%2FEMOTICON001%3Ft_src%3Demoticon%26t_ch%3Dweb%26t_obj%3Dbrandsearch_naver_P#login"><i
							class="fa-solid fa-comment"></i></a></li>
				</div>

			</form>
		</div>
	</div>
</body>
</html>
