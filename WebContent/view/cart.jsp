<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@page import="bean.Product_situatedin_cartBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.CartBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
CartBean cart=(CartBean)request.getSession().getAttribute("cart");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>

</head>
<body>

<form action="${pageContext.request.contextPath}/Payment_page">
<c:forEach var="product_incart" items="${cart}">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    Nome: ${product_incart.product.name}<br>
    Prezzo: ${product_incart.totalPrice}<br>
    
    <div>
    <button onclick="window.location.href='${pageContext.request.contextPath}/UpdateCart?action=decrease&product_incartID=${product_incart.id}'">-</button>
     ${product_incart.quantity}
     <button onclick="window.location.href='${pageContext.request.contextPath }/UpdateCart?action=increase&product_incartID=${product_incart.id}'">+</button>
    </div>
    
    <button onclick="window.location.href='${pageContext.request.contextPath}/UpdateCart?action=remove&product_incartID=${product_incart.id}'">Rimuovi</button>
    
</c:forEach>

    <input type="submit" value="Payment">
    </form>
    
</body>
</html>


