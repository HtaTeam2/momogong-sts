<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>공지사항</title>
</head>
<body>
<form action="Notice/list">
<div class="page-wrapper">
    <div class="container-fluid">
        <div class="col-lg-8"><!--게시판 넓이 -->
            <div class="col-lg-12">
                <h1 class="page-header">공지사항 </h1>
            </div>
            <div class="row">
                  <div class="col-lg-12">
                      <button type="button" Onclick="location.href='write.jsp'" class="btn btn-outline btn-primary pull-right">
                          <i class="fa fa-edit fa-fw"></i> 공지사항 작성
                      </button>
                  </div>
              </div>
            <div class="panel panel-default">
                <div class="panel-heading">공지사항 </div>
      <!--          <a href="Notice/list">모두보기</a>  -->
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>글번호</th>
                                <th>글내용</th>
                                <th>작성일</th>
                                <th>글제목</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.allData}" var="ndto">
                            <tr>
                                <td>${ndto.noticeNo}</td>
                                <td>${ndto.noticeContent}</td>
                              <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${ndto.noticeRegdate}"/></td>
                                 <td>${ndto.noticeTitle}</td>
                                <td>${ndto.viewCount}</td>
                                
                                
                         
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
