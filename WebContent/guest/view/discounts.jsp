<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.ProductBean" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%
		request.getAttribute("productsDiscounted");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Discounts Page</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Discounts.css?v=4"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<jsp:include page="/includes/header.jsp" />

<main class="page-layout">
  
   <!-- Messaggio di errore -->
 <%
String errors=(String)request.getAttribute("errors");  
if(errors!=null && !errors.equals("")) {%>
	
	<div class="error"><%=errors%></div> <% 
}
%>

 <!-- Colonna sinistra: filtro -->
 <aside class="sidebar-filter">
<h2>Filtra per percentuale di sconto</h2>

<form action="${pageContext.request.contextPath}/Discounts" method="get">
  <label for="discountRange">Sconto minimo: <span id="discountValue">0</span>%</label><br>
  <input type="range" id="discountRange" name="discountPercentage" min="0" max="100" value="0" step="1" 
         oninput="document.getElementById('discountValue').innerText = this.value">
  <button type="submit"> <i class="fas fa-filter"></i>Filtra</button>
</form>
</aside>

 <!-- Colonna destra: prodotti -->
 <section class="discounts-container">
 
 <h2>Prodotti in sconto</h2>

<div class="products-grid">
    <c:forEach var="product" items="${productsDiscounted}">
      <div class="product-card">
        <a href="${pageContext.request.contextPath}/Product?idProduct=${product.idProduct}">
          <img src="${product.imagePath}" alt="Immagine ${product.name}" />
        </a>
        <h3>${product.name}</h3>
        <p class="price">
          <span class="discounted-price">
            € <fmt:formatNumber value="${product.price - (product.price * product.discountPercentage / 100)}" type="number" maxFractionDigits="2" />
          </span>
          <span class="original-price">€ ${product.price}</span>
        </p>
        <p class="discount-info">-${product.discountPercentage}% fino al <fmt:formatDate value="${product.dateExpirationDiscount}" pattern="dd/MM/yyyy"/></p>
      </div>
    </c:forEach>
  </div>
</section>
</main>

<jsp:include page="/includes/footer.jsp" />

</body>
</html>