<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업데이트 성공</title>
</head>
<body onload='succ()'>

	<script type="text/javascript">
		function succ() {
			alert('성공적으로 수정했습니다.');
			window.location.href = "../main.jsp";
		}
	</script>
</body>
</html>