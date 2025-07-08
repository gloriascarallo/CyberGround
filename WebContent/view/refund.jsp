<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Refund page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Refund.css?v=3"/>
</head>
<body>

<h2>Pagina di Rimborso</h2>

<form action="<%=request.getContextPath() %>/Refund" method="post">

 <div>

	<label for="id">Inserisci l'ID dell'ordine:</label>
	<input type="text" name="id" id="id" onchange="validateFormElement(this, IDPattern, document.getElementById('errorID'), errorIDMessage" required>
	<span id="errorID"></span> <br>

<div id="form-buttons">
<input type="submit" value="Invia" onclick="return validate()">
<input type="reset" value="Reset">
</div>

</div>
</form>
</body>
</html>