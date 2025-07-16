<%@page import="bean.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Order refund Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Order_refund.css?v=2">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2><i class="fas fa-undo-alt"></i>Prodotti nell'ordine ${order.idOrder}: seleziona di quale farne il reso</h2>

<div id="container">

<form action="${pageContext.request.contextPath}/Product_refund">
<c:forEach var="user_order" items="${order.products_in_order}">
    <label class="radio-block">
	<input type="radio" name="idProduct_in_order" value="${user_order.id_product_in_order}">
	<img alt="Immagine ${user_order.product.name}" src="${user_order.product.imagePath}"/>
	<p>Nome Prodotto: ${user_order.product.name}</p><br>
	<p>Prezzo Prodotto: €${user_order.price}</p><br>
	<p>Prezzo Totale: €${user_order.subtotal}</p>
	</label><br>
</c:forEach>
<input type="submit" value="Seleziona prodotto">
</form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>