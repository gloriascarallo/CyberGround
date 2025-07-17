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
<title>Add Payment Method Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Add_payment_method.css?v=3"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Aggiunta Metodo di Pagamento</h2>

<form name="Add_method_paymentForm"action="<%=request.getContextPath() %>/Add_payment_method" method="post">
${message}
<fieldset>
<legend>Compilare i campi</legend>
<div>
<%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>
<label>Inserisci nuovo metodo di pagamento</label> <br>
	<label for="PAN">PAN:</label>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN'), errorPANMessage)" required> 
	<span id="errorPAN"></span> <br>
	<label for="Scadenza">Data di Scadenza:</label>
	<input type="text" name="Scadenza" id="Scadenza" placeholder="##/##" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza'), errorScadenzaMessage)" required>
	<span id="errorScadenza"></span>
	<label for="CVC">CVC:</label>
	<input type="text" name="CVC" id="CVC" placeholder="### or ####" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC'), errorCVCMessage)" required>
	<span id="errorCVC"></span> <br>
	<input type="hidden" name="redirectAfter" value="${param.redirectAfter}">
	<input type="submit" value="Invia" onclick="return validateAdd_method_paymentForm()">
	<input type="reset" value="Reset">

</div>
</fieldset>
</form>
<%@ include file="/includes/footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js?v=3" type="text/javascript"></script>
</html>