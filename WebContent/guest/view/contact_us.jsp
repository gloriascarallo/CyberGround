<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Contact_us Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Contact_us.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <div id="container">
    <p>Per qualsiasi informazione inerente al nostro sito puoi contattarci a questi recapiti:</p>

    <h3><i class="fas fa-user"></i>Sabetta Francesco</h3>
    <p class="recapiti">
      <i class="fas fa-phone"></i> 320-6742029<br>
      <i class="fas fa-envelope"></i> f.sabetta7@studenti.unisa.it
    </p>

    <h3><i class="fas fa-user"></i>Scarallo Gloria</h3>
    <p class="recapiti">
      <i class="fas fa-phone"></i> 392-1226276<br>
      <i class="fas fa-envelope"></i> g.scarallo1@studenti.unisa.it
    </p>
  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>