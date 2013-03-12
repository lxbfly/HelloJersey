<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Staff</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>
<style type="text/css">
	#delete-staff {
		width: 600px;
		border: 1px solid blue;
		margin: 0px auto;
		padding: 0 10% 1% 10%;
	}
</style>
<script type="text/javascript">

function deleteStaff() {
	var staffName = $("#staffName").val();
	alert(staffName);
	$.ajax({
        type: "DELETE",
        url: "http://localhost:8080/HelloJersey/rest/staff/delete",
        data: { "name": staffName },
        dataType: "html",
        success: function (mes) {
			alert("success");
        }
    });
}
</script>
</head>
<body>
	<div id="delete-staff">
	<!-- 
		<form action="../../rest/staff/delete" method="post">
	 -->
		<form id="deleteform">
			<!-- 
			<input name="_method" type="hidden" value="delete" />
			 -->
			<h2> Delete Staff </h2>
			<hr>
			<label for="name"><b>Staff Name : </b></label>
			<input id="staffName" name="name" type="text" width="50px;"/>
			<br />
			<input type="button" value="Submit" onclick="deleteStaff()"/>
			<input type="reset" value=" Reset "/>
		</form>
	</div>
</body>
</html>