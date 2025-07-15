<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Refund page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Refund.css?v=5"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Layout.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<h2>Pagina di Rimborso</h2>

<form action="<%=request.getContextPath() %>/Refund" method="get">

 <div>
 
 <!-- Se ci sono errori -->
  <%
    String errors = (String) request.getAttribute("errors");
    if (errors != null && !errors.trim().isEmpty()) {
  %>
    <div class="error"><%= errors %></div>
  <% } %>

	<label for="id">Inserisci l'ID dell'ordine:</label>
	<input type="text" name="id" id="id" onchange="validateFormElement(this, IDPattern, document.getElementById('errorID'), errorIDMessage)">
	<span id="errorID"></span> <br>

<div id="form-buttons">
<input type="submit" value="Invia" onclick="return validateRefundForm()">
<input type="reset" value="Reset">
</div>

</div>
</form>
<%@ include file="/includes/footer.jsp" %>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>