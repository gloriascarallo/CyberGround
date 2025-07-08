<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>

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

<a href="<%=request.getContextPath()%>/User">

  <img src="<%=request.getContextPath()%>/images/user_profile.jpg" alt="Profilo" width="100" height="100">

</a>

</body>
</html>