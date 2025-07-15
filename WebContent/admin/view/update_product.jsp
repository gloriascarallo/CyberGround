<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="bean.ProductBean" %>
<!DOCTYPE html>

<%
    request.getAttribute("product");
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin Update Product Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Update_product.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<main class="add-product-container">

  <h2>Modifica Prodotto</h2>

   <!-- Se ci sono errori -->
  <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>

  <form action="${pageContext.request.contextPath}/Update_product" method="post" enctype="multipart/form-data" class="product-form">

    <!-- idProduct hidden -->
    <input type="hidden" name="idProduct" value="${product.id}">

    <label for="name">Nome prodotto:</label>
    <input type="text" id="name" name="name" value="${product.name}" onchange="validateFormElement(this, namePattern, document.getElementById('errorName'), errorNameMessage)">
<span id="errorName"></span><br>

    <label for="price">Prezzo (€):</label>
    <input type="number" id="price" name="price" step="0.01" value="${product.price}" onchange="validateFormElement(this, pricePattern, document.getElementById('errorPrice'), errorPriceMessage)">
<span id="errorPrice"></span><br>

    <label for="discountPercentage">Percentuale Sconto (%):</label>
    <input type="number" id="discountPercentage" name="discountPercentage" step="0.01" value="${product.discountPercentage != null ? product.discountPercentage : ''}" onchange="validateFormElement(this, discountPattern, document.getElementById('errorDiscount'), errorDiscountMessage)">
<span id="errorDiscount"></span><br>

    <label for="dateExpirationDiscount">Data Scadenza Sconto:</label>
    <input type="date" id="dateExpirationDiscount" name="dateExpirationDiscount" value="${empty product.dateExpirationDiscount ? '' : product.dateExpirationDiscount}"  onchange="validateFormElement(this, datePattern, document.getElementById('errorDate'), errorDateMessage)">
<span id="errorDate"></span><br>

    <label for="description">Descrizione:</label>
    <textarea id="description" name="description" onchange="validateFormElement(this, descriptionPattern, document.getElementById('errorDescription'), errorDescriptionMessage)">${product.description}</textarea>
<span id="errorDescription"></span><br>

    <label for="supplier">Fornitore:</label>
    <input type="text" id="supplier" name="supplier" value="${product.supplier}" onchange="validateFormElement(this, supplierPattern, document.getElementById('errorSupplier'), errorSupplierMessage)">
<span id="errorSupplier"></span><br>

    <label for="category">Categoria:</label>
    <input type="text" id="category" name="category" value="${product.categoryName}" onchange="validateFormElement(this, categoryPattern, document.getElementById('errorCategory'), errorCategoryMessage)">
<span id="errorCategory"></span><br>

    <label for="quantityAvailable">Quantità Disponibile:</label>
    <input type="number" id="quantityAvailable" name="quantityAvailable" value="${product.quantityAvailable}" onchange="validateFormElement(this, quantityPattern, document.getElementById('errorQuantity'), errorQuantityMessage)">
<span id="errorQuantity"></span><br>

    <label for="productImgFile">Immagine Prodotto (carica per sostituire):</label>
    <input type="file" id="productImgFile" name="productImgFile" accept="image/*" onchange="validateFormElement(this, imagePattern, document.getElementById('errorImage'), errorImageMessage)">
<span id="errorImage"></span><br>

    <button type="submit" class="btn-submit" onclick="return validateProductForm()"><i class="fas fa-pen"></i> Modifica Prodotto</button>
  </form>
</main>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/product_validation.js" type="text/javascript"></script>
</html>
