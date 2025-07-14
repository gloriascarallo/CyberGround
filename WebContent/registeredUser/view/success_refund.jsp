<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Product Refund</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Success_refund.css?v=2">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <div class="container">
    <h1><i class="fas fa-money-check-alt"></i> Rimborso completato!</h1>
    <p>Il tuo rimborso ha avuto successo.</p>
    <a href="${pageContext.request.contextPath}/guest/view/index.jsp" class="button"><i class="fas fa-home"></i> Torna alla Home</a>
  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
