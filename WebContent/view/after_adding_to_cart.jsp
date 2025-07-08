<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Added to cart Page</title>
<script src="${pageContext.request.contextPath}/scripts/after_adding_to_cart.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/After_adding_to_cart.css"/>
</head>
<body>

<h3>Il prodotto è stato aggiunto al carrello</h3>

<div id="container">
<p>Desideri andare al carrello oppure tornare alla pagina precedente?</p>

<button onclick="vaiAlCarrello()">Carrello</button> <button onclick="tornaIndietro()">Indietro</button>
</div>

</body>
</html>