<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Accesso Negato</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/AccessDeniedAdmin.css?v=2">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <div id="container">
    <h2><i class="fas fa-ban"></i> Accesso Negato</h2>
    <p>Questa sezione è riservata agli amministratori.</p>
    <a href="/CyberGround/guest/view/login.jsp" class="back-button">
      <i class="fas fa-sign-in-alt"></i> Torna alla pagina di login
    </a>
  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
