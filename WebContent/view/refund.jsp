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

<form action="<%=request.getContextPath() %>/Refund" method="post">
<fieldset>
<legend>Compilare i campi</legend>
<div align="center">

	<label for="id">Inserisci l'ID dell'ordine:</label>
	<input type="text" name="id" id="id" onchange="validateFormElement(this, IDPattern, document.getElementById('errorID'), errorIDMessage" required>
	<span id="errorID"></span> <br>


</div>
</fieldset>
</form>
</body>
</html>