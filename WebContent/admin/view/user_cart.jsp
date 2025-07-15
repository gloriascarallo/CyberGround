<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
     request.getAttribute("products_situatedin_cart");
%>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Filter User Cart Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/User_cart.css?v=3" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

  <div class="cart-container">
    <h2>Console di Filtraggio Carrello Utente</h2>
    
    <div id="results"></div>

    <form id="filterForm">
      <input type="hidden" name="idCart" value="${idCart}" /> 

      <div class="filter-group">
        <label for="priceMin">Prezzo Min:</label>
        <input type="number" name="price" id="priceMin" step="0.01" />
      </div>

      <div class="filter-group">
        <label for="priceMax">Prezzo Max:</label>
        <input type="number" name="price" id="priceMax" step="0.01" />
      </div>

      <button type="button" class="filter-btn" onclick="filterByPrice()">
        <i class="fas fa-euro-sign"></i> Filtra per Prezzo
      </button>

      <hr />

      <div class="filter-group">
        <label for="dateAdded">Data Aggiunta:</label>
        <input type="date" id="dateAdded" />
      </div>

      <button type="button" class="filter-btn" onclick="filterByDate()">
        <i class="fas fa-calendar-day"></i> Filtra per Data
      </button>
    </form>
    
    <hr />
    
    <c:forEach var="product_incart" items="${products_situatedin_cart}">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product_incart.product.imagePath}" alt="Product image" />
    </a>
    <div id="product-${product_incart.id}">
    
    Nome: ${product_incart.product.name}<br>
    Prezzo: <span id="total-${product_incart.id}">${product_incart.totalPrice}</span><br>
    </div>
    </c:forEach>

  </div>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/filter_cart_by.js"></script>
</html>
