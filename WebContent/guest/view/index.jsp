<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Index Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Index.css?v=1" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

  <!-- HEADER -->
  <header>
    <div class="logo">
      <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo sito" />
      <h1>CyberGround</h1>
    </div>

    <div class="nav-links">
  <a href="${pageContext.request.contextPath}/guest/view/login.jsp">
    <i class="fas fa-sign-in-alt"></i> Login
  </a>
  <a href="${pageContext.request.contextPath}/guest/view/cart.jsp">
    <i class="fas fa-shopping-cart"></i> Carrello
  </a>
  <a href="${pageContext.request.contextPath}/registeredUser/view/user.jsp">
    <i class="fas fa-user-circle"></i> Area Utente
  </a>
</div>
  </header>

  <!-- CATEGORIE -->
  <div class="category-bar">
    <button onclick="loadProducts({action:'category', category:'telefonia'})">Telefonia</button>
    <button onclick="loadProducts({action:'category', category:'computer'})">Computer</button>
    <button onclick="loadProducts({action:'category', category:'console'})">Console</button>
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

<hr>

<!-- Qui verrà mostrato il risultato -->
<div id="result"></div>
</main>
</body>
<script src="${pageContext.request.contextPath}/scripts/index.js"></script>
</html>