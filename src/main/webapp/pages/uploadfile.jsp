<%@page import="com.sun.research.ws.wadl.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
	String scheme = request.getScheme();
	String serverPath = request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String basePath = scheme + "://" + serverPath + "/";
%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="UploadPage" method="post" enctype="multipart/form-data" action="<%=basePath %>rest/io/upload">
		   Mobile: <input type="text" name="mobileNo"><br>
		   Choose File To Upload: <input type="file" name="fileUpload"><br>
		   <input type="submit" value="Upload...">
	</form>
</body>
</html>