<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    request.setAttribute("nowTimestamp", System.currentTimeMillis());
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Admin Products Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Products.css?v=2">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<main class="admin-container">
  <h2>Filtra Prodotti</h2>
  
  <!-- Se ci sono errori -->
  <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>

  <!-- Filtra per Fornitore -->
  <form action="${pageContext.request.contextPath}/Filter_by_admin" method="get" class="filter-group">
    <input type="hidden" name="action" value="supplier">
    <label for="supplierInput">Fornitore:</label>
    <input type="text" id="supplierInput" name="supplier" onchange="validateFormElement(this, supplierPattern, document.getElementById('errorSupplier'), errorSupplierMessage)">
<span id="errorSupplier"></span><br>
    <button type="submit" onclick="return validateProductsForm(event)"><i class="fas fa-search"></i> Cerca</button>
  </form>

  <!-- Filtra per Prezzo -->
  <form action="${pageContext.request.contextPath}/Filter_by_admin" method="get" class="filter-group">
    <input type="hidden" name="action" value="price">
    <label for="min">Prezzo Minimo:</label>
    <input type="number" name="priceMin" id="min" step="0.01"  onchange="validateFormElement(this, pricePattern, document.getElementById('errorPriceMin'), errorPriceMessage)">
  <span id="errorPriceMin"></span><br>
    <label for="max">Prezzo Massimo:</label>
    <input type="number" name="priceMax" id="max" step="0.01" onchange="validateFormElement(this, pricePattern, document.getElementById('errorPriceMax'), errorPriceMessage)">
  <span id="errorPriceMax"></span><br>
    <button type="submit" onclick="return validateProductsForm(event)"><i class="fas fa-euro-sign"></i> Cerca</button>
  </form>

  <!-- Filtra per Anno di pubblicazione -->
  <form action="${pageContext.request.contextPath}/Filter_by_admin" method="get" class="filter-group">
    <input type="hidden" name="action" value="yearUpload">
    <label for="year">Anno di pubblicazione:</label>
    <input type="number" name="yearUpload" id="year" min="2000" max="2100" onchange="validateFormElement(this, yearPattern, document.getElementById('errorYear'), errorYearMessage)">
  <span id="errorYear"></span><br>
    <button type="submit" onclick="return validateProductsForm(event)"><i class="fas fa-calendar"></i> Cerca</button>
  </form>

  <!-- Filtra per Nome prodotto -->
  <form action="${pageContext.request.contextPath}/Filter_by_admin" method="get" class="filter-group">
    <input type="hidden" name="action" value="name">
    <label for="name">Nome prodotto:</label>
    <input type="text" name="name" id="name" onchange="validateFormElement(this, namePattern, document.getElementById('errorName'), errorProductNameMessage)">
<span id="errorName"></span><br>
    <button type="submit" onclick="return validateProductsForm(event)"><i class="fas fa-tag"></i> Cerca</button>
  </form>
  
  <h2>Elimina Prodotto</h2>
  
  <!-- Form per eliminare un prodotto tramite ID -->
<form action="${pageContext.request.contextPath}/Remove_product" method="post" class="filter-group">
<!--  <input type="hidden" name="action" value="idProduct"> -->
  <label for="idProduct">Elimina prodotto con ID:</label>
  <input type="number" name="idProduct" id="idProduct" onchange="validateFormElement(this, idPattern, document.getElementById('errorIdDelete'), errorIdMessage)">
  <span id="errorIdDelete"></span><br>
  <button type="submit" onclick="return validateIdProduct)(event)"><i class="fas fa-trash-alt"></i> Elimina</button>
</form>
  
  <h2>Modifica Prodotto</h2>
  
  <form action="${pageContext.request.contextPath}/LoadProductForUpdate" method="get" class="filter-group">
   <label for="idProduct">Modifica prodotto con ID:</label>
   <input type="number" name="idProduct" id="idProduct" onchange="validateFormElement(this, idPattern, document.getElementById('errorIdUpdate'), errorIdMessage)">
  <span id="errorIdUpdate"></span><br>
   <button type="submit" class="action-btn" onclick="return validateIdProduct(event)">
    <i class="fas fa-edit"></i> Modifica Prodotto
  </button>
  </form>

<h2>Aggiungi Prodotto</h2>

<div class="admin-actions">
<a href="${pageContext.request.contextPath}/admin/view/add_product.jsp" class="action-btn">
    <i class="fas fa-plus-circle"></i> Aggiungi Prodotto
  </a>
  </div>
  
  <!-- Lista prodotti -->
  <div class="products-grid">
  <c:forEach var="product" items="${products}">
    <div class="product-card">
    <a href="${pageContext.request.contextPath}/Product?idProduct=${product.idProduct}">
  <img src="${product.imagePath}" alt="${product.name}" class="product-image"/>
</a>
      <div class="product-info">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
       <c:choose>
  <c:when test="${product.discountPercentage != null && product.discountPercentage > 0 && (product.dateExpirationDiscount == null || product.dateExpirationDiscount.time>nowTimestamp)}">
    <p>
      <strong>Prezzo:</strong>
      <span class="prezzo_pieno">€ ${product.price}</span>
      <span class="prezzo_scontato">
        € ${product.price - (product.price * product.discountPercentage / 100)}
      </span>
    </p>
  </c:when>
  <c:otherwise>
    <p><strong>Prezzo:</strong> € ${product.price}</p>
  </c:otherwise>
</c:choose>
      </div>
    </div>
  </c:forEach>
 </div>

</main>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/product_validation.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/scripts/id_validation.js" type="text/javascript"></script>
</html>
