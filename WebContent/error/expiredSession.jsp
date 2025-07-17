<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Sessione Scaduta</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/ExpiredSession.css?v=2">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <div id="container">
    <h2><i class="fas fa-hourglass-end"></i> Sessione Scaduta</h2>
    <p>La tua sessione è scaduta per inattività  o timeout di sicurezza oppure il carrello era vuoto.</p>
    <a href="/CyberGround/Home" class="back-button">
      <i class="fas fa-sign-in-alt"></i> Torna alla pagina di login
    </a>
  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
