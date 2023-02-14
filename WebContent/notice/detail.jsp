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

