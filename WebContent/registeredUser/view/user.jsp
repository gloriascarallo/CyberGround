<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="model.RegisteredUserBean"%>


<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Hello, ${user.name}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/User.css?v=6">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<div class="container">
<p>Username: ${user.username}</p>
<p>Name: ${user.name} </p>
<p>Last Name: ${user.lastName}</p>
<p>Telephone number: ${user.telephone}</p>
<p>Email: ${user.email}</p>


<form action="${pageContext.request.contextPath}/Orders" method="get">
    <button type="submit" class="orders-button">
        <i class="fas fa-box"></i> Visualizza i tuoi ordini
    </button>
</form>

<form action="${pageContext.request.contextPath}/RegisteredUserAddresses" method="get">
<button type="submit" class="dati">
  <i class="fas fa-map-marker-alt"></i> Visualizza indirizzi
</button>
</form>

<br>

<form action="${pageContext.request.contextPath}/RegisteredUserMethodsPayment" method="get">
<button type="submit" class="dati">
  <i class="fas fa-credit-card"></i> Visualizza metodi di pagamento
</button>
</form>

<br><br>

<form action="${pageContext.request.contextPath}/Logout" method="post">
    <button type="submit" class="logout-button">
      <i class="fas fa-sign-out-alt"></i> Logout
    </button>
  </form>
  
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>