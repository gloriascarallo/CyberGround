<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<% 
CartBean cart=(CartBean)request.getSession().getAttribute("cart");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Page</title>
</head>
<body>

<c:forEach var="product_incart" items="${cart}">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    Nome: ${product_incart.product.name}<br>
    Prezzo: ${product_incart.totalPrice}<br>
</c:forEach>
    
<h2>Seleziona indirizzo e metodo di pagamento</h2>

<div>
Indirizzo <br>
<select name="indirizzo" id="indirizzo">
	<option value=""> </option>
</select>
</div>

Vuoi aggiungere un nuovo indirizzo? <a href="./add_address.jsp">Clicca qui!</a>

<div>
Metodo di pagamento <br>
<select name="metodo_pagamento" id="metodo_pagamento">
	<option value=""> </option>
</select>
</div>

Vuoi aggiungere un nuovo metodo di pagamento? <a href="./add_payment_method.jsp">Clicca qui!</a>

<div align="right">
<form action="${pageContext.request.contextPath}/Payment">
<input type="submit" value="Conferma pagamento">
</form>
</div>

</body>
</html>