<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.StudyListDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>스터디 리스트 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="assets/css/stdlist.css" />
	
</head>


<body class="is-preload">
	<!-- header -->
	<header>
	<div class="w3-container">
	    <div class="w3-section w3-bottombar w3-padding-16">
		  <img src="${pageContext.request.contextPath}/images/momogong.png" onclick='location.href="${pageContext.request.contextPath}/main.jsp"' style="width:10%" class="w3-hover-opacity">
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/StdGroup/mystudy"'>내 스터디</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/lists/createStdList.jsp"'>스터디 생성</button>
	      <button class="w3-button w3-white" onclick='location.href="${pageContext.request.contextPath}/Community/list"'>커뮤니티</button>
	      <button class="w3-button w3-white w3-hide-small" onclick='location.href="${pageContext.request.contextPath}/Notice/list"'>공지사항</button>
	      	 스터디 검색 : <input type="text" id="study" name="study" value=""><button id="btn1" class="w3-button w3-white w3-hide-small"><i class="fa fa-search"></i></button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/logout"'>로그아웃</button>
	      <button class="w3-button w3-white w3-hide-small w3-right" onclick='location.href="${pageContext.request.contextPath}/StdMembers/viewOne2"'>내 정보</button>
	    </div>
	</div>
	</header>
	
	<div id="searchList" class="w3-row-padding">
    <section class="wrapper style2 align-center">
        <div class="inner">
            <div class="index align-left">
                <!-- 내용 -->
                <header />
                <div class="content">
                    <form name="updateForm" action="${pageContext.request.contextPath}/StdList/updateList" method="post" accept-charset="UTF-8">
                        <div class="fields">
                            <div class="field">
                                <!-- update Form  -->
                                <table border="1" cellspacing="1" width="100%">

                                    <tr>
                                        <td>스터디 등급</td>
                                        <td>어떻게해야할까?</td>
                                    </tr>

                                    <tr>
                                        <td>스터디 이름</td>
                                        <td><input type="text" name="roomTitle" value=${sessionScope.sdto.roomTitle}></td>
                                    </tr>

                                    <tr>
                                        <td>스터디 타입</td>
                                        <td><select name="category" id="category">
                                                <option value="">타입을 지정해주세요.</option>
                                                <option value="자격증">자격증</option>
                                                <option value="수능">수능</option>
                                                <option value="취업">취업</option>
                                                <option value="자율">자율</option>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td>상세설명</td>
                                        <td><textarea name="roomDesc" id="roomDesc" rows="6" value=${sessionScope.sdto.roomDesc}></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>최대 가입 인원</td>
                                        <td>${sdto.maxMem}</td>
                                    </tr>
                                    <tr>
                                        <td>현재 가입 인원</td>
                                        <td>${sdto.memNum}</td>
                                    </tr>
                                    <tr>
                                        <td>비밀번호 수정</td>
                                        <td><input type="password" name="password" value="${sessionScope.sdto.roomPw}"></td>
                                    </tr>

                                </table>
                                <p />


                                <input type="submit" value="수정"> &nbsp; <input type="reset" value="취소">&nbsp;
                    </form>
	</div>
	<!-- axios 사용을 위한 추가 설정 -->
	<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
	<script src="main.js"></script>	
</body>
</html>
