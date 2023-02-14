<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
</head>
<body>
<h2>게시글 작성</h2>

 <form name="writeForm" action="${pageContext.request.contextPath}/Notice/insertNotice" accept-charset="utf-8" method="post" >


		<div>
			제목 <input name="noticeTitle" size="80" placeholder="제목을 입력해주세요">
		</div>
		<div>
        내용
        <textarea name="noticeContent"  rows="4" cols="80" placeholder="내용을 입력해주세요"></textarea>
    </div>

    <div style="width:650px; text-align: center;">
        <button type="button" id="btnSave">확인</button>
        <button type="reset">취소</button>
    </div>
</form>
</body>
</html>
