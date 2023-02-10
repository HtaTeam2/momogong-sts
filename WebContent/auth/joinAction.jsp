<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.StudyMembersDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="model.domain.StudyMembersDTO" scope="page" />
<jsp:setProperty name="dto" property="id" />
<jsp:setProperty name="dto" property="password" />	
<jsp:setProperty name="dto" property="grade" /> 
<jsp:setProperty name="dto" property="email" /> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	// ---> 2.
		if (dto.getId() == null || dto.getPassword() == null || dto.getEmail() == null
			|| dto.getGrade() == null ) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {  // ---> 3.
			StudyMembersDAO studyMembersDAO = new StudyMembersDAO();
			int result = studyMembersDAO.insertMember(dto);
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else  {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href = 'main.jsp'");
				script.println("</script>");
			}	
		}
	%>
</body>
</html>