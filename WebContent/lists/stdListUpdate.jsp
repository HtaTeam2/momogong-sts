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
</body>
</html>