<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		width: 350px;
		border-collapse: collapse;
	}
	table th, table td{
		border: 1px solid black;
	}

</style>
</head>
<body>
	<h1>MyBatis-Spring 비동기식 연습</h1>
	
	<table id="t1">
		<colgroup>
			<col width="15%"/>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<thead>
			<tr>
				<td colspan="3">
					<input type="button" value="전체" id="total_btn"/>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs=" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
		$(function(){
			
			//total_btn을 클릭했을 때
			$("#total_btn").bind("click", function(){
				
				$.ajax({
					url: "total",
					type: "post",
					dataType: "json",
				}).done(function(data){
					console.log(data);
					
				});
			});
		});
	</script>
	
</body>
</html>