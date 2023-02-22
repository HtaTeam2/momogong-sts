<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.StudyListDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>스터디 리스트 수정</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lists/stdlist.css" />
</head>


<body class="is-preload">
	<section class="wrapper style2 align-center">
	<p style="font-size: 36px;font-family: inherit;">방 [${lists.roomTitle}] 수정</p>
	<br>
		<div class="inner">
			<div class="index align-left">
				<!-- 내용 -->
				<header />
				<div class="content">
					<form name="updateForm"
						action="${pageContext.request.contextPath}/StdList/updatelist"
						method="post" accept-charset="UTF-8">
						<div class="fields">
							<div class="field">
								<!-- update Form  -->
								<table border="1" cellspacing="1" width="100%">
									<tr>
										<td>방번호</td>
										<td><input type="text" name="roomNo"
											value="${lists.roomNo}" readonly /></td>
									</tr>
									<tr>
										<td>등급</td>
										<td><label><input type="radio" name="grade"
												value="free" checked> free </label> <label><input
												type="radio" name="grade" value="premium"> premium </label>
										</td>
									</tr>
									<tr>
										<td>방이름</td>
										<td><input style="width: 300px;" type="text"
											name="roomTitle" value="${lists.roomTitle}" /></td>
									</tr>

									<tr>
										<td>카테고리</td>
										<td><select name="category" id="category">
												 <option value="자격증" ${lists.category == '자격증' ? 'selected' : ''}>자격증</option>
   												 <option value="수능" ${lists.category == '수능' ? 'selected' : ''}>수능</option>
    											 <option value="취업" ${lists.category == '취업' ? 'selected' : ''}>취업</option>
    											 <option value="자율" ${lists.category == '자율' ? 'selected' : ''}>자율</option>
										</select></td>
									</tr>
									<tr>
										<td>상세설명</td>
										<td><textarea name="roomDesc" id="roomDesc" rows="6">${lists.roomDesc}</textarea>
										</td>
									</tr>
									<tr>
										<td>최대 가입 인원</td>
										<td>${lists.maxMem}</td>
									</tr>
									<tr>
										<td>현재 가입 인원</td>
										<td>${lists.memNum}</td>
									</tr>
									<tr>
										<td>비밀번호 수정</td>
										<td><input type="password" name="roomPw"
											value="${lists.roomPw}"></td>
									</tr>

								</table>
								<p />


								<input type="submit" value="수정"> &nbsp; <input
									type="reset" value="취소">&nbsp;
					</form>
</body>
</html>