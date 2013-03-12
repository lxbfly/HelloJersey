<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Staff Page</title>
<style type="text/css">
	#create-staff {
		width: 600px;
		border: 1px solid blue;
		margin: 0px auto;
		padding: 0 10% 1% 10%;
	}
</style>
</head>
<body>
	<div id="create-staff">
		<form action="../../rest/staff" method="POST">
			<h2> Create Staff </h2>
			<hr>
			<label for="name"><b>Staff Name : </b></label>
			<input name="name" type="text" width="50px;"/>
			<br />
			<label for="age"><b>Staff Age : </b></label>
			<input name="age" type="text" width="50px;"/>
			<br />
			<label for="Career"><b>Staff Career : </b></label>
			<input name="career" type="text" width="50px;"/>
			<br />
			<input type="submit" value="Submit"/>
			<input type="reset" value=" Reset "/>
		</form>
	</div>
	
</body>
</html>