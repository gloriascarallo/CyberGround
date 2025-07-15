<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Guide Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Guide.css?v=2"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Breve Guida Al Sito</h2>

<div id="isolante">
<p>Nella home troverai, oltre che i classici prodotti, assieme alla propria Area Utente ed al carrello, 
anche dei link per poterci contattare, per scoprire le ultime notizie, per accedere alla pagina degli sconti e per affacciarti ai nostri servizi, 
dai quali si può accedere alla pagina per i rimborsi e alla pagina di assistenza; da quest'ultima, infine, 
è possibile accedere alle FAQ, ossia le domande più frequenti che ci vengono fatte con annesse risposte,
e la pagina per poter consultare il nostro operatore in caso di ulteriori difficoltà .</p>

<p>Speriamo che questa breve guida possa esservi stata utile.</p>
</div>

<%@ include file="/includes/footer.jsp" %>

</body>
</html>