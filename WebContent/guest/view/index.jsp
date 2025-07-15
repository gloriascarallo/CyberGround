<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Index Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Index.css?v=3" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
<%@ include file="/includes/header.jsp" %>

  <!-- CATEGORIE -->
  <div class="category-bar">
    <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Telefonia" />
    <button type="submit">
      <i class="fas fa-mobile-alt"></i> Telefonia
    </button>
  </form>

  <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Computer" />
    <button type="submit">
      <i class="fas fa-laptop"></i> Computer
    </button>
  </form>

  <form action="${pageContext.request.contextPath}/Category" method="post">
    <input type="hidden" name="category" value="Console" />
    <button type="submit">
      <i class="fas fa-gamepad"></i> Console
    </button>
  </form>
  </div>

  <main>
  
<%
String errors=(String)request.getAttribute("errors");  
if(errors!=null && !errors.equals("")) {%>
	
	<div class="error"><%=errors%></div> <% 
}
%>

<h2>Filtra prodotti</h2>

<!-- Ricerca per fornitore -->
<input type="text" id="supplierInput" placeholder="Fornitore">
<button onclick="loadProducts({action:'supplier', supplier: document.getElementById('supplierInput').value})">
    Cerca per fornitore
</button>

<br><br>

<!-- Ricerca per prezzo -->
<input type="text" id="minPrice" placeholder="Prezzo minimo">
<input type="text" id="maxPrice" placeholder="Prezzo massimo">
<button onclick="loadProducts({action:'price', priceMin: document.getElementById('minPrice').value, priceMax: document.getElementById('maxPrice').value})">
    Cerca per prezzo
</button>

<br><br>

<!-- Ricerca per anno -->
<input type="text" id="yearUpload" placeholder="Anno di pubblicazione">
<button onclick="loadProducts({action:'yearUpload', yearUpload: document.getElementById('yearUpload').value})">
    Cerca per anno
</button>

<br><br>

<!-- Ricerca per nome -->
<input type="text" id="nameInput" placeholder="Nome prodotto">
<button onclick="loadProducts({action:'name', name: document.getElementById('nameInput').value})">
    Cerca per nome
</button>

<br><br>

<!-- Ricerca per sconto -->
<form action="${pageContext.request.contextPath}/Discounts" method="get">
  <input type="text" name="discountPercentage" placeholder="Sconto in % (es. 20)">
  <button type="submit">Cerca prodotti scontati</button>
</form>

<hr>

<!-- Qui verrà mostrato il risultato -->
<div id="result"></div>
</main>
<%@ include file="/includes/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/filter_by.js"></script>
</html>