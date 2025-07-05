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
</head>
<body>

<h2>Qui sono mostrati tutti i tuoi indirizzi</h2>

<form action="RegisteredUserAddresses" method="post">
<c:forEach var="user_address" items="${addresses}">
	<label>
	<input type="radio" name="idMetodo" value="${user_address.id_has_address}" required>${user_address.nameAddress} (${user_address.usernameRegisteredUser})
	</label><br>
</c:forEach>

<input type="submit" value="Salva indirizzo">

</form>
</body>
</html>