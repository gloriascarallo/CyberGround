<%@page import="bean.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
OrderBean orders=(OrderBean)request.getAttribute("orders");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Personal Orders Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Orders.css">
</head>
<body>

<h2>Qui sono mostrati tutti i tuoi ordini</h2>

<c:forEach var="user_orders" items="${orders}">
	<div id="order-box">
	<p>Id Ordine: ${user_orders.idOrder}</p>
	<a href="${pageContext.request.contextPath}/Product">
    <img src="${user_orders.product_in_order.product.imagePath}" alt="Product image"/>
    </a>
    <p>Nome Prodotto: ${user_orders.product_in_order.product.name}</p>
    <p>Prezzo Prodotto: ${user_orders.product_in_order.price}<p>
    <p>Quantità Prodotto: ${user_orders.product_in_order.quantity}<p>
	<p>Data Acquisto: ${user_orders.datePurchase}<p>
	<p>Data Spedizione: ${user_orders.dateShipping}<p>
	<p>Data Consegna: ${user_orders.dateDelivery}<p>
	<p>Prezzo Totale: ${user_orders.totalOrder}<p>
	</div>
</c:forEach>

</body>
</html>