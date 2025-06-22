<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product_Refund Page</title>
</head>
<body>

<h2 align="center">Rimborso Prodotto</h2>
<form action="" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div align="center">

	<label for="ritiro">Inserisci l'indirizzo dove verremo a ritirare il pacco:</label> <br>
	<input type="text" name="ritiro" id="ritiro" required> <br>
	
	<label for="PAN">Inserisci i dati della carta sulla quale verrà addebitato il rimborso:</label> <br>
	<input type="text" name="PAN" id="PAN" placeholder="####-####-####-####" required> <br>
	<input type="text" name="scadenza" placeholder="##/##" required> <input type="text" name="CVC" placeholder="### or ####" required> <br>
	<input type="submit" value="Invia">
	<input type="reset" value="Annulla">
	
</div>
</fieldset>
</form>
</body>
</html>