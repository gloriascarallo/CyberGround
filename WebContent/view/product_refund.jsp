<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Refund Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Product_refund.css"/>
</head>
<body>

<h2 align="center">Rimborso Prodotto</h2>

<form action="<%=request.getContextPath()%>/Product_refund" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div>

	<label for="ritiro">Inserisci l'indirizzo dove verremo a ritirare il pacco:</label> <br>
	<input type="text" name="ritiro" id="ritiro" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress'), errorAddressMessage" required>
	<span id="errorAddress"></span> <br>
	
	<label>Inserisci i dati della carta sulla quale verrà addebitato il rimborso</label> <br>
	<label for="PAN">PAN:</label>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN'), errorPANMessage" required> 
	<span id="errorPAN"></span> <br>
	<label for="Scadenza">Data di Scadenza:</label>
	<input type="text" name="Scadenza" id="Scadenza" placeholder="##/##" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza'), errorScadenzaMessage" required>
	<span id="errorScadenza"></span>
	<label for="CVC">CVC:</label>
	<input type="text" name="CVC" id="CVC" placeholder="### or ####" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC'), errorCVCMessage" required>
	<span id="errorCVC"></span> <br>
	<input type="submit" value="Invia" onclick="return validate()">
	<input type="reset" value="Reset">
	
</div>
</fieldset>
</form>
</body>
</html>