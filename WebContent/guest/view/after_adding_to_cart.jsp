<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Added to cart Page</title>
<script src="${pageContext.request.contextPath}/scripts/after_adding_to_cart.js?v=3"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/After_adding_to_cart.css?v=3"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h3>Il prodotto è stato aggiunto al carrello</h3>

<div id="container">
<p>Desideri andare al carrello oppure tornare alla pagina precedente?</p>

<button onclick="vaiAlCarrello()">Carrello</button> <button onclick="tornaIndietro()">Indietro</button>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>