<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="bean.RegisteredUserBean"%>

<%
RegisteredUserBean user=(RegisteredUserBean)request.getAttribute("user");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Hello, <%=user.getName() %></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/User.css">
</head>
<body>

<div class="container">
<img src="${user.imagePath}" alt="User image"></img>
<p>Username: ${user.username}</p>
<p>Name: ${user.name} </p>
<p>Last Name: ${user.lastName}</p>
<p>Telephone number: ${user.telephone}</p>
<p>Email: ${user.email}</p>
<p>Password: ${user.password}</p>
</div>

</body>
</html>