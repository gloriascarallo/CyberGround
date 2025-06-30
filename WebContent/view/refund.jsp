<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Refund page</title>
</head>
<body>

<h2 align="center">Pagina di Rimborso</h2>

<form action="<%=request.getContextPath() %>Refund" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div align="center">

	<label for="id">Inserisci l'ID dell'ordine:</label>
	<input type="text" name="id" id="id" onchange="validateFormElement(this, IDPattern, document.getElementById('errorID'), errorIDMessage" required>
	<span id="errorID"></span> <br>
	
	<label for="email">E-mail a cui è associato l'ordine:</label>
	<input type="email" name="email" id="email" onchange="validateFormElement(this, emailPattern, document.getElementById('errorEmail'), errorEmailMessage" required>
	<span id="errorEmail"></span> <br>
	<input type="submit" value="Invia" onclick="return validate()">

</div>
</fieldset>
</form>
</body>
</html>