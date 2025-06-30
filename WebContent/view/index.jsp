<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String errors=(String)request.getAttribute("errors");  
if(errors!=null && !errors.equals("")) {%>
	
	<div class="error"><%=errors%></div> <% 
}
%>

<a href="<%=request.getContextPath()%>/User">
  <img src="<%=request.getContextPath()%>/images/user_profile.jpg" alt="Profilo">
</a>

</body>
</html>