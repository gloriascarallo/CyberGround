<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Service Page</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Service.css?v=2">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div id="container">
  <h1>Salve, di cosa hai bisogno?</h1>

  <div class="links">
    <a href="assistance.jsp">
      <i class="fas fa-life-ring"></i> Assistenza
    </a>
    <a href="${pageContext.request.contextPath}/registeredUser/view/refund.jsp">
      <i class="fas fa-undo-alt"></i> Rimborso Ordini
    </a>
  </div>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>