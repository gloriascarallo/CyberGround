<%@page import="bean.RegisteredUser_has_method_paymentBean"%>
<%@page import="bean.RegisteredUser_has_addressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<html>
<head>
<meta charset="UTF-8">
<title>Payment Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Payment_page.css"/>
</head>
<body>

<c:forEach var="product_incart" items="${cart.products}">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    Nome: ${product_incart.product.name}<br>
    Prezzo: ${product_incart.totalPrice}<br>
</c:forEach>
    
<h2>Seleziona indirizzo e metodo di pagamento</h2>

<form action="${pageContext.request.contextPath}/Payment" method="post">
<div>
Indirizzo <br>
<select name="indirizzo" id="indirizzo">
<c:forEach var="user_address" items="${addresses}">
	<option value="${user_address.id_has_address}">${user_address.nameAddress} (${user_address.usernameRegisteredUser})</option>
	</c:forEach>
</select>
</div>

Vuoi aggiungere un nuovo indirizzo? <a href="./add_address.jsp">Clicca qui!</a>

<div>
Metodo di pagamento <br>
<select name="metodo_pagamento" id="metodo_pagamento">
<c:forEach var="user_methods" items="${methods}">
	<option value="${user_methods.id_has_method_payment}">${user_methods.pan} - ${user_methods.expirationDate} - ${user_methods.cvc} (${user_methods.usernameRegisteredUser})</option>
	</c:forEach>
</select>
</div>

Vuoi aggiungere un nuovo metodo di pagamento? <a href="./add_payment_method.jsp">Clicca qui!</a>

<div>
<input type="submit" value="Conferma pagamento">
</div>

</form>

</body>
</html>