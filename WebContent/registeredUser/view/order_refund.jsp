<%@page import="bean.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.getAttribute("order");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Order refund Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Order_refund.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2><i class="fas fa-undo-alt"></i>Prodotti nell'ordine ${order.idOrder}: seleziona di quale farne il reso</h2>

<div id="container">

<form action="${pageContext.request.contextPath}/Product_refund">
<c:forEach var="user_order" items="${order}">
    <label>
	<input type="radio" name="idProduct_in_order" value="${user_order.product_in_order.id_product_in_order}">
	<img alt="Immagine ${user_order.product_in_order.product.name}" src="${pageContext.request.contextPath}${user_order.product_in_order.product.imagePath}"/>
	<p>Nome Prodotto:${user_order.product_in_order.product.name}</p><br>
	<p>Prezzo Prodotto:${user_order.product_in_order.product.price}</p><br>
	<p>Prezzo Totale:${user_order.product_in_order.SubTotal}</p>
	</label><br>
</c:forEach>
<input type="submit" value="Seleziona prodotto">
</form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>