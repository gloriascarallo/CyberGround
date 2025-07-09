<%@page import="bean.RegisteredUser_has_addressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
RegisteredUser_has_addressBean addresses=(RegisteredUser_has_addressBean)request.getAttribute("user_addresses");
 %>
 
<html>
<head>
<meta charset="UTF-8">
<title>Personal Addresses Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/RegisteredUser_addresses.css">
</head>
<body>

<h2>Qui sono mostrati tutti i tuoi indirizzi</h2>

<div id="container">
<c:forEach var="user_address" items="${addresses}">
	<label>
	${user_address.nameAddress}
	</label><br>
</c:forEach>
</div>

</body>
</html>