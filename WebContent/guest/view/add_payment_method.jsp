<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Payment Method Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Add_payment_method.css?v=3"/>
</head>
<body>

<h2>Aggiunta Metodo di Pagamento</h2>

<form name="Add_method_paymentForm"action="<%=request.getContextPath() %>/Add_payment_method" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div>

<label>Inserisci nuovo metodo di pagamento</label> <br>
	<label for="PAN">PAN:</label>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN'), errorPANMessage" required> 
	<span id="errorPAN"></span> <br>
	<label for="Scadenza">Data di Scadenza:</label>
	<input type="text" name="Scadenza" id="Scadenza" placeholder="##/##" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza'), errorScadenzaMessage" required>
	<span id="errorScadenza"></span>
	<label for="CVC">CVC:</label>
	<input type="text" name="CVC" id="CVC" placeholder="### or ####" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC'), errorCVCMessage" required>
	<span id="errorCVC"></span> <br>
	<input type="submit" value="Invia" onclick="return validateAdd_method_paymentForm()">
	<input type="reset" value="Reset">

</div>
</fieldset>
</form>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>