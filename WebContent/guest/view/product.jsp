<%@page import="bean.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
ProductBean pr = (ProductBean)request.getAttribute("product");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Product Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Product.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<h2>Pagina Prodotto</h2>
<div id="container">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${pr.imagePath}" alt="Product image" />
    </a>
    <p>Nome: ${pr.name}</p>
    <p>Descrizione: ${pr.description}</p>
    <p>Prezzo: ${pr.price}</p>
    <p>Fornitore: ${pr.supplier}</p>
    <p>Quantità disponibili: ${pr.quantityAvailable}</p>
    
    <form action="${pageContext.request.contextPath}/AddToCart" method="post">
    <input type="hidden" name="productID" value="${pr.id}">
    <button type="submit" class="add-to-cart-btn">
      <i class="fas fa-cart-plus"></i> Aggiungi al carrello
    </button>
    </form>
</div>
</body>
</html>