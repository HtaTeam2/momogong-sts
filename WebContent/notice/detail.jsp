<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 59d22dfe75dc202126bff9090d54b5b434776fc2
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div class="row" style="margin-bottom:20px; margin-left:1px;">
<div class="col-lg-12">
<<<<<<< HEAD
<h1 class="page-header">상세 페이지</h1>
=======
<h2 class="page-header">상세 페이지</h2>
>>>>>>> 59d22dfe75dc202126bff9090d54b5b434776fc2
</div>
</div>

<div class="panel" style="margin-left:1px;">
<div id="contAreaBox">
<div class="panel">
<div class="panel-body">
<form role="form" action="${pageContext.request.contextPath}/Notice/updateNotice/${dto.noticeNo}" method="get">
<div class="table-responsive" style="text-align:center;">
<table id="datatable-scroller"
	class="table table-bordered tbl_Form">
	<caption></caption>
	<colgroup>
		<col width="250px" />
		<col />
	</colgroup>
	<tbody>
		<tr>
			<th class="active" >글번호</th>
			<td>
				${dto.noticeNo}
				
			</td>
		</tr>
		<tr>
			<th class="active">글내용</th>
			<td>
				${dto.noticeContent}
			</td>
		</tr>
		<tr>
			<th class="active" >작성일</th>
			<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.noticeRegdate}"/>
			</td>
		</tr>
		<tr>
			<th class="active" >글제목</th>
			<td>
			${dto.noticeTitle}
			</td>
		</tr>
		<tr>
			<th class="active" >조회수</th>
			<td>
			${dto.viewCount}
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="margin-left:1px;">
<<<<<<< HEAD
<a href="/Notice/list" class="btn btn-primary">목록</a>
<button type="submit">수정</button>
</form>
=======

	<input type="button" value="목록으로"
				Onclick="location.href='${pageContext.request.contextPath}/Notice/list'">
<button type="submit">수정</button>
</form>

>>>>>>> 59d22dfe75dc202126bff9090d54b5b434776fc2
<form id="delFrm" action="${pageContext.request.contextPath}/Notice/deleteNotice/${dto.noticeNo}" method="post">
	<input type="hidden" name="noticeNo" value="${dto.noticeNo}">
	<button type="submit" >삭제</button>
</form>

</div>
</div>
</div>
</div>
</div>


<<<<<<< HEAD
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div class="row" style="margin-bottom:20px; margin-left:1px;">
<div class="col-lg-12">
<h1 class="page-header">상세 페이지</h1>
</div>
</div>

<div class="panel" style="margin-left:1px;">
<div id="contAreaBox">
<div class="panel">
<div class="panel-body">
<form role="form" action="${pageContext.request.contextPath}/Notice/updateNotice/${dto.noticeNo}" method="get">
<div class="table-responsive" style="text-align:center;">
<table id="datatable-scroller"
	class="table table-bordered tbl_Form">
	<caption></caption>
	<colgroup>
		<col width="250px" />
		<col />
	</colgroup>
	<tbody>
		<tr>
			<th class="active" >글번호</th>
			<td>
				${dto.noticeNo}
				
			</td>
		</tr>
		<tr>
			<th class="active">글내용</th>
			<td>
				${dto.noticeContent}
			</td>
		</tr>
		<tr>
			<th class="active" >작성일</th>
			<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.noticeRegdate}"/>
			</td>
		</tr>
		<tr>
			<th class="active" >글제목</th>
			<td>
			${dto.noticeTitle}
			</td>
		</tr>
		<tr>
			<th class="active" >조회수</th>
			<td>
			${dto.viewCount}
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="margin-left:1px;">
<a href="/Notice/list" class="btn btn-primary">목록</a>
<button type="submit">수정</button>
</form>
<form id="delFrm" action="${pageContext.request.contextPath}/Notice/deleteNotice/${dto.noticeNo}" method="post">
	<input type="hidden" name="noticeNo" value="${dto.noticeNo}">
	<button type="submit" >삭제</button>
</form>

</div>
</div>
</div>
</div>
</div>

>>>>>>> 8fd569769644628d30742270ebebd4fb54e9e322
=======
>>>>>>> 59d22dfe75dc202126bff9090d54b5b434776fc2
