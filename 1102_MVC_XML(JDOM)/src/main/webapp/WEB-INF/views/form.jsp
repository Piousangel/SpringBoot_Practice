<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JDOM Test</h1>
	<form action="add" method="post">
		<label for="name">이름:</label>
		<input type="text" name="name" id="name"/><br/>
		<label for="b_day">생일:</label>
		<input type="text" name="b_day" id="b_day"/><br/>
		<label for="phone">연락처:</label>
		<input type="text" name="phone" id="phone"/><br/>
		<input type="button" value="보내기" onclick="exe(this.form)"/>
	</form>
	<script>
		function exe(frm){
			//유효성검사 생략
			
			frm.submit();
		}
	</script>
</body>
</html>