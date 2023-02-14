<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./css/login.css">

</head>

<body>


<h1>로그인</h1>

     <div class="wrap">
        <div class="login">
            <h2>로그인</h2>
			<form name="f" action="StdMembers/Login" method="post">
            <div class="login_id">
                <h4>아이디</h4>
                <input type="id" name="" id="" placeholder="아이디를 입력해주세요.">
            </div>
            <div class="login_pw">
                <h4>비밀번호</h4>
                <input type="password" name="" id="" placeholder="비밀번호를 입력해주세요.">
            </div>
            <div class="login_cb">
                <div class="checkbox">
                    <input type="checkbox" name="" id=""> 아이디 저장
                </div>
                <div class="forgot_pw">
                    <a href="">비밀번호 찾기</a>
                </div>
            </div>
            <div class="submit">
                <input type="button" value="로그인" onclick="blank()">
            </div>
            <div class="text">
                <a href="">sns계정으로 시작하기</a>
            </div>
            <div class="login_sns">
            <li><a href=""><i class="fa-sharp fa-solid fa-n"></i></a></li>
            <li><a href=""><i class="fa-solid fa-comment"></i></a></li>
            </div>
            <div class="text">
                아직 회원이 아니신가요? &nbsp; <a href=""> 회원가입 하러가기</a>
            </div>
    	</form>
        </div>
    </div>
</body>
</html>
