<%@page import="bean.RegisteredUserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
     request.getAttribute("user");
     request.getAttribute("usernameString");
     request.getAttribute("idString");
%>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Admin Users Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/Users.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<main class="admin-users-container">

  <h2>Filtra Utente per Username</h2>
  
    <!-- Messaggi di errore -->
 <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>
  
  <p>${usernameString} ${user.username} <br>
  ${idString} ${user.id}</p>

  <form action="${pageContext.request.contextPath}/Filter_registeredusers_byUsername" method="get" class="filter-form">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username">
    <button type="submit"><i class="fas fa-search"></i> Cerca</button>
  </form>
  
  <h2>Accesso Pagina Utente</h2>
  
   <form action="${pageContext.request.contextPath}/UploadUser" method="post" class="filter-form">
   <label for="id">Vai alla pagina dell'utente con ID:</label>
   <input type="number" name="id" id="id">
   <button type="submit"><i class="fas fa-paper-plane"></i> Invia</button>
  </form>

</main>
<%@ include file="/includes/admin_footer.jsp" %>
<script src="${pageContext.request.contextPath}/scripts/admin_users_validation.js" type="text/javascript"></script>
</body>

</html>
