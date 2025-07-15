<%@page import="bean.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
request.getAttribute("product");
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Product Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Product.css?v=2">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Pagina Prodotto</h2>
<div id="container">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product.imagePath}" alt="Product image" />
    </a>
    <p>Nome: ${product.name}</p>
    <p>Descrizione: ${product.description}</p>
    <p>Prezzo: ${product.price}</p>
    <p>Fornitore: ${product.supplier}</p>
    <p>Quantità disponibili: ${product.quantityAvailable}</p>
    
    <form action="${pageContext.request.contextPath}/Adding_to_cart" method="post">
    <input type="hidden" name="productID" value="${product.idProduct}">
  

    <button type="submit" class="add-to-cart-btn">
      <i class="fas fa-cart-plus"></i> Aggiungi al carrello
    </button>
    </form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>