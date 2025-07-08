<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Address Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Add_address.css?v=3"/>
</head>
<body>

<h2>Aggiunta Indirizzo</h2>

<form name="Add_addressForm" action="<%=request.getContextPath() %>/Add_address" method="post">
<fieldset>
<legend>Compilare il campo</legend>
<div>

<label for="address">Inserisci nuovo indirizzo:</label> <br>
	<input type="text" name="address" id="address" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress'), errorAddressMessage" required > 
	<span id="errorAddress"></span> <br>
	<input type="submit" value="Invia" onclick="return validateAdd_addressForm()">
	<input type="reset" value="Reset">

</div>
</fieldset>
</form>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>