<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Added to cart Page</title>
<script src="${pageContext.request.contextPath}/scripts/after_adding_to_cart.js"></script>

</head>
<body>

<h3>Il prodotto è stato aggiunto al carrello</h3>

<p>Desideri andare al carrello oppure tornare alla pagina precedente?</p>

<button onclick="vaiAlCarrello()">Carrello</button> <button onclick="tornaIndietro()">Indietro</button>

</body>
</html>