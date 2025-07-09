<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina di registrazione</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/Registration.css?v=3"/>
</head>
<body>

<%
if(request.getAttribute("errors")!=null){
String errors=(String)request.getAttribute("errors");
if(!errors.equals("")) {%> <div id="error"><%=errors %></div>

<% } } %>

<div id="container">
<h1>Registrati</h1>

<form name="registrationForm" id="registrationForm" action="<%=request.getContextPath()%>/Registration" method="post">

<div>
<label for="name">Inserisci nome: </label>
<input type="text" name="name" id="name" oninput="validateFormElement(this, namePattern, document.getElementById('errorName'), errorNameMessage)" placeholder="Inserisci il tuo nome"><span id="errorName"></span>
</div>

<div>
<label for="lastName">Inserisci cognome: </label>
<input type="text" name="lastName" id="lastName" onchange="validateFormElement(this, namePattern, document.getElementById('errorLastName'), errorNameMessage)" placeholder="Inserisci il tuo cognome"><span id="errorLastName"></span>
</div>

<div>
<label for="telephone">Inserisci numero di telefono: </label>
<input type="text" name="telephone" id="telephone" placeholder="##########" onchange="validateFormElement(this, telephonePattern, document.getElementById('errorTelephone'), errorTelephoneMessage)"><span id="errorTelephone"></span>
</div>

<div>
<label for="email">Inserisci email: </label>
<input type="text" name="email" id="email" onchange="validateFormElement(this, emailPattern, document.getElementById('errorEmail'), errorEmailMessage)" placeholder="username@domain.ext"><span id="errorEmail"></span>
</div>

<div id="addresses">
<div id="addressNum1">
<label for="address1"> Inserisci un indirizzo (o più indirizzi): </label>
<input type="text" name="address" id="address1" onchange="validateFormElement(this, addressPattern, document.getElementById('errorAddress1'), errorAddressMessage)" placeholder="Inserisci il tuo indirizzo"><input type="button" value="+" onclick="addAddress()">
<span id="errorAddress1"></span>
</div>
</div>

<div id="methodsPayment">
<div id="methodPaymentNum1">
<label for="methodPaymentPAN1"> Inserisci un metodo di pagamento (o più metodi di pagamento): </label><br>
PAN:<input type="text" name="methodPaymentPAN" id="methodPaymentPAN1" onchange="validateFormElement(this, PANPattern, document.getElementById('errorPAN1'), errorPANMessage)" placeholder="####-####-####-####">
<span id="errorPAN1"></span><br>
Data di scadenza:<input type="text" name="methodPaymentScadenza" id="methodPaymentScadenza1" onchange="validateFormElement(this, ScadenzaPattern, document.getElementById('errorScadenza1'), errorScadenzaMessage)" placeholder="##/##">
<span id="errorScadenza1"></span><br>
CVC:<input type="text" name="methodPaymentCVC" id="methodPaymentCVC1" onchange="validateFormElement(this, CVCPattern, document.getElementById('errorCVC1'), errorCVCMessage)" placeholder="### or ####"><input type="button" value="+" onclick="addMethodPayment()">
<span id="errorCVC1"></span><br>

</div>
</div>

<div>
<label for="username">Inserisci username: </label>
<input type="text" name="username" id="username" onchange="validateFormElement(this, usernamePattern, document.getElementById('errorUsername'), errorUsernameMessage)" placeholder="Inserisci il tuo username"><span id="errorUsername"></span><br>
</div>

<div>
<label for="password">Inserisci password: </label>
<input type="password" name="password" id="password" onchange="validateFormElement(this, passwordPattern, document.getElementById('errorPassword'), errorPasswordMessage)" placeholder="Inserisci la tua password"><span id="errorPassword"></span><br>
</div>

<div id="form-buttons">
<input type="submit" value="Registrati" onclick="return validateRegistrationForm()">
<input type="reset" value="Reset">
</div>

</form>
</div>
</body>
<script src="<%=request.getContextPath() %>/scripts/validation.js?v=3" type="text/javascript"></script>
</html>