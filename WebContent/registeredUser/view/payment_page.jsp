<%@page import="bean.RegisteredUser_has_method_paymentBean"%>
<%@page import="bean.RegisteredUser_has_addressBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<% 
request.getSession().getAttribute("cart");
request.getAttribute("user_addresses");
request.getAttribute("user_methods_payment");
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Payment Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Payment_page.css?v=2"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<c:forEach var="product_incart" items="${cart}">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    Nome: ${product_incart.product.name}<br>
    Prezzo: ${product_incart.totalPrice}<br>
</c:forEach>
    
<h2>Seleziona indirizzo e metodo di pagamento</h2>

<div id="container">
<form action="${pageContext.request.contextPath}/Payment" method="post">
<div>
<label for="indirizzo">Indirizzo</label> <br>
<select name="indirizzo" id="indirizzo">
<c:forEach var="user_address" items="${user_addresses}">
	<option value="${user_address.id_has_address}">${user_address.nameAddress}</option>
	</c:forEach>
</select>
</div>

<span>Vuoi aggiungere un nuovo indirizzo? <a href="./add_address.jsp">Clicca qui!</a></span>

<div>
<label for="metodo_pagamento">Metodo di pagamento</label> <br>
<select name="metodo_pagamento" id="metodo_pagamento">
<c:forEach var="user_methods" items="${user_methods_payment}">
	<option value="${user_methods.id_has_method_payment}">${user_methods.pan} - ${user_methods.expirationDate} - ${user_methods.cvc}</option>
	</c:forEach>
</select>
</div>

<span>Vuoi aggiungere un nuovo metodo di pagamento? <a href="./add_payment_method.jsp">Clicca qui!</a></span>

<div>
<input type="submit" value="Conferma pagamento">
</div>
</form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>