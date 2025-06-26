<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Page</title>
</head>
<body>

<h2>Seleziona indirizzo e metodo di pagamento</h2>

<div>
Indirizzo <br>
<select name="indirizzo" id="indirizzo">
	<option value=""> </option>
</select>
</div>

Vuoi aggiungere un nuovo indirizzo? <a href="add_address.jsp">Clicca qui!</a>

<div>
Metodo di pagamento <br>
<select name="metodo_pagamento" id="metodo_pagamento">
	<option value=""> </option>
</select>
</div>

Vuoi aggiungere un nuovo metodo di pagamento? <a href="add_payment_method.jsp">Clicca qui!</a>

<div align="right">
<input type="button" value="Conferma pagamento" onclick="">
</div>

</body>
</html>