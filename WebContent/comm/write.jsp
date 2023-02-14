<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.domain.CommunityDTO, model.domain.StudyMembersDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% session.setAttribute("id", "test1"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/comm/ckeditor/ckeditor.js"></script>
<title>Insert title here</title>
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
</head>
 	<jsp:include page="/header.jsp"></jsp:include>
	<script>
	
	function checkValid() {
	    var f = window.document.writeForm;
			
		if ( f.comTitle.value == "" ) {
			alert( "제목을 입력해 주세요." );
			return false;
		}
		if ( f.subject.value == "" ) {
			alert( "카테고리를 선택해 주세요." );
			return false;
		}
		if ( f.comPw.value == "" ) {
	        alert( "비밀번호를 입력해 주세요" );
	        return false;
	    }
		if ( CKEDITOR.instances.content.getData() == "") {
	        alert( "글 내용을 입력해 주세요." );
	        return false;
	    }
	    return true;
	}
	</script>

<body>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="text-center">게시글 쓰기</h2>
        <form name="writeForm" action="${pageContext.request.contextPath}/Community/write" accept-charset="utf-8" method="post" onsubmit="return checkValid()">
          <table class="table table-striped">
            <tr>
                <td>작성자</td>
                <td>${sessionScope.id}<input type="hidden" name="memberid" value="${sessionScope.id}"></td>
                
            </tr>
            <tr>
                <td>제목</td>
                <td><input type="text"  class="form-control" name="comTitle"></td>
            </tr>
            <tr>
                <td>카테고리</td>
                <td>
	                <select name="subject">
				    <option value="">-----선 택-----</option>
				    <option value="시험 후기">시험 후기</option>
				    <option value="잡담">잡담</option>
				    <option value="스터디원 모집">스터디원 모집</option>
				    <option value="정보 공유">정보 공유</option>
					</select>
				</td>
            </tr>
             
            <tr>
                <td>비밀번호</td>
                <td><input type="password"  class="form-control" name="comPw"></td>
            </tr>
             
            <tr>
                <td>글내용</td>
                <td><textarea rows="10" cols="50" id="content" name="comContent" class="form-control"></textarea>
                <script type="text/javascript"> //글쓰기editor, 사진업로드 기능
				//CKEDITOR 적용 
				CKEDITOR.replace('content', {
				    filebrowserUploadUrl:'${pageContext.request.contextPath}/Community/fileUpload.do',     
				    width:'100%',
				    height:'350'
				         
				});
				</script>
                </td>
            </tr>
            <tr>
                 
                <td colspan="2"  class="text-center">
                    <input type="submit" value="글쓰기" class="btn btn-success">
                    <!-- <input type="reset" value="다시작성" class="btn btn-warning"> -->
                    <button type="button"  class="btn btn-primary">전체 게시글보기</button>
                </td>
            </tr>
             
          </table>
        </form>
    </div>
</div>

</body>
</html>