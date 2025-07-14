<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
     request.getAttribute("user");
%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin User Profile Page</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/User_profile.css">
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Admin_Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/admin_header.jsp" %>

<div class="container">
  <!-- Messaggi di errore -->
 <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>


<img src="${user.imagePath}" alt="User image"></img>
<p>Id: ${user.id}</p>
<p>Username: ${user.username}</p>
<p>Name: ${user.name} </p>
<p>Last Name: ${user.lastName}</p>
<p>Telephone number: ${user.telephone}</p>
<p>Email: ${user.email}</p>
<p>Password: ${user.password}</p>

<div class="action-buttons">

    <form action="${pageContext.request.contextPath}/UploadCart" method="post">
      <input type="hidden" name="idCart" value="${user.id}" />
      <button type="submit" class="submit-btn">
        <i class="fas fa-shopping-cart"></i> Visualizza Carrello
      </button>
    </form>

    <form action="${pageContext.request.contextPath}/UploadOrders" method="post">
      <input type="hidden" name="idCart" value="${user.id}" />
      <button type="submit" class="submit-btn">
        <i class="fas fa-receipt"></i> Visualizza Ordini
      </button>
    </form>
  </div>
  </div>
<%@ include file="/includes/admin_footer.jsp" %>
</body>
</html>