<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Assistance Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Assistance.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

  <h2>Qui puoi trovare link utili alla tua assistenza</h2>
  <div class="links">
    <a href="FAQ.jsp">
      <i class="fa fa-question-circle"></i> FAQ
    </a>
    <a href="consult_us.jsp">
      <i class="fa fa-comments-o"></i> Consultaci
    </a>
  </div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>