<%@page import="model.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Product Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Product.css?v=4">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Pagina Prodotto</h2>
<div id="container">
<%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>
    <img src="${product.imagePath}" alt="Product image" />
    <p>Nome: ${product.name}</p>
    <p>Descrizione: ${product.description}</p>
    <c:choose>
 <c:when test="${product.discountPercentage != null && product.discountPercentage > 0 && (product.dateExpirationDiscount == null || product.dateExpirationDiscount.time>nowTimestamp)}">
    <p>
      <strong>Prezzo:</strong>
      <span class="prezzo_pieno">€ ${product.price}</span>
      <span class="prezzo_scontato">
        € <fmt:formatNumber value="${product.price - (product.price * product.discountPercentage / 100)}" type="number" maxFractionDigits="2"/>
      </span>
    </p>
  </c:when>
  <c:otherwise>
    <p><strong>Prezzo:</strong> € ${product.price}</p>
  </c:otherwise>
</c:choose>
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