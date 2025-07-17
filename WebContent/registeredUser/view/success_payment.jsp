<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Success Payment Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Success_payment.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <div class="container">
    <h1><i class="fas fa-check-circle"></i> Pagamento completato!</h1>
    <p>Il tuo pagamento è avvenuto con successo.</p>
    <a href="/CyberGround/UploadProducts" class="button">
  <i class="fas fa-home"></i> Torna alla Home
</a>

  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>