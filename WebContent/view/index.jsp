<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>

<%
String errors=(String)request.getAttribute("errors");  
if(errors!=null && !errors.equals("")) {%>
	
	<div class="error"><%=errors%></div> <% 
}
%>

<a href="<%=request.getContextPath()%>/User">
  <img src="<%=request.getContextPath()%>/images/user_profile.jpg" alt="Profilo" width="100" height="100">
</a>

</body>
</html>