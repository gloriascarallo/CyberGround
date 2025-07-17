<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
     <%
    request.setAttribute("nowTimestamp", System.currentTimeMillis());
	%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Product Refund Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Product_refund.css?v=3"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Rimborso Prodotto</h2>

<div id="container">
<a href="${pageContext.request.contextPath}/Product">
    <img src="${product.product.imagePath}" alt="Product image" />
    </a>
    <p>Nome: ${product.product.name}</p>
    <p>Descrizione: ${product.product.description}</p>
     <c:choose>
  <c:when test="${product.product.discountPercentage != null && product.product.discountPercentage > 0 && (product.product.dateExpirationDiscount == null || product.product.dateExpirationDiscount.time>nowTimestamp)}">
    <p>
      <strong>Prezzo:</strong>
      <span class="prezzo_pieno">€ ${product.product.price}</span>
      <span class="prezzo_scontato">
        € <fmt:formatNumber value="${product.product.price - (product.product.price * product.product.discountPercentage / 100)}" type="number" maxFractionDigits="2"/>
      </span>
    </p>
  </c:when>
  <c:otherwise>
    <p><strong>Prezzo:</strong> € ${product.price}</p>
  </c:otherwise>
</c:choose>
    <p>Fornitore: ${product.product.supplier}</p>
    <p>Quantità disponibili: ${product.product.quantityAvailable}</p>
    </div>
    
    <br><br>

<form action="<%=request.getContextPath()%>/Success_refund" method="post" onsubmit="return validateProduct_refundForm()">
<fieldset>
<legend>Compilare i campi</legend>
<div>

	<label for="ritiro">Inserisci l'indirizzo dove verremo a ritirare il pacco:</label> <br>
	<input type="text" name="ritiro" id="ritiro" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress'), errorAddressMessage)">
	<span id="errorAddress"></span> <br>
	
	<label>Inserisci i dati della carta sulla quale verrà addebitato il rimborso</label> <br>
	<label for="PAN">PAN:</label>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN'), errorPANMessage)"> 
	<span id="errorPAN"></span> <br>
	<label for="methodPaymentScadenza1">Data di Scadenza:</label>
	<input type="text" name="Scadenza" id="methodPaymentScadenza1" placeholder="##/##" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza1'), errorScadenzaMessage)">
	<span id="errorScadenza1"></span>
	<label for="CVC">CVC:</label>
	<input type="text" name="CVC" id="CVC" placeholder="### or ####" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC'), errorCVCMessage)">
	<span id="errorCVC"></span> <br>
	<input type="submit" value="Invia">
	<input type="reset" value="Reset">
	
</div>
</fieldset>
</form>
<%@ include file="/includes/footer.jsp" %>
</body>
<script src="<%=request.getContextPath() %>/scripts/validation.js?v=4" type="text/javascript"></script>
</html>