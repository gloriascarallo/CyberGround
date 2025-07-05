<%@page import="bean.RegisteredUser_has_method_paymentBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
RegisteredUser_has_method_paymentBean methods=(RegisteredUser_has_method_paymentBean)request.getAttribute("user_methods_payment");
 %>

<html>
<head>
<meta charset="UTF-8">
<title>Personal Methods Page</title>
</head>
<body>

<h2>Qui sono mostrati tutti i tuoi metodi di pagamento</h2>

<form action="RegisteredUserMethodsPayment" method="post">
<c:forEach var="user_methods" items="${methods}">
	<label>
	<input type="radio" name="idMetodo" value="${user_methods.id_has_method_payment}" required>${user_methods.pan} - ${user_methods.expirationDate} - ${user_methods.cvc} (${user_methods.usernameRegisteredUser})
	</label><br>
</c:forEach>

<input type="submit" value="Salva metodo di pagamento">

</form>

</body>
</html>