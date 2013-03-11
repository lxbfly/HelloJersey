<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 900px;height: 120px;border: 1px solid blue; text-align: center;">
		<form action="../rest/contacts" method="POST">
				<h2> Create Staff </h2>
			<label for="name"><b>Staff Name : </b></label>
			<input name="name" type="text" width="50px;"/>
			<br />
			<label for="Career"><b>Staff Career : </b></label>
			<input name="career" type="text" width="50px;"/>
			
			<input type="submit" value="Submit" />
		</form>
	</div>
	
</body>
</html>