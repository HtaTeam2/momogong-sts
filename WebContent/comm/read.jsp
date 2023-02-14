<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<<<<<<< HEAD
<link rel="stylesheet" href="${pageContext.request.contextPath}/comm/table.css" type="text/css"/>
<title>커뮤니티 글 상세페이지</title>

	<script type="text/javascript">
	//수정
	function sendUpdate(){
		document.requestForm.action="${pageContext.request.contextPath}/Community/updateform";
		document.requestForm.submit();
	}
	
	//삭제
	function sendDelete(){
		var pw = prompt("게시글의 비밀번호를 입력하세요.");
		
		if(pw){
			document.requestForm.action="${pageContext.request.contextPath}/Community/delete";
			document.requestForm.comPw.value=pw;
			document.requestForm.submit();
		}else{
			return false;
		}
	}
	
	
	
	
	</script>
=======
<title>커뮤니티 글 상세페이지</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}
		
		td{font-family: "Raleway", sans-serif; font-size: 18px; align:center;}
		
		input[type=text] {
			width: 500px;
			height: 32px;
			font-size: 15px;
			border: 0;
			border-radius: 15px;
			outline: none;
			padding-left: 10px;
			background-color: rgb(233, 233, 233);
		}
		
		footer {
			width: 100%; 
			bottom:0; 
		}
	</style>
>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<div class="container">
    <hr>
<div class="row">
    <div class="col-md-10">
        <table class="table table-condensed">
            <thead>
                <tr align="center">
                    <th width="10%">[${requestScope.dto.subject}]</th>
<<<<<<< HEAD
                    <th width="60%">${requestScope.dto.comTitle}<span style='float:right'>글번호 : ${requestScope.dto.comNo}</span></th>
=======
                    <th width="60%">${requestScope.dto.comTitle}</th>
>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>작성일</td>
                    <td>${requestScope.dto.comRegdate}</td>
                </tr>
                <tr>
                    <td>글쓴이</td>
                    <td>${requestScope.dto.memberid}<span style='float:right'>조회 : ${requestScope.dto.comViewCount}</span></td>
                    <%-- <td>${request.memberid}<span style='float:right'>추천수 : ${requestScope.comViewCount}</span></td>
                    <td>${request.memberid}<span style='float:right'>댓글 : ${requestScope.comViewCount}</span></td> --%>
               </tr>
               <tr>
                   <td colspan="2">
                       <p>${requestScope.dto.comContent}</p>
                   </td>
               </tr>
           </tbody>
       </table>
       <!-- <table id="commentTable" class="table table-condensed"></table>
       <table class="table table-condensed">
           <tr>
               <td>
                   <span class="form-inline" role="form">
                       <p>
                           <div class="form-group">
                               <input type="text" id="commentParentName" name="commentParentName" class="form-control col-lg-2" data-rule-required="true" placeholder="이름" maxlength="10">
                           </div>
                           <div class="form-group">
                               <input type="password" id="commentParentPassword" name="commentParentPassword" class="form-control col-lg-2" data-rule-required="true" placeholder="패스워드" maxlength="10">
                           </div>
                           <div class="form-group">
                               <button type="button" id="commentParentSubmit" name="commentParentSubmit" class="btn btn-default">확인</button>
                           </div>
                       </p>
                           <textarea id="commentParentText" class="form-control col-lg-12" style="width:100%" rows="4"></textarea>
                   </span>
               </td>
           </tr>
       </table> -->
       <table class="table table-condensed">
           <thead>
               <tr>
                   <td>
                       <span style='float:right'>
<<<<<<< HEAD
                               <button type="button" id="list" onclick="location.href='${pageContext.request.contextPath}/Community/list'">목록</button>
                               <button type="button" onclick="location.href='${pageContext.request.contextPath}/Community/writeform'" >글쓰기</button>
                      </span>
                           		<form name="requestForm" method="post" action="">
                           			<input type="hidden" name="comNo" value="${requestScope.dto.comNo}">
                           			<input type="hidden" name="comPw" value="">
                           			<input type="button" value="수정" onclick="sendUpdate()">
                           			<input type="button" value="삭제" onclick="sendDelete()">
                           		</form>
                           
                           
=======
                               <button type="button" id="list" class="btn btn-default">목록</button>
                               <button type="button" id="modify" class="btn btn-default">수정</button>
                               <button type="button" id="delete" class="btn btn-default">삭제</button>
                               <button type="button" id="write" class="btn btn-default">글쓰기</button>
                           </span>
>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
                       </td>
                   </tr>
               </thead>
           </table>
</div>
            </div>
            <hr/>
        </div>    
    </body>
</html>