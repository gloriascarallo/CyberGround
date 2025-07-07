<%@page import="bean.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
OrderBean order=(OrderBean)request.getAttribute("order");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order refund Page</title>
</head>
<body>

<h2>Prodotti nell'ordine: seleziona di quale farne il reso</h2>

<form action="${pageContext.request.contextPath}/Product_refund">
<c:forEach var="user_order" items="${order}">
    <label>
	<input type="radio" name="idProdotto" value="${user_order.product_in_order.id_product_in_order}" required>
	<img alt="Immagine ${user_order.product_in_order.product.name}" src="${pageContext.request.contextPath}${user_order.product_in_order.product.imagePath}"/>
	${user_order.product_in_order.product.name}<br>
	${user_order.product_in_order.SubTotal}
	</label><br>
</c:forEach>
<input type="submit" value="Seleziona prodotto">
</form>

</body>
</html>