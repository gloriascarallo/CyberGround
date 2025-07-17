<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
request.getAttribute("message");
%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Add Address Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Add_address.css?v=5"/>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Aggiunta Indirizzo</h2>

<form name="Add_addressForm" action="<%=request.getContextPath() %>/Add_address" method="post">
${message}
<fieldset>
<legend>Compilare il campo</legend>
<div>
<%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>
  
<label for="address">Inserisci nuovo indirizzo:</label> <br>
	<input type="text" name="address" id="address" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress'), errorAddressMessage)" > 
	<span id="errorAddress"></span> <br>
	<input type="hidden" name="redirectAfter" value="${param.redirectAfter}">
	<input type="submit" value="Invia" onclick="return validateAdd_addressForm()">
	<input type="reset" value="Reset">

</div>
</fieldset>
</form>
<%@ include file="/includes/footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>