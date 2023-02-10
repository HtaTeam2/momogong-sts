<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f51a30e87b.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./join.css">
	<title>회원가입 페이지</title>
</head>
<body>
    <div class="wrap">
        <div class="join">
            <h2>회원가입</h2>
             <form action="StdMembers/insert" method="post">
	            <div class="join_a">
	                <h4>아이디</h4>
	                <input type="text" name="id" placeholder="아이디를 입력해주세요.">
	            </div>
	            <div class="join_a">
	                <h4>이메일</h4>
	                <input type="text" name="email" placeholder="이메일을 입력해주세요.">
	            </div>
	            <div class="join_grade">
	                <h4>등급</h4>
	                <label><input type="radio" name="grade" value="free" checked> free </label>
	                <label><input type="radio" name="grade" value="premium" > premium </label>
	            </div>
	           	<div class="join_a">
	                <h4>닉네임</h4>
	                <input type="text" name="email" placeholder="닉네임을 입력해주세요.">
	            </div>
	            <div class="join_a">
	                <h4>비밀번호</h4>
	                <input type="password" name="password" placeholder="비밀번호를 입력해주세요.">
	            </div>
	           
	            <div class="submit">
	                <input type="submit" value="회원가입">
	            </div>
	            <div class="text">
	                <p> sns계정으로 시작하기</p>
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