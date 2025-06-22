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
<form action="" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div align="center">

	<label for="id">Inserisci l'ID dell'ordine:</label> <input type="text" name="id" id="id" required> <br>
	<label for="email">E-mail a cui è associato l'ordine:</label> <input type="email" name="email" id="email" required> <br>
	<input type="submit" value="Invia">

</div>
</fieldset>
</form>
</body>
</html>