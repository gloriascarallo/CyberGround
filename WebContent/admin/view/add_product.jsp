<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Admin Add Product Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Add_product.css?v=2">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<main class="add-product-container">
  <h2>Aggiungi Nuovo Prodotto</h2>

  <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/Add_product" method="post" enctype="multipart/form-data" class="product-form">
  
    <label for="name">Nome prodotto:</label>
<input type="text" id="name" name="name" onchange="validateFormElement(this, namePattern, document.getElementById('errorName'), errorProductNameMessage)">
<span id="errorName"></span>

<label for="price">Prezzo (€):</label>
<input type="number" id="price" name="price" onchange="validateFormElement(this, pricePattern, document.getElementById('errorPrice'), errorPriceMessage)">
<span id="errorPrice"></span>

<label for="discountPercentage">Sconto (%):</label>
<input type="number" id="discountPercentage" name="discountPercentage" onchange="validateFormElement(this, discountPattern, document.getElementById('errorDiscount'), errorDiscountMessage)">
<span id="errorDiscount"></span>

<label for="dateExpirationDiscount">Data scadenza sconto:</label>
<input type="date" id="dateExpirationDiscount" name="dateExpirationDiscount"  onchange="validateFormElement(this, datePattern, document.getElementById('errorDate'), errorDateMessage)">
<span id="errorDate"></span>

<label for="description">Descrizione:</label>
<textarea id="description" name="description" rows="4" onchange="validateFormElement(this, descriptionPattern, document.getElementById('errorDescription'), errorDescriptionMessage)"></textarea>
<span id="errorDescription"></span>

<label for="supplier">Fornitore:</label>
<input type="text" id="supplier" name="supplier" onchange="validateFormElement(this, supplierPattern, document.getElementById('errorSupplier'), errorSupplierMessage)">
<span id="errorSupplier"></span>

<label for="category">Categoria:</label>
<input type="text" id="category" name="category" onchange="validateFormElement(this, categoryPattern, document.getElementById('errorCategory'), errorCategoryMessage)">
<span id="errorCategory"></span>

<label for="quantityAvailable">Quantità disponibile:</label>
<input type="number" id="quantityAvailable" name="quantityAvailable" onchange="validateFormElement(this, quantityPattern, document.getElementById('errorQuantity'), errorQuantityMessage)">
<span id="errorQuantity"></span>

<label for="productImgFile">Immagine prodotto:</label>
<input type="file" id="productImgFile" name="productImgFile" onchange="validateFormElement(this, imagePattern, document.getElementById('errorImage'), errorImageMessage)">
<span id="errorImage"></span>

    <button type="submit" class="btn-submit" onclick="return validateProductForm()"><i class="fas fa-plus-circle"></i> Aggiungi prodotto</button>
  </form>
</main>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/product_validation.js" type="text/javascript"></script>
</html>
