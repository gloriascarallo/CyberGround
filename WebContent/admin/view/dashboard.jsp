<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Admin Dashboard Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Dashboard.css?v=2" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<main class="dashboard-container">
  <h2>Area di Amministrazione</h2>

  <div class="dashboard-buttons">
    <a href="${pageContext.request.contextPath}/admin/view/products.jsp" class="dashboard-btn">
      <i class="fas fa-box-open"></i> Gestione Prodotti
    </a>

    <a href="${pageContext.request.contextPath}/admin/view/users.jsp" class="dashboard-btn">
      <i class="fas fa-users-cog"></i> Gestione Utenti
    </a>
    
    <form action="${pageContext.request.contextPath}/Logout" method="post">
    <button type="submit" class="logout-button">
      <i class="fas fa-sign-out-alt"></i> Logout
    </button>
  </form>
  </div>
</main>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
</html>
