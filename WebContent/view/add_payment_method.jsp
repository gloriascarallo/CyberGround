<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Payment Method Page</title>
</head>
<body>

<h2 align="center">Aggiunta Metodo di Pagamento</h2>

<form name="Add_method_paymentForm"action="<%=request.getContextPath() %>/Add_payment_method" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div align="center">

<label for="PAN">Inserisci nuovo metodo di pagamento:</label> <br>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN'), errorPANMessage" required> 
	<span id="errorPAN"></span> <br>
	<input type="text" name="Scadenza" placeholder="##/##" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza'), errorScadenzaMessage" required>
	<span id="errorScadenza"></span>
	<input type="text" name="CVC" placeholder="### or ####" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC'), errorCVCMessage" required>
	<span id="errorCVC"></span> <br>
	<input type="submit" value="Invia" onclick="return validateAdd_method_paymentForm()">
	<input type="reset" value="Annulla">

</div>
</fieldset>
</form>
</body>
<script src="<%=request.getContextPath()%>/scripts/validation.js" type="text/javascript"></script>
</html>