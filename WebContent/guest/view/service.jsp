<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Service Page</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Service.css">
</head>
<body>

  <h1>Salve, di cosa hai bisogno?</h1>

  <div class="links">
    <a href="assistance.jsp">
      <i class="fas fa-life-ring"></i> Assistenza
    </a>
    <a href="${pageContext.request.contextPath}/registeredUser/view/refund.jsp">
      <i class="fas fa-undo-alt"></i> Rimborso Ordini
    </a>
  </div>

</body>
</html>