<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Address Page</title>
</head>
<body>

<h2 align="center">Aggiunta Indirizzo</h2>

<form action="Add_address" method="post">
<fieldset>
<legend>Compilare il campo</legend>
<div align="center">

<label for="indirizzo">Inserisci nuovo indirizzo:</label> <br>
	<input type="text" name="indirizzo" id="indirizzo" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress'), errorAddressMessage" required > 
	<span id="errorAddress"></span> <br>
	<input type="submit" value="Invia" onclick="return validate()">
	<input type="reset" value="Annulla">

</div>
</fieldset>
</form>
</body>
</html>