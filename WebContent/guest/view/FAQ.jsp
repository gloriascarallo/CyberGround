<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>FAQ Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/FAQ.css?v=4"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<script src="/CyberGround/scripts/FAQ.js"></script>
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div id="faq-container">

<h2>Domande Frequenti</h2>


<div class="faq-set" onclick="toggleAnswer(this)">
<div class="faq-question">
<h3>Come effettuo un reso?</h3><span class="arrow">&#9660;</span>
</div>

<div class="faq-answer">
<p>Per effettuare un reso sul nostro sito basta dirigersi sulla pagina apposita alla gestione dei resi, 
che può trovare <a href="refund.jsp">qui</a>.</p></div>
</div>

<div class="faq-set" onclick="toggleAnswer(this)">
<div class="faq-question">
<h3>Quanto tempo ho a disposizione per poter effettuare un reso?</h3><span class="arrow">&#9660;</span>
</div>

<div class="faq-answer">
<p>Per poter effettuare un reso ha tempo fino a 14 giorni dal momento in cui il pacco arriva a destinazione.</p></div>
</div>

<div class="faq-set" onclick="toggleAnswer(this)">
<div class="faq-question">
<h3>Come posso controllare la mia spedizione?</h3><span class="arrow">&#9660;</span>
</div>

<div class="faq-answer">
<p>Per controllare lo stato del vostro ordine basta recarsi nella propria Area Personale e cliccare su "I Miei Ordini".</p></div>
</div>

<div class="faq-set" onclick="toggleAnswer(this)">
<div class="faq-question">
<h3>Ho riscontrato un problema, c'è un modo per contattare qualcuno?</h3><span class="arrow">&#9660;</span>
</div>

<div class="faq-answer">
<p>Se hai bisogno di aiuto puoi contattare il nostro operatore <a href="consult_us.html">qui</a>.</p></div>
</div>
</div>
<%@ include file="/includes/footer.jsp" %>

</body>
</html>