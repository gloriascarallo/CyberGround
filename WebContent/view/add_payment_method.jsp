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

<form action="Add_payment_method" method="post">
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
	<input type="submit" value="Invia" onclick="return validate()">
	<input type="reset" value="Annulla">

</div>
</fieldset>
</form>
</body>
</html>