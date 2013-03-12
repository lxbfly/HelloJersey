<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Staff</title>
<style type="text/css">
	#delete-staff {
		width: 600px;
		border: 1px solid blue;
		margin: 0px auto;
		padding: 0 10% 1% 10%;
	}
</style>
</head>
<body>
	<div id="delete-staff">
		<form action="../../rest/staff" method="DELETE">
			<h2> Delete Staff </h2>
			<hr>
			<label for="name"><b>Staff Name : </b></label>
			<input name="name" type="text" width="50px;"/>
			<br />
			<input type="submit" value="Submit"/>
			<input type="reset" value=" Reset "/>
		</form>
	</div>
</body>
</html>