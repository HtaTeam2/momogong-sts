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
<form role="form" action="/Notice/view" method="post">
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
				${ndto.noticeNo}
				
			</td>
		</tr>
		<tr>
			<th class="active">글내용</th>
			<td>
				${ndto.noticeContent}
			</td>
		</tr>
		<tr>
			<th class="active" >작성일</th>
			<td>
			${ndto.noticeRegdate}
			</td>
		</tr>
		<tr>
			<th class="active" >글제목</th>
			<td>
			${ndto.noticeTitle}
			</td>
		</tr>
		<tr>
			<th class="active" >조회수</th>
			<td>
			${ndto.viewCount}
			</td>
		</tr>
	</tbody>
</table>
</div>
<div style="margin-left:1px;">
<a href="/Notice/list" class="btn btn-primary">목록</a>
</div>
</form>
</div>
</div>
</div>
</div>


